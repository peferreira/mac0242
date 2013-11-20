package mundo;

import mundo.elementos.Robo;

public class FabricaRobos {
	private Robo robos[];
	private String coresRobos[] = { "azul", "rosa", "america", "vermelho" };
	private int numBases;
	private int numRobos;

	public FabricaRobos(int numRobos, int numBases) {
		this.robos = new Robo[numRobos];
		this.numBases = numBases;
		this.numRobos = numRobos;
	}

	void criaRobos() {
		int robosBase = numRobos / numBases;
		for (int i = 0; i < numBases; i++) {
			for (int j = robosBase * i; j < (robosBase + (robosBase * i)); j++) {
				robos[j] = new Robo(j, coresRobos[i]);
			}
			
		}
		
	}

	public Robo[] getRobos() {
		return robos;
	}

}
