package id.ac.ui.edoocatia.controller;

import java.sql.Time;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.IntroScreen;

public class IntroController {

	private Edoocatia app;
	private IntroScreen screen;
	private OrthographicCamera cam;
	private Rectangle viewport;
	
	public IntroController(IntroScreen screen) {
		Gdx.input.setCatchBackKey(false);
		this.screen = screen;
		app = screen.getAplikasi();
		cam = screen.getCam();
		viewport = screen.getViewport();
	}

	public void processInput() {
		// TODO Auto-generated method stub
		long currentTime;
		if((currentTime = TimeUtils.millis()) > (screen.getStartTime() + screen.getBgTime())) {
			screen.setStartTime(currentTime);
			screen.incrementCounter();
		}
		
		
	}

}
