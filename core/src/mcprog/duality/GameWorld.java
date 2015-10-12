package mcprog.duality;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import mcprog.duality.components.CameraComponent;
import mcprog.duality.components.PhysicsComponent;
import mcprog.duality.components.TextureComponent;
import mcprog.duality.components.TransformComponent;
import mcprog.duality.systems.AnimationSystem;
import mcprog.duality.systems.BackgroundSystem;
import mcprog.duality.systems.CameraSystem;
import mcprog.duality.systems.PhysicsSystem;
import mcprog.duality.systems.RenderingSystem;
import mcprog.duality.systems.StateSystem;

/**
 * Created by mcprog on 10/9/2015.
 */
public class GameWorld {

    protected World physicsWorld;
    protected PooledEngine ashleyEngine;
    protected Duality game;
    protected OrthographicCamera camera;


    public static final Vector2 gravity = new Vector2(0, -9.81f);
    public static final float timeStep = 1/60f;
    public static final int velocityIterations = 8;
    public static final int positionIterations = 3;

    private Box2DDebugRenderer debug;
    private Entity physicsBody;


    public GameWorld (Duality game, OrthographicCamera camera) {
        this.game = game;
        this.camera = camera;
        physicsWorld = new World(gravity, true);
        ashleyEngine = new PooledEngine();

        ashleyEngine.addSystem(new AnimationSystem());
        ashleyEngine.addSystem(new BackgroundSystem());
        ashleyEngine.addSystem(new CameraSystem());
        ashleyEngine.addSystem(new PhysicsSystem());
        ashleyEngine.addSystem(new RenderingSystem(game.gameBatch));
        ashleyEngine.addSystem(new StateSystem());

        //ashleyEngine.getSystem(BackgroundSystem.class).setCamera(ashleyEngine.getSystem(RenderingSystem.class).getCamera());

        debug = new Box2DDebugRenderer();
        physicsBody = createPhysicsBody(0, 0, BodyDef.BodyType.StaticBody);
    }

    public void create () {
        createCamera(physicsBody);
        createPhysicsBody(0, 5, BodyDef.BodyType.DynamicBody);
    }

    public void update (float delta) {
        physicsWorld.step(timeStep, velocityIterations, positionIterations);
        ashleyEngine.update(delta);
    }

    public void draw () {
        //debug.render(physicsWorld, ashleyEngine.getSystem(RenderingSystem.class).getCamera().combined);
    }

    private Entity createPhysicsBody (float x, float y, BodyDef.BodyType bodyType) {
        Entity entity = ashleyEngine.createEntity();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;

        bodyDef.position.set(x, y);

        PhysicsComponent physics = ashleyEngine.createComponent(PhysicsComponent.class);
        physics.physicsBody = physicsWorld.createBody(bodyDef);
        TransformComponent transform = ashleyEngine.createComponent(TransformComponent.class);
        TextureComponent texture = ashleyEngine.createComponent(TextureComponent.class);
        texture.region = new TextureRegion(new Texture(Gdx.files.internal("images/ore-tiles.png")), 0, 0, 16, 16);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.25f, 0.25f);
        physics.physicsBody.createFixture(shape, 1);





        entity.add(transform);
        entity.add(physics);
        entity.add(texture);

        ashleyEngine.addEntity(entity);
        return entity;
    }

    private void createCamera (Entity target) {
        Entity entity = ashleyEngine.createEntity();

        CameraComponent cameraComponent = ashleyEngine.createComponent(CameraComponent.class);
        //cameraComponent.camera = ashleyEngine.getSystem(RenderingSystem.class).getCamera();
        cameraComponent.target = target;

        entity.add(cameraComponent);

        ashleyEngine.addEntity(entity);
    }

    public void pauseSystems () {
        for (EntitySystem es : ashleyEngine.getSystems()) {
            es.setProcessing(false);
        }
    }

    public void resumeSystems () {
        for (EntitySystem es : ashleyEngine.getSystems()) {
            es.setProcessing(true);
        }
    }
}
