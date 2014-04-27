package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.Modul1Scene5Controller;
import id.ac.ui.edoocatia.model.LemariPerkakas;

public class Modul1Scene5Screen extends ProfessorInstructionScreen {
	private Modul1Scene5Controller controller;
	private Texture memaluBackground;

	// buat lemari perkakas
	private LemariPerkakas item;
	private Texture playerDefaultTexture;
	// jawaban benar/salah
	private TextureRegion currentPlayerFrame;
	private TextureRegion currentScoreFrame;
	private boolean justAnsweredWrong;
	private boolean justAnsweredCorrectly;
	private int correctItem;
	private short justSelectedItem;
	public int mistakes;
	public final int ANIMATION_STATE_LIMIT = 1;
	public final int WIN_DELAY = 3;

	// buat player memalu
	private Texture playerMemaluTexture;
	private Animation playerMemaluAnimation;
	private TextureRegion[] playerMemaluFrames;
	private TextureRegion currentMemaluFrame;
	private final int MEMALU_FRAME_COLS = 1;
	private final int MEMALU_FRAME_ROWS = 6;
	private float memaluStateTime;

	// private boolean debug = true;

	private short state;

	public short PROF_INSTRUCTION = 0;
	public short LEMARI_PERKAKAS = 1;
	public short PROF_INFO = 2;
	public short PLAYER_MEMALU = 3;
	public short PROF_INFO_WRONG = 4;
	
	public Modul1Scene5Screen(Edoocatia app) {
		super(app);
		this.setState(this.PROF_INSTRUCTION);
		controller = new Modul1Scene5Controller(this);
	}

	public boolean cekBesi(int index) {

		// System.out.println("CEK SHOW BESI+PALU " +this.showBesiDanPalu());
		// kl uda 3&4, dapetin indeks
		return index != item.besi
				|| (index == item.besi && this.showBesiDanPalu());
	}

	public boolean cekPalu(int index) {

		return index != item.palu
				|| (index == item.palu && this.showBesiDanPalu());
	}

	public int getCorrectItem() {
		return this.correctItem;
	}

	public LemariPerkakas getItem() {
		return this.item;
	}

	public short getJustSelectedItem() {
		return justSelectedItem;
	}

	public float getMemaluStateTime() {
		return this.memaluStateTime;
	}

	public float getPlayerStateTime() {
		return item.getPlayerStateTime();
	}

	public short getState() {
		return state;
	}

	private void initiateLemariPerkakas() {
		this.setLemariPerkakas();
		playerDefaultTexture = this.getApp().getEdocatiaData().getPlayer()
				.getKarakterDialogTexture();
		this.setJustAnsweredCorrectly(false);
		this.setJustAnsweredWrong(false);
		this.resetJustSelectedItem();
	}

	public boolean isJustAnsweredCorrectly() {
		return justAnsweredCorrectly;
	}

	public boolean isJustAnsweredWrong() {
		return justAnsweredWrong;
	}

