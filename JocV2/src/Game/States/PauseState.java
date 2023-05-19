package Game.States;

import Game.RefLinks;

import java.awt.*;
import java.util.Vector;

public class PauseState extends State
{
    static int pressed = 0, hold = 0;
    private static int currentOption = 0;
    Font menuFont;
    Vector<String> options;

    public PauseState(RefLinks refLink)
    {
        super(refLink);
        menuFont = new Font("Arial", Font.PLAIN,40);
        options = new Vector<>();
        options.add("Resume");
        options.add("Save Game");
        options.add("Options");
        options.add("Exit");
    }

    public void Update()
    {
        Select();
    }
    public void Select()
    {
        int nextPos = 0;
        if (refLink.GetKeyManager().up)
        {
            pressed = 1;
            nextPos = -1;
        }
        else
        if (refLink.GetKeyManager().down)
        {
            pressed = 1;
            nextPos = 1;
        }
        else
        {
            pressed = 0;
        }
        if(hold == 0)
        {
            currentOption += nextPos;
            if (currentOption > options.size() - 1) {
                currentOption = 0;
            }
            if (currentOption < 0) {
                currentOption = options.size() - 1;
            }
        }
        hold = pressed;
    }
    @Override
    public void Draw(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, refLink.GetWidth(), refLink.GetHeight());

        g.setFont(menuFont);
        g.setColor(Color.white);
        g.drawString("Pause",refLink.GetWidth()/2 - 80,refLink.GetHeight()/2 - 120);

        g.drawString("Option = " + currentOption,100,100);

        for(int i = 0; i < options.size(); ++i)
        {
            if(currentOption == i)
                g.setColor(Color.blue);
            else
                g.setColor(Color.white);
            g.drawString(options.get(i), refLink.GetWidth()/2 - 100,refLink.GetHeight()/2+i*40);
        }
    }
    public int midScreen()
    {
        return 0;
    }
    public static int getCurrentOption()
    {
        return currentOption;
    }
}
