package id.ac.ui.edoocatia.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.Modul1Scene7Screen;
import id.ac.ui.edoocatia.util.OverlapTester;
import id.ac.ui.edoocatia.util.ScreenEnum;

public class Modul1Scene7Controller {
	
	private Edoocatia app;
	private Modul1Scene7Screen screen;
	private OrthographicCamera cam;
	private Rectangle viewport;
	private Rectangle playerBounds;
	private float touchPointX;
	private float touchPointY;

	public Modul1Scene7Controller(Modul1Scene7Screen screen) {
		this.screen = screen;
		app = screen.getApp();
		cam = screen.getCam();
		viewport = screen.getViewport();
		touchPointX = -1;
		touchPointY = -1;
		this.playerBounds = screen.getPlayerBounds();
	}

	public void processInput() {
		
		if(screen.getStates() == screen.READY_TO_ENTER) {
			if(OverlapTester.overlapRectangles(screen.getPlayerCenterBounds(), screen.getPesawatCenterBounds())) {
				screen.setStates(screen.READY_TO_FLY);
			} else 
				if(Gdx.input.isTouched()) {
				//System.out.println("istouched");
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				if(OverlapTester.pointInRectangle( playerBounds, pos.x, pos.y)){
					if(this.touchPointX > 0) {
						
						float tempX = screen.getPlayerXPosition() + (pos.x - this.touchPointX);
						screen.setPlayerXPosition(tempX);
					}
					
					if(this.touchPointY > 0) {
						
						float tempY = screen.getPlayerYPosition() + (pos.y - this.touchPointY);
						screen.setPlayerYPosition(tempY);
					}
					this.touchPointX = pos.x;
					this.touchPointY = pos.y;
				}
			}
			
		} else if(screen.getStates() == screen.READY_TO_FLY) {
			long currentTime  = TimeUtils.millis();
			long[] lightStartTime = screen.getLightStartTime();
			// central light
			if(!screen.isLightOn(screen.CENTRAL_LIGHT)) {
				if(lightStartTime[screen.CENTRAL_LIGHT] > 0 && currentTime > (lightStartTime[screen.CENTRAL_LIGHT] + screen.CENTRAL_DIM_DELAY)) {
					screen.setLightOn(true, screen.CENTRAL_LIGHT);
				}
			}
			
			// monitor
			if(screen.getCurrentMonitorFlickerCount() == 0) {
				if(lightStartTime[screen.MONITOR_LIGHT] > 0 && currentTime > (lightStartTime[screen.MONITOR_LIGHT] + screen.MONITOR_DIM_DELAY)) {
					screen.setLightOn(true, screen.MONITOR_LIGHT);
					screen.incrementCurrentMonitorFlickerCount();
					screen.setLightStartTime(currentTime, screen.MONITOR_LIGHT);
				}
			} else if (screen.getCurrentMonitorFlickerCount() <= screen.MONITOR_FLICKER_COUNT){
				if(lightStartTime[screen.MONITOR_LIGHT] > 0 && currentTime > (lightStartTime[screen.MONITOR_LIGHT] + screen.MONITOR_FLICKER_DELAY)) {
					screen.setLightOn(!screen.isLightOn(screen.MONITOR_LIGHT), screen.MONITOR_LIGHT);
					screen.incrementCurrentMonitorFlickerCount();
					screen.setLightStartTime(currentTime, screen.MONITOR_LIGHT);
				}
			} else {
				if(lightStartTime[screen.MONITOR_LIGHT] > 0 && currentTime > (lightStartTime[screen.MONITOR_LIGHT] + 1000)) {
					screen.setStates(screen.FLYING);
				}
			}
			
		} else {
			if(screen.getPesawatSideXPosition() > screen.VIRTUAL_WIDTH + 100) {
				app.changeScreen(ScreenEnum.MODUL1_SCENE7, ScreenEnum.MAIN_MENU);
			}
		}
	}

}
