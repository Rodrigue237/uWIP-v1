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
@Table(name = "uuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uuser.findAll", query = "SELECT u FROM Uuser u"),
    @NamedQuery(name = "Uuser.findCopieDouble", query = "SELECT COUNT(u.iduuser) FROM Uuser u WHERE u.login = :logins"),
    @NamedQuery(name = "Uuser.findByIduuser", query = "SELECT u FROM Uuser u WHERE u.iduuser = :iduuser"),
    @NamedQuery(name = "Uuser.findByFirstname", query = "SELECT u FROM Uuser u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "Uuser.findByLastname", query = "SELECT u FROM Uuser u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "Uuser.findByBirth", query = "SELECT u FROM Uuser u WHERE u.birth = :birth"),
    @NamedQuery(name = "Uuser.findByLogin", query = "SELECT u FROM Uuser u WHERE u.login = :login"),
    @NamedQuery(name = "Uuser.findByLoginMdp", query = "SELECT u FROM Uuser u WHERE u.login = :login AND u.mdp=:mdp"),
    @NamedQuery(name = "Uuser.findAll2", query = "SELECT u FROM Uuser u WHERE u.idprofile.name != :name"),
    @NamedQuery(name = "Uuser.findByMdp", query = "SELECT u FROM Uuser u WHERE u.mdp = :mdp")})
public class Uuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iduuser")
    private Long iduuser;
    @Size(max = 254)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 254)
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "birth")
    @Temporal(TemporalType.DATE)
    private Date birth;
    @Size(max = 254)
    @Column(name = "login")
    private String login;
    @Size(max = 254)
    @Column(name = "mdp")
    private String mdp;
    @JoinColumn(name = "idprofile", referencedColumnName = "idprofile")
    @ManyToOne(optional = false)
    private Profile idprofile;
    @JoinColumn(name = "idorganization", referencedColumnName = "idorganization")
    @ManyToOne(optional = false)
    private Organization idorganization;

    public Uuser() {
    }

    public Uuser(Long iduuser) {
        this.iduuser = iduuser;
    }

    public Long getIduuser() {
        return iduuser;
    }

    public void setIduuser(Long iduuser) {
        this.iduuser = iduuser;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Profile getIdprofile() {
        return idprofile;
    }

    public void setIdprofile(Profile idprofile) {
        this.idprofile = idprofile;
    }

    public Organization getIdorganization() {
        return idorganization;
    }

    public void setIdorganization(Organization idorganization) {
        this.idorganization = idorganization;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iduuser != null ? iduuser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uuser)) {
            return false;
        }
        Uuser other = (Uuser) object;
        if ((this.iduuser == null && other.iduuser != null) || (this.iduuser != null && !this.iduuser.equals(other.iduuser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Uuser[ iduuser=" + iduuser + " ]";
    }
}
