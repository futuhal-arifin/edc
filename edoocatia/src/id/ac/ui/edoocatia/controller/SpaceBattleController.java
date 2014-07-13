package id.ac.ui.edoocatia.controller;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import id.ac.ui.edoocatia.model.SpaceBattle;
import id.ac.ui.edoocatia.model.SpaceBattleMeteor;
import id.ac.ui.edoocatia.model.SpaceBattleMissileAlien;
import id.ac.ui.edoocatia.model.SpaceBattleMissilePlayer;
import id.ac.ui.edoocatia.screen.SpaceBattleScreen;
import id.ac.ui.edoocatia.util.OverlapTester;

public class SpaceBattleController {

	SpaceBattleScreen screen;
	SpaceBattle data;
	private OrthographicCamera cam;
	private Rectangle viewport;
	private Rectangle[] buttonBounds;
	private int missileCount;
	private final int MAX_ALIEN_MISSILE = 20;

	public SpaceBattleController(SpaceBattleScreen screen) {
		this.screen = screen;
		this.cam = screen.getCam();
		this.viewport = screen.getViewport();
		this.data = screen.getSpaceBattleData();
		this.buttonBounds = screen.getButtonBounds();
		this.missileCount = 0;
	}
	
	public void updatePosition (float delta){
		Iterator<SpaceBattleMeteor> itr = data.getMeteors().iterator();
		while(itr.hasNext()){
			SpaceBattleMeteor obs = itr.next();
			obs.setYPosition(obs.getYPosition() - (data.VELOCITY * 2));
			
			if(obs.getYPosition()<-126){
				obs.dispose();
				itr.remove();
			}
			
		}
		
		if(data.isAlienComing()) {
			data.getAlienLeft().updateAlien(data.getPesawatXPosition()+ data.getPlayer().getPesawat().getWidth()/2, 10);
			data.getAlienRight().updateAlien(data.getPesawatXPosition() + data.getPlayer().getPesawat().getWidth()/2, 10);
			Iterator<SpaceBattleMissileAlien> itrLeft = data.getAlienLeft().getMissiles().iterator();
			while(itrLeft.hasNext()){
				SpaceBattleMissileAlien obs = itrLeft.next();
				obs.updateMissilePosition(data.getPesawatXPosition(), data.getPlayer().getPesawatYPosition());
				if(obs.getPosY() < -126){
					obs.dispose();
					itrLeft.remove();
					this.missileCount++;
				}
			}
			
			Iterator<SpaceBattleMissileAlien> itrRight = data.getAlienRight().getMissiles().iterator();
			while(itrRight.hasNext()){
				SpaceBattleMissileAlien obs = itrRight.next();
				obs.updateMissilePosition(data.getPesawatXPosition(), data.getPlayer().getPesawatYPosition());
				if(obs.getPosY() < -126){
					obs.dispose();
					itrRight.remove();
					this.missileCount++;
				}
			}
			Iterator<SpaceBattleMissilePlayer> itrPlayer = data.getPlayer().getMissiles().iterator();
			while(itrPlayer.hasNext()){
				SpaceBattleMissilePlayer obs = itrPlayer.next();
				obs.updateMissilePosition();
				if(obs.getPosY() < -126){
					obs.dispose();
					itrPlayer.remove();
				}
			}
			
			if(data.getAlienLeft().getStatus() == data.getAlienLeft().ALIEN_FLYING) {
				
			} else if(data.getAlienLeft().getStatus() == data.getAlienLeft().ALIEN_ATTACKING) {

				//System.out.println("s" + this.pesawatXPosition);
				data.generateMissiles(delta);
				
				if(this.missileCount >= this.MAX_ALIEN_MISSILE) {
					this.missileCount = 0;
					data.generateAlienPosition();
					data.getAlienLeft().setStatus(data.getAlienLeft().ALIEN_MOVING);
					data.getAlienRight().setStatus(data.getAlienRight().ALIEN_MOVING);
				}
			} else if(data.getAlienLeft().getStatus() == data.getAlienLeft().ALIEN_MOVING) {
				
			}	
		} else {
			if(data.getBackground1YPosition() >= - data.getScreenHeight()) {
				data.setBackground1YPosition(data
						.getBackground1YPosition() - data.VELOCITY);
			}
			
			if(data.getBackground2YPosition() >= - data.getScreenHeight()) {
				data.setBackground2YPosition(data
						.getBackground2YPosition() - data.VELOCITY);
			}
			
			if(data.getBackground1YPosition() < 0) {
				data.setBackground2YPosition(data.getBackground1YPosition() + data.getScreenHeight());
			} 
			
			if(data.getBackground2YPosition() < 0) {
				data.setBackground1YPosition(data.getBackground2YPosition() + data.getScreenHeight());
			}
			
			data.setCurrentDistance(data.getCurrentDistance()
					+ data.VELOCITY);
			if(data.getCurrentDistance() >= data.getAlienCounter() * 250 * data.MULTIPLIER) {
				data.setAlienCounter(data.getAlienCounter() + 1);
				System.out.println(data.getAlienCounter());
				data.setAlienComing(true);
			}
		}
		
	}
	

