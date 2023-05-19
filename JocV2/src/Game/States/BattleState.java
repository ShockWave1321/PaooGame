package Game.States;

import Game.Graphics.Assets;
import Game.Map.Map;
import Game.Objects.Character;
import Game.Objects.Enemy;
import Game.Objects.Hero;
import Game.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BattleState extends State
{
    BufferedImage fundal = Assets.battleMap;
    private Hero hero;
    private Enemy enemy;

    public BattleState(RefLinks refLink)
    {
        super(refLink);
        hero = refLink.getHero();
        enemy = null;
    }

    @Override
    public void Update()
    {
        hero.Update();
    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(fundal,0,0,refLink.GetWidth(), refLink.GetHeight(), null);
    }
}
