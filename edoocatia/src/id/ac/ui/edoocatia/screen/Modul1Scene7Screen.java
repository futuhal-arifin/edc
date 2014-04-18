package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.Modul1Scene7Controller;
import id.ac.ui.edoocatia.util.AbstractScreen;

public class Modul1Scene7Screen extends AbstractScreen {
	private Modul1Scene7Controller controller;
	private Texture background;
	
	private boolean debug = false;
	
	public final short READY_TO_ENTER = 0;
	public final short READY_TO_FLY = 1;
	public final short FLYING = 2;
	
	// buat adegan ready to enter
	private Texture pesawatTexture;
	private Texture playerTexture;
	private Animation player;
	private TextureRegion[] playerFrames;
	private TextureRegion currentFrame;
	private final int FRAME_COLS = 2;
	private final int FRAME_ROWS = 1;
	private float stateTime;
	private short states;
	private Rectangle playerBounds;
	private Rectangle playerCenterBounds;
	private Rectangle pesawatCenterBounds;
	
	private float playerXPosition;
	private float playerYPosition;
	
	// buat adegan ready to fly
	private Texture[] light = new Texture[2];
	private boolean[] lightOn = new boolean[2];
	private long[] lightStartTime = new long[2];
	public final int CENTRAL_LIGHT = 0;
	public final int MONITOR_LIGHT = 1;
	public final int CENTRAL_DIM_DELAY = 1000;
	public final int MONITOR_DIM_DELAY = 2000;
	public final int MONITOR_FLICKER_DELAY = 250;
	public final int MONITOR_FLICKER_COUNT = 6;
	private int currentMonitorFlickerCount;
	
	// buat adegan flying
	
	private Texture pesawatSideTexture;
	private float pesawatSideXPosition;

	public Modul1Scene7Screen(Edoocatia app) {
		super(app);
		this.setStates(this.READY_TO_ENTER);
		this.controller = new Modul1Scene7Controller(this);
	}
	
	private void initiateAdeganReadyToEnter() {
		background = new Texture(Gdx.files.internal("data/images/modul-1/background/lab_pesawat.jpg"));
		this.pesawatTexture = new Texture(Gdx.files.internal("data/images/modul-1/pesawat/pesawat.png"));
		
		
		this.setPlayerAnimation();	
		this.playerBounds = new Rectangle(this.playerXPosition, this.playerYPosition,
				this.playerTexture.getWidth() / 2, playerTexture.getHeight());
		this.playerCenterBounds = new Rectangle((playerBounds.getWidth()-50)/2, 
				(this.playerBounds.getHeight()-50)/2,
				75, 75);
		this.setPlayerPosition(0, 0);
		this.pesawatCenterBounds = new Rectangle((VIRTUAL_WIDTH-100)/2, 125,
				100, 125);
	}
	
	private void initiateAdeganReadyToFly() {
		background = new Texture(Gdx.files.internal("data/images/modul-1/pesawat/dalam-pesawat/dalam-pesawat.jpg"));
		this.light[this.CENTRAL_LIGHT] = new Texture(Gdx.files.internal("data/images/modul-1/pesawat/dalam-pesawat/light.png"));
		this.light[this.MONITOR_LIGHT] = new Texture(Gdx.files.internal("data/images/modul-1/pesawat/dalam-pesawat/monitor-light.png"));
		
		this.lightOn[this.CENTRAL_LIGHT] = false;
		this.lightOn[this.MONITOR_LIGHT] = false;
		
		this.lightStartTime[this.CENTRAL_LIGHT] = TimeUtils.millis();
		this.lightStartTime[this.MONITOR_LIGHT] = TimeUtils.millis();
		
		this.currentMonitorFlickerCount = 0;
	}
	
	private void initiateAdeganFlying() {
		background = new Texture(Gdx.files.internal("data/images/modul-1/background/background2.jpg"));
		this.pesawatSideTexture = new Texture(Gdx.files.internal("data/images/modul-1/pesawat/pesawat_tampak_samping.png"));
		this.setPesawatSideXPosition(-this.pesawatSideTexture.getWidth());
	}
	
	public void render(float delta) {
		cam.update();

		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
				(int) viewport.width, (int) viewport.height);
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
		batcher.draw(this.background, 0, 0);
		
