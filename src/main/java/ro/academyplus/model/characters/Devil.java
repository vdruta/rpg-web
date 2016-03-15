package ro.academyplus.model.characters;

/**
 * Created by MM on 2016-02-23.
 */

public class Devil extends Villain {
    public Devil (String name, int level){
        super(name, level);
        health = 70 + 70 * level / 10;
        damage = 340 + 340 * level / 7;
    }
}
