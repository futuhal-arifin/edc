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
	private Rectangle[] buttonBounds;

	private OrthographicCamera cam;
	private Rectangle viewport;

	public MainMenuController(MainMenuScreen screen) {
		this.screen = screen;
		app = screen.getApp();
		cam = screen.getCam();
		viewport = screen.getViewport();
		this.buttonBounds = screen.getButtonBounds();
	}

	public void processInput() {
		if (Gdx.input.justTouched()) {

			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width,
					viewport.height);

			if (OverlapTester.pointInRectangle(buttonBounds[screen.PLAY],
					pos.x, pos.y)) {
				// screen.stopMusic();
				screen.playSoundFx();
				screen.setButtonStatus(true, screen.PLAY);
			} else if (OverlapTester.pointInRectangle(
					buttonBounds[screen.FORUM], pos.x, pos.y)) {
				// screen.stopMusic();
				screen.playSoundFx();
				screen.setButtonStatus(true, screen.FORUM);
			} else if (OverlapTester.pointInRectangle(
					buttonBounds[screen.JELAJAH_WEB], pos.x, pos.y)) {
				// screen.stopMusic();
				screen.playSoundFx();
				screen.setButtonStatus(true, screen.JELAJAH_WEB);
			} else if (OverlapTester.pointInRectangle(
					buttonBounds[screen.SANG_JUARA], pos.x, pos.y)) {
				// screen.stopMusic();
				screen.playSoundFx();
				screen.setButtonStatus(true, screen.SANG_JUARA);
			}
		}

		if (Gdx.input.isTouched()) {
			// kosongin dulu deh~
		} else {
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width,
					viewport.height);

			if (screen.buttonIsActive(screen.PLAY)) {
				screen.setButtonStatus(false, screen.PLAY);
				if (OverlapTester.pointInRectangle(buttonBounds[screen.PLAY],
						pos.x, pos.y)) {
					screen.stopMusic();
					app.changeScreen(ScreenEnum.MAIN_MENU, ScreenEnum.INTRO);
				}
			} else if (screen.buttonIsActive(screen.FORUM)) {
				screen.setButtonStatus(false, screen.FORUM);
				if (OverlapTester.pointInRectangle(buttonBounds[screen.FORUM],
						pos.x, pos.y)) {
					screen.stopMusic();
					app.changeScreen(ScreenEnum.MAIN_MENU,
							ScreenEnum.MODUL1_SCENE1);
				}
			} else if (screen.buttonIsActive(screen.JELAJAH_WEB)) {
				screen.setButtonStatus(false, screen.JELAJAH_WEB);
				if (OverlapTester.pointInRectangle(
						buttonBounds[screen.JELAJAH_WEB], pos.x, pos.y)) {
					screen.stopMusic();
					app.changeScreen(ScreenEnum.MAIN_MENU,
							ScreenEnum.MODUL1_SCENE7);
				}
			} else if (screen.buttonIsActive(screen.SANG_JUARA)) {
				screen.setButtonStatus(false, screen.SANG_JUARA);
				if (OverlapTester.pointInRectangle(
						buttonBounds[screen.SANG_JUARA], pos.x, pos.y)) {
					// screen.stopMusic();
				}
			}

		}

	}

}
