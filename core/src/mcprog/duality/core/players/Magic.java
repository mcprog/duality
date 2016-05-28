package mcprog.duality.core.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import mcprog.duality.Duality;
import mcprog.duality.core.skills.Skill;
import mcprog.duality.core.skills.cast.SkillFireball;

/**
 * Created by mcprog on 10/31/2015.
 */
public class Magic extends Player {

    private SkillFireball primaryAttack;
    private Skill secondaryAttack;

    public Magic(float x, float y, Duality game, World world) {
        super(new TextureRegion(new Texture(Gdx.files.internal("images/player.png")), 0, 0, 48, 32), x, y, game, world);
        primaryAttack = new SkillFireball();
    }

    @Override
    protected Skill getPrimaryAttack() {
        return primaryAttack;
    }

    @Override
    public Skill getSecondaryAttack() {
        return secondaryAttack;
    }
}
