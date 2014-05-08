package id.ac.ui.edoocatia.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Merepresentasikan satu karakter
 * @author inassjunus
 *
 */

public class Karakter {
	private String karakterName;
	//private Texture karakterDialogTexture;
	//private Texture karakterWinTexture;
	//private Texture karakterLoseTexture;
	//private Texture karakterMemaluTexture;
	private String karakterDialogTexturePath;
	private String karakterWinTexturePath;
	private String karakterLoseTexturePath;
	private String karakterMemaluTexturePath;
	
	public Karakter(String karakterName) {
		if(karakterName.equals("player")) {
			String tempName= Gdx.app.getPreferences("preferences").getString("player");
			if(tempName !=null && !tempName.equals("")) {
				this.karakterName = tempName;
			} else {
				this.karakterName = "alta";
			}
		} else {
			this.karakterName = karakterName;
		}
		
		if(this.karakterName.equals("professor")) {
			this.karakterDialogTexturePath = "data/images/characters/"+this.karakterName+".png";
		} else {
			this.setKarakterTexture();
		}
	}
	
	private void setKarakterTexture() {
		this.karakterDialogTexturePath = "data/images/characters/"+this.karakterName+".png";
		this.setKarakterWinTexturePath("data/images/characters/"+this.karakterName+"_happy.png");
		this.setKarakterLoseTexturePath("data/images/characters/"+this.karakterName+"_sad.png");
		this.setKarakterMemaluTexturePath("data/images/modul-1/animasi/"+this.karakterName+"/"+this.karakterName+"_memalu.png");
	}

	public String getKarakterDialogTexturePath() {
		return this.karakterDialogTexturePath;
	}
	
	public Texture getKarakterDialogTexture(String karakterName) {
		return new Texture(Gdx.files.internal("data/images/characters/"+karakterName+".png"));
	}

	public String getName() {
		return this.karakterName;
	}

	public String getKarakterWinTexturePath() {
		return karakterWinTexturePath;
	}

	public void setKarakterWinTexturePath(String path) {
		this.karakterWinTexturePath = path;
	}

	public String getKarakterLoseTexturePath() {
		return karakterLoseTexturePath;
	}

	public void setKarakterLoseTexturePath(String path) {
		this.karakterLoseTexturePath = path;
	}

	public String getKarakterMemaluTexturePath() {
		return karakterMemaluTexturePath;
	}

	public void setKarakterMemaluTexturePath(String path) {
		this.karakterMemaluTexturePath = path;
	}
}
