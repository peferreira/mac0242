package lab2.ime.usp;

public class Teste implements Empilhavel {

	private String nome;

	public Teste(String nome) {
		this.nome = nome;
	}

	public Teste() {
	}

	public Teste clona() {
		return (new Teste(nome));
	}

	public void print() {
		System.out.println(nome);

	}

}
