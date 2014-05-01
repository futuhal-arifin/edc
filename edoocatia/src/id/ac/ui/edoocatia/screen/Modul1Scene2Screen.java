package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.Modul1Scene2Controller;

public class Modul1Scene2Screen extends DialogScreen {
	
	private Modul1Scene2Controller controller;
	private Texture background;
	private Texture pesawatBody;
	
	private Texture[] pesawatParts = new Texture[2];
	private Texture[] pesawatPartsSelected = new Texture[2];
	
	private Rectangle[] spionBounds;
	private Rectangle[] sayapBounds;
	
	private boolean[] partIsSelected = new boolean[2];
	//private boolean aPartHasBeenSelected;
	
	public final int SPION = 0;
	public final int SAYAP = 1;
	
	private final int spionYPosition = 300;
	//private final int spionXPosition = 300;
	private final int sayapYPosition = 100;
	//private final int sayapXPosition = 100;
	
	private boolean[] status = new boolean[2];
	
	public final short SAYAP_IS_DONE = 0;
	public final short SPION_IS_DONE = 1;
	
	private boolean showProfessor;
	private Texture playerTexture;
	
	private Animation player;
	private TextureRegion[] playerFrames;
	private TextureRegion currentFrame;
	private final int FRAME_COLS = 2;
	private final int FRAME_ROWS = 1;
	private float stateTime;
	
	private boolean debug = false;
	
	private long startDelay;

