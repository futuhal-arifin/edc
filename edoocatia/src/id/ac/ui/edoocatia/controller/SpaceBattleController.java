package id.ac.ui.edoocatia.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import id.ac.ui.edoocatia.model.SpaceBattle;
import id.ac.ui.edoocatia.screen.SpaceBattleScreen;
import id.ac.ui.edoocatia.util.OverlapTester;

public class SpaceBattleController {
	
	SpaceBattleScreen screen;
	SpaceBattle data;
	private OrthographicCamera cam;
	private Rectangle viewport;
	private Rectangle[] buttonBounds;

	public SpaceBattleController(SpaceBattleScreen screen) {
		this.screen = screen;
		this.cam = screen.getCam();
		this.viewport = screen.getViewport();
		this.data = screen.getSpaceBattleData();
		this.buttonBounds = screen.getButtonBounds();
	}

	public void processInput() {
		if (screen.isBattleEnded()) {
			
		}
		else {
			if (data.getCurrentDistance() >= data.getMaxDistance()) {
				screen.setBattleEnded(true);
			}
			//if(data.isAlienComing()) {
				
			//} else {
				if(Gdx.input.justTouched()){
					Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
					cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
					
					if(OverlapTester.pointInRectangle( buttonBounds[screen.LEFT], pos.x, pos.y)){
						screen.setButtonStatus(true, screen.LEFT);
					}
					else if(OverlapTester.pointInRectangle( buttonBounds[screen.RIGHT], pos.x, pos.y)){
						screen.setButtonStatus(true, screen.RIGHT);
					}
				}
				
				if(Gdx.input.isTouched()){
					Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
					cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
					if(screen.buttonIsActive(screen.LEFT)){
						//screen.setLeftButtonPressed(false);
						if(OverlapTester.pointInRectangle( buttonBounds[screen.LEFT], pos.x, pos.y)){
								
							float currentPosition = data.getPesawatXPosition();
							if(currentPosition >= data.getLeftLimit()-5.0f) {
								data.setPesawatXPosition(currentPosition-5.0f);
							} else {
								data.setPesawatXPosition(data.getLeftLimit());
							}
						}
					}
					else if(screen.buttonIsActive(screen.RIGHT)){
						
						if(OverlapTester.pointInRectangle( buttonBounds[screen.RIGHT], pos.x, pos.y)){
							float currentPosition = data.getPesawatXPosition();
							if(currentPosition <= (data.getRightLimit()+5.0f)) {
								data.setPesawatXPosition(currentPosition+5.0f);
							} else {
								data.setPesawatXPosition(data.getRightLimit());
							}
						}
					}
				} else {
					if(screen.buttonIsActive(screen.LEFT)){ 
						screen.setButtonStatus(false, screen.LEFT);
					} else if(screen.buttonIsActive(screen.RIGHT)){
						screen.setButtonStatus(false, screen.RIGHT);
					}
				}
			//}
			
		}
	}

}
