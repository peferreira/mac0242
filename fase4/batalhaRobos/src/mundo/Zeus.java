package mundo;

import java.util.ArrayList;
import java.util.LinkedList;

import mundo.elementos.Bala;
import mundo.elementos.Base;
import mundo.elementos.Cristal;
import mundo.elementos.Robo;

import comunicacao.Resposta;
import comunicacao.RespostaDEP;
import comunicacao.RespostaHOME;
import comunicacao.RespostaHOMED;
import comunicacao.RespostaMOVE;
import comunicacao.RespostaPICK;
import comunicacao.RespostaSCAN;
import comunicacao.RespostaSCAND;

public class Zeus {

	private MapaHexagonal mapa;
	private LinkedList<Resposta> respostas;
	private int[] turnos;

	Zeus(MapaHexagonal mapa, LinkedList<Resposta> respostas, int[] turnos) {
		this.mapa = mapa;
		this.respostas = respostas;
		this.turnos = turnos;
	}

	private void processaRequesicoes(int i, int j) {
		int c;
		Hexagono hex, hexAntigo;
		hex = mapa.getHexagono(i, j);
		if (!(hex.temOcupante())) {
			if (hex.numeroDeRequerentes() > 0) {
				Robo ocupante = hex.novoOcupante();
				for (int k = 0; k < hex.numeroDeRequerentes(); k++) {
					respostas.add(new RespostaMOVE(false, hex.getRequerente(k)
							.getID()));
				}
				// Meramente informativo
				System.out.println("ARobo:" + ocupante.getID() + " - i: "
						+ ocupante.getPosI() + " j: " + ocupante.getPosJ());

				hexAntigo = mapa.getHexagono(ocupante.getPosI(),
						ocupante.getPosJ());
				hexAntigo.retiraOcupante();
				ocupante.alteraPosicao(i, j);
				mapa.setNovoRobo(ocupante);

				// Calculo do custo da operacao (experimental)
				c = 0;
				if (ocupante.temCristal()) {
					c = ocupante.getCristal().custo();
				}
				turnos[ocupante.getID()] = hex.getSolo().custo() + c;

				// Meramente informativo
				System.out.println("NRobo:" + ocupante.getID() + " - i: "
						+ ocupante.getPosI() + " j: " + ocupante.getPosJ());

				respostas.add(new RespostaMOVE(true, ocupante.getID()));
				hex.delRequerentes();
			}
		} /*
		 * else { for (int k = 0; k < hex.numeroDeRequerentes(); k++) {
		 * respostas.add(new RespostaMOVE(false, hex.getRequerente(k)
		 * .getID())); } hex.delRequerentes(); }
		 */

	}

	private void processaMineradores(int i, int j) {
		Hexagono hex;
		hex = mapa.getHexagono(i, j);
		if (hex.temOcupante() && (hex.getOcupante() instanceof Cristal)) {
			if (hex.numeroDeMineradores() > 0) {
				Robo minerador = hex.novoMinerador();

				for (int k = 0; k < hex.numeroDeMineradores(); k++) {
					respostas.add(new RespostaPICK(false, hex.getMinerador(k)
							.getID()));
				}

				minerador.setCristal(((Cristal) hex.getOcupante()));
				hex.retiraOcupante();

				// Calculo do custo da operacao (experimental)
				turnos[minerador.getID()] = minerador.getCristal().custo();

				respostas.add(new RespostaPICK(true, minerador.getID()));
				hex.delMineradores();
			}
		} /*
		 * else { for (int k = 0; k < hex.numeroDeMineradores(); k++) {
		 * respostas.add(new RespostaPICK(false, hex.getMinerador(k) .getID()));
		 * } }
		 */

	}

	private void processaTurnoZero(Bala b, int i, int j) {
		int novoI = novoX(i, j, b.getDir());
		int novoJ = novoY(j, b.getDir());
		Hexagono hex;
		if (dentroDaArena(novoI, novoJ)) {
			System.out.println("O.O");
			hex = mapa.getHexagono(novoI, novoJ);
			hex.addNovoAtaque(b);
			b.decVida();
			b.setTurno(2);
		}

	}

	private void processaBala(int i, int j) {
		ArrayList<Bala> ataques;
		Hexagono hex;
		Bala b;
		hex = mapa.getHexagono(i, j);
		if (hex.temAtaques()) {
			System.out.println("i:" + i + "j:" + j);
			ataques = hex.getAtaques();

			for (int k = 0; k < ataques.size(); k++) {
				System.out.println("xXx");
				b = ataques.get(k);

				System.out.println("Bala turno:" + b.getTurno());
				switch (b.getTurno()) {
				case 0:
					ataques.remove(k);
					System.out.println("xXx0");
					if (b.getVida() > 0) {
						processaTurnoZero(b, i, j);
						System.out.println("xXx1");

					}
					System.out.println("xXx2");
					break;
				case 1:
					b.decTurno();
					break;
				case 2:
					b.decTurno();
					break;
				default:
					break;
				}
			}
		}
	}

	public boolean dentroDaArena(int i, int j) {
		if (i >= 0 && i < mapa.getMaxI()) {
			if (j >= 0 && j < mapa.getMaxJ()) {
				// Meramente informativo
				System.out.println("Robo dentro da arena!");

				return true;
			}
		}

		// Meramente informativo
		/*
		 * System.out.println("Robo " + id + " quer andar fora da arena!");
		 * 
		 * respostas.add(new RespostaMOVE(false, id));
		 */
		return false;
	}

	public boolean ehPossivelMover(int i, int j, int id) {
		boolean possivel;
		Hexagono hex;
		possivel = dentroDaArena(i, j);
		if (possivel) {
			hex = mapa.getHexagono(i, j);
			if (!hex.temOcupante()) {
				return true;
			}
		}

		respostas.add(new RespostaMOVE(false, id));
		return false;

	}

