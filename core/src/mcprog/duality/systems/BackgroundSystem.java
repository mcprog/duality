package mcprog.duality.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;

import mcprog.duality.components.BackgroundComponent;
import mcprog.duality.components.Mappers;
import mcprog.duality.components.TransformComponent;

/**
 * Created by mcprog on 10/6/2015.
 */
public class BackgroundSystem extends IteratingSystem {

    private OrthographicCamera camera;

    public BackgroundSystem () {
        super(Family.all(BackgroundComponent.class).get());
    }

    public void setCamera (OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent t = Mappers.transformMapper.get(entity);
        t.position.set(camera.position.x, camera.position.y, 10.0f);
    }
}
