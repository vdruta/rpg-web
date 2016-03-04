package ro.academyplus.model.artefacts;

import java.io.Serializable;

/**
 * Created by MM on 2016-02-25.
 */
public abstract class Artefact implements Serializable{
    private String name;

    protected void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
