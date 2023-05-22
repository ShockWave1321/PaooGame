package Game.Objects;

import java.awt.*;
import java.awt.image.BufferedImage;

import Game.AnimationManager.Animation;
import Game.Collision.CollisionChecker;
import Game.Graphics.Assets;
import Game.RefLinks;

public class Hero extends Character
{
    private final int screenX;
    private final int screenY;
    private final Animation animation;
    private final CollisionChecker collCheck;
    private int experience;
    private int mana;
    private int money;
    private final int ABILITIES_CAP = 3;
    private final Ability[] abilities;
    public Hero(RefLinks refLink, float x, float y)
    {
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);

        image = Assets.heroFront;
        normalBounds.x = 10;
        normalBounds.y = 12;
        normalBounds.width = 12;
        normalBounds.height = 18;

        attackBounds.x = 12;
        attackBounds.y = 14;
        attackBounds.width = 20;
        attackBounds.height = 20;

        speed = 2.0f;

        screenX = (refLink.GetWidth() - Character.DEFAULT_CREATURE_WIDTH)/2;
        screenY = (refLink.GetHeight() - Character.DEFAULT_CREATURE_HEIGHT)/2;

        collCheck = new CollisionChecker(refLink,this);
        animation = new Animation(this);
        abilities = new Ability[ABILITIES_CAP];

        experience = 0;
        mana = 3;
        money = 0;
    }
    @Override
    public void Update()
    {
        GetInput();
        collCheck.checkMapCollision();
        Move();
        animation.animate();
    }
    private void GetInput()
    {
        xMove = 0;
        yMove = 0;
        this.SetNormalMode();
        float currentSpeed = speed;

        if(refLink.GetKeyManager().attack)
        {
            this.SetAttackMode();
            for(Ability a:abilities)
            {
                a.Fire();
            }
        }
        if(refLink.GetKeyManager().shift)
        {
            currentSpeed = speed * 2;
        }
        if(refLink.GetKeyManager().up)
        {
            yMove = -currentSpeed;
        }
        if(refLink.GetKeyManager().down)
        {
            yMove = currentSpeed;
        }
        if(refLink.GetKeyManager().left)
        {
            xMove = -currentSpeed;
        }
        if(refLink.GetKeyManager().right)
        {
            xMove = currentSpeed;
        }
    }
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);
        //g.setColor(Color.red);
        //g.drawRect((int)x, (int)y, width, height);
        //g.setColor(Color.blue);
        //g.fillRect((int)x + bounds.x, (int)y + bounds.y, bounds.width, bounds.height);
    }
    public int GetScreenX()
    {
        return screenX;
    }
    public int GetScreenY()
    {
        return screenY;
    }
    public Animation GetAnimation()
    {
        return animation;
    }

    public BufferedImage GetImage()
    {
        return image;
    }
    public int GetExperience()
    {
        return experience;
    }
    public int GetMoney()
    {
        return money;
    }
    public int GetMana()
    {
        return mana;
    }
    public void SetExperience(int experience)
    {
        this.experience = experience;
    }
    public void SetMoney(int money)
    {
        this.money = money;
    }
    public void SetMana(int mana)
    {
        this.mana = mana;
    }
    public boolean CheckItemCollision(Item item)
    {
        return collCheck.CheckItemCollision(item);
    }
    public void CheckItemsCollision(Character[] characters)
    {
        for(Character character : characters)
        {
            if(collCheck.CheckItemCollision(character))
            {
                xMove = 0;
                yMove = 0;
            }
        }
    }
    public Hero PrebattleHero(Hero hero)
    {
        return hero;
    }

}