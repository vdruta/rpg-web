package ro.academyplus.model;

import ro.academyplus.model.characters.Hero;

/**
 * Created by MM on 2016-03-14.
 */
public class Arena {
    private final String name;
    private Hero hero1 = new Hero();
    private Hero hero2 = new Hero();

    public Arena(String name){
        this.name = name;
    }

    public Hero getHero1() {
        return hero1;
    }

    public void setHero1(Hero hero1) {
        this.hero1 = hero1;
    }

    public Hero getHero2() {
        return hero2;
    }

    public void setHero2(Hero hero2) {
        this.hero2 = hero2;
    }




}
