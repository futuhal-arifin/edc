package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
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
		
		this.showInstruction = true;
		this.setDialogBackground("data/images/general/dialog.png");
		this.setDialogBackgroundPosition((this.getWidth()- this.dialogBackground.getWidth())/2, 40);
		this.setKarakterLeftPosition(0, 40);
		this.setKarakterRightPosition(width, 40);
		this.setLineLength(750);
		this.setTextPosition(300, this.dialogBackground.getHeight() - 50);
		this.setFont("data/font/kg-corner-of-the-sky-44-black.fnt", 
				"data/font/kg-corner-of-the-sky-44-black.png");
		
		controller = new ProfessorInstructionController(this);
	}
	
	public void render(float delta) {
		if(this.showInstruction) {
			batcher.begin();
			
			batcher.draw(this.background, 0, 0);
			
			batcher.draw(this.instructionObject, 
					this.instructionObjectXPosition, 
					this.instructionObjectYPosition);

			batcher.end();
			
			super.render(delta);
			controller.processInput();
		}
	}

	public void setInstructionObject(String path) {
		this.instructionObject = new Texture(Gdx.files.internal(path));
		this.instructionObjectXPosition = (width-this.instructionObject.getWidth())/2;
		this.instructionObjectYPosition = this.dialogBackgroundYPosition + this.getDialogBackground().getHeight() + (height-this.instructionObject.getHeight())/2;
	}

	public void setBackground(String path) {
		this.background = new Texture(Gdx.files.internal(path));
	}
	
	public void setShowInstruction(boolean status) {
		this.showInstruction = status;
	}
	
	public boolean getShowInstruction() {
		return this.showInstruction;
	}

}