package mundo;

import java.util.LinkedList;

import comunicacao.*;
import mundo.elementos.Base;
import mundo.elementos.Cristal;
import mundo.elementos.Robo;

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

	public boolean dentroDaArena(int i, int j, int id) {
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
		possivel = dentroDaArena(i, j, id);
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
		possivel = dentroDaArena(i, j, id);
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
		possivel = dentroDaArena(i, j, id);
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
		if (dentroDaArena(i, j, id)) {
			hex = mapa.getHexagono(i, j);
			if (hex.temOcupante() && (hex.getOcupante() instanceof Cristal)) {
				respostas.add(new RespostaSCAND(dir, id));
				respostas.add(new RespostaSCAN(true, id));
				return true;
			}
		}
		return false;
	}

	public boolean enemigoDescoberto(int i, int j, int id, int exerc, String dir) {
		Hexagono hex;
		if (dentroDaArena(i, j, id)) {
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
		if (dentroDaArena(i, j, id)) {
			hex = mapa.getHexagono(i, j);
			if (!hex.temOcupante()) {
				respostas.add(new RespostaSCAND(dir, id));
				respostas.add(new RespostaSCAN(true, id));
				return true;
			}
		}
		return false;
	}
	
	public void regressoBase(int i, int j,int ni,int nj, int bi, int bj, int id, String dir) {
		Hexagono hex;
		if (dentroDaArena(ni, nj, id)) {
			//System.out.println("HI");
			respostas.add(new RespostaHOMED(dir, id));
			hex = mapa.getHexagono(ni, nj);
			if (hex.temOcupante() && (hex.getOcupante() instanceof Base)) {
				//System.out.println(":]");
				respostas.add(new RespostaHOME(true, id));
				return;
			}
		} else {
			//System.out.println(":[");
			if (j == bj){
				if(i > bi){
					respostas.add(new RespostaHOMED("W", id));
				}else {
					respostas.add(new RespostaHOMED("E", id));
				}
			}
			// 1º e 2º quadrante
			if (i == bi &&  j > bj){
				if (j % 2 == 0){
					respostas.add(new RespostaHOMED("NE", id));
				}else{
					respostas.add(new RespostaHOMED("NW", id));
				}
			}
			// 3º e 4º quadrante	
			if (i == bi &&  j < bj){
				if (j % 2 == 0){
					respostas.add(new RespostaHOMED("SE", id));
				}else{
					respostas.add(new RespostaHOMED("SW", id));
				}
			}
		}
		respostas.add(new RespostaHOME(false, id));
	}

	public void criaResposta(Resposta r) {
		respostas.add(r);
	}

	public void atualiza() {
		int maxI, maxJ;
		maxI = mapa.getMaxI();
		maxJ = mapa.getMaxJ();

		for (int j = 0; j < maxJ; j++) {
			for (int i = 0; i < maxI; i++) {
				processaRequesicoes(i, j);
				processaMineradores(i, j);
			}
		}
	}

}
