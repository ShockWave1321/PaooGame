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
    Random movement;
    int nextMove = 121;
    public Enemy(RefLinks refLink, float x, float y)
    {
        super(refLink, x, y, 64, 64);
        normalBounds.x = 9;
        normalBounds.y = 14;
        normalBounds.width = 45;
        normalBounds.height = 40;

        attackBounds.x = 12;
        attackBounds.y = 14;
        attackBounds.width = 20;
        attackBounds.height = 20;
        collCheck = new CollisionChecker(refLink,this);

        speed = 1.f;
        health = 10;
        movement = new Random();
        animation = new Animation(this, Assets.monster);
    }

    @Override
    public void Update()
    {
        collCheck.checkMapCollision();
        //RandomMove1();
        Move();
        animation.animate();
        //System.out.println("Inamic prezent");
    }

    public void RandomMove()
    {
        if(nextMove > 2 * seconds)
        {
            int mv = movement.nextInt(100);
            if(mv < 24) {
                xMove = 1; yMove = 0;
            }
            else
            if(mv < 49){
                xMove = 0; yMove = 1;
            }
            else
            if(mv < 74){
                xMove = -1; yMove = 0;
            }
            else{
                xMove = 0; yMove = -1;
            }
            nextMove = 0;
        }
        nextMove++;
    }
    public void RandomMove1()
    {
        if(nextMove > 2 * seconds)
        {
            int mv = movement.nextInt(75);
            if(mv < 25)
                yMove = 1;
            else
            if(mv < 50)
                yMove = 0;
            else
                yMove = -1;

            mv = movement.nextInt(75);
            if(mv < 25)
                xMove = 1;
            else
            if(mv < 50)
                xMove = 0;
            else
                xMove = -1;
            nextMove = 0;
        }
        nextMove++;
    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);
        g.setColor(Color.red);
        //g.fillRect((int)x + bounds.x, (int)y + bounds.y, bounds.width, bounds.height);
        g.drawRect((int)x + bounds.x, (int)y + bounds.y, bounds.width, bounds.height);
    }
}
