package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.Modul1Scene1Controller;

public class Modul1Scene1Screen extends DialogScreen {

	private Modul1Scene1Controller controller;
	private Texture background;

	public Modul1Scene1Screen(Edoocatia app) {
		super(app);

		// assetManager.load("data/images/modul-1/background/lab_pesawat.jpg",
		// Texture.class);
		// assetManager.load("data/images/general/dialog.png", Texture.class);

		this.setDialogNaration("data/dialog/modul1/scene1.txt");

		this.setDialogBackgroundPosition(
				(VIRTUAL_WIDTH - this.dialogBackground.getWidth()) / 2, 40);

		this.setKarakterLeftPosition(0, 40);
		this.setKarakterRightPosition(VIRTUAL_WIDTH, 40);
		this.setLineLength(750);
		this.setTextPosition(300, this.dialogBackground.getHeight() - 50);
		this.setFont("data/font/kg-corner-of-the-sky-44-black.fnt",
				"data/font/kg-corner-of-the-sky-44-black.png");

		this.setMusicBg("data/sounds/music/modul1/scene1theme.mp3");

		this.controller = new Modul1Scene1Controller(this);
	}

	public void render(float delta) {
		background = assets
				.get("data/images/modul-1/background/lab_pesawat.jpg",
						Texture.class);

		this.setDialogBackground(assets.get("data/images/general/dialog.png",
				Texture.class));

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

	@Override
	public void dispose() {
		this.background.dispose();
		super.dispose();
	}

}
