package mcprog.duality.core.skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import mcprog.duality.core.attacks.Attack;
import mcprog.duality.library.Assets;

/**
 * Created by mcprog on 10/16/2015.
 */
public class SkillFireball extends Skill {

    public SkillFireball () {
        super(new TextureRegion(new Texture(Gdx.files.internal("images/fireball.png")), 0, 0, 16, 16), 15, .75f);
        Assets.loadFireballSounds();
    }

    @Override
    public void update(float delta) {
        delete();
        coolDownTimer += delta;
        for (Attack atk : attacks) {
            atk.update(delta);
        }
    }

    @Override
    protected void playLaunchSound() {
        Assets.fireballWoosh.play();
    }

    @Override
    protected void playImpactSound() {
        Assets.fireballImpact.play();
    }
}
