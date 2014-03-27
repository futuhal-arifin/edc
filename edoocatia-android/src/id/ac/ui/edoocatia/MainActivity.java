package id.ac.ui.edoocatia;

//import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useAccelerometer = false;
		cfg.useCompass = false;

		initialize(new Edoocatia(), cfg);
		// super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		// AndroidApplicationConfiguration cfg = new
		// AndroidApplicationConfiguration();
		// cfg.useAccelerometer = false;
		// cfg.useCompass = false;
		// cfg.useGL20 = false;
		//
		// initialize(new Edoocatia(), cfg);

		// Button play =(Button) findViewById(R.id.buttonPlay);
		// play.setOnClickListener(l)
	}
	//
	// public void ButtonPlay_OnClick(View v) {
	// Intent i = new Intent(MainActivity.this, IntroScreen.class);
	// startActivity(i);
	// finish();
	// }

}