package id.ac.ui.edoocatia.model;

import id.ac.ui.edoocatia.Edoocatia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class LemariPerkakas {

	private Texture background;
	private Texture ImageSubstance[] = new Texture[13];
	private Rectangle ImageBounds[] = new Rectangle[13];
	private boolean ImageIsActive[] = new boolean[13];
	private Texture ImageTanda[] = new Texture[2];
	private Texture ImageScore[] = new Texture[2];

	// private Texture Texture;
	private Animation[] scoreAnimation = new Animation[2];
	private TextureRegion[][] scoreFrames = new TextureRegion[2][];
	private TextureRegion[] currentScoreFrame;
	public final int SCORE_FRAME_COLS = 1;
	public final int SCORE_FRAME_ROWS = 10;
	private float scoreStateTime;

	private Texture[] playerTexture = new Texture[2];
	private TextureRegion[][] playerFrames = new TextureRegion[2][];
	private Animation[] playerAnimation = new Animation[2];
	private final int FRAME_COLS = 2;
	private final int FRAME_ROWS = 1;
	private float playerStateTime;

	private String musicPath;
	private static String score = "0";
	private float scoreXPosition;
	private static BitmapFont font;
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

	private boolean[] partIsSelected = new boolean[2];
	public final int checklist = 0;
	public final int wrong = 1;

	private float VIRTUAL_HEIGHT;
	private float VIRTUAL_WIDTH;
	private static Edoocatia app;

	public LemariPerkakas(float vIRTUAL_WIDTH2, float vIRTUAL_HEIGHT2,
			Edoocatia app) {
		this.app = app;

		this.setMusicPath("data/sounds/music/modul1/lemariperkakas.ogg");

		this.VIRTUAL_HEIGHT = vIRTUAL_HEIGHT2;
		this.VIRTUAL_WIDTH = vIRTUAL_WIDTH2;

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

		ImageTanda[checklist] = new Texture(
				Gdx.files
						.internal("data/images/icon/right_and_wrong/benar.png"));
		ImageTanda[wrong] = new Texture(
				Gdx.files
						.internal("data/images/icon/right_and_wrong/salah.png"));

		this.setScoreAnimation(this.checklist);
		this.setScoreAnimation(this.wrong);

		this.setPlayerAnimation(this.checklist);
		this.setPlayerAnimation(this.wrong);
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

		score = app.getEdocatiaData().getScore() + "";
		return score;
	}

	public Texture getImageSubstanceTexture(String ImageSubstance) {

		return new Texture(Gdx.files.internal("data/images/modul-1/alat/"
				+ ImageSubstance + ".png"));
	}

	public Texture[] getImageSubstances() {
		return this.ImageSubstance;
	}

	public Texture[] getImageTanda() {
		return this.ImageTanda;
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

	public Texture getLemariPerkakasBackground() {
		return this.background;
	}

	public Texture[] getImageScore() {
		return ImageScore;
	}

	public void setImageScore(Texture imageScore[]) {
		ImageScore = imageScore;
	}

	public Animation[] getScoreAnimation() {
		return scoreAnimation;
	}

	public void setScoreAnimation(Animation[] scoreAnimation) {
		this.scoreAnimation = scoreAnimation;
	}

	public TextureRegion[] getCurrentScoreFrame() {
		return currentScoreFrame;
	}

	public void setCurrentScoreFrame(TextureRegion[] currentScoreFrame) {
		this.currentScoreFrame = currentScoreFrame;
	}

	private void setScoreAnimation(int state) {
		if (state == this.wrong) {
			ImageScore[state] = new Texture(
					Gdx.files.internal("data/images/general/min_20.png"));
		} else {
			ImageScore[state] = new Texture(
					Gdx.files.internal("data/images/general/plus100.png"));
		}
		TextureRegion[][] temp = TextureRegion.split(this.ImageScore[state],
				this.ImageScore[state].getWidth() / SCORE_FRAME_COLS,
				this.ImageScore[state].getHeight() / SCORE_FRAME_ROWS);
		this.scoreFrames[state] = new TextureRegion[SCORE_FRAME_COLS
				* SCORE_FRAME_ROWS];
		int index = 0;
		for (int ii = 0; ii < SCORE_FRAME_ROWS; ii++) {
			for (int jj = 0; jj < SCORE_FRAME_COLS; jj++) {
				scoreFrames[state][index++] = temp[ii][jj];
			}
		}
		scoreAnimation[state] = new Animation(0.05f, this.scoreFrames[state]);
		this.resetScoreStateTime();
	}

	private void setPlayerAnimation(int state) {
		if (state == this.wrong) {
			playerTexture[state] = new Texture(
					Gdx.files.internal(this.app.getEdocatiaData().getPlayer()
							.getKarakterLoseTexturePath()));
		} else {
			playerTexture[state] = new Texture(Gdx.files.internal(this.app
					.getEdocatiaData().getPlayer().getKarakterWinTexturePath()));
		}
		TextureRegion[][] temp = TextureRegion.split(playerTexture[state],
				playerTexture[state].getWidth() / FRAME_COLS,
				playerTexture[state].getHeight() / FRAME_ROWS);
		this.playerFrames[state] = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int ii = 0; ii < FRAME_ROWS; ii++) {
			for (int jj = 0; jj < FRAME_COLS; jj++) {
				playerFrames[state][index++] = temp[ii][jj];
			}
		}
		playerAnimation[state] = new Animation(0.25f, playerFrames[state]);
		this.resetPlayerStateTime();
	}

	public void resetScoreStateTime() {
		this.setScoreStateTime(0f);

	}

	public float getScoreStateTime() {
		return scoreStateTime;
	}

	public void setScoreStateTime(float scoreStateTime) {
		this.scoreStateTime = scoreStateTime;
	}

	public float getPlayerStateTime() {
		return playerStateTime;
	}

	public void setPlayerStateTime(float playerStateTime) {
		this.playerStateTime = playerStateTime;
	}

	public Animation[] getPlayerAnimation() {
		return playerAnimation;
	}

	public void setPlayerAnimation(Animation[] playerAnimation) {
		this.playerAnimation = playerAnimation;
	}

	public void resetPlayerStateTime() {
		playerStateTime = 0f;
	}

	public void dispose() {
		this.background.dispose();
		for (int index = 0; index < this.ImageSubstance.length; index++) {
			this.ImageSubstance[index].dispose();
		}
		for (int index = 0; index < this.ImageTanda.length; index++) {
			this.ImageTanda[index].dispose();
		}
		for (int index = 0; index < this.ImageScore.length; index++) {
			this.ImageScore[index].dispose();
		}
		for (int index = 0; index < this.playerTexture.length; index++) {
			this.playerTexture[index].dispose();
		}
	}

	public String getMusicPath() {
		return musicPath;
	}

	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}
}
