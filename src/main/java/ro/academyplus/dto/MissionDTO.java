package ro.academyplus.dto;

import java.util.List;

/**
 * Created by MM on 2016-03-09.
 */
public class MissionDTO {
    public List<String> actions;
    public String selectedAction;
    public List<String> fightActions;
    public String selectedFightAction;
    public List<String> getOrDropActions;
    public String selectedgetOrDropAction;

    public List<String> getGetOrDropActions() {
        return getOrDropActions;
    }

    public void setGetOrDropActions(List<String> getOrDropActions) {
        this.getOrDropActions = getOrDropActions;
    }

    public String getSelectedgetOrDropAction() {
        return selectedgetOrDropAction;
    }

    public void setSelectedgetOrDropAction(String selectedgetOrDropAction) {
        this.selectedgetOrDropAction = selectedgetOrDropAction;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public String getSelectedAction() {
        return selectedAction;
    }

    public void setSelectedAction(String selectedAction) {
        this.selectedAction = selectedAction;
    }

    public List<String> getFightActions() {
        return fightActions;
    }

    public void setFightActions(List<String> fightActions) {
        this.fightActions = fightActions;
    }

    public String getSelectedFightAction() {
        return selectedFightAction;
    }

    public void setSelectedFightAction(String selectedFightAction) {
        this.selectedFightAction = selectedFightAction;
    }

}
