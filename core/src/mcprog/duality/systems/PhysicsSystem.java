package mcprog.duality.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import mcprog.duality.components.Mappers;
import mcprog.duality.components.PhysicsComponent;
import mcprog.duality.components.TransformComponent;

/**
 * Created by mcprog on 10/9/2015.
 */
public class PhysicsSystem extends IteratingSystem {

    public PhysicsSystem () {
        super(Family.all(PhysicsComponent.class, TransformComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PhysicsComponent physicsComponent = Mappers.physicsMapper.get(entity);
        TransformComponent transformComponent = Mappers.transformMapper.get(entity);

        Vector2 position = physicsComponent.physicsBody.getPosition();
        transformComponent.position.set(position, transformComponent.position.z);
    }
}
