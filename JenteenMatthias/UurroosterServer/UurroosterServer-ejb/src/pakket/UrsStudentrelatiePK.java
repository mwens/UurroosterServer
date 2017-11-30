/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pakket;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author witmoca
 */
@Embeddable
public class UrsStudentrelatiePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "STUDENT")
    private int student;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COLLEGA")
    private int collega;

    public UrsStudentrelatiePK() {
    }

    public UrsStudentrelatiePK(int student, int collega) {
        this.student = student;
        this.collega = collega;
    }

    public int getStudent() {
        return student;
    }

    public void setStudent(int student) {
        this.student = student;
    }

    public int getCollega() {
        return collega;
    }

    public void setCollega(int collega) {
        this.collega = collega;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) student;
        hash += (int) collega;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UrsStudentrelatiePK)) {
            return false;
        }
        UrsStudentrelatiePK other = (UrsStudentrelatiePK) object;
        if (this.student != other.student) {
            return false;
        }
        if (this.collega != other.collega) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pakket.UrsStudentrelatiePK[ student=" + student + ", collega=" + collega + " ]";
    }
    
}
