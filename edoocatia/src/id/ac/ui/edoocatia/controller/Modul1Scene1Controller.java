package id.ac.ui.edoocatia.controller;

import com.badlogic.gdx.utils.TimeUtils;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.DialogScreen;
import id.ac.ui.edoocatia.screen.Modul1Scene1Screen;
import id.ac.ui.edoocatia.util.ScreenEnum;

public class Modul1Scene1Controller {
	private Edoocatia app;
	private DialogScreen screen;

	public Modul1Scene1Controller(Modul1Scene1Screen screen) {
		this.screen = screen;
		app = screen.getApp();
	}

	public void processInput() {
		if (screen.isDialogEnded()) {
			long currentTime = TimeUtils.millis();
			long startTime = screen.getStartTime();
			if (screen.getStartTime() > 0
					&& currentTime > (startTime + screen.getDialogTimeBreak())) {
				app.changeScreen(ScreenEnum.MODUL1_SCENE1,
						ScreenEnum.MODUL1_SCENE2);
			}
		}
	}

}
