package Game.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener
{
    private final boolean[] keys;
    public boolean up, down, left, right, attack, exit, enter, shift, event,
                    k1, k2, k3, k4;


    //int pressed = 0, hold = 0;
    public KeyManager()
    {
        keys = new boolean[256];
    }

    public void Update()
    {
        up    = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down  = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left  = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
        attack = keys[KeyEvent.VK_SPACE];
        exit = keys[KeyEvent.VK_ESCAPE];
        enter = keys[KeyEvent.VK_ENTER];
        shift = keys[KeyEvent.VK_SHIFT];
        event = keys[KeyEvent.VK_E];
        k1 = keys[KeyEvent.VK_1];
        k2 = keys[KeyEvent.VK_2];
        k3 = keys[KeyEvent.VK_3];
        k4 = keys[KeyEvent.VK_4];
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
        /*pressed = 0;
        if(hold == 0)
        {
            keys[e.getKeyCode()] = true;
            pressed = 1;
        }
        else
        {
            keys[e.getKeyCode()] = false;
        }
        hold = pressed;*/
    }
}
