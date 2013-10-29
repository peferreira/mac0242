package mundo.posicionaveis;

public class Robo implements Posicionavel {

	private int posI;
	private int posJ;

	Robo(int posI, int posJ) {
		this.posI = posI;
		this.posJ = posJ;

	}

	public int getI() {
		return posI;
	}

	public int getJ() {
		return posJ;
	}

	public void alteraPosicao(int posI, int posJ) {
		this.posI = posI;
		this.posJ = posJ;
	}

}