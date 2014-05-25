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
	private final float rightLimit = 800f;
	private float VELOCITY;
	private float time;
	private Random rand;
	private float lastObstacles;
	private List<SpaceBattleMeteor> meteors;
	private List<SpaceBattleAlien> aliens;
	private float backgroundYPosition;
	private Rectangle pesawatBounds;
	private Texture pesawat;
	
	private float screenWidth;
	private float screenHeight;

	public SpaceBattle(int distance, float vIRTUAL_WIDTH, float vIRTUAL_HEIGHT) {
		this.initSpaceBattle(distance, vIRTUAL_WIDTH, vIRTUAL_HEIGHT);
	}
	
	public void initSpaceBattle(int distance, float vIRTUAL_WIDTH, float vIRTUAL_HEIGHT) {
		this.maxDistance = distance * 28;
		this.screenHeight = vIRTUAL_HEIGHT;
		this.screenWidth = vIRTUAL_WIDTH;
		this.currentDistance = 0.0f;
		pesawatXPosition = screenWidth / 2;
		VELOCITY = 3.0f;
		rand = new Random();
		backgroundYPosition = 0.0f;
		time =0;
		lastObstacles = -1.5f;
		meteors = new ArrayList<SpaceBattleMeteor>();
		aliens = new ArrayList<SpaceBattleAlien>();
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
			if(index < 3)
			if(time>(lastObstacles+2.5)){
				lastObstacles = time;
				/*
				int obsType = 0;
				if(rand.nextFloat()>0.7){
					type +=3;
				} else if(rand.nextFloat() > 0.4) {
					obsType = 2;
					//type +=2;
				} 
				*/
				meteors.add(new SpaceBattleMeteor(index));
			}
		}
	}

	public float getBackgroundYPosition() {
		return this.backgroundYPosition;
	}

	public List<SpaceBattleMeteor> getMeteors() {
		return this.meteors;
	}

	public float getPesawatXPosition() {
		return this.pesawatXPosition;
	}

	public void updatePosition (){
		
		Iterator<SpaceBattleMeteor> itr = meteors.iterator();
		while(itr.hasNext()){
			SpaceBattleMeteor obs = itr.next();
			obs.setYPosition(obs.getYPosition() - (this.VELOCITY * 3));
			
			if(obs.getYPosition()<-126){
				obs.dispose();
				itr.remove();
			}
			
		}
		
		if(backgroundYPosition > -(screenHeight + VELOCITY + 2)) {
			this.backgroundYPosition -= VELOCITY;
		} else  {
			backgroundYPosition = 0.0f;
		}
		
		this.currentDistance +=VELOCITY;
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

}
