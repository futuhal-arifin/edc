package id.ac.ui.edoocatia.screen;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.TimeUtils;

import id.ac.ui.edoocatia.Edoocatia;
import id.ac.ui.edoocatia.controller.DialogController;
import id.ac.ui.edoocatia.model.CharacterDialog;
import id.ac.ui.edoocatia.model.Karakter;
import id.ac.ui.edoocatia.util.AbstractScreen;

/**
 * Merepresentasikan bagian dialog di suatu halaman
 * Harus di extends oleh halaman yang mengandung dialog
 * 
 * @author inassjunus
 *
 */
public class DialogScreen extends AbstractScreen {
	
	private DialogController controller;
	
	protected Texture dialogBackground;
	protected float dialogBackgroundXPosition;
	protected float dialogBackgroundYPosition;
	
	protected Texture currentKarakterTexture;
	private float karakterXLeftPosition;
	private float karakterYLeftPosition;
	
	private float karakterXRightPosition;
	private float karakterYRightPosition;
	
	private float textXPosition;
	private float textYPosition;
	private float lineLength = 800;
	private BitmapFont font;
	private float scrollIndex;
	
	/** dokumen txt berisi dialog */
	private FileHandle dialogNaration;
	
	private boolean showDialog;

	private ArrayList<CharacterDialog> dialogPerKarakter;
	
	// turn = giliran karakter 'bicara', terdiri dari >= 1 line
	// line = 1 baris dialog, bagian dari turn
	
	private int currentTurn;
	
	private boolean isAllDialogEnded;
	
	private boolean isDialogLineEnded;
	private boolean isDialogTurnEnded;
	
	private long startTime;
	
	/** jeda antar baris dialog */
	private final long DIALOG_TIME_BREAK = 3000;
	
	private CharacterDialog currentDialog;
	
	private boolean screenJustTouched;
	
	public DialogScreen(Edoocatia app) {
		super(app);
		this.setScreenJustTouched(false);
		controller = new DialogController(this);
	}
	
	private void setAllDialogVariables() {
		dialogPerKarakter = new ArrayList<CharacterDialog>();
		this.setShowDialog(true);
		currentTurn = 0;
		setDialogEnded(false);
		this.setDialogLineVariables();
	}

	public void endDialogLine() {
		this.scrollIndex = this.currentDialog.getCurrentDialogLine().length();
		startTime = TimeUtils.millis();
		this.setDialogLineEnded(true);
		if (currentDialog.isEndTurn()) {
			this.setDialogTurnEnded(true);
		}
	}

	public Texture getDialogBackground() {
		return this.dialogBackground;
	}
	
	public long getDialogTimeBreak() {
		return DIALOG_TIME_BREAK;
	}
	
	public float getLineLength() {
		return lineLength;
	}
	
	public long getStartTime() {
		return startTime;
	}
	
	public boolean isDialogEnded() {
		return isAllDialogEnded || (currentDialog.isEndTurn() && (currentTurn >= this.dialogPerKarakter.size() - 1) && this.scrollIndex >= this.currentDialog.getCurrentDialogLine().length());
	}
	
	public boolean isDialogLineEnded() {
		return isDialogLineEnded;
	}
	
	public boolean isDialogTurnEnded() {
		return isDialogTurnEnded;
	}
	
	public void nextDialogLine() {
		if(!this.isDialogEnded()) {
			this.currentDialog.incrementCurrentDialogLineNumber();
			this.setDialogLineVariables();
		}
	}

	public void nextDialogTurn() {
		if(!this.isDialogEnded()) {
			this.currentTurn++;
			this.setDialogLineVariables();
		}
	}
	
	public void setDialogLineVariables() {
		this.resetScrollIndex();
		this.setDialogTurnEnded(false);
		this.setDialogLineEnded(false);
		this.resetStartTime();
	}

