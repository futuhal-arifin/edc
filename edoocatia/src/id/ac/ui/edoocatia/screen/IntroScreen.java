package id.ac.ui.edoocatia.screen;

import sun.swing.BakedArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.IntroController;
import id.ac.ui.edoocatia.util.AbstractScreen;

public class IntroScreen extends AbstractScreen {
	private Texture background;
	private Texture background2;
	private Texture background3;
	private Texture background4;
	private Texture background5;
	private Texture background6;
	private Texture background7;

	private long startTime;
	private long endTime;
	private int rendCount;
	final int background1Time = 1500;
	final int background2Time = 3000;
	final int background3Time = 4500;
	final int background4Time = 6000;
	final int background5Time = 7500;
	private IntroController introController;

	public IntroScreen(Edoocatia app) {
		super(app);
		Texture.setEnforcePotImages(false);
		background = new Texture(Gdx.files.internal("data/images/intro/1.jpg"));
		background2 = new Texture(Gdx.files.internal("data/images/intro/2.jpg"));
		background3 = new Texture(Gdx.files.internal("data/images/intro/3.jpg"));
		background4 = new Texture(Gdx.files.internal("data/images/intro/4.jpg"));
		background5 = new Texture(Gdx.files.internal("data/images/intro/5.jpg"));
		background6 = new Texture(Gdx.files.internal("data/images/intro/6.jpg"));
		background7 = new Texture(Gdx.files.internal("data/images/intro/7.jpg"));

		rendCount = 1;
		startTime = TimeUtils.millis();
		// TODO Auto-generated constructor stub
	}

	public void render(float delta) {
		// TODO Auto-generated method stub
		cam.update();

		// set viewport
		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
				(int) viewport.width, (int) viewport.height);

		/*
		 * kalo ngga salah ini supaya background virtualnya warna hitam, tapi
		 * ngga tau juga sih ._.
		 */
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		/*
		 * Setiap mau gambar set dulu projection matrixnya, terus diawali
		 * batcher.begin(), diakhiri batcher.end()
		 */
		batcher.setProjectionMatrix(cam.combined);
		batcher.begin();

		/*
		 * gambar background di sini
		 */

		switch (rendCount) {
		case 1:
			batcher.draw(background, 0, 0);

			break;

		case 2:
			batcher.draw(background2, 0, 0);

			break;

		case 3:
			batcher.draw(background3, 0, 0);

			break;

		case 4:
			batcher.draw(background4, 0, 0);

			break;

		case 5:
			batcher.draw(background5, 0, 0);

			break;

		case 6:
			batcher.draw(background6, 0, 0);

			break;

		case 7:
			batcher.draw(background7, 0, 0);

			break;

			
		default:
			break;
		}

		batcher.end();

		introController.processInput();
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long newTime) {
		startTime = newTime;
	}

	public void incrementCounter() {
		this.rendCount++;
	}

	public long getBgTime() {
		return this.background1Time;
	}
}
