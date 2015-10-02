package mcprog.duality;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import mcprog.duality.screens.MenuScreen;

public class Duality extends Game {
	public SpriteBatch gameBatch;
	//Texture img;
	
	@Override
	public void create () {
		gameBatch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
        setScreen(new MenuScreen(this));
	}


}
