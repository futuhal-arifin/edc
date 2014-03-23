package id.ac.ui.edoocatia;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Modul1Screen implements Screen {
    final Edoocatia game;

    Texture EdoocatiaImage;
    Texture bucketImage;
    Sound EdoocatiaSound;
    Music rainMusic;
    OrthographicCamera camera;
    Rectangle bucket;
    Array<Rectangle> rainEdoocatias;
    long lastEdoocatiaTime;
    int EdoocatiasGathered;

    public Modul1Screen(final Edoocatia gam) {
        this.game = gam;

        // load the images for the Edoocatialet and the bucket, 64x64 pixels each
        EdoocatiaImage = new Texture(Gdx.files.internal("droplet.png"));
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));

        // load the Edoocatia sound effect and the rain background "music"
        EdoocatiaSound = Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        rainMusic.setLooping(true);

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
       

        // create a Rectangle to logically represent the bucket
        bucket = new Rectangle();
        bucket.x = 800 / 2 - 64 / 2; // center the bucket horizontally
        bucket.y = 20; // bottom left corner of the bucket is 20 pixels above
                        // the bottom screen edge
        bucket.width = 64;
        bucket.height = 64;

        // create the rainEdoocatias array and spawn the first rainEdoocatia
        rainEdoocatias = new Array<Rectangle>();
        spawnRainEdoocatia();

    }

    private void spawnRainEdoocatia() {
        Rectangle rainEdoocatia = new Rectangle();
        rainEdoocatia.x = MathUtils.random(0, 800 - 64);
        rainEdoocatia.y = 480;
        rainEdoocatia.width = 64;
        rainEdoocatia.height = 64;
        rainEdoocatias.add(rainEdoocatia);
        lastEdoocatiaTime = TimeUtils.nanoTime();
    }

    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        camera.update();
/*
        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the bucket and
        // all Edoocatias
        game.batch.begin();
        game.font.draw(game.batch, "Edoocatias Collected: " + EdoocatiasGathered, 0, 480);
        game.batch.draw(bucketImage, bucket.x, bucket.y);
        for (Rectangle rainEdoocatia : rainEdoocatias) {
            game.batch.draw(EdoocatiaImage, rainEdoocatia.x, rainEdoocatia.y);
        }
        game.batch.end();
*/
        // process user input
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.x = touchPos.x - 64 / 2;
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            bucket.x += 200 * Gdx.graphics.getDeltaTime();

        // make sure the bucket stays within the screen bounds
        if (bucket.x < 0)
            bucket.x = 0;
        if (bucket.x > 800 - 64)
            bucket.x = 800 - 64;

        // check if we need to create a new rainEdoocatia
        if (TimeUtils.nanoTime() - lastEdoocatiaTime > 1000000000)
            spawnRainEdoocatia();

        // move the rainEdoocatias, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the later case we increase the 
        // value our Edoocatias counter and add a sound effect.
        Iterator<Rectangle> iter = rainEdoocatias.iterator();
        while (iter.hasNext()) {
            Rectangle rainEdoocatia = iter.next();
            rainEdoocatia.y -= 200 * Gdx.graphics.getDeltaTime();
            if (rainEdoocatia.y + 64 < 0)
                iter.remove();
            if (rainEdoocatia.overlaps(bucket)) {
                EdoocatiasGathered++;
                EdoocatiaSound.play();
                iter.remove();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        rainMusic.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        EdoocatiaImage.dispose();
        bucketImage.dispose();
        EdoocatiaSound.dispose();
        rainMusic.dispose();
    }

}