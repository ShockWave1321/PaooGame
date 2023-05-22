package Game.Objects;

public class Ability
{
    static final int second = 60;
    int manaCost;
    float cooldown;
    static int timer = 1000;
    public Ability()
    {
        manaCost = 1;
        cooldown = second;
    }
    public void Fire()
    {
        if(timer > cooldown)
        {
            timer = 0;
        }
    }
    public void Draw()
    {

    }
}
