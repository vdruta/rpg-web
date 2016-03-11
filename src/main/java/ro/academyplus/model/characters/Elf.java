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
        health = 1200;
        damage = 70;
        heroType = heroType.ELF;
    }

    public Elf(){}
}
