package Game.Objects;

import Game.Collision.CollisionChecker;
import Game.Graphics.Assets;
import Game.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NPC extends Character
{
    private final BufferedImage image;
    private final CollisionChecker collCheck;
    Font font;
    int interact = 0;
    public NPC(RefLinks refLink, float x, float y)
    {
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        image = Assets.girlFront;
        font = new Font("Arial", Font.PLAIN,20);

        normalBounds.x = 6;        //normalBounds.x = 10;
        normalBounds.y = 2;        //normalBounds.y = 12;
        normalBounds.width = 20;    //normalBounds.width = 12;
        normalBounds.height = 28;   //normalBounds.height = 18;

        attackBounds.x = 12;
        attackBounds.y = 14;
        attackBounds.width = 20;
        attackBounds.height = 20;

        collCheck = new CollisionChecker(refLink,this);
        //yMove = 1;
    }

    @Override
    public void Update()
    {
        collCheck.checkMapCollision();
        Move();
    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);
        //g.setColor(Color.blue);
        //g.drawRect((int)x + bounds.x, (int)y + bounds.y, bounds.width, bounds.height);
        if(interact == 1)
        {
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString("HI",(int)x+8,(int)y-5);
        }
        if(interact == 2)
        {
            g.setFont(font);
            g.setColor(Color.RED);
            g.drawString("OUCH",(int)x+8,(int)y-5);
        }
    }
    public void Interact1()
    {
        interact = 1;
    }
    public void Interact2()
    {
        interact = 2;
    }
    public void EndInteraction()
    {
        interact = 0;
    }
}
