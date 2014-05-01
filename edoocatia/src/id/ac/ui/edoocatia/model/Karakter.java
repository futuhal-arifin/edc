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
	private Texture karakterDialogTexture;
	private Texture karakterWinTexture;
	private Texture karakterLoseTexture;
	private Texture karakterMemaluTexture;
	
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
			this.karakterDialogTexture = new Texture(Gdx.files.internal("data/images/characters/"+this.karakterName+".png"));
		} else {
			this.setKarakterTexture();
		}
		
		//this.setKarakterTexture();
		
	}
	
	private void setKarakterTexture() {
		this.karakterDialogTexture = new Texture(Gdx.files.internal("data/images/characters/"+this.karakterName+".png"));
		this.setKarakterWinTexture(new Texture(Gdx.files.internal("data/images/characters/"+this.karakterName+"_happy.png")));
		this.setKarakterLoseTexture(new Texture(Gdx.files.internal("data/images/characters/"+this.karakterName+"_sad.png")));
		this.setKarakterMemaluTexture(new Texture(Gdx.files.internal("data/images/modul-1/animasi/"+this.karakterName+"/"+this.karakterName+"_memalu.png")));
	}

	public Texture getKarakterDialogTexture() {
		return this.karakterDialogTexture;
	}
	
	public Texture getKarakterDialogTexture(String karakterName) {
		return new Texture(Gdx.files.internal("data/images/characters/"+karakterName+".png"));
	}

	public String getName() {
		return this.karakterName;
	}

	public Texture getKarakterWinTexture() {
		return karakterWinTexture;
	}

	public void setKarakterWinTexture(Texture karakterWinTexture) {
		this.karakterWinTexture = karakterWinTexture;
	}

	public Texture getKarakterLoseTexture() {
		return karakterLoseTexture;
	}

	public void setKarakterLoseTexture(Texture karakterLoseTexture) {
		this.karakterLoseTexture = karakterLoseTexture;
	}

	public Texture getKarakterMemaluTexture() {
		return karakterMemaluTexture;
	}

	public void setKarakterMemaluTexture(Texture karakterMemaluTexture) {
		this.karakterMemaluTexture = karakterMemaluTexture;
	}
}
