package com.mcprog.duality.test.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by mcprog on 4/27/2015.
 */
public class Bounds {

    private BodyDef bodyDef;
    private Body body;
    private FixtureDef fixtureDef;
    private Fixture fixture;

    public Bounds (World world, float hWidth, float hHeight) {
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(Vector2.Zero);

        body = world.createBody(bodyDef);
        ChainShape shape = new ChainShape();
        float[] corners = {-hWidth, -hHeight, hWidth, - hHeight, hWidth, hHeight, -hWidth, hHeight};
        shape.createLoop(corners);
        body.setFixedRotation(true);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 1;
        body.createFixture(shape, 1);

        shape.dispose();
    }
}
