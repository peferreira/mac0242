package lab2.ime.usp;

import java.util.ArrayList;
import java.util.List;



public class Memoria {
	final int SIZE = 50;
	Empilhavel[] mem;

	public Memoria(){
		mem = new Empilhavel[SIZE];
	}

	public void set(Empilhavel e, int index) {
		mem[index] = e;
	}

	public Empilhavel get(int index) {
		return mem[index];
	}
}
