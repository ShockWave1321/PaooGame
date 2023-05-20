package Game.Collision;

import Game.Map.Map;
import Game.Objects.Character;
import Game.Objects.Item;
import Game.RefLinks;
import Game.Tiles.Tile;


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
        float xMove = character.GetXMove();
        float yMove = character.GetYMove();
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

        /*if(t>30)
        {
           t = 0;
           System.out.println("X: "+(int)refLinks.getHero().GetX()/Tile.TILE_WIDTH+"|Y: "+(int)refLinks.getHero().GetY()/Tile.TILE_WIDTH);
        }
        ++t;*/
    }
}

