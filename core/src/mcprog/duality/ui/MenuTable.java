package mcprog.duality.ui;

import mcprog.duality.Duality;
import mcprog.duality.screens.GameScreen;

/**
 * Created by mcprog on 10/2/2015.
 * Implementation of the MenuFactory. Simply sets up the main menu and adds listeners.
 * @see MenuFactory if you haven't already.
 */
public class MenuTable extends MenuFactory {



    /**
     * This constructor does all composition and listener assignment.
     */
    public MenuTable (Duality game) {
        super(game);

        setFillParent(true);
        setupMainMenu();

        listenAndQuit(quitButton);
        listenToBackButton();

        listenAndSwitchTo(playButton, PLAY_SELECT);
        listenAndSwitchTo(singleplayerButton, SINGLE_SELECT);
        listenAndSwitchTo(multiplayerButton, MULTI_SELECT);
        listenAndSwitchTo(optionsButton, OPTIONS);
        listenToTwitterButton();
        listenAndSetScreen(storyButton, new GameScreen(game));
    }

    public MenuTable (Duality game, int state) {
        super(game, state);

        setFillParent(true);
        if (state == MenuFactory.MAIN) {
            setupMainMenu();
        }
        else if (state == MenuFactory.SINGLE_SELECT) {
            setupSingleSelect();
        }


        listenAndQuit(quitButton);
        listenToBackButton();

        listenAndSwitchTo(playButton, PLAY_SELECT);
        listenAndSwitchTo(singleplayerButton, SINGLE_SELECT);
        listenAndSwitchTo(multiplayerButton, MULTI_SELECT);
        listenAndSwitchTo(optionsButton, OPTIONS);
        listenToTwitterButton();
        listenAndSetScreen(storyButton, new GameScreen(game));
    }
}
