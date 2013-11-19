package mv.instrucoes;

import java.util.Stack;

import mv.*;
import mv.empilhaveis.*;

public class PUSH extends Instrucao {
    public PUSH (Empilhavel item) {
	super (item);
    }
    public void executar (Stack<Empilhavel> pilhaDeDados, Memoria memoria, Programa programa) {
	 pilhaDeDados.push(argumento);
	 programa.incPonteiroPrograma();
    }
}
