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
@Table(name = "mmtransaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mmtransaction.findAll", query = "SELECT m FROM Mmtransaction m"),
    @NamedQuery(name = "Mmtransaction.findMaxId", query = "SELECT MAX(m.idmmtransaction) FROM Mmtransaction m"),
    @NamedQuery(name = "Mmtransaction.findNumPcb", query = "SELECT b.numpcb FROM Box b WHERE b.numbox = :numbox"),
    @NamedQuery(name = "Mmtransaction.findCopieDouble", query = "SELECT COUNT(m.idmmtransaction) FROM Mmtransaction m WHERE m.idtransact = :filtre"),
    @NamedQuery(name = "Mmtransaction.findByIdmmtransaction", query = "SELECT m FROM Mmtransaction m WHERE m.idmmtransaction = :idmmtransaction"),
    @NamedQuery(name = "Mmtransaction.findByIdtrans", query = "SELECT m FROM Mmtransaction m WHERE m.idtrans = :idtrans"),
    @NamedQuery(name = "Mmtransaction.findByIdoperator", query = "SELECT m FROM Mmtransaction m WHERE m.idoperator = :idoperator"),
    @NamedQuery(name = "Mmtransaction.findByRefdist", query = "SELECT m FROM Mmtransaction m WHERE m.refdist = :refdist"),
    @NamedQuery(name = "Mmtransaction.findByNumbox", query = "SELECT m FROM Mmtransaction m WHERE m.numbox = :numbox"),
    @NamedQuery(name = "Mmtransaction.findByNumphone", query = "SELECT m FROM Mmtransaction m WHERE m.numphone = :numphone"),
    @NamedQuery(name = "Mmtransaction.findByDatetrans", query = "SELECT m FROM Mmtransaction m WHERE m.datetrans = :datetrans"),
    @NamedQuery(name = "Mmtransaction.findByRefoffre", query = "SELECT m FROM Mmtransaction m WHERE m.refoffre = :refoffre"),
    @NamedQuery(name = "Mmtransaction.findByNbreunite", query = "SELECT m FROM Mmtransaction m WHERE m.nbreunite = :nbreunite"),
    @NamedQuery(name = "Mmtransaction.findByPrixunite", query = "SELECT m FROM Mmtransaction m WHERE m.prixunite = :prixunite"),
    @NamedQuery(name = "Mmtransaction.findBySommetotale", query = "SELECT m FROM Mmtransaction m WHERE m.sommetotale = :sommetotale"),
    @NamedQuery(name = "Mmtransaction.findByInd1", query = "SELECT m FROM Mmtransaction m WHERE m.ind1 = :ind1"),
    @NamedQuery(name = "Mmtransaction.findByInd2", query = "SELECT m FROM Mmtransaction m WHERE m.ind2 = :ind2")})
public class Mmtransaction implements Serializable {

    @Column(name = "dateexpirationcode")
    @Temporal(TemporalType.DATE)
    private Date dateexpirationcode;
    @Column(name = "nbrejours")
    private Integer nbrejours;
    @Column(name = "totalgraceused")
    private Integer totalgraceused;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmmtransaction")
    private Long idmmtransaction;
    @Column(name = "idtrans")
    private Integer idtrans;
    @Column(name = "idoperator")
    private Integer idoperator;
    @Column(name = "refdist")
    private Integer refdist;
    @Column(name = "numbox")
    private Integer numbox;
    @Column(name = "numphone")
    private Integer numphone;
    @Size(max = 254)
    @Column(name = "datetrans")
    private String datetrans;
    @Size(max = 254)
    @Column(name = "refoffre")
    private String refoffre;
    @Column(name = "nbreunite")
    private Integer nbreunite;
    @Column(name = "prixunite")
    private Integer prixunite;
    @Column(name = "sommetotale")
    private Integer sommetotale;
    @Column(name = "ind1")
    private Integer ind1;
    @Column(name = "ind2")
    private Integer ind2;
    @Size(max = 254)
    @Column(name = "idtransact")
    private String idtransact;
    @Column(name = "savetime")
    @Temporal(TemporalType.TIME)
    private Date savetime;

    public Mmtransaction() {
    }

    public Mmtransaction(Long idmmtransaction) {
        this.idmmtransaction = idmmtransaction;
    }

    public Long getIdmmtransaction() {
        return idmmtransaction;
    }

    public void setIdmmtransaction(Long idmmtransaction) {
        this.idmmtransaction = idmmtransaction;
    }

    public Integer getIdtrans() {
        return idtrans;
    }

    public void setIdtrans(Integer idtrans) {
        this.idtrans = idtrans;
    }

    public Integer getIdoperator() {
        return idoperator;
    }

    public void setIdoperator(Integer idoperator) {
        this.idoperator = idoperator;
    }

    public Integer getRefdist() {
        return refdist;
    }

    public void setRefdist(Integer refdist) {
        this.refdist = refdist;
    }

    public Integer getNumbox() {
        return numbox;
    }

    public void setNumbox(Integer numbox) {
        this.numbox = numbox;
    }

    public Integer getNumphone() {
        return numphone;
    }

    public void setNumphone(Integer numphone) {
        this.numphone = numphone;
    }

    public String getDatetrans() {
        return datetrans;
    }

    public void setDatetrans(String datetrans) {
        this.datetrans = datetrans;
    }

    public String getRefoffre() {
        return refoffre;
    }

    public void setRefoffre(String refoffre) {
        this.refoffre = refoffre;
    }

    public Integer getNbreunite() {
        return nbreunite;
    }

    public void setNbreunite(Integer nbreunite) {
        this.nbreunite = nbreunite;
    }

    public Integer getPrixunite() {
        return prixunite;
    }

    public void setPrixunite(Integer prixunite) {
        this.prixunite = prixunite;
    }

    public Integer getSommetotale() {
        return sommetotale;
    }

    public void setSommetotale(Integer sommetotale) {
        this.sommetotale = sommetotale;
    }

    public Integer getInd1() {
        return ind1;
    }

    public void setInd1(Integer ind1) {
        this.ind1 = ind1;
    }

    public Integer getInd2() {
        return ind2;
    }

    public void setInd2(Integer ind2) {
        this.ind2 = ind2;
    }

    public String getIdtransact() {
        return idtransact;
    }

    public void setIdtransact(String idtransact) {
        this.idtransact = idtransact;
    }

    public Date getSavetime() {
        return savetime;
    }

    public void setSavetime(Date savetime) {
        this.savetime = savetime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmmtransaction != null ? idmmtransaction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mmtransaction)) {
            return false;
        }
        Mmtransaction other = (Mmtransaction) object;
        if ((this.idmmtransaction == null && other.idmmtransaction != null) || (this.idmmtransaction != null && !this.idmmtransaction.equals(other.idmmtransaction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Mmtransaction[ idmmtransaction=" + idmmtransaction + " ]";
    }

    public Date getDateexpirationcode() {
        return dateexpirationcode;
    }

    public void setDateexpirationcode(Date dateexpirationcode) {
        this.dateexpirationcode = dateexpirationcode;
    }

    public Integer getNbrejours() {
        return nbrejours;
    }

    public void setNbrejours(Integer nbrejours) {
        this.nbrejours = nbrejours;
    }

    public Integer getTotalgraceused() {
        return totalgraceused;
    }

    public void setTotalgraceused(Integer totalgraceused) {
        this.totalgraceused = totalgraceused;
    }
}
