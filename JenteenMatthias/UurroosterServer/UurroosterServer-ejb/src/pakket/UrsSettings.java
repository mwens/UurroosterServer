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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matthias Wens
 */
@Entity
@Table(name = "URS_SETTINGS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UrsSettings.findAll", query = "SELECT u FROM UrsSettings u")
    , @NamedQuery(name = "UrsSettings.findBySettingsid", query = "SELECT u FROM UrsSettings u WHERE u.settingsid = :settingsid")
    , @NamedQuery(name = "UrsSettings.findByNaam", query = "SELECT u FROM UrsSettings u WHERE u.naam = :naam")
    , @NamedQuery(name = "UrsSettings.setOmschrijving", query = "UPDATE UrsSettings u SET u.omschrijving = :omschrijving WHERE u.naam = :naam")
    , @NamedQuery(name = "UrsSettings.findByOmschrijving", query = "SELECT u FROM UrsSettings u WHERE u.omschrijving = :omschrijving")})
public class UrsSettings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SETTINGSID")
    private Integer settingsid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "NAAM")
    private String naam;
    @Size(max = 400)
    @Column(name = "OMSCHRIJVING")
    private String omschrijving;

    public UrsSettings() {
    }

    public UrsSettings(Integer settingsid) {
        this.settingsid = settingsid;
    }

    public UrsSettings(Integer settingsid, String naam) {
        this.settingsid = settingsid;
        this.naam = naam;
    }

    public Integer getSettingsid() {
        return settingsid;
    }

    public void setSettingsid(Integer settingsid) {
        this.settingsid = settingsid;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (settingsid != null ? settingsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UrsSettings)) {
            return false;
        }
        UrsSettings other = (UrsSettings) object;
        if ((this.settingsid == null && other.settingsid != null) || (this.settingsid != null && !this.settingsid.equals(other.settingsid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pakket.UrsSettings[ settingsid=" + settingsid + " ]";
    }
    
}
