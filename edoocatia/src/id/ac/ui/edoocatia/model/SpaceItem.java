package id.ac.ui.edoocatia.model;

import id.ac.ui.edoocatia.Edoocatia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

public class SpaceItem {

	private Texture background;
	private Texture ImageItem[] = new Texture[7];
	private float ImageItemX[] = new float[7];
	private float ImageItemY[] = new float[7];

	private boolean ImageIsActive[] = new boolean[7];

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
				Gdx.files.internal("data/images/modul-2/icon_efek/weapon/fire_button.png"));
		
		ImageItem[zoom_in] = new Texture(
				Gdx.files
						.internal("data/images/modul-2/icon_efek/zoom/zoom_in.png"));

		ImageItem[zoom_out] = new Texture(
				Gdx.files
						.internal("data/images/modul-2/icon_efek/zoom/zoom_out.png"));

		// batas2 image

		ImageItemX[power] = VIRTUAL_WIDTH - 1250;
		ImageItemY[power] = VIRTUAL_HEIGHT - 200;

		ImageItemX[timer] = VIRTUAL_WIDTH - 1150;
		ImageItemY[timer] = VIRTUAL_HEIGHT - 200;

		ImageItemX[speedometer] = VIRTUAL_WIDTH - 1000;
		ImageItemY[speedometer] = VIRTUAL_HEIGHT - 200;

		ImageItemX[spesialShoot] = VIRTUAL_WIDTH - 150;
		ImageItemY[spesialShoot] = VIRTUAL_HEIGHT - 700;

		ImageItemX[weapon] = VIRTUAL_WIDTH - 300;
		ImageItemY[weapon] = VIRTUAL_HEIGHT - 700;
		
		ImageItemX[zoom_in] = VIRTUAL_WIDTH - 300;
		ImageItemY[zoom_in] = VIRTUAL_HEIGHT - 200;
		
		ImageItemX[zoom_out] = VIRTUAL_WIDTH - 200;
		ImageItemY[zoom_out] = VIRTUAL_HEIGHT - 200;
		
		
		// this.setScoreAnimation(this.checklist);
		// this.setScoreAnimation(this.wrong);

		// this.setPlayerAnimation(this.checklist);
		// this.setPlayerAnimation(this.wrong);
	}

	// getter image pos X
	public float[] getImageItemX() {
		return ImageItemX;
	}

	// getter image pos Y
	public float[] getImageItemY() {
		return ImageItemY;
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
