/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pakket;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author witmoca
 */
@Entity
@Table(name = "URS_STUDENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UrsStudent.findAll", query = "SELECT u FROM UrsStudent u")
    , @NamedQuery(name = "UrsStudent.findByUserid", query = "SELECT u FROM UrsStudent u WHERE u.userid = :userid")
    , @NamedQuery(name = "UrsStudent.findByStatus", query = "SELECT u FROM UrsStudent u WHERE u.status = :status")
    , @NamedQuery(name = "UrsStudent.updateStatus", query = "UPDATE UrsStudent u SET u.status = :status WHERE u.userid = :userid")
    , @NamedQuery(name = "UrsStudent.updateStatusEindeKeuze", query = "UPDATE UrsStudent u SET u.status = 2")
    , @NamedQuery(name = "UrsStudent.setStudentKlas", query = "UPDATE UrsStudent u SET u.klasid = :klasid WHERE u.userid = :userid")
    , @NamedQuery(name = "UrsStudent.findStudentByKlas", query = "SELECT s FROM UrsStudent s WHERE s.klasid = :klasid")
    , @NamedQuery(name = "UrsStudent.findStudentZonderKlas", query = "SELECT s FROM UrsStudent s WHERE s.klasid IS NULL")
    })
public class UrsStudent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERID")
    private Integer userid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private int status;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private UrsGebruiker ursGebruiker;
    @JoinColumn(name = "KLASID", referencedColumnName = "KLASID")
    @ManyToOne
    private UrsKlas klasid;

    public UrsStudent() {
    }

    public UrsStudent(Integer userid) {
        this.userid = userid;
    }

    public UrsStudent(Integer userid, int status) {
        this.userid = userid;
        this.status = status;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UrsGebruiker getUrsGebruiker() {
        return ursGebruiker;
    }

    public void setUrsGebruiker(UrsGebruiker ursGebruiker) {
        this.ursGebruiker = ursGebruiker;
    }

    public UrsKlas getKlasid() {
        return klasid;
    }

    public void setKlasid(UrsKlas klasid) {
        this.klasid = klasid;
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
        if (!(object instanceof UrsStudent)) {
            return false;
        }
        UrsStudent other = (UrsStudent) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pakket.UrsStudent[ userid=" + userid + " ]";
    }
    
}
