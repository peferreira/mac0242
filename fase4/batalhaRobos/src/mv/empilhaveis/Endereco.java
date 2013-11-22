package mv.empilhaveis;

public class Endereco implements Empilhavel {

    private int endereco;
    
    public Endereco (int valor) {
	endereco = valor;
    }

    public int getEndereco () {
	return endereco;
    }

    public void setEndereco (int novo) {
	endereco = novo;
    }

    public void incEndereco () {
	endereco++;
    }

    public void incEndereco (Endereco e) {
	endereco += e.getEndereco();
    }

    public void print(){
	System.out.println(endereco);
    }

    public Endereco clona(){
	return (new Endereco(endereco));
    }

    public boolean equals (Object obj) {
	if (obj instanceof Endereco) {
	    return endereco == (((Endereco)obj).getEndereco());
	}
	return false;
    }
}