	public void render(float delta) {

		cam.update();

		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
				(int) viewport.width, (int) viewport.height);
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
		// lemari perkakas
		if (this.state == this.LEMARI_PERKAKAS) {
			batcher.draw(item.getLemariPerkakasBackground(), 0, 0);

			for (int i = 0; i < item.getImageSubstances().length; i++) {
				if (this.cekBesi(i) && cekPalu(i)) {

					// ngegambar items
					batcher.draw(item.getImageSubstances()[i],
							item.getImageBounds()[i].getX(),
							item.getImageBounds()[i].getY());

					if (this.getMistakes() < 4) {

						// ngegambar tanda silang/ceklis
						if (item.imageIsActive()[i]) {
							if (i == this.correctItem) {
								batcher.draw(
										item.getImageTanda()[item.checklist],
										item.getImageBounds()[i].getX(),
										item.getImageBounds()[i].getY());
							} else {
								batcher.draw(item.getImageTanda()[item.wrong],
										item.getImageBounds()[i].getX(),
										item.getImageBounds()[i].getY());

							}
						}
					}
				}
			}

			// kalo baru klik item yg benar/salah, mulai animasi karakter &
			// munculin skor
			if (this.justAnsweredCorrectly) {
				// animasi player senang
				item.setPlayerStateTime(item.getPlayerStateTime()
						+ Gdx.graphics.getDeltaTime());
				currentPlayerFrame = item.getPlayerAnimation()[item.checklist]
						.getKeyFrame(item.getPlayerStateTime(), true);
				batcher.draw(currentPlayerFrame, 0, 0);

				// animasi skor +100
				item.setScoreStateTime(item.getScoreStateTime()
						+ Gdx.graphics.getDeltaTime());
				currentScoreFrame = item.getScoreAnimation()[item.checklist]
						.getKeyFrame(item.getScoreStateTime(), false);
				batcher.draw(
						currentScoreFrame,
						item.getImageBounds()[this.correctItem].getX()
								+ item.getImageBounds()[this.correctItem].width
								/ 2,
						item.getImageBounds()[this.correctItem].getY()
								+ item.getImageBounds()[this.correctItem].height
								/ 2);

			} else if (this.justAnsweredWrong) {
				// animasi player sedih waktunya dibatasi, krn masih bisa
				// ngeklik lagi
				if (this.mistakes == 3 || item.getPlayerStateTime() < this.ANIMATION_STATE_LIMIT) {
					// animasi player sedih
					item.setPlayerStateTime(item.getPlayerStateTime()
							+ Gdx.graphics.getDeltaTime());
					currentPlayerFrame = item.getPlayerAnimation()[item.wrong]
							.getKeyFrame(item.getPlayerStateTime(), true);
					batcher.draw(currentPlayerFrame, 0, 0);
					// animasi skor -20
					item.setScoreStateTime(item.getScoreStateTime()
							+ Gdx.graphics.getDeltaTime());
					currentScoreFrame = item.getScoreAnimation()[item.wrong]
							.getKeyFrame(item.getScoreStateTime(), false);
					batcher.draw(
							currentScoreFrame,
							item.getImageBounds()[this.justSelectedItem].getX()
									+ item.getImageBounds()[this.justSelectedItem].width
									/ 2,
							item.getImageBounds()[this.justSelectedItem].getY()
									+ item.getImageBounds()[this.justSelectedItem].height
									/ 2);

				} else {
					// kl animasi udah, reset variabel
					item.resetPlayerStateTime();
					item.resetScoreStateTime();
					this.setJustAnsweredWrong(false);
				}
			} else {
				// keadaan normal
				this.resetJustSelectedItem();
				batcher.draw(this.playerDefaultTexture, 0, 0);
			}
		} else if (this.state == this.PLAYER_MEMALU) {
			batcher.draw(this.memaluBackground, 0, 0);
			// animasi player memalu besi
			memaluStateTime += Gdx.graphics.getDeltaTime();
			currentMemaluFrame = this.playerMemaluAnimation.getKeyFrame(
					memaluStateTime, false);
			batcher.draw(
					currentMemaluFrame,
					(VIRTUAL_WIDTH - (currentMemaluFrame.getRegionWidth())) / 2,
					(VIRTUAL_HEIGHT - (currentMemaluFrame.getRegionHeight())) / 2);
		}
		batcher.end();
		// ngegambar instruksi profesor di awal scene, trus ngegambar penjelasan
		// profesor stlh item dipilih
		super.render(delta);
		controller.processInput();
	}

	public void resetJustSelectedItem() {
		this.justSelectedItem = -1;
	}

	public void setJustAnsweredCorrectly(boolean justAnsweredCorrectly) {
		this.justAnsweredCorrectly = justAnsweredCorrectly;
	}

	public void setJustAnsweredWrong(boolean justAnsweredWrong) {
		this.justAnsweredWrong = justAnsweredWrong;
	}

	public void setJustSelectedItem(short justSelectedItem) {
		this.justSelectedItem = justSelectedItem;
	}

	private void setLemariPerkakas() {
		item = new LemariPerkakas(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, this.getApp());
		this.correctItem = item.cermin_cembung;
	}

	private void setPlayerMemaluAnimation() {
		memaluBackground = new Texture(
				Gdx.files.internal("data/images/modul-1/background/tada.jpg"));

		playerMemaluTexture = this.getApp().getEdocatiaData().getPlayer()
				.getKarakterMemaluTexture();
		TextureRegion[][] temp = TextureRegion.split(playerMemaluTexture,
				playerMemaluTexture.getWidth() / MEMALU_FRAME_COLS,
				playerMemaluTexture.getHeight() / MEMALU_FRAME_ROWS);
		this.playerMemaluFrames = new TextureRegion[MEMALU_FRAME_COLS
				* MEMALU_FRAME_ROWS];
		int index = 0;
		for (int ii = 0; ii < MEMALU_FRAME_ROWS; ii++) {
			for (int jj = 0; jj < MEMALU_FRAME_COLS; jj++) {
				playerMemaluFrames[index++] = temp[ii][jj];
			}
		}
		playerMemaluAnimation = new Animation(0.25f, playerMemaluFrames);
		memaluStateTime = 0f;
	}

	private void setProfessorInfoCerminCembung() {
		this.setBackground("data/images/modul-1/background/tada.jpg");
		this.setDialogNaration("data/dialog/modul1/scene5a.txt");
		this.setInstructionObject("data/images/modul-1/alat/cermin_cembung.png");
		this.setShowInstruction(true);
	}

	private void setProfessorInstruction() {
		this.setBackground("data/images/modul-1/background/tada.jpg");
		this.setDialogNaration("data/dialog/modul1/scene5.txt");
		this.setInstructionObject("data/images/modul-1/pesawat/spion.png");
	}
	
	private void setProfessorInfoKesulitan() {
		this.setBackground("data/images/modul-1/background/tada.jpg");
		this.setDialogNaration("data/dialog/modul1/scene5b.txt");
		this.setShowInstruction(true);
	}

	public void setState(short state) {
		this.state = state;
		if (state == this.LEMARI_PERKAKAS) {
			this.initiateLemariPerkakas();
		} else {
			this.item = null;
			if (state == this.PROF_INFO) {
				this.setProfessorInfoCerminCembung();
				
			} else if (state == this.PLAYER_MEMALU) {
				this.setPlayerMemaluAnimation();
			}else if(state == this.PROF_INFO_WRONG){
				this.setProfessorInfoKesulitan();
			}
			else {
				this.setProfessorInstruction();
			}
		}
	}

	private boolean showBesiDanPalu() {
		return !this.getApp().getEdocatiaData().isModul1Scene3Done()
				&& !this.getApp().getEdocatiaData().isModul1Scene4Done();
	}

	public void setMistakes(int mistakes) {
		this.mistakes = mistakes;
	}

	public int getMistakes() {
		return this.mistakes;
	}
}
