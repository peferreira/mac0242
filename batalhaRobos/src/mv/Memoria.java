package mv;

import mv.empilhaveis.*;

public class Memoria {

	private final int SIZE = 2048;
	private Empilhavel[] memoria;

	public Memoria() {
		memoria = new Empilhavel[SIZE];
	}

	public void setMemoria(Empilhavel item, Empilhavel indice) {
		try {
			memoria[((Endereco) indice).getEndereco()] = item;
		} catch (ArrayIndexOutOfBoundsException excecao) {
			System.out.println("setMemoria: O endereço "
					+ ((Endereco) indice).getEndereco()
					+ " está fora da memória alocada!");
			System.exit(1);
		}
	}

	public Empilhavel getMemoria(Empilhavel endereco) {
		Empilhavel item = null;
		try {
			item = memoria[((Endereco) endereco).getEndereco()];
		} catch (ArrayIndexOutOfBoundsException excecao) {
			System.out.println("getMemoria: O endereço "
					+ ((Endereco) endereco).getEndereco()
					+ " está fora da memória alocada!");
			System.exit(1);
		}
		return item;
	}
}
