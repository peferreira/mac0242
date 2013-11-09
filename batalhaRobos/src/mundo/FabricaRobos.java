package mundo;

import mundo.posicionaveis.Robo;

public class FabricaRobos {
	Robo robos[];
	int idRobo;

	public FabricaRobos(int n) {
		this.robos = new Robo[n];
		idRobo = 0;
	}

	public FabricaRobos() {

	}

	void criaRobos() {
		Robo robos[] = { new Robo(1, 2, 1, idRobo++),
				new Robo(3, 3, 1, idRobo++), new Robo(7, 7, 1, idRobo++),
				new Robo(3, 4, 1, idRobo++), new Robo(4, 3, 1, idRobo++),
				new Robo(7, 8, 1, idRobo++), new Robo(5, 5, 1, idRobo++) };
		setRobos(robos);
	}

	public Robo[] getRobos() {
		return robos;
	}

	private void setRobos(Robo robos[]) {
		this.robos = robos;
	}

}
