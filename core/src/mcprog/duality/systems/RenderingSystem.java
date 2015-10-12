package mcprog.duality.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

import mcprog.duality.components.Mappers;
import mcprog.duality.components.TextureComponent;
import mcprog.duality.components.TransformComponent;
import mcprog.duality.library.Reference;

/**
 * Created by mcprog on 10/6/2015.
 */
public class RenderingSystem extends IteratingSystem {

    private static final float PIXELS_TO_METERS = 1.0f / 1.0f;
    private static final float FRUSTUM_WIDTH = Reference.W_WIDTH * PIXELS_TO_METERS;
    private static final float FRUSTUM_HEIGHT = Reference.W_HEIGHT * PIXELS_TO_METERS;


    private SpriteBatch batch;
    private Array<Entity> renderQueue;
    private Comparator<Entity> comparator;
    private OrthographicCamera camera;

    public RenderingSystem (SpriteBatch batch) {
        super(Family.all(TextureComponent.class, TransformComponent.class).get());

        renderQueue = new Array<Entity>();

        comparator = new Comparator<Entity>() {
            @Override
            public int compare(Entity entityA, Entity entityB) {
                ComponentMapper<TransformComponent> tc = Mappers.transformMapper;
                return (int) Math.signum(tc.get(entityB).position.z - tc.get(entityA).position.z);
            }
        };

        this.batch = batch;
        camera = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        camera.position.set(FRUSTUM_WIDTH / 2f, FRUSTUM_HEIGHT / 2f, 0);
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        renderQueue.sort(comparator);// Sorts the queue so z order is accounted for

        camera.update();
        draw();
    }

    public void draw () {

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (Entity entity : renderQueue) {// Loops through ordered queue
            TextureComponent textureComponent = Mappers.textureMapper.get(entity);
            TransformComponent transformComponent = Mappers.transformMapper.get(entity);

            /*
            Nothing to draw? Move on to the next one
             */
            if (textureComponent.region == null) {
                continue;
            }



            float width = textureComponent.region.getRegionWidth();
            float height = textureComponent.region.getRegionHeight();
            float originX = width * 0.5f;
            float originY = height * 0.5f;

            batch.draw(
                    textureComponent.region,
                    transformComponent.position.x - originX,
                    transformComponent.position.y - originY,
                    originX,
                    originY,
                    width,
                    height,
                    transformComponent.scale.x * PIXELS_TO_METERS,
                    transformComponent.scale.y * PIXELS_TO_METERS,
                    MathUtils.radiansToDegrees * transformComponent.rotation
            );
        }// End of for loop
        batch.end();

        renderQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    /*public OrthographicCamera getCamera () {
        return camera;
    }*/
}
