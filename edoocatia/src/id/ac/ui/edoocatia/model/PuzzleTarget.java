package id.ac.ui.edoocatia.model;

import com.badlogic.gdx.math.Rectangle;

public class PuzzleTarget {
	Rectangle bounds;
	
	public PuzzleTarget(Rectangle bounds){
		this.bounds = bounds;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
}
