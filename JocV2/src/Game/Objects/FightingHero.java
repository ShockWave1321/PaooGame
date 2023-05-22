package Game.Objects;

import Game.AnimationManager.Animation;
import Game.Graphics.Assets;
import Game.Input.KeyManager;

import java.awt.*;
import java.awt.image.BufferedImage;


public class FightingHero
{
    float x, y, speed, currentSpeed;
    int xMove, yMove;
    int width, height;
    Rectangle bounds;
    BufferedImage image;
    Animation animation;
    KeyManager keyManager;
    int health, mana;
    int t = 0;
    public FightingHero(KeyManager keyManager, Hero hero)
    {
        x = 275;
        y = 600;
        speed = hero.GetSpeed();
        animation = hero.GetAnimation();
        this.keyManager = keyManager;

        width = hero.GetWidth();
        height = hero.GetHeight();
        bounds = hero.GetBounds();
        Scale(2);

        image = Assets.heroFront;
        health = hero.GetLife();
        mana = hero.GetMana();
    }
    public void Draw(Graphics g)
    {
        g.drawImage(image,(int) x,(int) y, width, height,null);
        g.setColor(Color.blue);
        g.fillRect((int)x + bounds.x, (int)y + bounds.y, bounds.width, bounds.height);
    }
    public void Update()
    {
        Move();
        animation.animate();
        if(t>60)
        {
            System.out.print(x+"-");
            System.out.println(y);
            t = 0;
        }
        t++;
    }
    void Move()
    {
        xMove = 0;
        yMove = 0;
        currentSpeed = speed;
        if(keyManager.attack)
        {
            mana++;
            health++;
        }
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
        x +=xMove * currentSpeed;
        y +=yMove * currentSpeed;
    }
    public void Scale(float s)
    {
        bounds.x *= s;
        bounds.y *= s;
        bounds.width *= s;
        bounds.height *= s;
        width *= s;
        height *= s;
    }
    public int GetHealth()
    {
        return health;
    }
    public int GetMana()
    {
        return mana;
    }
}
