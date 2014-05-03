package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.SplashController;
import id.ac.ui.edoocatia.util.AbstractScreen;


public class SplashScreen extends AbstractScreen {
	private Texture background;
	private SplashController controller;
	private Edoocatia app;
	private long startTime;
	private final long SPLASH_TIME = 3000;
	
	
	public SplashScreen(Edoocatia app) {
		super(app);
		this.app = app;
		Texture.setEnforcePotImages(false);
		background = new Texture(Gdx.files.internal("data/images/splashscreen.png"));
		startTime = TimeUtils.millis();
		controller = new SplashController(this);
		
	}
	
	public void render(float delta) {
		// TODO Auto-generated method stub
        cam.update();

        // set viewport
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
 
        /*
		 * kalo ngga salah ini supaya background virtualnya
		 * warna hitam, tapi ngga tau juga sih ._.
		 */
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		/*
		 * Setiap mau gambar set dulu projection matrixnya, terus
		 * diawali batcher.begin(), diakhiri batcher.end()
		 */
        batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
		
		/* 
		 * gambar background di sini
		 */
		batcher.draw(background, 0, 0);
		
		batcher.end();
		controller.processInput();
	}
	
	public Edoocatia getApp() {
		return app;
	}
	
	public long getStartTime() {
		return this.startTime;
	}
	
	public long getSplashTime() {
		return this.SPLASH_TIME;
	}

	@Override
	public void dispose() {
		this.background.dispose();
		super.dispose();
	}
}
