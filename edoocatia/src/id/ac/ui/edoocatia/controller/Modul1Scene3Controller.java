package id.ac.ui.edoocatia.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.MainMenuScreen;
import id.ac.ui.edoocatia.screen.Modul1Scene3Screen;

public class Modul1Scene3Controller {

	Modul1Scene3Screen screen;
	Edoocatia app;
	private Rectangle[] imageBounds;
	private OrthographicCamera cam;
	private Rectangle viewport;

	public Modul1Scene3Controller(Modul1Scene3Screen screen) {
		// TODO Auto-generated constructor stub
		this.screen = screen;
		app = screen.getApp();
		cam = screen.getCam();
		viewport = screen.getViewport();
		this.imageBounds = screen.getImageBounds();
		
	}

	public void processInput() {
		// TODO Auto-generated method stub

		
		
	}

}
