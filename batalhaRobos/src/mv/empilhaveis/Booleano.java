package mv.empilhaveis;

public abstract class Booleano implements Empilhavel {
	public abstract int valor();

	public void print() {
		System.out.println(valor());
	}

	public Booleano clona() {
		if (this instanceof Verdadeiro) {
			return ((Booleano) new Verdadeiro());
		} else if (this instanceof Falso) {
			return ((Booleano) new Falso());
		} else {
			System.out
					.println("Booleano: Um booleano tem que ser verdadeiro ou falso!");
			System.exit(1);
		}
		return null;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Booleano) {
			return (valor()) == (((Booleano) obj).valor());
		}
		return false;
	}
}
