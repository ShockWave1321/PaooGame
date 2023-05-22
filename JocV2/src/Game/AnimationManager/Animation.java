package Game.AnimationManager;

import Game.Objects.Character;
import Game.Graphics.Assets;

import java.awt.image.BufferedImage;

public class Animation
{
    private final Character character;
    private boolean left = false;
    private int animSpeed;
    private final int CONST_SPEED = 80;
    int t = 0;
    //private List<BufferedImage> cadre;
    public Animation(Character character)
    {
        this.character = character;
        animSpeed = CONST_SPEED/(int)character.GetSpeed();
        /*
        cadre = new ArrayList<>();
        cadre.add(Assets.heroLeft);
        cadre.add(Assets.heroRight);
        */
    }
    public void animate()
    {
        BufferedImage last;
        float characterX = character.GetXMove();
        float characterY = character.GetYMove();
        animSpeed = CONST_SPEED/(int)character.GetSpeed();

        if (characterX > 0) {
            if (left) {
                last = Assets.heroRightRunLeft;
            } else {
                last = Assets.heroRightRunRight;
            }
        } else if (characterX < 0) {
            if (left) {
                last = Assets.heroLeftRunLeft;
            } else {
                last = Assets.heroLeftRunRight;
            }
        } else if (characterY < 0) {
            if (left) {
                last = Assets.heroBackRunLeft;
            } else {
                last = Assets.heroBackRunRight;
            }
        } else if (characterY > 0) {
            if (left) {
                last = Assets.heroFrontRunLeft;
            } else {
                last = Assets.heroFrontRunRight;
            }
        }
        else
        {
            last = Assets.heroFront;
        }
        character.SetImage(last);
        if (t > animSpeed)
        {
            left = !left;
            t = 0;
        }
        t++;
    }
}
