package id.ac.ui.edoocatia.screen;

import java.io.Console;

import javax.script.AbstractScriptEngine;

import sun.util.logging.resources.logging;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.LemariPerkakasController;
import id.ac.ui.edoocatia.util.AbstractScreen;

public class LemariPerkakas extends AbstractScreen {

	private LemariPerkakasController controller;
	private Texture background;
	private Texture ImageSubstance[] = new Texture[13];
	private Rectangle ImageBounds[] = new Rectangle[13];
	private boolean ImageIsActive[] = new boolean[13];
	boolean debug = true;

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

	public LemariPerkakas(Edoocatia app) {
		super(app);

		background = new Texture(
				Gdx.files.internal("data/images/modul-1/background/lemari.jpg"));

		ImageSubstance[besi] = new Texture(
				Gdx.files.internal("data/images/modul-1/alat/besi.png"));
		ImageSubstance[kayu] = new Texture(
				Gdx.files.internal("data/images/modul-1/alat/kayu.png"));
		ImageSubstance[plastik] = new Texture(
				Gdx.files.internal("data/images/modul-1/alat/plastik.png"));

		ImageSubstance[cermin_cekung] = new Texture(
				Gdx.files
						.internal("data/images/modul-1/alat/cermin_cekung.png"));
		ImageSubstance[cermin_datar] = new Texture(
				Gdx.files.internal("data/images/modul-1/alat/cermin_datar.png"));
		ImageSubstance[cermin_cembung] = new Texture(
				Gdx.files
						.internal("data/images/modul-1/alat/cermin_cembung.png"));

		ImageSubstance[gunting] = new Texture(
				Gdx.files.internal("data/images/modul-1/alat/gunting.png"));
		ImageSubstance[tang] = new Texture(
				Gdx.files.internal("data/images/modul-1/alat/tang.png"));
		ImageSubstance[palu] = new Texture(
				Gdx.files.internal("data/images/modul-1/alat/palu.png"));
		ImageSubstance[magnet] = new Texture(
				Gdx.files.internal("data/images/modul-1/alat/magnet.png"));

		ImageSubstance[balon_hidrogen] = new Texture(
				Gdx.files
						.internal("data/images/modul-1/alat/balon_hidrogen.png"));
		ImageSubstance[balon_oksigen] = new Texture(
				Gdx.files
						.internal("data/images/modul-1/alat/balon_oksigen.png"));
		ImageSubstance[balon_nitrogen] = new Texture(
				Gdx.files
						.internal("data/images/modul-1/alat/balon_nitrogen.png"));

		// batas2 button
		ImageBounds[besi] = new Rectangle((VIRTUAL_WIDTH / 5) + 10,
				(VIRTUAL_HEIGHT / 2) + 50, ImageSubstance[besi].getWidth(),
				ImageSubstance[besi].getHeight());

		ImageBounds[kayu] = new Rectangle(ImageBounds[besi].getX() + 150,
				(VIRTUAL_HEIGHT / 2) + 50, ImageSubstance[kayu].getWidth(),
				ImageSubstance[kayu].getHeight());

		ImageBounds[plastik] = new Rectangle(ImageBounds[kayu].getX() + 150,
				(VIRTUAL_HEIGHT / 2) + 50, ImageSubstance[plastik].getWidth(),
				ImageSubstance[plastik].getHeight());

		ImageBounds[cermin_cekung] = new Rectangle(
				ImageBounds[plastik].getX() + 150, (VIRTUAL_HEIGHT / 2) + 50,
				ImageSubstance[cermin_cekung].getWidth(),
				ImageSubstance[cermin_cekung].getHeight());

		ImageBounds[cermin_datar] = new Rectangle(
				ImageBounds[cermin_cekung].getX() + 150,
				(VIRTUAL_HEIGHT / 2) + 50,
				ImageSubstance[cermin_datar].getWidth(),
				ImageSubstance[cermin_datar].getHeight());

		ImageBounds[cermin_cembung] = new Rectangle(
				ImageBounds[cermin_datar].getX() + 150,
				(VIRTUAL_HEIGHT / 2) + 50,
				ImageSubstance[cermin_cembung].getWidth(),
				ImageSubstance[cermin_cembung].getHeight());

		ImageBounds[gunting] = new Rectangle((VIRTUAL_WIDTH / 5) + 10,
				ImageBounds[besi].getY() - 200,
				ImageSubstance[gunting].getWidth(),
				ImageSubstance[gunting].getHeight());

		ImageBounds[tang] = new Rectangle((VIRTUAL_WIDTH / 5) + 10,
				ImageBounds[gunting].getY() - 200,
				ImageSubstance[tang].getWidth(),
				ImageSubstance[tang].getHeight());

		ImageBounds[palu] = new Rectangle(ImageBounds[cermin_cembung].getX(),
				ImageBounds[gunting].getY(), ImageSubstance[palu].getWidth(),
				ImageSubstance[palu].getHeight());

		ImageBounds[magnet] = new Rectangle(ImageBounds[palu].getX(),
				ImageBounds[tang].getY(), ImageSubstance[palu].getWidth(),
				ImageSubstance[palu].getHeight());

		ImageBounds[balon_hidrogen] = new Rectangle(
				ImageBounds[kayu].getX() + 20, ImageBounds[tang].getY() + 50,
				ImageSubstance[balon_hidrogen].getWidth(),
				ImageSubstance[balon_hidrogen].getHeight());

		ImageBounds[balon_oksigen] = new Rectangle(
				ImageBounds[plastik].getX() + 70,
				ImageBounds[balon_hidrogen].getY(),
				ImageSubstance[balon_oksigen].getWidth(),
				ImageSubstance[balon_oksigen].getHeight());

		ImageBounds[balon_nitrogen] = new Rectangle(
				ImageBounds[cermin_datar].getX() - 20,
				ImageBounds[balon_hidrogen].getY(),
				ImageSubstance[balon_nitrogen].getWidth(),
				ImageSubstance[balon_nitrogen].getHeight());

		// status button defaultnya inactive
		for (int idx = 0; idx < this.ImageIsActive.length; idx++) {
			ImageIsActive[idx] = false;
		}

		controller = new LemariPerkakasController(this);
	}

