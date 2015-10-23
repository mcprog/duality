package mcprog.duality.core.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.awt.event.ContainerListener;

import mcprog.duality.Duality;
import mcprog.duality.core.skills.SkillArrow;
import mcprog.duality.core.skills.SkillFireball;
import mcprog.duality.library.Assets;
import mcprog.duality.library.Reference;
import mcprog.duality.screens.MenuScreen;
import mcprog.duality.ui.MenuFactory;
import mcprog.duality.utility.LogHelper;
import mcprog.duality.utility.PlatformSpecific;

/**
 * Created by mcprog on 10/9/2015.
 */
public class Player {

    public int attackQueue;

    public static int LEFT = -1;
    public static int RIGHT = 1;
    public static int UP = 1;
    public static int DOWN = -1;

    protected float x;
    protected float y;
    protected Sprite sprite;
    protected TextureRegion[][] regions;
    protected BodyDef bodyDef;
    protected Body body;
    protected float width;
    protected float height;
    //protected Array<Attack> primaryAttacks;
    //protected Array<Attack> secondaryAttacks;

    private int dirX;
    private int dirY;
    private int left;
    private int right;

    private int atkDirX;
    private int atkDirY;
    //private float attackCoolDown;
    //private Sound fireballWhoosh;

    //private TextureRegion fireball;
    private SkillFireball skillFireball;
    private SkillArrow skillArrow;
    private Duality game;
    private World world;
    private Fixture fixture;

    public boolean canJump;

    public Player (TextureRegion tileRegion, float x, float y, Duality game, World world) {
        this.game = game;
        this.world = world;
        regions = tileRegion.split(24, 32);
        sprite = new Sprite(regions[0][0]);
        skillFireball = new SkillFireball();
        skillArrow = new SkillArrow();

        this.x = x;
        this.y = y;
        width = sprite.getRegionWidth() * Reference.PIXELS_TO_METERS;
        height = sprite.getRegionHeight() * Reference.PIXELS_TO_METERS;
        sprite.setSize(width, height);
        sprite.setCenter(x, y);
        sprite.setOrigin(width / 2, height / 2);
        bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
    }

    public void create (World world) {
        body = world.createBody(bodyDef);
        body.setUserData(this);
        ChainShape chainShape = new ChainShape();
        float[] points = {
                -.2f, -.45f,
                -.2f, .45f,
                .2f, .45f,
                .2f, -.45f
        };
        chainShape.createLoop(points);
        PolygonShape square = new PolygonShape();
        square.setAsBox(.2f, .45f);
        fixture = body.createFixture(chainShape, 1);
        square.dispose();
        chainShape.dispose();
        createFoot();
    }

    private void createFoot () {
        FixtureDef footFixtureDef = new FixtureDef();
        footFixtureDef.isSensor = true;
        PolygonShape rect = new PolygonShape();
        rect.setAsBox(.125f, .125f, new Vector2(0, -.45f), 0);
        footFixtureDef.shape = rect;

        body.createFixture(footFixtureDef).setUserData("foot");
        rect.dispose();
    }

    public void update (float delta) {

        dirX = left + right;
        float posX = body.getPosition().x;
        float posY = body.getPosition().y;
        if (dirX != 0) {
            if (dirX > 0) {
                sprite.setRegion(regions[0][0]);
                body.applyLinearImpulse(.1f, 0, posX, posY, true);
            } else {
                sprite.setRegion(regions[0][1]);
                body.applyLinearImpulse(-.1f, 0, posX, posY, true);
            }
        }

        if (PlatformSpecific.isMobile()) {
            if (Gdx.input.getAccelerometerY() > 2) {
                body.applyLinearImpulse(.1f, 0, posX, posY, true);
            }
            else if (Gdx.input.getAccelerometerY() < -2) {
                body.applyLinearImpulse(-.1f, 0, posX, posY, true);
            }
        }

        sprite.setRotation(MathUtils.radDeg * body.getAngle());
        sprite.setCenter(body.getPosition().x, body.getPosition().y);

        skillFireball.update(delta);
        skillArrow.update(delta);
    }

    public void update (float delta, boolean canJump) {
        this.canJump = canJump;
        this.update(delta);
    }

    public void draw (SpriteBatch batch) {
        sprite.draw(batch);
        skillFireball.draw(batch);
        skillArrow.draw(batch);
    }

    public Vector2 getPosition () {
        return body.getPosition();
    }

    public void jump () {
        body.applyLinearImpulse(0, 7, body.getPosition().x, body.getPosition().y, true);
    }

    public void assignLeft () {
        left = LEFT;
        atkDirX = LEFT;
    }

    public void assignRight () {
        right = RIGHT;
        atkDirX = RIGHT;
    }

    public void assignUp () {
        atkDirY = UP;
        if (left == 0 && right == 0) {
            atkDirX = 0;
        }
    }

    public void assignDown () {
        atkDirY = DOWN;
        if (left == 0 && right == 0) {
            atkDirX = 0;
        }
    }

    public void designLeft () {
        left = 0;
    }

    public void designRight () {
        right = 0;
    }

    public void designUp () {
        atkDirY = 0;
    }

    public void designDown () {
        atkDirY = 0;
    }

    public void primaryAttack () {
        skillFireball.launch(world, getPosition().x, getPosition().y, atkDirX, atkDirY);
    }




}
