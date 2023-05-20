package Game.Objects;

import Game.RefLinks;
import Game.Tiles.Tile;

import java.awt.*;

public abstract class Character extends Item
{
    public static final int DEFAULT_LIFE            = 10;
    public static final float DEFAULT_SPEED         = 4.0f;
    public static final int DEFAULT_CREATURE_WIDTH  = 32;
    public static final int DEFAULT_CREATURE_HEIGHT = 32;

    protected int life;
    protected float speed;
    protected float xMove;
    protected float yMove;
    public Character(RefLinks refLink, float x, float y, int width, int height)
    {
        super(refLink, x, y, width, height);
        life    = DEFAULT_LIFE;
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
        float nextX = x + xMove;
        if(nextX < 0 || nextX > refLink.GetMap().getWidth() * (Tile.TILE_WIDTH - 1))
        {
            xMove = 0;
        }
        x += xMove;
    }
    public void MoveY()
    {
        float nextY = y + yMove;
        if(nextY < 0 || nextY > refLink.GetMap().getHeight() * (Tile.TILE_HEIGHT - 1))
        {
            yMove = 0;
        }
        y += yMove;
    }
    public int GetLife()
    {
        return life;
    }
    public float GetSpeed()
    {
        return speed;
    }
    public void SetLife(int life)
    {
        this.life = life;
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
    public boolean CheckItemCollision(Item item)
    {
        return this.WorldBounds().intersects(item.WorldBounds());
    }
}
