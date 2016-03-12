package ro.academyplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.academyplus.dto.MissionDTO;
import ro.academyplus.model.Mission;
import ro.academyplus.model.characters.DarkMage;
import ro.academyplus.model.characters.Devil;
import ro.academyplus.model.characters.Hero;
import ro.academyplus.model.characters.Villain;
import ro.academyplus.repository.HeroRepository;

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
    @Autowired
    HeroRepository heroRepository;

    public void playMission(MissionDTO missionDTO) {
        Mission mission = (Mission) request.getSession().getAttribute("mission");
        if (mission.isMonster())
            updateMission(missionDTO);
        else
            processAction(missionDTO);
    }
    public void updateMission(MissionDTO missionDTO) {
        Mission mission = (Mission) request.getSession().getAttribute("mission");
        Hero hero = (Hero) request.getSession().getAttribute("hero");

        if (missionDTO.getSelectedFightAction().compareTo("Run") == 0){
            if (new Random().nextBoolean()) {
                fight(mission.getMonsterLocation(), hero, mission.getMap(), mission.getWidth(), mission.getVillains());
                if (hero.getHealth() > 0) {
                    mission.setMonster(false);
                    moveHero(mission.getMonsterLocation(), mission.getMap(), mission.getWidth());
                    if (heroIsOnBorder(mission.getMonsterLocation(), mission.getMap(), mission.getWidth())) {
                        mission.setWin(true);
                        giveHeroExperience(hero, 30);
                        heroRepository.saveAndFlush(hero);
                        return ;
                    }
                }
                else {
                    hero.setDeath(true);
                }
            }
            else {
                mission.setMonster(false);
                return;
            }
        }
        else if (missionDTO.getSelectedFightAction().compareTo("Fight") == 0) {
            fight(mission.getMonsterLocation(), hero, mission.getMap(), mission.getWidth(), mission.getVillains());
            if (hero.getHealth() > 0) {
                mission.setMonster(false);
                moveHero(mission.getMonsterLocation(), mission.getMap(), mission.getWidth());
                if (heroIsOnBorder(mission.getMonsterLocation(), mission.getMap(), mission.getWidth())) {
                    mission.setWin(true);
                    giveHeroExperience(hero, 30);
                    heroRepository.saveAndFlush(hero);
                    return ;
                }
            }
            else {
                hero.setDeath(true);
            }
        }
        /*
        if (missionDTO.getSelectedAction().compareTo("Up") == 0) {
                if (dirIsMonster("Up", mission.getMap(), mission.getWidth())) {
                    if (new Random().nextBoolean()) {
                        fight("Up", hero, mission.getMap(), mission.getWidth(), mission.getVillains());
                        if (hero.getHealth() > 0) {
                            moveHero("Up", mission.getMap(), mission.getWidth());
                            if (heroIsOnBorder("Up", mission.getMap(), mission.getWidth())) {
                                mission.setWin(true);
                                giveHeroExperience(hero, 30);
                                heroRepository.saveAndFlush(hero);
                                return ;
                            }
                        }
                        else {
                            hero.setDeath(true);
                        }
                    }
                    else {
                        System.out.println("I can run, ha ha ha :)");
                        return ;
                    }
                    printMap(mission.getMap(), mission.getWidth());
                }
                else {
                    moveHero("Up", mission.getMap(), mission.getWidth());
                    if (heroIsOnBorder("Up", mission.getMap(), mission.getWidth())) {
                        mission.setWin(true);
                        giveHeroExperience(hero, 30);
                        heroRepository.saveAndFlush(hero);
                        return ;
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
                            giveHeroExperience(hero, 30);
                            heroRepository.saveAndFlush(hero);
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
                    giveHeroExperience(hero, 30);
                    heroRepository.saveAndFlush(hero);
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
                            giveHeroExperience(hero, 30);
                            heroRepository.saveAndFlush(hero);
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
                    giveHeroExperience(hero, 30);
                    heroRepository.saveAndFlush(hero);
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
                            giveHeroExperience(hero, 30);
                            heroRepository.saveAndFlush(hero);
                        }
                    }
                    else
                        hero.setDeath(true);
                }
                else {
                    System.out.println("I can run, ha ha ha :)");
                    return;
                }
            }
            else {
                moveHero("Right", mission.getMap(), mission.getWidth());
                if (heroIsOnBorder("Right", mission.getMap(), mission.getWidth())) {
                    mission.setWin(true);
                    giveHeroExperience(hero, 30);
                    heroRepository.saveAndFlush(hero);
                }
            }
        }*/
    }

    public void processAction(MissionDTO missionDTO) {
        Mission mission = (Mission) request.getSession().getAttribute("mission");
        Hero hero = (Hero) request.getSession().getAttribute("hero");
        if (missionDTO.getSelectedAction().compareTo("Up") == 0) {
            if (dirIsMonster("Up", mission.getMap(), mission.getWidth())) {
                mission.setMonster(true);
                mission.setMonsterLocation(missionDTO.getSelectedAction());
            }
            else {
                moveHero("Up", mission.getMap(), mission.getWidth());
                if (heroIsOnBorder("Up", mission.getMap(), mission.getWidth())) {
                    mission.setWin(true);
                    giveHeroExperience(hero, 30);
                    heroRepository.saveAndFlush(hero);
                    return;
                }
            }

        }
        if (missionDTO.getSelectedAction().compareTo("Down") == 0) {
            if (dirIsMonster("Down", mission.getMap(), mission.getWidth())) {
                mission.setMonster(true);
                mission.setMonsterLocation(missionDTO.getSelectedAction());
            }
            else {
                moveHero("Down", mission.getMap(), mission.getWidth());
                if (heroIsOnBorder("Down", mission.getMap(), mission.getWidth())) {
                    mission.setWin(true);
                    giveHeroExperience(hero, 30);
                    heroRepository.saveAndFlush(hero);
                    return;
                }
            }

        }
        if (missionDTO.getSelectedAction().compareTo("Left") == 0) {
            if (dirIsMonster("Left", mission.getMap(), mission.getWidth())) {
                mission.setMonster(true);
                mission.setMonsterLocation(missionDTO.getSelectedAction());
            }
            else {
                moveHero("Left", mission.getMap(), mission.getWidth());
                if (heroIsOnBorder("Left", mission.getMap(), mission.getWidth())) {
                    mission.setWin(true);
                    giveHeroExperience(hero, 30);
                    heroRepository.saveAndFlush(hero);
                    return;
                }
            }

        }
        if (missionDTO.getSelectedAction().compareTo("Right") == 0) {
            if (dirIsMonster("Right", mission.getMap(), mission.getWidth())) {
                mission.setMonster(true);
                mission.setMonsterLocation(missionDTO.getSelectedAction());
            }
            else {
                moveHero("Right", mission.getMap(), mission.getWidth());
                if (heroIsOnBorder("Right", mission.getMap(), mission.getWidth())) {
                    mission.setWin(true);
                    giveHeroExperience(hero, 30);
                    heroRepository.saveAndFlush(hero);
                    return;
                }
            }

        }
    }

    public void printMap(int[][] map, int width) {
        for (int i = 0; i<width; i++) {
            for (int j = 0; j<width; j++) {
                System.out.print(map[i][j] +" ");
            }
            System.out.println();
        }
    }

    public void giveHeroExperience(Hero hero, int value) {
        if (hero.getExperience() < 100)
            hero.setExperience(hero.getExperience() + value);
        if (hero.getExperience() >= 100) {
            hero.setExperience(hero.getExperience() - 100);
            hero.levelUp();
        }
    }

    private void fight(String dir, Hero hero, int[][] map, int width, List<Villain> villains) {
        if (dir.compareTo("Up") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2) {
                        for (Villain v : villains) {
                            if (v.getIy() == i - 1 && v.getJx() == j) {
                                while (hero.getHealth() > 0 && v.getHealth() > 0) {
                                    v.receiveDamage(hero.getDamage());
                                    hero.receiveDamage(v.getDamage());
                                }
                                if (hero.getHealth() <= 0) {
                                    hero.setDeath(true);
                                    hero.setHealth(0);
                                }
                                else {//TODO :You get artifacts when you kill a monster with random coefficient. When you find artifact:keep or drop. Only one artifact type can be kept.
                                    giveHeroExperience(hero, 10);
                                }
                            }
                        }
                    }
                }
            }
        }
        else if (dir.compareTo("Down") == 0) {
            System.out.println(0);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2) {
                        for (Villain v : villains) {
                            if (v.getIy() == i + 1 && v.getJx() == j) {
                                while (hero.getHealth() > 0 && v.getHealth() > 0) {
                                    v.receiveDamage(hero.getDamage());
                                    hero.receiveDamage(v.getDamage());
                                }
                                if (hero.getHealth() <= 0) {
                                    hero.setDeath(true);
                                    hero.setHealth(0);
                                }
                                else {
                                    giveHeroExperience(hero, 10);
                                }
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
                                else {
                                    giveHeroExperience(hero, 10);
                                }
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
                                else {
                                    giveHeroExperience(hero, 10);
                                }
                            }
                        }
                    }
                }
            }
        }




    }

    private void moveHero(String dir, int[][] map, int width) {
        if (dir.compareTo("Up") == 0) {
            System.out.println("move up");
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2) {
                        if (i - 1 >= 0){
                            map[i -1][j] = 2;
                            map[i][j] = 0;
                            return;
                        }
                    }
                }
            }
        }
        else if (dir.compareTo("Down") == 0) {
            System.out.println(1);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2) {
                        System.out.println(2);
                        if (i + 1 <= width){
                            System.out.println(3);
                            map[i + 1][j] = 2;
                            map[i][j] = 0;
                            return;
                        }
                    }
                }
            }
        }
        else if (dir.compareTo("Left") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2) {
                        if (j - 1 >= 0){
                            map[i][j - 1] = 2;
                            map[i][j] = 0;
                            return;
                        }
                    }
                }
            }
        }
        else if (dir.compareTo("Right") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2) {
                        if (j + 1 <= width){
                            map[i][j + 1] = 2;
                            map[i][j] = 0;
                            return;
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
                    if (map[i][j] == 2) {
                        if (i == 0) {
                            return true;
                        }
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
