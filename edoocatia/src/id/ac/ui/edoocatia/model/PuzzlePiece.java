package id.ac.ui.edoocatia.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PuzzlePiece {

	// public static float SET_TOL = 0.1f;
	public static float TOUCH_TOL = 0.85f;

	// private final Vector2 INIT_POS = new Vector2(0,0);

	private PuzzleTarget target;

	private Vector2 pos;
	private Vector2 correctPos;
	private Rectangle bounds;
	private Rectangle toleranceBounds;
	private boolean pressed;
	private boolean solved;

	private Texture texture;

	private float touchWidth;
	private float touchHeight;

	public PuzzlePiece(PuzzleTarget target, Vector2 correctPos, Texture texture) {
		this.target = target;
		this.correctPos = correctPos;
		this.texture = texture;
		pressed = false;
		solved = false;
		pos = new Vector2(correctPos.x, correctPos.y);
		/*
		 * bounds = new Rectangle(pos.x+(texture.getWidth()/2)-TOL/2,
		 * pos.y+(texture.getHeight()/2)-TOL/2, TOL, TOL);
		 */
		touchWidth = texture.getWidth() * TOUCH_TOL;
		touchHeight = texture.getHeight() * TOUCH_TOL;
		bounds = new Rectangle(pos.x + (texture.getWidth() - touchWidth) / 2,
				pos.y + (texture.getHeight() - touchHeight) / 2, touchWidth,
				touchHeight);

		// tolWidth = texture.getWidth()*SET_TOL;
		// tolHeight = texture.getHeight()*SET_TOL;
		/*
		 * toleranceBounds = new
		 * Rectangle(pos.x+(texture.getWidth()-tolWidth)/2,
		 * pos.y+(texture.getHeight()-tolHeight)/2, tolWidth, tolHeight);
		 */
		// toleranceBounds = new Rectangle(correctPos.x+65, correctPos.y+65, 20,
		// 20);
		Rectangle rect = target.getBounds();
		toleranceBounds = new Rectangle(rect.x, rect.y, rect.width, rect.height);
	}

	public void reinit() {
		pressed = false;
		solved = false;
	}

	public void setX(float x) {
		float delta = x - pos.x;
		pos.x = x;
		bounds.setX(bounds.getX() + delta);
		toleranceBounds.setX(toleranceBounds.getX() + delta);
	}

	public void setY(float y) {
		float delta = y - pos.y;
		pos.y = y;
		bounds.setY(bounds.getY() + delta);
		toleranceBounds.setY(toleranceBounds.getY() + delta);
	}

	public void addX(float x) {
		pos.x += x;
		bounds.setX(bounds.getX() + x);
		toleranceBounds.setX(toleranceBounds.getX() + x);
	}

	public void addY(float y) {
		pos.y += y;
		bounds.setY(bounds.getY() + y);
		toleranceBounds.setY(toleranceBounds.getY() + y);
	}

	public void setPressed(boolean b) {
		pressed = b;
	}

	public void setSolved() {
		solved = true;
	}

	public PuzzleTarget getTarget() {
		return target;
	}

	public Texture getTexture() {
		return texture;
	}

	public float getX() {
		return pos.x;
	}

	public float getY() {
		return pos.y;
	}

	public Vector2 getCorrectPos() {
		return correctPos;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public Rectangle getToleranceBounds() {
		return toleranceBounds;
	}

	public boolean isPressed() {
		return pressed;
	}

	public boolean isSolved() {
		return solved;
	}

}
