/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Seanjackson
 */
@Entity
@Table(name = "payplandetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payplandetails.findAll", query = "SELECT p FROM Payplandetails p"),
    @NamedQuery(name = "Payplandetails.findByIdpayplandetails", query = "SELECT p FROM Payplandetails p WHERE p.idpayplandetails = :idpayplandetails"),
    @NamedQuery(name = "Payplandetails.findByPayplanid", query = "SELECT p FROM Payplandetails p WHERE p.payplanid = :payplanid"),
    @NamedQuery(name = "Payplandetails.findByOemproductid", query = "SELECT p FROM Payplandetails p WHERE p.oemproductid = :oemproductid"),
    @NamedQuery(name = "Payplandetails.findByPlanpaynumber", query = "SELECT p FROM Payplandetails p WHERE p.planpaynumber = :planpaynumber"),
    @NamedQuery(name = "Payplandetails.findByPaydate", query = "SELECT p FROM Payplandetails p WHERE p.paydate = :paydate"),
    @NamedQuery(name = "Payplandetails.findByPayamount", query = "SELECT p FROM Payplandetails p WHERE p.payamount = :payamount")})
public class Payplandetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "idpayplandetails")
    private String idpayplandetails;
    @Column(name = "payplanid")
    private Integer payplanid;
    @Size(max = 45)
    @Column(name = "oemproductid")
    private String oemproductid;
    @Column(name = "planpaynumber")
    private Integer planpaynumber;
    @Column(name = "paydate")
    @Temporal(TemporalType.DATE)
    private Date paydate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "payamount")
    private Double payamount;
    @JoinColumn(name = "idoemproductitems", referencedColumnName = "idoemproductitems")
    @ManyToOne
    private Oemproductitems idoemproductitems;

    public Payplandetails() {
    }

    public Payplandetails(String idpayplandetails) {
        this.idpayplandetails = idpayplandetails;
    }

    public String getIdpayplandetails() {
        return idpayplandetails;
    }

    public void setIdpayplandetails(String idpayplandetails) {
        this.idpayplandetails = idpayplandetails;
    }

    public Integer getPayplanid() {
        return payplanid;
    }

    public void setPayplanid(Integer payplanid) {
        this.payplanid = payplanid;
    }

    public String getOemproductid() {
        return oemproductid;
    }

    public void setOemproductid(String oemproductid) {
        this.oemproductid = oemproductid;
    }

    public Integer getPlanpaynumber() {
        return planpaynumber;
    }

    public void setPlanpaynumber(Integer planpaynumber) {
        this.planpaynumber = planpaynumber;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public Double getPayamount() {
        return payamount;
    }

    public void setPayamount(Double payamount) {
        this.payamount = payamount;
    }

    public Oemproductitems getIdoemproductitems() {
        return idoemproductitems;
    }

    public void setIdoemproductitems(Oemproductitems idoemproductitems) {
        this.idoemproductitems = idoemproductitems;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpayplandetails != null ? idpayplandetails.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payplandetails)) {
            return false;
        }
        Payplandetails other = (Payplandetails) object;
        if ((this.idpayplandetails == null && other.idpayplandetails != null) || (this.idpayplandetails != null && !this.idpayplandetails.equals(other.idpayplandetails))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Payplandetails[ idpayplandetails=" + idpayplandetails + " ]";
    }
}
