package id.ac.ui.edoocatia.screen;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.PuzzleController;
import id.ac.ui.edoocatia.model.Puzzle;
import id.ac.ui.edoocatia.model.PuzzlePiece;
import id.ac.ui.edoocatia.util.AbstractScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class PuzzleScreen extends AbstractScreen {

	private Puzzle puzzle;
	private PuzzleController controller;

	private PuzzlePiece[][] pieces;

	private Texture background;
	private Texture[][] piecesTexture;

	private Texture pauseButtonTexture;
	private Texture pauseButtonPressedTexture;
	private Rectangle pauseButtonBounds;

	private boolean pauseButtonPressed;

	private Texture helpButtonTexture;
	private Texture helpButtonPressedTexture;
	private Rectangle helpButtonBounds;

	private boolean helpButtonPressed;

	private Texture gameOverBg;
	private Texture replayTexture;
	private Texture replayPressedTexture;
	private Texture mainMenuTexture;
	private Texture mainMenuPressedTexture;
	private Texture nextTexture;

	private Rectangle replayBounds;
	private Rectangle mainMenuBounds;
	private Rectangle nextBounds;
	private boolean replayButtonPressed;
	private boolean mainMenuButtonPressed;
	private boolean nextButtonPressed;
	private BitmapFont font44;

	private Sound pauseClickSfx;
	private Sound dragPieceSfx;
	private Sound dropPieceSfx;
	private Music runningGameMusicBg;

	private boolean debug = false;
	private Texture solved;
	private Texture panel;

	public PuzzleScreen(Edoocatia app, Puzzle puzzle) {
		super(app);

		this.puzzle = puzzle;
		puzzle.reinit();

		pieces = puzzle.getPieces();

		background = new Texture(
				Gdx.files.internal("data/images/menu/bck_menu.jpg"));

		piecesTexture = new Texture[4][4];
		for (int i = 0; i < piecesTexture.length; i++) {
			for (int j = 0; j < piecesTexture[i].length; j++) {
				piecesTexture[i][j] = pieces[i][j].getTexture();
			}
		}

		solved = puzzle.getSolvedTexture();

		gameOverBg = new Texture(Gdx.files.internal("backgrounds/bg.png"));
		panel = new Texture(Gdx.files.internal("backgrounds/settings_bg.png"));
		replayTexture = new Texture(
				Gdx.files.internal("buttons/restart_80.png"));
		replayPressedTexture = new Texture(
				Gdx.files.internal("buttons/restart_pressed_80.png"));
		mainMenuTexture = new Texture(
				Gdx.files.internal("buttons/mainmenu_80.png"));
		mainMenuPressedTexture = new Texture(
				Gdx.files.internal("buttons/mainmenu_pressed_80.png"));
		nextTexture = new Texture(Gdx.files.internal("buttons/dialog_next.png"));

		// inisialisasi bounds buat game over screen here
		replayBounds = new Rectangle(
				(VIRTUAL_WIDTH - replayTexture.getWidth()) / 2 + 50, 200,
				replayTexture.getWidth(), replayTexture.getHeight());
		mainMenuBounds = new Rectangle(
				(VIRTUAL_WIDTH - mainMenuTexture.getWidth()) / 2 - 50, 200,
				mainMenuTexture.getWidth(), mainMenuTexture.getHeight());
		nextBounds = new Rectangle(950, 30, nextTexture.getWidth(),
				nextTexture.getHeight());
		replayButtonPressed = false;
		mainMenuButtonPressed = false;
		nextButtonPressed = false;

		pauseButtonTexture = new Texture(
				Gdx.files.internal("buttons/pause.png"));
		pauseButtonPressedTexture = new Texture(
				Gdx.files.internal("buttons/pause_pressed.png"));

		pauseButtonBounds = new Rectangle(VIRTUAL_WIDTH
				- pauseButtonTexture.getWidth() - 7, VIRTUAL_HEIGHT
				- pauseButtonTexture.getHeight() - 7,
				pauseButtonTexture.getWidth(), pauseButtonTexture.getHeight());

		pauseButtonPressed = false;

		helpButtonTexture = new Texture(Gdx.files.internal("buttons/help.png"));
		helpButtonPressedTexture = new Texture(
				Gdx.files.internal("buttons/help_pressed.png"));

		helpButtonBounds = new Rectangle(VIRTUAL_WIDTH
				- pauseButtonTexture.getWidth() - 97, VIRTUAL_HEIGHT
				- pauseButtonTexture.getHeight() - 7,
				helpButtonTexture.getWidth(), helpButtonTexture.getHeight());

		helpButtonPressed = false;

		font44 = new BitmapFont(
				Gdx.files
						.internal("fonts/sf-cartoonist-hand-44-black-bold.fnt"),
				Gdx.files
						.internal("fonts/sf-cartoonist-hand-44-black-bold.png"),
				false);

		runningGameMusicBg = Gdx.audio.newMusic(Gdx.files
				.internal("music/tapGame.ogg"));

		if (Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			if (this.runningGameMusicBg != null) {
				runningGameMusicBg.setLooping(true);
				runningGameMusicBg.setVolume(1.0f);
				runningGameMusicBg.play();
			}
		} else if (this.runningGameMusicBg != null
				&& this.runningGameMusicBg.isPlaying()) {
			this.stopMusic();
		}

		this.pauseClickSfx = Gdx.audio.newSound(Gdx.files
				.internal("sound/click.mp3"));
		this.dragPieceSfx = Gdx.audio.newSound(Gdx.files
				.internal("sound/drag_puzzle.ogg"));
		this.dropPieceSfx = Gdx.audio.newSound(Gdx.files
				.internal("sound/drop_puzzle.ogg"));

		controller = new PuzzleController(this);
	}

	public void render(float delta) {
		cam.update();
		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
				(int) viewport.width, (int) viewport.height);
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
		if (puzzle.isGameOver()) {
			batcher.draw(gameOverBg, 0, 0);
			batcher.draw(panel, (VIRTUAL_WIDTH - panel.getWidth()) / 2,
					(VIRTUAL_HEIGHT - panel.getHeight()) / 2);

			if (replayButtonPressed) {
				batcher.draw(replayPressedTexture, replayBounds.x,
						replayBounds.y);
			} else {
				batcher.draw(replayTexture, replayBounds.x, replayBounds.y);
			}

			if (mainMenuButtonPressed) {
				batcher.draw(mainMenuPressedTexture, mainMenuBounds.x,
						mainMenuBounds.y);
			} else {
				batcher.draw(mainMenuTexture, mainMenuBounds.x,
						mainMenuBounds.y);
			}

		} else {
			batcher.draw(background, 0, 0);

			if (pauseButtonPressed) {
				batcher.draw(pauseButtonPressedTexture,
						pauseButtonBounds.getX(), pauseButtonBounds.getY());
			} else {
				batcher.draw(pauseButtonTexture, pauseButtonBounds.getX(),
						pauseButtonBounds.getY());
			}

			if (helpButtonPressed) {
				batcher.draw(helpButtonPressedTexture, helpButtonBounds.getX(),
						helpButtonBounds.getY());
			} else {
				batcher.draw(helpButtonTexture, helpButtonBounds.getX(),
						helpButtonBounds.getY());
			}

			int time = (int) puzzle.getTimeLeft();

			font44.draw(batcher, (time / 60) + ":" + ((time % 60) / 10)
					+ (((time % 60) % 10)), 623, 575);

			if (puzzle.isShowingSolved()) {
				batcher.draw(solved, 0, 0);
			} else {
				for (int i = 0; i < pieces.length; i++) {
					for (int j = 0; j < pieces[i].length; j++) {
						batcher.draw(piecesTexture[i][j], pieces[i][j].getX(),
								pieces[i][j].getY());
						// System.out.println(pieces[i][j].getX()+" "+pieces[i][j].getY());
					}
				}
			}
		}
		batcher.end();

		controller.processInput(delta);

		if (debug) {
			drawDebug();
		}
	}

	public Puzzle getPuzzle() {
		return puzzle;
	}

	public Rectangle getPauseButtonBounds() {
		return pauseButtonBounds;
	}

	public void setPauseButtonPressed(boolean b) {
		pauseButtonPressed = b;
	}

	public boolean pauseButtonIsPressed() {
		return pauseButtonPressed;
	}

	public Rectangle getReplayBounds() {
		return replayBounds;
	}

	public Rectangle getMainMenuBounds() {
		return mainMenuBounds;
	}

	public Rectangle getNextBounds() {
		return nextBounds;
	}

	public void setHelpButtonPressed(boolean b) {
		helpButtonPressed = b;
	}

	public boolean helpButtonIsPressed() {
		return helpButtonPressed;
	}

	public Rectangle getHelpButtonBounds() {
		return helpButtonBounds;
	}

	public void setReplayButtonPressed(boolean b) {
		replayButtonPressed = b;
	}

	public void setMainMenuButtonPressed(boolean b) {
		mainMenuButtonPressed = b;
	}

	public void setNextButtonPressed(boolean b) {
		nextButtonPressed = b;
	}

	public boolean replayButtonIsPressed() {
		return replayButtonPressed;
	}

	public boolean mainMenuButtonIsPressed() {
		return mainMenuButtonPressed;
	}

	public boolean nextButtonIsPressed() {
		return nextButtonPressed;
	}

	private void drawDebug() {
		// TODO Auto-generated method stub

		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Point);
		debugRenderer.setColor(new Color(1, 1, 0, 1));
		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces[i].length; j++) {
				Rectangle bounds = pieces[i][j].getTarget().getBounds();
				debugRenderer.rect(bounds.x, bounds.y, bounds.width,
						bounds.height);
			}
		}
		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces[i].length; j++) {
				Rectangle bounds = pieces[i][j].getBounds();
				debugRenderer.rect(bounds.x, bounds.y, bounds.width,
						bounds.height);
			}
		}
		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces[i].length; j++) {
				Rectangle bounds = pieces[i][j].getToleranceBounds();
				debugRenderer.rect(bounds.x, bounds.y, bounds.width,
						bounds.height);
			}
		}
		debugRenderer.end();
	}

	public void playSoundFx(String key) {
		if (Gdx.app.getPreferences("preferences").getBoolean("soundOn"))
			if (key.equals("default")) {
				this.pauseClickSfx.play();
			} else if (key.equals("drag")) {
				this.dragPieceSfx.play();
			} else if (key.equals("drop")) {
				this.dropPieceSfx.play();
			}
	}

	public void stopMusic() {
		if (this.runningGameMusicBg != null) {
			if (this.runningGameMusicBg.isPlaying()) {
				if (this.runningGameMusicBg.isLooping()) {
					this.runningGameMusicBg.setLooping(false);
				}
				this.runningGameMusicBg.stop();
				this.runningGameMusicBg.dispose();
				this.runningGameMusicBg = null;
			}
			this.runningGameMusicBg = null;
		}
	}

	public void pauseMusic() {
		if (this.runningGameMusicBg != null) {
			if (this.runningGameMusicBg.isPlaying()) {
				if (this.runningGameMusicBg.isLooping()) {
					this.runningGameMusicBg.setLooping(false);
				}
				this.runningGameMusicBg.pause();
				// this.runningGameMusicBg.dispose();
				// this.runningGameMusicBg = null;
			}
			// this.runningGameMusicBg = null;
		}
	}

}
