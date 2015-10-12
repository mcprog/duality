package mcprog.duality.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import mcprog.duality.Duality;
import mcprog.duality.EntityFactory;
import mcprog.duality.GameWorld;
import mcprog.duality.components.Mappers;
import mcprog.duality.components.TextureComponent;
import mcprog.duality.components.TransformComponent;
import mcprog.duality.core.Player;
import mcprog.duality.core.Tile;
import mcprog.duality.library.Reference;
import mcprog.duality.systems.AnimationSystem;
import mcprog.duality.systems.BackgroundSystem;
import mcprog.duality.systems.CameraSystem;
import mcprog.duality.systems.PhysicsSystem;
import mcprog.duality.systems.RenderingSystem;
import mcprog.duality.systems.StateSystem;
import mcprog.duality.utility.LogHelper;

/**
 * Created by mcprog on 10/6/2015.
 */
public class GameScreen extends ScreenAdapter {

    protected static final int GAME_READY = 0;
    protected static final int GAME_RUNNING = 1;
    protected static final int GAME_PAUSED = 2;

    protected Duality game;
    protected OrthographicCamera camera;
    protected Viewport viewport;
    protected int state;
    protected World physicsWorld;

    private Texture bg;
    private TextureRegion oreTile;
    private TextureRegion testPlayerTex;
    private Tile[][] tiles;
    private Player player;
    private Box2DDebugRenderer box2DDebugRenderer;

    public GameScreen (Duality game) {
        this.game = game;


        bg = new Texture(Gdx.files.internal("images/gemma-arterton.jpg"));
        oreTile = new TextureRegion(new Texture(Gdx.files.internal("images/ore-tiles.png")), 0, 0, 16, 16);
        testPlayerTex = new TextureRegion(new Texture(Gdx.files.internal("images/player.png")), 0, 0, 24, 32);
        camera = new OrthographicCamera();
        viewport = new FillViewport(Reference.W_WIDTH / 48f, Reference.W_HEIGHT / 48f, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        physicsWorld = new World(new Vector2(0, -9.81f), true);
        box2DDebugRenderer = new Box2DDebugRenderer();

    }

    private void loadTile () {
        tiles = new Tile[64][64];
        for (int i = 0; i < 64; ++i) {
            for (int j = 0; j < 64; ++j) {
                boolean spawn;
                if (j % 5 < 2) {
                    spawn = Math.random() >= .25f;
                } else {
                    spawn = Math.random() >= 95f;
                }

                if (i % 10 < Math.random() * 4 + 1) {
                    spawn = Math.random() >= .95f;
                }
                if (spawn) {
                    tiles[i][j] = new Tile(oreTile, i * 16f * Reference.PIXELS_TO_METERS, j * 16f * Reference.PIXELS_TO_METERS);
                    tiles[i][j].create(physicsWorld);
                }

            }

        }

    }

    private void loadPlayer () {
        player = new Player(testPlayerTex, 2, 3);
        player.create(physicsWorld);
    }

    @Override
    public void show() {
        loadTile();
        loadPlayer();
        Gdx.input.setInputProcessor(player);
        Gdx.app.log(LogHelper.getClassyTag(this), "Game screen show done");
    }



    protected void update (float delta) {
        camera.position.set(player.getPosition(), 0);
        camera.update();
        player.update(delta);
        physicsWorld.step(1/60f, 8, 3);


    }

    protected void draw () {
        GL20 gl20 = Gdx.gl;
        gl20.glClearColor(0, 0, 0, 1);
        gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.gameBatch.setProjectionMatrix(camera.combined);
        game.gameBatch.begin();
        game.gameBatch.draw(bg, 0, 0);
        for (Tile[] tArr : tiles) {
            for (Tile t : tArr) {
                if (t != null) {
                    t.draw(game.gameBatch);
                }
            }

        }
        player.draw(game.gameBatch);
        game.gameBatch.end();
        box2DDebugRenderer.render(physicsWorld, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }
}
