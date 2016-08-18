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
@Table(name = "credits")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Credits.findAll", query = "SELECT c FROM Credits c ORDER By c.numinstall"),
    @NamedQuery(name = "Credits.findAllCredits", query = "SELECT c FROM Credits c ORDER By c.numinstall"),
    @NamedQuery(name = "Credits.findOmniBoxByNuminstall", query = "SELECT c FROM Credits c where c.numinstall = :numinstall"),
    @NamedQuery(name = "Credits.findCopieDouble", query = "SELECT COUNT(c.idcredits) FROM Credits c WHERE c.numinstall = :filtre"),
    @NamedQuery(name = "Credits.findOccurenceTermes", query = "SELECT COUNT(c.idcredits) FROM Credits c WHERE c.idtermes = :idtermes"),
    @NamedQuery(name = "Credits.findByIdcredits", query = "SELECT c FROM Credits c WHERE c.idcredits = :idcredits"),
    @NamedQuery(name = "Credits.findClientForSMS", query = "SELECT c.idclient FROM Credits c WHERE c.numinstall = :numinstall"),
    @NamedQuery(name = "Credits.findByNuminstall", query = "SELECT c FROM Credits c WHERE c.numinstall = :numinstall"),
    @NamedQuery(name = "Credits.findByNomclient", query = "SELECT c FROM Credits c WHERE c.nomclient = :nomclient"),
    @NamedQuery(name = "Credits.findByQteappsupcredit1", query = "SELECT c FROM Credits c WHERE c.qteappsupcredit1 = :qteappsupcredit1"),
    @NamedQuery(name = "Credits.findByQteappsupcredit2", query = "SELECT c FROM Credits c WHERE c.qteappsupcredit2 = :qteappsupcredit2"),
    @NamedQuery(name = "Credits.findByQteappsupcredit3", query = "SELECT c FROM Credits c WHERE c.qteappsupcredit3 = :qteappsupcredit3"),
    @NamedQuery(name = "Credits.findByQteappsupcash1", query = "SELECT c FROM Credits c WHERE c.qteappsupcash1 = :qteappsupcash1"),
    @NamedQuery(name = "Credits.findByQteappsupcash2", query = "SELECT c FROM Credits c WHERE c.qteappsupcash2 = :qteappsupcash2"),
    @NamedQuery(name = "Credits.findByQteappsupcash3", query = "SELECT c FROM Credits c WHERE c.qteappsupcash3 = :qteappsupcash3"),
    @NamedQuery(name = "Credits.findByTotalcredits", query = "SELECT c FROM Credits c WHERE c.totalcredits = :totalcredits"),
    @NamedQuery(name = "Credits.findByAcomptecredit", query = "SELECT c FROM Credits c WHERE c.acomptecredit = :acomptecredit"),
    @NamedQuery(name = "Credits.findByAcomptetotal", query = "SELECT c FROM Credits c WHERE c.acomptetotal = :acomptetotal"),
    @NamedQuery(name = "Credits.findByPrix1mois", query = "SELECT c FROM Credits c WHERE c.prix1mois = :prix1mois"),
    @NamedQuery(name = "Credits.findByPrix1jour", query = "SELECT c FROM Credits c WHERE c.prix1jour = :prix1jour"),
    @NamedQuery(name = "Credits.findByTotalmontantpaye", query = "SELECT c FROM Credits c WHERE c.totalmontantpaye = :totalmontantpaye"),
    @NamedQuery(name = "Credits.findByTotalmontantrestant", query = "SELECT c FROM Credits c WHERE c.totalmontantrestant = :totalmontantrestant"),
    @NamedQuery(name = "Credits.findByTotalgraceused", query = "SELECT c FROM Credits c WHERE c.totalgraceused = :totalgraceused"),
    @NamedQuery(name = "Credits.findByTotalgracerestant", query = "SELECT c FROM Credits c WHERE c.totalgracerestant = :totalgracerestant"),
    @NamedQuery(name = "Credits.findByCreditstatus", query = "SELECT c FROM Credits c WHERE c.creditstatus = :creditstatus"),
    @NamedQuery(name = "Credits.findByCreditexpirationdate", query = "SELECT c FROM Credits c WHERE c.creditexpirationdate = :creditexpirationdate"),
    @NamedQuery(name = "Credits.findByInstallstatus", query = "SELECT c FROM Credits c WHERE c.installstatus = :installstatus"),
    @NamedQuery(name = "Credits.findPAYGidprod", query = "SELECT c.idpaygproduct FROM Credits c WHERE c.numinstall = :numbox"),
    @NamedQuery(name = "Credits.findPrix1Jour", query = "SELECT c.prix1jour FROM Credits c WHERE c.numinstall = :numbox"),
    @NamedQuery(name = "Credits.findCreditStatus", query = "SELECT c.creditstatus FROM Credits c WHERE c.numinstall = :numbox"),
    @NamedQuery(name = "Credits.findCreditStatusByPAYG", query = "SELECT c.creditstatus FROM Credits c WHERE c.idpaygproduct = :idpaygproduct"),
    @NamedQuery(name = "Credits.findMontantPayee", query = "SELECT c.totalmontantpaye FROM Credits c WHERE c.numinstall = :numbox"),
    @NamedQuery(name = "Credits.findTotalOfCredits", query = "SELECT c.totalcredits FROM Credits c WHERE c.numinstall = :numbox"),
    @NamedQuery(name = "Credits.findMontantRestant", query = "SELECT c.totalmontantrestant FROM Credits c WHERE c.numinstall = :numbox"),
    @NamedQuery(name = "Credits.findAcompteCreditNow", query = "SELECT c.acomptecredit FROM Credits c WHERE c.numinstall = :numbox"),
    @NamedQuery(name = "Credits.findAcompteTotalNow", query = "SELECT c.acomptetotal FROM Credits c WHERE c.numinstall = :numbox"),
    @NamedQuery(name = "Credits.findNbreJrGraceRestNow", query = "SELECT c.totalgracerestant FROM Credits c WHERE c.numinstall = :numbox"),
    @NamedQuery(name = "Credits.findNbreJrGraceUsedNow", query = "SELECT c.totalgraceused FROM Credits c WHERE c.numinstall = :numbox"),
    @NamedQuery(name = "Credits.findCodeEndDateNow", query = "SELECT c.codeenddate FROM Credits c WHERE c.numinstall = :numbox"),
    @NamedQuery(name = "Credits.findNumInstallForFirstCode", query = "SELECT c.numinstall FROM Credits c WHERE c.idpaygproduct = :idpaygproduct"),
    @NamedQuery(name = "Credits.updateLastCode", query = "UPDATE Credits c SET c.lastcode = :lastcode WHERE c.numinstall = :numinstall"),
    @NamedQuery(name = "Credits.updateMontantPayeAndRest", query = "UPDATE Credits c SET c.totalmontantpaye = :totalmontantpaye, c.totalmontantrestant = :totalmontantrestant WHERE c.numinstall = :numinstall"),
    @NamedQuery(name = "Credits.updateGraceRestant", query = "UPDATE Credits c SET c.totalgracerestant = :totalgracerestant WHERE c.numinstall = :numinstall"),
    @NamedQuery(name = "Credits.updateGraceUsed", query = "UPDATE Credits c SET c.totalgraceused = :totalgraceused WHERE c.numinstall = :numinstall"),
    @NamedQuery(name = "Credits.updateGraceAndEndDate", query = "UPDATE Credits c SET c.totalgraceused = :totalgraceused, c.totalgracerestant = :totalgracerestant, c.codeenddate = :codeenddate, c.creditstatus = :creditstatus WHERE c.numinstall = :numinstall"),
    @NamedQuery(name = "Credits.updateEndDate", query = "UPDATE Credits c SET c.codeenddate = :codeenddate WHERE c.numinstall = :numinstall"),
    @NamedQuery(name = "Credits.updateDateInstall", query = "UPDATE Credits c SET c.installdate = :installdate WHERE c.numinstall = :numinstall"),
    @NamedQuery(name = "Credits.updateCodeEndDate", query = "UPDATE Credits c SET c.codeenddate = :codeenddate WHERE c.numinstall = :numinstall"),
    @NamedQuery(name = "Credits.updatemontantPaye", query = "UPDATE Credits c SET c.totalmontantpaye = :totalmontantpaye WHERE c.numinstall = :numbox"),
    @NamedQuery(name = "Credits.updateCreditStatus", query = "UPDATE Credits c SET c.creditstatus = :creditstatus WHERE c.numinstall = :numinstall"),
    @NamedQuery(name = "Credits.findByPAYGprodID", query = "SELECT c FROM Credits c WHERE c.idpaygproduct = :idpaygproduct"),
    @NamedQuery(name = "Credits.updateCredStatAfterAutoCDT", query = "UPDATE Credits c SET c.creditstatus = :creditstatus WHERE c.idpaygproduct = :idpaygproduct"),
    @NamedQuery(name = "Credits.updateCredStatusAfterNewInst", query = "UPDATE Credits c SET c.creditstatus = :creditstatus WHERE c.idpaygproduct = :idpaygproduct"),
    @NamedQuery(name = "Credits.findAllFreeCredits", query = "SELECT c FROM Credits c WHERE c.creditstatus = :creditstatus ORDER By c.numinstall"),
    @NamedQuery(name = "Credits.findAllInstalledCredits", query = "SELECT c FROM Credits c WHERE c.creditstatus = :creditstatusActiv OR c.creditstatus = :creditstatusDesactiv OR c.creditstatus = :creditstatusWaitAcompt OR c.creditstatus = :creditstatusWaitInstall ORDER By c.numinstall"),
    @NamedQuery(name = "Credits.findAllValidForMaintenance", query = "SELECT c FROM Credits c WHERE c.creditstatus = :creditstatusActiv OR c.creditstatus = :creditstatusDesactiv OR c.creditstatus = :creditstatusRembourse ORDER By c.numinstall"),
    @NamedQuery(name = "Credits.updateCreditsNewContract", query = "UPDATE Credits c SET c.idclient = :idclient, c.idproduits = :idproduits, c.idtermes = :idtermes WHERE c.numinstall = :numinstall"),
    @NamedQuery(name = "Credits.updateCredNewContr1", query = "UPDATE Credits c SET c.acomptecredit = :acomptecredit, c.acomptetotal = :acomptetotal, c.prix1jour = :prix1jour, c.prix1mois = :prix1mois, c.qteappsupcash1 = :qteappsupcash1, c.qteappsupcash2 = :qteappsupcash2, c.qteappsupcash3 = :qteappsupcash3, c.qteappsupcredit1 = :qteappsupcredit1, c.qteappsupcredit2 = :qteappsupcredit2, c.qteappsupcredit3 = :qteappsupcredit3, c.totalcredits = :totalcredits, c.totalmontantpaye = :totalmontantpaye, c.totalmontantrestant = :totalmontantrestant, c.creditstatus = :creditstatus WHERE c.numinstall = :numinstall"),
    @NamedQuery(name = "Credits.findByInstalldate", query = "SELECT c FROM Credits c WHERE c.installdate = :installdate")})
