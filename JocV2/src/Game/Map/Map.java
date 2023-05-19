package Game.Map;

import Game.Objects.Hero;
import Game.RefLinks;
import Game.Tiles.Tile;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map
{
    private RefLinks refLink;
    private int width;
    private int height;
    private int [][] tiles;
    private int level = 1;
    private final Point spawnPoint = new Point();
    private final Point nextPoint = new Point();

    public Map(RefLinks refLink)
    {
        this.refLink = refLink;
        LoadWorld(level);
    }
    public void Update()
    {
        if(
            (int)refLink.getHero().GetX()/Tile.TILE_WIDTH == nextPoint.x &&
            (int)refLink.getHero().GetY()/Tile.TILE_WIDTH == nextPoint.y
        )
        {
            nextLevel();
            refLink.getHero().SetX(spawnPoint.x*Tile.TILE_HEIGHT);
            refLink.getHero().SetY(spawnPoint.y*Tile.TILE_HEIGHT);
        }
    }
    /*
    int offsetX = (int) ((refLink.GetWidth()/2) - refLink.getHero().GetX());
        int offsetY = (int) ((refLink.GetHeight()/2) - refLink.getHero().GetY());
        g.translate(offsetX, offsetY);
        for (int y =-10; y < 50 / Tile.TILE_HEIGHT + 9; y++) {
            for (int x = -15; x < 50 / Tile.TILE_WIDTH + 13; x++) {
                GetTile(x, y).Draw(g, (int) x * Tile.TILE_HEIGHT, (int) y * Tile.TILE_WIDTH);
            }
        }
    */
    public void Draw(Graphics g)
    {
        int offsetX = (int) (refLink.GetWidth()/2 - refLink.getHero().GetX());
        int offsetY = (int) (refLink.GetHeight()/2 - refLink.getHero().GetY());
        g.translate(offsetX, offsetY);
        for (int y = 0; y < refLink.GetHeight() / Tile.TILE_HEIGHT; y++) {
            for (int x = 0; x < refLink.GetWidth() / Tile.TILE_WIDTH; x++) {
                GetTile(x, y).Draw(g, (int) x * Tile.TILE_HEIGHT, (int) y * Tile.TILE_WIDTH);
            }
        }
        /*
        for(int y = -10; y <= refLink.GetGame().GetHeight()/Tile.TILE_HEIGHT + 10; ++y)
        {
            for(int x = -10; x <= refLink.GetGame().GetWidth()/Tile.TILE_WIDTH + 10; ++x)
            {
                Tile.voidTile.Draw(g, x * Tile.TILE_HEIGHT, y * Tile.TILE_WIDTH);
            }
        }

        for(int worldY = 0; worldY < height; worldY++)
        {
            for(int worldX = 0; worldX < width; worldX++)
            {
                int tileX = worldX * Tile.TILE_HEIGHT;
                int tileY = worldY * Tile.TILE_WIDTH;
                Hero hero = refLink.getHero();

                int heroX = (int)hero.GetX();
                int heroY = (int)hero.GetY();
                int heroSX = hero.getScreenX();
                int heroSY = hero.getScreenY();

                int screenX = tileX - heroX + heroSX;
                int screenY = tileY - heroY + heroSY;

                if(
                    tileX + Tile.TILE_HEIGHT > heroX - heroSX &&
                    tileX - Tile.TILE_HEIGHT < heroX + heroSX &&
                    tileY + Tile.TILE_WIDTH > heroY - heroSY &&
                    tileY - Tile.TILE_WIDTH < heroY + heroSY
                )
                {
                    GetTile(worldX, worldY).Draw(g, screenX, screenY);
                }
            }
        }
         */
    }
    public Tile GetTile(int x, int y)
    {
        if(x < 0 || y < 0 || x >= width || y >= height)
        {
            return Tile.voidTile;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
        {
            return Tile.sandTile;
        }
        return t;
    }
    private void LoadWorld(int level)
    {
        try {
            File inputFile = new File("Resources/Maps/Map"+ level +".txt");
            Scanner scanner = new Scanner(inputFile);

            height = scanner.nextInt();
            width = scanner.nextInt();
            nextPoint.setPoint(scanner.nextInt(), scanner.nextInt());
            spawnPoint.setPoint(scanner.nextInt(), scanner.nextInt());
            tiles = new int[width][height];

            for(int y = 0; y < height; y++)
            {
                for(int x = 0; x < width; x++)
                {
                    tiles[x][y] = scanner.nextInt();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    public void nextLevel()
    {
        if (level == 2)
        {
            level = 1;
        }
        else
        {
            level = 2;
        }
        LoadWorld(level);
    }
}
