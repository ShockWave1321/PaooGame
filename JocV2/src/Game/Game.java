package Game;

import Game.GameWindow.GameWindow;
import Game.Input.KeyManager;
import Game.States.*;
import Game.Graphics.Assets;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable
{
    static int pressed = 0, hold = 0;
    private static Game game = null;
    private GameWindow wnd;
    private boolean runState;
    private Thread gameThread;
    private BufferStrategy bs;
    private Graphics g;

    private State playState;
    private State menuState;
    private State settingsState;
    private State aboutState;
    private State pauseState;
    private State battleState;
    private KeyManager keyManager;
    private RefLinks refLink;

    private Game(String title, int width, int height)
    {
        wnd = new GameWindow(title, width, height);
        runState = false;
        keyManager = new KeyManager();

        wnd.BuildGameWindow();
        wnd.getWndFrame().addKeyListener(keyManager);
        Assets.Init();

        refLink = new RefLinks(this);
        playState       = new PlayState(refLink);
        menuState       = new MenuState(refLink);
        settingsState   = new SettingsState(refLink);
        aboutState      = new AboutState(refLink);
        pauseState      = new PauseState(refLink);
        battleState     = new BattleState(refLink);
        State.SetState(menuState);
    }

    public static Game InitGame()
    {
        if(game == null)
        {
            game = new Game("Game", 1440, 720);
        }
        return game;
    }

    public void run()
    {
        InitGame();
        long oldTime = System.nanoTime();
        long curentTime;
        final int framesPerSecond   = 60;
        final double timeFrame      = 1000000000.d / framesPerSecond;
        while (runState)
        {
            curentTime = System.nanoTime();
            if((curentTime - oldTime) > timeFrame)
            {
                Update();
                Draw();
                oldTime = curentTime;
            }
        }
    }

    public synchronized void StartGame()
    {
        if(!runState)
        {
            runState = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
        else
        {
            return;
        }
    }

    public synchronized void StopGame()
    {
        if(runState)
        {
            runState = false;
            try
            {
                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
        else
        {
            return;
        }
    }

    private void Update()
    {
        keyManager.Update();
        if(State.GetState() != null)
        {
            State.GetState().Update();
            NextState();
        }
    }

    private void Draw()
    {
        bs = wnd.getCanvas().getBufferStrategy();
        if(bs == null)
        {
            try
            {
                wnd.getCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.getWndWidth(), wnd.getWndHeight());

        if(State.GetState() != null)
        {
            State.GetState().Draw(g);
        }
        bs.show();
        g.dispose();
    }
    public int GetWidth()
    {
        return wnd.getWndWidth();
    }
    public int GetHeight()
    {
        return wnd.getWndHeight();
    }
    public KeyManager GetKeyManager()
    {
        return keyManager;
    }

    public void NextState()
    {
        if(refLink.GetKeyManager().enter)
        {
            pressed = 1;
            if(hold == 0)
            {
                if (State.GetState() == menuState)
                {
                    switch (MenuState.getCurrentOption())
                    {
                        case 0 : State.SetState(playState);
                                break;
                        case 4 : {
                            //StopGame();
                            System.exit(0);
                        }
                        break;
                        case 5 : State.SetState(battleState);
                            break;
                        default : State.SetState(menuState);
                    }
                }
                else if (State.GetState() == pauseState)
                {
                    switch (PauseState.getCurrentOption())
                    {
                        case 0 : State.SetState(State.PreviousState());
                            break;
                        case 3 : State.SetState(menuState);
                            break;
                        default : State.SetState(pauseState);
                    }
                }
            }
        }
        else
        if(refLink.GetKeyManager().exit)
        {
            pressed = 1;
            if(State.GetState() == playState)
                State.SetState(pauseState);
        }
        else
        {
            pressed = 0;
        }
        hold = pressed;
    }
}
