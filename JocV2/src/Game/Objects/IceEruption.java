package Game.Objects;

import Game.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class IceEruption extends Ability
{
    static int manaCost = 4;
    private final BufferedImage[][] cadre;
    static int width = 32;
    static int height = 32;
    int leftDir;
    Character character;
    int timer = -1;
    int cadru, pos;
    int dist = 0;
    public IceEruption(Character character, float x, float y)
    {
        super(x, y, width, height);
        this.character = character;
        cadre = Assets.iceEruption;
        leftDir = character.lastDir ? 1:-1;
        bounds.x = 4;
        bounds.y = 12;
        bounds.width = 24;
        bounds.height = 20;
        cooldown = 2 * Ability.second;

        damage = 5;
        manaCost = 3;
        fired = true;
        range = 32 * 8;
        speed = 8.f;
        character.cooldown = cooldown;
        character.mana -= manaCost;
    }

    @Override
    public void Update()
    {
        x += speed * leftDir;
        timer++;
        if(x > character.GetX() + range || x < character.GetX() - range)
        {
            fired = false;
        }
        if(timer % 4 == 0)
        {
            timer = 0;
        }
    }

    @Override
    public void Draw(Graphics g)
    {
        if(leftDir == 1)
        {
            pos = 0;
        }
        else
        {
            pos = 1;
        }
        if(timer <= 0)
        {
            cadru++;
            if(cadru >= cadre[pos].length)
            {
                cadru = 0;
            }
        }
        for(int i = 0; i < dist - 1; ++i)
        {
            g.drawImage(cadre[pos][7 - i], (int) x - i * leftDir * 32, (int) y, width, height, null);
        }
        if(timer %4 == 0)
        {
            if (dist < 8)
                dist++;
        }
        //g.setColor(Color.red);
        //g.drawRect((int)x + bounds.x, (int)y + bounds.y, bounds.width, bounds.height);
    }
}
