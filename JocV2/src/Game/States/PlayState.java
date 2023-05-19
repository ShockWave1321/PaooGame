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
    private Hero hero;
    private Map map;
    private ArrayList<Character> characters;
    boolean fight = false;
    private State battleState;
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
        for (Character character : characters)
        {
            hero.checkCollisionWith(character);
            character.Update();
        }

        /*if(fight)
        {

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


