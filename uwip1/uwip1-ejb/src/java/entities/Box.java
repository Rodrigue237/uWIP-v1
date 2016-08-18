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
@Table(name = "box")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Box.findAll", query = "SELECT b FROM Box b"),
    @NamedQuery(name = "Box.findCopieDouble", query = "SELECT COUNT(b.idboxe) FROM Box b WHERE b.refbox = :filtre"),
    @NamedQuery(name = "Box.findNumPcb", query = "SELECT b.numpcb FROM Box b WHERE b.numbox = :numbox"),
    @NamedQuery(name = "Box.findByIdboxe", query = "SELECT b FROM Box b WHERE b.idboxe = :idboxe"),
    @NamedQuery(name = "Box.findByRefbox", query = "SELECT b FROM Box b WHERE b.refbox = :refbox"),
    @NamedQuery(name = "Box.findByNumbox", query = "SELECT b FROM Box b WHERE b.numbox = :numbox"),
    @NamedQuery(name = "Box.findByHardwareversion", query = "SELECT b FROM Box b WHERE b.hardwareversion = :hardwareversion"),
    @NamedQuery(name = "Box.findBySoftwareversion", query = "SELECT b FROM Box b WHERE b.softwareversion = :softwareversion"),
    @NamedQuery(name = "Box.findByCurrentlocation", query = "SELECT b FROM Box b WHERE b.currentlocation = :currentlocation"),
    @NamedQuery(name = "Box.findByLatitude", query = "SELECT b FROM Box b WHERE b.latitude = :latitude"),
    @NamedQuery(name = "Box.findByLongitude", query = "SELECT b FROM Box b WHERE b.longitude = :longitude"),
    @NamedQuery(name = "Box.findByCountry", query = "SELECT b FROM Box b WHERE b.country = :country"),
    @NamedQuery(name = "Box.findByFirstinstaldate", query = "SELECT b FROM Box b WHERE b.firstinstaldate = :firstinstaldate"),
    @NamedQuery(name = "Box.findByLastinstaldate", query = "SELECT b FROM Box b WHERE b.lastinstaldate = :lastinstaldate"),
    @NamedQuery(name = "Box.findByEstimtotaltimeadd", query = "SELECT b FROM Box b WHERE b.estimtotaltimeadd = :estimtotaltimeadd"),
    @NamedQuery(name = "Box.findByGenekeyactiv", query = "SELECT b FROM Box b WHERE b.genekeyactiv = :genekeyactiv"),
    @NamedQuery(name = "Box.findByLasttransact", query = "SELECT b FROM Box b WHERE b.lasttransact = :lasttransact"),
    @NamedQuery(name = "Box.findByLasttimecodevalue", query = "SELECT b FROM Box b WHERE b.lasttimecodevalue = :lasttimecodevalue"),
    @NamedQuery(name = "Box.findByTotaltimepaid", query = "SELECT b FROM Box b WHERE b.totaltimepaid = :totaltimepaid"),
    @NamedQuery(name = "Box.findByEstimlefttimepaid", query = "SELECT b FROM Box b WHERE b.estimlefttimepaid = :estimlefttimepaid"),
    @NamedQuery(name = "Box.findByLastmaintenance", query = "SELECT b FROM Box b WHERE b.lastmaintenance = :lastmaintenance"),
    @NamedQuery(name = "Box.findByMaintenancestatus", query = "SELECT b FROM Box b WHERE b.maintenancestatus = :maintenancestatus"),
    @NamedQuery(name = "Box.findByMarquerstatus", query = "SELECT b FROM Box b WHERE b.marquerstatus = :marquerstatus"),
    @NamedQuery(name = "Box.findByMapped", query = "SELECT b FROM Box b WHERE b.marquerstatus = 1"),
    @NamedQuery(name = "Box.findByMaintenance", query = "SELECT b FROM Box b ORDER By b.marquerstatus"),
    @NamedQuery(name = "Box.findByIndispo", query = "SELECT b FROM Box b WHERE b.atelier != :atelier AND b.marquerstatus > 1"),
    @NamedQuery(name = "Box.findByLibre", query = "SELECT b FROM Box b WHERE b.marquerstatus = 0"),
    @NamedQuery(name = "Box.changeStatusMarquer", query = "UPDATE Box b SET b.marquerstatus = 1, b.atelier = :atelier WHERE b.idboxe = :idboxe"),
    @NamedQuery(name = "Box.changeStatusMarquer1", query = "UPDATE Box b SET b.marquerstatus = 2, b.atelier = :atelier, b.numbox = :numbox WHERE b.idboxe = :idboxe"),
    @NamedQuery(name = "Box.desactivateClientNameBox", query = "UPDATE Box b SET b.idclient = :idclient WHERE b.idboxe = :idboxe"),
    @NamedQuery(name = "Box.initNumBox", query = "UPDATE Box b SET b.numbox = :numbox WHERE b.idboxe = :idboxe"),
    @NamedQuery(name = "Box.changeLatiAndLongi", query = "UPDATE Box b SET b.latitude = :latitude, b.longitude = :longitude WHERE b.idboxe = :idboxe"),
    @NamedQuery(name = "Box.changeClientNameBox", query = "UPDATE Box b SET b.idclient = :idclient WHERE b.idboxe = :idboxe"),
    @NamedQuery(name = "Box.changeOrganizationNameBox", query = "UPDATE Box b SET b.idorganization = :idorganization WHERE b.idboxe = :idboxe"),
    @NamedQuery(name = "Box.remplaceBox", query = "UPDATE Box b SET b.numbox = :numbox, b.latitude = :latitude, b.longitude = :longitude, b.country = :country, b.totalinstall = :totalinstall, b.idlastcontract = :idlastcontract,b.lastinstaldate = :lastinstaldate, b.lasttransact = :lasttransact, b.lasttimecodevalue = :lasttimecodevalue, b.totaltimepaid = :totaltimepaid, b.estimlefttimepaid = :estimlefttimepaid, b.marquerstatus = :marquerstatus, b.userofsystem = :userofsystem, b.estimatedenddate = :estimatedenddate, b.marquerstyle = :marquerstyle, b.atelier = :atelier, b.idtownlayer = :idtownlayer, b.idorganization = :idorganization, b.idclient = :idclient WHERE b.idboxe = :idboxe"),
    @NamedQuery(name = "Box.findIdClientOut", query = "SELECT b.idclient FROM Box b WHERE b.numbox = :numbox"),
    @NamedQuery(name = "Box.findIdOrganizationOut", query = "SELECT b.idorganization FROM Box b WHERE b.numbox = :numbox"),
    @NamedQuery(name = "Box.findIdTownlayerOut", query = "SELECT b.idtownlayer FROM Box b WHERE b.numbox = :numbox"),
    @NamedQuery(name = "Box.findKey1", query = "SELECT b.kpi1 FROM Box b WHERE b.idboxe = :idboxe"),
    @NamedQuery(name = "Box.findKey2", query = "SELECT b.kpi2 FROM Box b WHERE b.idboxe = :idboxe"),
    @NamedQuery(name = "Box.findKey3", query = "SELECT b.kpi3 FROM Box b WHERE b.idboxe = :idboxe"),
    @NamedQuery(name = "Box.findNumBox", query = "SELECT b.numbox FROM Box b WHERE b.idboxe = :idboxe"),
    @NamedQuery(name = "Box.findByKpi1", query = "SELECT b FROM Box b WHERE b.kpi1 = :kpi1"),
    @NamedQuery(name = "Box.findByKpi2", query = "SELECT b FROM Box b WHERE b.kpi2 = :kpi2"),
    @NamedQuery(name = "Box.findByKpi3", query = "SELECT b FROM Box b WHERE b.kpi3 = :kpi3"),
    @NamedQuery(name = "Box.findByNumpcb", query = "SELECT b FROM Box b WHERE b.numpcb = :numpcb"),
    @NamedQuery(name = "Box.findByUserofsystem", query = "SELECT b FROM Box b WHERE b.userofsystem = :userofsystem"),
    @NamedQuery(name = "Box.findByMarquerstyle", query = "SELECT b FROM Box b WHERE b.marquerstyle = :marquerstyle"),
    @NamedQuery(name = "Box.findByAtelier", query = "SELECT b FROM Box b WHERE b.atelier = :atelier"),
    @NamedQuery(name = "Box.changeTotalInstall", query = "UPDATE Box b SET b.totalinstall = :totalinstall, b.userofsystem = :userofsystem, b.idlastcontract = :idlastcontract, b.phoneofusersystem = :phoneofusersystem WHERE b.idboxe = :idboxe"),
    @NamedQuery(name = "Box.updateFirstInstallDate", query = "UPDATE Box b SET b.firstinstaldate = :firstinstaldate WHERE b.idboxe = :idboxe"),
    @NamedQuery(name = "Box.updateLastInstallDate", query = "UPDATE Box b SET b.lastinstaldate = :lastinstaldate WHERE b.idboxe = :idboxe"),
    @NamedQuery(name = "Box.findTotalInstall", query = "SELECT b.totalinstall FROM Box b WHERE b.idboxe = :idboxe"),
    @NamedQuery(name = "Box.findByTotalinstall", query = "SELECT b FROM Box b WHERE b.totalinstall = :totalinstall")})