public class Credits implements Serializable {

    @Column(name = "codeenddate")
    @Temporal(TemporalType.DATE)
    private Date codeenddate;
    @JoinColumn(name = "idclient", referencedColumnName = "idclient")
    @ManyToOne(optional = false)
    private Client idclient;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcredits")
    private Long idcredits;
    @Column(name = "numinstall")
    private Integer numinstall;
    @Size(max = 254)
    @Column(name = "nomclient")
    private String nomclient;
    @Column(name = "qteappsupcredit1")
    private Integer qteappsupcredit1;
    @Column(name = "qteappsupcredit2")
    private Integer qteappsupcredit2;
    @Column(name = "qteappsupcredit3")
    private Integer qteappsupcredit3;
    @Column(name = "qteappsupcash1")
    private Integer qteappsupcash1;
    @Column(name = "qteappsupcash2")
    private Integer qteappsupcash2;
    @Column(name = "qteappsupcash3")
    private Integer qteappsupcash3;
    @Column(name = "totalcredits")
    private Integer totalcredits;
    @Column(name = "acomptecredit")
    private Integer acomptecredit;
    @Column(name = "acomptetotal")
    private Integer acomptetotal;
    @Column(name = "prix1mois")
    private Integer prix1mois;
    @Column(name = "prix1jour")
    private Integer prix1jour;
    @Column(name = "totalmontantpaye")
    private Integer totalmontantpaye;
    @Column(name = "totalmontantrestant")
    private Integer totalmontantrestant;
    @Column(name = "totalgraceused")
    private Integer totalgraceused;
    @Column(name = "totalgracerestant")
    private Integer totalgracerestant;
    @Size(max = 254)
    @Column(name = "creditstatus")
    private String creditstatus;
    @Column(name = "creditexpirationdate")
    @Temporal(TemporalType.DATE)
    private Date creditexpirationdate;
    @Size(max = 254)
    @Column(name = "installstatus")
    private String installstatus;
    @Column(name = "installdate")
    @Temporal(TemporalType.DATE)
    private Date installdate;
    @JoinColumn(name = "idtermes", referencedColumnName = "idtermes")
    @ManyToOne(optional = false)
    private Termes idtermes;
    @JoinColumn(name = "idproduits", referencedColumnName = "idproduits")
    @ManyToOne(optional = false)
    private Produits idproduits;
    @Size(max = 254)
    @Column(name = "lastcode")
    private String lastcode;
    @JoinColumn(name = "idpaygproduct", referencedColumnName = "idpaygproduct")
    @ManyToOne(optional = false)
    private Paygotpgenerator idpaygproduct;

