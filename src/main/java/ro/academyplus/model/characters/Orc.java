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
        health = 1800;
        damage = 80;
        heroType = HeroType.ORC;
        baseDamage = 80;
        baseHealth = 1800;

    }
    public Orc(){}
}
