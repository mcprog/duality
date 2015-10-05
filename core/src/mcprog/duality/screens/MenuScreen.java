package mcprog.duality.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Vector;

import mcprog.duality.Duality;
import mcprog.duality.library.Reference;
import mcprog.duality.ui.MenuTable;
import mcprog.duality.utility.LogHelper;

/**
 * Created by mcprog on 10/1/2015.
 */
public class MenuScreen extends ScreenAdapter {
    protected Duality game;
    protected OrthographicCamera guiCam;
    protected Viewport viewport;
    protected Stage stage;

    private Table table;
    private Sprite viewportVisualizer;

    public MenuScreen (Duality game) {
        this.game = game;

        guiCam = new OrthographicCamera();
        viewport = new FillViewport(Reference.W_WIDTH / 4, Reference.W_HEIGHT / 4, guiCam);
        viewport.apply();
        guiCam.position.set(guiCam.viewportWidth / 2f, guiCam.viewportHeight / 2f, 0);
    }

    @Override
    public void show() {
        Gdx.app.log(LogHelper.getClassyTag(this), "Screen shown");

        viewportVisualizer = new Sprite(new Texture(Gdx.files.internal("images/gemma-arterton.jpg")));
        viewportVisualizer.setSize(guiCam.viewportWidth, guiCam.viewportHeight);
        stage = new Stage(viewport);
        table = new MenuTable();
        table.setBounds(0, 0, guiCam.viewportWidth, guiCam.viewportHeight);

        table.debug();

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        guiCam.position.set(guiCam.viewportWidth / 2f, guiCam.viewportHeight / 2f, 0);
    }

    public void update(float delta) {
        stage.act(delta);
    }

    public void draw () {
        GL20 gl20 = Gdx.gl;
        gl20.glClearColor(0, 1, 0, 1);
        gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        guiCam.update();

        game.gameBatch.setProjectionMatrix(guiCam.combined);
        game.gameBatch.begin();
        viewportVisualizer.draw(game.gameBatch);
        game.gameBatch.end();

        stage.draw();
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }
}
