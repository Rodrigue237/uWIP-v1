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
@Table(name = "client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.minId", query = "SELECT c FROM Client c WHERE c.idclient = 1"),
    @NamedQuery(name = "Client.findIdClientByNAme", query = "SELECT c.idclient FROM Client c WHERE c.firstname = :firstname AND c.lastname = :lastname"),
    //@NamedQuery(name = "Client.minId", query = "SELECT MIN(c.idclient) FROM Client c"),
    @NamedQuery(name = "Client.findCopieDouble", query = "SELECT COUNT(c.idclient) FROM Client c WHERE c.firstname = :filtre AND c.lastname = :filtre2"),
    @NamedQuery(name = "Client.findByIdclient", query = "SELECT c FROM Client c WHERE c.idclient = :idclient"),
    @NamedQuery(name = "Client.findByFirstname", query = "SELECT c FROM Client c WHERE c.firstname = :firstname"),
    @NamedQuery(name = "Client.findByLastname", query = "SELECT c FROM Client c WHERE c.lastname = :lastname"),
    @NamedQuery(name = "Client.findByClientuniqnumber", query = "SELECT c FROM Client c WHERE c.clientuniqnumber = :clientuniqnumber"),
    @NamedQuery(name = "Client.findByNationality", query = "SELECT c FROM Client c WHERE c.nationality = :nationality"),
    @NamedQuery(name = "Client.findByTotalbox", query = "SELECT c FROM Client c WHERE c.totalbox = :totalbox"),
    @NamedQuery(name = "Client.findByPreferredphone", query = "SELECT c FROM Client c WHERE c.preferredphone = :preferredphone"),
    @NamedQuery(name = "Client.findByPhoneorange", query = "SELECT c FROM Client c WHERE c.phoneorange = :phoneorange"),
    @NamedQuery(name = "Client.findByPhonemtn", query = "SELECT c FROM Client c WHERE c.phonemtn = :phonemtn"),
    @NamedQuery(name = "Client.findByPhonenexttel", query = "SELECT c FROM Client c WHERE c.phonenexttel = :phonenexttel"),
    @NamedQuery(name = "Client.findByCnipic", query = "SELECT c FROM Client c WHERE c.cnipic = :cnipic"),
    @NamedQuery(name = "Client.findAllOperators", query = "SELECT o FROM Mmoperator o WHERE o.code != :code"),
    @NamedQuery(name = "Client.changeTotalBox", query = "UPDATE Client c SET c.totalbox = (c.totalbox + 1) WHERE c.idclient = :idclient"),
    @NamedQuery(name = "Client.findByAcquisitdate", query = "SELECT c FROM Client c WHERE c.acquisitdate = :acquisitdate")})
public class Client implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idclient")
    private Collection<Credits> creditsCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idclient")
    private Long idclient;
    @Size(max = 254)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 254)
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "clientuniqnumber")
    private Integer clientuniqnumber;
    @Size(max = 254)
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "totalbox")
    private Integer totalbox;
    @Size(max = 254)
    @Column(name = "preferredphone")
    private String preferredphone;
    @Size(max = 254)
    @Column(name = "phoneorange")
    private String phoneorange;
    @Size(max = 254)
    @Column(name = "phonemtn")
    private String phonemtn;
    @Size(max = 254)
    @Column(name = "phonenexttel")
    private String phonenexttel;
    @Size(max = 254)
    @Column(name = "cnipic")
    private String cnipic;
    @Column(name = "acquisitdate")
    @Temporal(TemporalType.DATE)
    private Date acquisitdate;
    @Size(max = 254)
    @Column(name = "signedistinctif")
    private String signedistinctif;
    @Size(max = 254)
    @Column(name = "firstcontact")
    private String firstcontact;
    @Size(max = 254)
    @Column(name = "mobilemoney")
    private String mobilemoney;
    @Size(max = 254)
    @Column(name = "municipality")
    private String municipality;
    @Size(max = 254)
    @Column(name = "village")
    private String village;
    @JoinColumn(name = "idoperator", referencedColumnName = "idoperator")
    @ManyToOne(optional = false)
    private Mmoperator idoperator;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idclient")
    private Collection<Contract> contractCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idclient")
    private Collection<Code> codeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idclient")
    private Collection<Box> boxCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idclient")
    private Collection<Note> noteCollection;

    public Client() {
    }

    public Client(Long idclient) {
        this.idclient = idclient;
    }

    public Long getIdclient() {
        return idclient;
    }

    public void setIdclient(Long idclient) {
        this.idclient = idclient;
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

    public Integer getClientuniqnumber() {
        return clientuniqnumber;
    }

    public void setClientuniqnumber(Integer clientuniqnumber) {
        this.clientuniqnumber = clientuniqnumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getTotalbox() {
        return totalbox;
    }

    public void setTotalbox(Integer totalbox) {
        this.totalbox = totalbox;
    }

    public String getPreferredphone() {
        return preferredphone;
    }

    public void setPreferredphone(String preferredphone) {
        this.preferredphone = preferredphone;
    }

    public String getPhoneorange() {
        return phoneorange;
    }

    public void setPhoneorange(String phoneorange) {
        this.phoneorange = phoneorange;
    }

    public String getPhonemtn() {
        return phonemtn;
    }

    public void setPhonemtn(String phonemtn) {
        this.phonemtn = phonemtn;
    }

    public String getPhonenexttel() {
        return phonenexttel;
    }

    public void setPhonenexttel(String phonenexttel) {
        this.phonenexttel = phonenexttel;
    }

    public String getCnipic() {
        return cnipic;
    }

    public void setCnipic(String cnipic) {
        this.cnipic = cnipic;
    }

    public Date getAcquisitdate() {
        return acquisitdate;
    }

    public void setAcquisitdate(Date acquisitdate) {
        this.acquisitdate = acquisitdate;
    }

    public String getSignedistinctif() {
        return signedistinctif;
    }

    public void setSignedistinctif(String signedistinctif) {
        this.signedistinctif = signedistinctif;
    }

    public String getFirstcontact() {
        return firstcontact;
    }

    public void setFirstcontact(String firstcontact) {
        this.firstcontact = firstcontact;
    }

    public String getMobilemoney() {
        return mobilemoney;
    }

    public void setMobilemoney(String mobilemoney) {
        this.mobilemoney = mobilemoney;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public Mmoperator getIdoperator() {
        return idoperator;
    }

    public void setIdoperator(Mmoperator idoperator) {
        this.idoperator = idoperator;
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

    @XmlTransient
    public Collection<Box> getBoxCollection() {
        return boxCollection;
    }

    public void setBoxCollection(Collection<Box> boxCollection) {
        this.boxCollection = boxCollection;
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
        hash += (idclient != null ? idclient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.idclient == null && other.idclient != null) || (this.idclient != null && !this.idclient.equals(other.idclient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Client[ idclient=" + idclient + " ]";
    }

    @XmlTransient
    public Collection<Credits> getCreditsCollection() {
        return creditsCollection;
    }

    public void setCreditsCollection(Collection<Credits> creditsCollection) {
        this.creditsCollection = creditsCollection;
    }
}
