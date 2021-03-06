package id.ac.ui.edoocatia.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundFxPlayer {
	private Sound modul1RightSfx;
	private Sound modul1WrongSfx;
	private Sound modul1LoseSfx;
	private Sound pukulPaluSfx;
	
	public void setSoundFxModul1 () {
		modul1RightSfx = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/sfx/modul-1/benar.mp3"));
		modul1WrongSfx = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/sfx/modul-1/salah.mp3"));
		modul1LoseSfx = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/sfx/modul-1/oou.mp3"));
	}
	
	public void setPukulPaluSoundFx() {
		pukulPaluSfx = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/sfx/modul-1/pukul-palu.mp3"));
	}
	
	public void playPukulPaluSoundFx() {
		if(Gdx.app.getPreferences("preferences").getBoolean("soundOn")) {
			pukulPaluSfx.play();
		}
	}
	
	public void stopPukulPaluSoundFx() {
		if(Gdx.app.getPreferences("preferences").getBoolean("soundOn")) {
			pukulPaluSfx.stop();
		}
	}
	
	public void playModul1RightSoundFx() {
		if(Gdx.app.getPreferences("preferences").getBoolean("soundOn")) {
			modul1RightSfx.play();
		}
	}
	
	public void playModul1WrongSoundFx() {
		if(Gdx.app.getPreferences("preferences").getBoolean("soundOn")) {
			modul1WrongSfx.play();
		}
	}
	
	public void playModul1LoseSoundFx() {
		if(Gdx.app.getPreferences("preferences").getBoolean("soundOn")) {
			modul1LoseSfx.play();
		}
	}
	
	public void disposeModul1SoundFx() {
		this.modul1RightSfx.dispose();
		this.modul1WrongSfx.dispose();
		this.modul1LoseSfx.dispose();
	}
	
	public void disposePukulPaluSoundFx() {
		this.pukulPaluSfx.dispose();
	}
}
