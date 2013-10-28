package mv;


import java.util.Stack;
import java.util.LinkedList;

import mv.empilhaveis.*;
import mv.instrucoes.*;

public class MaquinaVirtual {
    public static void main(String[] args) {
	Stack<Empilhavel> pilhaDeDados = new Stack<Empilhavel>();
	Memoria memoria = new Memoria();
	Programas programas  = new Programas();
	Instrucao codigo;
	LinkedList<Programa> listaDeProgramas;
	
	programas.setListaDeProgramasPadroes();
	listaDeProgramas = programas.getListaDeProgramas();
	for (Programa prg : listaDeProgramas) {
	    codigo = prg.getInstrucao();
	    while (!(codigo instanceof END)) {
		codigo.executar(pilhaDeDados, memoria, prg);
		codigo = prg.getInstrucao();
	    }
	}

    }

}
