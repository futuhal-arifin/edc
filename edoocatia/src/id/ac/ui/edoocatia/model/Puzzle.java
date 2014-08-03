package id.ac.ui.edoocatia.model;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

public class Puzzle{
	
	public static final float TIME = 91f;
	
	private PuzzlePiece[][] pieces;
	
	private Texture background;
	private float timeLeft;
	private boolean gameOver;
	
	private Random rand;

	private boolean showSolved;

	public float showSolvedTime;

	private Texture solvedTexture;
	
	public Puzzle(String nama, int tipe){
		rand = new Random();
		timeLeft = TIME;
		gameOver = false;
		showSolved = false;
		showSolvedTime = 2f;
	}
	
	public void setPieces(PuzzlePiece[][] pieces){
		this.pieces = pieces;
	}
	
	public void setBackground(Texture background){
		this.background = background;
	}
	
	public void setSolvedTexture(Texture solved){
		solvedTexture = solved;
	}
	
	public void decTime(float delta){
		timeLeft -= delta;
	}
	
	public void gameOver(){
		gameOver = true;
	}
	
	public PuzzlePiece[][] getPieces(){
		return pieces;
	}
	
	public PuzzlePiece getPiece(int i, int j){
		return pieces[i][j];
	}
	
	public Texture getBackground(){
		return background;
	}
	
	public float getTimeLeft(){
		return timeLeft;
	}
	
	public boolean isGameOver(){
		return gameOver;
	}
	
	public void reinit(){
		for (int i=0; i<pieces.length; i++){
			for (int j=0; j<pieces[i].length; j++){
				pieces[i][j].reinit();
			}
		}
		randomize();
		timeLeft = TIME;
		gameOver = false;
		showSolved = false;
		showSolvedTime = 2f;
	}

	public void randomize() {
		
		float x_offset = 600;
		float y_offset = 10;
		for (int i=0; i<pieces.length; i++){
			for (int j=0; j<pieces[i].length; j++){
				float x = 180*rand.nextFloat();
				float y = 280*rand.nextFloat();
				pieces[i][j].setX(x_offset+x);
				pieces[i][j].setY(y_offset+y);
			}
		}
		
	}

	public void showSolved() {
		showSolved = true;
	}
	
	public boolean isShowingSolved(){
		return showSolved;
	}

	public Texture getSolvedTexture() {
		return solvedTexture;
	}
	
}
