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
@Table(name = "contract")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contract.findAll", query = "SELECT c FROM Contract c"),
    @NamedQuery(name = "Contract.maxContractId", query = "SELECT MAX(c.idcontract) FROM Contract c"),
    @NamedQuery(name = "Contract.lastClientContract", query = "SELECT c.idclient FROM Contract c WHERE c.idcontract = :idcontract"),
    @NamedQuery(name = "Contract.lastBoxContract", query = "SELECT c.idboxe FROM Contract c WHERE c.idcontract = :idcontract"),
    @NamedQuery(name = "Contract.findByIdcontract", query = "SELECT c FROM Contract c WHERE c.idcontract = :idcontract"),
    @NamedQuery(name = "Contract.findByDatecontra", query = "SELECT c FROM Contract c WHERE c.datecontra = :datecontra"),
    @NamedQuery(name = "Contract.findByTotalmoneypaid", query = "SELECT c FROM Contract c WHERE c.totalmoneypaid = :totalmoneypaid"),
    @NamedQuery(name = "Contract.findByTotalmoneydue", query = "SELECT c FROM Contract c WHERE c.totalmoneydue = :totalmoneydue"),
    @NamedQuery(name = "Contract.findByMoneybalance", query = "SELECT c FROM Contract c WHERE c.moneybalance = :moneybalance"),
    @NamedQuery(name = "Contract.findByContractpic", query = "SELECT c FROM Contract c WHERE c.contractpic = :contractpic"),
    @NamedQuery(name = "Contract.findByHomepic", query = "SELECT c FROM Contract c WHERE c.homepic = :homepic"),
    @NamedQuery(name = "Contract.findByVillage", query = "SELECT c FROM Contract c WHERE c.village = :village"),
    @NamedQuery(name = "Contract.findByMunicipality", query = "SELECT c FROM Contract c WHERE c.municipality = :municipality"),
    @NamedQuery(name = "Contract.updateDesinstallDate", query = "UPDATE Contract c SET c.desinstalldate = :desinstalldate, c.installstatus = :installstatus WHERE c.idcontract = :idcontract"),
    @NamedQuery(name = "Contract.balanceUpdate", query = "UPDATE Contract c SET c.moneybalance = :moneybalance WHERE c.idcontract = :idcontract"),
    @NamedQuery(name = "Contract.findByEstimnexttransact", query = "SELECT c FROM Contract c WHERE c.estimnexttransact = :estimnexttransact")})
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontract")
    private Long idcontract;
    @Column(name = "datecontra")
    @Temporal(TemporalType.DATE)
    private Date datecontra;
    @Column(name = "totalmoneypaid")
    private Double totalmoneypaid;
    @Column(name = "totalmoneydue")
    private Double totalmoneydue;
    @Column(name = "moneybalance")
    private Double moneybalance;
    @Size(max = 2147483647)
    @Column(name = "contractpic")
    private String contractpic;
    @Size(max = 2147483647)
    @Column(name = "homepic")
    private String homepic;
    @Size(max = 2147483647)
    @Column(name = "village")
    private String village;
    @Size(max = 2147483647)
    @Column(name = "municipality")
    private String municipality;
    @Column(name = "estimnexttransact")
    @Temporal(TemporalType.DATE)
    private Date estimnexttransact;
    @Size(max = 254)
    @Column(name = "typeinstall")
    private String typeinstall;
    @Size(max = 254)
    @Column(name = "natconnect")
    private String natconnect;
    @Size(max = 254)
    @Column(name = "installstatus")
    private String installstatus;
    @Size(max = 254)
    @Column(name = "userofsystem")
    private String userofsystem;
    @Column(name = "phoneofusersystem")
    private Integer phoneofusersystem;
    @Column(name = "numusersystem")
    private Integer numusersystem;
    @Size(max = 2147483647)
    @Column(name = "typecontrat")
    private String typecontrat;
    @Column(name = "installdate")
    @Temporal(TemporalType.DATE)
    private Date installdate;
    @Column(name = "desinstalldate")
    @Temporal(TemporalType.DATE)
    private Date desinstalldate;
    @JoinColumn(name = "idclient", referencedColumnName = "idclient")
    @ManyToOne(optional = false)
    private Client idclient;
    @JoinColumn(name = "idboxe", referencedColumnName = "idboxe")
    @ManyToOne(optional = false)
    private Box idboxe;

    public Contract() {
    }

    public Contract(Long idcontract) {
        this.idcontract = idcontract;
    }

    public Long getIdcontract() {
        return idcontract;
    }

    public void setIdcontract(Long idcontract) {
        this.idcontract = idcontract;
    }

    public Date getDatecontra() {
        return datecontra;
    }

    public void setDatecontra(Date datecontra) {
        this.datecontra = datecontra;
    }

    public Double getTotalmoneypaid() {
        return totalmoneypaid;
    }

    public void setTotalmoneypaid(Double totalmoneypaid) {
        this.totalmoneypaid = totalmoneypaid;
    }

    public Double getTotalmoneydue() {
        return totalmoneydue;
    }

    public void setTotalmoneydue(Double totalmoneydue) {
        this.totalmoneydue = totalmoneydue;
    }

    public Double getMoneybalance() {
        return moneybalance;
    }

    public void setMoneybalance(Double moneybalance) {
        this.moneybalance = moneybalance;
    }

    public String getContractpic() {
        return contractpic;
    }

    public void setContractpic(String contractpic) {
        this.contractpic = contractpic;
    }

    public String getHomepic() {
        return homepic;
    }

    public void setHomepic(String homepic) {
        this.homepic = homepic;
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

    public Date getEstimnexttransact() {
        return estimnexttransact;
    }

    public void setEstimnexttransact(Date estimnexttransact) {
        this.estimnexttransact = estimnexttransact;
    }

    public String getTypeinstall() {
        return typeinstall;
    }

    public void setTypeinstall(String typeinstall) {
        this.typeinstall = typeinstall;
    }

    public String getNatconnect() {
        return natconnect;
    }

    public void setNatconnect(String natconnect) {
        this.natconnect = natconnect;
    }

    public String getInstallstatus() {
        return installstatus;
    }

    public void setInstallstatus(String installstatus) {
        this.installstatus = installstatus;
    }

    public String getUserofsystem() {
        return userofsystem;
    }

    public void setUserofsystem(String userofsystem) {
        this.userofsystem = userofsystem;
    }

    public Integer getPhoneofusersystem() {
        return phoneofusersystem;
    }

    public void setPhoneofusersystem(Integer phoneofusersystem) {
        this.phoneofusersystem = phoneofusersystem;
    }

    public Date getInstalldate() {
        return installdate;
    }

    public void setInstalldate(Date installdate) {
        this.installdate = installdate;
    }

    public Date getDesinstalldate() {
        return desinstalldate;
    }

    public void setDesinstalldate(Date desinstalldate) {
        this.desinstalldate = desinstalldate;
    }

    public Integer getNumusersystem() {
        return numusersystem;
    }

    public void setNumusersystem(Integer numusersystem) {
        this.numusersystem = numusersystem;
    }

    public String getTypecontrat() {
        return typecontrat;
    }

    public void setTypecontrat(String typecontrat) {
        this.typecontrat = typecontrat;
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
        hash += (idcontract != null ? idcontract.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contract)) {
            return false;
        }
        Contract other = (Contract) object;
        if ((this.idcontract == null && other.idcontract != null) || (this.idcontract != null && !this.idcontract.equals(other.idcontract))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Contract[ idcontract=" + idcontract + " ]";
    }
}
