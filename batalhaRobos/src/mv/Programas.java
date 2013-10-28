package mv;


import java.util.LinkedList;

import mv.empilhaveis.*;
import mv.instrucoes.*;

public class Programas {

    private LinkedList<Programa> listaDeProgramas;

    public Programas () {
	listaDeProgramas = new LinkedList<Programa>();
    }

    public void addPrograma (Programa prg) {
	listaDeProgramas.add(prg);
    }

    public LinkedList<Programa> getListaDeProgramas() {
	return listaDeProgramas;
    }

    public void setListaDeProgramas (LinkedList<Programa> listaprg) {
	listaDeProgramas = listaprg;
    }

    public void setListaDeProgramasPadroes () {
	Programa prg1 = new Programa ();
	Programa prg2 = new Programa (7);
	
	prg1.addInstrucao(new PUSH(new CadeiaDeCaracteres("Teste 1")));
	prg1.addInstrucao(new PRN());
	prg1.addInstrucao(new PUSH(new Inteiro(13)));
	prg1.addInstrucao(new DUP());
	prg1.addInstrucao(new PRN());
	prg1.addInstrucao(new PRN());
	prg1.addInstrucao(new END());
	
	prg2.addInstrucao(new PUSH(new CadeiaDeCaracteres("Teste 2")));
	prg2.addInstrucao(new PRN());
	prg2.addInstrucao(new PUSH(new Inteiro(2)));
	prg2.addInstrucao(new PUSH(new Inteiro(3)));
	prg2.addInstrucao(new ADD());
	prg2.addInstrucao(new PRN());
	prg2.addInstrucao(new END());

	listaDeProgramas.add(prg1);
	listaDeProgramas.add(prg2);
    }

}