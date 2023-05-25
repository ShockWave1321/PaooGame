package Game.Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets
{
    public static BufferedImage sand, dryTree, stone, waterGrass, tent, tower,
                                Void, heroLeft, heroRight, heroUp, heroDown,
                                heroUpRunLeft, heroUpRunRight, heroLeftRunLeft, heroLeftRunRight,
                                heroRightRunLeft, heroRightRunRight, heroDownRunLeft, heroDownRunRight,
                                girlFront, battleMap, chest, coin, scroll;

    public static BufferedImage[] heroRunLeft, heroRunRight, heroRunUp, heroRunDown, monsterLeft, monsterRight;
    public static BufferedImage[][] heroRun, iceDagger, monster, slash;
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
        heroUp = heroSheet.crop(0, 0);
        heroUpRunLeft = heroSheet.crop(3, 0);
        heroUpRunRight = heroSheet.crop(6,0);

        heroLeft = heroSheet.crop(1, 0);
        heroLeftRunLeft = heroSheet.crop(4, 0);
        heroLeftRunRight = heroSheet.crop(7, 0);

        heroDown = heroSheet.crop(2, 0);
        heroDownRunLeft = heroSheet.crop(5, 0);
        heroDownRunRight = heroSheet.crop(8, 0);

        heroRight = heroSheet.crop(9, 0);
        heroRightRunLeft = heroSheet.crop(10, 0);
        heroRightRunRight = heroSheet.crop(11, 0);

        girlFront = heroSheet.crop(0, 2);
        battleMap = ImageLoader.LoadImage("/FightMap/layer_1_2.png");

        heroRunLeft = new BufferedImage[3];
        heroRunLeft[0] = heroLeft;
        heroRunLeft[1] = heroLeftRunLeft;
        heroRunLeft[2] = heroLeftRunRight;

        heroRunRight = new BufferedImage[3];
        heroRunRight[0] = heroRight;
        heroRunRight[1] = heroRightRunLeft;
        heroRunRight[2] = heroRightRunRight;

        heroRunUp = new BufferedImage[3];
        heroRunUp[0] = heroUp;
        heroRunUp[1] = heroUpRunLeft;
        heroRunUp[2] = heroUpRunRight;

        heroRunDown = new BufferedImage[3];
        heroRunDown[0] = heroDown;
        heroRunDown[1] = heroDownRunLeft;
        heroRunDown[2] = heroDownRunRight;

        heroRun = new BufferedImage[4][3];
        heroRun[0] = heroRunUp;
        heroRun[1] = heroRunDown;
        heroRun[2] = heroRunLeft;
        heroRun[3] = heroRunRight;


        SpriteSheet iceDaggerSheet = new SpriteSheet(ImageLoader.LoadImage("/Abilities/IceDagger.png"));
        iceDagger = new BufferedImage[2][10];
        for(int i = 0;i<10;++i)
        {
            iceDagger[0][i] = iceDaggerSheet.crop(i,0,48,32);
            iceDagger[1][i] = iceDaggerSheet.crop(9-i,1,48,32);
        }

        monster = new BufferedImage[8][];
        monster[0] = new BufferedImage[6];
        monster[1] = new BufferedImage[6];
        monster[4] = new BufferedImage[6];
        monster[5] = new BufferedImage[6];
        monster[2] = new BufferedImage[4];
        monster[6] = new BufferedImage[4];
        monster[3] = new BufferedImage[8];
        monster[7] = new BufferedImage[8];
        SpriteSheet monsterSheet = new SpriteSheet(ImageLoader.LoadImage("/Characters/Monster1.png"));
        for(int i = 0;i<6;++i)
        {
            monster[0][i] = monsterSheet.crop(i,0,64,64);
            monster[1][i] = monsterSheet.crop(i,1,64,64);
            monster[4][i] = monsterSheet.crop(7-i,4,64,64);
            monster[5][i] = monsterSheet.crop(7-i,5,64,64);
        }
        for(int i = 0;i < 4;++i)
        {
            monster[2][i] = monsterSheet.crop(i,2,64,64);
            monster[6][i] = monsterSheet.crop(7-i,6,64,64);
        }
        for(int i = 0;i<8;++i)
        {
            monster[3][i] = monsterSheet.crop(i,3,64,64);
            monster[7][i] = monsterSheet.crop(7-i,7,64,64);
        }
        SpriteSheet chestsSheet = new SpriteSheet(ImageLoader.LoadImage("/Items/Chests.png"));
        chest = chestsSheet.crop(4,0);

        SpriteSheet slashSheet = new SpriteSheet(ImageLoader.LoadImage("/Abilities/Slash.png"));
        slash = new BufferedImage[2][3];
        for(int i = 0; i < 3; ++i)
        {
            slash[0][i] = slashSheet.crop(i,2,64,64);
            slash[1][i] = slashSheet.crop(i,1,64,64);
        }

        coin = ImageLoader.LoadImage("/Items/StaticCoin.png");
        scroll = ImageLoader.LoadImage("/Items/StaticScroll.png");
    }
}
