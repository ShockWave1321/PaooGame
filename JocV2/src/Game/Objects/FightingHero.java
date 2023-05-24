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

    private int HEALTH_CAP = 10;
    private int MANA_CAP = 3;
    float currentSpeed;
    Animation animation;
    KeyManager keyManager;
    private final ArrayList<Ability> abilities;
    int mana;
    int pressed = 0, hold = 0;
    int timer = 0;
    public FightingHero(Hero hero)
    {
        super(null, xsp, ysp, hero.width, hero.height);
        bounds = new Rectangle(hero.GetBounds());
        speed = hero.GetSpeed();
        health = hero.GetHealth();
        mana = hero.GetMana();

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
        GetInput();
        Move();
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
        if(timer>60)
        {
            //System.out.println((int)x+"|"+(int)y+"|"+xMove+"|"+yMove);
            System.out.println(this.GetX());
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
        x +=xMove * currentSpeed;
        y +=yMove * currentSpeed;
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
                    abilities.add(new IceDaggers(this, x, y));
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
    public void SetMana(int mana)
    {
        if(mana <= MANA_CAP)
            this.mana = mana;
    }
    public void ManaRegen()
    {
        if(timer % 3 * second == 0)
        {
            SetMana(mana+1);
        }
    }
    public void SetHealth(int hp)
    {
        if(hp <= HEALTH_CAP)
        {
            health = hp;
        }
    }
    public void HealthRegen()
    {
        if(timer % 10 * second == 0)
        {
            SetHealth(health + 1);
        }
    }
}
