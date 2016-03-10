package ro.academyplus.model.characters;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by MM on 2016-02-23.
 */
@Entity
@Table(name = "DARKMAGE")
public class DarkMage extends Villain {
    public DarkMage(String name, int level){
        super(name, level);
        health = 100 + level * 5;
        damage = 7 + level * 5;
    }
}
