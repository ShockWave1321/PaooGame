package Game.Objects;

import Game.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Slash extends Ability
{
    static int manaCost = 0;
    private final BufferedImage[][] cadre;
    int leftDir;
    Character character;
    int timer = -1;
    int cadru, pos;
    public Slash(Character character, float x, float y) {
        super(x, y);
        cadre = Assets.slash;
        this.character = character;
        leftDir = character.lastDir ? 1:-1;
        bounds.x = 10;
        bounds.y = 2;
        bounds.width = 26;
        bounds.height = 28;
        cooldown = (float) Ability.second/2;

        damage = 1;
        range = 50;
        fired = true;
        speed = 15;
        character.cooldown = cooldown;
        character.mana -= manaCost;
    }

    @Override
    public void Update() {
        x += speed * leftDir;
        timer++;
        if(x > character.GetX() + range || x < character.GetX() - range)
        {
            fired = false;
        }
        if(timer > 4)
        {
            timer = 0;
        }
    }

    @Override
    public void Draw(Graphics g) {
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
        g.drawImage(cadre[pos][cadru], (int) x, (int) y, DEFAULT_ABILITY_WIDTH * 2, DEFAULT_ABILITY_HEIGHT * 2, null);
        //g.setColor(Color.red);
        //g.drawRect((int)x + bounds.x, (int)y + bounds.y, bounds.width, bounds.height);
        //g.setColor(Color.red);
        //g.drawRect((int)x + bounds.x, (int)y + bounds.y, bounds.width, bounds.height);
    }
}
