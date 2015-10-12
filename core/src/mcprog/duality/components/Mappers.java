package mcprog.duality.components;

import com.badlogic.ashley.core.ComponentMapper;

/**
 * Created by mcprog on 9/30/2015.
 */
public class Mappers {

    //TODO add ashley retrieval component mappers
    public static final ComponentMapper<StateComponent> stateMapper = ComponentMapper.getFor(StateComponent.class);
    public static final ComponentMapper<TextureComponent> textureMapper = ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<AnimationComponent> animationMapper = ComponentMapper.getFor(AnimationComponent.class);
    public static final ComponentMapper<TransformComponent> transformMapper = ComponentMapper.getFor(TransformComponent.class);
    public static final ComponentMapper<CameraComponent> cameraMapper = ComponentMapper.getFor(CameraComponent.class);
    public static final ComponentMapper<PhysicsComponent> physicsMapper = ComponentMapper.getFor(PhysicsComponent.class);

}
