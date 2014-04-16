package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.Modul1Scene6Controller;

public class Modul1Scene6Screen extends ProfessorInstructionScreen {

	private Modul1Scene6Controller controller;
	private Texture background;
	/*
	private Texture pesawatTexture;
	private Texture playerTexture;
	
	private Animation player;
	private TextureRegion[] playerFrames;
	private TextureRegion currentFrame;
	private final int FRAME_COLS = 2;
	private final int FRAME_ROWS = 1;
	private float stateTime;
	
	private boolean showDialog;
	*/
	public Modul1Scene6Screen(Edoocatia app) {
		super(app);
		background = new Texture(Gdx.files.internal("data/images/modul-1/background/lemari.jpg"));
		this.setShowInstruction(false);
		/*
		/pesawatTexture = new Texture(Gdx.files.internal("data/images/modul-1/pesawat/pesawat.jpg"));
		
		playerTexture = app.getEdocatiaData().getPlayer().getKarakterWinTexture();
		TextureRegion[][] temp = TextureRegion.split(playerTexture, playerTexture.getWidth()/FRAME_COLS, playerTexture.getHeight()/FRAME_ROWS);
		this.playerFrames = new TextureRegion[FRAME_COLS*FRAME_ROWS];
		int index= 0;
		for(int ii = 0; ii < FRAME_ROWS; ii++) {
			for(int jj = 0; jj < FRAME_COLS; jj++) {
				playerFrames[index++] = temp[ii][jj];
			}
		}
		player = new Animation(0.5f, playerFrames);
		stateTime = 0f;
		*/
		
		controller = new Modul1Scene6Controller(this);
	}
	
	public void render(float delta) {
		cam.update();

		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
				(int) viewport.width, (int) viewport.height);
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
		
		batcher.draw(this.background, 0, 0);
		batcher.end();
		
		super.render(delta);
		
			
		controller.processInput();
	}

}
