package com.mcprog.duality.test.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mcprog.duality.library.Constants;

/**
 * Created by mcprog on 4/25/2015.
 */
public class CameraTestScreen extends ScreenAdapter implements InputProcessor {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private static final int MIN_DIM = 100;
    private Sprite player;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private Sprite bg;

    public CameraTestScreen () {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        player = new Sprite(new Texture("badlogic.jpg"));
        //player.setCenter(0, 0);
        player.setSize(25, 25);

        bg = new Sprite(new Texture("keira-knightley-bg.jpg"));
        //bg.setSize(Constants.RAW_WIDTH, Constants.RAW_HEIGHT);
        bg.setCenter(0, 0);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (left && !right) {
            player.setX(player.getX() - 1);

        } else if (right && !left) {
            player.setX(player.getX() + 1);

        }

        if (up && !down) {
            player.setY(player.getY() + 1);

        } else if (down && !up) {
            player.setY(player.getY() - 1);

        }

        if (camera.position.x > player.getX() + (player.getWidth()) * 2) {
            camera.position.x -= 1;
            System.out.println("Cam x: " + camera.position.x);
            System.out.println("plry x: " + player.getX());
        } else if (camera.position.x < player.getX() - (player.getWidth()) * 2) {
            camera.position.x += 1;
        }
        if (camera.position.y > player.getY() + (player.getHeight()) * 1) {
            camera.position.y -= 1;
        } else if (camera.position.y < player.getY() - (player.getHeight() * 1)) {
            camera.position.y += 1;
        }
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        bg.draw(batch);
        player.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
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

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A) {

            left = true;
        }
        if (keycode == Input.Keys.D) {

            right = true;
        }
        if (keycode == Input.Keys.W) {

            up = true;
        }
        if (keycode == Input.Keys.S) {

            down = true;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A) {

            left = false;
        }
        if (keycode == Input.Keys.D) {

            right = false;
        }
        if (keycode == Input.Keys.W) {

            up = false;
        }
        if (keycode == Input.Keys.S) {

            down = false;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
