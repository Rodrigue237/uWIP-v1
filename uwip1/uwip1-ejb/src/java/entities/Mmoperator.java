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
@Table(name = "mmoperator")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mmoperator.findAll", query = "SELECT m FROM Mmoperator m"),
    @NamedQuery(name = "Mmoperator.findCopieDouble", query = "SELECT COUNT(m.idoperator) FROM Mmoperator m WHERE m.code = :filtre"),
    @NamedQuery(name = "Mmoperator.findByIdoperator", query = "SELECT m FROM Mmoperator m WHERE m.idoperator = :idoperator"),
    @NamedQuery(name = "Mmoperator.findByCode", query = "SELECT m FROM Mmoperator m WHERE m.code = :code")})
public class Mmoperator implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idoperator")
    private Long idoperator;
    @Size(max = 254)
    @Column(name = "code")
    private String code;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idoperator")
    private Collection<Client> clientCollection;

    public Mmoperator() {
    }

    public Mmoperator(Long idoperator) {
        this.idoperator = idoperator;
    }

    public Long getIdoperator() {
        return idoperator;
    }

    public void setIdoperator(Long idoperator) {
        this.idoperator = idoperator;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlTransient
    public Collection<Client> getClientCollection() {
        return clientCollection;
    }

    public void setClientCollection(Collection<Client> clientCollection) {
        this.clientCollection = clientCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idoperator != null ? idoperator.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mmoperator)) {
            return false;
        }
        Mmoperator other = (Mmoperator) object;
        if ((this.idoperator == null && other.idoperator != null) || (this.idoperator != null && !this.idoperator.equals(other.idoperator))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Mmoperator[ idoperator=" + idoperator + " ]";
    }
}
