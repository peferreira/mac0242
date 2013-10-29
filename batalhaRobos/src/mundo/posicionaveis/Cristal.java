package mundo.posicionaveis;

public class Cristal implements Posicionavel{
	int posI;
	int posJ;

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
