package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.Modul2Scene1Controller;
import id.ac.ui.edoocatia.model.SpaceItem;
import id.ac.ui.edoocatia.util.AbstractScreen;

public class Modul2Scene1Screen extends AbstractScreen {

	Stage stage;
	private Modul2Scene1Controller controller;
	// bg texture
	private SpaceItem item;

	// buttons
	/*
	 * bima sakti
	 */
	private Texture buttonTexture[] = new Texture[1];
	private Texture buttonActiveTexture[] = new Texture[1];
	private Rectangle buttonBounds[] = new Rectangle[1];
	private boolean buttonIsActive[] = new boolean[1];
	
	private Texture andromeda;
	private Texture elips;
	private Texture magellanBesar;
	private Texture magellanKecil;
	
	// konstanta biar kita gausah ngafalin indeksnya
	public final int bimasakti = 0;

	// music
	// private Music mainMenuMusicBg;
	private Sound clickSfx;

	private boolean debug = false;

	public Modul2Scene1Screen(Edoocatia app) {
		super(app);
		this.initiateSpaceItem();
		Texture.setEnforcePotImages(false);

		/* inisialisasi segalanya */

		// gambar2
		
		andromeda = new Texture(
				Gdx.files.internal("data/images/modul-2/andromeda.png"));
		elips = new Texture(
				Gdx.files.internal("data/images/modul-2/elips.png"));
		magellanBesar = new Texture(
				Gdx.files.internal("data/images/modul-2/big_magelan.png"));
		magellanKecil = new Texture(
				Gdx.files.internal("data/images/modul-2/little_magelan.png"));
	
		buttonTexture[bimasakti] = new Texture(
				Gdx.files.internal("data/images/modul-2/bimasakti.png"));

		buttonActiveTexture[bimasakti] = new Texture(
				Gdx.files.internal("data/images/modul-2/bimasakti.png"));

		// batas2 button
		buttonBounds[bimasakti] = new Rectangle(width * 7 / 20, height * 3 / 20
				+ this.buttonTexture[bimasakti].getHeight(),
				buttonTexture[bimasakti].getWidth(),
				buttonTexture[bimasakti].getHeight());
		// status button defaultnya inactive
		for (int idx = 0; idx < this.buttonIsActive.length; idx++) {
			buttonIsActive[idx] = false;
		}

		// !! wajib daftarin controller
		controller = new Modul2Scene1Controller(this);
	}

	public SpaceItem getItem() {
		return this.item;
	}

	private void initiateSpaceItem() {
		this.setSpaceItem();
		
	}

	private void setSpaceItem() {
		item = new SpaceItem(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, this.getApp());
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
		batcher.draw(item.getSpaceBackground(), 0, 0);
		batcher.draw(andromeda,  VIRTUAL_WIDTH - 1250, VIRTUAL_HEIGHT - 400);
		batcher.draw(elips,  VIRTUAL_WIDTH - 1150, VIRTUAL_HEIGHT - 750);
		batcher.draw(magellanKecil,  VIRTUAL_WIDTH - 750, VIRTUAL_HEIGHT - 750);
		batcher.draw(magellanBesar,  VIRTUAL_WIDTH - 450, VIRTUAL_HEIGHT - 400);
		
		for (int i = 0; i < item.getSpaceItem().length; i++) {

			// ngegambar items
			/*
			batcher.draw(item.getSpaceItem()[i],
					item.getImageItemX()[i],
					item.getImageItemY()[i]);
			*/
			
			
		}

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
		if (Gdx.app.getPreferences("preferences").getBoolean("soundOn"))
			this.clickSfx.play();
	}

	@Override
	public void dispose() {
		// this.titleTexture.dispose();

		for (int index = 0; index < this.buttonTexture.length; index++) {
			this.buttonTexture[index].dispose();
			this.buttonActiveTexture[index].dispose();
		}
		// this.clickSfx.dispose();
		super.dispose();
	}
}
