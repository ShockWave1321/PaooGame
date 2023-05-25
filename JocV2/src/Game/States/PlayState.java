package Game.States;

import Game.Graphics.Assets;
import Game.Map.Map;
import Game.Map.Point;
import Game.Objects.*;
import Game.Objects.Character;
import Game.RefLinks;
import Game.Tiles.Tile;

import java.awt.*;
import java.util.ArrayList;

public class PlayState extends State
{
    private final Hero hero;
    private final Map map;
    private ArrayList<Character> characters;
    private ArrayList<Item> items;
    Font font;
    int width, height;
    int currentLevel = 0;
    public PlayState(RefLinks refLink)
    {
        super(refLink);

        hero = new Hero(refLink, 8 * 32, 43 * 32); //23*48 = 1104  27*48 = 1296 || 8 * 32, 43 * 32
        refLink.setHero(hero);

        map = new Map(refLink);
        refLink.SetMap(map);

        font = new Font("Arial", Font.PLAIN, 30);
        width = refLink.GetWidth();
        height = refLink.GetHeight();

        characters = new ArrayList<>();
        items = new ArrayList<>();

        LoadState(currentLevel);
    }

    @Override
    public void Update()
    {
        map.Update();
        LoadState(map.GetLevel());
        hero.Update();
        for (int i = 0; i < characters.size(); ++i)
        {
            if(characters.get(i) instanceof NPC)
            {
                if(hero.CheckItemCollision(characters.get(i)))
                {
                    ((NPC) characters.get(i)).Interact1();
                }
                else
                if(hero.CheckAbilityCollision(characters.get(i)))
                {
                    ((NPC) characters.get(i)).Interact2();
                }
                else
                {
                    ((NPC) characters.get(i)).EndInteraction();
                }
            }
            else
            if(characters.get(i) instanceof Enemy)
            {
                if(hero.CheckItemCollision(characters.get(i)))
                {
                    //System.out.println("Touches");
                    ((Enemy) characters.get(i)).Fights();
                    State.SetState(new BattleState(refLink, (Enemy) characters.get(i)));
                    items.add(((Enemy) characters.get(i)).Reward());
                    characters.remove(characters.get(i));
                }
            }
        }
        for(int i = 0; i < items.size(); ++i)
        {
            if(hero.CheckItemCollision(items.get(i)))
            {
                if(items.get(i) instanceof Chest)
                {
                    ((Chest) items.get(i)).Rewards(hero);
                    items.remove(items.get(i));
                }
            }
        }
        for (Character character : characters)
        {
            character.Update();
        }
    }
    @Override
    public void Draw(Graphics g)
    {
        map.Draw(g);
        hero.Draw(g);

        for (Character character : characters)
        {
            character.Draw(g);
        }
        for(Item i : items)
        {
            i.Draw(g);
        }
        UI(g);
    }
    public void UI(Graphics g)
    {
        g.setFont(font);
        g.setColor(Color.white);
        g.drawImage(
                Assets.coin,
                (int)hero.GetX() - width/2 + 35,
                (int)hero.GetY()- height/2 + 115,
                null
        );
        g.drawString(
                "x " + hero.GetMoney(),
                (int)hero.GetX() - width/2 + 75,
                (int)hero.GetY()- height/2 + 140
        );
        g.drawImage(
                Assets.scroll,
                (int)hero.GetX() - width/2 + 35,
                (int)hero.GetY()- height/2 + 145,
                null
        );
        g.drawString(
                "x " + hero.GetExperience(),
                (int)hero.GetX() - width/2 + 75,
                (int)hero.GetY()- height/2 + 170
        );
        g.drawString(
                "World: " + currentLevel,
                (int)hero.GetX() - width/2 + 35,
                (int)hero.GetY()- height/2 + 210
        );
        g.drawString(
                "Level: " + hero.GetLevel(),
                (int)hero.GetX() - width/2 + 35,
                (int)hero.GetY()- height/2 + 240
        );
    }
    public void LoadState(int level)
    {
        if (!(level == currentLevel))
        {
            currentLevel = level;
            ArrayList<Point> points = map.GetPoints();
            characters = new ArrayList<>();
            items = new ArrayList<>();
            int i = 0;
            int n = map.GetNPCs();
            for (; i < n; ++i)
            {
                characters.add(new NPC(refLink, points.get(i).GetX() * Tile.TILE_WIDTH, points.get(i).GetY() * Tile.TILE_HEIGHT));
            }
            n += map.GetCharacters();
            for (; i < n; ++i)
            {
                characters.add(new Enemy(refLink, points.get(i).GetX() * Tile.TILE_WIDTH, points.get(i).GetY() * Tile.TILE_HEIGHT, currentLevel));
            }
            n += map.GetItems();
            for (; i < n; ++i)
            {
                items.add(new Chest(points.get(i).GetX() * Tile.TILE_WIDTH, points.get(i).GetY() * Tile.TILE_HEIGHT));
                //System.out.println(points.get(i).GetX()+" "+points.get(i).GetY());
            }
        }
    }
}


