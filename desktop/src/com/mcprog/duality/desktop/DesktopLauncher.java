package com.mcprog.duality.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mcprog.duality.Duality;
import com.mcprog.duality.library.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Constants.RAW_WIDTH;
        config.height = Constants.RAW_HEIGHT;
        new LwjglApplication(new Duality(), config);
	}
}
