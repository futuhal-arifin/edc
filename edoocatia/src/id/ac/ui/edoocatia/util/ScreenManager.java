package id.ac.ui.edoocatia.util;

import id.ac.ui.edoocatia.Edoocatia;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.IntMap;

public final class ScreenManager {
	 private static ScreenManager instance;

	 private Edoocatia edoocatiaGame;
	 
	 private IntMap<Screen> screens;
	 
	 private ScreenManager() {
	        screens = new IntMap<Screen>();
	    }


	    public static ScreenManager getInstance() {
	        if (null == instance) {
	            instance = new ScreenManager();
	        }
	        return instance;
	    }
	    
	    public void initialize(Edoocatia game) {
	        this.edoocatiaGame = game;
	    } 
	    
	    public void show(ScreenEnum screen) {
	        if (null == edoocatiaGame) return;
	        if (!screens.containsKey(screen.ordinal())) {
	            screens.put(screen.ordinal(), screen.getScreenInstance(edoocatiaGame));
	        }
	        edoocatiaGame.setScreen(screens.get(screen.ordinal()));
	    }

	    public void dispose(ScreenEnum screen) {
	        if (!screens.containsKey(screen.ordinal())) return;
	        screens.remove(screen.ordinal()).dispose();
	    }

	    public void dispose() {
	        for (Screen screen : screens.values()) {
	            screen.dispose();
	        }
	        screens.clear();
	        instance = null;
	    } 
}
