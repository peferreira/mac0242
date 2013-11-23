package mundo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import comunicacao.Resposta;
import comunicacao.RespostaDEP;
import comunicacao.RespostaSCAN;
import mundo.elementos.Bala;
import mundo.elementos.Base;
import mundo.elementos.Cristal;
import mundo.elementos.Posicionavel;
import mundo.elementos.Robo;

public class Arena extends Canvas {
	private static final long serialVersionUID = 1L; // Adicionado para remover
														// o warning da classe
														// Arena

	private Robo[] moveis;
	private Base[] bases;
	private MapaHexagonal mapa;
	private Zeus zeus;
	private LinkedList<Resposta> respostas;
	private BufferStrategy strategy;
	// private int numCristais;
	private int numRobos;
	private int numBases;
	private Random rand;

	public Arena(int numRobos, int numBases, int[] turnos) {
		rand = new Random(Double.doubleToLongBits(Math.random()));
		this.numRobos = numRobos;
		this.numBases = numBases;
		moveis = new Robo[numRobos];
		bases = new Base[numBases];
		mapa = new MapaHexagonal(15, 15, 30, 800, 800); // criacao do mapa
														// hexagonal
		respostas = new LinkedList<Resposta>();
		zeus = new Zeus(mapa, respostas, turnos);
	}

	public void draw() {
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 800);

		long startTime = System.nanoTime();
		mapa.draw(g);

		long endTime = System.nanoTime();

		long duration = endTime - startTime;
		g.dispose();

		strategy.show();

