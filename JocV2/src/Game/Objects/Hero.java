package Game.Objects;

import java.awt.*;
import java.awt.image.BufferedImage;

import Game.AnimationManager.Animation;
import Game.Collision.CollisionChecker;
import Game.Graphics.Assets;
import Game.RefLinks;

public class Hero extends Character
{
    private BufferedImage image;
    private final int screenX;
    private final int screenY;
    private final Animation animation;
    private final CollisionChecker collCheck;
    public Hero(RefLinks refLink, float x, float y)
    {
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);

        image = Assets.heroLeft;
        normalBounds.x = 10;
        normalBounds.y = 12;
        normalBounds.width = 12;
        normalBounds.height = 18;

        attackBounds.x = 12;
        attackBounds.y = 14;
        attackBounds.width = 20;
        attackBounds.height = 20;

        screenX = (refLink.GetWidth() - Character.DEFAULT_CREATURE_WIDTH)/2;
        screenY = (refLink.GetHeight() - Character.DEFAULT_CREATURE_HEIGHT)/2;

        collCheck = new CollisionChecker(refLink);
        animation = new Animation(this);
    }
    @Override
    public void Update()
    {
        GetInput();
        collCheck.checkMapCollision();
        Move();
        animation.animate();
    }
    private void GetInput()
    {
        xMove = 0;
        yMove = 0;
        this.SetNormalMode();
        if(refLink.GetKeyManager().up)
        {
            yMove = -speed;
        }
        if(refLink.GetKeyManager().down)
        {
            yMove = speed;
        }
        if(refLink.GetKeyManager().left)
        {
            xMove = -speed;
        }
        if(refLink.GetKeyManager().right)
        {
            xMove = speed;
        }
        if(refLink.GetKeyManager().attack)
        {
            this.SetAttackMode();
        }
    }
    @Override
    public void Draw(Graphics g)
    {
        //g.drawImage(image, screenX, screenY, width, height, null);
        g.drawImage(image, (int)x, (int)y, width, height, null);
        //g.setColor(Color.red);
        //g.drawRect(screenX , screenY, width, height);
        //g.setColor(Color.blue);
        //g.fillRect(screenX + bounds.x, screenY + bounds.y, bounds.width, bounds.height);
    }
    public int getScreenX()
    {
        return screenX;
    }
    public int getScreenY()
    {
        return screenY;
    }
    public void setImage(BufferedImage image)
    {
        this.image = image;
    }
    public BufferedImage getImage()
    {
        return image;
    }
}