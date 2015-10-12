package mcprog.duality.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import mcprog.duality.components.CameraComponent;
import mcprog.duality.components.Mappers;
import mcprog.duality.components.TransformComponent;

/**
 * Created by mcprog on 10/6/2015.
 */
public class CameraSystem extends IteratingSystem {

    public CameraSystem () {
        super(Family.all(CameraComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CameraComponent cameraComponent = Mappers.cameraMapper.get(entity);

        if (cameraComponent.target == null) {
            return;
        }

        TransformComponent target = Mappers.transformMapper.get(cameraComponent.target);

        if (target == null) {
            return;
        }

        cameraComponent.camera.position.set(target.position);
        cameraComponent.camera.viewportWidth *= cameraComponent.zoom;
        cameraComponent.camera.viewportHeight *= cameraComponent.zoom;
    }
}
