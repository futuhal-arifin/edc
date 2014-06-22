package id.ac.ui.edoocatia.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class SpaceBattlePlayer {
	private float pesawatXPosition;
	private float pesawatYPosition;
	private Rectangle pesawatBounds;
	private Texture pesawat;
	private List<SpaceBattleMissilePlayer> missiles;
	private final float VELOCITY = 6.0f;
	
	public SpaceBattlePlayer(float x, float y) {
		this.pesawatXPosition = x;
		this.pesawatYPosition = y;
		this.missiles = new ArrayList<SpaceBattleMissilePlayer>();
		this.pesawat = new Texture(
				Gdx.files.internal("data/images/modul-2/pesawat.png"));
		this.pesawatBounds = new Rectangle(pesawatXPosition+(pesawat.getWidth()/7), 20, pesawat.getWidth()/4, pesawat.getHeight());
	}
	
	public float getVelocity() {
		return this.VELOCITY;
	}
	
	public void addMissile() {
		this.missiles.add(new SpaceBattleMissilePlayer(this.pesawatXPosition, this.pesawatYPosition));
	}
	
	public float getPesawatXPosition() {
		return pesawatXPosition;
	}

	public void setPesawatXPosition(float pesawatXPosition) {
		this.pesawatXPosition = pesawatXPosition;
		this.pesawatBounds.x = this.pesawatXPosition;
	}

	public float getPesawatYPosition() {
		return pesawatYPosition;
	}

	public void setPesawatYPosition(float pesawatYPosition) {
		this.pesawatYPosition = pesawatYPosition;
	}

	public Texture getPesawat() {
		return pesawat;
	}

	public void setPesawat(Texture pesawat) {
		this.pesawat = pesawat;
	}

	public Rectangle getPesawatBounds() {
		return pesawatBounds;
	}

	public void setPesawatBounds(Rectangle pesawatBounds) {
		this.pesawatBounds = pesawatBounds;
	}
	
	public void dispose() {
		this.pesawat.dispose();
	}

	public List<SpaceBattleMissilePlayer> getMissiles() {
		return this.missiles;
	}
}
