package Game.States;

import Game.Graphics.Assets;
import Game.Objects.Enemy;
import Game.Objects.FightingHero;
import Game.Objects.Hero;
import Game.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BattleState extends State
{
    static int scaleFactor = 2;
    BufferedImage background = Assets.battleMap;
    private final Hero hero;
    private final FightingHero fHero;
    private final Enemy enemy;

    public BattleState(RefLinks refLink, Enemy enemy)
    {
        super(refLink);
        hero = refLink.GetHero();
        this.enemy = enemy;

        fHero = new FightingHero(hero);

        PreBattleSetup();
    }

    @Override
    public void Update()
    {
        fHero.Update();
        enemy.Update();
        if(refLink.GetKeyManager().event)
        {
            PostBattleSetup();
            State.SetState(State.PreviousState());
        }
        if(fHero.CheckAbilityCollision(enemy))
        {
            System.out.println("Enemy hit, health: "+ enemy.GetHealth());
            if(enemy.IsDead())
            {
                State.SetState(State.PreviousState());
            }
        }
    }
    void PreBattleSetup()
    {
        fHero.Scale(scaleFactor);

        enemy.SetX(fHero.GetX() + 800);
        enemy.SetY(fHero.GetY() - 50);
        enemy.Scale(scaleFactor);
    }
    void PostBattleSetup()
    {
        fHero.Scale(1.f/scaleFactor);
        hero.SetMana(fHero.GetMana());
        hero.SetHealth(fHero.GetHealth());
    }
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(background,0,0, refLink.GetWidth(), refLink.GetHeight(), null);
        fHero.Draw(g);
        enemy.Draw(g);
    }
}
