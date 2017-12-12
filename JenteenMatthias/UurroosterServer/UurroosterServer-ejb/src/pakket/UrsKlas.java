/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pakket;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author witmoca
 */
@Entity
@Table(name = "URS_KLAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UrsKlas.findAll", query = "SELECT u FROM UrsKlas u")
    , @NamedQuery(name = "UrsKlas.findByKlasid", query = "SELECT u FROM UrsKlas u WHERE u.klasid = :klasid")
    , @NamedQuery(name = "UrsKlas.findByNaam", query = "SELECT u FROM UrsKlas u WHERE u.naam = :naam")
    , @NamedQuery(name = "UrsKlas.findByStatus", query = "SELECT u FROM UrsKlas u WHERE u.status = :status")
    , @NamedQuery(name = "UrsKlas.removeByKlasid", query = "DELETE FROM UrsKlas u WHERE u.klasid = :klasid")
    , @NamedQuery(name = "UrsKlas.findKlasid", query = "SELECT MAX(u.klasid) FROM UrsKlas u")})
public class UrsKlas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "KLASID")
    private Integer klasid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "NAAM")
    private String naam;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private int status;
    @OneToMany(mappedBy = "klasid")
    private Collection<UrsStudent> ursStudentCollection;

    public UrsKlas() {
    }

    public UrsKlas(Integer klasid) {
        this.klasid = klasid;
    }

    public UrsKlas(Integer klasid, String naam, int status) {
        this.klasid = klasid;
        this.naam = naam;
        this.status = status;
    }

    public Integer getKlasid() {
        return klasid;
    }

    public void setKlasid(Integer klasid) {
        this.klasid = klasid;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<UrsStudent> getUrsStudentCollection() {
        return ursStudentCollection;
    }

    public void setUrsStudentCollection(Collection<UrsStudent> ursStudentCollection) {
        this.ursStudentCollection = ursStudentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (klasid != null ? klasid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UrsKlas)) {
            return false;
        }
        UrsKlas other = (UrsKlas) object;
        if ((this.klasid == null && other.klasid != null) || (this.klasid != null && !this.klasid.equals(other.klasid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pakket.UrsKlas[ klasid=" + klasid + " ]";
    }
    
}
