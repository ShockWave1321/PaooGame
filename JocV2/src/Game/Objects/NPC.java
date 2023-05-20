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
    public NPC(RefLinks refLink, float x, float y)
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
        yMove = 1;
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
        //g.setColor(Color.red);
        //g.fillRect((int)x + bounds.x, (int)y + bounds.y, bounds.width, bounds.height);
    }
    public void Interact()
    {
        System.out.println("Hi!");
    }

}
