package mv.empilhaveis;

public class Verdadeiro extends Booleano implements Empilhavel {

    private int v;
    
    public Verdadeiro () {
	v = 1;
    }

    public int valor () {
	return v;
    }
    
    public void print(){
	System.out.println(v);
    }

    public Verdadeiro clona(){
	return (new Verdadeiro ());
    }

    public boolean equals (Object obj) {
	return (obj instanceof Verdadeiro);
    }
}
