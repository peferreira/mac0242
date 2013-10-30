package mv.instrucoes;

import java.util.Stack;

import mv.Memoria;
import mv.Programa;
import mv.empilhaveis.Empilhavel;

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




	


