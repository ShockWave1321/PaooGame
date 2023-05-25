package Game.Objects;

import Game.Graphics.Assets;
import Game.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Chest extends Item
{
    static int DEFAULT_CHEST_WIDTH = 32;
    static int DEFAULT_CHEST_HEIGHT = 32;
    BufferedImage image;
    int money, experience;
    public Chest(float x, float y, int money, int experience)
    {
        super(null, x, y, DEFAULT_CHEST_WIDTH , DEFAULT_CHEST_HEIGHT);
        image = Assets.chest;
        bounds = new Rectangle(4,12,24,16);
        this.money = money;
        this.experience = experience;
    }
    public Chest(float x, float y)
    {
        super(null, x, y, DEFAULT_CHEST_WIDTH , DEFAULT_CHEST_HEIGHT);
        image = Assets.chest;
        bounds = new Rectangle(4,12,24,16);
        this.money = 5;
        this.experience = 5;
    }

    @Override
    public void Update()
    {

    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y,null);
        //g.setColor(Color.blue);
        //g.drawRect((int)x + bounds.x, (int)y + bounds.y, bounds.width, bounds.height);
    }
    public void Rewards(Hero hero)
    {
        hero.SetMoney(hero.GetMoney() + money);
        hero.SetExperience(hero.GetExperience() + experience);
        //System.out.println("Money: "+hero.GetMoney()+"|"+"Experience: "+hero.GetExperience());
    }
}
