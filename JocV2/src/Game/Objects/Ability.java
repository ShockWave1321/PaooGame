package Game.Objects;

import java.awt.*;

public abstract class Ability extends Item {
    static final int second = 60;
    static final int DEFAULT_ABILITY_WIDTH = 24;
    static final int DEFAULT_ABILITY_HEIGHT = 16;
    int range = 600;
    int manaCost = 1;
    float cooldown = second;
    float speed = 10.f;
    boolean fired;
    int damage;
    public Ability(float x, float y)
    {
        super(null, x, y , DEFAULT_ABILITY_WIDTH, DEFAULT_ABILITY_HEIGHT);
        fired = true;
        damage = 1;
    }
    public boolean IsFired()
    {
        return fired;
    }
    public boolean Hits(Item item)
    {
        boolean hit = new Rectangle((int)x + bounds.x, (int)y + bounds.y , DEFAULT_ABILITY_WIDTH, DEFAULT_ABILITY_HEIGHT).intersects(item.WorldBounds());
        if(hit)
        {
            fired = false;
        }
        return hit;
    }
    public void Damage(Character character)
    {
        character.health -= damage;
    }
    public int GetManaCost()
    {
        return manaCost;
    }
    public float GetCooldown()
    {
        return cooldown;
    }
}
