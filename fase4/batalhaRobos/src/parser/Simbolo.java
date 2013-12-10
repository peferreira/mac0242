package parser;

import java.util.Vector;

import mv.empilhaveis.Endereco;

// Simbolo: pode ser nome de variavel ou funcao
class Simbolo {
        Endereco pos; // posi��o na mem�ria: endere�o

        Simbolo(int n) {
                pos = new Endereco(n);
        }

        void SetPos(int n) {
                pos = new Endereco(n);
        }
}

// Variavel
class Variavel extends Simbolo {
        static int nvars = 0; // n�mero de vari�veis globais

        Variavel() {
                super(nvars++);
        }
}
