package Game.Tiles;

import Game.Graphics.Assets;

public class VoidTile extends Tile
{
    public VoidTile(int id)
    {
        super(Assets.Void, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
