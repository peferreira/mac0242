package mundo.elementos;

import java.awt.Graphics;

import graficos.Sprite;

public class Bala {
	private int vida;
	private int turno;
	private String dir;
	private Sprite[] balaSprite;
	private int posI;
	private int posJ;

	public Bala(String dir) {
		vida = 4;
		turno = 0;
		this.dir = dir;
		this.balaSprite = new Sprite[3];
		setBalaSprite();
	}
	
	public int getPosI() {
		return posI;
	}
	
	public int getPosJ() {
		return posJ;
	}
	
	public void setPosI(int posI) {
		this.posI = posI;
	}
	
	public void setPosJ(int posJ) {
		this.posJ = posJ;
	}

	public int getVida() {
		return vida;
	}
	
	public void decVida() {
		if (vida > 0){
			vida--;
		} else {
			System.out.println("Bala: Vida  menor do que zero!");
		}
	}

	public String getDir() {
		return dir;
	}
	
	public void setTurno(int turno) {
		this.turno = turno;
	}
	
	public void decTurno() {
		if (turno > 0){
			turno--;
		} else {
			System.out.println("Bala: Turno  menor do que zero!");
		}
	}
	
	public void draw(Graphics g, int x, int y) {
		balaSprite[2 - turno].draw(g, x, y);
	}

	private void setBalaSprite() {
		switch (dir) {
		case "E":
			balaSprite[0] = graficos.SpriteStore.get().getSprite(
					"graficos/balaW.png");
			balaSprite[1] = graficos.SpriteStore.get().getSprite(
					"graficos/balaC.png");
			balaSprite[2] = graficos.SpriteStore.get().getSprite(
					"graficos/balaE.png");
			break;
		case "W":
			balaSprite[0] = graficos.SpriteStore.get().getSprite(
					"graficos/balaE.png");
			balaSprite[1] = graficos.SpriteStore.get().getSprite(
					"graficos/balaC.png");
			balaSprite[2] = graficos.SpriteStore.get().getSprite(
					"graficos/balaW.png");
			break;
		case "NE":
			balaSprite[0] = graficos.SpriteStore.get().getSprite(
					"graficos/balaSW.png");
			balaSprite[1] = graficos.SpriteStore.get().getSprite(
					"graficos/balaC.png");
			balaSprite[2] = graficos.SpriteStore.get().getSprite(
					"graficos/balaNE.png");
			break;
		case "NW":
			balaSprite[0] = graficos.SpriteStore.get().getSprite(
					"graficos/balaSE.png");
			balaSprite[1] = graficos.SpriteStore.get().getSprite(
					"graficos/balaC.png");
			balaSprite[2] = graficos.SpriteStore.get().getSprite(
					"graficos/balaNW.png");
			break;
		case "SE":
			balaSprite[0] = graficos.SpriteStore.get().getSprite(
					"graficos/balaNW.png");
			balaSprite[1] = graficos.SpriteStore.get().getSprite(
					"graficos/balaC.png");
			balaSprite[2] = graficos.SpriteStore.get().getSprite(
					"graficos/balaSE.png");
			break;
		case "SW":
			balaSprite[0] = graficos.SpriteStore.get().getSprite(
					"graficos/balaNE.png");
			balaSprite[1] = graficos.SpriteStore.get().getSprite(
					"graficos/balaC.png");
			balaSprite[2] = graficos.SpriteStore.get().getSprite(
					"graficos/balaSW.png");
			break;

		default:
			System.out.println("Direção inválida de ataque!");
			System.exit(1);
			break;
		}
	}

	public int getTurno() {
		// TODO Auto-generated method stub
		return turno;
	}

}
