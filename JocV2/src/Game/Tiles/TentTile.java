package Game.Tiles;

import Game.Graphics.Assets;

public class TentTile extends Tile
{
    public TentTile(int id)
    {
        super(Assets.tent, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
