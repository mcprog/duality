package mcprog.duality.library;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by mcprog on 10/14/2015.
 */
public class Assets {

    public static Sound fireballWoosh;
    public static Sound fireballImpact;
    public static Sound arrowShot;

    public static void loadFireballSounds () {
        if (fireballWoosh != null) {
            return;
        }
        fireballWoosh = Gdx.audio.newSound(Gdx.files.internal("audio/fireball_woosh_short.mp3"));
        fireballImpact = Gdx.audio.newSound(Gdx.files.internal("audio/explosion_shorter.mp3"));
    }

    public static void loadArrowSounds () {
        if (arrowShot != null) {
            return;
        }
        arrowShot = Gdx.audio.newSound(Gdx.files.internal("audio/arrow_fire_short.mp3"));
    }
}
