package mcprog.duality.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Animation;

import mcprog.duality.components.AnimationComponent;
import mcprog.duality.components.Mappers;
import mcprog.duality.components.StateComponent;
import mcprog.duality.components.TextureComponent;

/**
 * Created by mcprog on 10/6/2015.
 */
public class AnimationSystem extends IteratingSystem {

    public AnimationSystem () {
        super(Family.all(TextureComponent.class, AnimationComponent.class, StateComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        long id = entity.getId();
        TextureComponent textureComponent = Mappers.textureMapper.get(entity);
        AnimationComponent animationComponent = Mappers.animationMapper.get(entity);
        StateComponent stateComponent = Mappers.stateMapper.get(entity);

        Animation animation = animationComponent.animations.get(stateComponent.get());

        if (animation != null) {
            textureComponent.region = animation.getKeyFrame(stateComponent.time);
        }

        stateComponent.time += deltaTime;
    }
}