	public void render(float delta) {
		/*
		 * cam.update();
		 * 
		 * Gdx.gl.glViewport((int) viewport.x, (int) viewport.y, (int)
		 * viewport.width, (int) viewport.height); Gdx.gl.glClearColor(0.1f,
		 * 0.1f, 0.1f, 1); Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		 * 
		 * batcher.setProjectionMatrix(cam.combined);
		 */
		batcher.begin();

		batcher.draw(background, 0, 0);

		for (int i = 0; i < ImageSubstance.length; i++) {
			batcher.draw(ImageSubstance[i], this.ImageBounds[i].getX(),
					this.ImageBounds[i].getY());
		}

		batcher.end();

		controller.processInput();
	}

	// getter button bounds
	public Rectangle[] getImageBounds() {
		return ImageBounds;
	}

	public Rectangle getImageSubstancePosition(String Image) {
		if (Image.contentEquals("besi")) {
			return ImageBounds[besi];
		} else if (Image.contentEquals("kayu")) {
			return ImageBounds[kayu];
		} else if (Image.contentEquals("plastik")) {
			return ImageBounds[plastik];
		} else if (Image.contentEquals("cermin_cekung")) {
			return ImageBounds[cermin_cekung];
		} else if (Image.contentEquals("cermin_datar")) {
			return ImageBounds[cermin_datar];
		} else if (Image.contentEquals("cermin_cembung")) {
			return ImageBounds[cermin_cembung];
		} else if (Image.contentEquals("gunting")) {
			return ImageBounds[gunting];
		} else if (Image.contentEquals("tang")) {
			return ImageBounds[tang];
		} else if (Image.contentEquals("palu")) {
			return ImageBounds[palu];
		} else if (Image.contentEquals("magnet")) {
			return ImageBounds[magnet];
		} else if (Image.contentEquals("balon_hidrogen")) {
			return ImageBounds[balon_hidrogen];
		} else if (Image.contentEquals("balon_nitrogen")) {
			return ImageBounds[balon_nitrogen];
		} else if (Image.contentEquals("balon_oksigen")) {
			return ImageBounds[balon_oksigen];
		}

		return null;

	}

	public Texture getImageSubstanceTexture(String ImageSubstance) {

		return new Texture(Gdx.files.internal("data/images/modul-1/alat/"
				+ ImageSubstance + ".png"));
	}

}