    public Credits() {
    }

    public Credits(Long idcredits) {
        this.idcredits = idcredits;
    }

    public Long getIdcredits() {
        return idcredits;
    }

    public void setIdcredits(Long idcredits) {
        this.idcredits = idcredits;
    }

    public Integer getNuminstall() {
        return numinstall;
    }

    public void setNuminstall(Integer numinstall) {
        this.numinstall = numinstall;
    }

    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public Integer getQteappsupcredit1() {
        return qteappsupcredit1;
    }

    public void setQteappsupcredit1(Integer qteappsupcredit1) {
        this.qteappsupcredit1 = qteappsupcredit1;
    }

    public Integer getQteappsupcredit2() {
        return qteappsupcredit2;
    }

    public void setQteappsupcredit2(Integer qteappsupcredit2) {
        this.qteappsupcredit2 = qteappsupcredit2;
    }

    public Integer getQteappsupcredit3() {
        return qteappsupcredit3;
    }

    public void setQteappsupcredit3(Integer qteappsupcredit3) {
        this.qteappsupcredit3 = qteappsupcredit3;
    }

    public Integer getQteappsupcash1() {
        return qteappsupcash1;
    }

    public void setQteappsupcash1(Integer qteappsupcash1) {
        this.qteappsupcash1 = qteappsupcash1;
    }

