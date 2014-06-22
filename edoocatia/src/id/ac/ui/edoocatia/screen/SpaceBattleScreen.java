package id.ac.ui.edoocatia.screen;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.SpaceBattleController;
import id.ac.ui.edoocatia.model.SpaceBattle;
import id.ac.ui.edoocatia.model.SpaceBattleMissileAlien;
import id.ac.ui.edoocatia.model.SpaceBattleMeteor;
import id.ac.ui.edoocatia.model.SpaceBattleMissilePlayer;
import id.ac.ui.edoocatia.util.AbstractScreen;

public class SpaceBattleScreen extends AbstractScreen {

	private SpaceBattleController controller;
	private Texture[] background = new Texture[2];

	private Texture button[] = new Texture[3];
	private Texture buttonActive[] = new Texture[3];
	private boolean buttonIsActive[] = new boolean[3];
	
	public final int LEFT = 0;
	public final int RIGHT = 1;
	public final int WEAPON = 2;

	private Rectangle buttonBounds[] = new Rectangle[3];
	
	private SpaceBattle data;
	private boolean isBattleEnded;
	
	private boolean debug = false;

	public SpaceBattleScreen(Edoocatia app, int distance) {
		super(app);

		this.setBattleEnded(false);
		data = new SpaceBattle(distance, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);

		background[0] = new Texture(
				Gdx.files.internal("data/images/modul-2/space.jpg"));
		background[1] = new Texture(
				Gdx.files.internal("data/images/modul-2/space2.jpg"));
		
		button[WEAPON] = new Texture(
				Gdx.files.internal("data/images/modul-2/icon_efek/weapon/fire_button.png"));
		buttonActive[WEAPON] = new Texture(
				Gdx.files.internal("data/images/modul-2/icon_efek/weapon/fire_button_active.png"));
		
		button[LEFT] = new Texture(
				Gdx.files.internal("data/images/modul-2/left.png"));
		button[RIGHT] = new Texture(
				Gdx.files.internal("data/images/modul-2/right.png"));
		buttonActive[LEFT] = new Texture(
				Gdx.files.internal("data/images/modul-2/left_pressed.png"));
		buttonActive[RIGHT] = new Texture(
				Gdx.files.internal("data/images/modul-2/right_pressed.png"));
		
		buttonBounds[LEFT] = new Rectangle(20, (VIRTUAL_HEIGHT-button[LEFT].getHeight())/2,
				button[LEFT].getWidth(), button[LEFT].getHeight());
		buttonBounds[RIGHT] = new Rectangle(VIRTUAL_WIDTH-20-button[RIGHT].getWidth(), (VIRTUAL_HEIGHT-button[RIGHT].getHeight())/2,
				button[RIGHT].getWidth(), button[RIGHT].getHeight());
		buttonBounds[WEAPON] = new Rectangle(VIRTUAL_WIDTH-20-button[WEAPON].getWidth(), 10,
				button[WEAPON].getWidth(), button[WEAPON].getHeight());
		
		for (int idx = 0; idx < this.buttonIsActive.length; idx++) {
			this.buttonIsActive[idx] = false;
		}

		controller = new SpaceBattleController(this);
	}

