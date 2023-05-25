package Game.Objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Game.AnimationManager.Animation;
import Game.Collision.CollisionChecker;
import Game.Graphics.Assets;
import Game.RefLinks;

public class Hero extends Character
{
    private int HEALTH_CAP = 5;
    private int MANA_CAP = 10;
    private final int screenX;
    private final int screenY;
    private final Animation animation;
    private final CollisionChecker collCheck;
    private int experience;
    private int mana;
    private int money;
    private final ArrayList<Ability> abilities;
    float defaultSpeed;
    int pressed = 0, hold = 0;
    int timer = 0;
    int level;
    public Hero(RefLinks refLink, float x, float y)
    {
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);

        normalBounds.x = 6;
        normalBounds.y = 2;
        normalBounds.width = 20;
        normalBounds.height = 28;

        attackBounds.x = 12;
        attackBounds.y = 14;
        attackBounds.width = 20;
        attackBounds.height = 20;

        speed = 2.0f;
        defaultSpeed = speed;
        level = 0;

        screenX = (refLink.GetWidth() - Character.DEFAULT_CREATURE_WIDTH)/2;
        screenY = (refLink.GetHeight() - Character.DEFAULT_CREATURE_HEIGHT)/2;

        collCheck = new CollisionChecker(refLink,this);
        animation = new Animation(this, Assets.heroRun);
        abilities = new ArrayList<>();

        experience = 0;
        health = 1;
        mana = 1;
        money = 0;
    }
    @Override
    public void Update()
    {
        Time();
        GetInput();
        collCheck.checkMapCollision();
        Move();
        HealthRegen();
        ManaRegen();
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
        if(level == 0)
        {
            if(experience > 50)
            {
                experience -= 50;
                level++;
            }
        }
        else
        if(level == 1)
        {
            if(experience > 100)
            {
                experience -= 100;
                level++;
            }
        }
    }
    public void Time()
    {
        if(timer > 180)
        {
            timer = 0;

        }
        if(timer % 60 == 0)
        {
            //System.out.println("Health "+ health);
            //System.out.println("Mana "+ mana);
        }
        timer++;
    }

    private void GetInput()
    {
        xMove = 0;
        yMove = 0;
        speed = defaultSpeed;

        pressed = 0;

        if(refLink.GetKeyManager().attack)
        {
            if(cooldown <= 0)
            {
                pressed = 1;
                if (hold == 0)
                {
                    if(this.mana >= Slash.manaCost)
                    {
                        abilities.add(new Slash(this, x, y));
                        System.out.println("Fired");
                    }
                }
            }
        }
        if(refLink.GetKeyManager().k1 && level > 0)
        {
            if(cooldown <= 0)
            {
                pressed = 1;
                if (hold == 0)
                {
                    abilities.add(new IceDaggers(this, x, y));
                    System.out.println("Fired");
                }
            }
        }
        if(refLink.GetKeyManager().k2 && level > 1)
        {
            if(cooldown <= 0)
            {
                pressed = 1;
                if (hold == 0)
                {
                    abilities.add(new IceEruption(this, x, y));
                    System.out.println("Fired");
                }
            }
        }
        if(cooldown > 0)
            cooldown--;
        hold = pressed;

        if(refLink.GetKeyManager().shift)
        {
            speed = speed * 2;
        }
        if(refLink.GetKeyManager().up)
        {
            yMove = -1;
        }
        if(refLink.GetKeyManager().down)
        {
            yMove = 1;
        }
        if(refLink.GetKeyManager().left)
        {
            xMove = -1;
        }
        if(refLink.GetKeyManager().right)
        {
            xMove = 1;
        }
    }
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);
        if(!abilities.isEmpty())
        {
            for(Ability a : abilities)
            {
                a.Draw(g);
            }
        }
        //g.setColor(Color.red);
        //g.drawRect((int)x, (int)y, width, height);
        //g.setColor(Color.blue);
        //g.drawRect((int)x + bounds.x, (int)y + bounds.y, bounds.width, bounds.height);
    }
    public int GetScreenX()
    {
        return screenX;
    }
    public int GetScreenY()
    {
        return screenY;
    }
    public int GetExperience()
    {
        return experience;
    }
    public int GetMoney()
    {
        return money;
    }
    public int GetMana()
    {
        return mana;
    }
    public void SetExperience(int experience)
    {
        this.experience = experience;
    }
    public void SetMoney(int money)
    {
        this.money = money;
    }
    public boolean CheckItemCollision(Item item)
    {
        return collCheck.CheckItemCollision(item);
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
            SetMana(mana + 1);
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
    public int GetLevel()
    {
        return level;
    }
}