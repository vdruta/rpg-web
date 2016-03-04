package ro.academyplus.model.artefacts;

/**
 * Created by MM on 2016-02-23.
 */
public class Axe extends Artefact {
    private int damage;

    public Axe(String name, int damage){
        this.setName(name);
        this.damage = damage;
    }

    public int getDamage(){
        return damage;
    }
}