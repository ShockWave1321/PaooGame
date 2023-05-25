package Game.Objects;

import Game.AnimationManager.Animation;
import Game.Collision.CollisionChecker;
import Game.Graphics.Assets;
import Game.RefLinks;

import java.awt.*;
import java.util.Random;

public class Enemy extends Character
{
    static final int seconds = 60;
    private final CollisionChecker collCheck;
    private final Animation animation;
    Ability ability;
    Random movement;
    int nextMove = 0;
    boolean fighting;
    boolean attacking;
    int lastX, lastY;
    int level;
    public Enemy(RefLinks refLink, float x, float y, int level)
    {
        super(refLink, x, y, 64, 64);
        normalBounds.x = 9;
        normalBounds.y = 14;
        normalBounds.width = 45;
        normalBounds.height = 40;

        attackBounds.x = -70;
        attackBounds.y = -66;
        attackBounds.width = 200;
        attackBounds.height = 200;

        collCheck = new CollisionChecker(refLink,this);
        attacking = true;

        speed = 1.f + level;
        health = 3 + 2 * level;
        movement = new Random();
        this.level = level;

        switch (this.level)
        {
            case 2 -> animation = new Animation(this, Assets.monsterLvl2);
            case 3 -> animation = new Animation(this, Assets.monsterLvl3);
            default -> animation = new Animation(this, Assets.monsterLvl1);
        }
        fighting = false;
    }

    @Override
    public void Update()
    {
        CheckMood();
        RandomMove();
        FollowHero();
        collCheck.checkMapCollision();
        lastX = (int)x;
        lastY = (int)y;
        Move();
        animation.animate(this);
    }

    public void RandomMove()
    {
        if(!attacking)
        {
            SetSpeed(1.f);
            if (nextMove > 2 * seconds)
            {
                //System.out.println("Wandering");
                int mv = movement.nextInt(100);
                if (mv < 24)
                {
                    xMove = 1;
                    yMove = 0;
                } else if (mv < 49)
                {
                    xMove = 0;
                    yMove = 1;
                } else if (mv < 74)
                {
                    xMove = -1;
                    yMove = 0;
                } else
                {
                    xMove = 0;
                    yMove = -1;
                }
                nextMove = 0;
            }
            nextMove++;
        }
    }
    public void RandomMove1()
    {
        if(!attacking)
        {
            if (nextMove > 2 * seconds)
            {
                int mv = movement.nextInt(75);
                if (mv < 25)
                    yMove = 1;
                else if (mv < 50)
                    yMove = 0;
                else
                    yMove = -1;

                mv = movement.nextInt(75);
                if (mv < 25)
                    xMove = 1;
                else if (mv < 50)
                    xMove = 0;
                else
                    xMove = -1;
                nextMove = 0;
            }
            nextMove++;
        }
    }
    public void FollowHero()
    {
        Rectangle heroBounds = refLink.GetHero().WorldBounds();
        if(attacking)
        {
            SetSpeed(2.f);
            //System.out.println("Attacking");
            if(!bounds.intersects(heroBounds))
            {
                if (x + bounds.x < heroBounds.x)
                {
                    xMove = 1;
                } else {
                    xMove = -1;
                }
                if (y + bounds.y < heroBounds.y)
                {
                    yMove = 1;
                } else {
                    yMove = -1;
                }
            }
        }
    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);
        //g.setColor(Color.blue);
        //g.drawRect((int)x + attackBounds.x, (int)y + attackBounds.y, attackBounds.width, attackBounds.height);
        //g.setColor(Color.red);
        //g.drawRect((int)x + bounds.x, (int)y + bounds.y, bounds.width, bounds.height);
        //g.fillRect((int)x + bounds.x, (int)y + bounds.y, bounds.width, bounds.height);

        if(fighting)
        {
            for (int i = 0; i < health; ++i)
            {
                g.setColor(Color.RED);
                g.fillRect(1350 - 50 * i, 50, 50, 25);
                g.setColor(Color.black);
                g.drawRect(1350 - 50 * i, 50, 50, 25);
            }
        }
    }
    public void Fights()
    {
        fighting = true;
    }
    public Item Reward()
    {
        return new Chest(lastX + 17, lastY + 15, 5 * level + 5, 10 + level * 5);
    }
    public void CheckMood()
    {
        attacking = new Rectangle(
                (int)x + attackBounds.x,
                (int)y + attackBounds.y,
                attackBounds.width,
                attackBounds.height
        ).intersects(refLink.GetHero().WorldBounds());
    }
    public boolean IsAttacking()
    {
        return attacking;
    }
}
