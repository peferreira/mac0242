package mundo;

import mundo.posicionaveis.Posicionavel;

/*Contem os métodos que resolvem os conflitos do jogo baseados na crença de Zeus
 --possibilidade de implementar outros deuses. Zeus recebe uma arena, Zeus também
 gerará as mensagens do que ocorreu com os posicionaveis do mundo.*/
public class Zeus {
	MapaHexagonal mapa;

	Zeus(MapaHexagonal mapa) {
		this.mapa = mapa;
	}

	private void leiDoPrimeiro(int i, int j) {

		Hexagono hex, hexAntigo;
		hex = mapa.getHexagono(i, j);
		if (!(hex.temOcupante())) {
			Posicionavel ocupante = hex.defineOcupante();
			hex.retiraCompetidores();
			ocupante.alteraPosicao(i, j);
			hexAntigo = mapa.getHexagono(ocupante.getI(), ocupante.getJ());
			hexAntigo.retiraOcupante();
		} else {
			hex.retiraCompetidores();
		}
	}

	void atualiza() {
		int i, j, sizeI, sizeJ;
		sizeI = mapa.getSizeI();
		sizeJ = mapa.getSizeJ();

		for (i = 0; i < sizeI; i++) {
			for (j = 0; j < sizeJ; j++) {
				leiDoPrimeiro(i, j);
			}
		}
	}
}
