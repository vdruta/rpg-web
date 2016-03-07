package ro.academyplus.model.characters;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by MM on 2016-02-23.
 */
@Entity
@Table(name = "ORC")
public class Orc extends Hero {
    public Orc(String name){
        super(name);
        health = 1700 + level * 20;
        damage = 20 + level * 5;
        heroType = HeroType.ORC;
    }
}
