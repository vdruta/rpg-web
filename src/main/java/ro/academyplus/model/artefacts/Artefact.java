package ro.academyplus.model.artefacts;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by MM on 2016-02-25.
 */
@Entity
@Table(name = "ARTEFACT")
public abstract class Artefact implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    protected void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
