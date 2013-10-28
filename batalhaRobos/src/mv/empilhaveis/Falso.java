package mv.empilhaveis;

public class Falso extends Booleano implements Empilhavel {

    private int v;
    
    public Falso () {
	v = 0;
    }
    
    public int valor () {
	return v;
    }
    
    public void print(){
	System.out.println(v);
    }
    
    public Falso clona(){
	return (new Falso ());
    }
    
    public boolean equals (Object obj) {
	return (obj instanceof Falso);
    }
}
