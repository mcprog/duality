package com.mcprog.duality.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.duality.handler.PlayerContactHandler;
import com.mcprog.duality.handler.PlayerControlHandler;
import com.mcprog.duality.library.Assets;

/**
 * Created by mcprog on 4/25/2015.
 */
public class Player extends Entity {

    private float hWidth;
    private float hHeight;
    private float hFootWidth;
    private float radius;
    private Animation[] animations;
    private TextureRegion[][] map;
    private float stateTime;
    private float speed = 15;
    private float impulse = 5f;
    private float jumpImpulse = 8f;
    private float jumpX = 5;
    private float rollX = 20;
    private float flipX = 15;
    private float rollTime = .7f;
    private float rollTimer;
    private float rollCoolTime = .5f;
    private float rollCoolTimer;
    private int direction;
    private int subDirection;
    private PlayerControlHandler playerControlHandler;
    private PlayerContactHandler playerContactHandler;
    private float delta;
    private boolean rolled;
    private FixtureDef sensorDef;
    private Fixture sensor;
    private Fixture main;
    private Fixture circle;
    private float actionTime;
    private float actionTimer;

    PlayerAnimationStates animationState = PlayerAnimationStates.IDLE;;

    /*public static final int IDLE = 0;
    public static final int SIT = 1;
    public static final int UNSIT = 2;
    public static final int WALK_LEFT = 3;
    public static final int WALK_RIGHT = 4;
    public static final int JUMP = 5;
    public static final int JUMP_LEFT = 6;
    public static final int JUMP_RIGHT = 7;
    public static final int ATTACK_LEFT = 8;
    public static final int ATTACK_RIGHT = 9;*/

    public Player(World world, Vector2 initialPosition) {
        super(world);
        this.initialPosition = initialPosition;

        hWidth = .35f;
        hHeight = .7f;
        hFootWidth = .3f;
        radius = .35f;
        animationState = PlayerAnimationStates.IDLE;

        switch (animationState) {
            default:
                Gdx.app.log("player", "enum instantion");
            break;
        }

        initControl();
        initContact();
        initBody();



        map = TextureRegion.split(new Texture("player.png"), 32, 32);
        currentFrame = new Sprite(map[0][0]);
    }

    @Override
    protected void initBody() {
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(initialPosition);


        body = world.createBody(bodyDef);
        body.setFixedRotation(true);
        initMain();
        initFoot();
    }

    private void initMain () {
        FixtureDef mainDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(hWidth, hHeight);
        mainDef.shape = shape;
        mainDef.friction = 1;
        mainDef.density = 1;
        main = body.createFixture(mainDef);

        shape.dispose();
    }

    private void initFoot () {
        FixtureDef sensorDef = new FixtureDef();
        sensorDef.isSensor = true;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(hFootWidth, .2f, new Vector2(0, -.5f), 0);
        sensorDef.shape = shape;
        sensor = body.createFixture(sensorDef);
        sensor.setUserData("foot");

        shape.dispose();
    }

    private void initRoll () {
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);
        circle = body.createFixture(shape, 1);

