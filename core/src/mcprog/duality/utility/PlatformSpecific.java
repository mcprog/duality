package mcprog.duality.utility;

import com.badlogic.gdx.Application;
import static com.badlogic.gdx.Application.ApplicationType.*;
import com.badlogic.gdx.Gdx;

/**
 * Created by mcprog on 10/5/2015.
 * This class is meant to help with platform specific code
 */
public class PlatformSpecific {

    public static boolean isExitable () {
        Application.ApplicationType type = Gdx.app.getType();
        return type != WebGL || type != iOS;
    }

    public static boolean isMultiplayer () {
        return Gdx.app.getType() == Desktop;
    }

}
