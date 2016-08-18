/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Seanjackson
 */
@Entity
@Table(name = "hardvers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hardvers.findAll", query = "SELECT h FROM Hardvers h"),
    @NamedQuery(name = "Hardvers.findAllHard", query = "SELECT h FROM Hardvers h WHERE h.idhardvers != 0"),
    @NamedQuery(name = "Hardvers.findCopieDouble", query = "SELECT COUNT(h.idhardvers) FROM Hardvers h WHERE h.name = :filtre AND h.version = :filtre2"),
    @NamedQuery(name = "Hardvers.findByIdhardvers", query = "SELECT h FROM Hardvers h WHERE h.idhardvers = :idhardvers"),
    @NamedQuery(name = "Hardvers.findByName", query = "SELECT h FROM Hardvers h WHERE h.name = :name"),
    @NamedQuery(name = "Hardvers.findByVersion", query = "SELECT h FROM Hardvers h WHERE h.version = :version"),
    @NamedQuery(name = "Hardvers.findByDescription", query = "SELECT h FROM Hardvers h WHERE h.description = :description"),
    @NamedQuery(name = "Hardvers.findByUpdates", query = "SELECT h FROM Hardvers h WHERE h.updates = :updates"),
    @NamedQuery(name = "Hardvers.findByCreateddate", query = "SELECT h FROM Hardvers h WHERE h.createddate = :createddate")})
public class Hardvers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhardvers")
    private Long idhardvers;
    @Size(max = 254)
    @Column(name = "name")
    private String name;
    @Size(max = 254)
    @Column(name = "version")
    private String version;
    @Size(max = 254)
    @Column(name = "description")
    private String description;
    @Size(max = 254)
    @Column(name = "updates")
    private String updates;
    @Column(name = "createddate")
    @Temporal(TemporalType.DATE)
    private Date createddate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idhardvers")
    private Collection<Box> boxCollection;

    public Hardvers() {
    }

    public Hardvers(Long idhardvers) {
        this.idhardvers = idhardvers;
    }

    public Long getIdhardvers() {
        return idhardvers;
    }

    public void setIdhardvers(Long idhardvers) {
        this.idhardvers = idhardvers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdates() {
        return updates;
    }

    public void setUpdates(String updates) {
        this.updates = updates;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    @XmlTransient
    public Collection<Box> getBoxCollection() {
        return boxCollection;
    }

    public void setBoxCollection(Collection<Box> boxCollection) {
        this.boxCollection = boxCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhardvers != null ? idhardvers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hardvers)) {
            return false;
        }
        Hardvers other = (Hardvers) object;
        if ((this.idhardvers == null && other.idhardvers != null) || (this.idhardvers != null && !this.idhardvers.equals(other.idhardvers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Hardvers[ idhardvers=" + idhardvers + " ]";
    }
}
