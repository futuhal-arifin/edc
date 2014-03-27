package id.ac.ui.edoocatia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MainMenuScreen1 implements Screen {

	// MultipleScreen
	private static final int VIRTUAL_WIDTH = 800;
	private static final int VIRTUAL_HEIGHT = 480;
	private static final float ASPECT_RATIO = (float) VIRTUAL_WIDTH
			/ (float) VIRTUAL_HEIGHT;

	private Camera cameras;
	private Rectangle viewport;

	public Texture splashScreen;
	public TextureRegion splashScreenRegion;
	public SpriteBatch splashScreenSprite;

	final Edoocatia game;

	OrthographicCamera camera;

	public MainMenuScreen(final Edoocatia main) {
		game = main;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT); // Aspect

		splashScreen = new Texture(
				Gdx.files.internal("data/images/splashscreen.png"));
		splashScreenRegion = new TextureRegion(splashScreen, 0, 0, 800, 480);
		splashScreenSprite = new SpriteBatch();
/*
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
		game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
		game.batch.end();
*/
		if (Gdx.input.isTouched()) {
			game.setScreen(new Modul1Screen(game));
			dispose();
		}

		// Multiple Screen
		// ----Aspect Ratio maintenance

		// update camera
		// cameras.update();
		// cameras.apply(Gdx.gl10);

		// set viewport
		// Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
		// (int) viewport.width, (int) viewport.height);

		// clear previous frame
		// Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// DRAW EVERYTHING
		// --maintenance end--

		// splashScreenSprite.begin();
		// splashScreenSprite.disableBlending();
		// splashScreenSprite.draw(splashScreenRegion, 0, 0);
		// splashScreenSprite.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

		// Multiple Screen
		// --Aspect Ratio Maintenance--
		// calculate new viewport
		float aspectRatio = (float) width / (float) height;
		float scale = 1f;
		Vector2 crop = new Vector2(0f, 0f);

		if (aspectRatio > ASPECT_RATIO) {
			scale = (float) height / (float) VIRTUAL_HEIGHT;
			crop.x = (width - VIRTUAL_WIDTH * scale) / 2f;
		} else if (aspectRatio < ASPECT_RATIO) {
			scale = (float) width / (float) VIRTUAL_WIDTH;
			crop.y = (height - VIRTUAL_HEIGHT * scale) / 2f;
		} else {
			scale = (float) width / (float) VIRTUAL_WIDTH;
		}

		float w = (float) VIRTUAL_WIDTH * scale;
		float h = (float) VIRTUAL_HEIGHT * scale;
		viewport = new Rectangle(crop.x, crop.y, w, h);
		// Maintenance ends here--
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		// cameras = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT); //
		// Aspect
		// // Ratio
		// // Maintenance
		//
		// splashScreen = new Texture(
		// Gdx.files.internal("data/images/splashscreen.png"));
		// splashScreenRegion = new TextureRegion(splashScreen, 0, 0, 800, 480);
		// splashScreenSprite = new SpriteBatch();
		//
		// if (Edoocatia.load()) {
		// this.dispose();
		// game.setScreen(new Modul1Screen(game));
		// }
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		// splashScreenSprite.dispose();
		// splashScreen.dispose();
	}
}
