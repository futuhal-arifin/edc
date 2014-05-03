package id.ac.ui.edoocatia.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.ChooseCharacterScreen;
import id.ac.ui.edoocatia.util.OverlapTester;
import id.ac.ui.edoocatia.util.ScreenEnum;

public class ChooseCharacterController {

	ChooseCharacterScreen screen;
	Edoocatia app;
	private Rectangle[] characterBounds;
	private OrthographicCamera cam;
	private Rectangle viewport;
	
	public ChooseCharacterController(ChooseCharacterScreen screen) {
		this.screen = screen;
		app = screen.getApp();
		cam = screen.getCam();
		viewport = screen.getViewport();
		this.characterBounds = screen.getCharacterBounds();
	}

	public void processInput() {
if(Gdx.input.justTouched()){
			
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			if(OverlapTester.pointInRectangle( characterBounds[screen.ALTA], pos.x, pos.y)){
				//screen.stopMusic();
				screen.playSoundFx();
				screen.setCharacterIsChosen(true, screen.ALTA);
			}
			else if(OverlapTester.pointInRectangle( characterBounds[screen.MOMO], 
					pos.x, pos.y)){
				//screen.stopMusic();
				screen.playSoundFx();
				screen.setCharacterIsChosen(true, screen.MOMO);
			}
			else if(OverlapTester.pointInRectangle( characterBounds[screen.AZMO], 
					pos.x, pos.y)){
				//screen.stopMusic();
				screen.playSoundFx();
				screen.setCharacterIsChosen(true, screen.AZMO);
			}
			else if(OverlapTester.pointInRectangle( characterBounds[screen.TSARINA], 
					pos.x, pos.y)){
				//screen.stopMusic();
				screen.playSoundFx();
				screen.setCharacterIsChosen(true, screen.TSARINA);
			}
		}
		
		if(Gdx.input.isTouched()){
			//kosongin dulu deh~
		}
		else{
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			if(screen.characterIsChosen(screen.ALTA)){
				screen.setCharacterIsChosen(false, screen.ALTA);
				if(OverlapTester.pointInRectangle( characterBounds[screen.ALTA], pos.x, pos.y)){
					app.getEdocatiaData().setPlayer("alta");
					app.changeScreen(ScreenEnum.CHOOSECHAR, ScreenEnum.MODUL1_SCENE1);
				}
			}
			else if(screen.characterIsChosen(screen.MOMO)){
				screen.setCharacterIsChosen(false, screen.MOMO);
				if(OverlapTester.pointInRectangle( characterBounds[screen.MOMO], pos.x, pos.y)){
					app.getEdocatiaData().setPlayer("momo");
					app.changeScreen(ScreenEnum.CHOOSECHAR, ScreenEnum.MODUL1_SCENE1);
				}
			}
			else if(screen.characterIsChosen(screen.AZMO)){
				screen.setCharacterIsChosen(false, screen.AZMO);
				if(OverlapTester.pointInRectangle( characterBounds[screen.AZMO], pos.x, pos.y)){
					app.getEdocatiaData().setPlayer("azmo");
					app.changeScreen(ScreenEnum.CHOOSECHAR, ScreenEnum.MODUL1_SCENE1);
				}
			}
			else if(screen.characterIsChosen(screen.TSARINA)){
				screen.setCharacterIsChosen(false, screen.TSARINA);
				if(OverlapTester.pointInRectangle( characterBounds[screen.TSARINA], pos.x, pos.y)){
					app.getEdocatiaData().setPlayer("tsarina");
					app.changeScreen(ScreenEnum.CHOOSECHAR, ScreenEnum.MODUL1_SCENE1);
				}
			}
			
			
			
		}
	}

}
