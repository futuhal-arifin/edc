package id.ac.ui.edoocatia.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.Modul1Scene4Screen;
import id.ac.ui.edoocatia.util.OverlapTester;
import id.ac.ui.edoocatia.util.ScreenEnum;

public class Modul1Scene4Controller {
	private Edoocatia app;
	private Modul1Scene4Screen screen;
	private Rectangle[] imageBounds;
	private Rectangle viewport;
	private OrthographicCamera cam;

	public Modul1Scene4Controller(Modul1Scene4Screen screen) {
		this.screen = screen;
		app = screen.getApp();
		cam = screen.getCam();
		viewport = screen.getViewport();
		app.sfxPlayer.setSoundFxModul1();
		app.sfxPlayer.setPukulPaluSoundFx();
	}

	public void processInput() {
		short currentState = screen.getState();
		if(currentState == screen.PROF_INSTRUCTION) {
			if(!screen.getShowInstruction()) {
				screen.setState(screen.LEMARI_PERKAKAS);
			}
		} else if (screen.getState() == screen.LEMARI_PERKAKAS) {

			if (Gdx.input.justTouched() && screen.getJustSelectedItem() < 0) {
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width,
						viewport.height);
				this.imageBounds = screen.getItem().getImageBounds();
				for (int i = 0; i < imageBounds.length; i++) {
					if(!screen.getItem().imageIsActive()[i]) {
						if (OverlapTester.pointInRectangle(imageBounds[i], pos.x,
							pos.y)) {
							screen.getItem().setImageStatus(true, i);
							screen.setJustSelectedItem((short) i);
							if(i == screen.getCorrectItem()) {
								screen.setJustAnsweredCorrectly(true);
								app.getEdocatiaData().setScore(app.getEdocatiaData().getScore() + 100);
								app.sfxPlayer.playModul1RightSoundFx();
							} else {
								screen.setJustAnsweredWrong(true);
								app.getEdocatiaData().setScore(app.getEdocatiaData().getScore() - 20);
								app.sfxPlayer.playModul1WrongSoundFx();
							}
						}
					}
				}
			}
			// ganti adegan
			if(screen.isJustAnsweredCorrectly() && screen.getPlayerStateTime() > screen.WIN_DELAY) {
				app.getEdocatiaData().setModul1Scene3Done(true);
				app.getEdocatiaData().setModul1Scene4Done(true);
				app.sfxPlayer.disposeModul1SoundFx();
				screen.setState(screen.PROF_INFO);
			}

		} else if(currentState == screen.PROF_INFO) {
			if(!screen.getShowInstruction()) {
				screen.setState(screen.PLAYER_MEMALU);
			}
		} else if(currentState == screen.PLAYER_MEMALU) {
			
			if(screen.getMemaluStateTime() > 3) {
				app.sfxPlayer.disposePukulPaluSoundFx();
				app.changeScreen(ScreenEnum.MODUL1_SCENE4, ScreenEnum.MODUL1_SCENE2);
			}
		}
	}
	
}
