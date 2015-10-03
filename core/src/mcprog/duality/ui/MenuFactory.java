package mcprog.duality.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by mcprog on 10/3/2015.
 * An abstract class that contains all ui elements, configurations and listeners in one play.
 * Simply extend this Table to use duality specific ui features.
 * UIs can be setup via the setup methods and listeners can be add via the listen methods.
 * @see MenuTable for implentation example.
 */
public abstract class MenuFactory extends Table {

    // Image Buttons
    protected ImageButton playButton;
    protected ImageButton quitButton;
    protected ImageButton optionsButton;
    protected ImageButton singleplayerButton;
    protected ImageButton multiplayerButton;
    protected ImageButton backButton;
    protected ImageButton storyButton;

    // States
    protected int state;
    protected static final int MAIN =           1;
    protected static final int PLAY_SELECT =    2;
    protected static final int SINGLE_SELECT =  3;
    protected static final int MULTI_SELECT =   4;
    protected static final int OPTIONS =        5;

    /**
     * Loads images to buttons. If there is file i/o errors, this is probably the culprit.
     */
    public MenuFactory () {
        playButton = getInitImageButton("images/play-button.png", 72, 32);
        quitButton = getInitImageButton("images/quit-button.png", 30, 32);
        optionsButton = getInitImageButton("images/options-button.png", 30, 32);
        singleplayerButton = getInitImageButton("images/singleplayer-button.png", 100, 32);
        multiplayerButton = getInitImageButton("images/multiplayer-button.png", 86, 32);
        backButton = getInitImageButton("images/back-button.png", 30, 32);
        storyButton = getInitImageButton("images/story-button.png", 86, 32);
    }

    /**
     * Sets up the main menu for duality that users see upon entering the game.
     * Also sets state to MAIN.
     */
    protected void setupMainMenu () {
        add(playButton).pad(6).colspan(2);
        row();
        add(optionsButton).pad(3).padTop(6);
        add(quitButton).pad(3).padTop(6);

        state = MAIN;
    }

    /**
     * Sets up the options/settings menu.
     * One gets to this by clicking the gear icon on the main menu.
     * Also sets state to OPTIONS.
     */
    protected void setupOptionsMenu () {
        add(backButton);

        state = OPTIONS;
    }

    /**
     * Sets up the menu to select play type (singleplayer/multiplayer).
     * Sets state to PLAY_SELECT.
     */
    protected void setupPlaySelect () {
        add(singleplayerButton).pad(6).colspan(2);
        row();
        add(multiplayerButton).pad(6).colspan(2);
        row();
        add(backButton).pad(3).padTop(6);
        add(quitButton).pad(3).padTop(6);

        state = PLAY_SELECT;
    }

    /**
     * Sets up the menu to select type of singleplayer (story/double standard).
     * Sets state to SINGLE_SELECT.
     */
    protected void setupSingleSelect () {
        add(storyButton).pad(6).colspan(2);
        row();
        add(backButton).pad(6);
        add(quitButton).pad(6);

        state = SINGLE_SELECT;
    }

    /**
     * Sets up the menu to select type on multiplayer (co-op/versus).
     * Sets state to MULTI_SELECT.
     */
    protected void setupMultiSelect () {
        add(backButton);

        state = MULTI_SELECT;
    }

    /**
     * Utility method to load image buttons with all necessary params in 1 method.
     * @param fileName file name of texture that contains the images for the up and down states of the button including extension
     * @param srcWidth width of the button in pixel according to the texture
     * @param srcHeight height of 1 state of the button in pixels
     * @return the configured Image button
     */
    public ImageButton getInitImageButton (String fileName, int srcWidth, int srcHeight) {
        Texture tex = new Texture(Gdx.files.internal(fileName));
        TextureRegion up = new TextureRegion(tex, 0, 0, srcWidth, srcHeight);
        TextureRegion down = new TextureRegion(tex, 0, srcHeight, srcWidth, srcHeight);
        return new ImageButton(new TextureRegionDrawable(up), new TextureRegionDrawable(down));
    }

    /**
     * Add a listener to the specified Button and quits the game when that button is clicked.
     * Uses <code>Gdx.app.exit()</code> to quit. Does not use <code>System.exit(0)</code>.
     * @param btn Button to add listener to.
     */
    public void listenAndQuit (Button btn) {
        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    /**
     * Adds Click Listener to specified Button.
     * When clicked, table is cleared and set up for the specified state to switch to is called.
     * If state does not exist, table is just cleared.
     * @param btn Button to add listener to.
     * @param nextState state to switch to when Button is clicked
     *                  (final because it is accessed from an inner class).
     */
    protected void listenAndSwitchTo (Button btn, final int nextState) {
        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clear();
                switch (nextState) {
                    case MAIN:
                        setupMainMenu();
                        break;
                    case PLAY_SELECT:
                        setupPlaySelect();
                        break;
                    case SINGLE_SELECT:
                        setupSingleSelect();
                        break;
                    case MULTI_SELECT:
                        setupMultiSelect();
                        break;
                    case OPTIONS:
                        setupOptionsMenu();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * Adds listener to the back button and determines what it is going back to using current state.
     * Defaults to main menu.
     */
    protected void listenToBackButton () {
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clear();
                switch (state) {
                    case SINGLE_SELECT:
                    case MULTI_SELECT:
                        setupPlaySelect();
                        break;
                    default:
                        setupMainMenu();
                        break;
                }
            }
        });
    }

}