	public Modul1Scene2Screen(Edoocatia app) {
		super(app);
		
		this.startDelay = TimeUtils.millis();
		background = new Texture(Gdx.files.internal("data/images/modul-1/background/background.jpg"));
		
		if(app.getEdocatiaData().isModul1Scene3Done() && app.getEdocatiaData().isModul1Scene5Done()) {
			this.status[this.SAYAP_IS_DONE] = true;
			this.status[this.SPION_IS_DONE] = true;
			this.showProfessor = false;
			this.setDialogNaration("data/dialog/modul1/scene2c.txt");
			this.setPlayerAnimation();
		} else if(app.getEdocatiaData().isModul1Scene3Done()) {
			this.status[this.SAYAP_IS_DONE] = true;
			this.status[this.SPION_IS_DONE] = false;
			this.showProfessor = true;
			this.setDialogNaration("data/dialog/modul1/scene2a.txt");
		} else if(app.getEdocatiaData().isModul1Scene5Done()) {
			this.status[this.SAYAP_IS_DONE] = false;
			this.status[this.SPION_IS_DONE] = true;
			this.showProfessor = true;
			this.setDialogNaration("data/dialog/modul1/scene2b.txt");
		} else {
			this.status[this.SAYAP_IS_DONE] = false;
			this.status[this.SPION_IS_DONE] = false;
			this.showProfessor = true;
			this.setDialogNaration("data/dialog/modul1/scene2.txt");
		}
		
		this.pesawatBody = new Texture(Gdx.files.internal("data/images/modul-1/pesawat/badan.png"));
		this.pesawatParts[this.SPION] = new Texture(Gdx.files.internal("data/images/modul-1/pesawat/spion.png"));
		this.pesawatParts[this.SAYAP] = new Texture(Gdx.files.internal("data/images/modul-1/pesawat/sayap.png"));
		
		this.pesawatPartsSelected[this.SPION] = new Texture(Gdx.files.internal("data/images/modul-1/pesawat/spion_gelap.png"));
		this.pesawatPartsSelected[this.SAYAP] = new Texture(Gdx.files.internal("data/images/modul-1/pesawat/sayap_gelap.png"));
		
		
		this.setProfessorInfo();
		
		
		for (int idx = 0; idx < this.partIsSelected.length; idx++) {
			this.partIsSelected[idx] = false;
		}
		
		if (!this.status[this.SPION_IS_DONE]) {
			float spionLeftBound = (width-this.pesawatParts[this.SPION].getWidth())/2;
			float spionRightBound = (width+this.pesawatParts[this.SPION].getWidth())/2;
			int spionHeight = this.pesawatParts[this.SPION].getHeight();
			float spionRect1Width = 50;
			float spionRect2Width = 100;
			
			spionBounds = new Rectangle[]{
					new Rectangle(spionLeftBound, 
							this.spionYPosition+(spionHeight/2), spionRect1Width, spionHeight/2),
					new Rectangle(spionRect1Width+spionLeftBound, 
							this.spionYPosition, spionRect2Width, spionHeight),
							
					new Rectangle(spionRightBound-spionRect1Width, 
							this.spionYPosition+(spionHeight/2), spionRect1Width, spionHeight/2),
					new Rectangle(spionRightBound-spionRect2Width-spionRect1Width, 
							this.spionYPosition, spionRect2Width, spionHeight),
				};
		}
		
		if (!this.status[this.SAYAP_IS_DONE]) {
			float sayapLeftBound = (VIRTUAL_WIDTH-this.pesawatParts[this.SAYAP].getWidth())/2;
			float sayapRightBound = (VIRTUAL_WIDTH+this.pesawatParts[this.SAYAP].getWidth())/2;
			int sayapHeight = this.pesawatParts[this.SAYAP].getHeight();
			int sayapRect1Width = 70;
			int sayapRect2Width = 85;
			int sayapRect3Width = 55;
			int sayapRect4Width = 55;
			sayapBounds = new Rectangle[]{
					new Rectangle(sayapLeftBound, 
							this.sayapYPosition+sayapHeight/2, sayapRect1Width, (sayapHeight)/2),
					new Rectangle(sayapRect1Width+sayapLeftBound, 
							(sayapHeight/2), sayapRect2Width, 230),
					new Rectangle(-5+sayapRect1Width+sayapRect2Width+sayapLeftBound, 
							(sayapHeight/2)-95, sayapRect3Width, 235),
					new Rectangle(-5+sayapRect1Width+sayapRect2Width+sayapRect3Width+sayapLeftBound, 
							this.sayapYPosition, sayapRect4Width, 200),
					
					new Rectangle(-sayapRect1Width+sayapRightBound, 
							this.sayapYPosition+sayapHeight/2, sayapRect1Width, sayapHeight/2),
					new Rectangle(-(sayapRect1Width+sayapRect2Width)+sayapRightBound, 
							(sayapHeight/2), sayapRect2Width, 230),
					new Rectangle(-(-5+sayapRect1Width+sayapRect2Width+sayapRect3Width)+sayapRightBound, 
							(sayapHeight/2)-95, sayapRect3Width, 235),
					new Rectangle(-(-5+sayapRect1Width+sayapRect2Width+sayapRect3Width+sayapRect3Width)+sayapRightBound, 
							this.sayapYPosition, sayapRect4Width, 200),
				};
		}
		//this.aPartHasBeenSelected = false;
		
		this.controller = new Modul1Scene2Controller(this);
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
		
			batcher.draw(this.pesawatBody, 
				(VIRTUAL_WIDTH-this.pesawatBody.getWidth())/2, 
				100);
			
			if(this.isShowDialog() || this.partIsSelected(this.SAYAP) || this.status[this.SAYAP_IS_DONE]) {
				batcher.draw(this.pesawatParts[this.SAYAP], 
						(VIRTUAL_WIDTH-this.pesawatParts[this.SAYAP].getWidth())/2, 
						100);
			} else {
				batcher.draw(this.pesawatPartsSelected[this.SAYAP], 
						(VIRTUAL_WIDTH-this.pesawatParts[this.SAYAP].getWidth())/2, 
						100);
			}
			
			if(this.isShowDialog() || this.partIsSelected(this.SPION) || this.status[this.SPION_IS_DONE]) {
				batcher.draw(this.pesawatParts[this.SPION], 
						(VIRTUAL_WIDTH-this.pesawatParts[this.SPION].getWidth())/2, 
						300);
			} else {
				batcher.draw(this.pesawatPartsSelected[this.SPION], 
						(VIRTUAL_WIDTH-this.pesawatParts[this.SPION].getWidth())/2, 
						300);
			}
			
			if(this.status[this.SAYAP_IS_DONE] && this.status[this.SPION_IS_DONE]) {
				stateTime += Gdx.graphics.getDeltaTime();
				currentFrame = player.getKeyFrame(stateTime, true);
				batcher.draw(currentFrame, VIRTUAL_WIDTH - (currentFrame.getRegionWidth()), 0);
			}
			
		
		batcher.end();
		
		// kl mau munculin bounds, ubah variabel debug jd true
		if (debug) {
			drawDebug(this.sayapBounds, this.partIsSelected[this.SAYAP]);
			drawDebug(this.spionBounds, this.partIsSelected[this.SPION]);
		}
		if(this.showProfessor) {
			super.render(delta);
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
	
	private void setProfessorInfo() {
		this.setDialogBackground("data/images/general/dialog.png");
		this.setDialogBackgroundPosition((VIRTUAL_WIDTH - this.dialogBackground.getWidth())/2, 0);
		this.setKarakterLeftPosition(0, 0);
		this.setKarakterRightPosition(VIRTUAL_WIDTH, 0);
		this.setLineLength(725);
		this.setTextPosition(300, this.dialogBackground.getHeight() - 100);
		this.setFont("data/font/kg-corner-of-the-sky-44-black.fnt", 
				"data/font/kg-corner-of-the-sky-44-black.png");
	}
	
	// getter button bounds
	public Rectangle[] getSpionBounds() {
		return spionBounds;
	}
	
	// getter button bounds
	public Rectangle[] getSayapBounds() {
		return sayapBounds;
	}
	
	// getter button status
	public boolean partIsSelected(int index) {
		return this.partIsSelected[index];
	}
	
	// setter button status
	public void setPartStatus(boolean status, int index) {
		partIsSelected[index] = status;
	}
	/*
	public void setAPartHasSelected(boolean status) {
		this.aPartHasBeenSelected = status;
	}
	
	public boolean getAPartHasSelected() {
		return this.aPartHasBeenSelected;
	}
	*/

	public boolean getStatus(int index) {
		return status[index];
	}
	
	public boolean isShowProfessor() {
		return this.showProfessor;
	}
	
	public void setShowProfessor(boolean status) {
		this.showProfessor = status;
	}

	public long getStartDelay() {
		return startDelay;
	}

	public void setStartDelay(long startDelay) {
		this.startDelay = startDelay;
	}
}
