package Game.Collision;

import Game.Map.Map;
import Game.Objects.Character;
import Game.Objects.Item;
import Game.RefLinks;
import Game.Tiles.Tile;

import java.awt.*;


public class CollisionChecker
{
    static int t = 0;
    private final RefLinks refLinks;
    private final Character character;
    int collisionRadius;
    public CollisionChecker(RefLinks refLinks, Character character)
    {
        this.collisionRadius = 10;
        this.refLinks = refLinks;
        this.character = character;
    }
    public void checkMapCollision()
    {
        float speed = character.GetSpeed();
        float xMove = character.GetXMove() * speed;
        float yMove = character.GetYMove() * speed;
        float characterX = character.GetX() + character.GetBounds().x;
        float characterY = character.GetY() + character.GetBounds().y;

        float characterXW = characterX + character.GetBounds().width;
        float characterYH = characterY + character.GetBounds().height;

        Map map = refLinks.GetMap();
        if(
            map.GetTile((int)(characterX + xMove)/Tile.TILE_WIDTH,(int)(characterY)/Tile.TILE_HEIGHT).IsSolid() ||
            map.GetTile((int)(characterXW + xMove)/Tile.TILE_WIDTH,(int)(characterY)/Tile.TILE_HEIGHT).IsSolid() ||
            map.GetTile((int)(characterX + xMove)/Tile.TILE_WIDTH,(int)(characterYH)/Tile.TILE_HEIGHT).IsSolid() ||
            map.GetTile((int)(characterXW + xMove)/Tile.TILE_WIDTH,(int)(characterYH)/Tile.TILE_HEIGHT).IsSolid()
        )
        {
            character.SetXMove(0);
        }
        if(
            map.GetTile((int)(characterX)/Tile.TILE_WIDTH,(int)(characterY+yMove)/Tile.TILE_HEIGHT).IsSolid() ||
            map.GetTile((int)(characterXW)/Tile.TILE_WIDTH,(int)(characterY+yMove)/Tile.TILE_HEIGHT).IsSolid() ||
            map.GetTile((int)(characterX)/Tile.TILE_WIDTH,(int)(characterYH+yMove)/Tile.TILE_HEIGHT).IsSolid() ||
            map.GetTile((int)(characterXW)/Tile.TILE_WIDTH,(int)(characterYH+yMove)/Tile.TILE_HEIGHT).IsSolid()
        )
        {
            character.SetYMove(0);
        }

        /*if(t>40)
        {
           t = 0;
           System.out.println("X: "+(int)refLinks.GetHero().GetX()/Tile.TILE_WIDTH+"|Y: "+(int)refLinks.GetHero().GetY()/Tile.TILE_WIDTH);
        }
        ++t;*/
    }
    public boolean CheckItemCollision(Item item)
    {
        Rectangle crr = character.WorldBounds();
        int crx = crr.x;
        int cry = crr.y;
        int crw = crr.width;
        int crh = crr.height;
        int crS = (int)character.GetSpeed();
        Rectangle ri = item.WorldBounds();

        return new Rectangle(crx + crS, cry, crw, crh).intersects(ri) ||
                new Rectangle(crx - crS, cry, crw, crh).intersects(ri) ||
                new Rectangle(crx,cry + crS, crw, crh).intersects(ri) ||
                new Rectangle(crx,cry - crS, crw, crh).intersects(ri);
        /*Rectangle crr = character.WorldBounds();
        Rectangle ri = item.WorldBounds();

        return crr.intersects(ri);*/
    }

}

