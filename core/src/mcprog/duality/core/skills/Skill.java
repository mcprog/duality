package mcprog.duality.core.skills;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import mcprog.duality.core.attacks.Attack;

/**
 * Created by mcprog on 10/16/2015.
 */
public abstract class Skill {

    protected Array<Attack> attacks = new Array<Attack>();
    protected TextureRegion region;
    protected float speed;
    protected float coolDownTimer;
    protected float coolDownTime;

    public Skill (TextureRegion region, float speed, float coolDownTime) {
        this.region = region;
        this.speed = speed;
        this.coolDownTime = coolDownTime;
    }

    public void launch (World world, float x, float y, int atkDirX, int atkDirY) {
        if (coolDownTimer >= coolDownTime) {
            playLaunchSound();
            Attack atk = new Attack(region, x, y, atkDirX * speed, atkDirY * speed);
            attacks.add(atk);
            atk.create(world);
            atk.setRotation(MathUtils.atan2(atkDirY, atkDirX));
            coolDownTimer = 0;
        }
    }

    public abstract void update (float delta);

    public void draw (SpriteBatch batch) {
        for (Attack atk : attacks) {
            atk.draw(batch);
        }
    }

    protected void delete () {
        for (int i = 0; i < attacks.size; ++i) {
            if (attacks.get(i).body.getUserData() != null) {
                if (attacks.get(i).body.getUserData().equals("delete")) {
                    playImpactSound();
                    attacks.removeIndex(i);
                }
            }
        }
    }

    protected void playLaunchSound () {
    }

    protected void playImpactSound () {
    }
}
