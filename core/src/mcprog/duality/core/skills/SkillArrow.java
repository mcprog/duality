package mcprog.duality.core.skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;

import mcprog.duality.core.attacks.Attack;
import mcprog.duality.core.attacks.AttackRectangle;
import mcprog.duality.library.Assets;

/**
 * Created by mcprog on 10/16/2015.
 */
public class SkillArrow extends Skill {

    private boolean newKnock;
    private float knockTimer;
    private float knockTime = 1;
    private float speedFromKnock;

    public SkillArrow () {
        super(new TextureRegion(new Texture(Gdx.files.internal("images/arrow.png")), 0, 0, 16, 16), 20, 1.3f);
        Assets.loadArrowSounds();
    }

    @Override
    public void update(float delta) {
        delete();
        coolDownTimer += delta;
        knockTimer += delta;
        if (newKnock && speedFromKnock <= speed){
            speedFromKnock += delta * 5;
        }
        for (Attack atk : attacks) {
            atk.update(delta);
        }
    }

    public void knock () {
       newKnock = true;
    }

    @Override
    public void launch(World world, float x, float y, int atkDirX, int atkDirY) {

            playLaunchSound();
            AttackRectangle atk = new AttackRectangle(region, x, y, atkDirX * speedFromKnock, atkDirY * speedFromKnock);
            attacks.add(atk);
            atk.hasGravity = true;
            atk.setCollisionBounds(16, 5);
            atk.create(world);
            atk.setRotation(MathUtils.atan2(atkDirY, atkDirX));
            coolDownTimer = 0;
        newKnock = false;
        knockTimer = 0;
        speedFromKnock = 0;
    }

    @Override
    protected void playLaunchSound() {
        Assets.arrowShot.play();
    }

    private void playKnockSound () {

    }
}
