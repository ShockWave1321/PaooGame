package Game.Tiles;

import Game.Graphics.Assets;

public class TowerTile extends Tile
{
    public TowerTile(int id)
    {
        super(Assets.tower, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
