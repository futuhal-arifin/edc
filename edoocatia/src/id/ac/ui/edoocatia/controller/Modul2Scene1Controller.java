package id.ac.ui.edoocatia.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.Modul2Scene1Screen;
import id.ac.ui.edoocatia.util.OverlapTester;
import id.ac.ui.edoocatia.util.ScreenEnum;

public class Modul2Scene1Controller {

	Modul2Scene1Screen screen;
	Edoocatia app;
	private Rectangle[] buttonBounds;

	private OrthographicCamera cam;
	private Rectangle viewport;

	public Modul2Scene1Controller(Modul2Scene1Screen screen) {
		this.screen = screen;
		app = screen.getApp();
		cam = screen.getCam();
		viewport = screen.getViewport();
		this.buttonBounds = screen.getButtonBounds();
		this.app.getEdocatiaData().setScore(0);
	}

	public void processInput() {
		if (Gdx.input.justTouched()) {

			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width,
					viewport.height);

			if (OverlapTester.pointInRectangle(buttonBounds[screen.bimasakti],
					pos.x, pos.y)) {
				// screen.stopMusic();
				//screen.playSoundFx();
				screen.setButtonStatus(true, screen.bimasakti);
			}
		}

		if (Gdx.input.isTouched()) {
			// kosongin dulu deh~
		} else {
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width,
					viewport.height);

			if (screen.buttonIsActive(screen.bimasakti)) {
				screen.setButtonStatus(false, screen.bimasakti);
				if (OverlapTester.pointInRectangle(
						buttonBounds[screen.bimasakti], pos.x, pos.y)) {
					screen.stopMusic();
					app.changeScreen(ScreenEnum.MODUL2_SCENE1, ScreenEnum.INTRO_TO_MODUL2_SCREEN1B);
				}
			}
		}

	}

}
