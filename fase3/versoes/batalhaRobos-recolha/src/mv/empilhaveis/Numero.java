package mv.empilhaveis;

public abstract class Numero implements Empilhavel {

	public abstract double valor();

	public void print() {
		System.out.println(valor());
	}

	public Numero clona() {
		if (this instanceof Inteiro) {
			return ((Numero) new Inteiro((int) valor()));
		} else if (this instanceof Real) {
			return ((Numero) new Real(valor()));
		} else {
			System.out
					.println("Numero: Um numero tem que ser inteiro ou real!");
			System.exit(1);
		}
		return null;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Numero) {
			return (valor()) == (((Numero) obj).valor());
		}
		return false;
	}
}
