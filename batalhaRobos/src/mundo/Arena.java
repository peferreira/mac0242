package mundo;

import mundo.posicionaveis.Posicionavel;
import mundo.posicionaveis.Robo;

public class Arena {
	private Hexagono[][] mapa;
	private Posicionavel[] moveis;
	private int numRobos;

	Arena() {
		mapa = new Hexagono[100][100];
		moveis = new Posicionavel[100];
	}

	Hexagono getHexagono(int i, int j) {
		return mapa[i][j];
	}

	void adicionaRobo(Robo robo, int i, int j) {
		mapa[i][j].adiciona(robo);
	}

	void moveCimaEsq(int movelID) {
		Posicionavel movel = moveis[movelID];
		moveis[movelID].alteraPosicao(movel.getI() + 1, movel.getJ() - 1);
	}

	void moveEsquerda(int movelID) {
		Posicionavel movel = moveis[movelID];
		moveis[movelID].alteraPosicao(movel.getI(), movel.getJ() - 1);
	}

	void moveBaixoEsq(int movelID) {
		Posicionavel movel = moveis[movelID];
		moveis[movelID].alteraPosicao(movel.getI() - 1, movel.getJ() - 1);
	}

	void moveCimaDir(int movelID) {
		Posicionavel movel = moveis[movelID];
		moveis[movelID].alteraPosicao(movel.getI() + 1, movel.getJ());
	}

	void moveDireita(int movelID) {
		Posicionavel movel = moveis[movelID];
		moveis[movelID].alteraPosicao(movel.getI(), movel.getJ() + 1);
	}

	void moveBaixoDir(int movelID) {
		Posicionavel movel = moveis[movelID];
		moveis[movelID].alteraPosicao(movel.getI() - 1, movel.getJ());
	}

}
