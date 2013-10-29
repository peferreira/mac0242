package mundo;

import java.util.Stack;

import mundo.posicionaveis.Posicionavel;
import mundo.terrenos.Solo;

/*Representa uma posicao da arena hexagonal do jogo*/

public class Hexagono {
	private Stack<Posicionavel> competidores;
	private Solo solo;

	void adiciona(Posicionavel competidor) {
		competidores.add(competidor);
	}

	void remove() {
		competidores.pop();
	}
}
