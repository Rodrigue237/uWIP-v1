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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "townlayer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Townlayer.findAll", query = "SELECT t FROM Townlayer t"),
    @NamedQuery(name = "Townlayer.findCopieDouble", query = "SELECT COUNT(t.idtownlayer) FROM Townlayer t WHERE t.layer = :filtre"),
    @NamedQuery(name = "Townlayer.findByIdtownlayer", query = "SELECT t FROM Townlayer t WHERE t.idtownlayer = :idtownlayer"),
    @NamedQuery(name = "Townlayer.findAllCountryLayer", query = "SELECT c FROM Countrylayer c WHERE c.idcountrylayer != 0"),
    @NamedQuery(name = "Townlayer.findByLayer", query = "SELECT t FROM Townlayer t WHERE t.layer = :layer")})
public class Townlayer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtownlayer")
    private Long idtownlayer;
    @Size(max = 254)
    @Column(name = "layer")
    private String layer;
    @JoinColumn(name = "idcountrylayer", referencedColumnName = "idcountrylayer")
    @ManyToOne(optional = false)
    private Countrylayer idcountrylayer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtownlayer")
    private Collection<Box> boxCollection;

    public Townlayer() {
    }

    public Townlayer(Long idtownlayer) {
        this.idtownlayer = idtownlayer;
    }

    public Long getIdtownlayer() {
        return idtownlayer;
    }

    public void setIdtownlayer(Long idtownlayer) {
        this.idtownlayer = idtownlayer;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public Countrylayer getIdcountrylayer() {
        return idcountrylayer;
    }

    public void setIdcountrylayer(Countrylayer idcountrylayer) {
        this.idcountrylayer = idcountrylayer;
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
        hash += (idtownlayer != null ? idtownlayer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Townlayer)) {
            return false;
        }
        Townlayer other = (Townlayer) object;
        if ((this.idtownlayer == null && other.idtownlayer != null) || (this.idtownlayer != null && !this.idtownlayer.equals(other.idtownlayer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Townlayer[ idtownlayer=" + idtownlayer + " ]";
    }
}
