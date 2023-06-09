package Game.Objects;

import Game.AnimationManager.Animation;
import Game.Graphics.Assets;
import Game.Input.KeyManager;

import java.awt.*;
import java.util.ArrayList;

public class FightingHero extends Character
{
    static float xsp = 275;
    static float ysp = 600;
    float currentSpeed;
    Animation animation;
    KeyManager keyManager;
    private final ArrayList<Ability> abilities;
    int pressed = 0, hold = 0;
    int level;
    public FightingHero(Hero hero)
    {
        super(null, xsp, ysp, hero.width, hero.height);
        bounds = new Rectangle(hero.GetBounds());
        speed = hero.GetSpeed();
        health = hero.GetHealth();
        mana = hero.GetMana();
        level = hero.GetLevel();

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
        UI(g);
    }
    public void Update()
    {
        GetInput();
        Move();
        HealthRegen();
        ManaRegen();
        animation.animate(this);
        if(!abilities.isEmpty())
        {
            for(int i = 0; i < abilities.size(); ++i)
            {
                if(abilities.get(i).IsFired())
                {
                    abilities.get(i).Update();
                }
                else
                {
                    abilities.remove(abilities.get(i));
                    //System.out.println("removed");
                }
            }
        }
        if(timer % second == 0)
        {
            //System.out.println((int)x+"|"+(int)y+"|"+xMove+"|"+yMove);
            System.out.println(mana);
        }
        if(timer > manaRegen * healthRegen)
        {
            timer = 0;
        }
        timer++;
    }
    public void Move()
    {
        if(xMove == 1)
        {
            lastDir = true;
        }
        else
        if(xMove == -1)
        {
            lastDir = false;
        }
        x += xMove * currentSpeed;
        y += yMove * currentSpeed;
    }
    public void GetInput()
    {
        xMove = 0;
        yMove = 0;
        currentSpeed = speed;

        pressed = 0;
        if(keyManager.attack)
        {
            if(cooldown <= 0)
            {
                pressed = 1;
                if (hold == 0)
                {
                    if(this.mana >= Slash.manaCost)
                    {
                        abilities.add(new Slash(this, x, y));
                       // System.out.println("Fired");
                    }
                }
            }
        }
        if(keyManager.k1 && level > 0)
        {
            if(cooldown <= 0)
            {
                pressed = 1;
                if (hold == 0)
                {
                    abilities.add(new IceDaggers(this, x, y));
                   // System.out.println("Fired");
                }
            }
        }
        if(keyManager.k2 && level > 1)
        {
            if(cooldown <= 0)
            {
                pressed = 1;
                if (hold == 0)
                {
                    abilities.add(new IceEruption(this, x, y));
                    //System.out.println("Fired");
                }
            }
        }
        if(cooldown > 0)
            cooldown--;
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
    }
    public boolean CheckAbilityCollision(Character character)
    {
        boolean hit = false;
        for(Ability a : abilities)
        {
            if(a.Hits(character))
            {
                a.Damage(character);
                hit = true;
            }
        }
        return hit;
    }
    public void UI(Graphics g)
    {
        for(int i = 0; i < health; ++i)
        {
            g.setColor(Color.GREEN);
            g.fillRect(40 + 50 * i,50,50,25);
            g.setColor(Color.black);
            g.drawRect(40 + 50 * i,50,50,25);
        }
        for(int i = 0; i < mana; ++i)
        {
            g.setColor(Color.blue);
            g.fillRect(40 + 30 * i,80,30,15);
            g.setColor(Color.black);
            g.drawRect(40 + 30 * i,80,30,15);
        }
        //g.setColor(Color.blue);
        //g.fillRect((int)x + bounds.x, (int)y + bounds.y, bounds.width, bounds.height);
    }
}
