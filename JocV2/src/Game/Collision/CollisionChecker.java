package Game.Collision;

import Game.Map.Map;
import Game.Objects.Hero;
import Game.Objects.Item;
import Game.RefLinks;
import Game.Tiles.Tile;

import java.awt.*;


public class CollisionChecker
{
    static int t = 0;
    private final RefLinks refLinks;
    int collisionRadius;
    public CollisionChecker(RefLinks refLinks)
    {
        this.collisionRadius = 10;
        this.refLinks = refLinks;
    }
    public void checkMapCollision()
    {
        Hero hero = refLinks.getHero();
        float xMove = hero.GetXMove();
        float yMove = hero.GetYMove();
        float heroX = hero.GetX() + hero.getBounds().x;
        float heroY = hero.GetY() + hero.getBounds().y;

        float heroXW = heroX + hero.getBounds().width;
        float heroYH = heroY + hero.getBounds().height;

        Map map = refLinks.GetMap();
        if( map.GetTile((int)(heroX + xMove)/Tile.TILE_WIDTH,(int)(heroY)/Tile.TILE_HEIGHT).IsSolid() ||
            map.GetTile((int)(heroXW + xMove)/Tile.TILE_WIDTH,(int)(heroY)/Tile.TILE_HEIGHT).IsSolid() ||
            map.GetTile((int)(heroX + xMove)/Tile.TILE_WIDTH,(int)(heroYH)/Tile.TILE_HEIGHT).IsSolid() ||
            map.GetTile((int)(heroXW + xMove)/Tile.TILE_WIDTH,(int)(heroYH)/Tile.TILE_HEIGHT).IsSolid()
        )
        {
            hero.SetXMove(0);
        }
        if( map.GetTile((int)(heroX)/Tile.TILE_WIDTH,(int)(heroY+yMove)/Tile.TILE_HEIGHT).IsSolid() ||
            map.GetTile((int)(heroXW)/Tile.TILE_WIDTH,(int)(heroY+yMove)/Tile.TILE_HEIGHT).IsSolid() ||
            map.GetTile((int)(heroX)/Tile.TILE_WIDTH,(int)(heroYH+yMove)/Tile.TILE_HEIGHT).IsSolid() ||
            map.GetTile((int)(heroXW)/Tile.TILE_WIDTH,(int)(heroYH+yMove)/Tile.TILE_HEIGHT).IsSolid()
        )
        {
            hero.SetYMove(0);
        }

        if(t>30)
        {
           t = 0;
           System.out.println("X: "+(int)hero.GetX()/Tile.TILE_WIDTH+"|Y: "+(int)hero.GetY()/Tile.TILE_WIDTH);
        }
        ++t;
    }
    public void checkCollisionWith(Item item)
    {

    }
    public boolean intersects(Rectangle r1, Rectangle r2)
    {
        return r1.x < r2.x + r2.width &&
                r2.x < r1.x + r1.width &&
                r1.y < r2.y + r2.height &&
                r2.y < r1.y + r1.height;
    }

}