        shape.dispose();
    }

    public void initControl () {
        playerControlHandler = new PlayerControlHandler();
        Gdx.input.setInputProcessor(playerControlHandler);
    }

    public void initContact () {
        playerContactHandler = new PlayerContactHandler();
        world.setContactListener(playerContactHandler);
    }

    private void rollBody () {
        body.destroyFixture(main);
        body.destroyFixture(sensor);

        initRoll();

        body.setTransform(getPosition().x, getPosition().y - 1, 0);

    }

    private void unrollBody () {
        body.destroyFixture(circle);

        initMain();
        initFoot();

        body.setTransform(getPosition().x, getPosition().y + 1, 0);
    }

    @Override
    public void update (float delta) {

        this.delta = delta;
        animationState = playerControlHandler.getPlayerAnimationState();

        /*if (playerControlHandler.actRollLeft || playerControlHandler.actRollRight) {
            rollTimer += delta;
        } else {
            rollCoolTimer += delta;
        }*/




        playerControlHandler.handleInput(body, speed, jumpImpulse, jumpX, rollX, flipX, playerContactHandler.onGround);

        /*if ((playerControlHandler.actRollLeft || playerControlHandler.actRollRight) && rollCoolTimer >= rollCoolTime && !rolled) {
            rollBody();
            rolled = true;
            rollTimer = 0;
        }
        else if ((!playerControlHandler.actRollLeft && !playerControlHandler.actRollRight) && rollTimer >= rollTime && rolled) {
            unrollBody();
            rolled = false;
            rollCoolTimer = 0;
        }*/


    }

    public void draw (float stateTime, SpriteBatch batch) {
        this.stateTime = stateTime;
        getCurrentFrame();
        /*if (rolled) {
            //currentFrame.setOrigin(currentFrame.getWidth() / 2, 5);
            currentFrame.setCenter(getPosition().x, getPosition().y);
            currentFrame.setScale(.125f);
            //currentFrame.translateY(5f);
        } else {
            //sscurrentFrame.setOriginCenter();
            currentFrame.setCenter(getPosition().x, getPosition().y + .25f);
            currentFrame.setScale(.125f);
        }*/

        currentFrame.setCenter(getPosition().x, getPosition().y + .27f);
        currentFrame.setScale(.07f);

        batch.begin();
        currentFrame.draw(batch);
        batch.end();
    }


    @Override
    protected void getCurrentFrame() {

        switch (animationState) {
            /*case IDLE:
                currentFrame.setRegion(map[0][0]);
                break;
            case WALK_LEFT:
                currentFrame.setRegion(Assets.getAnimation(2, 6, .125f, map).getKeyFrame(stateTime));
                break;
            case WALK_RIGHT:
                currentFrame.setRegion(Assets.getAnimation(3, 6, .125f, map).getKeyFrame(stateTime));
                break;
            case JUMP:
                if (playerControlHandler.justJump) {
                    actionTime = .5f;
                    actionTimer = 0;
                }

                if (actionTimer < actionTime) {
                    actionTimer += delta;


                } /*else {
                    currentFrame.setRegion(Assets.getAnimation(4, 6, .125f, map).getKeyFrame(actionTime, false));
                }*/
                //currentFrame.setRegion(Assets.getAnimation(4, 6, .1f, map).getKeyFrame(actionTimer, false));

                //break;
            default:
                break;
        }
        /*if (playerControlHandler.dirLeft) {
            Gdx.app.log("player", "dir <<<");
            if (playerControlHandler.actRollLeft) {
                currentFrame.setRegion(map[6][0]);
                currentFrame.setRotation(currentFrame.getRotation() + 10 + delta);
            } else {
                currentFrame.setRegion((Assets.getAnimation(2, 6, .125f, map).getKeyFrame(stateTime)));
                currentFrame.setRotation(0);
            }
        }
        else if (playerControlHandler.dirRight) {
            Gdx.app.log("player", "dir >>>");
            if (playerControlHandler.actRollRight) {
                currentFrame.setRegion(map[5][0]);
                currentFrame.setRotation(currentFrame.getRotation() - 10 + delta);
            } else {
                currentFrame.setRegion((Assets.getAnimation(3, 6, .125f, map).getKeyFrame(stateTime)));
                currentFrame.setRotation(0);
            }
        }
        else if (playerControlHandler.actSit) {
            Gdx.app.log("player", "sit");
            if (playerControlHandler.justSit) {
                actionTime = .25f;
                actionTimer = 0;
                playerControlHandler.justSit = false;
            }
            if (actionTimer < actionTime) {
                actionTimer += delta;
            }
            currentFrame.setRegion((Assets.getAnimation(1, 3, .125f, map).getKeyFrame(actionTimer, false)));
        }
        /*else if (playerControlHandler.actUnsit && actionTimer > 0) {
            Gdx.app.log("player", "dir unsit");
            actionTimer -= delta;
            currentFrame.setRegion((Assets.getAnimation(1, 3, .125f, map).getKeyFrame(actionTimer, false)));
        }*/
        /*else if (playerControlHandler.actJump) {
            /*if (playerControlHandler.actUnsit && actionTimer > 0) {
                Gdx.app.log("player", "dir unsit in jump");
                actionTimer -= delta;
                currentFrame.setRegion((Assets.getAnimation(1, 3, .125f, map).getKeyFrame(actionTimer, false)));
            } else {
                Gdx.app.log("player", "dir jump");
                if (playerControlHandler.justJump) {
                    actionTime = .75f;
                    actionTimer = 0;
                    playerControlHandler.justJump = false;
                }
                if (actionTimer < actionTime) {
                    actionTimer += delta;
                }
                currentFrame.setRegion((Assets.getAnimation(4, 6, .125f, map).getKeyFrame(actionTimer, false)));
            //}

        }
        else {
            currentFrame = new Sprite(map[0][0]);
        }*/

        /*if (direction == LEFT) {
            if (subDirection == ATTACK_LEFT) {
                currentFrame = new Sprite(map[0][1]);
            } else {
                currentFrame = new Sprite(Assets.getAnimation(3, 3, .25f, map).getKeyFrame(stateTime));
            }

        } else if (direction == RIGHT) {
            if (subDirection == ATTACK_LEFT) {
                currentFrame = new Sprite(map[0][2]);
            } else {
                currentFrame = new Sprite(Assets.getAnimation(2, 3, .25f, map).getKeyFrame(stateTime));
            }
        } else if (direction == SIT) {
            currentFrame = new Sprite(map[0][0]);
        }
        else {
            currentFrame = new Sprite(map[1][0]);
        }*/
    }


}
