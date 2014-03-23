package id.ac.ui.edoocatia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Edoocatia extends Game {

	public static final int GAME_STATE_PLAY 	= 0;
	public static final int GAME_STATE_PAUSE 	= 1;
	public static final int GAME_STATE_ANIMATE 	= 2;

	public final static int WIDTH 	= 800;
	public final static int HEIGHT 	= 480;
	
	private MainMenuScreen menuScreen;
	
	public OrthographicCamera camera;
	public SpriteBatch batch;
	public Texture texture;
	public Sprite sprite;
	public BitmapFont font;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();

		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		texture.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
