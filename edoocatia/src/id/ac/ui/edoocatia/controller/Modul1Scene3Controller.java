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
	private Rectangle[] buttonBounds;
	private Rectangle viewport;
	private OrthographicCamera cam;
	private int mistakes = 0;

	public Modul1Scene3Controller(Modul1Scene3Screen screen) {
		// TODO Auto-generated constructor stub
		this.screen = screen;
		app = screen.getApp();
		cam = screen.getCam();
		viewport = screen.getViewport();
		this.buttonBounds = screen.getImageBounds();

	}

	public void processInput() {
		// TODO Auto-generated method stub

		if (!screen.getShowInstruction()) {

			if (Gdx.input.justTouched()) {
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width,
						viewport.height);

				for (int i = 0; i < buttonBounds.length; i++) {
					if (OverlapTester.pointInRectangle(buttonBounds[i], pos.x,
							pos.y)) {
						screen.setImageStatus(true, i);

						if (i != 0) {
							screen.setMistakes(mistakes++);
						}

					}
				}

			}

			if (screen.getMistakes() > 2) {
				app.changeScreen(ScreenEnum.MODUL1_SCENE3,
						ScreenEnum.MODUL1_SCENE2);
			}

		}
	}

}
