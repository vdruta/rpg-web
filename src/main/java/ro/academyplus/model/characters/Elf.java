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
        health = 1700 + level * 5;
        damage = 70 + level * 5;
        heroType = heroType.ELF;
    }
}