	public void processInput(float delta) {
		if (screen.isBattleEnded()) {

		} else {
			this.updatePosition(delta);
			if (data.getCurrentDistance() >= data.getMaxDistance()) {
				screen.setBattleEnded(true);
			}
			if (data.isAlienComing()) {
				// cek kl misil player kena alien
				Iterator<SpaceBattleMissilePlayer> itr = data
						.getSpaceBattlePlayer().getMissiles().iterator();
				while (itr.hasNext()) {
					SpaceBattleMissilePlayer obs = itr.next();
					if (data.getAlienLeft() != null && data.getAlienLeft().isActive()
							&& OverlapTester.overlapRectangles(obs.getBounds(),
									this.data.getAlienLeft().getBounds())
							&& !obs.isHit()) {
						obs.setHit(true);
						obs.dispose();
						itr.remove();
						// System.out.println("kena alien kiri");
						data.getAlienLeft().decrementAlienHealthBy(1);
						if (!data.getAlienLeft().isActive()) {
							screen.getApp()
									.getEdocatiaData()
									.setScore(
											screen.getApp().getEdocatiaData()
													.getScore() + 50);
						}
					} else if (data.getAlienRight() != null && data.getAlienRight().isActive()
							&& OverlapTester.overlapRectangles(obs.getBounds(),
									this.data.getAlienRight().getBounds())
							&& !obs.isHit()) {
						obs.setHit(true);
						obs.dispose();
						itr.remove();
						data.getAlienRight().decrementAlienHealthBy(1);
						if (!data.getAlienRight().isActive()) {
							screen.getApp()
									.getEdocatiaData()
									.setScore(
											screen.getApp().getEdocatiaData()
													.getScore() + 50);
						}
					}
					if (!data.getAlienRight().isActive()
							&& !data.getAlienLeft().isActive()) {
						data.setAlienComing(false);
					}
				}

				// cek kl misil alien kena player
				Iterator<SpaceBattleMissileAlien> itrLeft = data.getAlienLeft()
						.getMissiles().iterator();
				while (itrLeft.hasNext()) {
					SpaceBattleMissileAlien obs = itrLeft.next();
					if (OverlapTester.overlapRectangles(obs.getBounds(),
							this.data.getPesawatBounds()) && !obs.isHit()) {
						obs.setHit(true);
						obs.dispose();
						itrLeft.remove();
						screen.getApp()
								.getEdocatiaData()
								.setScore(
										screen.getApp().getEdocatiaData()
												.getScore() - 10);
					}
				}
				Iterator<SpaceBattleMissileAlien> itrRight = data
						.getAlienRight().getMissiles().iterator();
				while (itrRight.hasNext()) {
					SpaceBattleMissileAlien obs = itrRight.next();
					if (OverlapTester.overlapRectangles(obs.getBounds(),
							this.data.getPesawatBounds()) && !obs.isHit()) {
						obs.setHit(true);
						obs.dispose();
						itrRight.remove();
						screen.getApp()
								.getEdocatiaData()
								.setScore(
										screen.getApp().getEdocatiaData()
												.getScore() - 10);
					}
				}
			}

			// cek kalo nabrak meteor
			Iterator<SpaceBattleMeteor> itr = data.getMeteors().iterator();
			while (itr.hasNext()) {
				SpaceBattleMeteor obs = itr.next();
				if (OverlapTester.overlapRectangles(obs.getBounds(),
						this.data.getPesawatBounds())
						&& !obs.isHit()) {
					obs.setHit(true);
					obs.dispose();
					itr.remove();
					screen.getApp()
							.getEdocatiaData()
							.setScore(
									screen.getApp().getEdocatiaData()
											.getScore() - 5);
				}
			}

			if (Gdx.input.justTouched()) {
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width,
						viewport.height);

				if (OverlapTester.pointInRectangle(buttonBounds[screen.LEFT],
						pos.x, pos.y)) {
					screen.setButtonStatus(true, screen.LEFT);
				} else if (OverlapTester.pointInRectangle(
						buttonBounds[screen.RIGHT], pos.x, pos.y)) {
					screen.setButtonStatus(true, screen.RIGHT);
				} else if (OverlapTester.pointInRectangle(
						buttonBounds[screen.WEAPON], pos.x, pos.y)) {
					if (data.isAlienComing()
							&& !screen.buttonIsActive(screen.WEAPON)) {
						screen.setButtonStatus(true, screen.WEAPON);
					}

				}
			}

			if (Gdx.input.isTouched()) {
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width,
						viewport.height);
				float velocity = data.getSpaceBattlePlayer().getVelocity();
				if (screen.buttonIsActive(screen.LEFT)) {
					// screen.setLeftButtonPressed(false);
					if (OverlapTester.pointInRectangle(
							buttonBounds[screen.LEFT], pos.x, pos.y)) {

						float currentPosition = data.getPesawatXPosition();
						if (currentPosition >= data.getLeftLimit() - velocity) {
							data.setPesawatXPosition(currentPosition - velocity);
						} else {
							data.setPesawatXPosition(data.getLeftLimit());
						}
					}
				} else if (screen.buttonIsActive(screen.RIGHT)) {

					if (OverlapTester.pointInRectangle(
							buttonBounds[screen.RIGHT], pos.x, pos.y)) {
						float currentPosition = data.getPesawatXPosition();
						if (currentPosition <= (data.getRightLimit() + velocity)) {
							data.setPesawatXPosition(currentPosition + velocity);
						} else {
							data.setPesawatXPosition(data.getRightLimit() - data.getPesawatTexture().getWidth());
						}
					}
				} else if (screen.buttonIsActive(screen.WEAPON)) {

					if (OverlapTester.pointInRectangle(
							buttonBounds[screen.WEAPON], pos.x, pos.y)) {
						//float currentPosition = data.getPesawatXPosition();
						data.launchPlayerMissile();
					}
				}
			} else {
				if (screen.buttonIsActive(screen.LEFT)) {
					screen.setButtonStatus(false, screen.LEFT);
				}
				if (screen.buttonIsActive(screen.RIGHT)) {
					screen.setButtonStatus(false, screen.RIGHT);
				}
				if (screen.buttonIsActive(screen.WEAPON)) {
					screen.setButtonStatus(false, screen.WEAPON);
				}
			}
			// }

		}
	}

}
