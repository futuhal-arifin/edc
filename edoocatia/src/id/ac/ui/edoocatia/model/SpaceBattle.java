package id.ac.ui.edoocatia.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class SpaceBattle {
	
	private float pesawatXPosition;
	private float maxDistance;
	private float currentDistance;
	private final float leftLimit = 230f;
	private final float rightLimit = 1050f;
	private final float VELOCITY = 5;
	private float time;
	private Random rand;
	private float lastObstacles;
	private float lastAlienMissile;
	private float lastPlayerMissile;
	private List<SpaceBattleMeteor> meteors;
	private SpaceBattleAlien alienLeft;
	private SpaceBattleAlien alienRight;
	private float background1YPosition;
	private float background2YPosition;
	private Rectangle pesawatBounds;
	private Texture pesawat;
	private boolean isAlienComing;
	private int alienCounter;
	private float screenWidth;
	private float screenHeight;
	private final int MULTIPLIER = 14;

	public SpaceBattle(int distance, float vIRTUAL_WIDTH, float vIRTUAL_HEIGHT) {
		this.initSpaceBattle(distance, vIRTUAL_WIDTH, vIRTUAL_HEIGHT);
	}
	
	public void initSpaceBattle(int distance, float vIRTUAL_WIDTH, float vIRTUAL_HEIGHT) {
		this.setAlienComing(false);
		this.alienCounter = 1;
		this.maxDistance = distance * this.MULTIPLIER;
		this.screenHeight = vIRTUAL_HEIGHT;
		this.screenWidth = vIRTUAL_WIDTH;
		this.currentDistance = 0.0f;
		pesawatXPosition = screenWidth / 2;
		rand = new Random();
		background1YPosition = 0.0f;
		background2YPosition = screenHeight;
		time =0;
		lastObstacles = -1.5f;
		lastAlienMissile = -0.5f;
		lastPlayerMissile = -0.2f;
		meteors = new ArrayList<SpaceBattleMeteor>();
		alienLeft = new SpaceBattleAlien((byte) 0);
		alienRight = new SpaceBattleAlien((byte) 1);
		this.pesawat = new Texture(
				Gdx.files.internal("data/images/modul-2/pesawat.png"));
		this.pesawatBounds = new Rectangle(pesawatXPosition+(pesawat.getWidth()/7), 20, pesawat.getWidth()/4, pesawat.getHeight());
	}
	
	public void generateMeteors(float delta){
		//if(gameOver) return;
		time += delta;
		float temp = rand.nextFloat();
		//System.out.println("time "+time);
		if(temp > 0.7) {
			int index = rand.nextInt(6);
			if(index < 3) {
				if(time>(lastObstacles+2.5)){
					lastObstacles = time;
					meteors.add(new SpaceBattleMeteor(index));
				}
			}
		}
	}
	
	public void generateMissiles(float delta){
		time += delta;
		float temp = rand.nextFloat();
		if(temp > 0.7) {
			int index = rand.nextInt(6);
			if(index < 3) {
				if(time>(lastAlienMissile+2.5)){
					if(rand.nextFloat()>0.5){
						this.alienLeft.addMissile(new SpaceBattleMissileAlien((byte) 0));
					} else {
						this.alienRight.addMissile(new SpaceBattleMissileAlien((byte) 1));
					}
				}
			}
		}
	}

	public float getBackground1YPosition() {
		return this.background1YPosition;
	}
	
	public float getBackground2YPosition() {
		return this.background2YPosition;
	}

	public List<SpaceBattleMeteor> getMeteors() {
		return this.meteors;
	}

	public float getPesawatXPosition() {
		return this.pesawatXPosition;
	}

	public void updatePosition (float delta){
		Iterator<SpaceBattleMeteor> itr = meteors.iterator();
		while(itr.hasNext()){
			SpaceBattleMeteor obs = itr.next();
			obs.setYPosition(obs.getYPosition() - (this.VELOCITY * 2));
			
			if(obs.getYPosition()<-126){
				obs.dispose();
				itr.remove();
			}
			
		}
		
		if(this.isAlienComing) {
			this.alienLeft.updateAlien(this.pesawatXPosition, 0);
			this.alienRight.updateAlien(this.pesawatXPosition, 0);
			if(this.alienLeft.getStatus() == this.alienLeft.ALIEN_FLYING) {
				
			} else if(this.alienLeft.getStatus() == this.alienLeft.ALIEN_ATTACKING) {
				this.generateMissiles(delta);
				Iterator<SpaceBattleMissileAlien> itrLeft = alienLeft.getMissiles().iterator();
				while(itrLeft.hasNext()){
					SpaceBattleMissileAlien obs = itrLeft.next();
					obs.updateMissilePosition();
					if(obs.getPosY() < -126){
						obs.dispose();
						itrLeft.remove();
					}
				}
				
				Iterator<SpaceBattleMissileAlien> itrRight = alienRight.getMissiles().iterator();
				while(itrRight.hasNext()){
					SpaceBattleMissileAlien obs = itrRight.next();
					obs.updateMissilePosition();
					if(obs.getPosY() < -126){
						obs.dispose();
						itrRight.remove();
					}
				}
			}
			
			
			
		} else {
			
			
			if(this.background1YPosition >= - this.screenHeight) {
				this.background1YPosition -= VELOCITY;
			}
			
			if(this.background2YPosition >= - this.screenHeight) {
				this.background2YPosition -= VELOCITY;
			}
			
			if(this.background1YPosition < 0) {
				this.background2YPosition = this.background1YPosition + this.screenHeight;
			} 
			
			if(this.background2YPosition < 0) {
				this.background1YPosition = this.background2YPosition + this.screenHeight;
			}
			
			this.currentDistance +=VELOCITY;
			if(this.currentDistance == this.alienCounter * 50 * this.MULTIPLIER) {
				this.setAlienComing(true);
				this.alienCounter++;
			}
		}
		
		
		
	}

	public float getLeftLimit() {
		return this.leftLimit;
	}
	
	public Rectangle getPesawatBounds() {
		return pesawatBounds;
	}
	
	public Texture getPesawatTexture() {
		return pesawat;
	}

	public void setPesawatBounds(Rectangle r) {
		this.pesawatBounds = r;
	}

	public void setPesawatXPosition(float f) {
		this.pesawatXPosition = f;
		this.pesawatBounds.x = f;
	}

	public float getRightLimit() {
		return this.rightLimit;
	}

	public float getCurrentDistance() {
		return this.currentDistance;
	}
	
	public float getMaxDistance() {
		return this.maxDistance;
	}

	public void dispose() {
		this.pesawat.dispose();
	}

	public boolean isAlienComing() {
		return isAlienComing;
	}

	public void setAlienComing(boolean isAlienComing) {
		this.isAlienComing = isAlienComing;
	}
	
	public SpaceBattleAlien getAlienLeft() {
		return this.alienLeft;
	}
	
	public SpaceBattleAlien getAlienRight() {
		return this.alienRight;
	}

}
