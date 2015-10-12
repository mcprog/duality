package mcprog.duality.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import mcprog.duality.components.Mappers;
import mcprog.duality.components.StateComponent;

/**
 * Created by mcprog on 10/6/2015.
 */
public class StateSystem extends IteratingSystem {

    public StateSystem () {
        super(Family.all(StateComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Mappers.stateMapper.get(entity).time += deltaTime;
    }
}
