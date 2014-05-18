package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.ProfessorInstructionController;

public class ProfessorInstructionScreen extends DialogScreen {
	
	private Texture instructionObject;
	private Texture background;
	private ProfessorInstructionController controller;
	private float instructionObjectXPosition;
	private float instructionObjectYPosition;
	private boolean showInstruction;

	public ProfessorInstructionScreen(Edoocatia app) {
		super(app);
		
		this.setShowInstruction(true);
		this.setDialogBackground("data/images/general/dialog.png");
		this.setDialogBackgroundPosition(100+(this.getWidth()- this.dialogBackground.getWidth())/2, 0);
		this.setKarakterLeftPosition(0, 0);
		this.setKarakterRightPosition(width, 0);
		this.setLineLength(750);
		this.setTextPosition(400, this.dialogBackground.getHeight() - 100);
		this.setFont("data/font/kg-corner-of-the-sky-44-black.fnt", 
				"data/font/kg-corner-of-the-sky-44-black.png");
		
		controller = new ProfessorInstructionController(this);
	}
	
	public void render(float delta) {
		if(this.showInstruction) {
			batcher.begin();
			
			batcher.draw(this.background, 0, 0);
			
			if(this.instructionObject != null) {
				batcher.draw(this.instructionObject, 
						this.instructionObjectXPosition, 
						this.instructionObjectYPosition);
			}
			
			batcher.end();
			
			super.render(delta);
			controller.processInput();
		}
	}

	public void setInstructionObject(String path) {
		this.instructionObject = new Texture(Gdx.files.internal(path));
		this.instructionObjectXPosition = (VIRTUAL_WIDTH-this.instructionObject.getWidth())/2;
		this.instructionObjectYPosition = 100 + (VIRTUAL_HEIGHT-this.instructionObject.getHeight())/2;;
	}

	public void setBackground(String path) {
		this.background = new Texture(Gdx.files.internal(path));
	}
	
	public void setShowInstruction(boolean status) {
		this.showInstruction = status;
		if(this.showInstruction) {
			this.setMusicBg("data/sounds/music/modul1/instruksi.mp3");
		} else {
			this.stopMusic();
		}
	}
	
	public boolean getShowInstruction() {
		return this.showInstruction;
	}
	
	@Override
	public void dispose() {
		this.background.dispose();
		this.instructionObject.dispose();
		super.dispose();
	}
}
