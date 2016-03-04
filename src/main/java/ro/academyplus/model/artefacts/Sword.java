package ro.academyplus.model.artefacts;

/**
 * Created by MM on 2016-02-23.
 */
public class Sword extends Artefact {
    private int damage;

    public Sword(String name, int damage) {
        this.setName(name);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}