		System.out.println("tempo desenhando o mapa" + duration);
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
		FabricaRobos fr = new FabricaRobos(numRobos, numBases);
		fr.criaRobos();
		moveis = fr.getRobos();
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
		System.out.println("pedido de recolha feito por robo:" + idRobo);
		int posI, posJ, depPosI, depPosJ;
		Hexagono hex;
		Robo movel = moveis[idRobo];
		posI = movel.getPosI();
		posJ = movel.getPosJ();
		depPosI = zeus.novoX(posI, posJ, dir);
		depPosJ = zeus.novoY(posJ, dir);
		if (movel.temCristal()) {
			if (zeus.ehPossivelDepositar(depPosI, depPosJ, idRobo)) {
				hex = mapa.getHexagono(depPosI, depPosJ);
				((Base) hex.getOcupante()).addCristal();
				movel.removeCristal();
				zeus.criaResposta(new RespostaDEP(true, idRobo));
			}
		} else {
			zeus.criaResposta(new RespostaDEP(false, idRobo));
		}
	}

	public void recolhe(int idRobo, String dir) {
		int posI, posJ, novoPosI, novoPosJ;
		Hexagono hex;
		Robo movel = moveis[idRobo];
		posI = movel.getPosI();
		posJ = movel.getPosJ();
		novoPosI = zeus.novoX(posI, posJ, dir);
		novoPosJ = zeus.novoY(posJ, dir);
		if (!movel.temCristal()) {
			if (zeus.ehPossivelRecolher(novoPosI, novoPosJ, idRobo)) {
				hex = mapa.getHexagono(novoPosI, novoPosJ);
				hex.adMinerador(movel);
			}
		} else {
			zeus.criaResposta(new RespostaDEP(false, idRobo));
		}
	}

	public void movimento(int idRobo, String dir) {
		int posI, posJ, novoPosI, novoPosJ;
		Hexagono hex;
		Robo movel = moveis[idRobo];
		posI = movel.getPosI();
		posJ = movel.getPosJ();
		novoPosI = zeus.novoX(posI, posJ, dir);
		novoPosJ = zeus.novoY(posJ, dir);
		if (zeus.ehPossivelMover(novoPosI, novoPosJ, idRobo)) {
			hex = mapa.getHexagono(novoPosI, novoPosJ);
			hex.adRequerente(movel);
		}
	}
	
	public void ataque(int idRobo, String dir) {
		Hexagono hex;
		Bala b = new Bala(dir);
		Robo movel = moveis[idRobo];
		hex = mapa.getHexagono(movel.getPosI(), movel.getPosJ());
		hex.addNovoAtaque(b);
	}

	public void scanCristal(int idRobo) {
		int posI, posJ, novoPosI, novoPosJ;
		String[] dirs = { "NE", "E", "SE", "SW", "W", "NW" };
		Robo movel = moveis[idRobo];
		posI = movel.getPosI();
		posJ = movel.getPosJ();
		for (int i = 0; i < dirs.length; i++) {
			novoPosI = zeus.novoX(posI, posJ, dirs[i]);
			novoPosJ = zeus.novoY(posJ, dirs[i]);
			if (zeus.cristalDescoberto(novoPosI, novoPosJ, idRobo, dirs[i])) {
				return;
			}
		}
		zeus.criaResposta(new RespostaSCAN(false, idRobo));
	}

	public void scanRobo(int idRobo, int dist) {
		int posI, posJ, novoPosI, novoPosJ;
		String[] dirs = { "NE", "E", "SE", "SW", "W", "NW" };
		Robo movel = moveis[idRobo];
		posI = movel.getPosI();
		posJ = movel.getPosJ();
		for (int i = 0; i < dirs.length; i++) {
			novoPosI = zeus.novoX(posI, posJ, dirs[i]);
			novoPosJ = zeus.novoY(posJ, dirs[i]);
			for (int j = 0; j < dist; j++) {
				if (zeus.inimigoDescoberto(novoPosI, novoPosJ, idRobo,
						movel.getExercito(), dirs[i])) {
					return;
				}
				novoPosI = zeus.novoX(novoPosI, novoPosJ, dirs[i]);
				novoPosJ = zeus.novoY(novoPosJ, dirs[i]);
			}
		}
		zeus.criaResposta(new RespostaSCAN(false, idRobo));
	}

	public void scanDir(int idRobo) {
		int posI, posJ, novoPosI, novoPosJ, k;
		ArrayList<String> dirs = new ArrayList<String>();
		Robo movel = moveis[idRobo];
		posI = movel.getPosI();
		posJ = movel.getPosJ();
		dirs.add("NE");
		dirs.add("E");
		dirs.add("SE");
		dirs.add("SW");
		dirs.add("W");
		dirs.add("NW");
		for (int i = 0; i < 6; i++) {
			k = rand.nextInt(dirs.size());
			novoPosI = zeus.novoX(posI, posJ, dirs.get(k));
			novoPosJ = zeus.novoY(posJ, dirs.get(k));
			if (zeus.dirLivre(novoPosI, novoPosJ, idRobo, dirs.get(k))) {
				return;
			}
		}
		zeus.criaResposta(new RespostaSCAN(false, idRobo));
	}

	public void regressoBase(int idRobo) {
		int posI, posJ, novoPosI, novoPosJ, basePosI, basePosJ;
		Hexagono hex;
		Base b;
		String dir;
		Robo movel = moveis[idRobo];
		ArrayList<String> lados = new ArrayList<String>();
		posI = movel.getPosI();
		posJ = movel.getPosJ();
		b = bases[movel.getExercito()];
		basePosI = b.getPosI();
		basePosJ = b.getPosJ();
		lados.add("E");
		lados.add("W");
		lados.add("NE");
		lados.add("NW");
		lados.add("SE");
		lados.add("SW");
		if (basePosI == 0 && basePosJ == 0) {
			dir = "NW";
		} else if (basePosI == 0 && basePosJ == (mapa.getMaxJ() - 1)) {
			dir = "SW";
		} else if (basePosI == (mapa.getMaxI() - 1) && basePosJ == 0) {
			dir = "NE";
		} else {
			dir = "SE";
		}
		novoPosI = zeus.novoX(posI, posJ, dir);
		novoPosJ = zeus.novoY(posJ, dir);
		if (!zeus.dentroDaArena(novoPosI, novoPosJ)) {
			for (int i = 0; i < lados.size(); i++) {
				novoPosI = zeus.novoX(posI, posJ, lados.get(i));
				novoPosJ = zeus.novoY(posJ, lados.get(i));
				if (zeus.dentroDaArena(novoPosI, novoPosJ)) {
					hex = mapa.getHexagono(novoPosI, novoPosJ);
					if (hex.temOcupante()
							&& (hex.getOcupante() instanceof Base)) {
						dir = lados.get(i);
						break;
					}
				}
			}
		}

		novoPosI = zeus.novoX(posI, posJ, dir);
		novoPosJ = zeus.novoY(posJ, dir);
		/*
		 * if (zeus.dentroDaArena(novoPosI, novoPosJ, idRobo)) { hex =
		 * mapa.getHexagono(novoPosI, novoPosJ); if (hex.temOcupante() &&
		 * (hex.getOcupante() instanceof Cristal)) { if (novoPosJ != basePosJ &&
		 * novoPosI != basePosI){ if(novoPosI > basePosI){ dir = "W"; }else {
		 * dir = "E"; } }
		 * 
		 * if (novoPosJ == basePosJ){ if(novoPosI > basePosI){ dir = "NE"; }else
		 * { dir = "NW"; } }
		 * 
		 * if (novoPosI == basePosI && novoPosJ < basePosJ){ if (novoPosI == 0){
		 * dir = "E"; } else if (novoPosI == mapa.getMaxI()){ dir = "W"; } }
		 * 
		 * } }
		 */
		if (zeus.dentroDaArena(novoPosI, novoPosJ)) {
			hex = mapa.getHexagono(novoPosI, novoPosJ);
			if (hex.temOcupante() && (hex.getOcupante() instanceof Cristal)) {

				if (basePosI == 0 && basePosJ == 0) {
					lados.remove("SE");
				} else if (basePosI == 0 && basePosJ == (mapa.getMaxJ() - 1)) {
					lados.remove("NE");
				} else if (basePosI == (mapa.getMaxI() - 1) && basePosJ == 0) {
					lados.remove("SW");
				} else {
					lados.remove("NW");
				}
				System.out.println("Size lados: " + lados.size());
				for (int i = 0; i < lados.size(); i++) {
					dir = lados.remove(rand.nextInt(lados.size()));

					novoPosI = zeus.novoX(posI, posJ, dir);
					novoPosJ = zeus.novoY(posJ, dir);
					if (zeus.dentroDaArena(novoPosI, novoPosJ)) {
						hex = mapa.getHexagono(novoPosI, novoPosJ);

						if (!hex.temOcupante()) {
							break;
						}
					}
				}
			}
		}
		/*
		 * novoPosI = zeus.novoX(posI, posJ, dir); novoPosJ = zeus.novoY(posJ, dir);
		 */
		zeus.regressoBase(posI, posJ, novoPosI, novoPosJ, basePosI, basePosJ,
				idRobo, dir);
	}

	
	/*
	 * private int insereExercito1(int roboAtual) { int numRobosAux = roboAtual;
	 * // Meramente informativo System.out.println("Exercito 1:"); for (int i =
	 * 0; i <= 4 && i < mapa.getMaxI(); i += 2) { for (int j = 1; j <
	 * mapa.getMaxJ(); j += 2) { if (numRobosAux < (numRobos / 2)) {
	 * moveis[numRobosAux].setPosI(i); moveis[numRobosAux].setPosJ(j);
	 * moveis[numRobosAux].setExercito(1);
	 * 
	 * // Meramente informativo System.out.println("Inserção de um robo:");
	 * System.out.println("Id: " + numRobosAux); System.out .println("PosI: " +
	 * moveis[numRobosAux].getPosI()); System.out .println("PosJ: " +
	 * moveis[numRobosAux].getPosJ());
	 * 
	 * mapa.setNovoRobo(moveis[numRobosAux]); numRobosAux++; } else { break; } }
	 * if (numRobosAux >= (numRobos / 2)) { break; } } return numRobosAux; }
	 * 
	 * private int insereExercito2(int roboAtual) { int numRobosAux = roboAtual;
	 * // Meramente informativo System.out.println("Exercito 2:"); for (int i =
	 * mapa.getMaxI() - 1; i >= (mapa.getMaxI() - 5) && i >= 0; i -= 2) { for
	 * (int j = 1; j < mapa.getMaxJ(); j += 2) { if (numRobosAux < numRobos) {
	 * moveis[numRobosAux].setPosI(i); moveis[numRobosAux].setPosJ(j);
	 * moveis[numRobosAux].setExercito(2);
	 * 
	 * // Meramente informativo System.out.println("Inserção de um robo:");
	 * System.out.println("Id: " + numRobosAux); System.out .println("PosI: " +
	 * moveis[numRobosAux].getPosI()); System.out .println("PosJ: " +
	 * moveis[numRobosAux].getPosJ());
	 * 
	 * mapa.setNovoRobo(moveis[numRobosAux]); numRobosAux++; } else { break; } }
	 * if (numRobosAux >= numRobos) { break; } } return numRobosAux; }
	 * 
	 * public void insereExercitos() { int roboAtual = 0; roboAtual =
	 * insereExercito1(roboAtual); roboAtual = insereExercito2(roboAtual); if
	 * (roboAtual < numRobos) { System.out
	 * .println("Atenção! Excesso de robos para o tamanho do mapa!");
	 * System.exit(1); }
	 * 
	 * }
	 */
	
	public void insereDoisRobosInimigos() {
		Hexagono h = mapa.getHexagono(4, 5);
		moveis[0].setPosI(4);
		moveis[0].setPosJ(5);
		moveis[0].setExercito(0);
		h.setOcupante(moveis[0]);
		h = mapa.getHexagono(9, 5);
		moveis[1].setPosI(9);
		moveis[1].setPosJ(5);
		moveis[1].setExercito(1);
		h.setOcupante(moveis[1]);
	}

	public void insereUmRobo() {
		Hexagono h = mapa.getHexagono(4, 5);
		moveis[0].setPosI(4);
		moveis[0].setPosJ(5);
		moveis[0].setExercito(0);
		h.setOcupante(moveis[0]);
	}

	public void insereUmCristal() {
		Hexagono h = mapa.getHexagono(4, 4);
		h.setOcupante(new Cristal(1));
		h = mapa.getHexagono(3, 3);
		h.setOcupante(new Cristal(1));
		h = mapa.getHexagono(3, 4);
		h.setOcupante(new Cristal(1));
		h = mapa.getHexagono(3, 5);
		h.setOcupante(new Cristal(1));
		h = mapa.getHexagono(4, 3);
		h.setOcupante(new Cristal(1));
		h = mapa.getHexagono(4, 6);
		h.setOcupante(new Cristal(1));
	}

	public void insereExercitos() {
		Base b;
		int posI, posJ;
		int posMedI = mapa.getMaxI() / 2;
		int posMedJ = mapa.getMaxJ() / 2;
		int robosBase = numRobos / numBases;
		for (int i = 0; i < numBases; i++) {
			b = bases[i];
			posI = b.getPosI();
			posJ = b.getPosJ();
			if (posJ < posMedJ) {
				if (posI < posMedI) {
					insereExercito(1, robosBase, i);
				} else {
					insereExercito(2, robosBase, i);
				}
			} else if (posI < posMedI) {
				insereExercito(3, robosBase, i);
			} else {
				insereExercito(4, robosBase, i);
			}
		}
	}

	private void insereExercito(int q, int robosBase, int exerc) {
		Hexagono h;
		int rposI = 0;
		int rposJ = 0;
		int posMedI = mapa.getMaxI() / 2;
		int posMedJ = mapa.getMaxJ() / 2;
		for (int i = robosBase * exerc; i < (robosBase + (robosBase * exerc)); i++) {
			do {
				switch (q) {
				case 1:
					rposI = rand.nextInt(posMedI);
					rposJ = rand.nextInt(posMedJ);
					break;
				case 2:
					rposI = posMedI + rand.nextInt(posMedI) + 1;
					rposJ = rand.nextInt(posMedJ);
					break;
				case 3:
					rposI = rand.nextInt(posMedI);
					rposJ = posMedJ + rand.nextInt(posMedJ) + 1;
					break;
				case 4:
					rposI = posMedI + rand.nextInt(posMedI) + 1;
					rposJ = posMedJ + rand.nextInt(posMedJ) + 1;
					break;
				default:
					System.out.println("Quadrante inválido!");
					break;
				}
				h = mapa.getHexagono(rposI, rposJ);
			} while (h.temOcupante());
			moveis[i].setPosI(rposI);
			moveis[i].setPosJ(rposJ);
			moveis[i].setExercito(exerc);
			h.setOcupante(moveis[i]);
		}
	}

	/*
	 * // Para o teste com um robo e um cristal public void insereUmRobo() {
	 * moveis[0].setPosI(1); moveis[0].setPosJ(12); mapa.setNovoRobo(moveis[0]);
	 * }
	 * 
	 * public void insereSeteCristais() { Hexagono h = mapa.getHexagono(1, 1);
	 * h.setOcupante(new Cristal(1)); h = mapa.getHexagono(2, 0);
	 * h.setOcupante(new Cristal(1)); h = mapa.getHexagono(3, 0);
	 * h.setOcupante(new Cristal(1)); h = mapa.getHexagono(4, 0);
	 * h.setOcupante(new Cristal(1)); h = mapa.getHexagono(2, 1);
	 * h.setOcupante(new Cristal(1)); h = mapa.getHexagono(3, 1);
	 * h.setOcupante(new Cristal(1)); h = mapa.getHexagono(4, 1);
	 * h.setOcupante(new Cristal(1)); }
	 */

	public void insereCristais(int n) {
		int t, cristaisColocados, r, s;
		Hexagono h;
		t = 0;
		cristaisColocados = 0;
		while (cristaisColocados < n && t < 3 * n) {
			r = rand.nextInt(mapa.getMaxI());
			s = rand.nextInt(mapa.getMaxJ());
			System.out.println("Inserindo Cristal:");
			System.out.println("i: " + r);
			System.out.println("j: " + s);
			h = mapa.getHexagono(r, s);
			if (!h.temOcupante()) {
				h.setOcupante(new Cristal(1));
				cristaisColocados++;
			}
			t++;
		}

		System.out.println("Insere cristais:");
		System.out.println("Numero de cristais:" + n);
		System.out.println("Cristais colocados:" + cristaisColocados);
		// numCristais = cristaisColocados;
	}

	public void insereBases() {
		int k;
		Base b;
		Hexagono h;
		ArrayList<Base> localizacao = new ArrayList<Base>();
		localizacao.add(new Base(0, 0, "azul"));
		localizacao.add(new Base(0, mapa.getMaxJ() - 1, "rosa"));
		localizacao.add(new Base(mapa.getMaxI() - 1, 0, "verde"));
		localizacao.add(new Base(mapa.getMaxI() - 1, mapa.getMaxJ() - 1,
				"vermelha"));
		for (int i = 0; i < numBases; i++) {
			k = rand.nextInt(localizacao.size());
			b = localizacao.remove(k);
			h = mapa.getHexagono(b.getPosI(), b.getPosJ());
			b.setEquipe(i);
			bases[i] = b;
			h.setOcupante(b);
		}
	}

	public void insereUmaBase() {
		Base b;
		Hexagono h;
		ArrayList<Base> localizacao = new ArrayList<Base>();
		localizacao.add(new Base(0, 0, "azul"));
		localizacao.add(new Base(0, mapa.getMaxJ() - 1, "rosa"));
		localizacao.add(new Base(mapa.getMaxI() - 1, 0, "verde"));
		localizacao.add(new Base(mapa.getMaxI() - 1, mapa.getMaxJ() - 1,
				"vermelha"));
		b = localizacao.get(0);
		h = mapa.getHexagono(b.getPosI(), b.getPosJ());
		b.setEquipe(0);
		b.addCristal();
		b.addCristal();
		b.addCristal();
		b.addCristal();
		b.addCristal();
		b.addCristal();
		bases[0] = b;
		h.setOcupante(b);
	}

	/*
	 * public void insereBase() { Base b; Hexagono h; ArrayList<Base>
	 * localizacao = new ArrayList<Base>(); localizacao.add(new Base(0,
	 * mapa.getMaxJ() - 1)); for (int i = 0; i < 1; i++) { b =
	 * localizacao.remove(i); h = mapa.getHexagono(b.getPosI(), b.getPosJ());
	 * b.setEquipe(i); bases[i] = b; h.setOcupante(b); } }
	 */

	public void removeExercito(int idExercito) {
		for (int j = 0; j < mapa.getMaxJ(); j++) {
			for (int i = 0; i < mapa.getMaxI(); i++) {
				Posicionavel ocupante = mapa.getHexagono(i, j).getOcupante();
				if (ocupante instanceof Robo || ocupante instanceof Base) {
					if (ocupante instanceof Robo) {
						if (((Robo) ocupante).getExercito() == idExercito)
							mapa.getHexagono(i, j).retiraOcupante();
					} else if(((Base) ocupante).getEquipe() == idExercito) {
						mapa.getHexagono(i, j).retiraOcupante();
					}

				}
			}
		}
		
	}

	public void removeExercitosPerdedores() {
		Base vencedor = baseVencedor();
		for (int i = 0; i < numBases; i++) {
			if (!vencedor.equals(bases[i])) {
				removeExercito(bases[i].getEquipe());
			}
		}
	}

	public Base baseVencedor() {
		Base vencedor = null;
		for (int i = 0; i < numBases; i++) {
			if (bases[i].getNumDeCristais() == 7) {
				vencedor = bases[i];
			}
		}
		return vencedor;
	}

}
