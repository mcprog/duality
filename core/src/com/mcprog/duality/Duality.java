package com.mcprog.duality;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcprog.duality.library.Constants;
import com.mcprog.duality.test.screen.CameraTestScreen;
import com.mcprog.duality.test.screen.WorldTestScreen;

public class Duality extends Game {

    //private CameraTestScreen cameraTestScreen;
    private WorldTestScreen worldTestScreen;
	
	@Override
	public void create () {
        worldTestScreen = new WorldTestScreen();
        setScreen(worldTestScreen);
	}

}
