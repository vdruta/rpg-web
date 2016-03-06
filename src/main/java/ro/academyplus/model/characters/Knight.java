package ro.academyplus.model.characters;
/**
 * Created by MM on 2016-02-23.
 */
public class Knight extends Hero {
    public Knight(String name){
        super(name);
        health = 2700 + level * 5;
        damage = 7 + level * 5;
    }
}