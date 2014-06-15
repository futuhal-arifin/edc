package id.ac.ui.edoocatia.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class SpaceBattleMissilePlayer implements SpaceBattleMissile {

	private float posY;
	private float posX;
	private Rectangle bounds;
	private boolean hit;
	private Texture missileTexture;
	
	public SpaceBattleMissilePlayer(float playerXPos, float playerYPos) {
		this.setHit(false);
		this.posY = playerYPos;
		this.setPosX(playerXPos);
		this.missileTexture = new Texture(
				Gdx.files.internal("data/images/modul-2/icon_efek/fire_alien.png"));
		setBounds(new Rectangle(posX, posY, missileTexture.getWidth(), missileTexture.getHeight()));
	}
	
	@Override
	public void updateMissilePosition() {
		this.posY = posY + 3;
		if(posY > 800) {
			this.dispose();
		}
	}

	@Override
	public Texture getMissileTexture() {
		return this.missileTexture;
	}

	@Override
	public float getPosY() {
		return this.posY;
	}

	@Override
	public void setPosY(float posY) {
		this.posY = posY;
	}

	@Override
	public float getPosX() {
		return this.posX;
	}

	@Override
	public void setPosX(float posX) {
		this.posX = posX;
	}

	@Override
	public void dispose() {
		this.missileTexture.dispose();
	}

	@Override
	public boolean isHit() {
		return this.hit;
	}

	@Override
	public void setHit(boolean hit) {
		this.hit = hit;
	}

	@Override
	public Rectangle getBounds() {
		return this.bounds;
	}

	@Override
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

}
