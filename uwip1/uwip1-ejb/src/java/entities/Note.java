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
@Table(name = "note")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n"),
    @NamedQuery(name = "Note.findAllOrganization", query = "SELECT o FROM Organization o"),
    @NamedQuery(name = "Note.findAllClient", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Note.findAllDistributor", query = "SELECT d FROM Distributor d"),
    @NamedQuery(name = "Note.findAllBox", query = "SELECT b FROM Box b"),
    @NamedQuery(name = "Note.findByIdnote", query = "SELECT n FROM Note n WHERE n.idnote = :idnote"),
    @NamedQuery(name = "Note.findByContent", query = "SELECT n FROM Note n WHERE n.content = :content"),
    @NamedQuery(name = "Note.findByDatecreated", query = "SELECT n FROM Note n WHERE n.datecreated = :datecreated")})
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnote")
    private Long idnote;
    @Size(max = 2147483647)
    @Column(name = "content")
    private String content;
    @Column(name = "datecreated")
    @Temporal(TemporalType.DATE)
    private Date datecreated;
    @JoinColumn(name = "idorganization", referencedColumnName = "idorganization")
    @ManyToOne(optional = false)
    private Organization idorganization;
    @JoinColumn(name = "iddistributor", referencedColumnName = "iddistributor")
    @ManyToOne(optional = false)
    private Distributor iddistributor;
    @JoinColumn(name = "idclient", referencedColumnName = "idclient")
    @ManyToOne(optional = false)
    private Client idclient;
    @JoinColumn(name = "idboxe", referencedColumnName = "idboxe")
    @ManyToOne(optional = false)
    private Box idboxe;

    public Note() {
    }

    public Note(Long idnote) {
        this.idnote = idnote;
    }

    public Long getIdnote() {
        return idnote;
    }

    public void setIdnote(Long idnote) {
        this.idnote = idnote;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public Organization getIdorganization() {
        return idorganization;
    }

    public void setIdorganization(Organization idorganization) {
        this.idorganization = idorganization;
    }

    public Distributor getIddistributor() {
        return iddistributor;
    }

    public void setIddistributor(Distributor iddistributor) {
        this.iddistributor = iddistributor;
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
        hash += (idnote != null ? idnote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.idnote == null && other.idnote != null) || (this.idnote != null && !this.idnote.equals(other.idnote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Note[ idnote=" + idnote + " ]";
    }
}
