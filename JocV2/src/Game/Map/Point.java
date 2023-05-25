package Game.Map;

public class Point
{
    int x, y;

    public Point()
    {
        this.x = 0;
        this.y = 0;
    }
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public void SetPoint(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public Point GetPoint()
    {
        return new Point(x,y);
    }

    public int GetX()
    {
        return x;
    }
    public int GetY()
    {
        return y;
    }
}
