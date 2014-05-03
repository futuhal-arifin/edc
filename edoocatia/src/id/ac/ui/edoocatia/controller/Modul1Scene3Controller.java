package id.ac.ui.edoocatia.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.Modul1Scene3Screen;
import id.ac.ui.edoocatia.util.OverlapTester;
import id.ac.ui.edoocatia.util.ScreenEnum;

public class Modul1Scene3Controller {

	private Edoocatia app;
	private Modul1Scene3Screen screen;
	private Rectangle[] imageBounds;
	private Rectangle viewport;
	private OrthographicCamera cam;
	private int mistakes = 0;

	public Modul1Scene3Controller(Modul1Scene3Screen screen) {
		this.screen = screen;
		app = screen.getApp();
		cam = screen.getCam();
		viewport = screen.getViewport();
	}

	public void processInput() {
		short currentState = screen.getState();
		if (screen.getState() == screen.LEMARI_PERKAKAS) {

			if (Gdx.input.justTouched() && screen.getJustSelectedItem() < 0) {
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width,
						viewport.height);
				this.imageBounds = screen.getItem().getImageBounds();
				for (int i = 0; i < imageBounds.length; i++) {
					if (!screen.getItem().imageIsActive()[i]) {
						if (OverlapTester.pointInRectangle(imageBounds[i],
								pos.x, pos.y)) {
							screen.getItem().setImageStatus(true, i);
							screen.setJustSelectedItem((short) i);

							if (i == screen.getCorrectItem()) {
								screen.setJustAnsweredCorrectly(true);
								app.getEdocatiaData().setScore(
										app.getEdocatiaData().getScore() + 100);
							} else {
								screen.setMistakes(++mistakes);
								screen.setJustAnsweredWrong(true);
								app.getEdocatiaData().setScore(
										app.getEdocatiaData().getScore() - 20);
							}
						}
					}
					// screen.setImageStatus(true, screen.besi);
				}

			}

			// cek timer
			if (screen.isJustAnsweredCorrectly()
					&& screen.getPlayerStateTime() > screen.WIN_OR_LOSE_DELAY) {
				app.getEdocatiaData().setModul1Scene3Done(true);
				screen.setState(screen.PROF_INFO);
			}

			if (screen.isJustAnsweredWrong()
					&& screen.getPlayerStateTime() > screen.WIN_OR_LOSE_DELAY
					&& screen.getMistakes() == 3) {
				screen.setState(screen.PROF_INFO_WRONG);
			}

		} else if (currentState == screen.PROF_INFO) {
			if (!screen.getShowInstruction()) {
				app.changeScreen(ScreenEnum.MODUL1_SCENE3,
						ScreenEnum.MODUL1_SCENE4);
			}

		} else if (currentState == screen.PROF_INFO_WRONG) {

			if (!screen.getShowInstruction()) {
				app.changeScreen(ScreenEnum.MODUL1_SCENE3,
						ScreenEnum.MODUL1_SCENE2);
			}
		} else if (currentState == screen.PROF_INFO_SAYAP) {

			if (!screen.getShowInstruction()) {
				screen.setState(screen.LEMARI_PERKAKAS);
			}
		}
	}

}
