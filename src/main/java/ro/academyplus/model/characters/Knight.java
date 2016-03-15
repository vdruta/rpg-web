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
        health = 2000;
        damage = 70;
        heroType = HeroType.KNIGHT;
    }
    public Knight(){
    }
}