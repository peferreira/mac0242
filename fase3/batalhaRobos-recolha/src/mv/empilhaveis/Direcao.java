package mv.empilhaveis;

public abstract class Direcao implements Empilhavel {
	
	public abstract String getDir();

	public void print() {
		System.out.println(getDir());
	}

	public Direcao clona() {
		if (this instanceof NWEST ) {
			return ((Direcao) new NWEST());
		} else if (this instanceof NEAST) {
			return ((Direcao) new NEAST());
		}else if (this instanceof EAST) {
			return ((Direcao) new EAST());
		}else if (this instanceof SEAST) {
			return ((Direcao) new SEAST());
		}else if (this instanceof SWEST) {
			return ((Direcao) new SWEST());
		}else if (this instanceof WEST) {
			return ((Direcao) new WEST());
		} else {
			System.out.println("Direcao: direcao inválida!");
			System.exit(1);
		}
		return null;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Direcao) {
			return getDir().equals(((Direcao) obj).getDir());
		}
		return false;
	}

}
