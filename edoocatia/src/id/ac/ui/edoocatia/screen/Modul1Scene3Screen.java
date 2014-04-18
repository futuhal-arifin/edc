package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.Modul1Scene3Controller;

public class Modul1Scene3Screen extends ProfessorInstructionScreen {

	private LemariPerkakas item;;
	private Modul1Scene3Controller controller;
	private Texture background;
	private Texture ImageTanda[] = new Texture[2];

	private Texture ImageSubstance[] = new Texture[13];
	private Rectangle ImageBounds[] = new Rectangle[13];
	private boolean ImageIsActive[] = new boolean[13];
	boolean debug = true;
	private boolean[] partIsSelected = new boolean[2];
	public boolean win;
	public int mistakes;

	// konstanta biar kita gausah ngafalin indeksnya
	public final int besi = 0;
	public final int kayu = 1;
	public final int plastik = 2;

	public final int cermin_cekung = 3;
	public final int cermin_datar = 4;
	public final int cermin_cembung = 5;

	public final int gunting = 6;
	public final int tang = 7;
	public final int palu = 8;
	public final int magnet = 9;

	public final int balon_hidrogen = 10;
	public final int balon_oksigen = 11;
	public final int balon_nitrogen = 12;

	public final int checklist = 0;
	public final int wrong = 1;

	public Modul1Scene3Screen(Edoocatia app) {
		super(app);
		item = new LemariPerkakas(app);
		background = new Texture(
				Gdx.files.internal("data/images/modul-1/background/lemari.jpg"));
		this.setBackground("data/images/modul-1/background/tada.jpg");
		this.setDialogNaration("data/dialog/modul1/scene3.txt");
		this.setInstructionObject("data/images/modul-1/pesawat/sayap.png");

		ImageTanda[checklist] = new Texture(
				Gdx.files
						.internal("data/images/icon/right_and_wrong/benar.png"));
		ImageTanda[wrong] = new Texture(
				Gdx.files
						.internal("data/images/icon/right_and_wrong/salah.png"));

		ImageSubstance[besi] = item.getImageSubstanceTexture("besi");
		ImageSubstance[kayu] = item.getImageSubstanceTexture("kayu");
		ImageSubstance[plastik] = item.getImageSubstanceTexture("plastik");
		ImageSubstance[cermin_cekung] = item
				.getImageSubstanceTexture("cermin_cekung");
		ImageSubstance[cermin_datar] = item
				.getImageSubstanceTexture("cermin_datar");
		ImageSubstance[cermin_cembung] = item
				.getImageSubstanceTexture("cermin_cembung");
		ImageSubstance[gunting] = item.getImageSubstanceTexture("gunting");
		ImageSubstance[tang] = item.getImageSubstanceTexture("tang");
		ImageSubstance[palu] = item.getImageSubstanceTexture("palu");
		ImageSubstance[magnet] = item.getImageSubstanceTexture("magnet");
		ImageSubstance[balon_hidrogen] = item
				.getImageSubstanceTexture("balon_hidrogen");
		ImageSubstance[balon_oksigen] = item
				.getImageSubstanceTexture("balon_oksigen");
		ImageSubstance[balon_nitrogen] = item
				.getImageSubstanceTexture("balon_nitrogen");

		// batas2 button
		ImageBounds[besi] = item.getImageSubstancePosition("besi");
		ImageBounds[kayu] = item.getImageSubstancePosition("kayu");
		ImageBounds[plastik] = item.getImageSubstancePosition("plastik");
		ImageBounds[cermin_cekung] = item
				.getImageSubstancePosition("cermin_cekung");
		ImageBounds[cermin_datar] = item
				.getImageSubstancePosition("cermin_datar");
		ImageBounds[cermin_cembung] = item
				.getImageSubstancePosition("cermin_cembung");
		ImageBounds[gunting] = item.getImageSubstancePosition("gunting");
		ImageBounds[tang] = item.getImageSubstancePosition("tang");
		ImageBounds[palu] = item.getImageSubstancePosition("palu");
		ImageBounds[magnet] = item.getImageSubstancePosition("magnet");
		ImageBounds[balon_hidrogen] = item
				.getImageSubstancePosition("balon_hidrogen");
		ImageBounds[balon_oksigen] = item
				.getImageSubstancePosition("balon_oksigen");
		ImageBounds[balon_nitrogen] = item
				.getImageSubstancePosition("balon_nitrogen");

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

		if (!this.getShowInstruction()) {

			item.render(delta);

			batcher.begin();

			if (this.partImageIsSelected(this.besi)) {
				batcher.draw(ImageTanda[checklist], ImageBounds[besi].getX(),
						this.ImageBounds[besi].getY());
				this.setWin(true);
			} else {

				if (this.getMistakes() < 3) {
					for (int i = 0; i < ImageBounds.length; i++) {
						if (ImageIsActive[i] && i != besi) {

							batcher.draw(ImageTanda[wrong],
									ImageBounds[i].getX(),
									this.ImageBounds[i].getY());

						}
					}
				} else {
					this.setWin(false);
				}
			}

			batcher.end();
		}
		super.render(delta);
		controller.processInput();
	}

	// getter button bounds
	public Rectangle[] getImageBounds() {
		return ImageBounds;
	}

	// getter button status
	public boolean ImageIsActive(int index) {
		return this.ImageIsActive[index];
	}

	// setter button status
	public void setImageStatus(boolean status, int index) {
		ImageIsActive[index] = status;
	}

	// getter button status
	public boolean partImageIsSelected(int index) {
		return this.ImageIsActive[index];
	}

	public void setMistakes(int mistakes) {
		this.mistakes = mistakes;
	}

	public int getMistakes() {
		return this.mistakes;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public boolean getWin() {
		return win;
	}
}
