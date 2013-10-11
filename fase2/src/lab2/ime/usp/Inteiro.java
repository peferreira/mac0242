package lab2.ime.usp;

public class Inteiro implements Empilhavel {
	private int valor;

	public Inteiro(int valor) {
		this.valor = valor;
	}
	public void print(){
		System.out.println(valor);
	}
	public Inteiro clona(){
		return (new Inteiro(valor));
	}
}
