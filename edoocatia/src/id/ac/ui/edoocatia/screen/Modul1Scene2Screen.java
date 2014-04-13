package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

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
	
	private boolean debug = false;

	public Modul1Scene2Screen(Edoocatia app) {
		super(app);
		background = new Texture(Gdx.files.internal("data/images/modul-1/background/background.jpg"));
		
		this.pesawatBody = new Texture(Gdx.files.internal("data/images/modul-1/pesawat/badan.png"));
		this.pesawatParts[this.SPION] = new Texture(Gdx.files.internal("data/images/modul-1/pesawat/spion.png"));
		this.pesawatParts[this.SAYAP] = new Texture(Gdx.files.internal("data/images/modul-1/pesawat/sayap.png"));
		
		this.pesawatPartsSelected[this.SPION] = new Texture(Gdx.files.internal("data/images/modul-1/pesawat/spion_gelap.png"));
		this.pesawatPartsSelected[this.SAYAP] = new Texture(Gdx.files.internal("data/images/modul-1/pesawat/sayap_gelap.png"));
		
		this.setDialogNaration("data/dialog/modul1/scene2.txt");
		this.setDialogBackground("data/images/general/dialog.png");
		this.setDialogBackgroundPosition((this.getWidth()- this.dialogBackground.getWidth())/2, 0);
		this.setKarakterLeftPosition(0, 0);
		this.setKarakterRightPosition(VIRTUAL_WIDTH, 0);
		this.setLineLength(750);
		this.setTextPosition(300, this.dialogBackground.getHeight() - 100);
		this.setFont("data/font/kg-corner-of-the-sky-44-black.fnt", 
				"data/font/kg-corner-of-the-sky-44-black.png");
		
		for (int idx = 0; idx < this.partIsSelected.length; idx++) {
			this.partIsSelected[idx] = false;
		}
		
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
		
		//if(this.aPartHasBeenSelected) {
			/*
			if(this.partIsSelected[this.SAYAP]) {
				batcher.draw(this.pesawatParts[this.SAYAP], 
						(width-this.pesawatParts[this.SAYAP].getWidth())/2, 
						400);
			} else {
				batcher.draw(this.pesawatParts[this.SPION], 
						(width-this.pesawatParts[this.SPION].getWidth())/2, 
						500);
			}
			*/
		//} else {
			batcher.draw(this.pesawatBody, 
				(VIRTUAL_WIDTH-this.pesawatBody.getWidth())/2, 
				100);
			if(this.isShowDialog() || this.partIsSelected(this.SAYAP)) {
				batcher.draw(this.pesawatParts[this.SAYAP], 
						(VIRTUAL_WIDTH-this.pesawatParts[this.SAYAP].getWidth())/2, 
						100);
			} else {
				batcher.draw(this.pesawatPartsSelected[this.SAYAP], 
						(VIRTUAL_WIDTH-this.pesawatParts[this.SAYAP].getWidth())/2, 
						100);
			}
			
			if(this.isShowDialog() || this.partIsSelected(this.SPION)) {
				batcher.draw(this.pesawatParts[this.SPION], 
						(VIRTUAL_WIDTH-this.pesawatParts[this.SPION].getWidth())/2, 
						300);
			} else {
				batcher.draw(this.pesawatPartsSelected[this.SPION], 
						(VIRTUAL_WIDTH-this.pesawatParts[this.SPION].getWidth())/2, 
						300);
			}
			
		//}

		batcher.end();
		
		// kl mau munculin bounds, ubah variabel debug jd true
		if (debug) {
			drawDebug(this.sayapBounds, this.partIsSelected[this.SAYAP]);
			drawDebug(this.spionBounds, this.partIsSelected[this.SPION]);
		}
		
		super.render(delta);
		controller.processInput();
	}
	/*
	public void showInfoSubScene() {
		background = new Texture(Gdx.files.internal("data/images/modul-1/background/tada.jpg"));
		this.pesawatPartsSelected[this.SAYAP] = null;
		this.pesawatPartsSelected[this.SPION] = null;
		this.pesawatBody = null;
		
		if(this.partIsSelected[this.SAYAP]) {
			this.setDialogNaration("data/dialog/modul1/scene2b1.txt");
			this.pesawatParts[this.SPION] = null;
		} else if (this.partIsSelected[this.SPION]) {
			this.setDialogNaration("data/dialog/modul1/scene2b2.txt");
			this.pesawatParts[this.SPION] = null;
		}
	}
	*/
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
}
