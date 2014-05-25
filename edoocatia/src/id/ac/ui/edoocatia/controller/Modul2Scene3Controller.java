package id.ac.ui.edoocatia.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

import id.ac.ui.edoocatia.screen.Modul2Scene3Screen;
import id.ac.ui.edoocatia.util.ScreenEnum;

public class Modul2Scene3Controller {
	
	private Modul2Scene3Screen screen;
	
	private Rectangle viewport;
	private OrthographicCamera cam;
	
	public Modul2Scene3Controller(Modul2Scene3Screen screen) {
		this.screen = screen;

		cam = screen.getCam();
		viewport = screen.getViewport();
	}

	public void processInput() {
		if(screen.isArrived()) {
			screen.getApp().changeScreen(ScreenEnum.MAIN_MENU, ScreenEnum.MODUL2_SCENE3);
		}
	}

}
