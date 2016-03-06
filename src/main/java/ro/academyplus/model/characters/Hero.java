package ro.academyplus.model.characters;


import ro.academyplus.model.artefacts.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * Created by MM on 2016-02-23.
 */
public class Hero extends Character implements ManageCharacter {
    private ArrayList<Artefact> inventory = new ArrayList<Artefact>();
    private int inventoryCount;
    private int inventorySize = 3;

    public Hero(String name) {
        super(name);
        this.level = 1;
    }

    @Override
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

    public ArrayList<Artefact> getInventory() {
        return inventory;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void levelUp(){
        level++;
        inventorySize++;
    }

    public void addStartWeapon(String weapon)throws Exception {
        inventoryCount++;
        if (inventoryCount > inventorySize ) {
            throw new Exception("Inventory is full. A hero can keep in the inventory max " + inventorySize + " artefacts");
        }
            if (weapon.compareTo("armor") == 0)
                addHealth(new Armor("armor", 330));
            else if (weapon.compareTo("helmet") == 0)
                addHealth(new Helmet("helmet", 130));
            else if (weapon.compareTo("axe") == 0)
                addDamage(new Axe("axe", 60));
            else if (weapon.compareTo("bow") == 0)
                addDamage(new Bow("bow", 20));
            else if (weapon.compareTo("staff") == 0)
                addDamage(new Staff("staff", 40));
            else if (weapon.compareTo("sword") == 0)
                addDamage(new Sword("sword", 70));
    }

    public void addArtefact (Artefact artefact) throws Exception {
        inventoryCount++;
        if (inventoryCount > inventorySize) {
            throw new Exception("Inventory is full. A hero can keep in the inventory max " + inventorySize + " artefacts");
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

    public void saveHeroToFile(String file) {
        try {
            FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Hero readHeroFromFile(String file) {
        Hero hero = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            hero = (Hero) ois.readObject();
            ois.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return (hero);
    }

}