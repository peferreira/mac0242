package lab2.ime.usp;

public class Teste implements Empilhavel{
	
	Teste(String nome){
		this.nome = nome;
	}
	public String nome;

	public void voar(){
		System.out.println("voarr!!! "+ nome);
		
	}

}
