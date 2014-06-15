package id.ac.ui.edoocatia.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class SpaceBattleAlien {
	private int alienXPosition;
	private int alienYPosition;
	private byte position;
	private final byte LEFT = 0;
	private final byte RIGHT = 1;
	private float alienRotation;
	private List<SpaceBattleMissileAlien> missiles;
	private int alienHealth;
	private boolean isActive;
	private Texture alienTexture;
	private Sprite alienSprite;
	private Rectangle bounds;
	private int status;
	public final int ALIEN_FLYING = 0;
	public final int ALIEN_ATTACKING = 1;
	public final int ALIEN_MOVING = 2;
	private final int velocity = 3;
	
	public SpaceBattleAlien(byte position) {
		this.position = position;
		this.setAlienYPosition(1000);
		this.setStatus(this.ALIEN_FLYING);
		this.missiles = new ArrayList<SpaceBattleMissileAlien>();
		if(this.position == LEFT) {
			this.setAlienXPosition(200);
			this.setAlienRotation(0.0f);
		} else {
			this.setAlienXPosition(1000);
			this.setAlienRotation(0.0f);
		}
		this.setAlienHealth(5);
		this.setActive(true);
		this.alienTexture = new Texture(
				Gdx.files.internal("data/images/modul-2/musuh.png"));
		this.alienSprite = new Sprite(alienTexture);
		this.alienSprite.rotate(this.alienRotation);
		this.alienSprite.setPosition(alienXPosition, alienYPosition);
		setBounds(new Rectangle(this.alienXPosition, this.alienYPosition, alienTexture.getWidth(), alienTexture.getHeight()));
	}
	
	public void addMissile (SpaceBattleMissileAlien newMissile) {
		this.missiles.add(newMissile);
	}
	
	public void updateAlien(float playerXPosition, float playerYPosition) {
		if (this.status == this.ALIEN_FLYING) {
			// posisinya turun ke bawah
			this.setAlienYPosition(alienYPosition - velocity);
			this.alienSprite.setPosition(alienXPosition, alienYPosition);
			if(this.alienYPosition < 600) {
				this.setStatus(this.ALIEN_ATTACKING);
			}
		} else if (this.status == this.ALIEN_ATTACKING) {
			// ganti rotasi
			double tan = Math.abs(playerXPosition - alienXPosition)/Math.abs(playerYPosition - alienYPosition);
			float gradient = (playerYPosition - this.alienYPosition) / (playerXPosition - this.alienXPosition); // m = (y1-y2)/(x1-x2)
			
			
			if(gradient > 0) { // alien kanan
				float degree =  Math.abs( (180.0f * (float)Math.atan(tan)) - 90.0f);
				if(Math.abs(this.getAlienRotation()) <  Math.abs(degree)) {
					//this.setAlienRotation(- degree - this.getAlienRotation()); 
					this.alienSprite.setRotation(-this.alienSprite.getRotation());
					this.setAlienRotation(-degree); 
					this.alienSprite.rotate(this.alienRotation);
					System.out.println("< right " + degree+" "+gradient);
				} else if(Math.abs(this.getAlienRotation()) >  Math.abs(degree)) {	
					//this.setAlienRotation(this.getAlienRotation() - degree); 
					this.alienSprite.setRotation(-this.alienSprite.getRotation());
					this.setAlienRotation(- degree); 
					this.alienSprite.rotate(this.alienRotation);
					System.out.println("> right " + degree+" "+gradient);
				}
			} else { //alien kiri
				float degree =   (180.0f * (float)Math.atan(tan))-90.0f;
				if(Math.abs(this.getAlienRotation()) <  Math.abs(degree)) {
					this.setAlienRotation(degree - this.getAlienRotation()); 
					this.alienSprite.rotate(this.alienRotation);
					//System.out.println(" < left " +  degree+" "+gradient);
				} else if(Math.abs(this.getAlienRotation()) >  Math.abs(degree)) {	
					this.setAlienRotation(this.getAlienRotation() - degree); 
					this.alienSprite.rotate(this.alienRotation);
					//System.out.println("> left " +  degree+" "+gradient);
				}	
			}
			
		} else {
			// ganti posisi
		}
		if(this.alienHealth == 0) {
			this.setActive(false);
		}
		
	}
	
	public int getAlienHealth() {
		return alienHealth;
	}
	public void setAlienHealth(int alienHealth) {
		this.alienHealth = alienHealth;
	}
	public void decrementAlienHealth() {
		this.setAlienHealth(this.alienHealth - 1);
	}
	public byte getPosition() {
		return position;
	}
	public void setPosition(byte position) {
		this.position = position;
	}
	public int getAlienXPosition() {
		return alienXPosition;
	}
	public void setAlienXPosition(int alienXPosition) {
		this.alienXPosition = alienXPosition;
	}
	public int getAlienYPosition() {
		return alienYPosition;
	}
	public void setAlienYPosition(int alienYPosition) {
		this.alienYPosition = alienYPosition;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public Sprite getAlienSprite() {
		return alienSprite;
	}

	public void setAlienSprite(Sprite alienSprite) {
		this.alienSprite = alienSprite;
	}
	
	public void dispose() {
		this.alienTexture.dispose();
	}

	public float getAlienRotation() {
		return alienRotation;
	}

	public void setAlienRotation(float alienRotation) {
		this.alienRotation = alienRotation;
	}

	public List<SpaceBattleMissileAlien> getMissiles() {
		return this.missiles;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
