package mundo;

import mundo.posicionaveis.Robo;

public class MapaHexagonal {
	private Hexagono[][] mapa;
	private int sizeI;
	private int sizeJ;

	MapaHexagonal() {
		this.sizeI = 3;
		this.sizeJ = 3;
		mapa = new Hexagono[3][3];
	}

	void inicializa() {
		int i, j;
		for (i = 0; i < sizeI; i++) {
			for (j = 0; j < sizeJ; j++) {
				mapa[i][j] = new Hexagono(j, i, 1);
			}
		}
	}

	int getSizeI() {
		return sizeI;
	}

	int getSizeJ() {
		return sizeJ;
	}

	Hexagono getHexagono(int i, int j) {
		return mapa[i][j];
	}

	void setNovoRobo(Robo r) {
		mapa[r.getI()][r.getJ()].setOcupante(r);
	}
}
