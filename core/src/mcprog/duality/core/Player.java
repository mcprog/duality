package mcprog.duality.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.sql.Ref;

import mcprog.duality.library.Reference;
import mcprog.duality.utility.LogHelper;
import mcprog.duality.utility.PlatformSpecific;

/**
 * Created by mcprog on 10/9/2015.
 */
public class Player extends InputAdapter {

    protected float x;
    protected float y;
    protected Sprite sprite;
    protected BodyDef bodyDef;
    protected Body body;
    protected float width;
    protected float height;

    private int dirX;
    private int dirY;
    private int left;
    private int right;

    public Player (TextureRegion tileRegion, float x, float y) {
        sprite = new Sprite(tileRegion);
        this.x = x;
        this.y = y;
        width = sprite.getRegionWidth() * Reference.PIXELS_TO_METERS;
        height = sprite.getRegionHeight() * Reference.PIXELS_TO_METERS;
        sprite.setSize(width, height);
        sprite.setCenter(x, y);
        sprite.setOrigin(width / 2, height / 2);
        bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
    }

    public void create (World world) {
        body = world.createBody(bodyDef);
        PolygonShape square = new PolygonShape();
        square.setAsBox(.375f, .5f);
        body.createFixture(square, 1);
        square.dispose();
    }

    public void update (float delta) {
        dirX = left + right;
        float posX = body.getPosition().x;
        float posY = body.getPosition().y;
        if (dirX != 0) {
            if (dirX > 0) {
                body.applyLinearImpulse(.1f, 0, posX, posY, true);
            } else {
                body.applyLinearImpulse(-.1f, 0, posX, posY, true);
            }
        }

        if (PlatformSpecific.isMobile()) {
            if (Gdx.input.getAccelerometerY() > 2) {
                body.applyLinearImpulse(.1f, 0, posX, posY, true);
            }
            else if (Gdx.input.getAccelerometerY() < -2) {
                body.applyLinearImpulse(-.1f, 0, posX, posY, true);
            }
        }

        sprite.setRotation(MathUtils.radDeg * body.getAngle());
        sprite.setCenter(body.getPosition().x, body.getPosition().y);

    }

    public void draw (SpriteBatch batch) {
        sprite.draw(batch);
    }

    public Vector2 getPosition () {
        return body.getPosition();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            body.applyLinearImpulse(0, 5, body.getPosition().x, body.getPosition().y, true);
            Gdx.app.log(LogHelper.getClassyTag(this), "Space pressed.");
            Gdx.app.log(LogHelper.getClassyTag(this), "" + ((PolygonShape)(body.getFixtureList().first().getShape())).getRadius());
        }
        if (keycode == Input.Keys.A) {
            //body.applyLinearImpulse(-1, 0, body.getPosition().x, body.getPosition().y, true);
            left = -1;
        }
        if (keycode == Input.Keys.D) {
            //body.applyLinearImpulse(1, 0, body.getPosition().x, body.getPosition().y, true);
            right = 1;
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A) {
            left = 0;
        }
        if (keycode == Input.Keys.D) {
            right = 0;

        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        /*Gdx.app.log(LogHelper.getClassyTag(this), "touch down anyting");
        if (PlatformSpecific.isMobile()) {
            if (screenX < Gdx.graphics.getWidth() / 3f) {
                left = -1;
            }
            else if (screenX > Gdx.graphics.getWidth() * 2f / 3f) {
                right = 1;
            } else {
                body.applyLinearImpulse(0, 5, body.getPosition().x, body.getPosition().y, true);
                return true;
            }
            Gdx.app.log(LogHelper.getClassyTag(this), "touch down mobile");


        }*/
        if (PlatformSpecific.isMobile()) {
            body.applyLinearImpulse(0, 5, body.getPosition().x, body.getPosition().y, true);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        /*if (PlatformSpecific.isMobile()) {
            if (screenX < Gdx.graphics.getWidth() / 3f) {
                left = 0;
            }
            else if (screenX > Gdx.graphics.getWidth() * 2f / 3f) {
                right = 0;
            }
            return true;
        }*/
        return false;
    }
}