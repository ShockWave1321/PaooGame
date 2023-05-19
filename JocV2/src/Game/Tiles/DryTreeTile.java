package Game.Tiles;

import Game.Graphics.Assets;

public class DryTreeTile extends Tile
{
    public DryTreeTile(int id)
    {
        super(Assets.dryTree, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
