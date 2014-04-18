package id.ac.ui.edoocatia.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.LemariPerkakas;
import id.ac.ui.edoocatia.screen.MainMenuScreen;
import id.ac.ui.edoocatia.screen.Modul1Scene3Screen;

public class LemariPerkakasController {

	LemariPerkakas screen;
	Edoocatia app;
	private Rectangle[] imageBounds;
	private OrthographicCamera cam;
	private Rectangle viewport;

	public LemariPerkakasController(LemariPerkakas lemariPerkakas) {
		// TODO Auto-generated constructor stub
		this.screen = lemariPerkakas;
		app = lemariPerkakas.getApp();
		cam = lemariPerkakas.getCam();
		viewport = lemariPerkakas.getViewport();
		this.imageBounds = lemariPerkakas.getImageBounds();

	}

	public void processInput() {
		// TODO Auto-generated method stub

	}

}
