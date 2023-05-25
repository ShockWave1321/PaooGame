package Game.States;

import Game.RefLinks;

import java.awt.*;
import java.util.Vector;

public class InfoState extends State
{
    Font font;
    Vector<String> options;
    public InfoState(RefLinks refLink)
    {
        super(refLink);
        font = new Font("Arial", Font.PLAIN,50);
        options = new Vector<>();
        options.add("Deplasare: W, A, S, D sau UP, DOWN, LEFT, RIGHT");
        options.add("Abilitati: SPACE - atac normal");
        options.add("Tasta 1: Ice Daggers");
        options.add("Tasta 2: Ice Eruption");
        options.add("SHIFT: Sprint");
        options.add("E: Activare porti pentru teleportare");
        options.add("ESC: Intrare in pauza");
    }
    @Override
    public void Update()
    {

    }
    @Override
    public void Draw(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, refLink.GetWidth(), refLink.GetHeight());

        g.setFont(font);
        g.setColor(Color.white);

        g.drawString("Controale",600,100);
        g.setFont(font.deriveFont(30.f));
        for(int i = 0; i < options.size(); ++i)
        {
            g.drawString(options.get(i), refLink.GetWidth()/2 - 400,refLink.GetHeight()/2+i*50);
        }
    }
}
