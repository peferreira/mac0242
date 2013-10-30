package mundo;

import java.util.Stack;

import mundo.posicionaveis.Posicionavel;
import mundo.posicionaveis.Robo;
import mundo.terrenos.Solo;

/*Representa uma posicao da arena hexagonal do jogo*/

public class Hexagono {
	private Stack<Posicionavel> competidores;
	private Posicionavel ocupante;
	private Solo solo;
	public Hexagono(){
		competidores = new Stack<Posicionavel>();
		
	}
	void adiciona(Posicionavel competidor) {
		competidores.push(competidor);
	}

	boolean temOcupante() {
		return ocupante != null;
	}

	void retiraOcupante() {
		ocupante = null;
	}

	void remove() {
		competidores.pop();
	}

	Posicionavel topo() {
		if(!competidores.empty()){
			Posicionavel vencedor = competidores.pop();
			return vencedor;
		}
		else {
			return null;
		}
	}
	void retiraCompetidores(){
		competidores.clear();
	}
	Posicionavel defineOcupante() {
		if(!competidores.empty()){
			ocupante = competidores.pop();
			return ocupante;
		}
		else {
			return null;
		}
	}
	
	public void setOcupante(int i, int j){
		this.ocupante = new Robo(i,j);
	}
}
