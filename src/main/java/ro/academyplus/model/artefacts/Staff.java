package ro.academyplus.model.artefacts;

/**
 * Created by MM on 2016-02-23.
 */
public class Staff extends Artefact{
    private int damage;

    public Staff(String name, int damage) {
        this.setName(name);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}