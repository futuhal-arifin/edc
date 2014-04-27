package id.ac.ui.edoocatia;

import id.ac.ui.edoocatia.model.EdoocatiaModel;
import id.ac.ui.edoocatia.util.ScreenEnum;
import id.ac.ui.edoocatia.util.ScreenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;

public class Edoocatia extends Game {

	private EdoocatiaModel edoocatia;
	public ScreenManager screenMgr;

	/*
	 * public static final int GAME_STATE_PLAY = 0; public static final int
	 * GAME_STATE_PAUSE = 1; public static final int GAME_STATE_ANIMATE = 2;
	 * 
	 * public final static int WIDTH = 800; public final static int HEIGHT =
	 * 480;
	 * 
	 * private MainMenuScreen menuScreen;
	 * 
	 * public OrthographicCamera camera; public SpriteBatch batch; public
	 * Texture texture; public Sprite sprite; public BitmapFont font;
	 */
	@Override
	public void create() {
		Texture.setEnforcePotImages(false);
		edoocatia = new EdoocatiaModel();
		screenMgr = ScreenManager.getInstance();
		screenMgr.initialize(this);
		screenMgr.show(ScreenEnum.SPLASH);
		
		// setScreen(new MainMenuScreen(this));
		/*
		 * batch = new SpriteBatch(); font = new BitmapFont();
		 * 
		 * this.setScreen(new MainMenuScreen(this));
		 */
	}

	public EdoocatiaModel getEdocatiaData() {
		return this.edoocatia;
	}

	public ScreenManager getScreenManager() {
		return this.screenMgr;
	}

	@Override
	public void dispose() {
		super.dispose();
		ScreenManager.getInstance().dispose();
		/*
		 * batch.dispose(); font.dispose(); texture.dispose();
		 */
	}

	public void changeScreen(ScreenEnum current, ScreenEnum next) {
		screenMgr.dispose(current);
		screenMgr.show(next);
	}
}
