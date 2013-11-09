package mundo;

import java.util.LinkedList;
import java.util.List;

import comunicacao.*;

import mundo.posicionaveis.Posicionavel;
import mundo.posicionaveis.Robo;

/*Contem os métodos que resolvem os conflitos do jogo baseados na crença de Zeus
 --possibilidade de implementar outros deuses. Zeus recebe uma arena, Zeus também
 gerará as mensagens do que ocorreu com os posicionaveis do mundo.*/
public class Zeus {
	MapaHexagonal mapa;
	RespostaMOVE resp;
	List<Resposta> respostas;
	Zeus(MapaHexagonal mapa, List<Resposta> respostas) {
		this.mapa = mapa;
		this.respostas = respostas;
	}

	private void leiDoPrimeiro(int i, int j) {

		Hexagono hex, hexAntigo;
		hex = mapa.getHexagono(i, j);
		if (!(hex.temOcupante())) {
			Robo ocupante = (Robo) hex.defineOcupante();
			if (ocupante != null) {
				Robo competidor = (Robo) hex.popCompetidor();
				while (competidor != null) {
					respostas.add(new RespostaMOVE(false, competidor.getID()));
					competidor = (Robo) hex.popCompetidor();
				}
				System.out.println("ARobo:" + ocupante.getID() + " - i: "
						+ ocupante.getI() + " j: " + ocupante.getJ());
				hexAntigo = mapa.getHexagono(ocupante.getI(), ocupante.getJ());
				hexAntigo.retiraOcupante();
				ocupante.alteraPosicao(i, j);
				System.out.println("NRobo:" + ocupante.getID() + " - i: "
						+ ocupante.getI() + " j: " + ocupante.getJ());
				respostas.add(new RespostaMOVE(true, ocupante.getID()));
			} else {

			}
		} else {
			Robo competidor = (Robo) hex.popCompetidor();
			/*Robo ocupante = (Robo)hex.getOcupante();*/
			while (competidor != null) {
				/*System.out.print(resp.responde(false, competidor.getID()));
				if (ocupante != null)
					System.out.println("ocupante id:"+ ocupante.getID());
				else
					System.out.println("não ha ocupante");*/
				respostas.add(new RespostaMOVE(false, competidor.getID()));

				competidor = (Robo) hex.popCompetidor();
				
			}
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
