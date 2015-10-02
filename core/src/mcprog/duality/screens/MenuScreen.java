package mcprog.duality.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import mcprog.duality.Duality;
import mcprog.duality.utility.LogHelper;

/**
 * Created by mcprog on 10/1/2015.
 */
public class MenuScreen extends ScreenAdapter {
    protected Duality game;
    private Rectangle playBounds;
    private Sprite playButton;

    public MenuScreen (Duality game) {
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.app.log(LogHelper.getClassyTag(this), "Screen shown");
        playButton = new Sprite(new Texture(Gdx.files.internal("play-button.png")));
        playButton.setBounds(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, playButton.getTexture().getWidth() * 3, playButton.getTexture().getHeight() * 3);

    }


    public void update(float delta) {
        int mX = Gdx.input.getX();
        int mY = Gdx.graphics.getHeight() - Gdx.input.getY();
        if (playButton.getBoundingRectangle().contains(mX, mY)) {
            //Gdx.app.log(LogHelper.getClassyTag(this), "Play hovered over at: (" + mX + ", " + mY + ").");
            if (Gdx.input.justTouched()) {
                Gdx.app.log(LogHelper.getClassyTag(this), "Play button clicked");
            }
        }
    }

    public void draw () {
        GL20 gl20 = Gdx.gl;
        gl20.glClearColor(0, 1, 0, 1);
        gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.gameBatch.begin();
        playButton.draw(game.gameBatch);
        game.gameBatch.end();
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }
}
