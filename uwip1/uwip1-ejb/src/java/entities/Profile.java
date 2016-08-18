/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Seanjackson
 */
@Entity
@Table(name = "profile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p"),
    @NamedQuery(name = "Profile.findAllPrivilege", query = "SELECT p FROM Privilege p"),
    @NamedQuery(name = "Profile.findByIdprofile", query = "SELECT p FROM Profile p WHERE p.idprofile = :idprofile"),
    @NamedQuery(name = "Profile.findByName", query = "SELECT p FROM Profile p WHERE p.name = :name"),
    @NamedQuery(name = "Profile.findAll2", query = "SELECT p FROM Profile p WHERE p.name != :name"),
    @NamedQuery(name = "Profile.findByKey", query = "SELECT p FROM Privilege p WHERE p.key = :key"),
    @NamedQuery(name = "Profile.findByConfigprivilege", query = "SELECT p FROM Profile p WHERE p.configprivilege = :configprivilege")})
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprofile")
    private Long idprofile;
    @Size(max = 254)
    @Column(name = "name")
    private String name;
    @Size(max = 2147483647)
    @Column(name = "configprivilege")
    private String configprivilege;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprofile")
    private Collection<Uuser> uuserCollection;

    public Profile() {
    }

    public Profile(Long idprofile) {
        this.idprofile = idprofile;
    }

    public Long getIdprofile() {
        return idprofile;
    }

    public void setIdprofile(Long idprofile) {
        this.idprofile = idprofile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfigprivilege() {
        return configprivilege;
    }

    public void setConfigprivilege(String configprivilege) {
        this.configprivilege = configprivilege;
    }

    @XmlTransient
    public Collection<Uuser> getUuserCollection() {
        return uuserCollection;
    }

    public void setUuserCollection(Collection<Uuser> uuserCollection) {
        this.uuserCollection = uuserCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprofile != null ? idprofile.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.idprofile == null && other.idprofile != null) || (this.idprofile != null && !this.idprofile.equals(other.idprofile))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Profile[ idprofile=" + idprofile + " ]";
    }
}
