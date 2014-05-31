package id.ac.ui.edoocatia.util;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.ChooseCharacterScreen;
import id.ac.ui.edoocatia.screen.IntroScreen;
import id.ac.ui.edoocatia.screen.MainMenuScreen;
import id.ac.ui.edoocatia.screen.Modul1Scene1Screen;
import id.ac.ui.edoocatia.screen.Modul1Scene2Screen;
import id.ac.ui.edoocatia.screen.Modul1Scene3Screen;
import id.ac.ui.edoocatia.screen.Modul1Scene4Screen;
import id.ac.ui.edoocatia.screen.Modul1Scene5Screen;
import id.ac.ui.edoocatia.screen.Modul1Scene6Screen;
import id.ac.ui.edoocatia.screen.Modul1Scene7Screen;
import id.ac.ui.edoocatia.screen.Modul2Scene3Screen;
import id.ac.ui.edoocatia.screen.SplashScreen;

import com.badlogic.gdx.Screen;

public enum ScreenEnum {

	MODUL1_SCENE1 {
		@Override
		protected Screen getScreenInstance(Edoocatia app) {
			return new Modul1Scene1Screen(app);
		}
	},

	MODUL1_SCENE2 {
		@Override
		protected Screen getScreenInstance(Edoocatia app) {
			return new Modul1Scene2Screen(app);
		}
	},

	MODUL1_SCENE3 {
		@Override
		protected Screen getScreenInstance(Edoocatia app) {
			return new Modul1Scene3Screen(app);
		}
	},

	MODUL1_SCENE4 {
		@Override
		protected Screen getScreenInstance(Edoocatia app) {
			return new Modul1Scene4Screen(app);
		}
	},

	MODUL1_SCENE5 {
		@Override
		protected Screen getScreenInstance(Edoocatia app) {
			return new Modul1Scene5Screen(app);
		}
	},

	MODUL1_SCENE6 {
		@Override
		protected Screen getScreenInstance(Edoocatia app) {
			return new Modul1Scene6Screen(app);
		}
	},

	MODUL1_SCENE7 {
		@Override
		protected Screen getScreenInstance(Edoocatia app) {
			return new Modul1Scene7Screen(app);
		}
	},
	
	MODUL2_SCENE3 {
		@Override
		protected Screen getScreenInstance(Edoocatia app) {
			return new Modul2Scene3Screen(app);
		}
	},

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
	},

	INTRO {
		@Override
		protected Screen getScreenInstance(Edoocatia app) {
			return new IntroScreen(app);
		}
	},

	CHOOSECHAR {
		@Override
		protected Screen getScreenInstance(Edoocatia app) {
			return new ChooseCharacterScreen(app);
		}
	};

	protected abstract Screen getScreenInstance(Edoocatia app);

}
