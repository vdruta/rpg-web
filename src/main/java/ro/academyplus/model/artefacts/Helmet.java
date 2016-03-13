package ro.academyplus.model.artefacts;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by MM on 2016-02-23.
 */
@Entity
@Table(name = "HELMET")
public class Helmet extends Artefact {
    private int health;

    public Helmet(String name, int health){
        this.setName(name);
        this.health = health;
        this.artefactType = artefactType.HELMET;
    }

    public Helmet(){

    }
    public int getHealth(){
        return health;
    }
}