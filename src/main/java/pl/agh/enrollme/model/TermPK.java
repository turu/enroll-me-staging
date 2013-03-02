package pl.agh.enrollme.model;

import javax.persistence.*;
import java.io.Serializable;

public class TermPK implements Serializable {

    @Transient
    private static final long serialVersionUID = -5771235478609230476L;

    private Subject subject;

    private Integer termPerSubjectID;

    public TermPK() {
    }

    public TermPK(Subject subject, Integer termPerSubjectID) {
        this.subject = subject;
        this.termPerSubjectID = termPerSubjectID;
    }

    public Subject getSubject() {
        return subject;
    }

    public Integer getTermPerSubjectID() {
        return termPerSubjectID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TermPK)) return false;

        TermPK termPK = (TermPK) o;

        if (subject != null ? !subject.equals(termPK.subject) : termPK.subject != null) return false;
        if (termPerSubjectID != null ? !termPerSubjectID.equals(termPK.termPerSubjectID) : termPK.termPerSubjectID != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = subject != null ? subject.hashCode() : 0;
        result = 31 * result + (termPerSubjectID != null ? termPerSubjectID.hashCode() : 0);
        return result;
    }
}
