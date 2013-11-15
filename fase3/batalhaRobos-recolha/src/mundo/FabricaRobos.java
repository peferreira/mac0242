package mundo;

import mundo.elementos.Robo;

public class FabricaRobos {
	Robo robos[];
	
	public FabricaRobos(int n) {
		this.robos = new Robo[n];
	}

	void criaRobos() {
		for (int i = 0; i < robos.length; i++){
			robos[i] = new Robo (i);
		}
	}

	public Robo[] getRobos() {
		return robos;
	}

}