		if(this.states == this.READY_TO_ENTER) {
			batcher.draw(this.pesawatTexture, 
					(VIRTUAL_WIDTH-this.pesawatTexture.getWidth())/2, 
					100);
			stateTime += Gdx.graphics.getDeltaTime();
			currentFrame = player.getKeyFrame(stateTime, true);
			//System.out.println(this.playerXPosition + " | " + this.playerYPosition);
			batcher.draw(currentFrame, this.playerXPosition, this.playerYPosition);
		} else if(this.states == this.READY_TO_FLY) {
			if(this.lightOn[this.CENTRAL_LIGHT]) {
				batcher.draw(this.light[this.CENTRAL_LIGHT], 0, 70);
			}
			if(this.lightOn[this.MONITOR_LIGHT]) {
				batcher.draw(this.light[this.MONITOR_LIGHT], 0, -40);
			}
		} else {
			batcher.draw(this.pesawatSideTexture, this.pesawatSideXPosition, (VIRTUAL_HEIGHT-this.pesawatSideTexture.getHeight())/2);
			this.pesawatSideXPosition += 20;
		}

		batcher.end();
		
		if(debug) {
			this.drawDebug(this.pesawatCenterBounds, false);
			this.drawDebug(this.playerBounds, false);
			this.drawDebug(this.playerCenterBounds, false);
		}
		
		controller.processInput();
	}
	
	private void setPlayerAnimation() {
		playerTexture = this.getApp().getEdocatiaData().getPlayer().getKarakterWinTexture();
		TextureRegion[][] temp = TextureRegion.split(playerTexture, playerTexture.getWidth()/FRAME_COLS, playerTexture.getHeight()/FRAME_ROWS);
		this.playerFrames = new TextureRegion[FRAME_COLS*FRAME_ROWS];
		int index= 0;
		for(int ii = 0; ii < FRAME_ROWS; ii++) {
			for(int jj = 0; jj < FRAME_COLS; jj++) {
				playerFrames[index++] = temp[ii][jj];
			}
		}
		player = new Animation(0.25f, playerFrames);
		stateTime = 0f;
	}
	
	public boolean isLightOn(int index) {
		return this.lightOn[index];
	}
	
	public void setLightOn(boolean status, int index) {
		 this.lightOn[index] = status;
	}
	
	public void setLightStartTime(long time, int index) {
		 this.lightStartTime[index] = time;
	}
	
	public long getLightStartTime(int index) {
		return this.lightStartTime[index];
	}
	
	public long[] getLightStartTime() {
		return this.lightStartTime;
	}
	
	public short getStates() {
		return states;
	}

	public void setStates(short states) {
		this.states = states;
		this.initiateAdegan(states);
	}
	
	private void initiateAdegan(short states) {
		switch(states) {
		case 0: this.initiateAdeganReadyToEnter(); break;
		case 1: this.initiateAdeganReadyToFly(); break;
		case 2: this.initiateAdeganFlying(); break;
		 
		}
	}
	

	public float getPlayerXPosition() {
		return playerXPosition;
	}

	

	public float getPlayerYPosition() {
		return playerYPosition;
	}

	public void setPlayerPosition(float playerXPosition, float playerYPosition) {
		this.playerXPosition = playerXPosition;
		this.playerYPosition = playerYPosition;
		this.updateBounds();
	}
	
	private void updateBounds() {
		this.playerBounds.setPosition(this.playerXPosition, this.playerYPosition);
		this.playerCenterBounds.setPosition(this.playerXPosition+((this.playerBounds.getWidth()-this.playerCenterBounds.getWidth())/2), 
				this.playerYPosition+((this.playerBounds.getHeight()-this.playerCenterBounds.getHeight())/2));
	}
	
	public void setPlayerXPosition(float playerXPosition) {
		this.playerXPosition = playerXPosition;
		this.playerBounds.setPosition(this.playerXPosition, this.playerYPosition);
		this.updateBounds();
	}
	
	public void setPlayerYPosition(float playerYPosition) {
		this.playerYPosition = playerYPosition;
		this.playerBounds.setPosition(this.playerXPosition, this.playerYPosition);
		this.updateBounds();
	}

	public float getPesawatSideXPosition() {
		return pesawatSideXPosition;
	}

	public void setPesawatSideXPosition(float pesawatSideXPosition) {
		this.pesawatSideXPosition = pesawatSideXPosition;
	}

	public Rectangle getPlayerBounds() {
		return playerBounds;
	}

	public void setPlayerBounds(Rectangle playerBounds) {
		this.playerBounds = playerBounds;
	}

	public Rectangle getPesawatCenterBounds() {
		return pesawatCenterBounds;
	}

	public void setPesawatCenterBounds(Rectangle pesawatCenterBounds) {
		this.pesawatCenterBounds = pesawatCenterBounds;
	}

	public Rectangle getPlayerCenterBounds() {
		return playerCenterBounds;
	}

	public void setPlayerCenterBounds(Rectangle playerCenterBounds) {
		this.playerCenterBounds = playerCenterBounds;
	}

	public int getCurrentMonitorFlickerCount() {
		return currentMonitorFlickerCount;
	}

	public void incrementCurrentMonitorFlickerCount() {
		this.currentMonitorFlickerCount++;
	}
}
