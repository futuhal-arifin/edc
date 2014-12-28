package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.LoadingMainController;
import id.ac.ui.edoocatia.util.AbstractScreen;

public class LoadingMainScreen extends AbstractScreen {

	private TextureRegion logo;
	private TextureRegion loadingFrame;
	private TextureRegion loadingBarHidden;
	private TextureRegion screenBg;
	private TextureRegion loadingBg;
	private TextureRegion currentFrame;

	private TextureAtlas atlas;

	private float percent;
	private float stateTime;

	private Animation anim;

	private LoadingMainController controller;
	
	private Edoocatia app;

	public LoadingMainScreen(Edoocatia app) {
		super(app);
		this.app = app;
		Texture.setEnforcePotImages(false);
		controller = new LoadingMainController(this);

		atlas = new TextureAtlas(
				Gdx.files.internal("data/loading-screen/loading.pack"));

		// load the assets
		assets.loadGroup("menu");
	}

	public void render(float delta) {

		if (assets.update()) { // Load some, will return true if done
								// loading
			Texture background = assets.get("data/images/menu/title.png", Texture.class);
			
			if (Gdx.input.isTouched()) { // If the screen is touched after the
											// game is done loading, go to the
											// main menu screen
				controller.processInput();
			}
		}

		cam.update();

		// set viewport
		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
				(int) viewport.width, (int) viewport.height);

		/*
		 * kalo ngga salah ini supaya background virtualnya warna hitam, tapi
		 * ngga tau juga sih ._.
		 */
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = anim.getKeyFrame(stateTime, true);

		// Interpolate the percentage to make it more smooth
		percent = Interpolation.linear.apply(percent, assets.getProgress(),
				0.1f);

		/*
		 * Setiap mau gambar set dulu projection matrixnya, terus diawali
		 * batcher.begin(), diakhiri batcher.end()
		 */
		batcher.setProjectionMatrix(cam.combined);
		batcher.begin();

		batcher.draw(logo, (VIRTUAL_WIDTH - logo.getRegionWidth()) / 2,
				(VIRTUAL_HEIGHT - logo.getRegionHeight()) / 2 + 100);

		batcher.draw(loadingFrame,
				(VIRTUAL_WIDTH - loadingFrame.getRegionWidth()) / 2,
				(VIRTUAL_HEIGHT - loadingFrame.getRegionHeight()) / 2);

		batcher.draw(currentFrame,
				(VIRTUAL_WIDTH - loadingFrame.getRegionWidth()) / 2 + 15,
				(VIRTUAL_HEIGHT - loadingFrame.getRegionHeight()) / 2 + 5);

		batcher.draw(
				loadingBarHidden,
				((VIRTUAL_WIDTH - loadingFrame.getRegionWidth() / 2 + 440) + 50)
						* percent,
				(VIRTUAL_HEIGHT - loadingFrame.getRegionHeight() / 2) + 2);

		batcher.draw(screenBg, 0, 0);

		batcher.draw(
				loadingBg,
				(((VIRTUAL_WIDTH - loadingFrame.getRegionWidth() / 2) + 50) * percent) + 30,
				(VIRTUAL_HEIGHT - loadingFrame.getRegionHeight() / 2) + 5, 450,
				450 - 450 * percent);

		batcher.end();

	}

	@Override
	public void show() {
		// Grab the regions from the atlas and create some images
		logo = atlas.findRegion("libgdx-logo");
		loadingFrame = atlas.findRegion("loading-frame");
		loadingBarHidden = atlas.findRegion("loading-bar-hidden");
		screenBg = atlas.findRegion("screen-bg");
		loadingBg = atlas.findRegion("loading-frame-bg");

		// Add the loading bar animation
		anim = new Animation(0.05f, atlas.findRegions("loading-bar-anim"));
		anim.setPlayMode(Animation.LOOP_REVERSED);
		// loadingBar = new LoadingBar(anim);
	}

	public Edoocatia getApp() {
		return app;
	}
}
