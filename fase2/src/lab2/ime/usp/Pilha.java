package lab2.ime.usp;

import java.util.ArrayList;
import java.util.List;

public class Pilha {

	List<Empilhavel> dataqueue;

	public Pilha() {
		dataqueue = new ArrayList<Empilhavel>();
	}

	public void push(Empilhavel novoElemento) {
		dataqueue.add(novoElemento);
	}

	public Empilhavel pop() {
		int size = dataqueue.size();
		return dataqueue.remove(size - 1);
	}

	public Empilhavel getIndice(int idx) {
		return dataqueue.get(idx);
	}
	/*
	 * public static void main(String args[]) { Pilha p = new Pilha(); Teste
	 * a,b,c;
	 * 
	 * a = new Teste("arara"); b = new Teste("baleia"); c = new
	 * Teste("cachorro"); p.dataqueue = new ArrayList<Empilhavel>();
	 * 
	 * p.dataqueue.add(a); p.dataqueue.add(b); p.dataqueue.add(c);
	 * 
	 * for (Empilhavel x: p.dataqueue) { x.voar();
	 * 
	 * }
	 */

}