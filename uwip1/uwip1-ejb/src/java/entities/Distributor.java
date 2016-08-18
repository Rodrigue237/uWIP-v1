/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
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
@Table(name = "distributor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distributor.findAll", query = "SELECT d FROM Distributor d"),
    @NamedQuery(name = "Distributor.findCopieDouble", query = "SELECT COUNT(d.iddistributor) FROM Distributor d WHERE d.distribuniqnumber = :filtre"),
    @NamedQuery(name = "Distributor.findByIddistributor", query = "SELECT d FROM Distributor d WHERE d.iddistributor = :iddistributor"),
    @NamedQuery(name = "Distributor.findByFirstname", query = "SELECT d FROM Distributor d WHERE d.firstname = :firstname"),
    @NamedQuery(name = "Distributor.findByLastname", query = "SELECT d FROM Distributor d WHERE d.lastname = :lastname"),
    @NamedQuery(name = "Distributor.findByDistribuniqnumber", query = "SELECT d FROM Distributor d WHERE d.distribuniqnumber = :distribuniqnumber"),
    @NamedQuery(name = "Distributor.findByShoplocation", query = "SELECT d FROM Distributor d WHERE d.shoplocation = :shoplocation"),
    @NamedQuery(name = "Distributor.findByCnipic", query = "SELECT d FROM Distributor d WHERE d.cnipic = :cnipic"),
    @NamedQuery(name = "Distributor.findByShoppic", query = "SELECT d FROM Distributor d WHERE d.shoppic = :shoppic"),
    @NamedQuery(name = "Distributor.findByVillage", query = "SELECT d FROM Distributor d WHERE d.village = :village"),
    @NamedQuery(name = "Distributor.findByMunicipality", query = "SELECT d FROM Distributor d WHERE d.municipality = :municipality"),
    @NamedQuery(name = "Distributor.findByTotalcodesolde", query = "SELECT d FROM Distributor d WHERE d.totalcodesolde = :totalcodesolde"),
    @NamedQuery(name = "Distributor.findByTotaltimesolde", query = "SELECT d FROM Distributor d WHERE d.totaltimesolde = :totaltimesolde"),
    @NamedQuery(name = "Distributor.findByLasttransact", query = "SELECT d FROM Distributor d WHERE d.lasttransact = :lasttransact"),
    @NamedQuery(name = "Distributor.findByLatitude", query = "SELECT d FROM Distributor d WHERE d.latitude = :latitude"),
    @NamedQuery(name = "Distributor.findByLongitude", query = "SELECT d FROM Distributor d WHERE d.longitude = :longitude"),
    @NamedQuery(name = "Distributor.findByMapped", query = "SELECT d FROM Distributor d WHERE d.marquerstatus = 1"),
    @NamedQuery(name = "Distributor.findByMarquerstatus", query = "SELECT d FROM Distributor d WHERE d.marquerstatus = :marquerstatus")})
public class Distributor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddistributor")
    private Long iddistributor;
    @Size(max = 254)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 254)
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "distribuniqnumber")
    private Integer distribuniqnumber;
    @Size(max = 254)
    @Column(name = "shoplocation")
    private String shoplocation;
    @Size(max = 254)
    @Column(name = "cnipic")
    private String cnipic;
    @Size(max = 254)
    @Column(name = "shoppic")
    private String shoppic;
    @Size(max = 254)
    @Column(name = "village")
    private String village;
    @Size(max = 254)
    @Column(name = "municipality")
    private String municipality;
    @Column(name = "totalcodesolde")
    private BigInteger totalcodesolde;
    @Column(name = "totaltimesolde")
    private BigInteger totaltimesolde;
    @Column(name = "lasttransact")
    @Temporal(TemporalType.DATE)
    private Date lasttransact;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "marquerstatus")
    private Integer marquerstatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddistributor")
    private Collection<Code> codeCollection;
    @JoinColumn(name = "idorganization", referencedColumnName = "idorganization")
    @ManyToOne(optional = false)
    private Organization idorganization;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddistributor")
    private Collection<Note> noteCollection;

    public Distributor() {
    }

    public Distributor(Long iddistributor) {
        this.iddistributor = iddistributor;
    }

    public Long getIddistributor() {
        return iddistributor;
    }

    public void setIddistributor(Long iddistributor) {
        this.iddistributor = iddistributor;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getDistribuniqnumber() {
        return distribuniqnumber;
    }

    public void setDistribuniqnumber(Integer distribuniqnumber) {
        this.distribuniqnumber = distribuniqnumber;
    }

    public String getShoplocation() {
        return shoplocation;
    }

    public void setShoplocation(String shoplocation) {
        this.shoplocation = shoplocation;
    }

    public String getCnipic() {
        return cnipic;
    }

    public void setCnipic(String cnipic) {
        this.cnipic = cnipic;
    }

    public String getShoppic() {
        return shoppic;
    }

    public void setShoppic(String shoppic) {
        this.shoppic = shoppic;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public BigInteger getTotalcodesolde() {
        return totalcodesolde;
    }

    public void setTotalcodesolde(BigInteger totalcodesolde) {
        this.totalcodesolde = totalcodesolde;
    }

    public BigInteger getTotaltimesolde() {
        return totaltimesolde;
    }

    public void setTotaltimesolde(BigInteger totaltimesolde) {
        this.totaltimesolde = totaltimesolde;
    }

    public Date getLasttransact() {
        return lasttransact;
    }

    public void setLasttransact(Date lasttransact) {
        this.lasttransact = lasttransact;
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

    public Integer getMarquerstatus() {
        return marquerstatus;
    }

    public void setMarquerstatus(Integer marquerstatus) {
        this.marquerstatus = marquerstatus;
    }

    @XmlTransient
    public Collection<Code> getCodeCollection() {
        return codeCollection;
    }

    public void setCodeCollection(Collection<Code> codeCollection) {
        this.codeCollection = codeCollection;
    }

    public Organization getIdorganization() {
        return idorganization;
    }

    public void setIdorganization(Organization idorganization) {
        this.idorganization = idorganization;
    }

    @XmlTransient
    public Collection<Note> getNoteCollection() {
        return noteCollection;
    }

    public void setNoteCollection(Collection<Note> noteCollection) {
        this.noteCollection = noteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddistributor != null ? iddistributor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distributor)) {
            return false;
        }
        Distributor other = (Distributor) object;
        if ((this.iddistributor == null && other.iddistributor != null) || (this.iddistributor != null && !this.iddistributor.equals(other.iddistributor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Distributor[ iddistributor=" + iddistributor + " ]";
    }
}
