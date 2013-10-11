package Teste;

import lab2.ime.usp.Empilhavel;
import lab2.ime.usp.Inteiro;
import lab2.ime.usp.PilhaAritmetica;
import lab2.ime.usp.Teste;

import org.junit.Test;

public class TestaPilhaAritmetica {

	@Test
	public void testaOperacaoDupInteger() {
		PilhaAritmetica pa = new PilhaAritmetica();
		pa.push(new Inteiro(1));
		pa.push(new Inteiro(2));
		Empilhavel e, f;
		pa.dup();
		e = pa.pop();
		f = pa.pop();

	}

	@Test
	public void testaOperacaoPrn() {
		PilhaAritmetica pa = new PilhaAritmetica();
		pa.push(new Inteiro(3));
		pa.push(new Teste("Gilmar"));
		pa.prn();
		pa.prn();
	}
}