    public Integer getQteappsupcash2() {
        return qteappsupcash2;
    }

    public void setQteappsupcash2(Integer qteappsupcash2) {
        this.qteappsupcash2 = qteappsupcash2;
    }

    public Integer getQteappsupcash3() {
        return qteappsupcash3;
    }

    public void setQteappsupcash3(Integer qteappsupcash3) {
        this.qteappsupcash3 = qteappsupcash3;
    }

    public Integer getTotalcredits() {
        return totalcredits;
    }

    public void setTotalcredits(Integer totalcredits) {
        this.totalcredits = totalcredits;
    }

    public Integer getAcomptecredit() {
        return acomptecredit;
    }

    public void setAcomptecredit(Integer acomptecredit) {
        this.acomptecredit = acomptecredit;
    }

    public Integer getAcomptetotal() {
        return acomptetotal;
    }

    public void setAcomptetotal(Integer acomptetotal) {
        this.acomptetotal = acomptetotal;
    }

    public Integer getPrix1mois() {
        return prix1mois;
    }

    public void setPrix1mois(Integer prix1mois) {
        this.prix1mois = prix1mois;
    }

    public Integer getPrix1jour() {
        return prix1jour;
    }

    public void setPrix1jour(Integer prix1jour) {
        this.prix1jour = prix1jour;
    }

    public Integer getTotalmontantpaye() {
        return totalmontantpaye;
    }

    public void setTotalmontantpaye(Integer totalmontantpaye) {
        this.totalmontantpaye = totalmontantpaye;
    }

    public Integer getTotalmontantrestant() {
        return totalmontantrestant;
    }

    public void setTotalmontantrestant(Integer totalmontantrestant) {
        this.totalmontantrestant = totalmontantrestant;
    }

    public Integer getTotalgraceused() {
        return totalgraceused;
    }

    public void setTotalgraceused(Integer totalgraceused) {
        this.totalgraceused = totalgraceused;
    }

    public Integer getTotalgracerestant() {
        return totalgracerestant;
    }

    public void setTotalgracerestant(Integer totalgracerestant) {
        this.totalgracerestant = totalgracerestant;
    }

    public String getCreditstatus() {
        return creditstatus;
    }

    public void setCreditstatus(String creditstatus) {
        this.creditstatus = creditstatus;
    }

    public Date getCreditexpirationdate() {
        return creditexpirationdate;
    }

    public void setCreditexpirationdate(Date creditexpirationdate) {
        this.creditexpirationdate = creditexpirationdate;
    }

    public String getInstallstatus() {
        return installstatus;
    }

    public void setInstallstatus(String installstatus) {
        this.installstatus = installstatus;
    }

    public Date getInstalldate() {
        return installdate;
    }

    public void setInstalldate(Date installdate) {
        this.installdate = installdate;
    }

    public Termes getIdtermes() {
        return idtermes;
    }

    public void setIdtermes(Termes idtermes) {
        this.idtermes = idtermes;
    }

    public Produits getIdproduits() {
        return idproduits;
    }

    public void setIdproduits(Produits idproduits) {
        this.idproduits = idproduits;
    }

    public Paygotpgenerator getIdpaygproduct() {
        return idpaygproduct;
    }

    public void setIdpaygproduct(Paygotpgenerator idpaygproduct) {
        this.idpaygproduct = idpaygproduct;
    }

    public String getLastcode() {
        return lastcode;
    }

    public void setLastcode(String lastcode) {
        this.lastcode = lastcode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcredits != null ? idcredits.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credits)) {
            return false;
        }
        Credits other = (Credits) object;
        if ((this.idcredits == null && other.idcredits != null) || (this.idcredits != null && !this.idcredits.equals(other.idcredits))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Credits[ idcredits=" + idcredits + " ]";
    }

    public Client getIdclient() {
        return idclient;
    }

    public void setIdclient(Client idclient) {
        this.idclient = idclient;
    }

    public Date getCodeenddate() {
        return codeenddate;
    }

    public void setCodeenddate(Date codeenddate) {
        this.codeenddate = codeenddate;
    }
}
