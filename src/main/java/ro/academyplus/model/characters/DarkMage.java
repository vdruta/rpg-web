package ro.academyplus.model.characters;

/**
 * Created by MM on 2016-02-23.
 */

public class DarkMage extends Villain {
    public DarkMage(String name, int level){
        super(name, level);
        health = 90 + 90 * level / 10;
        damage = 340 + 340 * level / 10;
    }
}
