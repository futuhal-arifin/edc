package id.ac.ui.edoocatia.controller;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.IntroScreen1Modul2;
import id.ac.ui.edoocatia.util.ScreenEnum;

public class IntroScreen1Modul2Controller {

	private Edoocatia app;
	private IntroScreen1Modul2 screen;

	public IntroScreen1Modul2Controller(IntroScreen1Modul2 screen) {
		Gdx.input.setCatchBackKey(false);
		this.screen = screen;
		app = screen.getApp();
	}

	public void processInput() {
		// TODO Auto-generated method stub
		long currentTime = TimeUtils.millis();
		if (currentTime > (screen.getStartTime() + screen.getBgTime())) {
			if (screen.isIntroEnded()) {
				screen.stopMusic();
				app.changeScreen(ScreenEnum.INTRO_TO_MODUL2_SCREEN1B, ScreenEnum.MODUL2_SCENE1B);
			} else {
				screen.setStartTime(currentTime);
				screen.incrementCounter();
			}
		}

	}

}
