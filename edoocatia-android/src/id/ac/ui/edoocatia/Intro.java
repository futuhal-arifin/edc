//package id.ac.ui.edoocatia;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.drawable.AnimationDrawable;
//import android.os.Bundle;
//import android.os.Handler;
//import android.widget.ImageView;
//
//public class IntroScreen extends Activity {
//
//	// Splash screen timer
//	private static int SPLASH_TIME_OUT = 3000;
//	private Thread mSplashThread;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.active_splash_intro);
//
//		final ImageView splashImageView = (ImageView) findViewById(R.id.SplashImageView);
//		splashImageView.setBackgroundResource(R.drawable.flag_intro);
//		final AnimationDrawable frameAnimation = (AnimationDrawable) splashImageView
//				.getBackground();
//
//		splashImageView.post(new Runnable() {
//			@Override
//			public void run() {
//				frameAnimation.start();
//			}
//		});
//
//		final IntroScreen sPlashScreen = this;
//
//		// The thread to wait for splash screen events
//		mSplashThread = new Thread() {
//			@Override
//			public void run() {
//				try {
//					synchronized (this) {
//						// Wait given period of time or exit on touch
//						wait(13000);
//					}
//				} catch (InterruptedException ex) {
//				}
//
//				finish();
//
//				// Run next activity
//				Intent intent = new Intent();
//				intent.setClass(sPlashScreen, SceneSatu.class);
//				startActivity(intent);
//				finish();
//
//			}
//		};
//
//		mSplashThread.start();
//	}
//
//}
