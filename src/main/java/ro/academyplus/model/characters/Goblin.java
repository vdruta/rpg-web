package ro.academyplus.model.characters;

/**
 * Created by MM on 2016-02-23.
 */

public class Goblin extends Villain {
    public Goblin(String name, int level){
        super(name, level);
        health = 80 + 80 * level / 10;
        damage = 340 + 340 * level / 8;
    }
}
