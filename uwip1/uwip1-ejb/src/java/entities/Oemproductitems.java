/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Seanjackson
 */
@Entity
@Table(name = "oemproductitems")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oemproductitems.findAll", query = "SELECT o FROM Oemproductitems o"),
    @NamedQuery(name = "Oemproductitems.findByIdoemproductitems", query = "SELECT o FROM Oemproductitems o WHERE o.idoemproductitems = :idoemproductitems"),
    @NamedQuery(name = "Oemproductitems.findByProductcode", query = "SELECT o FROM Oemproductitems o WHERE o.productcode = :productcode"),
    @NamedQuery(name = "Oemproductitems.findByBatchnumber", query = "SELECT o FROM Oemproductitems o WHERE o.batchnumber = :batchnumber"),
    @NamedQuery(name = "Oemproductitems.findByOemlifecyclestatus", query = "SELECT o FROM Oemproductitems o WHERE o.oemlifecyclestatus = :oemlifecyclestatus")})
public class Oemproductitems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "idoemproductitems")
    private String idoemproductitems;
    @Size(max = 45)
    @Column(name = "productcode")
    private String productcode;
    @Size(max = 45)
    @Column(name = "batchnumber")
    private String batchnumber;
    @Size(max = 10)
    @Column(name = "oemlifecyclestatus")
    private String oemlifecyclestatus;
    @OneToMany(mappedBy = "idoemproductitems")
    private Collection<Payplandetails> payplandetailsCollection;
    @JoinColumn(name = "idpaygproduct", referencedColumnName = "idpaygproduct")
    @ManyToOne
    private Paygotpgenerator idpaygproduct;

    public Oemproductitems() {
    }

    public Oemproductitems(String idoemproductitems) {
        this.idoemproductitems = idoemproductitems;
    }

    public String getIdoemproductitems() {
        return idoemproductitems;
    }

    public void setIdoemproductitems(String idoemproductitems) {
        this.idoemproductitems = idoemproductitems;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public String getBatchnumber() {
        return batchnumber;
    }

    public void setBatchnumber(String batchnumber) {
        this.batchnumber = batchnumber;
    }

    public String getOemlifecyclestatus() {
        return oemlifecyclestatus;
    }

    public void setOemlifecyclestatus(String oemlifecyclestatus) {
        this.oemlifecyclestatus = oemlifecyclestatus;
    }

    @XmlTransient
    public Collection<Payplandetails> getPayplandetailsCollection() {
        return payplandetailsCollection;
    }

    public void setPayplandetailsCollection(Collection<Payplandetails> payplandetailsCollection) {
        this.payplandetailsCollection = payplandetailsCollection;
    }

    public Paygotpgenerator getIdpaygproduct() {
        return idpaygproduct;
    }

    public void setIdpaygproduct(Paygotpgenerator idpaygproduct) {
        this.idpaygproduct = idpaygproduct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idoemproductitems != null ? idoemproductitems.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oemproductitems)) {
            return false;
        }
        Oemproductitems other = (Oemproductitems) object;
        if ((this.idoemproductitems == null && other.idoemproductitems != null) || (this.idoemproductitems != null && !this.idoemproductitems.equals(other.idoemproductitems))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Oemproductitems[ idoemproductitems=" + idoemproductitems + " ]";
    }
}
