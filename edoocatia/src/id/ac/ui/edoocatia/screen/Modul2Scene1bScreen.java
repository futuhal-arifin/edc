package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.Modul2Scene1bController;

public class Modul2Scene1bScreen extends DialogScreen {

	private Modul2Scene1bController controller;
	private Texture background;

	private Texture buttonTexture[] = new Texture[7];
	private Texture buttonActiveTexture[] = new Texture[7];
	private Rectangle buttonBounds[] = new Rectangle[7];
	private boolean buttonIsActive[] = new boolean[7];

	// konstanta biar kita gausah ngafalin indeksnya
	public final int SUN = 0;
	public final int EARTH = 1;
	public final int JUPITER = 2;
	public final int MARS = 3;
	public final int NEPTUNUS = 4;
	public final int SATURNUS = 5;
	public final int URANUS = 6;

	public Modul2Scene1bScreen(Edoocatia app) {
		super(app);

		background = new Texture(
				Gdx.files.internal("data/images/modul-2/space.jpg"));

		buttonTexture[SUN] = new Texture(
				Gdx.files.internal("data/images/modul-2/sun.png"));

		buttonTexture[EARTH] = new Texture(
				Gdx.files.internal("data/images/modul-2/earth.png"));

		buttonTexture[JUPITER] = new Texture(
				Gdx.files.internal("data/images/modul-2/jupiter.png"));

		buttonTexture[MARS] = new Texture(
				Gdx.files.internal("data/images/modul-2/mars.png"));

		buttonTexture[NEPTUNUS] = new Texture(
				Gdx.files.internal("data/images/modul-2/neptunus.png"));

		buttonTexture[SATURNUS] = new Texture(
				Gdx.files.internal("data/images/modul-2/saturnus.png"));
		
		buttonTexture[URANUS] = new Texture(
				Gdx.files.internal("data/images/modul-2/uranus.png"));

		// batas2 button
		buttonBounds[SUN] = new Rectangle(width/4, (height/4)+100,
				buttonTexture[SUN].getWidth(), buttonTexture[SUN].getHeight());

		buttonBounds[EARTH] = new Rectangle((width/2)-200, (height/2)-400,
				buttonTexture[EARTH].getWidth(), buttonTexture[EARTH].getHeight());
		
		buttonBounds[JUPITER] = new Rectangle((width/2)-800, (height/2)-100,
				buttonTexture[JUPITER].getWidth(), buttonTexture[JUPITER].getHeight());
		
		buttonBounds[MARS] = new Rectangle((width/2)+100, (height/2),
				buttonTexture[MARS].getWidth(), buttonTexture[MARS].getHeight());
		
		buttonBounds[NEPTUNUS] = new Rectangle((width/2)+200, (height/2)-400,
				buttonTexture[NEPTUNUS].getWidth(), buttonTexture[NEPTUNUS].getHeight());
		
		buttonBounds[SATURNUS] = new Rectangle((width/2)-700, (height/2)-400,
				buttonTexture[SATURNUS].getWidth(), buttonTexture[SATURNUS].getHeight());
		
		buttonBounds[URANUS] = new Rectangle((width/2)-700, (height/2)-400,
				buttonTexture[URANUS].getWidth(), buttonTexture[URANUS].getHeight());
		
		// status button defaultnya inactive
		for (int idx = 0; idx < this.buttonIsActive.length; idx++) {
			buttonIsActive[idx] = false;
		}

		/*
		this.setDialogNaration("data/dialog/modul2/dialog1.txt");
		this.setDialogBackground("data/images/general/dialog.png");
		this.setDialogBackgroundPosition(
				(VIRTUAL_WIDTH - this.dialogBackground.getWidth())/2,this.dialogBackground.getHeight()-350);
		this.setKarakterLeftPosition(0, 40);
		this.setKarakterRightPosition(VIRTUAL_WIDTH, 40);
		this.setLineLength(750);
		this.setTextPosition(300, this.dialogBackground.getHeight() - 50);
		this.setFont("data/font/kg-corner-of-the-sky-44-black.fnt",
				"data/font/kg-corner-of-the-sky-44-black.png");
		*/
		this.setMusicBg("data/sounds/music/modul1/scene1theme.mp3");

		this.controller = new Modul2Scene1bController(this);
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

		// gambar button2
		for (int idx = 0; idx < 6; idx++) {
			if (buttonIsActive[idx]) {
				batcher.draw(buttonActiveTexture[idx],
						this.buttonBounds[idx].getX(),
						this.buttonBounds[idx].getY());

			} else {
				batcher.draw(buttonTexture[idx], this.buttonBounds[idx].getX(),
						this.buttonBounds[idx].getY());
			}
		}

		batcher.end();

		//super.render(delta);
		controller.processInput();
	}

	@Override
	public void dispose() {
		this.background.dispose();
		super.dispose();
	}

}
