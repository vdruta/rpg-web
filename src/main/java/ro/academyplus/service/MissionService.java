package ro.academyplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.academyplus.dto.MissionDTO;
import ro.academyplus.model.Mission;
import ro.academyplus.model.characters.DarkMage;
import ro.academyplus.model.characters.Devil;
import ro.academyplus.model.characters.Hero;
import ro.academyplus.model.characters.Villain;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;

/**
 * Created by MM on 2016-03-10.
 */
@Service
public class MissionService {

    @Autowired
    HttpServletRequest request;

    public void updateMission(MissionDTO missionDTO) {
        System.out.println(6);
        Mission mission = (Mission) request.getSession().getAttribute("mission");
        System.out.println(7);
        Hero hero = (Hero) request.getSession().getAttribute("hero");
        System.out.println(8);
        System.out.println(missionDTO.getSelectedAction());
        System.out.println(hero.getName());
        System.out.println(mission.getWidth());
        if (missionDTO.getSelectedAction().compareTo("Up") == 0) {
            System.out.println(9);
                if (dirIsMonster("Up", mission.getMap(), mission.getWidth())) {
                    System.out.println(10);
                    if (new Random().nextBoolean()) {
                        System.out.println(11);
                        fight("Up", hero, mission.getMap(), mission.getWidth(), mission.getVillains());
                        System.out.println(12);
                        if (hero.getHealth() > 0) {
                            moveHero("Up", mission.getMap(), mission.getWidth());
                            if (heroIsOnBorder("Up", mission.getMap(), mission.getWidth())) {
                                mission.setWin(true);
                            }
                        }
                        else {
                            hero.setDeath(true);
                            System.out.println(13);
                        }
                    }
                    else {
                        System.out.println("I can run, ha ha ha :)");
                        return ;
                    }
                }
                else {
                    moveHero("Up", mission.getMap(), mission.getWidth());
                    if (heroIsOnBorder("Up", mission.getMap(), mission.getWidth())) {
                        mission.setWin(true);
                    }
                }
        }
        if (missionDTO.getSelectedAction().compareTo("Down") == 0) {
            if (dirIsMonster("Down", mission.getMap(), mission.getWidth())) {
                if (new Random().nextBoolean()) {
                    fight("Down", hero, mission.getMap(), mission.getWidth(), mission.getVillains());
                    if (hero.getHealth() > 0) {
                        moveHero("Down", mission.getMap(), mission.getWidth());
                        if (heroIsOnBorder("Down", mission.getMap(), mission.getWidth())) {
                            mission.setWin(true);
                        }
                    }
                    else
                        hero.setDeath(true);
                }
                else {
                    System.out.println("I can run, ha ha ha :)");
                    return ;
                }
            }
            else {
                moveHero("Down", mission.getMap(), mission.getWidth());
                if (heroIsOnBorder("Down", mission.getMap(), mission.getWidth())) {
                    mission.setWin(true);
                }
            }
        }
        if (missionDTO.getSelectedAction().compareTo("Left") == 0) {
            if (dirIsMonster("Left", mission.getMap(), mission.getWidth())) {
                if (new Random().nextBoolean()) {
                    fight("Left", hero, mission.getMap(), mission.getWidth(), mission.getVillains());
                    if (hero.getHealth() > 0) {
                        moveHero("Left", mission.getMap(), mission.getWidth());
                        if (heroIsOnBorder("Left", mission.getMap(), mission.getWidth())) {
                            mission.setWin(true);
                        }
                    }
                    else
                        hero.setDeath(true);
                }
                else {
                    System.out.println("I can run, ha ha ha :)");
                    return ;
                }
            }
            else {
                moveHero("Left", mission.getMap(), mission.getWidth());
                if (heroIsOnBorder("Left", mission.getMap(), mission.getWidth())) {
                    mission.setWin(true);
                }
            }
        }
        if (missionDTO.getSelectedAction().compareTo("Right") == 0) {
            if (dirIsMonster("Right", mission.getMap(), mission.getWidth())) {
                if (new Random().nextBoolean()) {
                    fight("Right", hero, mission.getMap(), mission.getWidth(), mission.getVillains());
                    if (hero.getHealth() > 0) {
                        moveHero("Right", mission.getMap(), mission.getWidth());
                        if (heroIsOnBorder("Right", mission.getMap(), mission.getWidth())) {
                            mission.setWin(true);
                        }
                    }
                    else
                        hero.setDeath(true);
                }
                else {
                    System.out.println("I can run, ha ha ha :)");
                    return ;
                }
            }
            else {
                moveHero("Right", mission.getMap(), mission.getWidth());
                if (heroIsOnBorder("Right", mission.getMap(), mission.getWidth())) {
                    mission.setWin(true);
                }
            }
        }
    }

