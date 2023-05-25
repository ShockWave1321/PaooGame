package Game.Tiles;

import Game.Graphics.Assets;

public class BoulderTile extends Tile
{
    public BoulderTile(int idd)
    {
        super(Assets.boulderTile, idd);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
