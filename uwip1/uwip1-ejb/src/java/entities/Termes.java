/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Seanjackson
 */
@Entity
@Table(name = "termes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Termes.findAll", query = "SELECT t FROM Termes t"),
    @NamedQuery(name = "Termes.findAllValideTermes", query = "SELECT t FROM Termes t WHERE t.nomcredit != :nomcredit"),
    @NamedQuery(name = "Termes.findCopieDouble", query = "SELECT COUNT(t.idtermes) FROM Termes t WHERE t.nomcredit = :filtre"),
    @NamedQuery(name = "Termes.findByIdtermes", query = "SELECT t FROM Termes t WHERE t.idtermes = :idtermes"),
    @NamedQuery(name = "Termes.findByNomcredit", query = "SELECT t FROM Termes t WHERE t.nomcredit = :nomcredit"),
    @NamedQuery(name = "Termes.findByDureecredit", query = "SELECT t FROM Termes t WHERE t.dureecredit = :dureecredit"),
    @NamedQuery(name = "Termes.findByAcompte", query = "SELECT t FROM Termes t WHERE t.acompte = :acompte"),
    @NamedQuery(name = "Termes.findByNbremensualite", query = "SELECT t FROM Termes t WHERE t.nbremensualite = :nbremensualite"),
    @NamedQuery(name = "Termes.findDureeCredit", query = "SELECT t.dureecredit FROM Termes t WHERE t.idtermes = :idtermes"),
    @NamedQuery(name = "Termes.findAcomptePourcent", query = "SELECT t.acompte FROM Termes t WHERE t.idtermes = :idtermes"),
    @NamedQuery(name = "Termes.findNbreMensualite", query = "SELECT t.nbremensualite FROM Termes t WHERE t.idtermes = :idtermes"),
    @NamedQuery(name = "Termes.findByNbrejourgrace", query = "SELECT t FROM Termes t WHERE t.nbrejourgrace = :nbrejourgrace")})
public class Termes implements Serializable {

    @Size(max = 10)
    @Column(name = "acompte")
    private String acompte;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtermes")
    private Long idtermes;
    @Size(max = 254)
    @Column(name = "nomcredit")
    private String nomcredit;
    @Column(name = "dureecredit")
    private Integer dureecredit;
    @Column(name = "nbremensualite")
    private Integer nbremensualite;
    @Column(name = "nbrejourgrace")
    private Integer nbrejourgrace;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtermes")
    private Collection<Credits> creditsCollection;

    public Termes() {
    }

    public Termes(Long idtermes) {
        this.idtermes = idtermes;
    }

    public Long getIdtermes() {
        return idtermes;
    }

    public void setIdtermes(Long idtermes) {
        this.idtermes = idtermes;
    }

    public String getNomcredit() {
        return nomcredit;
    }

    public void setNomcredit(String nomcredit) {
        this.nomcredit = nomcredit;
    }

    public Integer getDureecredit() {
        return dureecredit;
    }

    public void setDureecredit(Integer dureecredit) {
        this.dureecredit = dureecredit;
    }

    public Integer getNbremensualite() {
        return nbremensualite;
    }

    public void setNbremensualite(Integer nbremensualite) {
        this.nbremensualite = nbremensualite;
    }

    public Integer getNbrejourgrace() {
        return nbrejourgrace;
    }

    public void setNbrejourgrace(Integer nbrejourgrace) {
        this.nbrejourgrace = nbrejourgrace;
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
        hash += (idtermes != null ? idtermes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Termes)) {
            return false;
        }
        Termes other = (Termes) object;
        if ((this.idtermes == null && other.idtermes != null) || (this.idtermes != null && !this.idtermes.equals(other.idtermes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Termes[ idtermes=" + idtermes + " ]";
    }

    public String getAcompte() {
        return acompte;
    }

    public void setAcompte(String acompte) {
        this.acompte = acompte;
    }
}
