package mcprog.duality.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import mcprog.duality.Duality;
import mcprog.duality.library.Reference;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Reference.W_WIDTH;
		config.height = Reference.W_HEIGHT;
        config.resizable = false;
		new LwjglApplication(new Duality(), config);
	}
}
