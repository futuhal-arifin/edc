package id.ac.ui.edoocatia.util;

import id.ac.ui.edoocatia.Edoocatia;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractScreen implements Screen {
private Edoocatia edoocatiaApp;
	
	public final float VIRTUAL_WIDTH = 1280.0f;
	public final float VIRTUAL_HEIGHT = 800.0f;
	public final float ASPECT_RATIO = VIRTUAL_WIDTH/VIRTUAL_HEIGHT;
	protected int width, height;
	protected Rectangle viewport;
	
	protected OrthographicCamera cam;
	protected SpriteBatch batcher;
	private int screenType;
	
	//buat ngerender rectangle bounds kalo mau liat boundsnya
	protected ShapeRenderer debugRenderer = new ShapeRenderer();
	
	public AbstractScreen(Edoocatia app){
		this.edoocatiaApp = app;
		
		width = (int)VIRTUAL_WIDTH;
		height = (int)VIRTUAL_HEIGHT;
		
		cam = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		cam.position.set(VIRTUAL_WIDTH / 2, VIRTUAL_HEIGHT / 2, 0);
		
		batcher = new SpriteBatch();
		
		viewport = new Rectangle(0f, 0f, (float)width, (float)height);
	}
	
	public int getScreenType() {
		return screenType;
	}
	
	public void setScreenType(int type) {
		screenType = type;
	}
	
	public Edoocatia getApp(){
		return this.edoocatiaApp;
	}
	
	public OrthographicCamera getCam(){
		return cam;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Rectangle getViewport(){
		return viewport;
	}
	
	//rectangle boundsnya digambar kalo mau debug==true
	protected void drawDebug(Rectangle[] bounds, boolean[] status){
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Line);
			for(int idx = 0; idx < bounds.length; idx++) {
				if(status[idx]){
					debugRenderer.setColor(new Color(1, 1, 0, 1));
				}
				else{
					debugRenderer.setColor(new Color(1, 0, 0, 1));
				}
				debugRenderer.rect(bounds[idx].x, bounds[idx].y,
						bounds[idx].width, bounds[idx].height);
			}
			
		debugRenderer.end();
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		//System.out.printf("resize called with width: %d and height: %d%n", width, height);
		this.width = width; 
		this.height = height;
		
		float screenAspectRatio = width/height;
		float scale = 1f;
		Vector2 crop = new Vector2(0f, 0f);
		
        if(screenAspectRatio > ASPECT_RATIO)
        {
            scale = (float)height/(float)VIRTUAL_HEIGHT;
            crop.x = (width - VIRTUAL_WIDTH*scale)/2f;
        }
        else if(screenAspectRatio < ASPECT_RATIO)
        {
            scale = (float)width/(float)VIRTUAL_WIDTH;
            crop.y = (height - VIRTUAL_HEIGHT*scale)/2f;
        }
        else
        {
            scale = (float)width/(float)VIRTUAL_WIDTH;
        }
 
        float w = (float)VIRTUAL_WIDTH*scale;
        float h = (float)VIRTUAL_HEIGHT*scale;
        viewport.set(crop.x, crop.y, w, h);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		//System.out.println("show called");
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		//com.badlogic.gdx.Screen.dispose();
	}
}
