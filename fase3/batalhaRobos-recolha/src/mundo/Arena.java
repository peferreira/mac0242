package mundo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import comunicacao.Resposta;
import comunicacao.RespostaDEP;
import mundo.elementos.Base;
import mundo.elementos.Cristal;
import mundo.elementos.Posicionavel;
import mundo.elementos.Robo;

public class Arena extends Canvas {
	private static final long serialVersionUID = 1L; // Adicionado para remover
														// o warning da classe
														// Arena

	private Robo[] moveis;
	private MapaHexagonal mapa;
	private Zeus zeus;
	private LinkedList<Resposta> respostas;
	private BufferStrategy strategy;
	// private int numCristais;
	private int numRobos;

	public Arena(int numRobos, int[] turnos) {
		this.numRobos = numRobos;
		moveis = new Robo[numRobos];
		mapa = new MapaHexagonal(5, 5, 30, 800, 800); // criacao do mapa
														// hexagonal
		respostas = new LinkedList<Resposta>();
		zeus = new Zeus(mapa, respostas, turnos);
	}

	public void draw() {
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 800);
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
		setBounds(0, 0, 800, 800);
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

	public LinkedList<Resposta> getRespostas() {
		return respostas;
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
	}

	private boolean eLinhaPar(int posLinha) {
		return (posLinha % 2) == 0;
	}

	/*
	 * public void moveCimaEsq(int idRobo) { int posI, posJ, novoPosI, novoPosJ;
	 * Hexagono hex; Robo movel = moveis[idRobo]; posI = movel.getPosI(); posJ =
	 * movel.getPosJ(); if (eLinhaPar(posJ)) { novoPosI = posI - 1; novoPosJ =
	 * posJ - 1; } else { novoPosI = posI; novoPosJ = posJ - 1; } if
	 * (zeus.roboDentroDaArena(novoPosI, novoPosJ, idRobo)) { hex =
	 * mapa.getHexagono(novoPosI, novoPosJ); hex.adRequerente(movel); } }
	 * 
	 * public void moveEsquerda(int idRobo) { int posJ, novoPosI; Robo movel =
	 * moveis[idRobo]; novoPosI = movel.getPosI() - 1; posJ = movel.getPosJ();
	 * if (zeus.roboDentroDaArena(novoPosI, posJ, idRobo)) { Hexagono hex =
	 * mapa.getHexagono(novoPosI, posJ); hex.adRequerente(movel); } }
	 * 
	 * public void moveBaixoEsq(int idRobo) { int posI, posJ, novoPosI,
	 * novoPosJ; Hexagono hex; Robo movel = moveis[idRobo]; posI =
	 * movel.getPosI(); posJ = movel.getPosJ(); if (eLinhaPar(posJ)) { novoPosI
	 * = posI - 1; novoPosJ = posJ + 1; } else { novoPosI = posI; novoPosJ =
	 * posJ + 1; } if (zeus.roboDentroDaArena(novoPosI, novoPosJ, idRobo)) { hex
	 * = mapa.getHexagono(novoPosI, novoPosJ); hex.adRequerente(movel); } }
	 * 
	 * public void moveCimaDir(int idRobo) { int posI, posJ, novoPosI, novoPosJ;
	 * Hexagono hex; Robo movel = moveis[idRobo]; posI = movel.getPosI(); posJ =
	 * movel.getPosJ(); if (eLinhaPar(posJ)) { novoPosI = posI; novoPosJ = posJ
	 * - 1; } else { novoPosI = posI + 1; novoPosJ = posJ - 1; } if
	 * (zeus.roboDentroDaArena(novoPosI, novoPosJ, idRobo)) { hex =
	 * mapa.getHexagono(novoPosI, novoPosJ); hex.adRequerente(movel); } }
	 * 
	 * public void moveDireita(int idRobo) { int posJ, novoPosI; Hexagono hex;
	 * Robo movel = moveis[idRobo]; novoPosI = movel.getPosI() + 1; posJ =
	 * movel.getPosJ(); if (zeus.roboDentroDaArena(novoPosI, posJ, idRobo)) {
	 * hex = mapa.getHexagono(novoPosI, posJ); hex.adRequerente(movel); } }
	 * 
	 * public void moveBaixoDir(int idRobo) { int posI, posJ, novoPosI,
	 * novoPosJ; Hexagono hex; Robo movel = moveis[idRobo]; posI =
	 * movel.getPosI(); posJ = movel.getPosJ(); if (eLinhaPar(posJ)) { novoPosI
	 * = posI; novoPosJ = posJ + 1; } else { novoPosI = posI + 1; novoPosJ =
	 * posJ + 1; } if (zeus.roboDentroDaArena(novoPosI, novoPosJ, idRobo)) { hex
	 * = mapa.getHexagono(novoPosI, novoPosJ); hex.adRequerente(movel); } }
	 */

