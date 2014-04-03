package id.ac.ui.edoocatia.model;

import java.util.ArrayList;

/**
 * Merepresentasikan satu giliran dialog untuk satu karakter
 * @author inassjunus
 *
 */

public class CharacterDialog {
	/** karakternya bisa di kiri atau di kanan */
	private boolean isPositionOnTheLeft;
	private Karakter karakter;
	private ArrayList<String> narasi;
	private int currentDialogLineNumber;
	
	public CharacterDialog(boolean pos, Karakter karakter, ArrayList<String> narasi) {
		this.setPositionOnTheLeft(pos);
		this.karakter = karakter;
		this.narasi = narasi;
		this.currentDialogLineNumber = 0;
	}
	
	public Karakter getKarakter() {
		return karakter;
	}

	public ArrayList<String> getNarasi() {
		return narasi;
	}
	
	public int getDialogLineNumber() {
		return this.narasi.size();
	}
	
	public String getCurrentDialogLine() {
		return this.narasi.get(this.currentDialogLineNumber);
	}
	
	public void incrementCurrentDialogLineNumber() {
		this.currentDialogLineNumber++;
	}
	
	/**
	 * Menandai giliran 'bicara' karakter sudah selesai
	 * @return
	 */
	public boolean isEndTurn() {
		if(this.currentDialogLineNumber == this.getDialogLineNumber() - 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPositionOnTheLeft() {
		return isPositionOnTheLeft;
	}

	public void setPositionOnTheLeft(boolean isPositionOnTheLeft) {
		this.isPositionOnTheLeft = isPositionOnTheLeft;
	}
}
