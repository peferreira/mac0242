package parser;

import java.util.Vector;

import mv.empilhaveis.Endereco;

// S�mbolo: pode ser nome de vari�vel ou fun��o
class S�mbolo {
        Endereco pos; // posi��o na mem�ria: endere�o

        S�mbolo(int n) {
                pos = new Endereco(n);
        }

        void SetPos(int n) {
                pos = new Endereco(n);
        }
}

// Fun��o
class Fun��o extends S�mbolo {
        Vector<String> args = new Vector<String>(); // lista dos argumentos
        TabSim Vars = new TabSim(); // nomes dos argumentos

        Fun��o(int n) {
                super(n);
        }

        // inclui um argumento
        void addarg(String a) {
                Vari�vel v = new Vari�vel();
                args.add(a);
                Vars.add(a, v);
        }

        // pega o nome do argumento na posi��o n
        String getarg(int n) {
                return args.get(n);
        }

        // retorna a vari�vel de nome a
        Vari�vel get(String a) {
                return (Vari�vel) Vars.get(a);
        }

        // verifica a exist�ncia
        Boolean exists(String a) {
                return Vars.exists(a);
        }
}

// Vari�vel
class Vari�vel extends S�mbolo {
        static int nvars = 0; // n�mero de vari�veis globais

        Vari�vel() {
                super(nvars++);
        }
}