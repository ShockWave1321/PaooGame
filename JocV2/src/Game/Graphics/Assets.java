package Game.Graphics;

import java.awt.image.BufferedImage;

public class Assets
{
    public static BufferedImage sand, dryTree, stone, waterGrass, tent, tower,
                                Void, heroLeft, heroRight, heroFront, heroBack,
                                heroFrontRunLeft, heroFrontRunRight, heroLeftRunLeft, heroLeftRunRight,
                                heroRightRunLeft, heroRightRunRight, heroBackRunLeft, heroBackRunRight,
                                girlFront, battleMap;
    public static void Init()
    {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/WorldMap/MapSpriteSheet.png"));
        sand = sheet.crop(0,0);
        dryTree = sheet.crop(1,0);
        stone= sheet.crop(2,0);
        waterGrass = sheet.crop(3,0);
        tent = sheet.crop(4,0);
        tower = sheet.crop(5,0);
        Void = sheet.crop(2,2);

        SpriteSheet heroSheet = new SpriteSheet(ImageLoader.LoadImage("/Characters/Characters.png"));
        heroFront = heroSheet.crop(0, 0);
        heroFrontRunLeft = heroSheet.crop(3, 0);
        heroFrontRunRight = heroSheet.crop(6,0);

        heroLeft = heroSheet.crop(1, 0);
        heroLeftRunLeft = heroSheet.crop(4, 0);
        heroLeftRunRight = heroSheet.crop(7, 0);

        heroBack = heroSheet.crop(2, 0);
        heroBackRunLeft = heroSheet.crop(5, 0);
        heroBackRunRight = heroSheet.crop(8, 0);

        heroRight = heroSheet.crop(9, 0);
        heroRightRunLeft = heroSheet.crop(10, 0);
        heroRightRunRight = heroSheet.crop(11, 0);

        girlFront = heroSheet.crop(0, 2);
        battleMap = ImageLoader.LoadImage("/FightMap/layer_1_2.png");
    }
}
