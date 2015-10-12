package mcprog.duality.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by mcprog on 10/6/2015.
 */
public class StateComponent implements Component {

    private int state;
    public float time;

    public int get () {
        return state;
    }

    public void set (int newState) {
        state = newState;
        time = 0;
    }
}
