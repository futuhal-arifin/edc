package id.ac.ui.edoocatia.controller;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.LoadingMainScreen;
import id.ac.ui.edoocatia.util.ScreenEnum;

public class LoadingMainController {
	LoadingMainScreen screen;
	Edoocatia app;

	public LoadingMainController(LoadingMainScreen screen) {
		this.screen = screen;
		app = screen.getApp();
	}

	public void processInput() {
		app.changeScreen(ScreenEnum.LOADING_MENU_ASSETS,
				ScreenEnum.MAIN_MENU);
	}
}
