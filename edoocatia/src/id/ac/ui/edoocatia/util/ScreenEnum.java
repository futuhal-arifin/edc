package id.ac.ui.edoocatia.util;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.SplashScreen;

import com.badlogic.gdx.Screen;



public enum ScreenEnum {
    
    MAIN_MENU {
        @Override
        protected Screen getScreenInstance(Edoocatia app) {
             return new MainMenuScreen(app);
        }
    },

    SPLASH {
        @Override
        protected Screen getScreenInstance(Edoocatia app) {
            return new SplashScreen(app);
        }
    };
/*
    MAIN_MENU {
        @Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
             return new MainMenuScreen();
        }
    },

    GAME {
        @Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
             return new GameScreen();
        }
    },

    CREDITS {
        @Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
             return new CreditsScreen();
        }
    };
*/
    protected abstract Screen getScreenInstance(Edoocatia app);

}
