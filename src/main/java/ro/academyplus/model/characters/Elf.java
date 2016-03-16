package ro.academyplus.model.characters;

import javax.persistence.*;

/**
 * Created by MM on 2016-02-23.
 */
@Entity
@Table(name = "ELF")
public class Elf extends Hero{

    public Elf(String name){
        super(name);
        health = 1600;
        damage = 90;
        heroType = heroType.ELF;
        baseHealth = 1600;
        baseDamage = 90;

    }

    public Elf(){}
}
