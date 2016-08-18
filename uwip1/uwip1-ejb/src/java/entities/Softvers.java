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
@Table(name = "softvers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Softvers.findAll", query = "SELECT s FROM Softvers s"),
    @NamedQuery(name = "Softvers.findAllSoft", query = "SELECT s FROM Softvers s WHERE s.idsoftvers != 0"),
    @NamedQuery(name = "Softvers.findCopieDouble", query = "SELECT COUNT(s.idsoftvers) FROM Softvers s WHERE s.name = :filtre AND s.version = :filtre2"),
    @NamedQuery(name = "Softvers.findByIdsoftvers", query = "SELECT s FROM Softvers s WHERE s.idsoftvers = :idsoftvers"),
    @NamedQuery(name = "Softvers.findByName", query = "SELECT s FROM Softvers s WHERE s.name = :name"),
    @NamedQuery(name = "Softvers.findByVersion", query = "SELECT s FROM Softvers s WHERE s.version = :version"),
    @NamedQuery(name = "Softvers.findByDescription", query = "SELECT s FROM Softvers s WHERE s.description = :description"),
    @NamedQuery(name = "Softvers.findByUpdates", query = "SELECT s FROM Softvers s WHERE s.updates = :updates"),
    @NamedQuery(name = "Softvers.findByCreateddate", query = "SELECT s FROM Softvers s WHERE s.createddate = :createddate")})
public class Softvers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsoftvers")
    private Long idsoftvers;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsoftvers")
    private Collection<Box> boxCollection;

    public Softvers() {
    }

    public Softvers(Long idsoftvers) {
        this.idsoftvers = idsoftvers;
    }

    public Long getIdsoftvers() {
        return idsoftvers;
    }

    public void setIdsoftvers(Long idsoftvers) {
        this.idsoftvers = idsoftvers;
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
        hash += (idsoftvers != null ? idsoftvers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Softvers)) {
            return false;
        }
        Softvers other = (Softvers) object;
        if ((this.idsoftvers == null && other.idsoftvers != null) || (this.idsoftvers != null && !this.idsoftvers.equals(other.idsoftvers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Softvers[ idsoftvers=" + idsoftvers + " ]";
    }
}
