package Game;

import Game.Input.KeyManager;
import Game.Map.Map;
import Game.Objects.Hero;

public class RefLinks
{
    private Game game;
    private Map map;
    private Hero hero;


    public RefLinks(Game game)
    {
        this.game = game;
    }
    public KeyManager GetKeyManager()
    {
        return game.GetKeyManager();
    }
    public int GetWidth()
    {
        return game.GetWidth();
    }
    public int GetHeight()
    {
        return game.GetHeight();
    }
    public Game GetGame()
    {
        return game;
    }
    public void SetGame(Game game)
    {
        this.game = game;
    }
    public Map GetMap()
    {
        return map;
    }
    public void SetMap(Map map)
    {
        this.map = map;
    }
    public Hero GetHero()
    {
        return hero;
    }
    public void setHero(Hero hero) {
        this.hero = hero;
    }
}