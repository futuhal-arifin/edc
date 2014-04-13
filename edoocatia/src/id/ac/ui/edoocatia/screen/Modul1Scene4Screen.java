package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.Modul1Scene4Controller;

public class Modul1Scene4Screen extends ProfessorInstructionScreen {
	private Modul1Scene4Controller controller;
	private Texture background;
	
	public Modul1Scene4Screen(Edoocatia app) {
		super(app);
		
		background = new Texture(Gdx.files.internal("data/images/modul-1/background/lemari.jpg"));
		this.setBackground("data/images/modul-1/background/tada.jpg");
		this.setDialogNaration("data/dialog/modul1/scene4.txt");
		this.setInstructionObject("data/images/modul-1/alat/besi.png");
		
		controller = new Modul1Scene4Controller(this);
	}
	
	
}
