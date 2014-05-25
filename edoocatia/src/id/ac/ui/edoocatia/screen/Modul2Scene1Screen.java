package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.MainMenuController;
import id.ac.ui.edoocatia.util.AbstractScreen;

public class Modul2Scene1Screen extends AbstractScreen {

	Stage stage;
	private MainMenuController controller;
	// bg texture
	private Texture background;
	private Texture andromeda;
	private Texture elips;
	private Texture magelanBesar;
	private Texture magelanKecil;
	private Texture power;
	private Texture timer;
	private Texture speedometer;
	private Texture senjata;
	private Texture spesialShoot;
			
	private Texture charTexture;
	// buttons
	/*
	 * bima sakti
	 */
	private Texture buttonTexture[] = new Texture[1];
	private Texture buttonActiveTexture[] = new Texture[1];
	private Rectangle buttonBounds[] = new Rectangle[1];
	private boolean buttonIsActive[] = new boolean[1];
	// konstanta biar kita gausah ngafalin indeksnya
	public final int bimasakti = 0;
	
	// music
	//private Music mainMenuMusicBg;
	private Sound clickSfx;

	private boolean debug = false;

	public Modul2Scene1Screen(Edoocatia app) {
		super(app);

		Texture.setEnforcePotImages(false);

		/* inisialisasi segalanya */

		// gambar2
		background = new Texture(
				Gdx.files.internal("data/images/modul-2/background/background_langit.jpg"));
		andromeda = new Texture(
				Gdx.files.internal("data/images/menu/title.png"));
		charTexture = new Texture(
				Gdx.files.internal("data/images/menu/char.png"));

		buttonTexture[PLAY] = new Texture(
				Gdx.files.internal("data/images/icon/menu/play_button.png"));
		buttonTexture[FORUM] = new Texture(
				Gdx.files.internal("data/images/icon/menu/forum_button.png"));
		buttonTexture[JELAJAH_WEB] = new Texture(
				Gdx.files
						.internal("data/images/icon/menu/jelajah_web_button.png"));
		buttonTexture[SANG_JUARA] = new Texture(
				Gdx.files
						.internal("data/images/icon/menu/sang_juara_button.png"));

		buttonActiveTexture[PLAY] = new Texture(
				Gdx.files
						.internal("data/images/icon/menu/play_button_active.png"));
		buttonActiveTexture[FORUM] = new Texture(
				Gdx.files
						.internal("data/images/icon/menu/forum_button_active.png"));
		buttonActiveTexture[JELAJAH_WEB] = new Texture(
				Gdx.files
						.internal("data/images/icon/menu/jelajah_web_button_active.png"));
		buttonActiveTexture[SANG_JUARA] = new Texture(
				Gdx.files
						.internal("data/images/icon/menu/sang_juara_button_active.png"));

		// batas2 button
		buttonBounds[PLAY] = new Rectangle(width * 14 / 20, height / 20
				+ (this.buttonTexture[JELAJAH_WEB].getHeight() * 1.75f),
				buttonTexture[PLAY].getWidth(), buttonTexture[PLAY].getHeight());
		buttonBounds[FORUM] = new Rectangle((width * 14 / 20)
				- (this.buttonTexture[FORUM].getWidth() * (0.875f)), height
				/ 20 + (this.buttonTexture[JELAJAH_WEB].getHeight() * 0.875f),
				buttonTexture[FORUM].getWidth(),
				buttonTexture[FORUM].getHeight());
		buttonBounds[SANG_JUARA] = new Rectangle((width * 14 / 20)
				+ (this.buttonTexture[FORUM].getWidth() * (0.875f)), height
				/ 20 + (this.buttonTexture[JELAJAH_WEB].getHeight() * 0.875f),
				buttonTexture[SANG_JUARA].getWidth(),
				buttonTexture[SANG_JUARA].getHeight());
		buttonBounds[JELAJAH_WEB] = new Rectangle(width * 14 / 20, height / 20,
				buttonTexture[JELAJAH_WEB].getWidth(),
				buttonTexture[JELAJAH_WEB].getHeight());

		// status button defaultnya inactive
		for (int idx = 0; idx < this.buttonIsActive.length; idx++) {
			buttonIsActive[idx] = false;
		}
		
		// kalau dibuka langsung play bg music
		this.setMusicBg("data/sounds/music/menu.ogg");

		// sfx buat klik
		clickSfx = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/sfx/click.wav"));

		// !! wajib daftarin controller
		//controller = new MainMenuController(this);
	}

	/**
	 * dipanggil terus-menerus selama screen ini hidup
	 */
	public void render(float delta) {
		// copas.begin (tiap render() di tiap screen mesti ada)
		cam.update();

		// set viewport
		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
				(int) viewport.width, (int) viewport.height);

		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		
		/*
		 * Setiap mau gambar set dulu projection matrixnya, terus diawali
		 * batcher.begin(), diakhiri batcher.end()
		 */
		batcher.setProjectionMatrix(cam.combined);

		// copas.end

		/* AREA MENGGAMBAR */
		batcher.begin();

		// gambar background
		batcher.draw(background, 0, 0);

		//batcher.draw(titleTexture, (VIRTUAL_WIDTH-titleTexture.getWidth())/2,
			//	VIRTUAL_HEIGHT - titleTexture.getHeight() - 80);
		batcher.draw(charTexture, 0, 0);
		// gambar button2
		for (int idx = 0; idx < this.buttonIsActive.length; idx++) {
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

		// kl mau munculin bounds, ubah variabel debug jd true
		if (debug) {
			drawDebug(buttonBounds, buttonIsActive);
		}

		controller.processInput();
	}

	// getter button bounds
	public Rectangle[] getButtonBounds() {
		return buttonBounds;
	}
	
	// getter button status
	public boolean buttonIsActive(int index) {
		return this.buttonIsActive[index];
	}
	
	// setter button status
	public void setButtonStatus(boolean status, int index) {
		buttonIsActive[index] = status;
	}
	
	// music

	public void playSoundFx() {
	 if(Gdx.app.getPreferences("preferences").getBoolean("soundOn"))
		this.clickSfx.play();
	}

	@Override
	public void dispose() {
		this.background.dispose();
		//this.titleTexture.dispose();
		this.charTexture.dispose();
		for(int index = 0; index < this.buttonTexture.length; index++) {
			this.buttonTexture[index].dispose();
			this.buttonActiveTexture[index].dispose();
		}
		this.clickSfx.dispose();
		super.dispose();
	}
}
