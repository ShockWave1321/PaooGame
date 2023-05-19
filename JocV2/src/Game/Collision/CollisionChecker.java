package Game.Collision;

import Game.Map.Map;
import Game.Objects.Character;
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
        float heroX = character.GetX() + character.getBounds().x;
        float heroY = character.GetY() + character.getBounds().y;

        float heroXW = heroX + character.getBounds().width;
        float heroYH = heroY + character.getBounds().height;

        Map map = refLinks.GetMap();
        if(
            map.GetTile((int)(heroX + xMove)/Tile.TILE_WIDTH,(int)(heroY)/Tile.TILE_HEIGHT).IsSolid() ||
            map.GetTile((int)(heroXW + xMove)/Tile.TILE_WIDTH,(int)(heroY)/Tile.TILE_HEIGHT).IsSolid() ||
            map.GetTile((int)(heroX + xMove)/Tile.TILE_WIDTH,(int)(heroYH)/Tile.TILE_HEIGHT).IsSolid() ||
            map.GetTile((int)(heroXW + xMove)/Tile.TILE_WIDTH,(int)(heroYH)/Tile.TILE_HEIGHT).IsSolid()
        )
        {
            character.SetXMove(0);
        }
        if(
            map.GetTile((int)(heroX)/Tile.TILE_WIDTH,(int)(heroY+yMove)/Tile.TILE_HEIGHT).IsSolid() ||
            map.GetTile((int)(heroXW)/Tile.TILE_WIDTH,(int)(heroY+yMove)/Tile.TILE_HEIGHT).IsSolid() ||
            map.GetTile((int)(heroX)/Tile.TILE_WIDTH,(int)(heroYH+yMove)/Tile.TILE_HEIGHT).IsSolid() ||
            map.GetTile((int)(heroXW)/Tile.TILE_WIDTH,(int)(heroYH+yMove)/Tile.TILE_HEIGHT).IsSolid()
        )
        {
            character.SetYMove(0);
        }

        if(t>30)
        {
           t = 0;
           System.out.println("X: "+(int)character.GetX()/Tile.TILE_WIDTH+"|Y: "+(int)character.GetY()/Tile.TILE_WIDTH);
        }
        ++t;
    }
    public void checkItemCollision()
    {
        if(character.isColliding())
        {
            character.SetXMove(0);
            character.SetYMove(0);
        }
    }
}