	public void render(float delta) {
		batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
			if(this.showDialog) {
				if(this.dialogBackground != null) {
					batcher.draw(this.dialogBackground, 
							this.dialogBackgroundXPosition, 
							this.dialogBackgroundYPosition);
				}
				//if(!this.isDialogEnded())
				currentDialog = this.dialogPerKarakter.get(currentTurn);
				currentKarakterTexture = currentDialog.getKarakter().getKarakterDialogTexture();
				if(currentDialog.isPositionOnTheLeft()) {
					batcher.draw(currentKarakterTexture, 
							this.karakterXLeftPosition, 
							this.karakterYLeftPosition);
				} else {
					batcher.draw(currentKarakterTexture, 
							this.karakterXRightPosition - currentKarakterTexture.getWidth(), 
							this.karakterYRightPosition);
				}
				
				String currentShownText = currentDialog.getCurrentDialogLine();
				font.drawWrapped(batcher, currentShownText.substring(0, (int)scrollIndex), 
						this.textXPosition, this.textYPosition, this.lineLength);

				if(scrollIndex < currentShownText.length()){
					scrollIndex += 0.5;
				} 
				else {	
					if(startTime < 0) {
						this.endDialogLine();
						if (this.isDialogEnded()) {
							this.setDialogEnded(true);
						} 
					}
				}
			}
			
		batcher.end();
		
		controller.processInput();
	}
	
	public void resetScrollIndex() {
		this.scrollIndex = 0;
	}
	
	public void resetStartTime() {
		this.startTime = -1;
	}
	
	public void setDialogBackground(String path) {
		this.dialogBackground = new Texture(Gdx.files.internal(path));
	}
	
	public void setDialogBackgroundPosition(float x, float y) {
		this.dialogBackgroundXPosition = x;
		this.dialogBackgroundYPosition = y;
	}

	public void setDialogEnded(boolean isDialogEnded) {
		this.isAllDialogEnded = isDialogEnded;
	}

	public void setDialogLineEnded(boolean isDialogLineEnded) {
		this.isDialogLineEnded = isDialogLineEnded;
	}

	@SuppressWarnings("unchecked")
	public void setDialogNaration(String path) {
		this.setAllDialogVariables();
		this.dialogNaration = Gdx.files.internal(path);
		String data = dialogNaration.readString();
		StringTokenizer st = new StringTokenizer(data, System.getProperty("line.separator"));
		String in;
		String karakter = "";
		boolean position = true;
		ArrayList<String> tempDialogText = new ArrayList<String>();
		while(st.hasMoreTokens()) {
			in = st.nextToken().trim();
			if(in.substring(0, 1).equals(":")) {
				if (!karakter.equals("") && !tempDialogText.isEmpty()) {
					this.dialogPerKarakter.add(new CharacterDialog(position, new Karakter(karakter), (ArrayList<String>) tempDialogText.clone()));
					tempDialogText.clear();
				}
				karakter = in.substring(1);
				if(Integer.parseInt(st.nextToken().trim()) == 1) {
					position = true;
				}
				else {
					position = false;
				}
			} else {
				tempDialogText.add(in);
			}
		}
		if (!karakter.equals("") && !tempDialogText.isEmpty()) {
			this.dialogPerKarakter.add(new CharacterDialog(position, new Karakter(karakter), (ArrayList<String>) tempDialogText.clone()));
			tempDialogText.clear();
		}
	}

	public void setDialogTurnEnded(boolean isDialogTurnEnded) {
		this.isDialogTurnEnded = isDialogTurnEnded;
	}

	public void setFont(String fontPath, String imageFontPath) {
		this.font = new BitmapFont(
				Gdx.files.internal(fontPath),
				Gdx.files.internal(imageFontPath),
				false);
				
	}

	public void setKarakterLeftPosition(float x, float y) {
		this.karakterXLeftPosition = x;
		this.karakterYLeftPosition = y;
	}

	public void setKarakterRightPosition(float x, float y) {
		this.karakterXRightPosition = x;
		this.karakterYRightPosition = y;
	}

	public void setLineLength(float lineLength) {
		this.lineLength = lineLength;
	}

	public void setTextPosition(float x, float y) {
		this.textXPosition = x;
		this.textYPosition = y;
	}

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}

	public boolean isScreenJustTouched() {
		return screenJustTouched;
	}

	public void setScreenJustTouched(boolean screenJustTouched) {
		this.screenJustTouched = screenJustTouched;
	}

	@Override
	public void dispose() {
		this.font.dispose();
		this.dialogBackground.dispose();
		this.currentKarakterTexture.dispose();
		super.dispose();
	}
}
