package ro.academyplus.model.characters;


import ro.academyplus.model.User;
import ro.academyplus.model.artefacts.*;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by MM on 2016-02-23.
 */
@Entity
@Table(name = "HERO")
public class Hero implements ManageCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    protected String name;
    protected int level;
    protected int health;
    protected int damage;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    protected List<Artefact> inventory;
    protected int inventoryCount;
    protected int inventorySize = 6;
    protected HeroType heroType;
    protected Date date;
    protected int experience;
    protected boolean death;

    public Hero(String name) {
        this.name = name;
        this.level = 1;
        this.date = new Date();
        this.experience = 0;
    }

    public Hero() {
    }

    public boolean isDeath() {
        return death;
    }

    public void setDeath(boolean death) {
        this.death = death;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void receiveDamage(int value) {
        health -= value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (obj instanceof Hero) {
            Hero hero = (Hero) obj;
            if (this.getId() == hero.getId())
                return true;
        }
        return false;
    }

    public List<Artefact> getInventory() {
        return inventory;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void levelUp(){ //TODO With every level up you get life, defense, attack, depending on hero type.
        level++;
        inventorySize++;
        if (this instanceof Elf) {
            health += health * level * 1 / 10;
            damage += damage * level * 1 / 10;
        }
        if (this instanceof Knight) {
            health += health * level * 1 / 10;
            damage += damage * level * 1 / 10;
        }
        if (this instanceof Mage) {
            health += health * level * 1 / 10;
            damage += damage * level * 1 / 10;
        }
        if (this instanceof Orc) {
            health += health * level * 1 / 10;
            damage += damage * level * 1 / 10;
        }
    }

    public void addArtefact (Artefact artefact) throws Exception {
        inventoryCount++;
        if (inventoryCount > inventorySize) {
            throw new Exception("Inventory is full. A hero can keep in the inventory max " + inventorySize + " artefacts");
        }
        if (inventoryAlreadyContainsArtefactType(artefact)) {
            throw new Exception("Inventory already contains this artefact type");
        }
        if (artefact instanceof Armor) {
            this.inventory.add(artefact);
            addHealth(artefact);
        }
        else if (artefact instanceof Axe) {
            this.inventory.add(artefact);
            addDamage(artefact);
        }
        else if (artefact instanceof Bow) {
            this.inventory.add(artefact);
            addDamage(artefact);
        }
        else if (artefact instanceof Helmet) {
            this.inventory.add(artefact);
            addHealth(artefact);
        }
        else if (artefact instanceof Staff) {
            this.inventory.add(artefact);
            addDamage(artefact);
        }
        else if (artefact instanceof Sword) {
            this.inventory.add(artefact);
            addDamage(artefact);
        }
    }

    private boolean inventoryAlreadyContainsArtefactType(Artefact artefact) {
        for (Artefact art: inventory) {
            if (art != null && art.equals(artefact))
                return true;
        }
        return false;
    }

    public void printInventory() {
        System.out.print("Print inventory: ");
        for (Artefact artefact : inventory)
            System.out.print(artefact.getName() + ", ");
        System.out.println();
    }

    @Override
    public void addHealth(Artefact artefact) {
        if (artefact instanceof Armor)
            health += ((Armor) artefact).getHealth();
        if (artefact instanceof Helmet)
            health += ((Helmet) artefact).getHealth();
    }

    @Override
    public void addDamage(Artefact artefact) {
        if (artefact instanceof Axe)
            damage += ((Axe) artefact).getDamage();
        if (artefact instanceof Bow)
            damage += ((Bow) artefact).getDamage();
        if (artefact instanceof Staff)
            damage += ((Staff) artefact).getDamage();
        if (artefact instanceof Sword)
            damage += ((Sword) artefact).getDamage();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setInventory(ArrayList<Artefact> inventory) {
        this.inventory = inventory;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public int getInventorySize() {
        return inventorySize;
    }

    public void setInventorySize(int inventorySize) {
        this.inventorySize = inventorySize;
    }

    public HeroType getHeroType() {
        return heroType;
    }

    public void setHeroType(HeroType heroType) {
        this.heroType = heroType;
    }

}