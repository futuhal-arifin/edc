package id.ac.ui.edoocatia.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class SpaceBattleMissileAlien implements SpaceBattleMissile {
	//public static final float INIT_POS = 600;
	private float posY;
	private float posX;
	private Rectangle bounds;
	private boolean hit;
	private Texture missileTexture;
	
	//private byte alienSource;
	//private final byte LEFT = 0;
	//private final byte RIGHT = 1;
	private final float velocity = 7;
	private boolean isMoving;
	private double distance;
	private double directionX;
	private double directionY;
	
	public SpaceBattleMissileAlien(float startX, float startY) {
		//this.setAlienSource(position);
		this.setHit(false);
		this.posY = startY;
		this.posX = startX;
		// untuk keperluan gerakin misil
		isMoving = false;
		distance = 0.0;
		directionX = 0.0;
		directionY = 0.0;
		
		this.missileTexture = new Texture(
				Gdx.files.internal("data/images/modul-2/icon_efek/fire_alien.png"));
		setBounds(new Rectangle(posX, posY, missileTexture.getWidth(), missileTexture.getHeight()));
	}
	
	
	public void updateMissilePosition(float playerXPos, float playerYPos) {
		if(!isMoving) {
			distance = Math.sqrt(Math.pow(playerXPos-posX,2)+Math.pow(playerYPos-posY,2));
			directionX = (posX-playerXPos) / distance;
			directionY = (posY-playerYPos) / distance;
			this.isMoving = true;
		} else {
			 posX -= directionX * this.velocity;
			 posY -= directionY * this.velocity;
			 this.bounds.x = this.posX;
			 this.bounds.y = this.posY;
			    //if(Math.sqrt(Math.pow(object.X-startX,2)+Math.pow(object.Y-startY,2)) >= distance)
			    //{
			      //  object.X = endX;
			        //object.Y = endY;
			        //moving = false;
			    //}
		}
		
		
		float gradient = (playerYPos - this.posY) / (playerXPos - this.posX); // m = (y1-y2)/(x1-x2)
		float constant = posY - (gradient * posX); // c = y - mx
		//double b = Math.sqrt(Math.pow(this.velocity, 2) - Math.pow(a, 2));
		float b;
		if(gradient > 0) { // player di kirinya alien
			b =  posX - playerXPos;
			posX--;
		} else {
			b =  playerXPos - posX;
			posX++;
		}
		
		posY = gradient * posX + constant; // y = mx+c
	}
	
	public Texture getMissileTexture() {
		return this.missileTexture;
	}
	
	public float getPosY() {
		return posY;
	}
	public void setPosY(float posY) {
		this.posY = posY;
	}
	public float getPosX() {
		return posX;
	}
	public void setPosX(float posX) {
		this.posX = posX;
	}
	/*
	public byte getAlienSource() {
		return alienSource;
	}
	public void setAlienSource(byte alienSource) {
		this.alienSource = alienSource;
	}
	*/
	public void dispose() {
		this.missileTexture.dispose();
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}


	@Override
	public void updateMissilePosition() {
		// TODO Auto-generated method stub
		
	}
	
}
