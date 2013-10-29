package mv.instrucoes;

import java.util.Queue;
import java.util.Stack;

import mv.*;
import mv.empilhaveis.*;

public class Instrucao {
    Empilhavel argumento;

    public Instrucao () {
	argumento = null;
    }

    public Instrucao (Empilhavel operando) {
	argumento = operando;
    }

    public void executar (Stack<Empilhavel> pilhaDeDados, Memoria memoria, Programa programa) {
    }
}




	


