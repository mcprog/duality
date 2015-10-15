package mcprog.duality.library;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by mcprog on 10/14/2015.
 */
public class Assets {

    public static Sound fireballWoosh;
    public static Sound fireballImpact;

    public static void loadFireballSounds () {
        fireballWoosh = Gdx.audio.newSound(Gdx.files.internal("audio/fireball_woosh_short.mp3"));
        fireballImpact = Gdx.audio.newSound(Gdx.files.internal("audio/explosion_shorter.mp3"));
    }
}
