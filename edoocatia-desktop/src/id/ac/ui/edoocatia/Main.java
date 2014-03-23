package id.ac.ui.edoocatia;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Edoocatia";
		// cfg.useGL20 = false;
		cfg.width = 1280;
		cfg.height = 800;

		new LwjglApplication(new Edoocatia(), cfg);
	}
}