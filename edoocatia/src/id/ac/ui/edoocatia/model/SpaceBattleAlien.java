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
	
	public SpaceBattleAlien(byte position) {
		this.position = position;
		this.setAlienYPosition(600);
		this.missiles = new ArrayList<SpaceBattleMissileAlien>();
		if(this.position == LEFT) {
			this.setAlienXPosition(200);
			this.setAlienRotation(135.0f);
		} else {
			this.setAlienXPosition(600);
			this.setAlienRotation(-135.0f);
		}
		this.setAlienHealth(5);
		this.setActive(true);
		this.alienTexture = new Texture(
				Gdx.files.internal("data/images/modul-2/musuh.png"));
		setBounds(new Rectangle(this.alienXPosition, this.alienYPosition, alienTexture.getWidth(), alienTexture.getHeight()));
	}
	
	public void updateAlien() {
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
}
