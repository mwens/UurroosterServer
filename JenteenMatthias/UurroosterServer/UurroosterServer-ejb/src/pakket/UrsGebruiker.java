/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pakket;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "URS_GEBRUIKER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UrsGebruiker.findAll", query = "SELECT u FROM UrsGebruiker u")
    , @NamedQuery(name = "UrsGebruiker.findByUserid", query = "SELECT u FROM UrsGebruiker u WHERE u.userid = :userid")
    , @NamedQuery(name = "UrsGebruiker.findByNaam", query = "SELECT u FROM UrsGebruiker u WHERE u.naam = :naam")
    , @NamedQuery(name = "UrsGebruiker.findByWw", query = "SELECT u FROM UrsGebruiker u WHERE u.ww = :ww")
    , @NamedQuery(name = "UrsGebruiker.findByGroep", query = "SELECT u FROM UrsGebruiker u WHERE u.groep = :groep")})
public class UrsGebruiker implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERID")
    private Integer userid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "NAAM")
    private String naam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "WW")
    private String ww;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "GROEP")
    private String groep;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ursGebruiker")
    private Collection<UrsStudentrelatie> ursStudentrelatieCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ursGebruiker1")
    private Collection<UrsStudentrelatie> ursStudentrelatieCollection1;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "ursGebruiker")
    private UrsStudent ursStudent;

    public UrsGebruiker() {
    }

    public UrsGebruiker(Integer userid) {
        this.userid = userid;
    }

    public UrsGebruiker(Integer userid, String naam, String ww, String groep) {
        this.userid = userid;
        this.naam = naam;
        this.ww = ww;
        this.groep = groep;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getWw() {
        return ww;
    }

    public void setWw(String ww) {
        this.ww = ww;
    }

    public String getGroep() {
        return groep;
    }

    public void setGroep(String groep) {
        this.groep = groep;
    }

    @XmlTransient
    public Collection<UrsStudentrelatie> getUrsStudentrelatieCollection() {
        return ursStudentrelatieCollection;
    }

    public void setUrsStudentrelatieCollection(Collection<UrsStudentrelatie> ursStudentrelatieCollection) {
        this.ursStudentrelatieCollection = ursStudentrelatieCollection;
    }

    @XmlTransient
    public Collection<UrsStudentrelatie> getUrsStudentrelatieCollection1() {
        return ursStudentrelatieCollection1;
    }

    public void setUrsStudentrelatieCollection1(Collection<UrsStudentrelatie> ursStudentrelatieCollection1) {
        this.ursStudentrelatieCollection1 = ursStudentrelatieCollection1;
    }

    public UrsStudent getUrsStudent() {
        return ursStudent;
    }

    public void setUrsStudent(UrsStudent ursStudent) {
        this.ursStudent = ursStudent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UrsGebruiker)) {
            return false;
        }
        UrsGebruiker other = (UrsGebruiker) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pakket.UrsGebruiker[ userid=" + userid + " ]";
    }
    
}
