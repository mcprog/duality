package com.mcprog.duality;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcprog.duality.library.Constants;

public class Duality extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    boolean full;
    OrthographicCamera camera;
    Sprite logo;
    private static final int WORLD_WIDTH = 100;
    private static final int WORLD_HEIGHT = 100;
    private static final int MIN_DIM = 50;
	
	@Override
	public void create () {
        camera = new OrthographicCamera(Constants.RAW_WIDTH, Constants.RAW_HEIGHT);

		batch = new SpriteBatch();

		img = new Texture("badlogic.jpg");
        logo = new Sprite(img);
        logo.setSize(50, 50);
        logo.setCenter(0, 0);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /*if (Gdx.input.justTouched()) {
            if (full) {
                System.out.println("tough touch to full");

                Gdx.graphics.setDisplayMode(1280, 720, full);
                full = false;
            } else {
                System.out.println("tough touch to windowed");
                Gdx.graphics.setDisplayMode(Constants.RAW_WIDTH, Constants.RAW_HEIGHT, full);
                full = true;
            }

        }*/
        batch.setProjectionMatrix(camera.combined);
		batch.begin();
		logo.draw(batch);
		batch.end();
	}

    @Override
    public void resize(int width, int height) {
        //Gdx.graphics.setDisplayMode(width, height, full);
        //logo.scale(width / camera.viewportWidth);
        if (width > height) {
            camera.viewportWidth = MIN_DIM * ((float)width/(float)height);
            camera.viewportHeight = MIN_DIM;
        } else {
            camera.viewportWidth = MIN_DIM;
            camera.viewportHeight = MIN_DIM * ((float)height/(float)width);
        }

        //camera.viewportHeight = height;
        camera.update();

    }
}
