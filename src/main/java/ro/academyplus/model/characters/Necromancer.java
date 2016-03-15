package ro.academyplus.model.characters;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by MM on 2016-02-23.
 */

public class Necromancer extends Villain {
    public Necromancer(String name, int level){
        super(name, level);
        health = 100 + 100 * level / 10;
        damage = 340 + 340 * level / 9;
    }
}
