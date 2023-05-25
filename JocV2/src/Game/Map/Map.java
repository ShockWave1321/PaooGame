package Game.Map;

import Game.Objects.Hero;
import Game.RefLinks;
import Game.Tiles.Tile;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map
{
    private final RefLinks refLinks;
    private final Hero hero;
    private int width;
    private int height;
    private int [][] tiles;
    private int level = 1;

    private static Point currentPoint;
    private static Point spawnPoint;
    private static Point previousPoint;
    private static Point nextPoint;
    int charN, itemN, npcN;
    ArrayList<Point> Points;
    int t = 0;
    static int cooldown;
    public Map(RefLinks refLink)
    {
        this.refLinks = refLink;
        this.hero = refLink.GetHero();
        LoadWorld(level);

        currentPoint = new Point(
                ((int)hero.GetX() + 16)/Tile.TILE_WIDTH,
                ((int)hero.GetY() + 16)/Tile.TILE_HEIGHT
        );
        cooldown = 0;
    }
    public void Update()
    {
        currentPoint.SetPoint(
                ((int)hero.GetX() + 16)/Tile.TILE_WIDTH,
                ((int)hero.GetY() + 16)/Tile.TILE_HEIGHT
        );
        if(refLinks.GetKeyManager().event)
        {
            if (currentPoint.x == nextPoint.x && currentPoint.y == nextPoint.y)
            {
                if(cooldown <= 0)
                {
                    cooldown = 60;
                    NextLevel();
                    hero.SetX(spawnPoint.x * Tile.TILE_WIDTH);
                    hero.SetY(spawnPoint.y * Tile.TILE_HEIGHT);
                }
                else
                {
                    System.out.println("Not ready");
                }
            }
            else
            if (currentPoint.x == previousPoint.x && currentPoint.y == previousPoint.y)
            {
                if (cooldown <= 0)
                {
                    cooldown = 60;
                    PreviousLevel();
                    hero.SetX(nextPoint.x * Tile.TILE_WIDTH);
                    hero.SetY(nextPoint.y * Tile.TILE_HEIGHT);
                }
                else
                {
                    System.out.println("Not ready");
                }
            }
        }
        if(cooldown > 0)
        {
            cooldown--;
        }

        if(t > 2 * 60)
        {
            //System.out.println((int)hero.GetX()+" "+(int)hero.GetY()+" "+level);
            //System.out.println("__________________");
            //System.out.println(currentPoint.x+" "+currentPoint.y);
            //System.out.println(previousPoint.x+" "+previousPoint.y);
            //System.out.println(nextPoint.x+" "+nextPoint.y);
            //System.out.println(level);
            t = 0;
        }
        t++;
    }

    public void Draw(Graphics g)
    {
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                Tile.voidTile.Draw(g,x * Tile.TILE_HEIGHT,y * Tile.TILE_WIDTH);
            }
        }

        int offsetX = (int) (hero.GetScreenX() - hero.GetX());
        int offsetY = (int) (hero.GetScreenY() - hero.GetY());

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
            tiles = new int[width][height];

            for(int y = 0; y < height; y++)
            {
                for(int x = 0; x < width; x++)
                {
                    tiles[x][y] = scanner.nextInt();
                }
            }

            spawnPoint = new Point(scanner.nextInt(),scanner.nextInt());
            previousPoint = new Point(scanner.nextInt(),scanner.nextInt());
            nextPoint = new Point(scanner.nextInt(),scanner.nextInt());

            npcN = scanner.nextInt();
            Points = new ArrayList<>();
            //System.out.println(npcN);
            for(int i = 0; i < npcN; i++)
            {
                Points.add(new Point(scanner.nextInt(),scanner.nextInt()));
                //System.out.println(Points.get(i).x+" "+Points.get(i).y);
            }
            charN = scanner.nextInt();
            //System.out.println(charN);
            for(int i = npcN; i < npcN+charN; i++)
            {
                Points.add(new Point(scanner.nextInt(),scanner.nextInt()));
            }
            itemN = scanner.nextInt();
            //System.out.println(itemN);
            for(int i = npcN + charN ;i < npcN + charN + itemN; i++)
            {
                Points.add(new Point(scanner.nextInt(),scanner.nextInt()));
            }
            /*for(Point p : Points)
            {
                System.out.println(p.GetX()+" "+p.GetY());
            }*/
            scanner.close();
        } catch (FileNotFoundException e)
        {
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
    public void NextLevel()
    {
        if(level < 3)
        {
            level++;
        }
        LoadWorld(level);
    }
    public void PreviousLevel()
    {
        if(level > 1)
        {
            level--;
        }
        LoadWorld(level);
    }
    public ArrayList<Point> GetPoints()
    {
        return Points;
    }
    public int GetItems()
    {
        return itemN;
    }
    public int GetCharacters()
    {
        return charN;
    }
    public int GetNPCs()
    {
        return npcN;
    }
    public int GetLevel()
    {
        return level;
    }

}
