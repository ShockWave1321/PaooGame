package Game.Objects;

import Game.AnimationManager.Animation;
import Game.Graphics.Assets;
import Game.Input.KeyManager;

import java.awt.*;
import java.util.ArrayList;

public class FightingHero extends Character
{
    static float x = 275;
    static float y = 600;
    static int scaleFactor = 2;
    float speed, currentSpeed;
    Animation animation;
    KeyManager keyManager;
    private final ArrayList<Ability> abilities;
    int health, mana;
    int pressed = 0,hold = 0;
    int t = 0;
    public FightingHero(Hero hero)
    {
        super(null, x, y, hero.GetWidth(), hero.GetHeight());
        bounds = new Rectangle(hero.GetBounds());
        speed = hero.GetSpeed();
        health = hero.GetHealth();
        mana = hero.GetMana();
        Scale();

        animation = new Animation(this, Assets.heroRun);
        this.keyManager = hero.refLink.GetKeyManager();
        abilities = new ArrayList<>();

    }
    public void Draw(Graphics g)
    {
        g.drawImage(image,(int) x,(int) y, width, height,null);
        if(!abilities.isEmpty())
        {
            for(Ability a : abilities)
            {
                a.Draw(g);
            }
        }
        //g.setColor(Color.blue);
        //g.fillRect((int)x + bounds.x, (int)y + bounds.y, bounds.width, bounds.height);
    }
    public void Update()
    {
        Move();
        animation.animate(this);
        if(!abilities.isEmpty())
        {
            for(int i = 0; i<abilities.size(); ++i)
            {
                if(abilities.get(i).IsFired())
                {
                    abilities.get(i).Update();
                }
                else
                {
                    abilities.remove(abilities.get(i));
                    System.out.println("removed");
                }
            }
        }
        if(t>60)
        {
            System.out.println((int)x+"|"+(int)y+"|"+xMove+"|"+yMove);
            t = 0;
        }
        t++;
    }
    public void Move()
    {
        xMove = 0;
        yMove = 0;
        currentSpeed = speed;
        pressed = 0;
        if(keyManager.attack)
        {
            pressed = 1;
            if(hold == 0)
            {
                abilities.add(new IceDaggers(this, x, y));
                System.out.println("Fired");
            }
        }
        hold = pressed;
        if(keyManager.shift)
        {
            currentSpeed = speed * 2;
        }
        if(keyManager.up)
        {
            yMove = -1;
        }
        if(keyManager.down)
        {
            yMove = 1;
        }
        if(keyManager.left)
        {
            xMove = -1;
        }
        if(keyManager.right)
        {
            xMove = 1;
        }
        if(y + yMove * currentSpeed > 650 || y + yMove * currentSpeed < 500)
        {
            yMove = 0;
        }
        if(x + xMove * currentSpeed > 1375 ||x + xMove * currentSpeed < 0)
        {
            xMove = 0;
        }
        x +=xMove * currentSpeed;
        y +=yMove * currentSpeed;
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
    public void ReScale()
    {
        bounds.x /= scaleFactor;
        bounds.y /= scaleFactor;
        bounds.width /= scaleFactor;
        bounds.height /= scaleFactor;
        width /= scaleFactor;
        height /= scaleFactor;
    }
    public int GetHealth()
    {
        return health;
    }
    public int GetMana()
    {
        return mana;
    }
    public float GetSpeed()
    {
        return speed;
    }

}
