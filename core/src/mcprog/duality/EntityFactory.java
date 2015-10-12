package mcprog.duality;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;

import mcprog.duality.components.CameraComponent;
import mcprog.duality.components.PhysicsComponent;
import mcprog.duality.components.TextureComponent;
import mcprog.duality.components.TransformComponent;
import mcprog.duality.systems.RenderingSystem;

/**
 * Created by mcprog on 10/7/2015.
 */
public class EntityFactory {

    protected PooledEngine engine;

    public EntityFactory (PooledEngine engine) {
        this.engine = engine;

    }

    public void create () {

        //createCamera(createCoin(0, 0));

    }

    private Entity createCoin(float x, float y) {
        /*Entity entity = engine.createEntity();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 0);

        PhysicsComponent physics = engine.createComponent(PhysicsComponent.class);
        physics.physicsBody =
        TransformComponent transform = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = new TextureRegion(new Texture(Gdx.files.internal("images/ore-tiles.png")), 0, 0, 16, 16);




        entity.add(physics);
        entity.add(transform);
        entity.add(texture);

        engine.addEntity(entity);*/
        return null;
    }

    /*private void createCamera (Entity target) {
        Entity entity = engine.createEntity();

        CameraComponent cameraComponent = engine.createComponent(CameraComponent.class);
        cameraComponent.camera = engine.getSystem(RenderingSystem.class).getCamera();
        cameraComponent.target = target;

        entity.add(cameraComponent);

        engine.addEntity(entity);
    }*/
}
