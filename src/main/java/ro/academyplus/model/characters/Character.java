package ro.academyplus.model.characters;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Observable;

/**
 * Created by MM on 2016-02-23.
 */
@MappedSuperclass
public abstract class Character implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    protected String name;
    protected int level;
    protected int health;
    protected int damage;
    protected int iy;
    protected int jx;

    public int getJx() {
        return jx;
    }

    public void setJx(int jx) {
        this.jx = jx;
    }

    public int getIy() {
        return iy;
    }

    public void setIy(int iy) {
        this.iy = iy;
    }

    public Character(String name){
        this.name = name;
        this.level = 1;
    }


    public String getName(){
        return name;
    }

    public int getHealth(){
        return health;
    }

    public int getLevel(){
        return level;
    }

    public int getDamage(){
        return damage;
    }

    public int getId() {
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public abstract void receiveDamage(int value);

    public abstract boolean equals (Object obj);
/*
    public boolean equals (Object obj){
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (obj instanceof Character) {
            Character anotherObj = (Character) obj;
            if (this.getId() == anotherObj.getId())
                return true;
        }
        return false;
    }
*/
    public void printStats (){
        System.out.print("Name: ");
        System.out.print(name);
        System.out.print(" ; Level: ");
        System.out.print(level);
        System.out.print(" ; Health: ");
        System.out.print(health);
        System.out.print(" ; Damage: ");
        System.out.print(damage);
        System.out.print(" ; Id: ");
        System.out.println(id);
    }
}
