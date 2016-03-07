package ro.academyplus.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by MM on 2016-03-07.
 */
public class HeroDTO {
    @NotNull
    @Size(min = 3, max = 40, message = "Hero name must be between 3 and 40 characters")
    public String name;
    public String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
