package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.Modul1Scene1Controller;

public class Modul1Scene1Screen extends DialogScreen {
	
	private Modul1Scene1Controller controller;
	private Texture background;
	
	// music
	private Music sceneMusicBg;
	private Sound clickSfx;

	public Modul1Scene1Screen(Edoocatia app) {
		super(app);
		
		background = new Texture(Gdx.files.internal("data/images/modul-1/background/lab_pesawat.jpg"));
		
		this.setDialogNaration("data/dialog/modul1/scene1.txt");
		this.setDialogBackground("data/images/general/dialog.png");
		this.setDialogBackgroundPosition((this.getWidth()- this.dialogBackground.getWidth())/2, 40);
		this.setKarakterLeftPosition(0, 40);
		this.setKarakterRightPosition(width, 40);
		this.setLineLength(750);
		this.setTextPosition(300, this.dialogBackground.getHeight() - 50);
		this.setFont("data/font/kg-corner-of-the-sky-44-black.fnt", 
				"data/font/kg-corner-of-the-sky-44-black.png");
		
		this.controller = new Modul1Scene1Controller(this);
	}

	public void render(float delta) {
		cam.update();

		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
				(int) viewport.width, (int) viewport.height);
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
			
		batcher.draw(background, 0, 0);


		batcher.end();
		
		super.render(delta);
		controller.processInput();
	}

}
