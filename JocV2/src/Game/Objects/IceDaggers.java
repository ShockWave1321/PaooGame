package Game.Objects;

import Game.Graphics.Assets;
import Game.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class IceDaggers extends Ability
{
    private final BufferedImage[][] cadre;
    int leftDir;
    Character character;
    int t = 0;
    int cadru;
    public IceDaggers(Character character, float x, float y)
    {
        super(x, y);
        this.character = character;
        cadre = Assets.iceDagger;
        leftDir = character.lastDir ? 1:-1;
        bounds.x = 12;
        bounds.y = 10;
        bounds.width = 22;
        bounds.height = 12;

        fired = true;
    }

    @Override
    public void Update()
    {
        x += speed * leftDir;
        t++;
        if(x > character.GetX() + range || x < character.GetX() - range)
        {
            fired = false;
        }
        if(t>4)
        {
            t = 0;
        }
    }

    @Override
    public void Draw(Graphics g)
    {
        if(t == 0)
        {
            cadru++;
            if(cadru > 9)
            {
                cadru = 0;
            }
        }
        if(leftDir == 1) {
            g.drawImage(cadre[0][cadru], (int) x, (int) y, DEFAULT_ABILITY_WIDTH * 2, DEFAULT_ABILITY_HEIGHT * 2, null);
        }else
        {
            g.drawImage(cadre[1][cadru], (int) x, (int) y, DEFAULT_ABILITY_WIDTH * 2, DEFAULT_ABILITY_HEIGHT * 2, null);
        }
        g.setColor(Color.red);
        g.drawRect((int)x + bounds.x, (int)y + bounds.y, bounds.width, bounds.height);
    }
}
