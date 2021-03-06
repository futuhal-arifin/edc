package id.ac.ui.edoocatia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.Modul1Scene3Controller;
import id.ac.ui.edoocatia.model.LemariPerkakas;

public class Modul1Scene3Screen extends ProfessorInstructionScreen {

	private Modul1Scene3Controller controller;
	// private Texture background;

	// buat lemari perkakas
	private LemariPerkakas item;
	private Texture playerDefaultTexture;
	private String score = "0";
	private float scoreXPosition;
	private BitmapFont font;

	// jawaban benar/salah
	private TextureRegion currentPlayerFrame;
	private TextureRegion currentScoreFrame;
	private boolean justAnsweredWrong;
	private boolean justAnsweredCorrectly;
	private int correctItem;
	private short justSelectedItem;

	public final int ANIMATION_STATE_LIMIT = 1;
	public final int WIN_OR_LOSE_DELAY = 3;

	private short state;
	public int mistakes;
	public short PROF_INFO_SAYAP = 0;
	public short LEMARI_PERKAKAS = 1;
	public short PROF_INFO = 2;
	public short PROF_INFO_WRONG = 3;

	public Modul1Scene3Screen(Edoocatia app) {
		super(app);
		this.setState(this.PROF_INFO_SAYAP);
		controller = new Modul1Scene3Controller(this);
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

	public float getPlayerStateTime() {
		return item.getPlayerStateTime();
	}

	public short getState() {
		return state;
	}

	private void initiateLemariPerkakas() {
		this.setLemariPerkakas();

		playerDefaultTexture = new Texture(Gdx.files.internal(this.getApp()
				.getEdocatiaData().getPlayer().getKarakterDialogTexturePath()));

		this.setJustAnsweredCorrectly(false);
		this.setJustAnsweredWrong(false);
		this.resetJustSelectedItem();
		this.setMusicBg(item.getMusicPath());
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

		if (this.state == this.LEMARI_PERKAKAS) {
			batcher.draw(item.getLemariPerkakasBackground(), 0, 0);

			font.drawWrapped(batcher, this.score, this.scoreXPosition, 700, 800);

			for (int i = 0; i < item.getImageSubstances().length; i++) {

				if (this.cekCerminCembung(i)) {

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

			// kalo baru klik item yg benar/salah, mulai animasi
			// karakter &
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
				// animasi player sedih waktunya dibatasi, krn masih
				// bisa
				// ngeklik lagi
				if (this.mistakes == 3
						|| item.getPlayerStateTime() < this.ANIMATION_STATE_LIMIT) {
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

		}
		batcher.end();
		// ngegambar penjelasan profesor di akhir scene
		super.render(delta);
		controller.processInput();
	}

	public void resetJustSelectedItem() {
		this.justSelectedItem = -1;
	}

	public void setJustSelectedItem(short justSelectedItem) {
		this.justSelectedItem = justSelectedItem;
	}

	public void setJustAnsweredCorrectly(boolean justAnsweredCorrectly) {
		this.justAnsweredCorrectly = justAnsweredCorrectly;
	}

	public void setJustAnsweredWrong(boolean justAnsweredWrong) {
		this.justAnsweredWrong = justAnsweredWrong;
	}

	private void setLemariPerkakas() {
		item = new LemariPerkakas(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, this.getApp());
		this.correctItem = item.besi;

		font = LemariPerkakas.getFont();

		score = LemariPerkakas.getScore();

		this.scoreXPosition = (VIRTUAL_WIDTH - this.font.getBounds(score).width) / 2;

	}

	private void setProfessorInfoBesi() {
		this.setBackground("data/images/modul-1/background/tada.jpg");
		this.setDialogNaration("data/dialog/modul1/scene3a.txt");
		this.setInstructionObject("data/images/modul-1/alat/besi.png");
		this.setShowInstruction(true);
	}

	private void setProfessorInfoSayap() {
		this.setBackground("data/images/modul-1/background/tada.jpg");
		this.setDialogNaration("data/dialog/modul1/scene3.txt");
		this.setInstructionObject("data/images/modul-1/pesawat/sayap.png");
		this.setShowInstruction(true);
	}

	private void setProfessorInfoKesulitan() {
		this.setBackground("data/images/modul-1/background/tada.jpg");
		this.setDialogNaration("data/dialog/modul1/scene3b.txt");
		this.setShowInstruction(true);
	}

	public void setState(short state) {
		this.state = state;
		if (state == this.LEMARI_PERKAKAS) {
			this.setShowInstruction(false);
			this.initiateLemariPerkakas();

		} else {
			if (item != null) {
				this.stopMusic();
				item.dispose();
			}
			if (state == this.PROF_INFO_SAYAP) {
				this.item = null;
				this.setProfessorInfoSayap();
			} else if (state == this.PROF_INFO) {
				this.setProfessorInfoBesi();
			} else if (state == this.PROF_INFO_WRONG) {
				this.setProfessorInfoKesulitan();
			}
		}
	}

	public void setMistakes(int mistakes) {
		this.mistakes = mistakes;
	}

	public int getMistakes() {
		return this.mistakes;
	}

	public boolean cekCerminCembung(int index) {
		return index != item.cermin_cembung
				|| (index == item.cermin_cembung && this.showCerminCembung());
	}

	private boolean showCerminCembung() {
		return !this.getApp().getEdocatiaData().isModul1Scene5Done();
	}

	@Override
	public void dispose() {
		this.playerDefaultTexture.dispose();
		super.dispose();
	}
}
