package Game.Tiles;

import Game.Graphics.Assets;


public class LavaTile extends Tile
{
    public LavaTile(int idd)
    {
        super(Assets.lavaTile, idd);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
