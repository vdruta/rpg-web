package ro.academyplus.model;

import org.springframework.beans.factory.annotation.Autowired;
import ro.academyplus.model.artefacts.Artefact;
import ro.academyplus.model.characters.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by MM on 2016-03-09.
 */

public class Mission {

    private static int maplevel;
    private int width;
    private int height;
    private List<Villain> villains = new ArrayList<Villain>();
    private int[][] map;
    boolean win;
    boolean monster;
    String monsterLocation;
    boolean newArtefact;
    private Artefact latestArtefact = new Artefact();
    private boolean inventoryAlreadyContainsArtefact;
    private boolean monsterOnLastLocation;

    public Mission(int maplevel) {
        this.maplevel = maplevel;
        this.width = 3 + 2 * maplevel;
        this.height = 3 + 2 * maplevel;
        int monstersNumber = randInt(width * width / 3, width * width / 2);
        for (int i = 0; i < monstersNumber; i++) {
            if (i % 4 == 0) {
                DarkMage darkMage = new DarkMage("DarkMage" + i, maplevel);
                this.villains.add(darkMage);
            }
            else if (i % 4 == 1) {
                Devil devil = new Devil("Devil" + i, maplevel);
                this.villains.add(devil);
            }
            else if (i % 4 == 2) {
                Goblin goblin = new Goblin("Goblin" + i, maplevel);
                this.villains.add(goblin);
            }
            else if (i % 4 == 3) {
                Necromancer necromancer = new Necromancer("Necromancer" + i, maplevel);
                this.villains.add(necromancer);
            }
        }
        this.map = new int[width][width];
        //put hero on map center; (2)
        map[width / 2][width / 2] = 2;
        //put villains random on map (1)
        map = putVillainsOnMap(map, monstersNumber, width);

    }

    private int[][] putVillainsOnMap(int[][] map, int monstersNumber, int x) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                if (map[i][j] != 2) {
                    map[i][j] = 0;
                }
            }
        }
        int i, j;
        while (monstersNumber > 0) {
            i = randInt(0, x - 1);
            j = randInt(0, x - 1);
            if (map[i][j] != 2 && map[i][j] != 1) {
                map[i][j] = 1;
                Villain villain = villains.get(monstersNumber - 1);
                villain.setIy(i);
                villain.setJx(j);
                monstersNumber--;
            }
        }
        return map;
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int[][] getMap() {
        return map;
    }

    public void setVillains(List<Villain> villains) {
        this.villains = villains;
    }

    public List<Villain> getVillains() {
        return villains;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isMonster() {
        return monster;
    }

    public void setMonster(boolean monster) {
        this.monster = monster;
    }

    public String getMonsterLocation() {
        return monsterLocation;
    }

    public void setMonsterLocation(String monsterLocation) {
        this.monsterLocation = monsterLocation;
    }

    public boolean isNewArtefact() {
        return newArtefact;
    }

    public void setNewArtefact(boolean newArtefact) {
        this.newArtefact = newArtefact;
    }

    public Artefact getLatestArtefact() {
        return latestArtefact;
    }

    public void setLatestArtefact(Artefact latestArtefact) {
        this.latestArtefact = latestArtefact;
    }

    public boolean isInventoryAlreadyContainsArtefact() {
        return inventoryAlreadyContainsArtefact;
    }

    public void setInventoryAlreadyContainsArtefact(boolean inventoryAlreadyContainsArtefact) {
        this.inventoryAlreadyContainsArtefact = inventoryAlreadyContainsArtefact;
    }

    public boolean isMonsterOnLastLocation() {
        return monsterOnLastLocation;
    }

    public void setMonsterOnLastLocation(boolean monsterOnLastLocation) {
        this.monsterOnLastLocation = monsterOnLastLocation;
    }
}
