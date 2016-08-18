/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Seanjackson
 */
@Entity
@Table(name = "privilege")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Privilege.findAll", query = "SELECT p FROM Privilege p"),
    @NamedQuery(name = "Privilege.findByIdprivilege", query = "SELECT p FROM Privilege p WHERE p.idprivilege = :idprivilege"),
    @NamedQuery(name = "Privilege.findByDescription", query = "SELECT p FROM Privilege p WHERE p.description = :description"),
    @NamedQuery(name = "Privilege.findByKey", query = "SELECT p FROM Privilege p WHERE p.key = :key"),
    @NamedQuery(name = "Privilege.findByIdprivilegeacces", query = "SELECT p FROM Privilege p WHERE p.idprivilegeacces = :idprivilegeacces")})
public class Privilege implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprivilege")
    private Long idprivilege;
    @Size(max = 254)
    @Column(name = "description")
    private String description;
    @Size(max = 254)
    @Column(name = "key")
    private String key;
    @Column(name = "idprivilegeacces")
    private Integer idprivilegeacces;

    public Privilege() {
    }

    public Privilege(Long idprivilege) {
        this.idprivilege = idprivilege;
    }

    public Long getIdprivilege() {
        return idprivilege;
    }

    public void setIdprivilege(Long idprivilege) {
        this.idprivilege = idprivilege;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getIdprivilegeacces() {
        return idprivilegeacces;
    }

    public void setIdprivilegeacces(Integer idprivilegeacces) {
        this.idprivilegeacces = idprivilegeacces;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprivilege != null ? idprivilege.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privilege)) {
            return false;
        }
        Privilege other = (Privilege) object;
        if ((this.idprivilege == null && other.idprivilege != null) || (this.idprivilege != null && !this.idprivilege.equals(other.idprivilege))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Privilege[ idprivilege=" + idprivilege + " ]";
    }
}
