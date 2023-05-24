package Game.Objects;

import Game.AnimationManager.Animation;
import Game.Graphics.Assets;
import Game.Input.KeyManager;
import Game.RefLinks;

import java.awt.*;
import java.util.ArrayList;

public class FightingEnemy extends Character
{
    static float x = 275;
    static float y = 600;
    static int scaleFactor = 2;
    float currentSpeed;
    Animation animation;
    private final ArrayList<Ability> abilities;
    int t = 0;
    public FightingEnemy(Enemy enemy)
    {
        super(null, x, y, enemy.width, enemy.height);
        bounds = new Rectangle(enemy.GetBounds());
        speed = enemy.GetSpeed();
        health = enemy.GetHealth();
        Scale();

        animation = new Animation(this, Assets.heroRun);
        abilities = new ArrayList<>();
    }

    @Override
    public void Update()
    {

    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int) x, (int) y, width, height,null);
        if(!abilities.isEmpty())
        {
            for(Ability a : abilities)
            {
                a.Draw(g);
            }
        }
    }
    public void Scale()
    {
        bounds.x *= scaleFactor;
        bounds.y *= scaleFactor;
        bounds.width *= scaleFactor;
        bounds.height *= scaleFactor;
        width *= scaleFactor;
        height *= scaleFactor;
    }
}
