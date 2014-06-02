package id.ac.ui.edoocatia.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class SpaceBattleAlienMissile {
	public static final float INIT_POS = 600;
	
	private float posY;
	private float posX;
	private Rectangle bounds;
	private boolean hit;
	private Texture missileTexture;
	
	private byte alienSource;
	private final byte LEFT = 0;
	private final byte RIGHT = 1;
	
	public SpaceBattleAlienMissile(byte position) {
		this.setAlienSource(position);
		this.posY = INIT_POS;
		if(this.alienSource == LEFT) {
			this.setPosX(200);
		} else {
			this.setPosX(600);
		}
		this.missileTexture = new Texture(
				Gdx.files.internal("data/images/modul-2/icon_efek/fire_alien.png"));
		setBounds(new Rectangle(posX, INIT_POS+31, missileTexture.getWidth(), missileTexture.getHeight()));
	}
	
	public void updateMissilePosition() {
		this.setPosY(posY - 1);
		if(this.alienSource == LEFT) {
			this.setPosX(posX + 1);
		} else {
			this.setPosX(posX - 1);
		}
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
	public byte getAlienSource() {
		return alienSource;
	}
	public void setAlienSource(byte alienSource) {
		this.alienSource = alienSource;
	}
	
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
	
}
