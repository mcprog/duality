package mcprog.duality.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;

/**
 * Created by mcprog on 10/6/2015.
 */
public class AnimationComponent implements Component {

    public IntMap<Animation> animations = new IntMap<Animation>();
}
