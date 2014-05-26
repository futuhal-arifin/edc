package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.Modul2Scene3Controller;

public class Modul2Scene3Screen extends SpaceBattleScreen {

	private Modul2Scene3Controller controller;
	private final static int distance = 1627;
	
	private boolean isArrived;
	
	public Modul2Scene3Screen(Edoocatia app) {
		super(app, distance);
		
		this.setArrived(false);
		
		controller = new Modul2Scene3Controller(this);
	}
	
	public void render(float delta) {
		cam.update();

		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
				(int) viewport.width, (int) viewport.height);
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batcher.setProjectionMatrix(cam.combined);
		if(this.isBattleEnded()) {
			this.setArrived(true);
		}
			
		if(this.isArrived) {
			
			batcher.begin();
			batcher.end();
		} else {
			super.render(delta);
		}
		controller.processInput();
	}

	public boolean isArrived() {
		return isArrived;
	}

	public void setArrived(boolean isArrived) {
		this.isArrived = isArrived;
	}

}
