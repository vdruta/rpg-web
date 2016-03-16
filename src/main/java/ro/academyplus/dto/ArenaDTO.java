package ro.academyplus.dto;

import java.util.List;

/**
 * Created by MM on 2016-03-16.
 */
public class ArenaDTO {
    public List<String> actions;
    public String selectedAction;
    public List<String> actions2;
    public String selectedAction2;

    public List<String> getActions2() {
        return actions2;
    }

    public void setActions2(List<String> actions2) {
        this.actions2 = actions2;
    }

    public String getSelectedAction2() {
        return selectedAction2;
    }

    public void setSelectedAction2(String selectedAction2) {
        this.selectedAction2 = selectedAction2;
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
}
