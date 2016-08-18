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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Seanjackson
 */
@Entity
@Table(name = "produits")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produits.findAll", query = "SELECT p FROM Produits p"),
    @NamedQuery(name = "Produits.findCopieDouble", query = "SELECT COUNT(p.idproduits) FROM Produits p WHERE p.designation = :filtre"),
    @NamedQuery(name = "Produits.findByIdproduits", query = "SELECT p FROM Produits p WHERE p.idproduits = :idproduits"),
    @NamedQuery(name = "Produits.findByDesignation", query = "SELECT p FROM Produits p WHERE p.designation = :designation"),
    @NamedQuery(name = "Produits.findByPrixcredit3ans", query = "SELECT p FROM Produits p WHERE p.prixcredit3ans = :prixcredit3ans"),
    @NamedQuery(name = "Produits.findByPrixcredit1an", query = "SELECT p FROM Produits p WHERE p.prixcredit1an = :prixcredit1an"),
    @NamedQuery(name = "Produits.findByPrixcash", query = "SELECT p FROM Produits p WHERE p.prixcash = :prixcash"),
    @NamedQuery(name = "Produits.findPrixCredit1ans", query = "SELECT p.prixcredit1an FROM Produits p WHERE p.idproduits = :idproduits"),
    @NamedQuery(name = "Produits.findPrixCredit2ans", query = "SELECT p.prixcredit2ans FROM Produits p WHERE p.idproduits = :idproduits"),
    @NamedQuery(name = "Produits.findPrixCredit3ans", query = "SELECT p.prixcredit3ans FROM Produits p WHERE p.idproduits = :idproduits"),
    @NamedQuery(name = "Produits.findPrixRadio1an", query = "SELECT p.prixcredit1an FROM Produits p WHERE p.designation = :designation"),
    @NamedQuery(name = "Produits.findPrixLampe1an", query = "SELECT p.prixcredit1an FROM Produits p WHERE p.designation = :designation"),
    @NamedQuery(name = "Produits.findPrixTorche1an", query = "SELECT p.prixcredit1an FROM Produits p WHERE p.designation = :designation"),
    @NamedQuery(name = "Produits.findPrixRadio2ans", query = "SELECT p.prixcredit2ans FROM Produits p WHERE p.designation = :designation"),
    @NamedQuery(name = "Produits.findPrixLampe2ans", query = "SELECT p.prixcredit2ans FROM Produits p WHERE p.designation = :designation"),
    @NamedQuery(name = "Produits.findPrixTorche2ans", query = "SELECT p.prixcredit2ans FROM Produits p WHERE p.designation = :designation"),
    @NamedQuery(name = "Produits.findPrixRadio3ans", query = "SELECT p.prixcredit3ans FROM Produits p WHERE p.designation = :designation"),
    @NamedQuery(name = "Produits.findPrixLampe3ans", query = "SELECT p.prixcredit3ans FROM Produits p WHERE p.designation = :designation"),
    @NamedQuery(name = "Produits.findPrixTorche3ans", query = "SELECT p.prixcredit3ans FROM Produits p WHERE p.designation = :designation"),
    @NamedQuery(name = "Produits.findPrixAppSupCash", query = "SELECT p.prixcash FROM Produits p WHERE p.designation = :designation"),
    @NamedQuery(name = "Produits.findByTypeproduit", query = "SELECT p FROM Produits p WHERE p.typeproduit = :typeproduit")})
public class Produits implements Serializable {

    @Column(name = "prixcredit2ans")
    private Integer prixcredit2ans;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproduits")
    private Long idproduits;
    @Size(max = 254)
    @Column(name = "designation")
    private String designation;
    @Column(name = "prixcredit3ans")
    private Integer prixcredit3ans;
    @Column(name = "prixcredit1an")
    private Integer prixcredit1an;
    @Column(name = "prixcash")
    private Integer prixcash;
    @Size(max = 254)
    @Column(name = "typeproduit")
    private String typeproduit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproduits")
    private Collection<Credits> creditsCollection;

    public Produits() {
    }

    public Produits(Long idproduits) {
        this.idproduits = idproduits;
    }

    public Long getIdproduits() {
        return idproduits;
    }

    public void setIdproduits(Long idproduits) {
        this.idproduits = idproduits;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getPrixcredit3ans() {
        return prixcredit3ans;
    }

    public void setPrixcredit3ans(Integer prixcredit3ans) {
        this.prixcredit3ans = prixcredit3ans;
    }

    public Integer getPrixcredit1an() {
        return prixcredit1an;
    }

    public void setPrixcredit1an(Integer prixcredit1an) {
        this.prixcredit1an = prixcredit1an;
    }

    public Integer getPrixcash() {
        return prixcash;
    }

    public void setPrixcash(Integer prixcash) {
        this.prixcash = prixcash;
    }

    public String getTypeproduit() {
        return typeproduit;
    }

    public void setTypeproduit(String typeproduit) {
        this.typeproduit = typeproduit;
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
        hash += (idproduits != null ? idproduits.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produits)) {
            return false;
        }
        Produits other = (Produits) object;
        if ((this.idproduits == null && other.idproduits != null) || (this.idproduits != null && !this.idproduits.equals(other.idproduits))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Produits[ idproduits=" + idproduits + " ]";
    }

    public Integer getPrixcredit2ans() {
        return prixcredit2ans;
    }

    public void setPrixcredit2ans(Integer prixcredit2ans) {
        this.prixcredit2ans = prixcredit2ans;
    }
}