	public void render(float delta) {


		if (!this.isBattleEnded) {
			
			batcher.begin();
			batcher.draw(background[0], 0, data.getBackground1YPosition());
			batcher.draw(background[1], 0, data.getBackground2YPosition());
			if(data.isAlienComing()) {
				if (buttonIsActive[WEAPON]) {
					batcher.draw(buttonActive[WEAPON], this.buttonBounds[WEAPON].getX(),
							this.buttonBounds[WEAPON].getY());
				} else {
					batcher.draw(button[WEAPON], this.buttonBounds[WEAPON].getX(),
							this.buttonBounds[WEAPON].getY());
				}
				
				Iterator<SpaceBattleMissilePlayer> itrPlayer = data.getSpaceBattlePlayer().getMissiles().iterator();
				while(itrPlayer.hasNext()){
					SpaceBattleMissilePlayer obs = itrPlayer.next();
						batcher.draw(obs.getMissileTexture(), 
								obs.getPosX(),
								obs.getPosY());
				}

				if(data.getAlienLeft().isActive()) {
					Iterator<SpaceBattleMissileAlien> itr = data.getAlienLeft().getMissiles().iterator();
					while(itr.hasNext()){
						SpaceBattleMissileAlien obs = itr.next();
							batcher.draw(obs.getMissileTexture(), 
									obs.getPosX(),
									obs.getPosY());
					}
					data.getAlienLeft().getAlienSprite().draw(batcher);
				}
				
				if(data.getAlienRight().isActive()) {
					Iterator<SpaceBattleMissileAlien> itr = data.getAlienRight().getMissiles().iterator();
					while(itr.hasNext()){
						SpaceBattleMissileAlien obs = itr.next();
							batcher.draw(obs.getMissileTexture(), 
									obs.getPosX(),
									obs.getPosY());
					}
					data.getAlienRight().getAlienSprite().draw(batcher);
					/*
					batcher.draw(data.getAlienRight().getAlienTextureRegion(), 
							data.getAlienRight().getAlienXPosition(), 
							data.getAlienRight().getAlienYPosition(), 
							data.getAlienRight().getAlienXPosition() + data.getAlienRight().getAlienTexture().getWidth() / 2, 
							data.getAlienRight().getAlienYPosition() + data.getAlienRight().getAlienTexture().getHeight() / 2, 
							data.getAlienRight().getAlienTexture().getWidth(), 
							data.getAlienRight().getAlienTexture().getHeight(), 
							1, 1, data.getAlienRight().getAlienRotation());
					System.out.println("x " + data.getAlienRight().getAlienXPosition()+" y "+data.getAlienRight().getAlienYPosition());
					System.out.println("xOr " + (data.getAlienRight().getAlienXPosition()+data.getAlienRight().getAlienTexture().getWidth() / 2)+" yOr "+(data.getAlienRight().getAlienYPosition()+ data.getAlienRight().getAlienTexture().getHeight() / 2));
					System.out.println("rotation " + data.getAlienRight().getAlienRotation());
					
					batcher.draw(data.getAlienRight().getAlienTexture(), 
							data.getAlienRight().getAlienXPosition(), 
							data.getAlienRight().getAlienYPosition(), 
							data.getAlienRight().getAlienXPosition() + data.getAlienRight().getAlienTexture().getWidth() / 2, 
							data.getAlienRight().getAlienYPosition() + data.getAlienRight().getAlienTexture().getHeight() / 2, 
							data.getAlienRight().getAlienTexture().getWidth(), 
							data.getAlienRight().getAlienTexture().getHeight(), 
							1, 
							1, 
							data.getAlienRight().getAlienRotation(), 
							0, 
							0, 
							data.getAlienRight().getAlienTexture().getWidth(), 
							data.getAlienRight().getAlienTexture().getHeight(), 
							false, false); */
				}
				
				batcher.draw(data.getPesawatTexture(), data.getPesawatXPosition()
						- (data.getPesawatTexture().getWidth() / 2), 10);
				
			} else {
				data.generateMeteors(delta);

				
				Iterator<SpaceBattleMeteor> itr = data.getMeteors().iterator();
				while(itr.hasNext()){
					SpaceBattleMeteor obs = itr.next();
						batcher.draw(obs.getMeteorTexture(), 
								obs.getXPosition(),
								obs.getYPosition());
				}
				
				batcher.draw(data.getPesawatTexture(), data.getPesawatXPosition()
						- (data.getPesawatTexture().getWidth() / 2), 10);
			}
			
			if (buttonIsActive[LEFT]) {
				batcher.draw(buttonActive[LEFT], 20,
						(VIRTUAL_HEIGHT - buttonActive[LEFT].getHeight()) / 2);
			} else {
				batcher.draw(button[LEFT], 20,
						(VIRTUAL_HEIGHT - button[LEFT].getHeight()) / 2);
			}

			if (buttonIsActive[RIGHT]) {
				batcher.draw(buttonActive[RIGHT], VIRTUAL_WIDTH - 20
						- buttonActive[RIGHT].getWidth(),
						(VIRTUAL_HEIGHT - buttonActive[RIGHT].getHeight()) / 2);
			} else {
				batcher.draw(button[RIGHT],
						VIRTUAL_WIDTH - 20 - button[RIGHT].getWidth(),
						(VIRTUAL_HEIGHT - button[RIGHT].getHeight()) / 2);
			}

			

			batcher.end();
		}
		// playerTexture = runningGame.getProgressIcon();

		if (debug) {
			// drawDebug();
		}
		data.updatePosition(delta);
		controller.processInput();
	}

	// getter button bounds
	public Rectangle[] getButtonBounds() {
		return buttonBounds;
	}

	// getter button status
	public boolean buttonIsActive(int index) {
		return this.buttonIsActive[index];
	}

	// setter button status
	public void setButtonStatus(boolean status, int index) {
		buttonIsActive[index] = status;
	}

	public SpaceBattle getSpaceBattleData() {
		return this.data;
	}

	@Override
	public void dispose() {
		for (int idx = 0; idx < this.buttonIsActive.length; idx++) {
			this.button[idx].dispose();
			this.buttonActive[idx].dispose();
		}
		for (int idx = 0; idx < this.background.length; idx++) {
			this.background[idx].dispose();
		}
		data.dispose();
		super.dispose();
	}

	public boolean isBattleEnded() {
		return isBattleEnded;
	}

	public void setBattleEnded(boolean isGameOver) {
		this.isBattleEnded = isGameOver;
	}
}
