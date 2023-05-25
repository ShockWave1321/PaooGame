package Game.Objects;

import Game.RefLinks;
import Game.Tiles.Tile;

import java.awt.image.BufferedImage;

public abstract class Character extends Item
{
    static final int second = 60;
    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED         = 1.0f;
    public static final int DEFAULT_CREATURE_WIDTH  = 32;
    public static final int DEFAULT_CREATURE_HEIGHT = 32;
    private int HEALTH_CAP = 5;
    private int MANA_CAP = 10;
    int mana, manaRegen = 3 * second, healthRegen = 5 * second;

    protected BufferedImage image;
    protected int health;
    protected float speed;
    protected float xMove;
    protected float yMove;
    protected float cooldown;
    boolean lastDir;
    int timer = 10000;

    public Character(RefLinks refLink, float x, float y, int width, int height)
    {
        super(refLink, x, y, width, height);
        health = DEFAULT_HEALTH;
        speed   = DEFAULT_SPEED;
        xMove   = 0;
        yMove   = 0;
    }
    public void Move()
    {
        MoveX();
        MoveY();
    }
    public void MoveX()
    {
        float nextX = x + xMove * speed;
        if(nextX < 0 || nextX > refLink.GetMap().getWidth() * (Tile.TILE_WIDTH - 1))
        {
            xMove = 0;
        }
        x += xMove * speed;
        if(xMove == 1)
        {
            lastDir = true;
        }
        else
        if(xMove == -1)
        {
            lastDir = false;
        }
    }
    public void MoveY()
    {
        float nextY = y + yMove * speed;
        if(nextY < 0 || nextY > refLink.GetMap().getHeight() * (Tile.TILE_HEIGHT - 1))
        {
            yMove = 0;
        }
        y += yMove * speed;
    }
    public void SetImage(BufferedImage image)
    {
        this.image = image;
    }
    public int GetHealth()
    {
        return health;
    }
    public int GetMana()
    {
        return health;
    }
    public float GetSpeed()
    {
        return speed;
    }
    public void SetSpeed(float speed) {
        this.speed = speed;
    }
    public float GetXMove()
    {
        return xMove;
    }
    public float GetYMove()
    {
        return yMove;
    }
    public void SetXMove(float xMove)
    {
        this.xMove = xMove;
    }
    public void SetYMove(float yMove)
    {
        this.yMove = yMove;
    }
    public boolean IsDead()
    {
        return health <= 0;
    }
    public void SetMana(int mana)
    {
        if(mana <= MANA_CAP)
        {
            this.mana = mana;
        }
    }
    public void ManaRegen()
    {
        if(timer % manaRegen == 0)
        {
            SetMana(mana+1);
        }
    }
    public void SetHealth(int hp)
    {
        if(hp <= HEALTH_CAP)
        {
            health = hp;
        }
    }
    public void HealthRegen()
    {
        if(timer % healthRegen == 0)
        {
            SetHealth(health + 1);
        }
    }
}
