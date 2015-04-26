package com.mcprog.duality.test.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.duality.library.Constants;
import com.mcprog.duality.world.Player;

/**
 * Created by mcprog on 4/25/2015.
 */
public class WorldTestScreen extends ScreenAdapter implements InputProcessor {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    private World world;
    private Player player;
    private Sprite bg;
    private float stateTime;
    private static final int MIN_DIM = 32;
    private boolean left;
    private boolean right;
    private boolean sit;
    private boolean roll;
    private boolean attack1;
    private float playerSpeed = 8;

    public WorldTestScreen () {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        debugRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void show() {
        stateTime = 0;
        Gdx.input.setInputProcessor(this);
        world = new World(Vector2.Zero, true);
        player = new Player(world, Vector2.Zero);
        /*player = new Sprite(new Texture("badlogic.jpg"));
        //player.setCenter(0, 0);
        player.setSize(25, 25);*/

        bg = new Sprite(new Texture("keira-knightley-bg.jpg"));
        bg.setSize(MIN_DIM * 2, MIN_DIM * Constants.RAW_HEIGHT / Constants.RAW_WIDTH * 2);
        bg.setCenter(0, 0);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += delta;

        if (left && !right) {
            player.moveLeft();
            if (attack1) {
                player.attackRight();
            }
            if (roll) {
                player.roll();
            } else {
                player.unroll();
            }

        } else if (right && !left) {
            player.moveRight();
            if (attack1) {
                player.attackRight();
            }
            if (roll) {
                player.roll();
            } else {
                player.unroll();
            }

        } else {
            player.stop();
            if (sit) {
                player.sit();
            } else if (roll) {
                player.roll();
            } else {
                player.unroll();
            }
        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        bg.draw(batch);
        batch.end();

        player.draw(stateTime, batch);
        debugRenderer.render(world, camera.combined);



        world.step(1/60f, 8, 3);

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
            sit = false;
        }
        if (keycode == Input.Keys.D) {

            right = true;
            sit = false;
        }
        if (keycode == Input.Keys.C) {
            sit = !sit;
        }
        if (keycode == Input.Keys.S) {
            roll = true;
        }
        if (keycode == Input.Keys.F) {
            attack1 = true;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A) {

            left = false;
            sit = false;
        }
        if (keycode == Input.Keys.D) {

            right = false;
            sit = false;
        }
        if (keycode == Input.Keys.S) {
            roll = false;
        }
        if (keycode == Input.Keys.UP) {
            attack1 = false;
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
