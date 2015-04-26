package com.mcprog.duality.library;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by mcprog on 4/25/2015.
 */
public class Assets {

    public static Animation getAnimation (int row, int frames, float length, TextureRegion[][] map) {
        Array<TextureRegion> holder = new Array<TextureRegion>();
        for (int i = 0; i < frames; ++i) {
            holder.add(map[row][i]);
        }
        return new Animation(length, holder, Animation.PlayMode.LOOP);
    }
}
