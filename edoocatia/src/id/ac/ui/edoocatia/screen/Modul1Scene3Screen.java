package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.Modul1Scene3Controller;

public class Modul1Scene3Screen extends ProfessorInstructionScreen {

	private Modul1Scene3Controller controller;
	private Texture background;
	private Texture ImageSubstance[] = new Texture[1];
	private Rectangle ImageBounds[] = new Rectangle[1];
	private boolean ImageIsActive[] = new boolean[1];
	boolean debug = true;

	// konstanta biar kita gausah ngafalin indeksnya
	public final int BESI = 0;
	public final int KAYU = 1;
	public final int GUNTING = 2;
	public final int PLASTIK = 3;
	public final int TANG = 4;
	public final int CERMIN_CEKUNG = 5;
	public final int PALU = 6;
	public final int CERMIN_CEMBUNG = 7;
	public final int BALON_HIDROGEN = 8;
	public final int BALON_OKSIGEN = 9;
	public final int BALON_NITROGEN = 10;
	public final int CERMIN_DATAR = 11;
	public final int MAGNET = 12;

	public Modul1Scene3Screen(Edoocatia app) {
		super(app);

		background = new Texture(
				Gdx.files.internal("data/images/modul-1/background/lemari.jpg"));
		this.setBackground("data/images/modul-1/background/tada.jpg");
		this.setDialogNaration("data/dialog/modul1/scene3.txt");
		this.setInstructionObject("data/images/modul-1/pesawat/sayap.png");

		ImageSubstance[BESI] = new Texture(
				Gdx.files.internal("data/images/modul-1/alat/besi.png"));

		// batas2 button
		ImageBounds[BESI] = new Rectangle(width/ 5, (height/2)+50,
				ImageSubstance[BESI].getWidth(),
				ImageSubstance[BESI].getHeight());

		// status button defaultnya inactive
		for (int idx = 0; idx < this.ImageIsActive.length; idx++) {
			ImageIsActive[idx] = false;
		}

		controller = new Modul1Scene3Controller(this);
	}

	public void render(float delta) {
		cam.update();

		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
				(int) viewport.width, (int) viewport.height);
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batcher.setProjectionMatrix(cam.combined);
		batcher.begin();

		if (!this.getShowInstruction()) {
			batcher.draw(background, 0, 0);
			batcher.draw(ImageSubstance[BESI], this.ImageBounds[BESI].getX(),
					this.ImageBounds[BESI].getY());

		}

		if (debug) {
			drawDebug(ImageBounds, ImageIsActive);
		}

		batcher.end();

		super.render(delta);
		controller.processInput();
	}

	// getter button bounds
	public Rectangle[] getImageBounds() {
		return ImageBounds;
	}
}
