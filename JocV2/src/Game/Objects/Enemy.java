package Game.Objects;

import Game.Collision.CollisionChecker;
import Game.Graphics.Assets;
import Game.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends Character
{
    static final int seconds = 60;
    private final BufferedImage image;
    private final CollisionChecker collCheck;
    boolean dead;
    Random movement;
    int nextMove = 121;
    public Enemy(RefLinks refLink, float x, float y)
    {
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        image = Assets.girlFront;
        normalBounds.x = 10;
        normalBounds.y = 12;
        normalBounds.width = 12;
        normalBounds.height = 18;

        attackBounds.x = 12;
        attackBounds.y = 14;
        attackBounds.width = 20;
        attackBounds.height = 20;
        collCheck = new CollisionChecker(refLink,this);

        dead = false;
        speed = 0.5f;
        movement = new Random(1);
    }

    @Override
    public void Update()
    {
        collCheck.checkMapCollision();
        RandomMove();
        Move();
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

            /*if(movement.nextInt(75) > 24)
                yMove = 1;
            else
            if(movement.nextInt(75) > 48)
                yMove = 0;
            else
                yMove = -1;

            if(movement.nextInt(75) > 24)
                xMove = 1;
            else
            if(movement.nextInt(75) > 48)
                xMove = 0;
            else
                xMove = -1;*/

            nextMove = 0;
        }
        nextMove++;
    }


    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);
        //g.setColor(Color.red);
        //g.fillRect((int)x + bounds.x, (int)y + bounds.y, bounds.width, bounds.height);
    }
    public boolean IsDead()
    {
        return dead;
    }
}
