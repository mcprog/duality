package mcprog.duality.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import net.dermetfan.gdx.assets.AnnotationAssetManager;
import net.dermetfan.gdx.physics.box2d.ContactAdapter;

import mcprog.duality.library.Assets;
import mcprog.duality.utility.LogHelper;

/**
 * Created by mcprog on 10/13/2015.
 */
public class Collision extends ContactAdapter {

    private int playerFootContacts;

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        //Gdx.app.log(LogHelper.getClassyTag(this), "contact happened");
        if (a.getBody().getUserData() != null) {
            if (b.getBody().getUserData() != null) {
                if (a.getBody().getUserData().equals("attack") && b.getBody().getUserData().equals("tile")) {
                    a.getBody().setUserData("delete");
                    b.getBody().setUserData("--health");
                    //attackHit();
                } else if (b.getBody().getUserData().equals("attack") && a.getBody().getUserData().equals("tile")) {
                    b.getBody().setUserData("delete");
                    a.getBody().setUserData("--health");
                    //attackHit();
                }
            }
        }

        if (a.getUserData() != null && a.getUserData().equals("foot")) {
            ++playerFootContacts;
            Gdx.app.log(LogHelper.getClassyTag(this), "player on ground a");

        }
        else if (b.getUserData() != null && b.getUserData().equals("foot")) {
            ++playerFootContacts;
            Gdx.app.log(LogHelper.getClassyTag(this), "cplayer on ground b");

        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        //Gdx.app.log(LogHelper.getClassyTag(this), "contact ended");
        if (a.getUserData() != null && a.getUserData().equals("foot")) {
                --playerFootContacts;
                Gdx.app.log(LogHelper.getClassyTag(this), "player off ground a");
        }
        else if (b.getUserData() != null && b.getUserData().equals("foot")) {
                --playerFootContacts;
                Gdx.app.log(LogHelper.getClassyTag(this), "cplayer off ground b");

        }
    }

    public void update (float delta, World world) {
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body b : bodies) {
            if (b.getUserData() != null) {
                if (b.getUserData().equals("delete")) {
                    world.destroyBody(b);
                    b.setUserData(null);
                    b = null;
                }
            }
        }

    }

    public boolean isPlayerOnGround () {
        return playerFootContacts > 0;
    }
}
