package Game;

import Game.DataBase.DataBase;
import Game.GameWindow.GameWindow;
import Game.Input.KeyManager;
import Game.Objects.Hero;
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
    private State infoState;
    private State pauseState;
    private KeyManager keyManager;
    private RefLinks refLink;
    private DataBase database;
    int t = 0;

    private Game(String title, int width, int height)
    {
        wnd = new GameWindow(title, width, height);
        runState = false;
        keyManager = new KeyManager();

        wnd.BuildGameWindow();
        wnd.getWndFrame().addKeyListener(keyManager);
        Assets.Init();

        refLink         = new RefLinks(this);
        database        = new DataBase();

        playState       = new PlayState(refLink);
        menuState       = new MenuState(refLink);
        infoState      = new InfoState(refLink);
        pauseState      = new PauseState(refLink);

        State.SetState(menuState);
    }

    public static Game InitGame()
    {
        if(game == null)
        {
            game = new Game("Game", 1440, 720); ///("Game", 1440, 720); ("Game", 1920, 1080);
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
        if(State.GetState() != null) {
            State.GetState().Update();
            NextState();
            if (t > 60){
                //System.out.println(State.GetState().toString());
                t = 0;
            }
        }
        t++;
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
                        case 0 -> {
                            playState = new PlayState(refLink);
                            State.SetState(playState);
                        }
                        case 1 -> State.SetState(playState);
                        case 2 -> {
                            database.DeleteDB();
                        }
                        case 3 -> State.SetState(infoState);
                        case 4 -> {
                            //StopGame();
                            System.exit(0);
                        }
                        default -> State.SetState(menuState);
                    }
                }
                else if (State.GetState() == pauseState)
                {
                    switch (PauseState.GetCurrentOption()) {
                        case 0 -> State.SetState(State.PreviousState());
                        case 1 ->{
                            Hero hero = refLink.GetHero();
                            database.InsertCharacterRecord(
                                    hero.GetX(),
                                    hero.GetY(),
                                    hero.GetHealth(),
                                    hero.GetMana(),
                                    hero.GetLevel(),
                                    hero.GetExperience(),
                                    hero.GetMoney()
                            );
                            State.SetState(menuState);
                        }
                        case 2 -> State.SetState(menuState);
                        default -> State.SetState(pauseState);
                    }
                }
                else
                if(State.GetState() == infoState)
                {
                    State.SetState(State.PreviousState());
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
