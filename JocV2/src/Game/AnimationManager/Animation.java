package Game.AnimationManager;

import Game.Objects.Hero;
import Game.Graphics.Assets;

import java.awt.image.BufferedImage;

public class Animation
{
    private final Hero hero;
    private boolean left = false;
    private int animSpeed;
    private final int CONST_SPEED = 80;
    int t = 0;
    //private List<BufferedImage> cadre;
    public Animation(Hero hero)
    {
        this.hero = hero;
        animSpeed = CONST_SPEED/(int)hero.GetSpeed();
        /*
        cadre = new ArrayList<>();
        cadre.add(Assets.heroLeft);
        cadre.add(Assets.heroRight);
        */
    }
    public void animate()
    {
        BufferedImage last;
        float heroX = hero.GetXMove();
        float heroY = hero.GetYMove();
        animSpeed = CONST_SPEED/(int)hero.GetSpeed();

        if (heroX > 0) {
            if (left) {
                last = Assets.heroRightRunLeft;
            } else {
                last = Assets.heroRightRunRight;
            }
        } else if (heroX < 0) {
            if (left) {
                last = Assets.heroLeftRunLeft;
            } else {
                last = Assets.heroLeftRunRight;
            }
        } else if (heroY < 0) {
            if (left) {
                last = Assets.heroBackRunLeft;
            } else {
                last = Assets.heroBackRunRight;
            }
        } else if (heroY > 0) {
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
        hero.setImage(last);
        if (t > animSpeed)
        {
            left = !left;
            t = 0;
        }
        t++;
    }
}
