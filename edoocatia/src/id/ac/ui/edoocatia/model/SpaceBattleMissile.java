package id.ac.ui.edoocatia.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public interface SpaceBattleMissile {
	
	public void updateMissilePosition();
	
	public Texture getMissileTexture();
	
	public float getPosY();
	
	public void setPosY(float posY);
	public float getPosX();
	public void setPosX(float posX);
	
	public void dispose();

	public boolean isHit();

	public void setHit(boolean hit);

	public Rectangle getBounds();

	public void setBounds(Rectangle bounds);
	
}
