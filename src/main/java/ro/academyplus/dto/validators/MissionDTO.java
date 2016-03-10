package ro.academyplus.dto.validators;

import ro.academyplus.model.characters.Hero;
import ro.academyplus.model.characters.Villain;

import java.util.List;

/**
 * Created by MM on 2016-03-09.
 */
public class MissionDTO {
    public List<String> actions;
    public String selectedAction;
    public Hero hero;
    public int[][] map;
    public List<Villain> villains;

    public List<Villain> getVillains() {
        return villains;
    }

    public void setVillains(List<Villain> villains) {
        this.villains = villains;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public void setSelectedAction(String selectedAction) {
        this.selectedAction = selectedAction;
    }

    public String getSelectedAction() {
        return selectedAction;
    }
}
