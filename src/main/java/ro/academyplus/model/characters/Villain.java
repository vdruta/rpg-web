package ro.academyplus.model.characters;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by MM on 2016-02-23.
 */
public class Villain extends Character {
    public Villain(String name, int level) {
        super(name);
        this.level = level;
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
        if (obj instanceof Villain) {
            Villain villain = (Villain) obj;
            if (this.getName() == villain.getName())
                return true;
        }
        return false;
    }

    public void saveVillainToFile(String file) {
        try {
            FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Villain readVillainFromFile(String file) {
        Villain villain = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            villain = (Villain) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (villain);
    }

}
