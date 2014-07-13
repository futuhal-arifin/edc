package id.ac.ui.edoocatia.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class SpaceBattle {
	
	//private float pesawatXPosition;
	private float maxDistance;
	private float currentDistance;
	private final float leftLimit = 230f;
	private final float rightLimit = 1050f;
	public final float VELOCITY = 5;
	private float time;
	private Random rand;
	private float lastObstacles;
	private float lastAlienMissile;
	private List<SpaceBattleMeteor> meteors;
	private SpaceBattleAlien alienLeft;
	private SpaceBattleAlien alienRight;
	private float background1YPosition;
	private float background2YPosition;
	//private Rectangle pesawatBounds;
	//private Texture pesawat;
	private boolean isAlienComing;
	private int alienCounter;
	private float screenWidth;
	private float screenHeight;
	public final int MULTIPLIER = 8;
	private SpaceBattlePlayer player;

	public SpaceBattle(int distance, float vIRTUAL_WIDTH, float vIRTUAL_HEIGHT) {
		this.initSpaceBattle(distance, vIRTUAL_WIDTH, vIRTUAL_HEIGHT);
	}
	
	public void initSpaceBattle(int distance, float vIRTUAL_WIDTH, float vIRTUAL_HEIGHT) {
		this.setAlienComing(false);
		this.setAlienCounter(1);
		this.maxDistance = distance * this.MULTIPLIER;
		this.setScreenHeight(vIRTUAL_HEIGHT);
		this.screenWidth = vIRTUAL_WIDTH;
		this.setCurrentDistance(0.0f);
		setPlayer(new SpaceBattlePlayer(screenWidth / 2, 10));
		rand = new Random();
		setBackground1YPosition(0.0f);
		setBackground2YPosition(getScreenHeight());
		time =0;
		lastObstacles = -1.5f;
		meteors = new ArrayList<SpaceBattleMeteor>();
		
	}
	
	public void generateAlienPosition() {
		int newXLeft = this.generateRandomAlienPosition((int)this.rightLimit, (int)this.leftLimit);
		int newYLeft = this.generateRandomAlienPosition(700, 300);
		this.alienLeft.setTargetXPosition(newXLeft);
		this.alienLeft.setTargetYPosition(newYLeft);
		boolean isCollide = true;
		// kanan
		int newXRight = -1;
		int newYRight = -1;
		while(isCollide) {
			newXRight = this.generateRandomAlienPosition((int)this.rightLimit, (int)this.leftLimit);
			newYRight = this.generateRandomAlienPosition(700, 300);
			
			if(Math.abs(newXLeft - newXRight) > this.alienLeft.getAlienTexture().getWidth()) {
				isCollide = false;
				this.alienRight.setTargetXPosition(newXRight);
				this.alienRight.setTargetYPosition(newYRight);
			}
		}
		System.out.println(newXLeft + " " + newYLeft + " " + newXRight + " " + newYRight);
	}
	
	private int generateRandomAlienPosition(int upperLimit, int lowerLimit) {
		Random rand = new Random();
		boolean isOk = false;
		int newPos = 500;
		while(!isOk) {
			newPos = rand.nextInt(upperLimit);
			if (newPos > lowerLimit) {
				isOk = true;
			}
		}
		return newPos;
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
		if(temp > 0.8) {
			int index = rand.nextInt(10);
			if(index < 3) {
				if(time>(lastAlienMissile+5)){
					if(rand.nextFloat()>0.5){
						this.alienLeft.addMissile(new SpaceBattleMissileAlien(this.alienLeft.getAlienXPosition(),this.alienLeft.getAlienYPosition()));
					} else {
						this.alienRight.addMissile(new SpaceBattleMissileAlien(this.alienRight.getAlienXPosition(),this.alienRight.getAlienYPosition()));
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
		return this.getPlayer().getPesawatXPosition();
	}
/*
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
			this.alienLeft.updateAlien(this.getPesawatXPosition()+ this.getPlayer().getPesawat().getWidth()/2, 10);
			this.alienRight.updateAlien(this.getPesawatXPosition() + this.getPlayer().getPesawat().getWidth()/2, 10);
			if(this.alienLeft.getStatus() == this.alienLeft.ALIEN_FLYING) {
				
			} else if(this.alienLeft.getStatus() == this.alienLeft.ALIEN_ATTACKING) {

				//System.out.println("sb" + this.pesawatXPosition);
				this.generateMissiles(delta);
				Iterator<SpaceBattleMissileAlien> itrLeft = alienLeft.getMissiles().iterator();
				while(itrLeft.hasNext()){
					SpaceBattleMissileAlien obs = itrLeft.next();
					obs.updateMissilePosition(this.getPesawatXPosition(), this.getPlayer().getPesawatYPosition());
					if(obs.getPosY() < -126){
						obs.dispose();
						itrLeft.remove();
					}
				}
				
				Iterator<SpaceBattleMissileAlien> itrRight = alienRight.getMissiles().iterator();
				while(itrRight.hasNext()){
					SpaceBattleMissileAlien obs = itrRight.next();
					obs.updateMissilePosition(this.getPesawatXPosition(), this.getPlayer().getPesawatYPosition());
					if(obs.getPosY() < -126){
						obs.dispose();
						itrRight.remove();
					}
				}
				Iterator<SpaceBattleMissilePlayer> itrPlayer = getPlayer().getMissiles().iterator();
				while(itrPlayer.hasNext()){
					SpaceBattleMissilePlayer obs = itrPlayer.next();
					obs.updateMissilePosition();
					if(obs.getPosY() < -126){
						obs.dispose();
						itrPlayer.remove();
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
			if(this.currentDistance == this.alienCounter * 150 * this.MULTIPLIER) {
				this.setAlienComing(true);
				this.alienCounter++;
			}
		}
		
	}

	*/
	public float getLeftLimit() {
		return this.leftLimit;
	}
	
	public Rectangle getPesawatBounds() {
		return this.getPlayer().getPesawatBounds();
	}
	
	public Texture getPesawatTexture() {
		return this.getPlayer().getPesawat();
	}

	public void setPesawatBounds(Rectangle r) {
		this.getPlayer().setPesawatBounds(r);
	}

	public void setPesawatXPosition(float f) {
		this.getPlayer().setPesawatXPosition(f);
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
		this.getPlayer().dispose();;
	}

	public boolean isAlienComing() {
		return isAlienComing;
	}

	public void setAlienComing(boolean isAlienComing) {
		this.isAlienComing = isAlienComing;
		if(isAlienComing){
			lastAlienMissile = -1.0f;
			alienLeft = new SpaceBattleAlien((byte) 0);
			alienRight = new SpaceBattleAlien((byte) 1);
		} else {
			if(alienLeft != null) {
				alienLeft.dispose();
			}
			if(alienRight != null) {
				alienRight.dispose();
			}
		}
	}
	
	public SpaceBattleAlien getAlienLeft() {
		return this.alienLeft;
	}
	
	public SpaceBattleAlien getAlienRight() {
		return this.alienRight;
	}

	public void launchPlayerMissile() {
		this.getPlayer().addMissile();
	}

	public SpaceBattlePlayer getSpaceBattlePlayer() {
		return this.getPlayer();
	}

	public SpaceBattlePlayer getPlayer() {
		return player;
	}

	public void setPlayer(SpaceBattlePlayer player) {
		this.player = player;
	}

	public void setBackground1YPosition(float background1YPosition) {
		this.background1YPosition = background1YPosition;
	}

	public float getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(float screenHeight) {
		this.screenHeight = screenHeight;
	}

	public void setBackground2YPosition(float background2YPosition) {
		this.background2YPosition = background2YPosition;
	}

	public void setCurrentDistance(float currentDistance) {
		this.currentDistance = currentDistance;
	}

	public int getAlienCounter() {
		return alienCounter;
	}

	public void setAlienCounter(int alienCounter) {
		this.alienCounter = alienCounter;
	}

}
