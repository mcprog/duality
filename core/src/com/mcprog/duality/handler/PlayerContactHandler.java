package com.mcprog.duality.handler;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by mcprog on 4/27/2015.
 */
public class PlayerContactHandler implements ContactListener {

    public boolean onGround;

    @Override
    public void beginContact(Contact contact) {
        Fixture fA = contact.getFixtureA();
        Fixture fB = contact.getFixtureB();
        if (fA.getUserData() != null && fA.getUserData().equals("foot")) {
            onGround = true;
        }
        else if (fB.getUserData() != null && fB.getUserData().equals("foot")) {
            onGround = true;
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fA = contact.getFixtureA();
        Fixture fB = contact.getFixtureB();

        if (fA.getUserData() != null && fA.getUserData().equals("foot")) {
            onGround = false;
        }
        else if (fB.getUserData() != null && fB.getUserData().equals("foot")) {
            onGround = false;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