	public void deposita(int idRobo, String dir) {
		System.out.println("pedido de recolha feito por robo:" +idRobo);
		int posI, posJ, depPosI, depPosJ;
		Hexagono hex;
		Robo movel = moveis[idRobo];
		Posicionavel posicionavel;
		posI = movel.getPosI();
		posJ = movel.getPosJ();
		depPosI = novoX(posI, posJ, dir);
		depPosJ = novoY(posJ, dir);
		if (movel.temCristal() && zeus.ehPossivelDepositar(depPosI, depPosJ, idRobo)) {
			hex = mapa.getHexagono(depPosI, depPosJ);
			posicionavel = hex.getOcupante();
			if(posicionavel instanceof Base){
				((Base) posicionavel).addCristal();
				movel.removeCristal();			
				zeus.criaResposta(new RespostaDEP(true,idRobo));
			}
			else{
				zeus.criaResposta(new RespostaDEP(false,idRobo));
			}
		}
	}
	
	public void recolhe(int idRobo, String dir) {
		int posI, posJ, novoPosI, novoPosJ;
		Hexagono hex;
		Robo movel = moveis[idRobo];
		posI = movel.getPosI();
		posJ = movel.getPosJ();
		novoPosI = novoX(posI, posJ, dir);
		novoPosJ = novoY(posJ, dir);
		if (zeus.ehPossivelRecolher(novoPosI, novoPosJ, idRobo)) {
			hex = mapa.getHexagono(novoPosI, novoPosJ);
			hex.adMinerador(movel);
		}
	}

	public void movimento(int idRobo, String dir) {
		int posI, posJ, novoPosI, novoPosJ;
		Hexagono hex;
		Robo movel = moveis[idRobo];
		posI = movel.getPosI();
		posJ = movel.getPosJ();
		novoPosI = novoX(posI, posJ, dir);
		novoPosJ = novoY(posJ, dir);
		if (zeus.ehPossivelMover(novoPosI, novoPosJ, idRobo)) {
			hex = mapa.getHexagono(novoPosI, novoPosJ);
			hex.adRequerente(movel);
		}
	}

	private int novoY(int y, String dir) {
		switch (dir) {
		case "NE":
		case "NW":
			return y - 1;
		case "E":
		case "W":
			return y;
		case "SW":
		case "SE":
			return y + 1;
		default:
			System.out.println("Direcao inválida!");
			return -1;
		}
	}

	private int novoX(int x, int y, String dir) {
		switch (dir) {
		case "SE":
		case "NE":
			return (eLinhaPar(y) == true) ? x : x + 1;
		case "SW":
		case "NW":
			return (eLinhaPar(y) == true) ? x - 1 : x;
		case "E":
			return x + 1;
		case "W":
			return x - 1;
		default:
			System.out.println("Direcao inválida!");
			return -1;
		}
	}

