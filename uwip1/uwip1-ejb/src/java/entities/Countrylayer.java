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
@Table(name = "countrylayer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Countrylayer.findAll", query = "SELECT c FROM Countrylayer c"),
    @NamedQuery(name = "Countrylayer.findCopieDouble", query = "SELECT COUNT(c.idcountrylayer) FROM Countrylayer c WHERE c.layer = :filtre"),
    @NamedQuery(name = "Countrylayer.findByIdcountrylayer", query = "SELECT c FROM Countrylayer c WHERE c.idcountrylayer = :idcountrylayer"),
    @NamedQuery(name = "Countrylayer.findByLayer", query = "SELECT c FROM Countrylayer c WHERE c.layer = :layer")})
public class Countrylayer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcountrylayer")
    private Long idcountrylayer;
    @Size(max = 254)
    @Column(name = "layer")
    private String layer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcountrylayer")
    private Collection<Townlayer> townlayerCollection;

    public Countrylayer() {
    }

    public Countrylayer(Long idcountrylayer) {
        this.idcountrylayer = idcountrylayer;
    }

    public Long getIdcountrylayer() {
        return idcountrylayer;
    }

    public void setIdcountrylayer(Long idcountrylayer) {
        this.idcountrylayer = idcountrylayer;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    @XmlTransient
    public Collection<Townlayer> getTownlayerCollection() {
        return townlayerCollection;
    }

    public void setTownlayerCollection(Collection<Townlayer> townlayerCollection) {
        this.townlayerCollection = townlayerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcountrylayer != null ? idcountrylayer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Countrylayer)) {
            return false;
        }
        Countrylayer other = (Countrylayer) object;
        if ((this.idcountrylayer == null && other.idcountrylayer != null) || (this.idcountrylayer != null && !this.idcountrylayer.equals(other.idcountrylayer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Countrylayer[ idcountrylayer=" + idcountrylayer + " ]";
    }
}
