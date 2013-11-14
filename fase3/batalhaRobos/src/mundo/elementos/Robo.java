package mundo.elementos;

import graficos.Sprite;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Robo {

	private int posI;
	private int posJ;
	private int equipe;
	private int id;
	private int vidas;
	BufferedImage img;
	private Sprite sprite;
	
	public Robo(int id) {
		this.id = id;
		this.sprite = graficos.SpriteStore.get().getSprite("graficos/robo1.png");

		
	}
	
	public void setPosI(int posI) {
		this.posI = posI;
	}

	public void setPosJ(int posJ) {
		this.posJ = posJ;
	}

	public int getPosI() {
		return posI;
	}

	public int getPosJ() {
		return posJ;
	}
	
	public int getVidas() {
		return vidas;
	}

	public void alteraPosicao(int posI, int posJ) {
		this.posI = posI;
		this.posJ = posJ;
	}
	
	public int getEquipe(){
		return equipe;
	}
	
	public void setEquipe(int equipe){
		this.equipe = equipe;
	}
	
	public int getID(){
		return id;
	}

	public void draw(Graphics g, int x, int y) {
		sprite.draw(g, x, y);
	}

}