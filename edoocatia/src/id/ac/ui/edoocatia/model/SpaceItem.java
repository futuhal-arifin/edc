package id.ac.ui.edoocatia.model;

import id.ac.ui.edoocatia.Edoocatia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

public class SpaceItem {

	private Texture background;
	private Texture ImageItem[] = new Texture[6];
	private Rectangle ImageBounds[] = new Rectangle[6];
	private boolean ImageIsActive[] = new boolean[6];

	// private Texture Texture;

	private final int FRAME_COLS = 2;
	private final int FRAME_ROWS = 1;

	private String musicPath;
	private static BitmapFont font;
	boolean debug = true;

	// konstanta biar kita gausah ngafalin indeksnya
	public final int power = 0;
	public final int timer = 1;
	public final int speedometer = 2;
	public final int spesialShoot = 3;
	public final int weapon = 4;
	public final int zoom_in = 5;
	public final int zoom_out = 6;

	private boolean[] partIsSelected = new boolean[2];
	public final int checklist = 0;
	public final int wrong = 1;

	private float VIRTUAL_HEIGHT;
	private float VIRTUAL_WIDTH;
	private static Edoocatia app;

	public SpaceItem(float vIRTUAL_WIDTH2, float vIRTUAL_HEIGHT2, Edoocatia app) {
		this.app = app;

		// this.setMusicPath("data/sounds/music/modul1/lemariperkakas.mp3");

		this.VIRTUAL_HEIGHT = vIRTUAL_HEIGHT2;
		this.VIRTUAL_WIDTH = vIRTUAL_WIDTH2;

		background = new Texture(
				Gdx.files.internal("data/images/modul-2/space.jpg"));

		ImageItem[power] = new Texture(
				Gdx.files
						.internal("data/images/modul-2/icon_efek/power/full.png"));
		ImageItem[timer] = new Texture(
				Gdx.files
						.internal("data/images/modul-2/icon_efek/time/injury_time.png"));
		ImageItem[speedometer] = new Texture(
				Gdx.files
						.internal("data/images/modul-2/icon_efek/speed_indicator/speedometer.png"));

		ImageItem[spesialShoot] = new Texture(
				Gdx.files
						.internal("data/images/modul-2/icon_efek/bomb/bomb_button.png"));
		ImageItem[weapon] = new Texture(
				Gdx.files.internal("data/images/modul-2/icon_efek/fire.png"));
		ImageItem[zoom_in] = new Texture(
				Gdx.files
						.internal("data/images/modul-2/icon_efek/zoom/zoom_in.png"));

		ImageItem[zoom_out] = new Texture(
				Gdx.files
						.internal("data/images/modul-2/icon_efek/zoom/zoom_out.png"));

		// batas2 image

		ImageBounds[power] = new Rectangle(VIRTUAL_WIDTH - 1250,
				VIRTUAL_HEIGHT - 200, ImageItem[power].getWidth(),
				ImageItem[power].getHeight());

		ImageBounds[timer] = new Rectangle(VIRTUAL_WIDTH - 1150,
				VIRTUAL_HEIGHT - 200, ImageItem[timer].getWidth(),
				ImageItem[timer].getHeight());

		ImageBounds[speedometer] = new Rectangle(VIRTUAL_WIDTH - 1000,
				VIRTUAL_HEIGHT - 200, ImageItem[speedometer].getWidth(),
				ImageItem[speedometer].getHeight());

		ImageBounds[spesialShoot] = new Rectangle(VIRTUAL_WIDTH - 150,
				VIRTUAL_HEIGHT - 700, ImageItem[spesialShoot].getWidth(),
				ImageItem[spesialShoot].getHeight());

		ImageBounds[spesialShoot] = new Rectangle(VIRTUAL_WIDTH - 150,
				VIRTUAL_HEIGHT - 700, ImageItem[spesialShoot].getWidth(),
				ImageItem[spesialShoot].getHeight());

		ImageBounds[weapon] = new Rectangle(VIRTUAL_WIDTH - 300,
				VIRTUAL_HEIGHT - 700, ImageItem[weapon].getWidth(),
				ImageItem[weapon].getHeight());

		// ImageBounds[zoom_in] = new Rectangle(,
		// ImageItem[zoom_in].getWidth(),ImageItem[zoom_in].getHeight());

		// this.setScoreAnimation(this.checklist);
		// this.setScoreAnimation(this.wrong);

		// this.setPlayerAnimation(this.checklist);
		// this.setPlayerAnimation(this.wrong);
	}

	// getter button bounds
	public Rectangle[] getImageBounds() {
		return ImageBounds;
	}

	public Rectangle getImageSubstancePosition(String Image) {
		if (Image.contentEquals("power")) {
			return ImageBounds[power];
		} else if (Image.contentEquals("timer")) {
			return ImageBounds[timer];
		} else if (Image.contentEquals("speedometer")) {
			return ImageBounds[speedometer];
		} else if (Image.contentEquals("cermin_cekung")) {
			return ImageBounds[spesialShoot];
		} else if (Image.contentEquals("spesialShoot")) {
			return ImageBounds[spesialShoot];
		} else if (Image.contentEquals("weapon")) {
			return ImageBounds[weapon];
		}

		return null;

	}

	public static BitmapFont getFont() {

		font = new BitmapFont(
				Gdx.files
						.internal("data/font/kg-corner-of-the-sky-44-white.fnt"),
				Gdx.files
						.internal("data/font/kg-corner-of-the-sky-44-white.png"),
				false);

		return font;

	}

	public static String getScore() {

		// score = app.getEdocatiaData().getScore() + "";
		return "";
	}

	/*
	 * public Texture getSpaceItemTexture(String SpaceItem) {
	 * 
	 * return new Texture(Gdx.files.internal("data/images/modul-2/alat/" +
	 * ImageSubstance + ".png")); }
	 */

	public Texture[] getSpaceItem() {
		return this.ImageItem;
	}

	public boolean[] imageIsActive() {
		return this.ImageIsActive;
	}

	public boolean[] getPartIsSelected() {
		return partIsSelected;
	}

	public void setPartIsSelected(boolean[] partIsSelected) {
		this.partIsSelected = partIsSelected;
	}

	public void setImageStatus(boolean status, int index) {
		this.ImageIsActive[index] = status;
	}

	public Texture getSpaceBackground() {
		return this.background;
	}

	public void dispose() {
		this.background.dispose();
		for (int index = 0; index < this.ImageItem.length; index++) {
			this.ImageItem[index].dispose();
		}

	}

	public String getMusicPath() {
		return musicPath;
	}

	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}
}
