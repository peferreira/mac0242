package mv.instrucoes;

import java.util.Stack;

import mv.*;
import mv.empilhaveis.*;

public class LE extends Instrucao {
    public LE () {
	super();
    }
    public void executar (Stack<Empilhavel> pilhaDeDados, Memoria memoria, Programa programa) {
	Empilhavel item1 = pilhaDeDados.pop();
	Empilhavel item2 = pilhaDeDados.pop();
	boolean resultado = false;
	if ((item1 instanceof Inteiro) && (item2 instanceof Inteiro)){
	    resultado = ((Inteiro)item2).getInteiro() <= ((Inteiro)item1).getInteiro();
	} else if ((item1 instanceof Endereco) && (item2 instanceof Endereco)){
	    resultado = ((Endereco)item2).getEndereco() <= ((Endereco)item1).getEndereco();
	} else if ((item1 instanceof Booleano) && (item2 instanceof Booleano)){
	    resultado = ((Booleano)item2).valor() <= ((Booleano)item1).valor();
	} else {
	    System.out.println("LE: Operandos inválidos!");
	    System.exit(1);
	}  
	pilhaDeDados.push(resultado ? new Verdadeiro() : new Falso());
	programa.incPonteiroPrograma();
    }
}
