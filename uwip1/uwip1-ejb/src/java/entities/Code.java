/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Seanjackson
 */
@Entity
@Table(name = "code")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Code.findAll", query = "SELECT c FROM Code c"),
    @NamedQuery(name = "Code.findByIdcode", query = "SELECT c FROM Code c WHERE c.idcode = :idcode"),
    @NamedQuery(name = "Code.findBoxInstalled", query = "SELECT b FROM Box b WHERE b.marquerstatus = 1"),
    @NamedQuery(name = "Code.findByCodevalue", query = "SELECT c FROM Code c WHERE c.codevalue = :codevalue"),
    @NamedQuery(name = "Code.findByGenekeyused", query = "SELECT c FROM Code c WHERE c.genekeyused = :genekeyused"),
    @NamedQuery(name = "Code.findByDategene", query = "SELECT c FROM Code c WHERE c.dategene = :dategene"),
    @NamedQuery(name = "Code.findByTimevalue", query = "SELECT c FROM Code c WHERE c.timevalue = :timevalue"),
    @NamedQuery(name = "Code.findKey1", query = "SELECT b.kpi1 FROM Box b WHERE b.numbox = :numbox"),
    @NamedQuery(name = "Code.findKey2", query = "SELECT b.kpi2 FROM Box b WHERE b.numbox = :numbox"),
    @NamedQuery(name = "Code.findKey3", query = "SELECT b.kpi3 FROM Box b WHERE b.numbox = :numbox"),
    //@NamedQuery(name = "Code.findNumBox", query = "SELECT b.numbox FROM Box b WHERE b.idboxe = :idboxe"),
    @NamedQuery(name = "Code.findNumPcb", query = "SELECT b.numpcb FROM Box b WHERE b.numbox = :numbox"),
    @NamedQuery(name = "Code.findPaygIdprod", query = "SELECT c.idpaygproduct.idpaygproduct FROM Credits c WHERE c.numinstall = :numinstall"),
    @NamedQuery(name = "Code.findPrix1Jour", query = "SELECT c.prix1jour FROM Credits c WHERE c.numinstall = :numbox"),
    @NamedQuery(name = "Code.findTotalTimePaid", query = "SELECT b.totaltimepaid FROM Box b WHERE b.numbox = :numbox"),
    @NamedQuery(name = "Code.findIdBox", query = "SELECT b.idboxe FROM Box b WHERE b.numpcb = :numpcb"),
    @NamedQuery(name = "Code.findIdBoxOfCode", query = "SELECT b.idboxe FROM Box b WHERE b.numbox = :numbox"),
    @NamedQuery(name = "Code.findIdClientOfCode", query = "SELECT b.idclient FROM Box b WHERE b.numbox = :numbox"),
    @NamedQuery(name = "Code.findIdOrganizationOfCode", query = "SELECT b.idorganization FROM Box b WHERE b.numbox = :numbox"),
    @NamedQuery(name = "Code.findIdDistributorOfCode", query = "SELECT d.iddistributor FROM Distributor d WHERE d.distribuniqnumber = :distribuniqnumber"),
    @NamedQuery(name = "Code.updateKeyactivAndKey3", query = "UPDATE Box b SET b.genekeyactiv = :genekeyactiv, b.kpi3 = :kpi3, b.lasttransact = :lasttransact, b.lasttimecodevalue = :lasttimecodevalue, b.estimatedenddate = :estimatedenddate, b.estimlefttimepaid = :estimlefttimepaid, b.totaltimepaid = :totaltimepaid WHERE b.idboxe = :idboxe"),
    @NamedQuery(name = "Code.findLastEndDate", query = "SELECT b.estimatedenddate FROM Box b WHERE b.numbox = :numbox"),
    @NamedQuery(name = "Code.findLastTotalTimePaid", query = "SELECT b.totaltimepaid FROM Box b WHERE b.numbox = :numbox"),
    @NamedQuery(name = "Code.findByPrice", query = "SELECT c FROM Code c WHERE c.price = :price"),
    @NamedQuery(name = "Code.findByTimegene", query = "SELECT c FROM Code c WHERE c.timegene = :timegene"),
    @NamedQuery(name = "Code.findByEnddate", query = "SELECT c FROM Code c WHERE c.enddate = :enddate")})
public class Code implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcode")
    private Long idcode;
    @Column(name = "codevalue")
    private BigInteger codevalue;
    @Column(name = "genekeyused")
    private Integer genekeyused;
    @Column(name = "dategene")
    @Temporal(TemporalType.DATE)
    private Date dategene;
    @Column(name = "timevalue")
    private BigInteger timevalue;
    @Column(name = "price")
    private Double price;
    @Size(max = 254)
    @Column(name = "timegene")
    private String timegene;
    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date enddate;
    @JoinColumn(name = "idorganization", referencedColumnName = "idorganization")
    @ManyToOne(optional = false)
    private Organization idorganization;
    @JoinColumn(name = "iddistributor", referencedColumnName = "iddistributor")
    @ManyToOne(optional = false)
    private Distributor iddistributor;
    @JoinColumn(name = "idclient", referencedColumnName = "idclient")
    @ManyToOne(optional = false)
    private Client idclient;
    @JoinColumn(name = "idboxe", referencedColumnName = "idboxe")
    @ManyToOne(optional = false)
    private Box idboxe;

    public Code() {
    }

    public Code(Long idcode) {
        this.idcode = idcode;
    }

    public Long getIdcode() {
        return idcode;
    }

    public void setIdcode(Long idcode) {
        this.idcode = idcode;
    }

    public BigInteger getCodevalue() {
        return codevalue;
    }

    public void setCodevalue(BigInteger codevalue) {
        this.codevalue = codevalue;
    }

    public Integer getGenekeyused() {
        return genekeyused;
    }

    public void setGenekeyused(Integer genekeyused) {
        this.genekeyused = genekeyused;
    }

    public Date getDategene() {
        return dategene;
    }

    public void setDategene(Date dategene) {
        this.dategene = dategene;
    }

    public BigInteger getTimevalue() {
        return timevalue;
    }

    public void setTimevalue(BigInteger timevalue) {
        this.timevalue = timevalue;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTimegene() {
        return timegene;
    }

    public void setTimegene(String timegene) {
        this.timegene = timegene;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Organization getIdorganization() {
        return idorganization;
    }

    public void setIdorganization(Organization idorganization) {
        this.idorganization = idorganization;
    }

    public Distributor getIddistributor() {
        return iddistributor;
    }

    public void setIddistributor(Distributor iddistributor) {
        this.iddistributor = iddistributor;
    }

    public Client getIdclient() {
        return idclient;
    }

    public void setIdclient(Client idclient) {
        this.idclient = idclient;
    }

    public Box getIdboxe() {
        return idboxe;
    }

    public void setIdboxe(Box idboxe) {
        this.idboxe = idboxe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcode != null ? idcode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Code)) {
            return false;
        }
        Code other = (Code) object;
        if ((this.idcode == null && other.idcode != null) || (this.idcode != null && !this.idcode.equals(other.idcode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Code[ idcode=" + idcode + " ]";
    }
}
