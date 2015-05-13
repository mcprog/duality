package com.mcprog.duality.world;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by mcprog on 4/25/2015.
 */
public abstract class Entity {

    protected World world;
    protected BodyDef bodyDef;
    protected Body body;
    protected int health;
    protected String name;
    protected Vector2 initialPosition;

    protected Sprite currentFrame;

    public Entity (World world) {
        this.world = world;

    }

    protected abstract void initBody ();

    protected abstract void getCurrentFrame ();

    public void update (float delta) {

    }

    public void draw (SpriteBatch batch) {

    }

    protected void takeDamage (int amount) {
        health = Math.min(health - amount, 0);
    }


    protected Vector2 getPosition() {
        return body.getPosition();
    }

    protected Vector2 getVelocity() {
        return body.getLinearVelocity();
    }
}
