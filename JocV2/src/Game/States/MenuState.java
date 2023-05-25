package Game.States;

import Game.RefLinks;

import java.awt.*;
import java.util.Vector;

public class MenuState extends State
{
    static int pressed = 0, hold = 0;
    private static int currentOption = 0;
    Font menuFont;
    Vector<String> options;
    Color titleColor, selectColor;
    public MenuState(RefLinks refLink)
    {
        super(refLink);
        menuFont = new Font("Arial", Font.PLAIN,40);
        titleColor = new Color(126, 31, 31, 255);
        selectColor = new Color(86, 20, 20, 255);
        options = new Vector<>();
        options.add("Start");
        options.add("Load Game");
        options.add("Reset");
        options.add("Controls");

        options.add("Exit");
    }
    @Override
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
        g.setColor(titleColor);
        g.drawString("Drowninig Ritm ",575,100);
        g.setColor(Color.white);
        for(int i = 0; i < options.size(); ++i)
        {
            g.setColor(Color.white);
            if(currentOption == i)
                g.setColor(selectColor);
            g.drawString(options.get(i), refLink.GetWidth()/2 - 100,refLink.GetHeight()/2+i*40);
        }
    }
    public static int getCurrentOption()
    {
        return currentOption;
    }
}