package mcprog.duality.core.attacks;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by mcprog on 11/1/2015.
 * Not good just use attack conjure
 */
public class AttackConjure extends AttackRectangle {

    public AttackConjure(TextureRegion tileRegion, float x, float y, float atkRadius) {
        super(tileRegion, x, y, 0, 0);
        hasGravity = true;
    }
}
