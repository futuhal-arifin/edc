package id.ac.ui.edoocatia.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

//import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.screen.DialogScreen;

public class DialogController {
	//private Edoocatia app;
	private DialogScreen screen;
	
	//private OrthographicCamera cam;
	//private Rectangle viewport;
	
	public DialogController(DialogScreen screen) {
		Gdx.input.setCatchBackKey(false);
		this.screen = screen;
		//app = screen.getApp();
		//cam = screen.getCam();
		//viewport = screen.getViewport();
	}
	
	public void processInput() {
		// kalau klik waktu baris dialog lagi jalan, teks baris dialognyanya langsung dimunculin semua
		if(Gdx.input.justTouched()) {
			screen.endDialogLine();
		}
		
		// kalau teks baris dialog sudah tampil tampil semua, ada jeda waktu sebelum baris selanjutnya muncul
		if(screen.isDialogLineEnded()) {
			long currentTime  = TimeUtils.millis();
			long startTime = screen.getStartTime();
			if(screen.getStartTime() > 0 && !screen.isDialogEnded() && currentTime > (startTime + screen.getDialogTimeBreak())) {
				if(screen.isDialogTurnEnded()) { // ganti giliran karakter yg ngomong
					screen.nextDialogTurn();
				} else {
					screen.nextDialogLine(); // lanjut baris selanjutnya (karakternya tetap)
				}
			}
		}
	}
	
}
