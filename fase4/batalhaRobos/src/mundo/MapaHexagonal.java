package mundo;

import graficos.Sprite;

import java.awt.Graphics;
//import java.awt.image.BufferedImage;





import mundo.elementos.Grama;
import mundo.elementos.Pantano;
import mundo.elementos.Robo;
import mundo.elementos.Solo;
import mundo.elementos.Terra;

//import javax.imageio.ImageIO;

public class MapaHexagonal {

	private int maxJ;
	private int maxI;
	private Hexagono[][] mapaHexagonal;
	private int[][] Terreno;
	int Larg, Alt, Dx, Dy; // largura do terreno, altura do terreno, incremento
	// em x e incremento em y
	Sprite grama, terra, pantano;

	public MapaHexagonal(int maxJ, int maxI, int L, int W, int H) {
		this.maxJ = maxJ;
		this.maxI = maxI;
		int DELTA = 0;
		double p;
		int t;
		Solo soloAux;
		mapaHexagonal = new Hexagono[this.maxJ][this.maxI];

		this.Terreno = new int[maxJ][maxI];
		for (int j = 0; j < maxJ; j++) {
			for (int i = 0; i < maxI; i++) {
				p = Math.random();
				if (p < 0.5) {
					t = 2;
				} else if (p >= 0.5 && p < 0.9) {
					t = 1;
				} else {
					t = 0;
				}
				this.Terreno[j][i] = t;
			}
		}
		/*
		 * Dx = (int) (2 * L * Math.sin(2 * Math.PI / 6)); // incremento em x
		 * para // desenhar os hexágonos Dy = 3 * L / 2; // idem para y Larg =
		 * W; Alt = H;
		 */

		// cada try..catch que segue carregará uma textura, ou levantará uma
		// exceção que encerrará a aplicação com erro

		pantano = graficos.SpriteStore.get().getSprite("graficos/hexagua.png");
		grama = graficos.SpriteStore.get().getSprite("graficos/hexgrama.png");
		terra = graficos.SpriteStore.get().getSprite("graficos/hexterra.png");
		Sprite[] imagem = { pantano, terra, grama }; // array de texturas

		// (valores de
		// enumeração: 0, 1,
		// 2)

		/*
		 * int DELTA = 0; for (int j = 0; j < maxJ; j++) { for (int i = 0; i <
		 * maxI; i++) { // instância das células hexagonais, com as texturas
		 * // adequadas, // e atribuição destas ao mapa (a ser renderizado em
		 * // paintComponent) mapaHexagonal[j][i] = new Hexagono(DELTA + L + i *
		 * Dx, L + j Dy, L, Textura[Terreno[j][i]]); } DELTA = DELTA == 0 ? Dx /
		 * 2 : 0; }
		 */
		for (int j = 0; j < maxJ; j++) {
			for (int i = 0; i < maxI; i++) {
				switch(Terreno[j][i]){
				case 0:
					soloAux = new Pantano();
					break;
				case 1:
					soloAux = new Terra();
					break;
				case 2:
					soloAux = new Grama();
					break;
				default:
					soloAux = null;
					break;
				}
				mapaHexagonal[j][i] = new Hexagono(DELTA + i * 52, j * 45, L, soloAux,
						imagem[Terreno[j][i]]);

			}
			DELTA = DELTA == 0 ? 52 / 2 : 0;
		}
	}

	void geraMapaAleatorio() {

	}

	void draw(Graphics g) {
		for (int j = 0; j < maxJ; j++) {
			for (int i = 0; i < maxI; i++) {
				mapaHexagonal[j][i].draw(g);
			}
		}
	}

	int getMaxI() {
		return maxI;
	}

	int getMaxJ() {
		return maxJ;
	}

	Hexagono getHexagono(int i, int j) {
		return mapaHexagonal[j][i];
	}

	void setNovoRobo(Robo r) {
		mapaHexagonal[r.getPosJ()][r.getPosI()].setOcupante(r);
	}
}
