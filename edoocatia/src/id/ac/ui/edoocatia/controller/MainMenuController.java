package id.ac.ui.edoocatia.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.MainMenuScreen;
import id.ac.ui.edoocatia.util.OverlapTester;
import id.ac.ui.edoocatia.util.ScreenEnum;

public class MainMenuController {
	
	MainMenuScreen screen;
	Edoocatia app;
	private Rectangle playButtonBounds;
	private Rectangle forumButtonBounds;
	private Rectangle sangJuaraButtonBounds;
	private Rectangle jelajahWebButtonBounds;
	private OrthographicCamera cam;
	private Rectangle viewport;

	public MainMenuController(MainMenuScreen screen) {
		this.screen = screen;
		app = screen.getApp();
		cam = screen.getCam();
		viewport = screen.getViewport();

		playButtonBounds = screen.getPlayButtonBounds();
		forumButtonBounds = screen.getForumButtonBounds();
		sangJuaraButtonBounds = screen.getSangJuaraButtonBounds();
		jelajahWebButtonBounds = screen.getJelajahWebButtonBounds();
	}

	public void processInput() {
		if(Gdx.input.justTouched()){
			
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			if(OverlapTester.pointInRectangle( playButtonBounds, pos.x, pos.y)){
				//screen.stopMusic();
				screen.playSoundFx();
				screen.setPlayButtonStatus(true);
			}
			else if(OverlapTester.pointInRectangle( forumButtonBounds, 
					pos.x, pos.y)){
				//screen.stopMusic();
				screen.playSoundFx();
				screen.setForumButtonStatus(true);
			}
			else if(OverlapTester.pointInRectangle( jelajahWebButtonBounds, 
					pos.x, pos.y)){
				//screen.stopMusic();
				screen.playSoundFx();
				screen.setJelajahWebButtonStatus(true);
			}
			else if(OverlapTester.pointInRectangle( sangJuaraButtonBounds, 
					pos.x, pos.y)){
				//screen.stopMusic();
				screen.playSoundFx();
				screen.setSangJuaraButtonStatus(true);
			}
		}
		
		if(Gdx.input.isTouched()){
			//kosongin dulu deh~
		}
		else{
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			if(screen.playButtonIsActive()){
				screen.setPlayButtonStatus(false);
				if(OverlapTester.pointInRectangle( playButtonBounds, pos.x, pos.y)){
					screen.stopMusic();
					app.getScreenManager().dispose(ScreenEnum.MAIN_MENU);
					app.getScreenManager().show(ScreenEnum.INTRO);
				}
			}
			else if(screen.forumButtonIsActive()){
				screen.setForumButtonStatus(false);
				if(OverlapTester.pointInRectangle( forumButtonBounds, pos.x, pos.y)){
					screen.stopMusic();
					app.screenMgr.dispose(ScreenEnum.MAIN_MENU);
					app.screenMgr.show(ScreenEnum.MODUL1_SCENE1);
				}
			}
			else if(screen.jelajahWebButtonIsActive()){
				screen.setJelajahWebButtonStatus(false);
				if(OverlapTester.pointInRectangle( jelajahWebButtonBounds, pos.x, pos.y)){
					
				}
			}
			else if(screen.sangJuaraButtonIsActive()){
				screen.setSangJuaraButtonStatus(false);
				if(OverlapTester.pointInRectangle( sangJuaraButtonBounds, pos.x, pos.y)){
					
				}
			}
			
		}
		
	}

}
