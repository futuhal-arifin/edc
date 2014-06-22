package id.ac.ui.edoocatia.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class SpaceBattleMeteor {
	public static final float INIT_POS = 860;
	
	private int index;
	private float posY;
	private float posX;
	private Rectangle bounds;
	private boolean hit;
	private Texture meteorTexture;
	
	public SpaceBattleMeteor(int index) {
		this.setIndex(index);
		this.meteorTexture = new Texture(
				Gdx.files.internal("data/images/modul-2/meteor_small.png"));
		hit = false;
		
		float xBounds = -100;
		if(index==0){
			xBounds = 250;
		}
		else if(index==1){
			xBounds = 512;
		}
		else if(index==2){
			xBounds = 774;
		}
		this.setXPosition(xBounds);
		setBounds(new Rectangle(xBounds, INIT_POS, meteorTexture.getWidth(), meteorTexture.getHeight()));
		setYPosition(INIT_POS);
	}
	
	public float getYPosition() {
		return posY;
	}

	public void setYPosition(float f) {
		this.posY = f;
		this.bounds.y = f;
	}
	
	public float getXPosition() {
		return posX;
	}

	public void setXPosition(float f) {
		this.posX = f;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
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
	public Texture getMeteorTexture() {
		return meteorTexture;
	}
	
	public void dispose() {
		this.meteorTexture.dispose();
	}

}
