package mcprog.duality.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import mcprog.duality.library.Reference;

/**
 * Created by mcprog on 10/13/2015.
 */
public class Terrain {

    protected Tile[][] tiles;
    private TextureRegion oreTile;

    protected int width;
    protected int height;

    public Terrain (int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        oreTile = new TextureRegion(new Texture(Gdx.files.internal("images/ore-tiles.png")), 0, 0, 16, 16);
    }

    public void loadTestMap (World world) {
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                boolean spawn;
                if (y % 5 < 2) {
                    spawn = Math.random() >= .25f;
                } else {
                    spawn = Math.random() >= 95f;
                }

                if (x % 10 < Math.random() * 4 + 1) {
                    spawn = Math.random() >= .95f;
                }
                if (spawn) {
                    tiles[x][y] = new Tile(oreTile, x * 16f * Reference.PIXELS_TO_METERS, y * 16f * Reference.PIXELS_TO_METERS);
                    tiles[x][y].create(world);
                }

            }

        }
    }

    public void draw (SpriteBatch batch) {
        for (Tile[] tArr : tiles) {
            for (Tile t : tArr) {
                if (t != null) {
                    t.draw(batch);
                }
            }

        }
    }
}
