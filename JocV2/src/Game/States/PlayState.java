package Game.States;

import Game.Map.Map;
import Game.Objects.Character;
import Game.Objects.Hero;
import Game.Objects.NPC;
import Game.RefLinks;

import java.awt.*;
import java.util.ArrayList;

public class PlayState extends State
{
    private final Hero hero;
    private final Map map;
    private final ArrayList<Character> characters;
    boolean fight = false;
    private State battleState;
    int i = 0;
    public PlayState(RefLinks refLink)
    {
        super(refLink);
        map = new Map(refLink);
        refLink.SetMap(map);
        hero = new Hero(refLink,8*32, 43*32); //23*48 = 1104  27*48 = 1296
        refLink.setHero(hero);
        battleState = new BattleState(refLink);

        characters = new ArrayList<>();
        characters.add(new NPC(refLink,32 * 9,32 * 43));
    }

    @Override
    public void Update()
    {
        map.Update();
        hero.Update();
        for (int i = 0; i< characters.size(); ++i)
        {
            if(hero.CheckItemCollision(characters.get(i)))
            {
                if(characters.get(i) instanceof NPC)
                {
                    //refLink.GetGame().NextState();
                    //characters.remove(characters.get(i));
                    ((NPC) characters.get(i)).Interact();
                }
            }
        }
        for (Character character : characters)
        {
            character.Update();
        }

        /*
        for (int i = 0; i< characters.size(); ++i)
        {
            characters.get(i).Update();
        }
        if(fight)
        {
            reflink.GetGame().SetState(battleState);
        }*/
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
    }
}


