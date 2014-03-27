package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.util.AbstractScreen;

public class Intro extends AbstractScreen {
	private Texture background;
	private Texture background2;
	private long startTime;
	private long endTime;
	private int rendCount;
	final int background1Time = 1500;
	final int background2Time = 3000;
	final int background3Time = 4500;
	final int background4Time = 6000;
	final int background5Time = 7500;
	
	
	public Intro(Edoocatia app) {
		super(app);
		Texture.setEnforcePotImages(false);
		background = new Texture(Gdx.files.internal("data/images/intro/1.jpg"));
		startTime = TimeUtils.millis();
		// TODO Auto-generated constructor stub
	}
	
	public void render(float delta) {
		// TODO Auto-generated method stub
        cam.update();

        rendCount++;
        
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
	}
}
