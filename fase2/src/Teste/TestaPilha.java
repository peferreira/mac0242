package Teste;

import lab2.ime.usp.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestaPilha {

	@Test
	public void testaPush() {
		String nomeTeste = "Gilmar";
		Empilhavel a = new Teste(nomeTeste);
		Pilha pi = new Pilha();
		pi.push(a);
		Empilhavel t = pi.getIndice(0);
		assertEquals(a, t);
	}

	@Test
	public void testaPop() {
		Empilhavel a = new Teste();
		Empilhavel b = new Teste();
		Empilhavel c = new Teste();

		Pilha pi = new Pilha();
		pi.push(a);
		pi.push(b);
		pi.push(c);

		Empilhavel d = pi.pop();
		d = pi.pop();
		d = pi.pop();
		assertEquals(a, d);
	}

}
