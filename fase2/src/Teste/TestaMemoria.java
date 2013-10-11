package Teste;

import lab2.ime.usp.Empilhavel;
import lab2.ime.usp.Inteiro;
import lab2.ime.usp.Memoria;
import lab2.ime.usp.Pilha;
import lab2.ime.usp.PilhaAritmetica;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestaMemoria {

	private static final String Memoria = null;

	@Test
	public void testaOperacoes() {
		Memoria mem = new Memoria();
		PilhaAritmetica pa = new PilhaAritmetica(mem);
		Empilhavel a = new Inteiro(1);
	    pa.push(a);
		pa.sto(5);
	    pa.rcl(5);
		Empilhavel b = pa.pop();
		assertEquals(a, b);
	}

}
