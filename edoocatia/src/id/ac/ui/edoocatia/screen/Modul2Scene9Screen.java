package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.MainMenuController;
import id.ac.ui.edoocatia.controller.Modul2Scene1Controller;
import id.ac.ui.edoocatia.controller.Modul2Scene9Controller;
import id.ac.ui.edoocatia.model.LemariPerkakas;
import id.ac.ui.edoocatia.model.SpaceItem;
import id.ac.ui.edoocatia.util.AbstractScreen;

public class Modul2Scene9Screen extends ProfessorInstructionScreen {

	Stage stage;
	private Modul2Scene9Controller controller;
	// bg texture
	private Texture background;
	private Texture bird;
	private Texture plane;
	private Texture player;

	// buttons

	private Texture buttonTexture[] = new Texture[1];
	private Texture buttonActiveTexture[] = new Texture[1];
	private Rectangle buttonBounds[] = new Rectangle[1];
	private boolean buttonIsActive[] = new boolean[1];

	private short state;
	public short BUMI = 0;
	public short PROF_INFO = 1;

	// konstanta biar kita gausah ngafalin indeksnya
	public final int bimasakti = 0;

	// music
	// private Music mainMenuMusicBg;
	private Sound clickSfx;

	private boolean debug = false;

	public Modul2Scene9Screen(Edoocatia app) {
		super(app);
		this.setState(this.BUMI);
		Texture.setEnforcePotImages(false);

		/* inisialisasi segalanya */

		// gambar2
		background = new Texture(
				Gdx.files.internal("data/images/modul-2/earth_surface.jpg"));
		bird = new Texture(Gdx.files.internal("data/images/modul-2/burung.png"));
		plane = new Texture(
				Gdx.files
						.internal("data/images/modul-2/pesawat_samping_medium.png"));
		player = new Texture(
				Gdx.files.internal("data/images/characters/azmo_amazed.png"));
		// batas2 button

		// status button defaultnya inactive

		// !! wajib daftarin controller
		controller = new Modul2Scene9Controller(this);
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
		batcher.draw(bird, 700, 50);
		batcher.draw(plane, 200, 300);
		batcher.draw(player, 800, 300);

		batcher.end();

		// kl mau munculin bounds, ubah variabel debug jd true
		if (debug) {
			drawDebug(buttonBounds, buttonIsActive);
		}

		controller.processInput();
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
		if (state == this.PROF_INFO) {
			this.setProfessorInfoBumi();
			this.setShowInstruction(true);

		} else {
			// STATE INFO BUMI
			this.setShowInstruction(false);

		}
	}

	private void setProfessorInfoBumi() {
		this.setDialogNaration("data/dialog/modul2/dialog9.txt");
		this.setShowInstruction(true);
	}

	// music

	public void playSoundFx() {
		if (Gdx.app.getPreferences("preferences").getBoolean("soundOn"))
			this.clickSfx.play();
	}

}
