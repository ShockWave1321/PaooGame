package Game.AnimationManager;

import Game.Objects.Character;
import Game.Objects.Enemy;

import java.awt.image.BufferedImage;

public class Animation
{
    private final Character character;
    private int animSpeed;
    private final int CONST_SPEED = 40;
    int t = 0;
    private final BufferedImage[][] cadre;
    int cadru = 0;
    int dir = 0;
    public Animation(Character character, BufferedImage[][] cadre)
    {
        this.character = character;
        animSpeed = CONST_SPEED/(int)character.GetSpeed();
        this.cadre = cadre;
    }
    public void animate(Character character)
    {
        float characterX = character.GetXMove();
        float characterY = character.GetYMove();
        animSpeed = CONST_SPEED/(int)character.GetSpeed();

        if (characterX > 0) {
            dir = 3;
        } else
        if (characterX < 0) {
            dir = 2;
        } else
        if (characterY < 0) {
            dir = 1;
        } else
        if (characterY > 0) {
            dir = 0;
        }
        else {
            cadru = 0;
        }
        character.SetImage(cadre[dir][cadru]);
        if (t > animSpeed)
        {
            cadru++;
            if(cadru >= cadre[dir].length)
                cadru = 1;
            t = 0;
        }
        t++;
    }
    public void animate(Enemy enemy)
    {
        float characterX = character.GetXMove();
        animSpeed = CONST_SPEED/((int)character.GetSpeed()*2);
        if(((Enemy)character).IsAttacking())
        {
            if (characterX > 0) {
                dir = 1;
            } else
            if (characterX < 0) {
                dir = 5;
            }
        }
        else
        {
            if (characterX > 0) {
                dir = 0;
            } else
            if (characterX < 0) {
                dir = 4;
            }
        }
        if (t > animSpeed)
        {
            cadru++;
            if(cadru >= cadre[dir].length)
                cadru = 0;
            t = 0;
        }
        character.SetImage(cadre[dir][cadru]);
        t++;
    }
}
