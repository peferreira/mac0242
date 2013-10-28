package mv.instrucoes;

import java.util.Stack;

import mv.*;
import mv.empilhaveis.*;

public class POP extends Instrucao {
    public POP () {
	super ();
    }
    public void executar (Stack<Empilhavel> pilhaDeDados, Memoria memoria, Programa programa) {
	 pilhaDeDados.pop();
	 programa.incPonteiroPrograma();
    }
}
