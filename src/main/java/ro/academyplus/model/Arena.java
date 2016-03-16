package ro.academyplus.model;

import ro.academyplus.model.characters.Hero;

import static ro.academyplus.model.Mission.randInt;

/**
 * Created by MM on 2016-03-14.
 */
public class Arena {
    private final String name;
    private Hero hero1 = new Hero();
    private boolean tourn1;
    private Hero hero2 = new Hero();
    private boolean tourn2;
    private int[][] map;
    private int width;

    public Arena(String name){
        this.name = name;
        this.tourn1 = true;
        this.width = 15;
        this.map = new int[width][width];
        initMap(map, width);
        putHeroRandomOnMap(map, width, 1);
        putHeroRandomOnMap(map, width, 2);
    }

    private void putHeroRandomOnMap(int[][] map, int width, int h) {
        while (h != 10) {
            int i = randInt(0, width - 1);
            int j = randInt(0, width - 1);
            if (map[i][j] != 1 && map[i][j] != 2) {
                map[i][j] = h;
                h = 10;
            }
        }
    }

    private void initMap(int[][] map, int width) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                    map[i][j] = 0;
            }
        }
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

    public String getName() {
        return name;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public boolean isTourn1() {
        return tourn1;
    }

    public void setTourn1(boolean tourn1) {
        this.tourn1 = tourn1;
    }

    public boolean isTourn2() {
        return tourn2;
    }

    public void setTourn2(boolean tourn2) {
        this.tourn2 = tourn2;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }
}
