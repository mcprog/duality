package com.mcprog.duality.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.mcprog.duality.configuration.Mappings;
import com.mcprog.duality.input.XBox360Mappings;
import com.mcprog.duality.world.Player;
import com.mcprog.duality.world.PlayerAnimationStates;

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

    public boolean dirLeft;
    public boolean dirRight;
    public boolean dirUp;
    public boolean dirDown;
    public boolean actJump;
    public boolean justJump;
    public boolean actSit;
    //public boolean actUnsit;
    public boolean justSit;
    public boolean actRollLeft;
    public boolean actRollRight;
    public boolean actFlipLeft;
    public boolean actFlipRight;

    private boolean onGround;
    private PlayerAnimationStates playerAnimationState;



    public PlayerControlHandler () {
        this.left = Mappings.leftKey;
        this.right = Mappings.rightKey;
        this.up = Mappings.upKey;
        this.down = Mappings.downKey;
        this.jump = Mappings.jumpKey;
        this.sit = Mappings.sitKey;
        this.interact = Mappings.interactKey;
        this.attack1 = Mappings.attack1Key;
        this.attack2 = Mappings.attack2Key;
        this.defence1 = Mappings.defense1Key;
        this.defence2 = Mappings.defense2Key;
    }

    public void handleInput (Body body, float xSpeed, float jumpImp, float jumpX, float rollX, float flipX, boolean onGround) {
        this.onGround = onGround;
        if (onGround) {
            if (actRollLeft) {
                setVX(body, -rollX);
            }
            else if (actRollRight) {
                setVX(body, rollX);
            } else {
                handleInputNormal(body, xSpeed, jumpImp);
            }
        } else {
            if (actFlipLeft) {
                setVX(body, -flipX);
            }
            else if (actFlipRight) {
                setVX(body, flipX);
            } else {
                handleJumpInput(body, jumpX);
            }

        }
    }

    private void handleInputNormal (Body body, float xSpeed, float jumpImp) {
        if (dirLeft && !dirRight) {
            setVX(body, -xSpeed);
            playerAnimationState = PlayerAnimationStates.WALK_LEFT;
        }
        else if (dirRight && !dirLeft) {
            setVX(body, xSpeed);
            playerAnimationState = PlayerAnimationStates.WALK_RIGHT;
        }
        else {
            setVX(body, 0);
            playerAnimationState = PlayerAnimationStates.IDLE;
        }

        if (actJump && Gdx.input.isKeyPressed(jump)) {
            body.applyLinearImpulse(0, jumpImp, body.getPosition().x, body.getPosition().y, true);
            playerAnimationState = PlayerAnimationStates.JUMP;
        }
    }

    /*private void handleRollInput (Body body, float xSpeed) {
        if (dirLeft && !dirRight) {
            setVX(body, -xSpeed);
        } else {
            setVX(body, xSpeed);
        }
    }

    private void handleFlipInput (Body body, float xSpeed) {
        if (dirLeft && !dirRight) {
            setVX(body, -xSpeed);
        } else {
            setVX(body, xSpeed);
        }
    }*/

    private void handleJumpInput (Body body, float xSpeed) {
        if (dirLeft && !dirRight) {
            setVX(body, -xSpeed);
            playerAnimationState = PlayerAnimationStates.JUMP_LEFT;
        }
        else if (dirRight && !dirLeft) {
            setVX(body, xSpeed);
            playerAnimationState = PlayerAnimationStates.JUMP_RIGHT;
        }
    }

    private void setVX (Body body, float vX) {
        body.setLinearVelocity(vX, body.getLinearVelocity().y);
    }

    public PlayerAnimationStates getPlayerAnimationState () {
        return playerAnimationState;
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
        if (keycode == left) {
            dirLeft = true;
            dirRight = false;
            //if (onGround) {
            if (dirDown) {
                actRollLeft = true;
                actRollRight = false;
            }
            //}

        }
        else if (keycode == right) {
            dirRight = true;
            dirLeft = false;
            //if (onGround) {
            if (dirDown) {
                actRollRight = true;
                actRollLeft = false;
            }
            //}

        }
        else if (keycode == up) {
            dirUp = true;
            dirDown = false;
        }
        else if (keycode == down) {
            dirDown = true;
            dirUp = false;
            //if (onGround) {
            if (dirLeft) {
                actRollLeft = true;
                actRollRight = false;
            }
            else if (dirRight) {
                actRollRight = true;
                actRollLeft = false;
            }
            //}

        }
        else if (keycode == jump && onGround) {
            actJump = true;
            justJump = true;
        }
        else if (keycode == sit) {
            actSit = !actSit;
            justSit = true;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == left) {
            dirLeft = false;
            actRollLeft = false;
            if (Gdx.input.isKeyPressed(right)) {
                dirRight = true;
            }
        }
        else if (keycode == right) {
            dirRight = false;
            actRollRight = false;
            if (Gdx.input.isKeyPressed(left)) {
                dirLeft = true;
            }
        }
        else if (keycode == up) {
            dirUp = false;
            if (Gdx.input.isKeyPressed(down)) {
                dirDown = true;
            }
        }
        else if (keycode == down) {
            dirDown = false;
            actRollLeft = false;
            actRollRight = false;
            if (Gdx.input.isKeyPressed(up)) {
                dirUp = true;
            }
        }
        else if (keycode == jump) {
            justJump = false;
        }
        else if (keycode == sit) {
            justSit = false;
        }
        return true;
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
