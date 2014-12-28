package id.ac.ui.edoocatia.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.SplashScreen;
import id.ac.ui.edoocatia.util.ScreenEnum;

public class SplashController {
	SplashScreen screen;
	Edoocatia app;

	public SplashController(SplashScreen screen) {
		this.screen = screen;
		app = screen.getApp();
	}

	public void processInput() {
		if (Gdx.input.isTouched()
				|| TimeUtils.millis() > screen.getStartTime()
						+ screen.getSplashTime()) {
			app.changeScreen(ScreenEnum.SPLASH, ScreenEnum.LOADING_MENU_ASSETS);
		}
	}
}
