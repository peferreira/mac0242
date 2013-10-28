package mv.instrucoes;

import java.util.Stack;

import mv.*;
import mv.empilhaveis.*;

public class MUL extends Instrucao {
    public MUL () {
	super();
    }
    public void executar (Stack<Empilhavel> pilhaDeDados, Memoria memoria, Programa programa) {
	Empilhavel item1 = pilhaDeDados.pop();
	Empilhavel item2 = pilhaDeDados.pop();
	int resultado;
	if ((item1 instanceof Inteiro) && (item2 instanceof Inteiro)) {
	    resultado = ((Inteiro)item2).getInteiro() * ((Inteiro)item1).getInteiro();
	    pilhaDeDados.push(new Inteiro(resultado));
	    programa.incPonteiroPrograma();
	} else {
	    System.out.println("MUL: Todos os operandos devem ser inteiros!");
	    System.exit(1);
	}
    }
}
