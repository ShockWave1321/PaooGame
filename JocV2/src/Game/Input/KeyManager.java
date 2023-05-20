package Game.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener
{
    private final boolean[] keys;
    public boolean up, down, left, right, attack, exit, enter, shift;
    int pressed, hold;
    public KeyManager()
    {
        keys = new boolean[256];
    }

    public void Update()
    {
        up    = keys[KeyEvent.VK_W];
        down  = keys[KeyEvent.VK_S];
        left  = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        attack = keys[KeyEvent.VK_SPACE];
        exit = keys[KeyEvent.VK_ESCAPE];
        enter = keys[KeyEvent.VK_ENTER];
        shift = keys[KeyEvent.VK_SHIFT];
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        /*pressed = 1;
        if(hold == 0)
        {
            keys[e.getKeyCode()] = true;
        }
        else
        {
            keys[e.getKeyCode()] = false;
            pressed = 0;
        }
        hold = pressed;*/
    }
}
