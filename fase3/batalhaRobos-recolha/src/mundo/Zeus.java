package mundo;

import java.util.LinkedList;

import comunicacao.*;
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
			}
		} else {
			for (int k = 0; k < hex.numeroDeRequerentes(); k++) {
				respostas.add(new RespostaMOVE(false, hex.getRequerente(k)
						.getID()));
			}
		}

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
			}
		} else {
			for (int k = 0; k < hex.numeroDeRequerentes(); k++) {
				respostas.add(new RespostaPICK(false, hex.getMinerador(k)
						.getID()));
			}
		}

	}

	public boolean roboDentroDaArena(int i, int j, int id) {
		if (i >= 0 && i < mapa.getMaxI()) {
			if (j >= 0 && j < mapa.getMaxJ()) {
				// Meramente informativo
				System.out.println("Robo dentro da arena!");

				return true;
			}
		}

		// Meramente informativo
		System.out.println("Robo " + id + " quer andar fora da arena!");

		respostas.add(new RespostaMOVE(false, id));
		return false;
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
