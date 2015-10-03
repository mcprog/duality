package mcprog.duality;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import mcprog.duality.screens.MenuScreen;

/**
 * mcprog
 * <p>Entry point for core module of the game.
 * Main methods from platform specific modules access this.</p>
 *
 * <p>All this does is creates a SpriteBatch to be uses throughout the program,
 * and sets the screen to the menu screen.
 * Extends Game, not ApplicationAdapter so casting may be necessary to access certain methods.</p>
 *
 * <p>When setting screen, <code>this</code> is always passed,
 * so an instance of the game is readily available.</p>
 */
public class Duality extends Game {

    /**
     * Public SpriteBatch to be used for all drawing in the game. More efficient this way.
     */
	public SpriteBatch gameBatch;
	
	@Override
	public void create () {
		gameBatch = new SpriteBatch();
        setScreen(new MenuScreen(this));
	}


}
