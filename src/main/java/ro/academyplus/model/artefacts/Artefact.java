package ro.academyplus.model.artefacts;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by MM on 2016-02-25.
 */
@Entity
@Table(name = "ARTEFACT")
public class Artefact implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    protected String name;
    protected ArtefactType artefactType;

    protected void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public ArtefactType getArtefactType() {
        return artefactType;
    }

    public void setArtefactType(ArtefactType artefactType) {
        this.artefactType = artefactType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (obj instanceof Artefact) {
            Artefact anotherArtefact = (Artefact) obj;
            if (this.getArtefactType() == anotherArtefact.getArtefactType())
                return true;
        }
        return false;
    }
}
