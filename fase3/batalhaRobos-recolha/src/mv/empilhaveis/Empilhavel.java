package mv.empilhaveis;

public interface Empilhavel {
    abstract void print();
    abstract Empilhavel clona();
    abstract boolean equals(Object obj); 
}
