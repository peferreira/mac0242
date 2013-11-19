package mv;

import java.util.LinkedList;
import mv.empilhaveis.*;
import mv.instrucoes.*;

public class Programas {

	private LinkedList<Programa> listaDeProgramas;

	public Programas() {
		listaDeProgramas = new LinkedList<Programa>();
	}

	public void addPrograma(Programa prg) {
		listaDeProgramas.add(prg);
	}

	public LinkedList<Programa> getListaDeProgramas() {
		return listaDeProgramas;
	}

	public void setListaDeProgramas(LinkedList<Programa> listaprg) {
		listaDeProgramas = listaprg;
	}

	public void setTestaMaquinaVirtual() {
		Programa prg1 = new Programa();
		Programa prg2 = new Programa();
		Programa prg3 = new Programa();
		Programa prg4 = new Programa();
		Programa prg5 = new Programa();
		Programa prg6 = new Programa();
		Programa prg7 = new Programa();
		Programa prg8 = new Programa();
		
		Endereco loop;

		prg1.addInstrucao(new PUSH(new CadeiaDeCaracteres(
				"Calcula a hipotenusa (ao quadraddo):")));
		prg1.addInstrucao(new PRN());
		prg1.addInstrucao(new PUSH(new Inteiro(3)));
		prg1.addInstrucao(new STO(new Endereco(0)));
		prg1.addInstrucao(new PUSH(new Inteiro(4)));
		prg1.addInstrucao(new STO(new Endereco(1)));
		prg1.addInstrucao(new RCL(new Endereco(0)));
		prg1.addInstrucao(new DUP());
		prg1.addInstrucao(new MUL());
		prg1.addInstrucao(new STO(new Endereco(0)));
		prg1.addInstrucao(new RCL(new Endereco(1)));
		prg1.addInstrucao(new DUP());
		prg1.addInstrucao(new MUL());
		prg1.addInstrucao(new STO(new Endereco(1)));
		prg1.addInstrucao(new RCL(new Endereco(0)));
		prg1.addInstrucao(new RCL(new Endereco(1)));
		prg1.addInstrucao(new ADD());
		prg1.addInstrucao(new PRN());
		prg1.addInstrucao(new END());

		prg2.addInstrucao(new PUSH(new CadeiaDeCaracteres("Contador Simples:")));
		prg2.addInstrucao(new PRN());
		prg2.addInstrucao(new PUSH(new Inteiro(10)));
		prg2.addInstrucao(new STO(new Endereco(0)));
		prg2.addInstrucao(new PUSH(new Inteiro(1)));
		prg2.addInstrucao(new STO(new Endereco(1)));
		prg2.addInstrucao(new RCL(new Endereco(1)));
		prg2.addInstrucao(new PRN());
		loop = new Endereco(prg2.getNumeroDeInstrucoes());
		prg2.addInstrucao(new PUSH(new Inteiro(1)));
		prg2.addInstrucao(new RCL(new Endereco(1)));
		prg2.addInstrucao(new ADD());
		prg2.addInstrucao(new STO(new Endereco(1)));
		prg2.addInstrucao(new RCL(new Endereco(1)));
		prg2.addInstrucao(new PRN());
		prg2.addInstrucao(new RCL(new Endereco(1)));
		prg2.addInstrucao(new RCL(new Endereco(0)));
		prg2.addInstrucao(new LT());
		prg2.addInstrucao(new JIT(loop));
		prg2.addInstrucao(new END());

		prg3.addInstrucao(new PUSH(new CadeiaDeCaracteres("Conta Simples:")));
		prg3.addInstrucao(new PRN());
		prg3.addInstrucao(new PUSH(new Inteiro(10)));
		prg3.addInstrucao(new PUSH(new Inteiro(4)));
		prg3.addInstrucao(new ADD());
		prg3.addInstrucao(new PUSH(new Inteiro(3)));
		prg3.addInstrucao(new MUL());
		prg3.addInstrucao(new PRN());
		prg3.addInstrucao(new END());

		prg4.addInstrucao(new PUSH(new CadeiaDeCaracteres("Fibonacci:")));
		prg4.addInstrucao(new PRN());
		prg4.addInstrucao(new PUSH(new Inteiro(1)));
		prg4.addInstrucao(new PUSH(new Inteiro(1)));
		prg4.addInstrucao(new STO(new Endereco(0)));
		prg4.addInstrucao(new STO(new Endereco(1)));
		prg4.addInstrucao(new PUSH(new Inteiro(10)));
		prg4.addInstrucao(new STO(new Endereco(2)));
		loop = new Endereco(prg4.getNumeroDeInstrucoes());
		prg4.addInstrucao(new RCL(new Endereco(0)));
		prg4.addInstrucao(new RCL(new Endereco(1)));
		prg4.addInstrucao(new DUP());
		prg4.addInstrucao(new STO(new Endereco(0)));
		prg4.addInstrucao(new ADD());
		prg4.addInstrucao(new DUP());
		prg4.addInstrucao(new STO(new Endereco(1)));
		prg4.addInstrucao(new PRN());
		prg4.addInstrucao(new RCL(new Endereco(2)));
		prg4.addInstrucao(new PUSH(new Inteiro(1)));
		prg4.addInstrucao(new SUB());
		prg4.addInstrucao(new DUP());
		prg4.addInstrucao(new STO(new Endereco(2)));
		prg4.addInstrucao(new PUSH(new Inteiro(0)));
		prg4.addInstrucao(new EQ());
		prg4.addInstrucao(new JIF(loop));
		prg4.addInstrucao(new END());
		
		prg5.addInstrucao(new PUSH(new CadeiaDeCaracteres("Fibonacci2:")));
		prg5.addInstrucao(new PRN());
		prg5.addInstrucao(new PUSH(new Inteiro(1)));
		prg5.addInstrucao(new PUSH(new Inteiro(0)));
		prg5.addInstrucao(new STO(new Endereco(0)));
		prg5.addInstrucao(new STO(new Endereco(1)));
		prg5.addInstrucao(new PUSH(new Inteiro(10)));
		prg5.addInstrucao(new STO(new Endereco(2)));
		loop = new Endereco(prg5.getNumeroDeInstrucoes());
		prg5.addInstrucao(new RCL(new Endereco(0)));
		prg5.addInstrucao(new RCL(new Endereco(1)));
		prg5.addInstrucao(new DUP());
		prg5.addInstrucao(new STO(new Endereco(0)));
		prg5.addInstrucao(new ADD());
		prg5.addInstrucao(new DUP());
		prg5.addInstrucao(new STO(new Endereco(1)));
		prg5.addInstrucao(new PRN());
		prg5.addInstrucao(new RCL(new Endereco(2)));
		prg5.addInstrucao(new PUSH(new Inteiro(1)));
		prg5.addInstrucao(new SUB());
		prg5.addInstrucao(new DUP());
		prg5.addInstrucao(new STO(new Endereco(2)));
		prg5.addInstrucao(new PUSH(new Inteiro(0)));
		prg5.addInstrucao(new EQ());
		prg5.addInstrucao(new JIF(loop));
		prg5.addInstrucao(new END());
		
		prg6.addInstrucao(new PUSH(new CadeiaDeCaracteres("Fibonacci3:")));
		prg6.addInstrucao(new PRN());
		prg6.addInstrucao(new PUSH(new Inteiro(0)));
		prg6.addInstrucao(new STO(new Endereco(0)));
		prg6.addInstrucao(new PUSH(new Inteiro(1)));
		prg6.addInstrucao(new STO(new Endereco(1)));
		prg6.addInstrucao(new RCL(new Endereco(1)));
		prg6.addInstrucao(new PRN());
		prg6.addInstrucao(new PUSH(new Inteiro(20)));
		prg6.addInstrucao(new STO(new Endereco(2)));
		loop = new Endereco(prg6.getNumeroDeInstrucoes());
		prg6.addInstrucao(new RCL(new Endereco(0)));
		prg6.addInstrucao(new RCL(new Endereco(1)));
		prg6.addInstrucao(new ADD());
		prg6.addInstrucao(new RCL(new Endereco(1)));
		prg6.addInstrucao(new STO(new Endereco(0)));
		prg6.addInstrucao(new STO(new Endereco(1)));
		prg6.addInstrucao(new RCL(new Endereco(1)));
		prg6.addInstrucao(new PRN());
		prg6.addInstrucao(new RCL(new Endereco(2)));
		prg6.addInstrucao(new PUSH(new Inteiro(1)));
		prg6.addInstrucao(new SUB());
		prg6.addInstrucao(new STO(new Endereco(2)));
		prg6.addInstrucao(new PUSH(new Inteiro(0)));
		prg6.addInstrucao(new RCL(new Endereco(2)));
		prg6.addInstrucao(new EQ());
		prg6.addInstrucao(new JIF(loop));
		prg6.addInstrucao(new END());
		
		prg7.addInstrucao(new PUSH(new CadeiaDeCaracteres("PG:")));
		prg7.addInstrucao(new PRN());
		prg7.addInstrucao(new PUSH(new Real(1.5)));
		prg7.addInstrucao(new STO(new Endereco(0)));
		prg7.addInstrucao(new PUSH(new Inteiro(1)));
		prg7.addInstrucao(new STO(new Endereco(1)));
		prg7.addInstrucao(new PUSH(new Inteiro(20)));
		prg7.addInstrucao(new STO(new Endereco(2)));
		loop = new Endereco(prg7.getNumeroDeInstrucoes());
		prg7.addInstrucao(new RCL(new Endereco(1)));
		prg7.addInstrucao(new PRN());
		prg7.addInstrucao(new RCL(new Endereco(0)));
		prg7.addInstrucao(new RCL(new Endereco(1)));
		prg7.addInstrucao(new MUL());
		prg7.addInstrucao(new STO(new Endereco(1)));
		prg7.addInstrucao(new RCL(new Endereco(2)));
		prg7.addInstrucao(new PUSH(new Inteiro(1)));
		prg7.addInstrucao(new SUB());
		prg7.addInstrucao(new STO(new Endereco(2)));
		prg7.addInstrucao(new RCL(new Endereco(2)));
		prg7.addInstrucao(new PUSH(new Inteiro(0)));
		prg7.addInstrucao(new NE());
		prg7.addInstrucao(new JIT(loop));
		prg7.addInstrucao(new END());
		
		prg8.addInstrucao(new PUSH(new CadeiaDeCaracteres("Potencia2:")));
		prg8.addInstrucao(new PRN());
		prg8.addInstrucao(new PUSH(new Inteiro(1)));
		prg8.addInstrucao(new STO(new Endereco(0)));
		prg8.addInstrucao(new PUSH(new Inteiro(10000000)));
		prg8.addInstrucao(new STO(new Endereco(1)));
		loop = new Endereco(prg8.getNumeroDeInstrucoes());
		prg8.addInstrucao(new RCL(new Endereco(0)));
		prg8.addInstrucao(new DUP());
		prg8.addInstrucao(new ADD());
		prg8.addInstrucao(new STO(new Endereco(0)));
		prg8.addInstrucao(new RCL(new Endereco(0)));
		prg8.addInstrucao(new PRN());
		prg8.addInstrucao(new RCL(new Endereco(1)));
		prg8.addInstrucao(new RCL(new Endereco(0)));
		prg8.addInstrucao(new LT());
		prg8.addInstrucao(new JIF(loop));
		prg8.addInstrucao(new END());
		
		listaDeProgramas.add(prg1);
		listaDeProgramas.add(prg2);
		listaDeProgramas.add(prg3);
		listaDeProgramas.add(prg4);
		listaDeProgramas.add(prg5);
		listaDeProgramas.add(prg6);
		listaDeProgramas.add(prg7);
		listaDeProgramas.add(prg8);
	}

	public void testaMovimentoDeUmRobo() {
		Programa prg1 = new Programa();

		prg1.addInstrucao(new PUSH(new EAST()));
		prg1.addInstrucao(new MOVE());
		prg1.addInstrucao(new PRN());
		prg1.addInstrucao(new PUSH(new SWEST()));
		prg1.addInstrucao(new MOVE());
		prg1.addInstrucao(new PRN());
		prg1.addInstrucao(new PUSH(new WEST()));
		prg1.addInstrucao(new MOVE());
		prg1.addInstrucao(new PRN());
		prg1.addInstrucao(new PUSH(new NEAST()));
		prg1.addInstrucao(new MOVE());
		prg1.addInstrucao(new PRN());
		prg1.addInstrucao(new END());

		listaDeProgramas.add(prg1);
	}

}