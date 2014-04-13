package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.Modul1Scene3Controller;
import id.ac.ui.edoocatia.controller.Modul1Scene5Controller;

public class Modul1Scene5Screen extends ProfessorInstructionScreen {
	
	private Modul1Scene5Controller controller;
	private Texture background;

	public Modul1Scene5Screen(Edoocatia app) {
		super(app);
		
		background = new Texture(Gdx.files.internal("data/images/modul-1/background/lemari.jpg"));
		this.setBackground("data/images/modul-1/background/tada.jpg");
		this.setDialogNaration("data/dialog/modul1/scene5.txt");
		this.setInstructionObject("data/images/modul-1/pesawat/spion.png");
	
		controller = new Modul1Scene5Controller(this);
	}
	
	public void render(float delta) {
		cam.update();

		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
				(int) viewport.width, (int) viewport.height);
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
		
		if(!this.getShowInstruction()) {
			batcher.draw(background, 0, 0);
		}


		batcher.end();
		
		super.render(delta);
		controller.processInput();
	}
}
