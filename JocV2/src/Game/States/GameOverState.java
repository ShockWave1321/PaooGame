package Game.States;

import Game.Game;
import Game.RefLinks;

import java.awt.*;

public class GameOverState extends State
{
    static int pressed = 0, hold = 0;
    Font menuFont;
    public GameOverState(RefLinks refLink)
    {
        super(refLink);
        menuFont = new Font("Arial", Font.PLAIN,40);

    }

    @Override
    public void Update()
    {
        if(refLink.GetKeyManager().enter)
        {
            //State.SetState(menuState);
        }
    }

    @Override
    public void Draw(Graphics g)
    {

    }
}