	public boolean ehPossivelRecolher(int i, int j, int id) {
		boolean possivel;
		Hexagono hex;
		possivel = dentroDaArena(i, j);
		if (possivel) {
			hex = mapa.getHexagono(i, j);
			if (hex.temOcupante() && (hex.getOcupante() instanceof Cristal)) {
				return true;
			}
		}

		respostas.add(new RespostaPICK(false, id));
		return false;
	}

	public boolean ehPossivelDepositar(int i, int j, int id) {
		boolean possivel;
		Hexagono hex;
		possivel = dentroDaArena(i, j);
		if (possivel) {
			hex = mapa.getHexagono(i, j);
			if (hex.temOcupante() && (hex.getOcupante() instanceof Base)) {
				return true;
			}
		}

		respostas.add(new RespostaDEP(false, id));
		return false;
	}

	public boolean cristalDescoberto(int i, int j, int id, String dir) {
		Hexagono hex;
		if (dentroDaArena(i, j)) {
			hex = mapa.getHexagono(i, j);
			if (hex.temOcupante() && (hex.getOcupante() instanceof Cristal)) {
				respostas.add(new RespostaSCAND(dir, id));
				respostas.add(new RespostaSCAN(true, id));
				return true;
			}
		}
		return false;
	}

	public boolean inimigoDescoberto(int i, int j, int id, int exerc, String dir) {
		Hexagono hex;
		if (dentroDaArena(i, j)) {
			hex = mapa.getHexagono(i, j);
			if (hex.temOcupante() && (hex.getOcupante() instanceof Robo)
					&& (exerc != ((Robo) hex.getOcupante()).getExercito())) {
				respostas.add(new RespostaSCAND(dir, id));
				respostas.add(new RespostaSCAN(true, id));
				return true;
			}
		}
		return false;
	}

	public boolean dirLivre(int i, int j, int id, String dir) {
		Hexagono hex;
		if (dentroDaArena(i, j)) {
			hex = mapa.getHexagono(i, j);
			if (!hex.temOcupante()) {
				respostas.add(new RespostaSCAND(dir, id));
				respostas.add(new RespostaSCAN(true, id));
				return true;
			}
		}
		return false;
	}

	public void regressoBase(int i, int j, int ni, int nj, int bi, int bj,
			int id, String dir) {
		Hexagono hex;
		String novoDir;
		if (dentroDaArena(ni, nj)) {
			// System.out.println("O.O");
			respostas.add(new RespostaHOMED(dir, id));
			hex = mapa.getHexagono(ni, nj);
			if (hex.temOcupante() && (hex.getOcupante() instanceof Base)) {
				// System.out.println(":]");
				respostas.add(new RespostaHOME(true, id));
				return;
			}
		} else {
			// System.out.println(":[");
			novoDir = "";
			if (j == bj) {
				if (i > bi) {
					novoDir = "W";
				} else {
					novoDir = "E";
				}
			}
			// 1Âº e 2Âº quadrante
			if (i == bi && j > bj) {
				if (j % 2 == 0) {
					novoDir = "NE";
				} else {
					novoDir = "NW";
				}
			}
			// 3Âº e 4Âº quadrante
			if (i == bi && j < bj) {
				if (j % 2 == 0) {
					novoDir = "SE";
				} else {
					novoDir = "SW";
				}
			}

			/*
			 * // Pode ser usado para resolver colisões com cristais (mas não é
			 * genérico) // Não recomendo usar hex = mapa.getHexagono(novoX(i,
			 * j, novoDir), novoY(j, novoDir)); if (hex.temOcupante() &&
			 * (hex.getOcupante() instanceof Cristal)) { if (j == bj) { if (i >
			 * bi) { novoDir = "SW"; } else { novoDir = "SE"; } } else { novoDir
			 * = "E"; if (!dentroDaArena(novoX(i, j, novoDir), novoY(j,
			 * novoDir))) { novoDir = "W"; } } }
			 */
			respostas.add(new RespostaHOMED(novoDir, id));
		}
		respostas.add(new RespostaHOME(false, id));
	}

	public void criaResposta(Resposta r) {
		respostas.add(r);
	}

	void atualizaBala() {
		int maxI, maxJ;
		maxI = mapa.getMaxI();
		maxJ = mapa.getMaxJ();
		Hexagono hex;
		ArrayList<Bala> novosAtaques;
		ArrayList<Bala> ataques;
		for (int i = 0; i < maxI; i++) {
			for (int j = 0; j < maxJ; j++) {
				hex = mapa.getHexagono(i, j);
				novosAtaques = hex.getNovosAtaques();
				ataques = hex.getAtaques();
				for (int k = 0; k < novosAtaques.size(); k++) {
					hex.adAtaque(novosAtaques.get(k));
				}
				novosAtaques.clear();
			}
		}

	}

	public void atualiza() {
		int maxI, maxJ;
		maxI = mapa.getMaxI();
		maxJ = mapa.getMaxJ();

		for (int j = 0; j < maxJ; j++) {
			for (int i = 0; i < maxI; i++) {
				processaRequesicoes(i, j);
				processaMineradores(i, j);
				processaBala(i, j);
			}
		}
		atualizaBala();
	}

	public int novoY(int y, String dir) {
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
			System.out.println("Y: Direcao invÃ¡lida! - " + dir);
			return -1;
		}
	}

	public int novoX(int x, int y, String dir) {
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
			System.out.println("X: Direcao invÃ¡lida! - " + dir);
			return -1;
		}
	}

	private boolean eLinhaPar(int posLinha) {
		return (posLinha % 2) == 0;
	}

}
