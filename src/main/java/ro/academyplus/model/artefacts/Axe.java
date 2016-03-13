package ro.academyplus.model.artefacts;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by MM on 2016-02-23.
 */
@Entity
@Table(name = "AXE")
public class Axe extends Artefact {
    private int damage;

    public Axe(String name, int damage){
        this.setName(name);
        this.damage = damage;
        this.artefactType = artefactType.AXE;
    }

    public int getDamage(){
        return damage;
    }
}