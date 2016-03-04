package ro.academyplus.model.characters;
/**
 * Created by MM on 2016-02-23.
 */
public class Mage extends Hero {
    public Mage(String name, int level){
        super(name, level);
        health = 700 + level * 5;
        damage = 70 + level * 5;
    }
}
