package pl.agh.enrollme.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Michal Partyka
 */
@Embeddable
public class TermPK implements Serializable {

    @Transient
    private static final long serialVersionUID = -5771235478609230476L;

//    @Column
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Subject subject;

    @Column
    private Integer subject;

    @Column
    private Integer TermPerSubjectID;

    public TermPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TermPK)) return false;

        TermPK termPK = (TermPK) o;

        if (!TermPerSubjectID.equals(termPK.TermPerSubjectID)) {
            return false;
        }
        if (!subject.equals(termPK.subject)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = subject.hashCode();
        result = 31 * result + TermPerSubjectID.hashCode();
        return result;
    }

    public Integer getSubject() {
        return subject;
    }

    public Integer getTermPerSubjectID() {
        return TermPerSubjectID;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public void setTermPerSubjectID(Integer termPerSubjectID) {
        TermPerSubjectID = termPerSubjectID;
    }
}
