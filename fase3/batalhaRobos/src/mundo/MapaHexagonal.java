package mundo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import mundo.elementos.Robo;

public class MapaHexagonal{

	private int maxJ;
	private int maxI;
	private Hexagono[][] mapaHexagonal;
	int Larg, Alt, Dx, Dy; // largura do terreno, altura do terreno, incremento
							// em x e incremento em y
	BufferedImage grama, terra, agua; // texturas a serem carregadas para o
	private int[][] Terreno;
										// terreno

	/*int[][] Terreno = { 
			{ 0, 0, 0, 1, 2, 2, 2, 2, 1, 1 }, { 0, 0, 1, 1, 2, 2, 2, 2, 1, 1 },
			{ 0, 0, 1, 2, 2, 2, 0, 2, 1, 1 }, { 0, 0, 1, 1, 1, 2, 2, 2, 2, 2 },
			{ 0, 0, 0, 0, 1, 2, 2, 2, 2, 2 }, { 0, 0, 0, 1, 0, 2, 2, 2, 2, 2 },
			{ 0, 0, 1, 1, 0, 0, 0, 2, 2, 1 }, { 0, 0, 1, 1, 0, 0, 2, 2, 2, 1 },
			{ 1, 1, 1, 1, 0, 0, 0, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, };*/

	public MapaHexagonal(int maxJ, int maxI, int L, int W, int H) {
		this.maxJ = maxJ;
		this.maxI = maxI;
		this.Terreno = new int[maxI][maxJ];
		for(int i = 0; i < maxI;i++)
			for(int j = 0; j < maxJ; j++)
				Terreno[i][j] = 1;

		mapaHexagonal = new Hexagono[this.maxJ][this.maxI];

		Dx = (int) (2 * L * Math.sin(2 * Math.PI / 6)); // incremento em x para
														// desenhar os hexágonos
		Dy = 3 * L / 2; // idem para y
		Larg = W;
		Alt = H;

		// cada try..catch que segue carregará uma textura, ou levantará uma
		// exceção que encerrará a aplicação com erro
		try {
			grama = ImageIO.read(this.getClass().getResource("grama2.png"));
		} catch (Exception e) {

			System.exit(1);
		}

		
		try {
			terra = ImageIO.read(this.getClass().getResource("terra2.png"));
		} catch (Exception e) {
			System.exit(1);
		}

		try {
			agua = ImageIO.read(this.getClass().getResource("agua2.png"));
		} catch (Exception e) {
			System.exit(1);
		}

		BufferedImage[] Textura = { agua, terra, grama }; // array de texturas
															// (valores de
															// enumeração: 0, 1,
															// 2)

		int DELTA = 0;
		for (int j = 0; j < maxJ; j++) {
			for (int i = 0; i < maxI; i++) {
				// instância das células hexagonais, com as texturas adequadas,
				// e atribuição destas ao mapa (a ser renderizado em
				// paintComponent)
				mapaHexagonal[j][i] = new Hexagono(DELTA + L + i * Dx,   L + j
						* Dy,L, Textura[Terreno[j][i]]);
			}
			DELTA = DELTA == 0 ? Dx / 2 : 0 ;
		}
	}
	
	

	/*void inicializa() {
		for (int j = 0; j < maxJ; j++) {
			for (int i = 0; i < maxI; i++) {
				mapaHexagonal[j][i] = new Hexagono(j, i, 1, agua);
			}
		}
	}*/
	void draw(Graphics g){
		for (int i = 0; i < maxI; i++) {
			for (int j = 0; j < maxJ; j++) {
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
