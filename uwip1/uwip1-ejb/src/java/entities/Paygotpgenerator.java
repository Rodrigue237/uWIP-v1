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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Seanjackson
 */
@Entity
@Table(name = "paygotpgenerator")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paygotpgenerator.findAll", query = "SELECT p FROM Paygotpgenerator p"),
    @NamedQuery(name = "Paygotpgenerator.findLengthData", query = "SELECT COUNT(p.idpaygproduct) FROM Paygotpgenerator p WHERE p.idpaygproduct != :filtre"),
    @NamedQuery(name = "Paygotpgenerator.findAllFreeTo", query = "SELECT p FROM Paygotpgenerator p WHERE p.initialisationstatus = :initialisationstatus AND(p.boxstatus = :boxstatus AND p.payglifecyclestatus = :payglifecyclestatus) ORDER by p.idpaygproduct"),
    @NamedQuery(name = "Paygotpgenerator.findByIdpaygproduct", query = "SELECT p FROM Paygotpgenerator p WHERE p.idpaygproduct = :idpaygproduct"),
    @NamedQuery(name = "Paygotpgenerator.findByPaygsechashtop", query = "SELECT p FROM Paygotpgenerator p WHERE p.paygsechashtop = :paygsechashtop"),
    @NamedQuery(name = "Paygotpgenerator.findByPaygsechashroot", query = "SELECT p FROM Paygotpgenerator p WHERE p.paygsechashroot = :paygsechashroot"),
    @NamedQuery(name = "Paygotpgenerator.findByOtpcount", query = "SELECT p FROM Paygotpgenerator p WHERE p.otpcount = :otpcount"),
    @NamedQuery(name = "Paygotpgenerator.findByCurrenthashindex", query = "SELECT p FROM Paygotpgenerator p WHERE p.currenthashindex = :currenthashindex"),
    @NamedQuery(name = "Paygotpgenerator.findByHashchainlength", query = "SELECT p FROM Paygotpgenerator p WHERE p.hashchainlength = :hashchainlength"),
    @NamedQuery(name = "Paygotpgenerator.findByMaxhcj", query = "SELECT p FROM Paygotpgenerator p WHERE p.maxhcj = :maxhcj"),
    @NamedQuery(name = "Paygotpgenerator.findHRoot", query = "SELECT p.paygsechashroot FROM Paygotpgenerator p WHERE p.idpaygproduct = :idpaygproduct"),
    @NamedQuery(name = "Paygotpgenerator.findCurrentCindex", query = "SELECT p.currenthashindex FROM Paygotpgenerator p WHERE p.idpaygproduct = :idpaygproduct"),
    @NamedQuery(name = "Paygotpgenerator.findCDT", query = "SELECT p.otpcount FROM Paygotpgenerator p WHERE p.idpaygproduct = :idpaygproduct"),
    @NamedQuery(name = "Paygotpgenerator.findMaxHCJ", query = "SELECT p.maxhcj FROM Paygotpgenerator p WHERE p.idpaygproduct = :idpaygproduct"),
    @NamedQuery(name = "Paygotpgenerator.updateCDT", query = "UPDATE Paygotpgenerator p SET p.otpcount = :otpcount WHERE p.idpaygproduct = :idpaygproduct"),
    @NamedQuery(name = "Paygotpgenerator.updateFirstCode", query = "UPDATE Paygotpgenerator p SET p.firstcode = :firstcode WHERE p.idpaygproduct = :idpaygproduct"),
    @NamedQuery(name = "Paygotpgenerator.updateinitstatus", query = "UPDATE Paygotpgenerator p SET p.initialisationstatus = :initialisationstatus WHERE p.idpaygproduct = :idpaygproduct"),
    @NamedQuery(name = "Paygotpgenerator.updateDateInitFirst", query = "UPDATE Paygotpgenerator p SET p.dateinitialisation = :dateinitialisation WHERE p.idpaygproduct = :idpaygproduct"),
    //@NamedQuery(name = "Paygotpgenerator.updateBoxInstallInfo", query = "UPDATE Paygotpgenerator p SET p.boxstatus = :boxstatus, p.payglifecyclestatus = :payglifecyclestatus, p.latitude = :latitude, p.longitude = :longitude WHERE p.idpaygproduct = :idpaygproduct"),
    @NamedQuery(name = "Paygotpgenerator.updateBoxInstallInfo", query = "UPDATE Paygotpgenerator p SET p.boxstatus = :boxstatus, p.payglifecyclestatus = :payglifecyclestatus WHERE p.idpaygproduct = :idpaygproduct"),
    @NamedQuery(name = "Paygotpgenerator.updateDateLastMaintenance", query = "UPDATE Paygotpgenerator p SET p.lastmaintenance = :lastmaintenance WHERE p.idpaygproduct = :idpaygproduct"),
    @NamedQuery(name = "Paygotpgenerator.findByPayglifecyclestatus", query = "SELECT p FROM Paygotpgenerator p WHERE p.payglifecyclestatus = :payglifecyclestatus")})