	private int insereExercito1(int roboAtual) {
		int numRobosAux = roboAtual;
		// Meramente informativo
		System.out.println("Exercito 1:");
		for (int i = 0; i <= 4 && i < mapa.getMaxI(); i += 2) {
			for (int j = 1; j < mapa.getMaxJ(); j += 2) {
				if (numRobosAux < (numRobos / 2)) {
					moveis[numRobosAux].setPosI(i);
					moveis[numRobosAux].setPosJ(j);
					moveis[numRobosAux].setExercito(1);

					// Meramente informativo
					System.out.println("Inserção de um robo:");
					System.out.println("Id: " + numRobosAux);
					System.out
							.println("PosI: " + moveis[numRobosAux].getPosI());
					System.out
							.println("PosJ: " + moveis[numRobosAux].getPosJ());

					mapa.setNovoRobo(moveis[numRobosAux]);
					numRobosAux++;
				} else {
					break;
				}
			}
			if (numRobosAux >= (numRobos / 2)) {
				break;
			}
		}
		return numRobosAux;
	}

	private int insereExercito2(int roboAtual) {
		int numRobosAux = roboAtual;
		// Meramente informativo
		System.out.println("Exercito 2:");
		for (int i = mapa.getMaxI() - 1; i >= (mapa.getMaxI() - 5) && i >= 0; i -= 2) {
			for (int j = 1; j < mapa.getMaxJ(); j += 2) {
				if (numRobosAux < numRobos) {
					moveis[numRobosAux].setPosI(i);
					moveis[numRobosAux].setPosJ(j);
					moveis[numRobosAux].setExercito(2);

					// Meramente informativo
					System.out.println("Inserção de um robo:");
					System.out.println("Id: " + numRobosAux);
					System.out
							.println("PosI: " + moveis[numRobosAux].getPosI());
					System.out
							.println("PosJ: " + moveis[numRobosAux].getPosJ());

					mapa.setNovoRobo(moveis[numRobosAux]);
					numRobosAux++;
				} else {
					break;
				}
			}
			if (numRobosAux >= numRobos) {
				break;
			}
		}
		return numRobosAux;
	}

	public void insereExercitos() {
		int roboAtual = 0;
		roboAtual = insereExercito1(roboAtual);
		roboAtual = insereExercito2(roboAtual);
		if (roboAtual < numRobos) {
			System.out
					.println("Atenção! Excesso de robos para o tamanho do mapa!");
			System.exit(1);
		}

	}

	// Para o teste com um robo e um cristal
	public void insereUmRobo() {
		moveis[0].setPosI(0);
		moveis[0].setPosJ(1);
		mapa.setNovoRobo(moveis[0]);
	}
	public void insereUmCristal() {
		Hexagono h = mapa.getHexagono(1, 1);
		h.setOcupante(new Cristal(1));
	}

	public void insereCristais(int n) {
		int t, cristaisColocados, auxr, auxs;
		Random r, s;
		Hexagono h;
		r = new Random();
		s = new Random();
		t = 0;
		cristaisColocados = 0;
		while (cristaisColocados < n && t < 3 * n) {
			auxr = r.nextInt(mapa.getMaxI());
			auxs = s.nextInt(mapa.getMaxJ());
			System.out.println("Inserindo Cristal:");
			System.out.println("i: " + auxr);
			System.out.println("j: " + auxs);
			h = mapa.getHexagono(auxs, auxr);
			if (!h.temOcupante()) {
				h.setOcupante(new Cristal(1));
				cristaisColocados++;
			}
			t++;
		}
		// numCristais = cristaisColocados;
	}
	
	public void insereBase(){
		Hexagono h = mapa.getHexagono(0, 0);
		h.setOcupante(new Base(1));
		
	}

	public void removeExercito(int idExercito) {
		for (int j = 0; j < mapa.getMaxJ(); j++) {
			for (int i = 0; i < mapa.getMaxI(); i++) {

				Posicionavel ocupante = mapa.getHexagono(i, j).getOcupante();
				if (ocupante instanceof Robo) {
					if (((Robo) ocupante).getExercito() == idExercito)
						mapa.getHexagono(i, j).retiraOcupante();
				}
			}
		}
	}





}
