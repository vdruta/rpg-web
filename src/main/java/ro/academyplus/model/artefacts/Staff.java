package ro.academyplus.model.artefacts;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by MM on 2016-02-23.
 */
@Entity
@Table(name = "STAFF")
public class Staff extends Artefact{
    private int damage;

    public Staff(String name, int damage) {
        this.setName(name);
        this.damage = damage;
        this.artefactType = artefactType.STAFF;
    }

    public Staff(){

    }
    public int getDamage() {
        return damage;
    }
}