package Game.AnimationManager;

import Game.Objects.Hero;
import Game.Graphics.Assets;

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
        float heroX = hero.GetXMove();
        float heroY = hero.GetYMove();
        animSpeed = CONST_SPEED/(int)hero.GetSpeed();

        if (heroX > 0) {
            if (left) {
                hero.setImage(Assets.heroRightRunLeft);
            } else {
                hero.setImage(Assets.heroRightRunRight);
            }
        } else if (heroX < 0) {
            if (left) {
                hero.setImage(Assets.heroLeftRunLeft);
            } else {
                hero.setImage(Assets.heroLeftRunRight);
            }
        } else if (heroY < 0) {
            if (left) {
                hero.setImage(Assets.heroBackRunLeft);
            } else {
                hero.setImage(Assets.heroBackRunRight);
            }
        } else if (heroY > 0) {
            if (left) {
                hero.setImage(Assets.heroFrontRunLeft);
            } else {
                hero.setImage(Assets.heroFrontRunRight);
            }
        }
        else
        {
            hero.setImage(Assets.heroFront);
        }
        if (t > animSpeed)
        {
            left = !left;
            t = 0;
        }
        t++;
    }
}