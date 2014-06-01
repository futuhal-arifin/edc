package id.ac.ui.edoocatia;

import id.ac.ui.edoocatia.model.EdoocatiaModel;
import id.ac.ui.edoocatia.util.ScreenEnum;
import id.ac.ui.edoocatia.util.ScreenManager;
import id.ac.ui.edoocatia.util.SoundFxPlayer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;

public class Edoocatia extends Game {

	private EdoocatiaModel edoocatia;
	public ScreenManager screenMgr;
	public SoundFxPlayer sfxPlayer = new SoundFxPlayer();

	@Override
	public void create() {
		Texture.setEnforcePotImages(false);
		edoocatia = new EdoocatiaModel();
		screenMgr = ScreenManager.getInstance();
		screenMgr.initialize(this);
		//screenMgr.show(ScreenEnum.SPLASH);
		//screenMgr.show(ScreenEnum.MODUL2_SCENE1);

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

	}

	public void changeScreen(ScreenEnum current, ScreenEnum next) {
		screenMgr.dispose(current);
		screenMgr.show(next);
	}
}
