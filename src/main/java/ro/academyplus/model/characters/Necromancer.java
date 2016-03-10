package ro.academyplus.model.characters;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by MM on 2016-02-23.
 */
@Entity
@Table(name = "NECROMANCER")
public class Necromancer extends Villain {
    public Necromancer(String name, int level){
        super(name, level);
        health = 10 + level * 5;
        damage = 7 + level * 5;
    }
}
