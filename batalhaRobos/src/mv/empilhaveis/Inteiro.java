package mv.empilhaveis;

public class Inteiro extends Numero {
	int v;

	public Inteiro(int valor) {
		v = valor;
	}

	public double valor() {
		return (double) v;
	}

}
