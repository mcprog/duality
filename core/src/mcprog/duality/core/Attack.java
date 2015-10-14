package mcprog.duality.core;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;

import mcprog.duality.library.Reference;

/**
 * Created by mcprog on 10/12/2015.
 */
public class Attack {

    protected float x;
    protected float y;
    protected float vX;
    protected float vY;
    protected Sprite sprite;
    protected BodyDef bodyDef;
    protected Body body;
    protected float width;
    protected float height;


    //private float attackTimer = .5f;

    public Attack (TextureRegion tileRegion, float x, float y, float vX, float vY) {
        sprite = new Sprite(tileRegion);
        this.x = x;
        this.y = y;
        this.vX = vX;
        this.vY = vY;
        width = sprite.getRegionWidth() * Reference.PIXELS_TO_METERS;
        height = sprite.getRegionHeight() * Reference.PIXELS_TO_METERS;
        sprite.setSize(width, height);
        sprite.setCenter(x, y);
        bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;


    }

    public void create (World world) {
        body = world.createBody(bodyDef);
        CircleShape circle = new CircleShape();
        circle.setRadius(.125f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.isSensor = true;
        fixtureDef.density = 1;
        body.createFixture(fixtureDef);
        circle.dispose();
        body.setGravityScale(0);
        body.setLinearVelocity(vX, vY);
        body.setUserData("attack");
    }

    public void update (float delta) {
        sprite.setOrigin(width / 2f, height / 2f);
        sprite.setCenter(body.getPosition().x, body.getPosition().y);
        sprite.setRotation(MathUtils.radDeg * body.getAngle());
        //attackTimer -= delta;
    }

    public void draw (SpriteBatch batch) {
        if (body.isActive()) {
            sprite.draw(batch);
        }
    }
}
