package id.ac.ui.edoocatia.controller;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.model.Puzzle;
import id.ac.ui.edoocatia.model.PuzzlePiece;
import id.ac.ui.edoocatia.screen.PuzzleScreen;
import id.ac.ui.edoocatia.util.OverlapTester;

import java.util.StringTokenizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class PuzzleController {

	private Edoocatia app;
	private PuzzleScreen screen;
	private Puzzle puzzle;

	private OrthographicCamera cam;
	private Rectangle viewport;

	private Rectangle pauseButtonBounds;
	private Rectangle replayBounds;
	private Rectangle mainMenuBounds;
	private Rectangle nextBounds;
	private Rectangle helpButtonBounds;

	private PuzzlePiece[][] pieces;

	private PuzzlePiece currentPiece;

	private Vector3 prevPos;

	public PuzzleController(PuzzleScreen screen) {
		this.screen = screen;
		app = screen.getApp();
		puzzle = screen.getPuzzle();

		cam = screen.getCam();
		viewport = screen.getViewport();

		pauseButtonBounds = screen.getPauseButtonBounds();
		helpButtonBounds = screen.getHelpButtonBounds();

		replayBounds = screen.getReplayBounds();
		mainMenuBounds = screen.getMainMenuBounds();
		nextBounds = screen.getNextBounds();

		pieces = puzzle.getPieces();
		currentPiece = null;
		prevPos = null;
	}

	public void processInput(float delta) {

		if (puzzle.getTimeLeft() < 0) {
			save();
			puzzle.gameOver();
		}

		if (puzzle.showSolvedTime < 0) {
			save();
			puzzle.gameOver();
		}

		boolean allSolved = true;
		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces[i].length; j++) {
				if (!pieces[i][j].isSolved()) {
					allSolved = false;
				}
			}
		}

		if (allSolved) {
			float score = 60;
			score += ((puzzle.getTimeLeft() * 2.5) / Puzzle.TIME) * 40;
			if (score > 100) {
				score = 100;
			}
			puzzle.showSolved();
		}

		if (puzzle.isGameOver()) {
			processGameOver();
		} else if (puzzle.isShowingSolved()) {
			processShowSolved(delta);
		} else {
			processGame(delta);
		}
	}

	private void save() {
		FileHandle localFile = null;

		String data = localFile.readString();
		StringTokenizer st = new StringTokenizer(data, ";");
		int i = 0;
		String tmp = "";

		if (tmp.charAt(tmp.length() - 1) == ';') {
			tmp = tmp.substring(0, tmp.length() - 1);
		}
		localFile.writeString(tmp, false);
		// System.out.println(tmp);

	}

	private void processShowSolved(float delta) {
		puzzle.showSolvedTime -= delta;
	}

	private void processGame(float delta) {

		puzzle.decTime(delta);

		if (Gdx.input.justTouched()) {
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width,
					viewport.height);

			if (OverlapTester.pointInRectangle(pauseButtonBounds, pos.x, pos.y)) {
				screen.playSoundFx("default");
				screen.pauseMusic();
				screen.setPauseButtonPressed(true);
			} else if (OverlapTester.pointInRectangle(helpButtonBounds, pos.x,
					pos.y)) {
				screen.playSoundFx("default");
				screen.pauseMusic();
				screen.setHelpButtonPressed(true);
			}

			else {
				for (int i = pieces.length - 1; i >= 0 && currentPiece == null; i--) {
					for (int j = pieces[i].length - 1; j >= 0
							&& currentPiece == null; j--) {
						if (OverlapTester.pointInRectangle(
								pieces[i][j].getBounds(), pos.x, pos.y)
								&& !pieces[i][j].isSolved()) {
							screen.playSoundFx("drag");
							pieces[i][j].setPressed(true);
							currentPiece = pieces[i][j];
							prevPos = pos;
						}
					}
				}
			}
		}

		if (Gdx.input.isTouched()) {
			if (currentPiece != null) {
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width,
						viewport.height);

				Vector2 diff = new Vector2(pos.x - prevPos.x, pos.y - prevPos.y);
				if ((diff.x > 0 && currentPiece.getX()
						+ currentPiece.getTexture().getWidth() <= 1024)
						|| (diff.x < 0 && currentPiece.getX() > 0)) {
					currentPiece.addX(diff.x);
				}
				if ((diff.y > 0 && currentPiece.getY()
						+ currentPiece.getTexture().getHeight() <= 600)
						|| (diff.y < 0 && currentPiece.getY() > 0)) {
					currentPiece.addY(diff.y);
				}

				prevPos = pos;
			}
		} else {
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width,
					viewport.height);

			if (currentPiece != null) {
				if (OverlapTester.overlapRectangles(currentPiece
						.getToleranceBounds(), currentPiece.getTarget()
						.getBounds())) {
					currentPiece.setX(currentPiece.getCorrectPos().x);
					currentPiece.setY(currentPiece.getCorrectPos().y);
					screen.playSoundFx("drop");
					currentPiece.setSolved();
				}

				currentPiece = null;
				prevPos = null;

			}
		}
	}

	private void processGameOver() {
		if (Gdx.input.justTouched()) {
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width,
					viewport.height);

			if (OverlapTester.pointInRectangle(replayBounds, pos.x, pos.y)) {
				screen.playSoundFx("default");
				screen.stopMusic();
				screen.setReplayButtonPressed(true);
			}

			else if (OverlapTester.pointInRectangle(mainMenuBounds, pos.x,
					pos.y)) {
				screen.playSoundFx("default");
				screen.stopMusic();
				screen.setMainMenuButtonPressed(true);
			}

		}

		if (!Gdx.input.isTouched()) {
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width,
					viewport.height);
			if (screen.replayButtonIsPressed()) {
				screen.setReplayButtonPressed(false);
				if (OverlapTester.pointInRectangle(replayBounds, pos.x, pos.y)) {
					app.getScreen().dispose();
					app.setScreen(new PuzzleScreen(app, puzzle));
				}
			}

			else if (screen.nextButtonIsPressed()) {
				screen.setNextButtonPressed(false);
				if (OverlapTester.pointInRectangle(nextBounds, pos.x, pos.y)) {
					screen.setNextButtonPressed(false);
					if (OverlapTester
							.pointInRectangle(nextBounds, pos.x, pos.y)) {
						screen.setNextButtonPressed(false);

					}
				}
			}
		}
	}
}
