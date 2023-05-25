package Game.States;

import Game.Graphics.Assets;
import Game.Map.Map;
import Game.Objects.*;
import Game.Objects.Character;
import Game.RefLinks;

import java.awt.*;
import java.util.ArrayList;

public class PlayState extends State
{
    private final Hero hero;
    private final Map map;
    private final ArrayList<Character> characters;
    private final ArrayList<Item> items;
    Font font;
    int width, height;
    public PlayState(RefLinks refLink) {
        super(refLink);
        map = new Map(refLink);
        refLink.SetMap(map);
        hero = new Hero(refLink, 8 * 32, 43 * 32); //23*48 = 1104  27*48 = 1296
        refLink.setHero(hero);

        font = new Font("Arial", Font.PLAIN, 30);
        width = refLink.GetWidth();
        height = refLink.GetHeight();

        characters = new ArrayList<>();
        characters.add(new NPC(refLink, 32 * 9, 32 * 43));
        characters.add(new Enemy(refLink, 32 * 2, 32 * 42));
        //characters.add(new Enemy(refLink, 32 * 20, 32 * 42));
        items = new ArrayList<>();
        items.add(new Chest(32 * 6, 32 * 42, 5, 10));
    }

    @Override
    public void Update()
    {
        map.Update();
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
                    System.out.println("Touches");
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
    }
}


