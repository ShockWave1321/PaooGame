package Game.Graphics;

import Game.Input.KeyManager;
import java.awt.*;
import java.util.Vector;

public class UI
{
    KeyManager keyManager;
    int currentOption;
    int pressed, hold;
    Graphics g;
    int width, height;
    Font menuFont;
    Vector<String> options;
    public UI(Graphics graph, KeyManager keyManager)
    {
        g = graph;
        this.keyManager = keyManager;
        menuFont = new Font("Arial", Font.PLAIN,40);
        width = 1440;
        height = 720;
    }
    public void Draw()
    {
        g.setColor(Color.black);
        g.fillRect(0,0, width, height);
        g.setFont(menuFont);
    }
    public void OptionAdd(String s)
    {
        options.add(s);
    }
    public void Select()
    {
        int nextPos = 0;
        if (keyManager.up)
        {
            pressed = 1;
            nextPos = -1;
        }
        else
        if (keyManager.down)
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
}
