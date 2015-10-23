package mcprog.duality.utility;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import mcprog.duality.Duality;
import mcprog.duality.core.players.Player;
import mcprog.duality.screens.MenuScreen;

/**
 * Created by mcprog on 10/20/2015.
 */
public class KeyboardHandler extends InputAdapter {

    private static int JUMP_KEY = Input.Keys.SPACE;
    private static int LEFT_KEY = Input.Keys.A;
    private static int RIGHT_KEY = Input.Keys.D;
    private static int UP_KEY = Input.Keys.W;
    private static int DOWN_KEY = Input.Keys.S;
    private static int PRIMARY_ATTACK_KEY = Input.Keys.UP;

    private static int EXIT_GAME_KEY = Input.Keys.ESCAPE;


    private Duality game;
    private Player player;

    public KeyboardHandler (Duality game, Player player) {
        this.game = game;
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == JUMP_KEY) {
            if (player.canJump) {
                player.jump();
                return true;
            }
        }
        else if (keycode == LEFT_KEY) {
            player.assignLeft();
            return true;
        }
        else if (keycode == RIGHT_KEY) {
            player.assignRight();
            return true;
        }
        else if (keycode == UP_KEY) {
            player.assignUp();
            return true;
        }
        else if (keycode == DOWN_KEY) {
            player.assignDown();
            return true;
        }
        else if (keycode == PRIMARY_ATTACK_KEY) {
            player.primaryAttack();
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == LEFT_KEY) {
            player.designLeft();
            return true;
        }
        else if (keycode == RIGHT_KEY) {
            player.designRight();
            return true;
        }
        else if (keycode == UP_KEY) {
            player.designUp();
            return true;
        }
        else if (keycode == DOWN_KEY) {
            player.designDown();
            return true;
        }
        else if (keycode == EXIT_GAME_KEY) {
            game.setScreen(new MenuScreen(game));
            return true;
        }
        return false;
    }
}
