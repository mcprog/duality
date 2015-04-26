package com.mcprog.duality.handler;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by mcprog on 4/26/2015.
 */
public class PlayerControlHandler implements InputProcessor, ControllerListener {

    private int left;
    private int right;
    private int up;
    private int down;
    private int jump;
    private int roll;
    private int sit;
    private int interact;
    private int attack1;
    private int attack2;
    private int defence1;
    private int defence2;


    public PlayerControlHandler (int left, int right, int up, int down, int jump, int roll, int sit, int interact, int attack1, int attack2, int defence1, int defence2) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
        this.jump = jump;
        this.roll = roll;
        this.sit = sit;
        this.interact = interact;
        this.attack1 = attack1;
        this.attack2 = attack2;
        this.defence1 = defence1;
        this.defence2 = defence2;
    }

    @Override
    public void connected(Controller controller) {

    }

    @Override
    public void disconnected(Controller controller) {

    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
