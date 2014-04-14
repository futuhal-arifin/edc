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
			// masukin win lose texture
		} else if (this.karakterName.equals("alta")) {
			// masukin win lose texture
		} else if (this.karakterName.equals("azmo")) {
			// masukin win lose texture
		} else if (this.karakterName.equals("momo")) {
			// masukin win lose texture
		} else if (this.karakterName.equals("tsarina")) {
			// masukin win lose texture
		} 
		
		this.karakterDialogTexture = new Texture(Gdx.files.internal("data/images/characters/"+this.karakterName+".png"));
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
}
