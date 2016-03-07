package ro.academyplus.model.artefacts;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by MM on 2016-02-23.
 */
@Entity
@Table(name = "ARMOR")
public class Armor extends Artefact {
    private int health;

    public Armor(String name, int health){
        this.setName(name);
        this.health = health;
    }

    public int getHealth(){
        return health;
    }
}
