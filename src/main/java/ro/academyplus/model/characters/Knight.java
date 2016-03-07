package ro.academyplus.model.characters;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by MM on 2016-02-23.
 */
@Entity
@Table(name = "KNIGHT")
public class Knight extends Hero {
    public Knight(String name){
        super(name);
        health = 2700 + level * 5;
        damage = 7 + level * 5;
        heroType = HeroType.KNIGHT;
    }
}