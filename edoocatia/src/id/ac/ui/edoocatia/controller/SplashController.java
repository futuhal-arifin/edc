package id.ac.ui.edoocatia.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.SplashScreen;
import id.ac.ui.edoocatia.util.ScreenEnum;
import id.ac.ui.edoocatia.util.ScreenManager;

public class SplashController {
	SplashScreen screen;
	Edoocatia app;
	public SplashController(SplashScreen screen) {
		this.screen = screen;
		app = screen.getApp();
	}
	
	public void processInput() {
		if(Gdx.input.justTouched()) {
			ScreenManager manager = app.screenMgr;
			manager.dispose(ScreenEnum.SPLASH);
			app.getScreenManager().show(ScreenEnum.MAIN_MENU);
		}
		
		
		
		if(TimeUtils.millis() > screen.getStartTime() + screen.getSplashTime()) {
			app.getScreenManager().dispose(ScreenEnum.SPLASH);
			app.getScreenManager().show(ScreenEnum.MAIN_MENU);
		}
            //myGame.setScreen(new GameScreen());
	}
}