    private void fight(String dir, Hero hero, int[][] map, int width, List<Villain> villains) {

        if (dir.compareTo("Up") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2) {
                        System.out.println("q");
                        for (Villain v : villains) {
                            System.out.println("w");
                            if (v.getIy() == i - 1 && v.getJx() == j) {
                                System.out.println("e");
                                while (hero.getHealth() > 0 && v.getHealth() > 0) {
                                    v.receiveDamage(hero.getDamage());
                                    hero.receiveDamage(v.getDamage());
                                }
                                System.out.println("r");
                                if (hero.getHealth() < 0)
                                    hero.setDeath(true);
                                else
                                    System.out.println("todo kill monster part");
                                    //get artifact with random coefficient
                                    //get experience
                            }
                        }
                    }
                }
            }
        }
        else if (dir.compareTo("Down") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2) {
                        for (Villain v : villains) {
                            if (v.getIy() == i + 1 && v.getJx() == j){
                                while (hero.getHealth() > 0 && v.getHealth() > 0) {
                                    v.receiveDamage(hero.getDamage());
                                    hero.receiveDamage(v.getDamage());
                                }
                                if (hero.getHealth() < 0)
                                    hero.setDeath(true);
                                else
                                    System.out.println("todo kill monster part");
                                //get artifact with random coefficient
                                //get experience
                            }

                        }
                    }
                }
            }
        }
        else if (dir.compareTo("Left") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2) {
                        for (Villain v : villains) {
                            if (v.getIy() == i && v.getJx() == j - 1){
                                while (hero.getHealth() > 0 && v.getHealth() > 0) {
                                    v.receiveDamage(hero.getDamage());
                                    hero.receiveDamage(v.getDamage());
                                }
                                if (hero.getHealth() < 0)
                                    hero.setDeath(true);
                                else
                                    System.out.println("todo kill monster part");
                                //get artifact with random coefficient
                                //get experience
                            }
                        }
                    }
                }
            }
        }
        else if (dir.compareTo("Right") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2) {
                        for (Villain v : villains) {
                            if (v.getIy() == i && v.getJx() == j + 1){
                                while (hero.getHealth() > 0 && v.getHealth() > 0) {
                                    v.receiveDamage(hero.getDamage());
                                    hero.receiveDamage(v.getDamage());
                                }
                                if (hero.getHealth() < 0)
                                    hero.setDeath(true);
                                else
                                    System.out.println("todo kill monster part");
                                //get artifact with random coefficient
                                //get experience
                            }
                        }
                    }
                }
            }
        }




    }

    private void moveHero(String dir, int[][] map, int width) {
        if (dir.compareTo("Up") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2) {
                        if (i - 1 > 0){
                            map[i -1][j] = 2;
                            map[i][j] = 0;
                        }
                    }
                }
            }
        }
        else if (dir.compareTo("Down") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2) {
                        if (i + 1 < width){
                            map[i + 1][j] = 2;
                            map[i][j] = 0;
                        }
                    }
                }
            }
        }
        else if (dir.compareTo("Left") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2) {
                        if (j - 1 > 0){
                            map[i][j - 1] = 2;
                            map[i][j] = 0;
                        }
                    }
                }
            }
        }
        else if (dir.compareTo("Right") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2) {
                        if (j + 1 < width){
                            map[i][j + 1] = 2;
                            map[i][j] = 0;
                        }
                    }
                }
            }
        }
    }

    public boolean dirIsMonster(String dir, int[][] map, int width) {
        if (dir.compareTo("Up") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2) {
                        if (i - 1 > 0 && map[i - 1][j] == 1)
                            return true;
                    }
                }
            }
        }
        else if (dir.compareTo("Down") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2) {
                        if (i + 1 < width && map[i + 1][j] == 1)
                            return true;
                    }
                }
            }
        }
        else if (dir.compareTo("Left") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2) {
                        if (j - 1 > 0 && map[i][j - 1] == 1)
                            return true;
                    }
                }
            }
        }
        else if (dir.compareTo("Right") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2) {
                        if (j + 1 < width && map[i][j + 1] == 1)
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean heroIsOnBorder(String dir, int[][] map, int width) {
        if (dir.compareTo("Up") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2 && i == 0) {
                            return true;
                    }
                }
            }
        }
        else if (dir.compareTo("Down") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2 && i == width - 1) {
                        return true;
                    }
                }
            }
        }
        else if (dir.compareTo("Left") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2 && j == 0) {
                        return true;
                    }
                }
            }
        }
        else if (dir.compareTo("Right") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2 && j == width - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
