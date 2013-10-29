package mundo;

public class MapaHexagonal {
	private Hexagono[][] mapa;
	private final int sizeI;
	private final int sizeJ;
	MapaHexagonal() {
		this.sizeI = 100;
		this.sizeJ = 100;
		mapa = new Hexagono[sizeI][sizeJ];
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
}
