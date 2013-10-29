package mv.empilhaveis;

public class Direcao implements Empilhavel {

	private String dir;

	public Direcao(String dir) {
		this.dir = dir;
	}
	
	public String getDir() {
		return dir; 
	}

	public int getCodigoDir() {
		int codigo;
		switch (dir) {
		case "NWEST":
			codigo = 1;
			break;
		case "WEST":
			codigo = 2;
			break;
		case "SWEST":
			codigo = 3;
			break;
		case "SEAST":
			codigo = 4;
			break;
		case "EAST":
			codigo = 5;
			break;
		case "NEAST":
			codigo = 6;
			break;
		default:
			codigo = 0;
			System.out.println("Direcao inválida!");
			break;
		}
		return codigo;
	}

	public void print() {
		System.out.println(dir);
	}

	public Direcao clona() {
		return new Direcao(dir);
	}

	public boolean equals(Object obj) {
		if (obj instanceof Direcao) {
			return dir.equals(((Direcao) obj).getDir());
		}
		return false;
	}

}
