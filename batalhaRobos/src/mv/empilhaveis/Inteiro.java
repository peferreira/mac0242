package mv.empilhaveis;

public class Inteiro implements Empilhavel {

    private int valor;
    
    public Inteiro (int valor) {
	this.valor = valor;
    }

    public int getInteiro () {
	return valor;
    }

    public void print(){
	System.out.println(valor);
    }

    public Inteiro clona(){
	return (new Inteiro (valor));
    }

    public boolean equals (Object obj) {
	if (obj instanceof Inteiro) {
	    return valor == (((Inteiro)obj).getInteiro());
	}
	return false;
    }

}
