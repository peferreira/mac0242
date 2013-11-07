package mundo;

import java.util.Stack;
import java.lang.Math;
import mundo.posicionaveis.Posicionavel;
import mundo.posicionaveis.Robo;
import mundo.terrenos.Solo;

/*Representa uma posicao da arena hexagonal do jogo*/

public class Hexagono {
	private Stack<Posicionavel> competidores;
	private Posicionavel ocupante;
	private Solo solo;
	
	/*
	private double x;
	private double y;
	*/

	public Hexagono(double coordx, double coordy, double r) {
		/*
		double xOff = Math.sin(Math.toRadians(30)) * r;
		double yOff = Math.cos(Math.toRadians(30)) * r;
		x = coordx * xOff * 2;
		if (coordy % 2 != 0) {
			x += xOff;
		}
		y = coordy * yOff * 3;
		*/
		competidores = new Stack<Posicionavel>();
	}
	
	/*
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	*/

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
		if (!competidores.empty()) {
			Posicionavel vencedor = competidores.pop();
			return vencedor;
		} else {
			return null;
		}
	}

	void retiraCompetidores() {
		competidores.clear();
	}

	Posicionavel defineOcupante() {
		if (!competidores.empty()) {
			ocupante = competidores.pop();
			return ocupante;
		} else {
			return null;
		}
	}

	public void setOcupante(Robo r) {
		this.ocupante = r;
	}
}
