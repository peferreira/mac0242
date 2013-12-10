package mv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;
import java.util.LinkedList;

import parser.ParseException;
import parser.Parser;
import mv.empilhaveis.*;
import mv.instrucoes.*;

public class MaquinaVirtual {
	private boolean estado;
	private Stack<Operacao> pilhaDeOperacoes;
	private Stack<Empilhavel> pilhaDeDados;
	private Memoria memoria;
	private Programas programas;
	private Instrucao codigo;
	LinkedList<Programa> listaDeProgramas;
	boolean ultimaResposta;
	Random rand;
	String nameProg;
	public MaquinaVirtual(String s) {
		estado = true;
		rand = new Random(Double.doubleToLongBits(Math.random()));
		pilhaDeOperacoes = new Stack<Operacao>();
		pilhaDeDados = new Stack<Empilhavel>();
		memoria = new Memoria();
		/*programas = new Programas();
		programas.testaRoboVsCristalVsBase();
		listaDeProgramas = programas.getListaDeProgramas();*/
		listaDeProgramas = new LinkedList<Programa>();
		this.nameProg = s;
	}

	public void defineProg(){
		Parser parser = null;
		Instrucao[] prog = null;
		try
		{
				
			  System.out.println(System.getProperty("user.dir"));
		      parser = new Parser(new FileInputStream(System.getProperty("user.dir") + "/src/parser/programa"+nameProg+".txt"));
	    }			
		 catch (FileNotFoundException e)
		    {}// e um cristal
		
		try {
		prog = parser.Programa();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Programa programa = new Programa();
		programa.addInstrucoes(prog);
		listaDeProgramas.add(programa);
		
	}
	
	public void passo() {
		if (estado) {
			for (Programa prg : listaDeProgramas) {
				codigo = prg.getInstrucao();
				while (!(codigo instanceof END)
						&& !(codigo instanceof Especial)) {
					codigo.executar(pilhaDeDados, memoria, prg, rand);
					codigo = prg.getInstrucao();
				}
				if (codigo instanceof Especial) {
					((Especial) codigo).geraOperacao(pilhaDeDados,
							pilhaDeOperacoes, prg);
				}
			}
		}
	}

	public Operacao getOperacao() {
		Operacao op;
		try {
			op = pilhaDeOperacoes.pop();
		} catch (EmptyStackException excecao) {
			System.out.println("Não existem operações para processamento!");
			op = null;
		}
		return op;
	}

	public void setResposta(Empilhavel resposta) {
		pilhaDeDados.push(resposta);
	}
	
	public void desativarMV() {
		estado = false;
	}
}
