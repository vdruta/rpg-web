package ro.academyplus.dto;


import ro.academyplus.model.characters.Hero;
import ro.academyplus.model.characters.Villain;

import java.util.List;

/**
 * Created by MM on 2016-03-09.
 */
public class MissionDTO {
    public List<String> actions;
    public String selectedAction;

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
}
