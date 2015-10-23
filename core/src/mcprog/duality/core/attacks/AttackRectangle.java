package mcprog.duality.core.attacks;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import mcprog.duality.library.Reference;

/**
 * Created by mcprog on 10/16/2015.
 */
public class AttackRectangle extends Attack {

    public AttackRectangle(TextureRegion tileRegion, float x, float y, float vX, float vY) {
      super(tileRegion, x, y, vX, vY);
    }

    public void setCollisionBounds (float newWidth, float newHeight) {
        width = newWidth * Reference.PIXELS_TO_METERS;
        height = newHeight * Reference.PIXELS_TO_METERS;
    }

    @Override
    public void create(World world) {
        body = world.createBody(bodyDef);
        PolygonShape rect = new PolygonShape();
        rect.setAsBox(width / 2f, height / 2f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rect;
        fixtureDef.isSensor = true;
        fixtureDef.density = 1;
        body.createFixture(fixtureDef);
        rect.dispose();
        if (!hasGravity) {
            body.setGravityScale(0);
        }
        body.setLinearVelocity(vX, vY);
        body.setUserData("attack");
    }
}
