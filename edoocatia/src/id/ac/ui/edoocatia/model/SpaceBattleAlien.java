package id.ac.ui.edoocatia.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class SpaceBattleAlien {
	private float alienXPosition;
	private float alienYPosition;
	private int targetXPosition;
	private int targetYPosition;

	private byte position;

	private final byte LEFT = 0;

	private final byte RIGHT = 1;

	private float alienRotation;
	private List<SpaceBattleMissileAlien> missiles;
	private int alienHealth;
	private boolean isActive;
	private Texture alienTexture;
	//private TextureRegion alienTextureRegion;
	private Sprite alienSprite;
	private Rectangle bounds;
	private int status;
	public final int ALIEN_FLYING = 0;
	public final int ALIEN_ATTACKING = 1;
	public final int ALIEN_MOVING = 2;
	private float playerPrevPosition;
	private float alienPrevRotation;
	private final int VELOCITY = 3;
	private boolean isMoving;
	private boolean isArrived;
	public SpaceBattleAlien(byte position) {
		this.position = position;
		this.setAlienYPosition(1000);
		this.setStatus(this.ALIEN_FLYING);
		this.missiles = new ArrayList<SpaceBattleMissileAlien>();
		if(this.position == LEFT) {
			this.setAlienXPosition(200);
		} else {
			this.setAlienXPosition(1000);
		}
		this.playerPrevPosition = 0;
		this.setAlienRotation(0.0f);
		this.alienPrevRotation = -1.0f;
		this.setAlienHealth(5);
		this.setActive(true);
		this.isMoving = false;
		this.isArrived = false;
		this.alienTexture = new Texture(
				Gdx.files.internal("data/images/modul-2/musuh.png"));
		//this.alienTextureRegion = new TextureRegion(this.alienTexture, this.alienTexture.getWidth(), this.alienTexture.getHeight());
		this.alienSprite = new Sprite(alienTexture);
		alienSprite.setOrigin(alienSprite.getWidth() / 2,alienSprite.getHeight() / 2);
		this.alienSprite.rotate(this.alienRotation);
		this.alienSprite.setPosition(alienXPosition, alienYPosition);
		setBounds(new Rectangle(this.alienXPosition, this.alienYPosition, alienTexture.getWidth(), alienTexture.getHeight()));
	}
	public void addMissile (SpaceBattleMissileAlien newMissile) {
		this.missiles.add(newMissile);
	}
	public void checkHealth() {
		if(this.alienHealth == 0) {
			this.setActive(false);
		}
	}
	public void decrementAlienHealth() {
		this.setAlienHealth(this.alienHealth - 1);
	}
	
	public void decrementAlienHealthBy(int i) {
		this.alienHealth = this.alienHealth  - i;
		this.checkHealth();
	}
	
	public void dispose() {
		this.alienTexture.dispose();
	}
	
	public int getAlienHealth() {
		return alienHealth;
	}
	
	public float getAlienRotation() {
		return alienRotation;
	}
	
	public Sprite getAlienSprite() {
		return alienSprite;
	}
	
	public Texture getAlienTexture(){
		return this.alienTexture;
	}
	
	public float getAlienXPosition() {
		return alienXPosition;
	}
	public float getAlienYPosition() {
		return alienYPosition;
	}
	public Rectangle getBounds() {
		return bounds;
	}
	public List<SpaceBattleMissileAlien> getMissiles() {
		return this.missiles;
	}
	public byte getPosition() {
		return position;
	}
	public int getStatus() {
		return status;
	}
	public int getTargetXPosition() {
		return targetXPosition;
	}
	public int getTargetYPosition() {
		return targetYPosition;
	}
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setAlienHealth(int alienHealth) {
		this.alienHealth = alienHealth;
	}

	public void setAlienRotation(float alienRotation) {
		this.alienRotation = alienRotation;
	}

	public void setAlienSprite(Sprite alienSprite) {
		this.alienSprite = alienSprite;
	}

	public void setAlienXPosition(float alienXPosition2) {
		this.alienXPosition = alienXPosition2;
	}

	public void setAlienYPosition(float f) {
		this.alienYPosition = f;
	}
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public void setPosition(byte position) {
		this.position = position;
	}

	public void setStatus(int status) {
		this.status = status;
	}
/*
	public TextureRegion getAlienTextureRegion() {
		return alienTextureRegion;
	}

	public void setAlienTextureRegion(TextureRegion alienTextureRegion) {
		this.alienTextureRegion = alienTextureRegion;
	}*/

	public void setTargetXPosition(int targetXPosition) {
		this.targetXPosition = targetXPosition;
	}

	public void setTargetYPosition(int targetYPosition) {
		this.targetYPosition = targetYPosition;
	}

	public void updateAlien(float playerXPosition, float playerYPosition) {
		this.checkHealth();
		if (this.status == this.ALIEN_FLYING) {
			// posisinya turun ke bawah
			this.setAlienYPosition(alienYPosition - VELOCITY);
			this.alienSprite.setPosition(alienXPosition, alienYPosition);
			if(this.alienYPosition < 600) {
				this.setStatus(this.ALIEN_ATTACKING);
			}
			//System.out.println("fly ");
		} else if (this.status == this.ALIEN_ATTACKING) {
			
			
			// ganti rotasi
			double tan = Math.atan2(playerYPosition - alienYPosition, playerXPosition - alienXPosition);
			double angle = tan * (180/Math.PI);
			
			if(angle < 0)
			{
			    angle = 360 - (-angle);
			}
			//System.out.println("prev " + this.playerPrevPosition + " curr " + playerXPosition);
			if((int)this.playerPrevPosition != (int)angle) { // player gerak ke kanan
				this.alienRotation = (float) angle + 90.0f;
			//} else { // player gerak ke kiri
				//this.alienRotation = (float) angle;
				//System.out.println("angle " + angle);
				//alienSprite.setOrigin(alienSprite.getWidth() / 2,alienSprite.getHeight() / 2);
				this.alienSprite.rotate(-this.alienPrevRotation);
				this.alienSprite.rotate(this.alienRotation);
				this.alienPrevRotation = this.alienRotation;
			}
			this.playerPrevPosition = playerXPosition;
			float gradient = (playerYPosition - this.alienYPosition) / (playerXPosition - this.alienXPosition); // m = (y1-y2)/(x1-x2)
			
			/*
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
			*/
		} else if (this.status == this.ALIEN_MOVING) {
			// random posisi
			//this.generateAlienPosition();
			// ganti posisi
			this.moveAlienPosition();
		}
		
		
	}
	

	double directionX = 0.0;
	double directionY = 0.0;
	private void resetMoving() {
		directionX = 0.0;
		directionY = 0.0;
		this.isMoving = false;
	}
	private void moveAlienPosition() {
		if(!isMoving) {
			double distance = Math.sqrt(Math.pow(targetXPosition-alienXPosition,2)+Math.pow(targetYPosition-alienYPosition,2));
			directionX = (alienXPosition-targetXPosition) / distance;
			directionY = (alienYPosition-targetYPosition) / distance;
			this.isMoving = true;
			//System.out.println(directionX + " " + directionY);
		} else {
			if(Math.abs(this.targetXPosition - this.alienXPosition) <= 2 
				&& Math.abs(this.targetYPosition - this.alienYPosition) <= 2) {
				this.resetMoving();
				this.setStatus(this.ALIEN_ATTACKING);
				//System.out.println("arrived " + this.status);
			} else {
				//System.out.println("before "+ alienXPosition + " " + alienYPosition);
				this.setAlienXPosition((float) (alienXPosition - (directionX * this.VELOCITY)));
				this.setAlienYPosition((float) (alienYPosition - (directionY * this.VELOCITY)));
				this.bounds.x = this.alienXPosition;
				this.bounds.y = this.alienYPosition;
				this.alienSprite.setPosition(this.alienXPosition, this.alienYPosition);
				//System.out.println(alienXPosition + " " + alienYPosition +" "+ this.status);
			}
			 
			 //if()
			    //if(Math.sqrt(Math.pow(object.X-startX,2)+Math.pow(object.Y-startY,2)) >= distance)
			    //{
			      //  object.X = endX;
			        //object.Y = endY;
			        //moving = false;
			    //}
		}
	}
}
