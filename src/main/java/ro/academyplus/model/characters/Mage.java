package ro.academyplus.model.characters;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by MM on 2016-02-23.
 */
@Entity
@Table(name = "MAGE")
public class Mage extends Hero {
    public Mage(String name){
        super(name);
        health = 700 + level * 5;
        damage = 70 + level * 5;
        heroType = HeroType.MAGE;
    }
}
