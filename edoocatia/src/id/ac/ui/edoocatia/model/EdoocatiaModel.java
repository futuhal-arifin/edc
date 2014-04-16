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
	
	
	private Preferences prefs;
	
	public EdoocatiaModel() {
		prefs = Gdx.app.getPreferences("preferences");
		this.initiateGlobalVariables();
		//this.debug();
	}
	
	public Karakter getPlayer() {
		return player;
	}

	public int getScore() {
		return score;
	}
	
	private void debug() {
		this.setPlayer(new Karakter("alta"));
		this.setModul1Scene3Done(true);
		this.setModul1Scene5Done(false);
	}

	/** initialize setting, game progress & player info */
	private void initiateGlobalVariables() {
		/* player */
		String tempName= Gdx.app.getPreferences("preferences").getString("player");
		if(tempName !=null && !tempName.equals("")) {
			this.player = new Karakter(tempName);
		} else {
			this.setPlayer(new Karakter("alta"));
		}
		
		/* score */
		String tempScore = Gdx.app.getPreferences("preferences").getString("score");
		if(tempScore !=null && !tempScore.equals("")) {
			this.score = Integer.parseInt(tempScore);
		} else {
			this.setScore(0);
		}
		
		this.soundOn = Gdx.app.getPreferences("preferences").getBoolean("soundOn");
		
		this.musicOn = Gdx.app.getPreferences("preferences").getBoolean("musicOn");
		
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
	}
	
	public boolean isModul1Scene3Done() {
		return modul1Scene3Done;
	}

	public boolean isModul1Scene4Done() {
		return modul1Scene4Done;
	}

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
}
