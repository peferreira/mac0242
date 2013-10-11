package lab2.ime.usp;

import java.util.ArrayList;

public class PilhaAritmetica extends Pilha {
	public PilhaAritmetica() {
		dataqueue = new ArrayList<Empilhavel>();
	}
	
	public PilhaAritmetica(Memoria mem) {
		dataqueue = new ArrayList<Empilhavel>();
		this.mem = mem;
	}

	Memoria mem;

	public void dup() {
		Empilhavel e, f = pop();
		
		e = f.clona();
		push(e);
		push(f);
		
	}
	public void prn() {
		Empilhavel topo = pop();
		topo.print();
	}

	public void sto(int index) {
		Empilhavel topo = pop();
		mem.set(topo, index);
	}
	
	public void rcl(int index) {
		push(mem.get(index));
	}
}
