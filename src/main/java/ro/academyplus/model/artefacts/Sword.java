package ro.academyplus.model.artefacts;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by MM on 2016-02-23.
 */
@Entity
@Table(name = "SWORD")
public class Sword extends Artefact {
    private int damage;

    public Sword(String name, int damage) {
        this.setName(name);
        this.damage = damage;
        this.artefactType = artefactType.SWORD;
    }

    public int getDamage() {
        return damage;
    }
}