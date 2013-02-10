package pl.agh.enrollme.utils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Michal Partyka
 */
@Entity
public class StupidDate implements Serializable {

    @Id
    private Integer stupidDateID;
    private String string;

    public static StupidDate valueOf(String time) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public void setID(Integer ID) {
        this.stupidDateID = ID;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Integer getID() {

        return stupidDateID;
    }

    public String getString() {
        return string;
    }

    public Integer mapIntoInteger() {
        return stupidDateID;
    }

    public String mapIntoString() {
        return string;
    }
}
