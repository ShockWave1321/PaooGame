package Game.Objects;

import Game.RefLinks;

import java.awt.*;

public abstract class Item
{
    protected float x;
    protected float y;
    protected int width;
    protected int height;
    protected Rectangle bounds;
    protected Rectangle normalBounds;
    protected Rectangle attackBounds;

    protected RefLinks refLink;

    public Item(RefLinks refLink, float x, float y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.refLink = refLink;

        normalBounds = new Rectangle(0, 0, width, height);

        attackBounds = new Rectangle(0, 0, width, height);

        bounds = normalBounds;
    }
    public abstract void Update();

    public abstract void Draw(Graphics g);
    public float GetX()
    {
        return x;
    }
    public float GetY()
    {
        return y;
    }
    public int GetWidth()
    {
        return width;
    }
    public int GetHeight()
    {
        return height;
    }
    public void SetX(float x)
    {
        this.x = x;
    }
    public void SetY(float y)
    {
        this.y = y;
    }
    public void SetWidth(int width)
    {
        this.width = width;
    }
    public void SetHeight(int height)
    {
        this.height = height;
    }
    public void SetNormalMode()
    {
        bounds = normalBounds;
    }
    public void SetAttackMode()
    {
        bounds = attackBounds;
    }
    public Rectangle GetBounds() {
        return bounds;
    }
    public Rectangle WorldBounds()
    {
        return new Rectangle(bounds.x + (int)x,bounds.y + (int)y, bounds.width,bounds.height);
    }
}