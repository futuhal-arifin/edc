package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.IntroScreen1Modul2Controller;
import id.ac.ui.edoocatia.util.AbstractScreen;

public class IntroScreen1Modul2 extends AbstractScreen {

	private final int SCENE_NUMBER = 1;
	private Texture[] sceneIntro = new Texture[SCENE_NUMBER];

	private long startTime;

	private int rendCount;

	private final int SCENE_TIME_DEFAULT = 1000;
	private final int SCENE_TEXT_1 = 2000;
	private int current_scene_time = SCENE_TEXT_1;

	private IntroScreen1Modul2Controller introController;

	public IntroScreen1Modul2(Edoocatia app) {
		super(app);

		for (int idx = 0; idx < this.sceneIntro.length; idx++) {
			sceneIntro[idx] = new Texture(
					Gdx.files
							.internal("data/images/modul-2/galaksi_bima_sakti.jpg"));
		}

		rendCount = 0;
		startTime = TimeUtils.millis();

		// kalau dibuka langsung play bg music
		this.setMusicBg("data/sounds/music/intro.ogg");
		// !! wajib daftarin controller
		introController = new IntroScreen1Modul2Controller(this);
	}

	public void render(float delta) {

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

		batcher.draw(sceneIntro[rendCount], 0, 0);

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
		switch (this.rendCount) {
		case 0:
			current_scene_time = this.SCENE_TEXT_1;
			break;
		default:
			current_scene_time = this.SCENE_TIME_DEFAULT;
			break;
		}
	}

	public long getBgTime() {
		return this.current_scene_time;
	}

	public boolean isIntroEnded() {
		if (rendCount == SCENE_NUMBER - 1)
			return true;
		else
			return false;
	}

	@Override
	public void dispose() {
		for (int index = 0; index < this.sceneIntro.length; index++) {
			this.sceneIntro[index].dispose();
		}
		super.dispose();
	}
}
