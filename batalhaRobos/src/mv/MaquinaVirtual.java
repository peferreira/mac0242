package mv;

import java.util.Stack;
import java.util.LinkedList;

import mv.empilhaveis.*;
import mv.instrucoes.*;



public class MaquinaVirtual {

	private Stack<Empilhavel> pilhaDeDados;
	private Memoria memoria;
	private Programas programas;
	private Instrucao codigo;
	LinkedList<Programa> listaDeProgramas;
	
	void passo(){
		
	}
	
	void rodaMaquina() {
		pilhaDeDados = new Stack<Empilhavel>();
	    memoria = new Memoria();
		programas = new Programas();
		

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
	
	public static void main(String[] args) {
			MaquinaVirtual m = new MaquinaVirtual();
			m.rodaMaquina();
	}

}
