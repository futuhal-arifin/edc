package id.ac.ui.edoocatia.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

//import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.DialogScreen;

public class DialogController {

	private DialogScreen screen;

	public DialogController(DialogScreen screen) {
		Gdx.input.setCatchBackKey(false);
		this.screen = screen;
	}

	public void processInput() {
		// kalau klik waktu baris dialog lagi jalan, teks baris dialognyanya
		// langsung dimunculin semua
		if (Gdx.input.justTouched()) {
			screen.setScreenJustTouched(true);
		}

		if (!Gdx.input.isTouched() && screen.isScreenJustTouched()) {
			screen.setScreenJustTouched(false);
			if (screen.isDialogLineEnded()) {
				// System.out.print(" isDialogLineEnded");
				this.showNextDialogLine();
			} else {
				// System.out.print(" NOT isDialogLineEnded");
				screen.endDialogLine();
			}
		}

		// kalau teks baris dialog sudah tampil tampil semua, ada jeda waktu
		// sebelum baris selanjutnya muncul
		if (screen.isDialogLineEnded()) {
			long currentTime = TimeUtils.millis();
			long startTime = screen.getStartTime();
			if (screen.getStartTime() > 0 && !screen.isDialogEnded()
					&& currentTime > (startTime + screen.getDialogTimeBreak())) {
				this.showNextDialogLine();
			}
		}
	}

	private void showNextDialogLine() {
		if (screen.isDialogTurnEnded()) { // ganti giliran karakter yg ngomong
			screen.nextDialogTurn();
		} else {
			screen.nextDialogLine(); // lanjut baris selanjutnya (karakternya
										// tetap)
		}
	}

}
