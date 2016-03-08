package ro.academyplus.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

/**
 * Created by MM on 2016-03-07.
 */
public class HeroDTO {
    @NotNull
    @Size(min = 3, max = 40, message = "Hero name must be between 3 and 40 characters")
    public String name;
    public List<String> htypes;
    public String selectedType;
    public long tmpid;


    public long getTmpid() {
        return tmpid;
    }

    public void setTmpid(long tmpid) {
        this.tmpid = tmpid;
    }

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }

    public List<String> getHtypes() {
        return htypes;
    }

    public void setHtypes(List<String> htypes) {
        this.htypes = htypes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
