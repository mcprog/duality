package mcprog.duality.core;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import mcprog.duality.library.Reference;

/**
 * Created by mcprog on 10/9/2015.
 */
public class Tile {

    protected float x;
    protected float y;
    protected Sprite sprite;
    protected BodyDef bodyDef;
    protected Body body;
    protected float width;
    protected float height;

    public Tile (TextureRegion tileRegion, float x, float y) {
        sprite = new Sprite(tileRegion);
        this.x = x;
        this.y = y;
        width = sprite.getRegionWidth() * Reference.PIXELS_TO_METERS;
        height = sprite.getRegionHeight() * Reference.PIXELS_TO_METERS;
        sprite.setSize(width, height);
        sprite.setCenter(x, y);
        bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.StaticBody;

    }

    public void create (World world) {
        body = world.createBody(bodyDef);
        PolygonShape square = new PolygonShape();
        square.setAsBox(.25f, .25f);
        body.createFixture(square, 1);
        square.dispose();
        body.setUserData("tile");
    }

    public void draw (SpriteBatch batch) {
        sprite.draw(batch);
    }
}
