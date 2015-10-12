package mcprog.duality.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by mcprog on 10/6/2015.
 */
public class TransformComponent implements Component {

    public final Vector3 position = new Vector3();
    public final Vector2 scale = new Vector2(1, 1);
    public float rotation;

}
