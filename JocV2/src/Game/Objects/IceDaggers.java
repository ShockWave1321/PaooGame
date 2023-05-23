package Game.Objects;

import Game.Graphics.Assets;
import Game.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class IceDaggers extends Ability
{
    private final BufferedImage[][] cadre;
    int leftDir = -1;
    Character character;
    int t = 0;
    public IceDaggers(Character character, float x, float y)
    {
        super(x, y);
        this.character = character;
        cadre = Assets.iceDagger;
        leftDir = character.lastDir ? 1:-1;

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
    }

    @Override
    public void Draw(Graphics g)
    {
        if(leftDir == 1) {
            g.drawImage(cadre[0][0], (int) x, (int) y, DEFAULT_ABILITY_WIDTH * 2, DEFAULT_ABILITY_HEIGHT * 2, null);
        }else
        {
            g.drawImage(cadre[1][0], (int) x, (int) y, DEFAULT_ABILITY_WIDTH * 2, DEFAULT_ABILITY_HEIGHT * 2, null);
        }

    }
}
