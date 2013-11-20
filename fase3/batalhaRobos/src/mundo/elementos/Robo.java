package mundo.elementos;

import graficos.Sprite;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Robo implements Posicionavel {

	private int posI;
	private int posJ;
	private int exercito;
	private int id;
	private int vidas;
	private Cristal cristal;
	BufferedImage img;
	private Sprite sprite;
	
	
	public Robo(int id) {
		this.id = id;
		vidas = 3;
		cristal = null;
		this.sprite = graficos.SpriteStore.get().getSprite("graficos/roboamerica.png");
	}
	public void draw(Graphics g, int x, int y) {
		
		sprite.draw(g, x+11, y+15);
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
	
	public int getExercito(){
		return exercito;
	}
	
	public void setExercito(int exercito){
		this.exercito = exercito;
	}
	
	public Cristal getCristal(){
		return cristal;
	}
	
	public void setCristal(Cristal c){
		cristal = c;
	}
	
	public Cristal removeCristal(){
		Cristal aux = cristal;
		cristal = null;
		return aux;
	}
	
	public boolean temCristal(){
		return cristal == null ? false : true;
	}
	
	public int getID(){
		return id;
	}

}