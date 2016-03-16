package ro.academyplus.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by agheboianu on 04.03.2016.
 */
public class ArenaJoinDTO {

    @NotNull
    @Size(min = 1, max = 40, message = "Join id min 1 character")
    private String joinId;

    public String getJoinId() {
        return joinId;
    }

    public void setJoinId(String joinId) {
        this.joinId = joinId;
    }
}