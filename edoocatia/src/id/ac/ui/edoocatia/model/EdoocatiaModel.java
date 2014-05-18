package id.ac.ui.edoocatia.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * menyimpan setting, game progress & player info 
 * @author inassjunus
 *
 */
public class EdoocatiaModel {
	// player info
	private Karakter player;
	private int score;
	
	// setting
	private boolean soundOn;
	private boolean musicOn;
	
	// game progress
	private boolean modul1Scene3Done;
	private boolean modul1Scene4Done;
	private boolean modul1Scene5Done;
	private boolean modul1Scene6Done;
	
	
	private Preferences prefs;
	
	public EdoocatiaModel() {
		prefs = Gdx.app.getPreferences("preferences");
		this.initiateGlobalVariables();
		this.debug();
	}
	
	public Karakter getPlayer() {
		return player;
	}

	public int getScore() {
		return score;
	}
	
	private void debug() {
		this.setPlayer(new Karakter("alta"));
		this.setModul1Scene3Done(false);
		this.setModul1Scene4Done(false);
		this.setModul1Scene5Done(false);
		this.setModul1Scene6Done(false);
		//this.setScore(0);
		
	}

	/** initialize setting, game progress & player info */
	private void initiateGlobalVariables() {
		/* player */
		String tempName;
		if(Gdx.app.getPreferences("preferences").contains("player") && 
				!(tempName = Gdx.app.getPreferences("preferences").getString("player")).equals("")) {
			this.player = new Karakter(tempName);
		} else {
			this.setPlayer(new Karakter("alta"));
		}
		
		/* score */
		String tempScore;
		if(Gdx.app.getPreferences("preferences").contains("score") && 
				!(tempScore = Gdx.app.getPreferences("preferences").getString("score")).equals("")) {
			this.score = Integer.parseInt(tempScore);
		} else {
			this.setScore(0);
		}
		
		/* sfx */
		if(Gdx.app.getPreferences("preferences").contains("soundOn")) {
			this.soundOn = Gdx.app.getPreferences("preferences").getBoolean("soundOn");
		} else {
			this.setSoundOn(true);
		}
		
		/* music */
		if(Gdx.app.getPreferences("preferences").contains("musicOn")) {
			this.musicOn = Gdx.app.getPreferences("preferences").getBoolean("musicOn");
		} else {
			this.setMusicOn(true);
		}
		
		/* modul 1 scene 3 */
		if(Gdx.app.getPreferences("preferences").contains("modul1Scene3Done")) {
			this.modul1Scene3Done = Gdx.app.getPreferences("preferences").getBoolean("modul1Scene3Done");
		} else {
			this.setModul1Scene3Done(false);
		}
		
		
		/* modul 1 scene 4 */
		if(Gdx.app.getPreferences("preferences").contains("modul1Scene4Done")) {
			this.modul1Scene4Done = Gdx.app.getPreferences("preferences").getBoolean("modul1Scene4Done");
		} else {
			this.setModul1Scene4Done(false);
		}
		
		/* modul 1 scene 5 */
		if(Gdx.app.getPreferences("preferences").contains("modul1Scene5Done")) {
			this.modul1Scene5Done = Gdx.app.getPreferences("preferences").getBoolean("modul1Scene5Done");
		} else {
			this.setModul1Scene5Done(false);
		}
		
		/* modul 1 scene 6 */
		if(Gdx.app.getPreferences("preferences").contains("modul1Scene6Done")) {
			this.modul1Scene6Done = Gdx.app.getPreferences("preferences").getBoolean("modul1Scene6Done");
		} else {
			this.setModul1Scene6Done(false);
		}
	}
	
	/**
	 * Cek apakah bahan sayap sudah dipilih
	 * @return TRUE if bahan sayap sudah dipilih
	 */
	public boolean isModul1Scene3Done() {
		return modul1Scene3Done;
	}

	/**
	 * Cek apakah sayap sudah terpasang
	 * @return TRUE if sayap sudah terpasang
	 */
	public boolean isModul1Scene4Done() {
		return modul1Scene4Done;
	}

	/**
	 * Cek apakah spion sudah terpasang
	 * @return TRUE if spion sudah terpasang
	 */
	public boolean isModul1Scene5Done() {
		return modul1Scene5Done;
	}

	public boolean isMusicOn() {
		return musicOn;
	}

	public boolean isSoundOn() {
		return soundOn;
	}

	public void setModul1Scene3Done(boolean modul1Scene3Done) {
		this.modul1Scene3Done = modul1Scene3Done;
		prefs.putBoolean("modul1Scene3Done", modul1Scene3Done);
		prefs.flush();
	}

	public void setModul1Scene4Done(boolean modul1Scene4Done) {
		this.modul1Scene4Done = modul1Scene4Done;
		prefs.putBoolean("modul1Scene4Done", modul1Scene4Done);
		prefs.flush();
	}

	public void setModul1Scene5Done(boolean modul1Scene5Done) {
		this.modul1Scene5Done = modul1Scene5Done;
		prefs.putBoolean("modul1Scene5Done", modul1Scene5Done);
		prefs.flush();
	}

	public void setMusicOn(boolean musicOn) {
		this.musicOn = musicOn;
		prefs.putBoolean("musicOn", this.musicOn);
		prefs.flush();
	}

	public void setPlayer(Karakter player) {
		this.player = player;
		prefs.putString("player", player.getName());
		prefs.flush();
	}

	public void setPlayer(String player) {
		this.setPlayer(new Karakter(player));
	}

	public void setScore(int score) {
		this.score = score;
		prefs.putString("score", score + "");
		prefs.flush();
	}

	public void setSoundOn(boolean soundOn) {
		this.soundOn = soundOn;
		prefs.putBoolean("soundOn", this.soundOn);
		prefs.flush();
	}

	public boolean isModul1Scene6Done() {
		return modul1Scene6Done;
	}

	public void setModul1Scene6Done(boolean modul1Scene6Done) {
		this.modul1Scene6Done = modul1Scene6Done;
		prefs.putBoolean("modul1Scene6Done", modul1Scene6Done);
		prefs.flush();
	}
}
