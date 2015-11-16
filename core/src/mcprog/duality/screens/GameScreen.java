package mcprog.duality.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import mcprog.duality.Duality;
import mcprog.duality.core.Collision;
import mcprog.duality.core.players.Magic;
import mcprog.duality.core.players.Player;
import mcprog.duality.core.Terrain;
import mcprog.duality.library.Reference;
import mcprog.duality.utility.KeyboardHandler;
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
    private TextureRegion testPlayerTex;
    private Player player;
    private Box2DDebugRenderer box2DDebugRenderer;
    private Terrain terrain;
    private SpriteBatch bgBatch;
    private Collision collision;

    public GameScreen (Duality game) {
        this.game = game;

        bgBatch = new SpriteBatch();
        bg = new Texture(Gdx.files.internal("images/pyramid.jpg"));
        testPlayerTex = new TextureRegion(new Texture(Gdx.files.internal("images/player.png")), 0, 0, 48, 32);
        camera = new OrthographicCamera();
        viewport = new FillViewport(Reference.W_WIDTH / 64f, Reference.W_HEIGHT / 64f, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        physicsWorld = new World(new Vector2(0, -9.81f), true);
        box2DDebugRenderer = new Box2DDebugRenderer();
        terrain = new Terrain(256, 64);
        collision = new Collision();
    }


    private void loadPlayer () {
        player = new Magic(4, 4, game, physicsWorld);
        player.create(physicsWorld);
    }

    @Override
    public void show() {
        terrain.loadTestMap(physicsWorld);
        loadPlayer();
        Gdx.input.setInputProcessor(new KeyboardHandler(game, player));
        physicsWorld.setContactListener(collision);
        Gdx.app.log(LogHelper.getClassyTag(this), "Game screen show done");
    }



    protected void update (float delta) {
        camera.position.set(player.getPosition(), 0);
        camera.update();
        terrain.update();

        player.update(delta, collision.isPlayerOnGround());
        collision.update(delta, physicsWorld);

        physicsWorld.step(1/60f, 8, 3);


    }

    protected void draw () {
        GL20 gl20 = Gdx.gl;
        gl20.glClearColor(0, 0, 0, 1);
        gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        bgBatch.begin();
        //bgBatch.draw(bg, 0, 0);
        bgBatch.end();

        game.gameBatch.setProjectionMatrix(camera.combined);
        game.gameBatch.begin();

        terrain.draw(game.gameBatch);

        player.draw(game.gameBatch);
        game.gameBatch.end();
        //box2DDebugRenderer.render(physicsWorld, camera.combined);
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
