package id.ac.ui.edoocatia.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.Modul2Scene1Screen;
import id.ac.ui.edoocatia.screen.Modul2Scene9Screen;
import id.ac.ui.edoocatia.util.OverlapTester;
import id.ac.ui.edoocatia.util.ScreenEnum;

public class Modul2Scene9Controller {

	Modul2Scene9Screen screen;
	Edoocatia app;
	private Rectangle[] buttonBounds;
	public short BUMI = 0;
	private OrthographicCamera cam;
	private Rectangle viewport;

	public Modul2Scene9Controller(Modul2Scene9Screen screen) {
		this.screen = screen;
		app = screen.getApp();
		cam = screen.getCam();
		viewport = screen.getViewport();

	}

	public void processInput() {
		short currentState = screen.getState();

		if (currentState == screen.BUMI) {
			if (!screen.getShowInstruction()) {
				screen.setState(screen.PROF_INFO);
			}

		} else {

		}
	}

}
