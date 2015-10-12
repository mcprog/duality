package mcprog.duality.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by mcprog on 10/6/2015.
 */
public class CameraComponent implements Component {

    public Entity target;
    public OrthographicCamera camera;
    public float zoom = 1;

}
