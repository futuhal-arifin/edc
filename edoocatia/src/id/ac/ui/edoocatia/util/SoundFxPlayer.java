package id.ac.ui.edoocatia.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundFxPlayer {
	private Sound modul1RightSfx;
	private Sound modul1WrongSfx;
	private Sound pukulPaluSfx;

	public void setSoundFxModul1() {
		modul1RightSfx = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/sfx/modul-1/benar.ogg"));
		modul1WrongSfx = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/sfx/modul-1/salah.ogg"));
	}

	public void setPukulPaluSoundFx() {
		pukulPaluSfx = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/sfx/modul-1/pukul-palu.ogg"));
	}

	public void playPukulPaluSoundFx() {
		if (Gdx.app.getPreferences("preferences").getBoolean("soundOn")) {
			pukulPaluSfx.play();
		}
	}

	public void stopPukulPaluSoundFx() {
		if (Gdx.app.getPreferences("preferences").getBoolean("soundOn")) {
			pukulPaluSfx.stop();
		}
	}

	public void playModul1RightSoundFx() {
		if (Gdx.app.getPreferences("preferences").getBoolean("soundOn")) {
			modul1RightSfx.play();
		}
	}

	public void playModul1WrongSoundFx() {
		if (Gdx.app.getPreferences("preferences").getBoolean("soundOn")) {
			modul1WrongSfx.play();
		}
	}

	public void disposeModul1SoundFx() {
		this.modul1RightSfx.dispose();
		this.modul1WrongSfx.dispose();
	}

	public void disposePukulPaluSoundFx() {
		this.pukulPaluSfx.dispose();
	}
}
