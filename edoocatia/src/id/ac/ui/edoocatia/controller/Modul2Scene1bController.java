package id.ac.ui.edoocatia.controller;

import com.badlogic.gdx.utils.TimeUtils;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.DialogScreen;
import id.ac.ui.edoocatia.screen.Modul1Scene1Screen;
import id.ac.ui.edoocatia.screen.Modul2Scene1bScreen;
import id.ac.ui.edoocatia.util.ScreenEnum;

public class Modul2Scene1bController {
	private Edoocatia app;
	private DialogScreen screen;

	public Modul2Scene1bController(Modul2Scene1bScreen screen) {
		this.screen = screen;
		app = screen.getApp();
	}

	public void processInput() {
		if (screen.isDialogEnded()) {
			long currentTime = TimeUtils.millis();
			long startTime = screen.getStartTime();
			if (screen.getStartTime() > 0
					&& currentTime > (startTime + screen.getDialogTimeBreak())) {
				//app.changeScreen(ScreenEnum.MODUL2_SCENE1B,ScreenEnum.MODUL2_SCENE1);
			}
		}
	}

}
