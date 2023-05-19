package Game.Objects;

import Game.Collision.CollisionChecker;
import Game.Graphics.Assets;
import Game.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NPC extends Character
{
    private BufferedImage image;
    private final int screenX;
    private final int screenY;
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

        //screenX = (refLink.GetWidth() - Character.DEFAULT_CREATURE_WIDTH)/2;
        //screenY = (refLink.GetHeight() - Character.DEFAULT_CREATURE_HEIGHT)/2;

        screenX = (int)x ;
        screenY = (int)y ;

        collCheck = new CollisionChecker(refLink);
    }

    @Override
    public void Update()
    {
        collCheck.checkMapCollision();
    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, screenX, screenY, width, height, null);
        g.setColor(Color.red);
        g.fillRect(screenX + bounds.x, screenY + bounds.y, bounds.width, bounds.height);
    }
}
