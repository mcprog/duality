package mcprog.duality.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import mcprog.duality.library.Reference;
import mcprog.duality.utility.LogHelper;

/**
 * Created by mcprog on 10/13/2015.
 */
public class Terrain {

    protected Tile[][] tiles;
    private TextureRegion stone;
    private TextureRegion[][] stoneTileset;

    protected int width;
    protected int height;

    public Terrain (int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        stone = new TextureRegion(new Texture(Gdx.files.internal("images/stone-tiles1.png")));
        stoneTileset = stone.split(16, 16);
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
                    tiles[x][y] = new Tile(stoneTileset[0][0], x * 16f * Reference.PIXELS_TO_METERS, y * 16f * Reference.PIXELS_TO_METERS);
                    tiles[x][y].create(world);
                }

            }

        }
    }

    public void update () {
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                if (tiles[x][y] != null) {
                    if (tiles[x][y].body.getUserData().equals("--health")) {

                        tiles[x][y].decrementHealth();
                        Gdx.app.log(LogHelper.getClassyTag(this), "health of tile --");
                        tiles[x][y].body.setUserData("tile");
                        if (tiles[x][y].isDead()) {
                            tiles[x][y].body.setUserData("delete");
                            tiles[x][y] = null;
                        }
                    } else {
                        determineTiles(x, y);
                    }

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

    private void determineTiles (int x, int y) {
        String tileDeterminer = getTouchingUp(x, y) + getTouchingDown(x, y) + getTouchingLeft(x, y) + getTouchingRight(x, y);

        if (tileDeterminer.equals("Up")) {
            tiles[x][y].setRegion(stoneTileset[1][4]);
        }
        else if (tileDeterminer.equals("Down")) {
            tiles[x][y].setRegion(stoneTileset[1][3]);
        }
        else if (tileDeterminer.equals("Left")) {
            tiles[x][y].setRegion(stoneTileset[1][2]);
        }
        else if (tileDeterminer.equals("Right")) {
            tiles[x][y].setRegion(stoneTileset[1][1]);
        }

        else if (tileDeterminer.equals("UpDown")) {
            tiles[x][y].setRegion(stoneTileset[1][6]);
        }
        else if (tileDeterminer.equals("LeftRight")) {
            tiles[x][y].setRegion(stoneTileset[1][5]);
        }

        else if (tileDeterminer.equals("UpLeft")) {
            tiles[x][y].setRegion(stoneTileset[1][0]);
        }
        else if (tileDeterminer.equals("UpRight")) {
            tiles[x][y].setRegion(stoneTileset[0][7]);
        }
        else if (tileDeterminer.equals("DownLeft")) {
            tiles[x][y].setRegion(stoneTileset[0][6]);
        }
        else if (tileDeterminer.equals("DownRight")) {
            tiles[x][y].setRegion(stoneTileset[0][5]);
        }

        else if (tileDeterminer.equals("UpDownLeft")) {
            tiles[x][y].setRegion(stoneTileset[0][2]);
        }
        else if (tileDeterminer.equals("UpDownRight")) {
            tiles[x][y].setRegion(stoneTileset[0][1]);
        }
        else if (tileDeterminer.equals("UpLeftRight")) {
            tiles[x][y].setRegion(stoneTileset[0][4]);
        }
        else if (tileDeterminer.equals("DownLeftRight")) {
            tiles[x][y].setRegion(stoneTileset[0][3]);
        }
        else if (tileDeterminer.equals("UpDownLeftRight")) {
            tiles[x][y].setRegion(stoneTileset[0][0]);
        }

        else {
            tiles[x][y].setRegion(stoneTileset[1][7]);
        }
    }

    protected String getTouchingUp (int x, int y) {
        if (y < height - 1) {// Safety check for off by on error
            return tiles[x][y + 1] != null ? "Up" : "";
        }
        return "";
    }

    protected String getTouchingDown (int x, int y) {
        if (y > 0) {
            return tiles[x][y - 1] != null ? "Down" : "";
        }
        return "";
    }

    protected String getTouchingLeft (int x, int y) {
        if (x > 0) {// Safety check for off by on error
            return tiles[x - 1][y] != null ? "Left" : "";
        }
        return "";
    }

    protected String getTouchingRight (int x, int y) {
        if (x < width - 1) {// Safety check for off by on error
            return tiles[x + 1][y] != null ? "Right" : "";
        }
        return "";
    }

}
