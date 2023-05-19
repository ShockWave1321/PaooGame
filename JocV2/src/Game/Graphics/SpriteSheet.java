package Game.Graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet
{
    private BufferedImage spriteSheet;
    private static final int    tileWidth   = 32;
    private static final int    tileHeight  = 32;


    public SpriteSheet(BufferedImage buffImg)
    {
        spriteSheet = buffImg;
    }

    public BufferedImage crop(int x, int y)
    {
        return spriteSheet.getSubimage(
                x * tileWidth,
                y * tileHeight,
                tileWidth,
                tileHeight
        );
    }
}
