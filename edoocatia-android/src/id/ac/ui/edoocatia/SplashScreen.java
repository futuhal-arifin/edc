package id.ac.ui.edoocatia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashScreen extends Activity {

	// Splash screen timer
	private static int SPLASH_TIME_OUT = 3000;
	private Thread mSplashThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.active_splash);

		final ImageView splashImageView = (ImageView) findViewById(R.id.SplashImageView);
		splashImageView.setBackgroundResource(R.drawable.flag);
		final AnimationDrawable frameAnimation = (AnimationDrawable) splashImageView
				.getBackground();

		splashImageView.post(new Runnable() {
			@Override
			public void run() {
				frameAnimation.start();
			}
		});


		//cuma iseng

		final SplashScreen sPlashScreen = this;

		// The thread to wait for splash screen events
		mSplashThread = new Thread() {
			@Override
			public void run() {
				try {
					synchronized (this) {
						// Wait given period of time or exit on touch
						wait(1000);
					}
				} catch (InterruptedException ex) {
				}

				finish();

				// Run next activity
				Intent intent = new Intent();
				intent.setClass(sPlashScreen, MainActivity.class);
				startActivity(intent);
				finish();
			}
		};

		mSplashThread.start();
	}

}