public class Box implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idboxe")
    private Long idboxe;
    @Size(max = 254)
    @Column(name = "refbox")
    private String refbox;
    @Column(name = "numbox")
    private Integer numbox;
    @Size(max = 254)
    @Column(name = "hardwareversion")
    private String hardwareversion;
    @Size(max = 254)
    @Column(name = "softwareversion")
    private String softwareversion;
    @Size(max = 2147483647)
    @Column(name = "currentlocation")
    private String currentlocation;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Size(max = 254)
    @Column(name = "country")
    private String country;
    @Column(name = "firstinstaldate")
    @Temporal(TemporalType.DATE)
    private Date firstinstaldate;
    @Column(name = "lastinstaldate")
    @Temporal(TemporalType.DATE)
    private Date lastinstaldate;
    @Column(name = "estimtotaltimeadd")
    private BigInteger estimtotaltimeadd;
    @Column(name = "genekeyactiv")
    private Integer genekeyactiv;
    @Column(name = "lasttransact")
    @Temporal(TemporalType.DATE)
    private Date lasttransact;
    @Column(name = "lasttimecodevalue")
    private BigInteger lasttimecodevalue;
    @Column(name = "totaltimepaid")
    private BigInteger totaltimepaid;
    @Column(name = "estimlefttimepaid")
    private BigInteger estimlefttimepaid;
    @Column(name = "lastmaintenance")
    @Temporal(TemporalType.DATE)
    private Date lastmaintenance;
    @Column(name = "maintenancestatus")
    private Integer maintenancestatus;
    @Column(name = "marquerstatus")
    private Integer marquerstatus;
    @Column(name = "kpi1")
    private Integer kpi1;
    @Column(name = "kpi2")
    private Integer kpi2;
    @Column(name = "kpi3")
    private Integer kpi3;
    @Column(name = "numpcb")
    private Integer numpcb;
    @Size(max = 254)
    @Column(name = "userofsystem")
    private String userofsystem;
    @Size(max = 254)
    @Column(name = "marquerstyle")
    private String marquerstyle;
    @Size(max = 254)
    @Column(name = "atelier")
    private String atelier;
    @Column(name = "totalinstall")
    private Integer totalinstall;
    @Column(name = "phoneofusersystem")
    private Integer phoneofusersystem;
    @Column(name = "estimatedenddate")
    @Temporal(TemporalType.DATE)
    private Date estimatedenddate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idboxe")
    private Collection<Contract> contractCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idboxe")
    private Collection<Code> codeCollection;
    @JoinColumn(name = "idtownlayer", referencedColumnName = "idtownlayer")
    @ManyToOne(optional = false)
    private Townlayer idtownlayer;
    @JoinColumn(name = "idsoftvers", referencedColumnName = "idsoftvers")
    @ManyToOne(optional = false)
    private Softvers idsoftvers;
    @JoinColumn(name = "idorganization", referencedColumnName = "idorganization")
    @ManyToOne(optional = false)
    private Organization idorganization;
    @JoinColumn(name = "idhardvers", referencedColumnName = "idhardvers")
    @ManyToOne(optional = false)
    private Hardvers idhardvers;
    @Column(name = "idlastcontract")
    private Integer idlastcontract;
    @JoinColumn(name = "idclient", referencedColumnName = "idclient")
    @ManyToOne(optional = false)
    private Client idclient;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idboxe")
    private Collection<Note> noteCollection;

    public Box() {
    }

    public Box(Long idboxe) {
        this.idboxe = idboxe;
    }

    public Long getIdboxe() {
        return idboxe;
    }

    public void setIdboxe(Long idboxe) {
        this.idboxe = idboxe;
    }

    public String getRefbox() {
        return refbox;
    }

    public void setRefbox(String refbox) {
        this.refbox = refbox;
    }

    public Integer getNumbox() {
        return numbox;
    }

    public void setNumbox(Integer numbox) {
        this.numbox = numbox;
    }

    public String getHardwareversion() {
        return hardwareversion;
    }

    public void setHardwareversion(String hardwareversion) {
        this.hardwareversion = hardwareversion;
    }

    public String getSoftwareversion() {
        return softwareversion;
    }

    public void setSoftwareversion(String softwareversion) {
        this.softwareversion = softwareversion;
    }

    public String getCurrentlocation() {
        return currentlocation;
    }

    public void setCurrentlocation(String currentlocation) {
        this.currentlocation = currentlocation;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getFirstinstaldate() {
        return firstinstaldate;
    }

    public void setFirstinstaldate(Date firstinstaldate) {
        this.firstinstaldate = firstinstaldate;
    }

    public Date getLastinstaldate() {
        return lastinstaldate;
    }

    public void setLastinstaldate(Date lastinstaldate) {
        this.lastinstaldate = lastinstaldate;
    }

    public BigInteger getEstimtotaltimeadd() {
        return estimtotaltimeadd;
    }

    public void setEstimtotaltimeadd(BigInteger estimtotaltimeadd) {
        this.estimtotaltimeadd = estimtotaltimeadd;
    }

    public Integer getGenekeyactiv() {
        return genekeyactiv;
    }

    public void setGenekeyactiv(Integer genekeyactiv) {
        this.genekeyactiv = genekeyactiv;
    }

    public Date getLasttransact() {
        return lasttransact;
    }

    public void setLasttransact(Date lasttransact) {
        this.lasttransact = lasttransact;
    }

    public BigInteger getLasttimecodevalue() {
        return lasttimecodevalue;
    }

    public void setLasttimecodevalue(BigInteger lasttimecodevalue) {
        this.lasttimecodevalue = lasttimecodevalue;
    }

    public BigInteger getTotaltimepaid() {
        return totaltimepaid;
    }

    public void setTotaltimepaid(BigInteger totaltimepaid) {
        this.totaltimepaid = totaltimepaid;
    }

    public BigInteger getEstimlefttimepaid() {
        return estimlefttimepaid;
    }

    public void setEstimlefttimepaid(BigInteger estimlefttimepaid) {
        this.estimlefttimepaid = estimlefttimepaid;
    }

    public Date getLastmaintenance() {
        return lastmaintenance;
    }

    public void setLastmaintenance(Date lastmaintenance) {
        this.lastmaintenance = lastmaintenance;
    }

    public Integer getMaintenancestatus() {
        return maintenancestatus;
    }

    public void setMaintenancestatus(Integer maintenancestatus) {
        this.maintenancestatus = maintenancestatus;
    }

    public Integer getMarquerstatus() {
        return marquerstatus;
    }

    public void setMarquerstatus(Integer marquerstatus) {
        this.marquerstatus = marquerstatus;
    }

    public Integer getKpi1() {
        return kpi1;
    }

    public void setKpi1(Integer kpi1) {
        this.kpi1 = kpi1;
    }

    public Integer getKpi2() {
        return kpi2;
    }

    public void setKpi2(Integer kpi2) {
        this.kpi2 = kpi2;
    }

    public Integer getKpi3() {
        return kpi3;
    }

    public void setKpi3(Integer kpi3) {
        this.kpi3 = kpi3;
    }

    public Integer getNumpcb() {
        return numpcb;
    }

    public void setNumpcb(Integer numpcb) {
        this.numpcb = numpcb;
    }

    public String getUserofsystem() {
        return userofsystem;
    }

    public void setUserofsystem(String userofsystem) {
        this.userofsystem = userofsystem;
    }

    public String getMarquerstyle() {
        return marquerstyle;
    }

    public void setMarquerstyle(String marquerstyle) {
        this.marquerstyle = marquerstyle;
    }

    public String getAtelier() {
        return atelier;
    }

    public void setAtelier(String atelier) {
        this.atelier = atelier;
    }

    public Integer getTotalinstall() {
        return totalinstall;
    }

    public void setTotalinstall(Integer totalinstall) {
        this.totalinstall = totalinstall;
    }

    public Date getEstimatedenddate() {
        return estimatedenddate;
    }

    public void setEstimatedenddate(Date estimatedenddate) {
        this.estimatedenddate = estimatedenddate;
    }

    public Integer getIdlastcontract() {
        return idlastcontract;
    }

    public void setIdlastcontract(Integer idlastcontract) {
        this.idlastcontract = idlastcontract;
    }

    public Integer getPhoneofusersystem() {
        return phoneofusersystem;
    }

    public void setPhoneofusersystem(Integer phoneofusersystem) {
        this.phoneofusersystem = phoneofusersystem;
    }

    @XmlTransient
    public Collection<Contract> getContractCollection() {
        return contractCollection;
    }

    public void setContractCollection(Collection<Contract> contractCollection) {
        this.contractCollection = contractCollection;
    }

    @XmlTransient
    public Collection<Code> getCodeCollection() {
        return codeCollection;
    }

    public void setCodeCollection(Collection<Code> codeCollection) {
        this.codeCollection = codeCollection;
    }

    public Townlayer getIdtownlayer() {
        return idtownlayer;
    }

    public void setIdtownlayer(Townlayer idtownlayer) {
        this.idtownlayer = idtownlayer;
    }

    public Softvers getIdsoftvers() {
        return idsoftvers;
    }

    public void setIdsoftvers(Softvers idsoftvers) {
        this.idsoftvers = idsoftvers;
    }

    public Organization getIdorganization() {
        return idorganization;
    }

    public void setIdorganization(Organization idorganization) {
        this.idorganization = idorganization;
    }

    public Hardvers getIdhardvers() {
        return idhardvers;
    }

    public void setIdhardvers(Hardvers idhardvers) {
        this.idhardvers = idhardvers;
    }

    public Client getIdclient() {
        return idclient;
    }

    public void setIdclient(Client idclient) {
        this.idclient = idclient;
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
        hash += (idboxe != null ? idboxe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Box)) {
            return false;
        }
        Box other = (Box) object;
        if ((this.idboxe == null && other.idboxe != null) || (this.idboxe != null && !this.idboxe.equals(other.idboxe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Box[ idboxe=" + idboxe + " ]";
    }
}
