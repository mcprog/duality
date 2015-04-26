package com.mcprog.duality.world;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.duality.library.Assets;

/**
 * Created by mcprog on 4/25/2015.
 */
public class Player extends Entity {

    private float hWidth;
    private float hHeight;
    private Animation[] animations;
    private TextureRegion[][] map;
    private float stateTime;
    private float speed = 15;
    private int direction;

    public static final int LEFT = -1;
    public static final int RIGHT = 1;
    public static final int IDLE = 0;
    public static final int SIT = 2;

    public Player(World world, Vector2 initialPosition) {
        super(world);
        this.initialPosition = initialPosition;

        hWidth = 1.4f;
        hHeight = 2.8f;

        initBody();

        map = TextureRegion.split(new Texture("wizard.png"), 16, 16);
    }

    @Override
    protected void initBody() {
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(initialPosition);

        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(hWidth, hHeight);
        body.setFixedRotation(true);
        body.createFixture(shape, 1);

        shape.dispose();
    }

    private void rollBody () {
        body.destroyFixture(body.getFixtureList().first());
        CircleShape shape = new CircleShape();
        shape.setRadius(hWidth);
        body.createFixture(shape, 1);

        shape.dispose();
    }

    private void unrollBody () {
        body.destroyFixture(body.getFixtureList().first());
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(hWidth, hHeight);
        body.setFixedRotation(true);
        body.createFixture(shape, 1);

        shape.dispose();
    }

    public void draw (float stateTime, SpriteBatch batch) {
        this.stateTime = stateTime;
        getCurrentFrame();
        currentFrame.setCenter(getPosition().x + 0, getPosition().y + 1);
        currentFrame.setScale(.5f);

        batch.begin();
        currentFrame.draw(batch);
        batch.end();
    }

    public void moveLeft () {
        body.setLinearVelocity(-speed, getVelocity().y);
        direction = LEFT;
    }

    public void moveRight () {
        body.setLinearVelocity(speed, getVelocity().y);
        direction = RIGHT;
    }

    public void stop () {
        body.setLinearVelocity(0, getVelocity().y);
        direction = IDLE;
    }

    public void sit () {
        direction = SIT;
    }

    public void roll () {
        direction = SIT;
        rollBody();
    }

    public void unroll () {
        unrollBody();
    }

    @Override
    protected void getCurrentFrame() {

        if (direction == LEFT) {
            currentFrame = new Sprite(Assets.getAnimation(3, 3, .25f, map).getKeyFrame(stateTime));
        } else if (direction == RIGHT) {
            currentFrame = new Sprite(Assets.getAnimation(2, 3, .25f, map).getKeyFrame(stateTime));
        } else if (direction == SIT) {
            currentFrame = new Sprite(map[0][0]);
        }
        else {
            currentFrame = new Sprite(map[1][0]);
        }
    }
}
