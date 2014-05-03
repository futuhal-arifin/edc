package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.ChooseCharacterController;
import id.ac.ui.edoocatia.util.AbstractScreen;

public class ChooseCharacterScreen extends AbstractScreen {
	
	private ChooseCharacterController controller;
	
	private Texture noneChosenTexture;
	private Texture characterChosenTexture[] = new Texture[4];
	private Rectangle characterBounds[] = new Rectangle[4];
	private boolean characterIsChosen[] = new boolean[4];
	
	public final int ALTA = 0;
	public final int MOMO = 1;
	public final int AZMO = 2;
	public final int TSARINA = 3;
	// music
	private Sound clickSfx;
	
	private BitmapFont fontTitle;
	
	private boolean debug = false;

	public ChooseCharacterScreen(Edoocatia app) {
		super(app);
		
		this.noneChosenTexture = new Texture(
				Gdx.files.internal("data/images/characters/none_selected.jpg"));
		this.characterChosenTexture[this.ALTA] = new Texture(
				Gdx.files.internal("data/images/characters/alta_selected.jpg"));
		this.characterChosenTexture[this.MOMO] = new Texture(
				Gdx.files.internal("data/images/characters/momo_selected.jpg"));
		this.characterChosenTexture[this.AZMO] = new Texture(
				Gdx.files.internal("data/images/characters/azmo_selected.jpg"));
		this.characterChosenTexture[this.TSARINA] = new Texture(
				Gdx.files.internal("data/images/characters/tsarina_selected.jpg"));
		
		characterBounds[this.ALTA] = new Rectangle(0, 0, width/4, height);
		characterBounds[this.MOMO] = new Rectangle(width/4, 0, width/4, height);
		characterBounds[this.AZMO] = new Rectangle(width/2, 0, width/4, height);
		characterBounds[this.TSARINA] = new Rectangle(3*width/4, 0, width/4, height);
		
		for (int idx = 0; idx < this.characterIsChosen.length; idx++) {
			characterIsChosen[idx] = false;
		}
		
		// sfx buat klik
		clickSfx = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/sfx/click.wav"));

		controller = new ChooseCharacterController(this);
	}

	public void render(float delta) {
		// copas.begin (tiap render() di tiap screen mesti ada)
		cam.update();

		// set viewport
		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
				(int) viewport.width, (int) viewport.height);

		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//stage.act(delta);
		//stage.draw();

		/*
		 * Setiap mau gambar set dulu projection matrixnya, terus diawali
		 * batcher.begin(), diakhiri batcher.end()
		 */
		batcher.setProjectionMatrix(cam.combined);

		// copas.end

		/* AREA MENGGAMBAR */
		batcher.begin();
		
		if(this.characterIsChosen(this.ALTA)) {
			batcher.draw(this.characterChosenTexture[this.ALTA], 0, 0);
		} else if(this.characterIsChosen(this.MOMO)) {
			batcher.draw(this.characterChosenTexture[this.MOMO], 0, 0);
		} else if(this.characterIsChosen(this.AZMO)) {
			batcher.draw(this.characterChosenTexture[this.AZMO], 0, 0);
		} else if(this.characterIsChosen(this.TSARINA)) {
			batcher.draw(this.characterChosenTexture[this.TSARINA], 0, 0);
		} else {
			batcher.draw(this.noneChosenTexture, 0, 0);
		}
		
		batcher.end();

		// kl mau munculin bounds, ubah variabel debug jd true
		if (debug) {
			drawDebug(characterBounds, characterIsChosen);
		}

		controller.processInput();
	}
	
	public void playSoundFx() {
	 if(Gdx.app.getPreferences("preferences").getBoolean("soundOn"))
		this.clickSfx.play();
	}
	
	public Rectangle[] getCharacterBounds() {
		return characterBounds;
	}
	
	public boolean characterIsChosen(int index) {
		return this.characterIsChosen[index];
	}

	public void setCharacterIsChosen(boolean status, int index) {
		this.characterIsChosen[index] = status;
	}

	@Override
	public void dispose() {
		this.noneChosenTexture.dispose();
		for(int index = 0; index < this.characterChosenTexture.length; index++) {
			this.characterChosenTexture[index].dispose();
		}
		fontTitle.dispose();
		this.clickSfx.dispose();
		super.dispose();
	}
}
