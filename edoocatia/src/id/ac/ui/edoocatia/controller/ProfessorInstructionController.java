package id.ac.ui.edoocatia.controller;

import id.ac.ui.edoocatia.screen.ProfessorInstructionScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

public class ProfessorInstructionController {
	
	//private Edoocatia app;
	private ProfessorInstructionScreen screen;

	public ProfessorInstructionController(ProfessorInstructionScreen screen) {
		this.screen = screen;
		//app = screen.getApp();
	}

	public void processInput() {
		if(screen.isDialogEnded()) {
			long currentTime  = TimeUtils.millis();
			long startTime = screen.getStartTime();
			//System.out.println("touch "+ Gdx.input.justTouched());
			if(Gdx.input.justTouched() || (screen.getStartTime() > 0 && currentTime > (startTime + 1500))) {
				screen.setShowInstruction(false);
			}
		}
	}

}