public class Paygotpgenerator implements Serializable {
    @Column(name = "lastmaintenance")
    @Temporal(TemporalType.DATE)
    private Date lastmaintenance;
    @Size(max = 50)
    @Column(name = "firstcode")
    private String firstcode;
    @Column(name = "dateinitialisation")
    @Temporal(TemporalType.DATE)
    private Date dateinitialisation;
    @Size(max = 20)
    @Column(name = "initialisationstatus")
    private String initialisationstatus;

    @Size(max = 10)
    @Column(name = "boxstatus")
    private String boxstatus;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Size(max = 20)
    @Column(name = "marquerstyle")
    private String marquerstyle;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "idpaygproduct")
    private String idpaygproduct;
    @Size(max = 45)
    @Column(name = "paygsechashtop")
    private String paygsechashtop;
    @Size(max = 45)
    @Column(name = "paygsechashroot")
    private String paygsechashroot;
    @Column(name = "otpcount")
    private Integer otpcount;
    @Column(name = "currenthashindex")
    private Integer currenthashindex;
    @Column(name = "hashchainlength")
    private Integer hashchainlength;
    @Column(name = "maxhcj")
    private Integer maxhcj;
    @Size(max = 10)
    @Column(name = "payglifecyclestatus")
    private String payglifecyclestatus;
    @OneToMany(mappedBy = "idpaygproduct")
    private Collection<Oemproductitems> oemproductitemsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpaygproduct")
    private Collection<Credits> creditsCollection;

    public Paygotpgenerator() {
    }

    public Paygotpgenerator(String idpaygproduct) {
        this.idpaygproduct = idpaygproduct;
    }

    public String getIdpaygproduct() {
        return idpaygproduct;
    }

    public void setIdpaygproduct(String idpaygproduct) {
        this.idpaygproduct = idpaygproduct;
    }

    public String getPaygsechashtop() {
        return paygsechashtop;
    }

    public void setPaygsechashtop(String paygsechashtop) {
        this.paygsechashtop = paygsechashtop;
    }

    public String getPaygsechashroot() {
        return paygsechashroot;
    }

    public void setPaygsechashroot(String paygsechashroot) {
        this.paygsechashroot = paygsechashroot;
    }

    public Integer getOtpcount() {
        return otpcount;
    }

    public void setOtpcount(Integer otpcount) {
        this.otpcount = otpcount;
    }

    public Integer getCurrenthashindex() {
        return currenthashindex;
    }

    public void setCurrenthashindex(Integer currenthashindex) {
        this.currenthashindex = currenthashindex;
    }

    public Integer getHashchainlength() {
        return hashchainlength;
    }

    public void setHashchainlength(Integer hashchainlength) {
        this.hashchainlength = hashchainlength;
    }

    public Integer getMaxhcj() {
        return maxhcj;
    }

    public void setMaxhcj(Integer maxhcj) {
        this.maxhcj = maxhcj;
    }

    public String getPayglifecyclestatus() {
        return payglifecyclestatus;
    }

    public void setPayglifecyclestatus(String payglifecyclestatus) {
        this.payglifecyclestatus = payglifecyclestatus;
    }

    @XmlTransient
    public Collection<Oemproductitems> getOemproductitemsCollection() {
        return oemproductitemsCollection;
    }

    public void setOemproductitemsCollection(Collection<Oemproductitems> oemproductitemsCollection) {
        this.oemproductitemsCollection = oemproductitemsCollection;
    }

    @XmlTransient
    public Collection<Credits> getCreditsCollection() {
        return creditsCollection;
    }

    public void setCreditsCollection(Collection<Credits> creditsCollection) {
        this.creditsCollection = creditsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpaygproduct != null ? idpaygproduct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paygotpgenerator)) {
            return false;
        }
        Paygotpgenerator other = (Paygotpgenerator) object;
        if ((this.idpaygproduct == null && other.idpaygproduct != null) || (this.idpaygproduct != null && !this.idpaygproduct.equals(other.idpaygproduct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Paygotpgenerator[ idpaygproduct=" + idpaygproduct + " ]";
    }

    public String getBoxstatus() {
        return boxstatus;
    }

    public void setBoxstatus(String boxstatus) {
        this.boxstatus = boxstatus;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getMarquerstyle() {
        return marquerstyle;
    }

    public void setMarquerstyle(String marquerstyle) {
        this.marquerstyle = marquerstyle;
    }

    public Date getDateinitialisation() {
        return dateinitialisation;
    }

    public void setDateinitialisation(Date dateinitialisation) {
        this.dateinitialisation = dateinitialisation;
    }

    public String getInitialisationstatus() {
        return initialisationstatus;
    }

    public void setInitialisationstatus(String initialisationstatus) {
        this.initialisationstatus = initialisationstatus;
    }

    public String getFirstcode() {
        return firstcode;
    }

    public void setFirstcode(String firstcode) {
        this.firstcode = firstcode;
    }

    public Date getLastmaintenance() {
        return lastmaintenance;
    }

    public void setLastmaintenance(Date lastmaintenance) {
        this.lastmaintenance = lastmaintenance;
    }
}
