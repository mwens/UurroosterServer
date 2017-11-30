/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pakket;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author witmoca
 */
@Entity
@Table(name = "URS_STUDENTRELATIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UrsStudentrelatie.findAll", query = "SELECT u FROM UrsStudentrelatie u")
    , @NamedQuery(name = "UrsStudentrelatie.findByStudent", query = "SELECT u FROM UrsStudentrelatie u WHERE u.ursStudentrelatiePK.student = :student")
    , @NamedQuery(name = "UrsStudentrelatie.findByCollega", query = "SELECT u FROM UrsStudentrelatie u WHERE u.ursStudentrelatiePK.collega = :collega")
    , @NamedQuery(name = "UrsStudentrelatie.findByRelatie", query = "SELECT u FROM UrsStudentrelatie u WHERE u.relatie = :relatie")
    , @NamedQuery(name = "UrsStudentrelatie.findByPk", query = "SELECT u FROM UrsStudentrelatie u WHERE u.ursStudentrelatiePK.student = :student AND u.ursStudentrelatiePK.collega = :collega")
    , @NamedQuery(name = "UrsStudentrelatie.deleteByPk", query = "DELETE FROM UrsStudentrelatie u WHERE u.ursStudentrelatiePK.student = :student AND u.ursStudentrelatiePK.collega = :collega")
    , @NamedQuery(name = "UrsStudentrelatie.updateRelatie", query = "UPDATE UrsStudentrelatie u SET u.relatie = :relatie WHERE u.ursStudentrelatiePK.student = :student AND u.ursStudentrelatiePK.collega = :collega")})
public class UrsStudentrelatie implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UrsStudentrelatiePK ursStudentrelatiePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RELATIE")
    private int relatie;
    @JoinColumn(name = "COLLEGA", referencedColumnName = "USERID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UrsGebruiker ursGebruiker;
    @JoinColumn(name = "STUDENT", referencedColumnName = "USERID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UrsGebruiker ursGebruiker1;

    public UrsStudentrelatie() {
    }

    public UrsStudentrelatie(UrsStudentrelatiePK ursStudentrelatiePK) {
        this.ursStudentrelatiePK = ursStudentrelatiePK;
    }

    public UrsStudentrelatie(UrsStudentrelatiePK ursStudentrelatiePK, int relatie) {
        this.ursStudentrelatiePK = ursStudentrelatiePK;
        this.relatie = relatie;
    }
    
    public UrsStudentrelatie(int student, int collega, int relatie) {
        this.ursStudentrelatiePK = new UrsStudentrelatiePK(student, collega);
        this.relatie = relatie;
    }

    public UrsStudentrelatie(int student, int collega) {
        this.ursStudentrelatiePK = new UrsStudentrelatiePK(student, collega);
    }

    public UrsStudentrelatiePK getUrsStudentrelatiePK() {
        return ursStudentrelatiePK;
    }

    public void setUrsStudentrelatiePK(UrsStudentrelatiePK ursStudentrelatiePK) {
        this.ursStudentrelatiePK = ursStudentrelatiePK;
    }

    public int getRelatie() {
        return relatie;
    }

    public void setRelatie(int relatie) {
        this.relatie = relatie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ursStudentrelatiePK != null ? ursStudentrelatiePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UrsStudentrelatie)) {
            return false;
        }
        UrsStudentrelatie other = (UrsStudentrelatie) object;
        if ((this.ursStudentrelatiePK == null && other.ursStudentrelatiePK != null) || (this.ursStudentrelatiePK != null && !this.ursStudentrelatiePK.equals(other.ursStudentrelatiePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pakket.UrsStudentrelatie[ ursStudentrelatiePK=" + ursStudentrelatiePK + " ]";
    }
    
}
