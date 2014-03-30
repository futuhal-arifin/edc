package id.ac.ui.edoocatia.controller;

import java.sql.Time;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.IntroScreen;
import id.ac.ui.edoocatia.util.ScreenEnum;

public class IntroController {

	private Edoocatia app;
	private IntroScreen screen;
	//private OrthographicCamera cam;
	//private Rectangle viewport;
	
	public IntroController(IntroScreen screen) {
		Gdx.input.setCatchBackKey(false);
		this.screen = screen;
		app = screen.getApp();
		//cam = screen.getCam();
		//viewport = screen.getViewport();
	}

	public void processInput() {
		// TODO Auto-generated method stub
		long currentTime  = TimeUtils.millis();
		if(currentTime > (screen.getStartTime() + screen.getBgTime())) {
			if(screen.isIntroEnded()) {
				screen.stopMusic();
				app.getScreenManager().dispose(ScreenEnum.INTRO);
				app.getScreenManager().show(ScreenEnum.MAIN_MENU);
			}
			else {
				screen.setStartTime(currentTime);
				screen.incrementCounter();
			}
		}		
	}
}