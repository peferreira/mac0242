package parser;

import java.util.Vector;

import mv.empilhaveis.Endereco;

// Simbolo: pode ser nome de variavel ou funcao
class Simbolo {
        Endereco pos; // posição na memória: endereço

        Simbolo(int n) {
                pos = new Endereco(n);
        }

        void SetPos(int n) {
                pos = new Endereco(n);
        }
}

// Variavel
class Variavel extends Simbolo {
        static int nvars = 0; // número de variáveis globais

        Variavel() {
                super(nvars++);
        }
}
