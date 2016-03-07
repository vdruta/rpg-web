package ro.academyplus.model.artefacts;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by MM on 2016-02-23.
 */
@Entity
@Table(name = "BOW")
public class Bow extends Artefact{
     private int damage;

    public Bow(String name, int damage) {
        this.setName(name);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}