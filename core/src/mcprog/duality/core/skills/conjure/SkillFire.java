package mcprog.duality.core.skills.conjure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import mcprog.duality.core.skills.Skill;

/**
 * Created by mcprog on 11/1/2015.
 */
public class SkillFire extends Skill {

    private float burnTime;

    public SkillFire() {
        super(new TextureRegion(new Texture(Gdx.files.internal("images/fire.png")), 0, 0, 128, 16), 0, 5f);
    }

    @Override
    public void update(float delta) {

    }
    @Override
    protected void delete() {
        if (burnTime <= 0 ) {

        }
    }
}
