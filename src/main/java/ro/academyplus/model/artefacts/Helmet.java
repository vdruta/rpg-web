package ro.academyplus.model.artefacts;

/**
 * Created by MM on 2016-02-23.
 */
public class Helmet extends Artefact {
    private int health;

    public Helmet(String name, int health){
        this.setName(name);
        this.health = health;
    }

    public int getHealth(){
        return health;
    }
}