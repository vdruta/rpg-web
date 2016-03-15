package ro.academyplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.academyplus.dto.MissionDTO;
import ro.academyplus.model.Mission;
import ro.academyplus.model.artefacts.*;
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
        if (!mission.isNewArtefact()) {
            mission.setInventoryAlreadyContainsArtefact(false);
            if (mission.isMonster())
                updateMission(missionDTO);
            else
                processAction(missionDTO);
        }
        else {
            keepOrDropArtefact(missionDTO);
        }
    }

    public void updateMission(MissionDTO missionDTO) {
        Mission mission = (Mission) request.getSession().getAttribute("mission");
        Hero hero = (Hero) request.getSession().getAttribute("hero");

        if (missionDTO.getSelectedFightAction().compareTo("Run") == 0){
            if (new Random().nextBoolean()) {
                fight(mission.getMonsterLocation(), hero, mission.getMap(), mission.getWidth(), mission.getVillains());
                if (hero.getHealth() > 0) {
                    mission.setMonster(false);
                    moveHero(mission.getMonsterLocation(), mission.getMap(), mission.getWidth(), mission.isMonsterOnLastLocation());
                    mission.setMonsterOnLastLocation(true);
                    if (heroIsOnBorder(mission.getMap(), mission.getWidth())) {
                        mission.setNewArtefact(true);
                    }
                }
                else {
                    hero.setDeath(true);
                }
            }
            else {
                mission.setMonster(false);
            }
        }
        else if (missionDTO.getSelectedFightAction().compareTo("Fight") == 0) {
            fight(mission.getMonsterLocation(), hero, mission.getMap(), mission.getWidth(), mission.getVillains());
            if (hero.getHealth() > 0) {
                mission.setMonster(false);
                moveHero(mission.getMonsterLocation(), mission.getMap(), mission.getWidth(), mission.isMonsterOnLastLocation());
                mission.setMonsterOnLastLocation(true);
                if (heroIsOnBorder(mission.getMap(), mission.getWidth())) {
                    mission.setNewArtefact(true);
                }
            }
            else {
                hero.setDeath(true);
            }
        }
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
                moveHero("Up", mission.getMap(), mission.getWidth(), mission.isMonsterOnLastLocation());
                if (heroIsOnBorder(mission.getMap(), mission.getWidth())) {
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
                moveHero("Down", mission.getMap(), mission.getWidth(), mission.isMonsterOnLastLocation());
                if (heroIsOnBorder(mission.getMap(), mission.getWidth())) {
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
                moveHero("Left", mission.getMap(), mission.getWidth(), mission.isMonsterOnLastLocation());
                if (heroIsOnBorder(mission.getMap(), mission.getWidth())) {
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
                moveHero("Right", mission.getMap(), mission.getWidth(), mission.isMonsterOnLastLocation());
                if (heroIsOnBorder(mission.getMap(), mission.getWidth())) {
                    mission.setWin(true);
                    giveHeroExperience(hero, 30);
                    heroRepository.saveAndFlush(hero);
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

    private void keepOrDropArtefact(MissionDTO missionDTO) {
        Mission mission = (Mission) request.getSession().getAttribute("mission");
        Hero hero = (Hero) request.getSession().getAttribute("hero");

        if (missionDTO.getSelectedgetOrDropAction().compareTo("Drop") == 0) {
            mission.setNewArtefact(false);
            if (heroIsOnBorder(mission.getMap(), mission.getWidth())) {
                mission.setWin(true);
                giveHeroExperience(hero, 30);
                heroRepository.saveAndFlush(hero);
            }
        }
        else if (missionDTO.getSelectedgetOrDropAction().compareTo("Keep") == 0) {
            mission.setNewArtefact(false);
            try {
                hero.addArtefact(mission.getLatestArtefact());
            } catch (Exception e) { // TODO: 2016-03-13 Message to player: You already have one artefact type: sword, axe, bow, etc
                mission.setInventoryAlreadyContainsArtefact(true);
            }
            if (heroIsOnBorder(mission.getMap(), mission.getWidth())) {
                mission.setWin(true);
                giveHeroExperience(hero, 30); //TODO reset hero to base level
                heroRepository.saveAndFlush(hero);
            }
        }
    }

    private void fight(String dir, Hero hero, int[][] map, int width, List<Villain> villains) {
        Mission mission = (Mission) request.getSession().getAttribute("mission");

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
                                if (hero.getHealth() <= 0) { //TODO :Reset hero? FIXED: reset experience to what it was before
                                    hero.setDeath(true);
                                    hero.setHealth(0);
                                    hero.setExperience(heroRepository.findOne(hero.getId()).getExperience());
                                }
                                else {//TODO :You get artifacts when you kill a monster with random coefficient. When you find artifact:keep or drop. Only one artifact type can be kept.
                                    giveHeroExperience(hero, 10);
                                    mission.setNewArtefact(true);
                                    mission.setLatestArtefact(getNewRandomArtefact(mission.getWidth()));
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
                                    hero.setExperience(heroRepository.findOne(hero.getId()).getExperience());
                                }
                                else {
                                    giveHeroExperience(hero, 10);
                                    mission.setNewArtefact(true);
                                    mission.setLatestArtefact(getNewRandomArtefact(mission.getWidth()));
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
                                if (hero.getHealth() < 0) {
                                    hero.setDeath(true);
                                    hero.setHealth(0);
                                    hero.setExperience(heroRepository.findOne(hero.getId()).getExperience());
                                }
                                else {
                                    giveHeroExperience(hero, 10);
                                    mission.setNewArtefact(true);
                                    mission.setLatestArtefact(getNewRandomArtefact(mission.getWidth()));
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
                                if (hero.getHealth() < 0) {
                                    hero.setDeath(true);
                                    hero.setHealth(0);
                                    hero.setExperience(heroRepository.findOne(hero.getId()).getExperience());
                                }
                                else {
                                    giveHeroExperience(hero, 10);
                                    mission.setNewArtefact(true);
                                    mission.setLatestArtefact(getNewRandomArtefact(mission.getWidth()));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private Artefact getNewRandomArtefact(int mapWidth) {
        int i = Mission.randInt(mapWidth - 5, mapWidth);

        if (i % 6 == 0)
            return new Armor("Armor"+i, i * 20);
        else if (i % 6 == 1)
            return new Axe("Axe"+i, i);
        else if (i % 6 == 2)
            return new Bow("Bow"+i, i);
        else if (i % 6 == 3)
            return new Helmet("Helmet"+i, i * 15);
        else if (i % 6 == 4)
            return new Staff("Staff"+i, i);
        else if (i % 6 == 5)
            return new Sword("Sword"+i, i);
        return null;
    }

    private void moveHero(String dir, int[][] map, int width, boolean monsterOnLastLocation) {
        Mission mission = (Mission) request.getSession().getAttribute("mission");

        if (dir.compareTo("Up") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2 || map[i][j] == 5) {
                        if (i - 1 >= 0){
                            if (map[i][j] == 2) {
                                if (map[i - 1][j] == 3)
                                    map[i - 1][j] = 5;
                                else
                                    map[i - 1][j] = 2;
                            }
                            else if (map[i][j] == 5) {
                                map[i - 1][j] = 2;
                                map[i][j] = 3;
                            }
                            if (monsterOnLastLocation) {
                                map[i][j] = 3;
                                mission.setMonsterOnLastLocation(false);
                            }
                            else {
                                if (map[i][j] != 3 && map[i][j] != 5)
                                    map[i][j] = 4;
                            }
                            return;
                        }
                    }
                }
            }
        }
        else if (dir.compareTo("Down") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2 || map[i][j] == 5) {
                        if (i + 1 <= width){
                            if (map[i][j] == 2) {
                                if (map[i + 1][j] == 3)
                                    map[i + 1][j] = 5;
                                else
                                    map[i + 1][j] = 2;
                            }
                            else if (map[i][j] == 5) {
                                map[i + 1][j] = 2;
                                map[i][j] = 3;
                            }
                            if (monsterOnLastLocation) {
                                map[i][j] = 3;
                                mission.setMonsterOnLastLocation(false);
                            }
                            else {
                                if (map[i][j] != 3 && map[i][j] != 5)
                                    map[i][j] = 4;
                            }
                            return;
                        }
                    }
                }
            }
        }
        else if (dir.compareTo("Left") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2 || map[i][j] == 5) {
                        if (j - 1 >= 0){
                            if (map[i][j] == 2) {
                                if (map[i][j - 1] == 3)
                                    map[i][j -1] = 5;
                                else
                                    map[i][j - 1] = 2;
                            }
                            else if (map[i][j] == 5) {
                                map[i][j - 1] = 2;
                                map[i][j] = 3;
                            }
                            if (monsterOnLastLocation) {
                                map[i][j] = 3;
                                mission.setMonsterOnLastLocation(false);
                            }
                            else {
                                if (map[i][j] != 3 && map[i][j] != 5)
                                    map[i][j] = 4;
                            }
                            return;
                        }
                    }
                }
            }
        }
        else if (dir.compareTo("Right") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2 || map[i][j] == 5) {
                        if (j + 1 <= width){
                            if (map[i][j] == 2) {
                                if (map[i][j + 1] == 3)
                                    map[i][j + 1] = 5;
                                else
                                    map[i][j + 1] = 2;
                            }
                            else if (map[i][j] == 5) {
                                map[i][j + 1] = 2;
                                map[i][j] = 3;
                            }
                            if (monsterOnLastLocation) {
                                map[i][j] = 3;
                                mission.setMonsterOnLastLocation(false);
                            }
                            else {
                                if (map[i][j] != 3 && map[i][j] != 5)
                                    map[i][j] = 4;
                            }
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
                        if (i - 1 >= 0 && map[i - 1][j] == 1)
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
                        if (j - 1 >= 0 && map[i][j - 1] == 1)
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

    public boolean heroIsOnBorder(int[][] map, int width) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == 2) {
                        if (i == 0) {
                            return true;
                        }
                        else if (i == width - 1) {
                            return true;
                        }
                        else if (j == 0) {
                            return true;
                        }
                        else if (j == width - 1) {
                            return true;
                        }
                    }
                }
            }
        return false;
    }
}
