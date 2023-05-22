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
    private final RefLinks refLink;
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
            (int)refLink.GetHero().GetX()/Tile.TILE_WIDTH == nextPoint.x &&
            (int)refLink.GetHero().GetY()/Tile.TILE_WIDTH == nextPoint.y
        )
        {
            nextLevel();
            refLink.GetHero().SetX(spawnPoint.x * Tile.TILE_HEIGHT);
            refLink.GetHero().SetY(spawnPoint.y * Tile.TILE_HEIGHT);
        }
    }

    public void Draw(Graphics g)
    {
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                Tile.voidTile.Draw(g, (int) x * Tile.TILE_HEIGHT, (int) y * Tile.TILE_WIDTH);
            }
        }

        Hero hero = refLink.GetHero();
        int offsetX = (int) (hero.GetScreenX() - refLink.GetHero().GetX());
        int offsetY = (int) (hero.GetScreenY() - refLink.GetHero().GetY());

        g.translate(offsetX, offsetY);

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                GetTile(x, y).Draw(g, x * Tile.TILE_HEIGHT,y * Tile.TILE_WIDTH);
            }
        }
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
