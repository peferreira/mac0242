package mundo;

import java.util.Stack;

import mundo.posicionaveis.Posicionavel;
import mundo.terrenos.Solo;

/*Representa uma posicao da arena hexagonal do jogo*/

public class Hexagono {
	private Stack<Posicionavel> competidores;
	private Posicionavel ocupante;
	private Solo solo;

	void adiciona(Posicionavel competidor) {
		competidores.push(competidor);
	}

	boolean temOcupante() {
		return ocupante == null ? false : true;
	}

	void retiraOcupante() {
		ocupante = null;
	}

	void remove() {
		competidores.pop();
	}

	Posicionavel topo() {
		Posicionavel vencedor = competidores.lastElement();
		return vencedor;
	}
	void retiraCompetidores(){
		competidores.clear();
	}
	Posicionavel defineOcupante() {
		ocupante = competidores.lastElement();
		return ocupante;
	}
}
