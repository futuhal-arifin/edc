package id.ac.ui.edoocatia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// AndroidApplicationConfiguration cfg = new
		// AndroidApplicationConfiguration();
		// cfg.useGL20 = false;

		// initialize(new Edoocatia(), cfg);

		// Button play =(Button) findViewById(R.id.buttonPlay);
		// play.setOnClickListener(l)

	}

	public void ButtonPlay_OnClick(View v) {
		Intent i = new Intent(MainActivity.this, Intro.class);
		startActivity(i);
		finish();

	}

}