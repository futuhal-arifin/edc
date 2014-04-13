package id.ac.ui.edoocatia.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.Modul1Scene2Screen;
import id.ac.ui.edoocatia.util.OverlapTester;
import id.ac.ui.edoocatia.util.ScreenEnum;

public class Modul1Scene2Controller {
	
	private Edoocatia app;
	private Modul1Scene2Screen screen;
	private OrthographicCamera cam;
	private Rectangle viewport;
	private Rectangle[] spionBounds;
	private Rectangle[] sayapBounds;
	

	public Modul1Scene2Controller(Modul1Scene2Screen screen) {
		this.screen = screen;
		app = screen.getApp();
		cam = screen.getCam();
		viewport = screen.getViewport();
		this.spionBounds = screen.getSpionBounds();
		this.sayapBounds = screen.getSayapBounds();
	}

	public void processInput() {
		if(screen.isDialogEnded()) {
			long currentTime  = TimeUtils.millis();
			long startTime = screen.getStartTime();
			if(screen.getStartTime() > 0 && currentTime > (startTime + 1500)) {
				screen.setShowDialog(false);
			}
		}
		/*
		if(screen.getAPartHasSelected() && screen.isDialogEnded()) {
			long currentTime  = TimeUtils.millis();
			long startTime = screen.getStartTime();
			if(screen.getStartTime() > 0 && currentTime > (startTime + screen.getDialogTimeBreak())) {
				app.changeScreen(ScreenEnum.MODUL1_SCENE2, ScreenEnum.MAIN_MENU);
			}
		}
		*/
		if(!screen.isShowDialog()) {
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			if(Gdx.input.justTouched()){
				for(int i=0; i<spionBounds.length; i++){
					if(OverlapTester.pointInRectangle( spionBounds[i], pos.x, pos.y)){
						screen.setPartStatus(true, screen.SPION);
					}
				}
				for(int i=0; i<sayapBounds.length; i++){
					if(OverlapTester.pointInRectangle( sayapBounds[i], pos.x, pos.y)){
						screen.setPartStatus(true, screen.SAYAP);
					}
				}
			}
			
			if(Gdx.input.isTouched()){
				//kosongin dulu deh~
			}
			else{
				if(screen.partIsSelected(screen.SPION)){
					screen.setPartStatus(false, screen.SPION);
					for(int i=0; i<spionBounds.length; i++){
						if(OverlapTester.pointInRectangle( spionBounds[i], pos.x, pos.y)){
							//screen.setAPartHasSelected(true);
							//screen.showInfoSubScene();
							app.changeScreen(ScreenEnum.MODUL1_SCENE2, ScreenEnum.MODUL1_SCENE5);
							/*screen.stopMusic();
							//app.getScreen().dispose();
							app.setScreen(new PilihSubCeritaScreen(app, 
									app.getCeritaNusantara().getCerita(CeritaNusantara.JAWA)));
							*/
						}
					}
				}
				
				if(screen.partIsSelected(screen.SAYAP)){
					screen.setPartStatus(false, screen.SAYAP);
					for(int i=0; i<sayapBounds.length; i++){
						if(OverlapTester.pointInRectangle( sayapBounds[i], pos.x, pos.y)){
							app.changeScreen(ScreenEnum.MODUL1_SCENE2, ScreenEnum.MODUL1_SCENE3);
							//screen.setAPartHasSelected(true);
							//screen.showInfoSubScene();
							/*screen.stopMusic();
							//app.getScreen().dispose();
							app.setScreen(new PilihSubCeritaScreen(app, 
									app.getCeritaNusantara().getCerita(CeritaNusantara.JAWA)));
							*/
						}
					}
				}
				
			}
		}
		
	}

}