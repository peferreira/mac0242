package mundo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import comunicacao.Resposta;
import mundo.elementos.Robo;

public class Arena extends Canvas {
	private Robo[] moveis;
	private MapaHexagonal mapa;
	private Zeus zeus;
	private LinkedList<Resposta> respostas;

	private int numRobos;
	private int numExercitos;
	private BufferStrategy strategy;

	public Arena(int numRobos, int tamX, int tamY) {
		this.numRobos = numRobos;

		moveis = new Robo[numRobos];

		mapa = new MapaHexagonal(tamX, tamY, 20, 800, 800);

		respostas = new LinkedList<Resposta>();
		zeus = new Zeus(mapa, respostas);

	}

	public LinkedList<Resposta> getRespostas() {
		return respostas;
	}

	public void draw() {
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0,0,800,800);
		mapa.draw(g);
		g.dispose();
		strategy.show();
	}

	public void initGraphics() {
		// create a frame to contain our game
		JFrame container = new JFrame("Batalha de Robôs");

		// get hold the content of the frame and set up the resolution of the
		// game
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(800, 800));
		panel.setLayout(null);

		// setup our canvas size and put it into the content of the frame
		setBounds(0, 0, 800, 800 );
		panel.add(this);

		// Tell AWT not to bother repainting our canvas since we're
		// going to do that our self in accelerated mode
		setIgnoreRepaint(true);

		// finally make the window visible
		container.pack();
		container.setResizable(false);
		container.setVisible(true);

		// add a listener to respond to the user closing the window. If they
		// do we'd like to exit the game
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// add a key input system (defined below) to our canvas
		// so we can respond to key pressed

		// request the focus so key events come to us
		requestFocus();

		// create the buffering strategy which will allow AWT
		// to manage our accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();

		// initialise the entities in our game so there's something
		// to see at startup
	}

	public void apagaRespostas() {
		respostas.clear();
	}

	public void atualiza() {
		zeus.atualiza();
	}

	public void inicializa() {
		FabricaRobos fr = new FabricaRobos(numRobos);
		fr.criaRobos();
		moveis = fr.getRobos();
		/* mapa.inicializa(); */
		numExercitos = 0;
	}

	private boolean eLinhaPar(int posLinha) {
		return (posLinha % 2) == 0;
	}

	public void moveCimaEsq(int idRobo) {
		int posI, posJ, novoPosI, novoPosJ;
		Hexagono hex;
		Robo movel = moveis[idRobo];
		posI = movel.getPosI();
		posJ = movel.getPosJ();
		if (eLinhaPar(posJ)) {
			novoPosI = posI - 1;
			novoPosJ = posJ - 1;
		} else {
			novoPosI = posI;
			novoPosJ = posJ - 1;
		}
		if (zeus.roboDentroDaArena(novoPosI, novoPosJ, idRobo)) {
			hex = mapa.getHexagono(novoPosI, novoPosJ);
			hex.adRequerente(movel);
		}
	}

	public void moveEsquerda(int idRobo) {
		int posJ, novoPosI;
		Robo movel = moveis[idRobo];
		novoPosI = movel.getPosI() - 1;
		posJ = movel.getPosJ();
		if (zeus.roboDentroDaArena(novoPosI, posJ, idRobo)) {
			Hexagono hex = mapa.getHexagono(novoPosI, posJ);
			hex.adRequerente(movel);
		}
	}

	public void moveBaixoEsq(int idRobo) {
		int posI, posJ, novoPosI, novoPosJ;
		Hexagono hex;
		Robo movel = moveis[idRobo];
		posI = movel.getPosI();
		posJ = movel.getPosJ();
		if (eLinhaPar(posJ)) {
			novoPosI = posI - 1;
			novoPosJ = posJ + 1;
		} else {
			novoPosI = posI;
			novoPosJ = posJ + 1;
		}
		if (zeus.roboDentroDaArena(novoPosI, novoPosJ, idRobo)) {
			hex = mapa.getHexagono(novoPosI, novoPosJ);
			hex.adRequerente(movel);
		}
	}

	public void moveCimaDir(int idRobo) {
		int posI, posJ, novoPosI, novoPosJ;
		Hexagono hex;
		Robo movel = moveis[idRobo];
		posI = movel.getPosI();
		posJ = movel.getPosJ();
		if (eLinhaPar(posJ)) {
			novoPosI = posI;
			novoPosJ = posJ - 1;
		} else {
			novoPosI = posI + 1;
			novoPosJ = posJ - 1;
		}
		if (zeus.roboDentroDaArena(novoPosI, novoPosJ, idRobo)) {
			hex = mapa.getHexagono(novoPosI, novoPosJ);
			hex.adRequerente(movel);
		}
	}

	public void moveDireita(int idRobo) {
		int posJ, novoPosI;
		Hexagono hex;
		Robo movel = moveis[idRobo];
		novoPosI = movel.getPosI() + 1;
		posJ = movel.getPosJ();
		if (zeus.roboDentroDaArena(novoPosI, posJ, idRobo)) {
			hex = mapa.getHexagono(novoPosI, posJ);
			hex.adRequerente(movel);
		}
	}

	public void moveBaixoDir(int idRobo) {
		int posI, posJ, novoPosI, novoPosJ;
		Hexagono hex;
		Robo movel = moveis[idRobo];
		posI = movel.getPosI();
		posJ = movel.getPosJ();
		if (eLinhaPar(posJ)) {
			novoPosI = posI;
			novoPosJ = posJ + 1;
		} else {
			novoPosI = posI + 1;
			novoPosJ = posJ + 1;
		}
		if (zeus.roboDentroDaArena(novoPosI, novoPosJ, idRobo)) {
			hex = mapa.getHexagono(novoPosI, novoPosJ);
			hex.adRequerente(movel);
		}
	}

	public void insereExercito() {
		moveis[0].setPosI(1);
		moveis[0].setPosJ(1);
		// Meramente informativo
		System.out.println("Inserção de um robo:");
		System.out.println("Id: " + 0);
		System.out.println("PosI: " + moveis[0].getPosI());
		System.out.println("PosJ: " + moveis[0].getPosJ());

		mapa.setNovoRobo(moveis[0]);
		/*
		moveis[1].setPosI(1);
		moveis[1].setPosJ(1);
		// Meramente informativo
		System.out.println("Inserção de um robo:");
		System.out.println("Id: " + 0);
		System.out.println("PosI: " + moveis[1].getPosI());
		System.out.println("PosJ: " + moveis[1].getPosJ());

		mapa.setNovoRobo(moveis[1]);*/
	}

	private void insere(int coluna) {
		if (numRobos <= mapa.getMaxJ()) {
			for (int k = 0; k < numRobos; k++) {
				moveis[k].setPosI(coluna);
				moveis[k].setPosJ(k);
				// Meramente informativo
				System.out.println("Inserção de um robo:");
				System.out.println("Id: " + k);
				System.out.println("PosI: " + moveis[k].getPosI());
				System.out.println("PosJ: " + moveis[k].getPosJ());

				mapa.setNovoRobo(moveis[k]);
			}
		}
	}

	/*
	 * public void removeExercito(){}
	 */

}
