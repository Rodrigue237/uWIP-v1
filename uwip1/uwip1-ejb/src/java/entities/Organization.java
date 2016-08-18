/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "organization")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organization.findAll", query = "SELECT o FROM Organization o"),
    @NamedQuery(name = "Organization.findCopieDouble", query = "SELECT COUNT(o.idorganization) FROM Organization o WHERE o.name = :filtre"),
    @NamedQuery(name = "Organization.findByIdorganization", query = "SELECT o FROM Organization o WHERE o.idorganization = :idorganization"),
    @NamedQuery(name = "Organization.findByName", query = "SELECT o FROM Organization o WHERE o.name = :name"),
    @NamedQuery(name = "Organization.findByActivity", query = "SELECT o FROM Organization o WHERE o.activity = :activity"),
    @NamedQuery(name = "Organization.findByCapital", query = "SELECT o FROM Organization o WHERE o.capital = :capital")})
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idorganization")
    private Long idorganization;
    @Size(max = 254)
    @Column(name = "name")
    private String name;
    @Size(max = 254)
    @Column(name = "activity")
    private String activity;
    @Column(name = "capital")
    private BigInteger capital;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idorganization")
    private Collection<Uuser> uuserCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idorganization")
    private Collection<Code> codeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idorganization")
    private Collection<Distributor> distributorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idorganization")
    private Collection<Box> boxCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idorganization")
    private Collection<Note> noteCollection;

    public Organization() {
    }

    public Organization(Long idorganization) {
        this.idorganization = idorganization;
    }

    public Long getIdorganization() {
        return idorganization;
    }

    public void setIdorganization(Long idorganization) {
        this.idorganization = idorganization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public BigInteger getCapital() {
        return capital;
    }

    public void setCapital(BigInteger capital) {
        this.capital = capital;
    }

    @XmlTransient
    public Collection<Uuser> getUuserCollection() {
        return uuserCollection;
    }

    public void setUuserCollection(Collection<Uuser> uuserCollection) {
        this.uuserCollection = uuserCollection;
    }

    @XmlTransient
    public Collection<Code> getCodeCollection() {
        return codeCollection;
    }

    public void setCodeCollection(Collection<Code> codeCollection) {
        this.codeCollection = codeCollection;
    }

    @XmlTransient
    public Collection<Distributor> getDistributorCollection() {
        return distributorCollection;
    }

    public void setDistributorCollection(Collection<Distributor> distributorCollection) {
        this.distributorCollection = distributorCollection;
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
        hash += (idorganization != null ? idorganization.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organization)) {
            return false;
        }
        Organization other = (Organization) object;
        if ((this.idorganization == null && other.idorganization != null) || (this.idorganization != null && !this.idorganization.equals(other.idorganization))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Organization[ idorganization=" + idorganization + " ]";
    }
}
