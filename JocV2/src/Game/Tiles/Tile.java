package Game.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Tile
{
    private static final int NO_TILES   = 32;
    public static Tile[] tiles          = new Tile[NO_TILES];
    public static Tile sandTile        = new SandTile(0);
    public static Tile dryTreeTile     = new DryTreeTile(1);
    public static Tile stoneTile        = new StoneTile(2);
    public static Tile waterGrassTile   = new WaterGrassTile(3);
    public static Tile tentTile         = new TentTile(4);
    public static Tile towerTile        = new TowerTile(5);
    public static Tile voidTile         = new VoidTile(6);
    public static Tile lavaTile         = new LavaTile(7);
    public static Tile centerDirtTile   = new CenterDirtTile(8);
    public static Tile centerPavementTile = new CenterPavementTile(9);
    public static Tile boulderTile      = new BoulderTile(10);
    public static final int TILE_WIDTH  = 32;
    public static final int TILE_HEIGHT = 32;

    protected BufferedImage img;
    protected final int id;

    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;

        tiles[id] = this;
    }
    public void Update()
    {

    }
    public void Draw(Graphics g, int x, int y)
    {
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }
    public boolean IsSolid()
    {
        return false;
    }
    public int GetId()
    {
        return id;
    }
}
