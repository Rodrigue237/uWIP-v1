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
@Table(name = "paygcodes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paygcodes.findAll", query = "SELECT p FROM Paygcodes p"),
    @NamedQuery(name = "Paygcodes.findByIdpaygcodes", query = "SELECT p FROM Paygcodes p WHERE p.idpaygcodes = :idpaygcodes"),
    @NamedQuery(name = "Paygcodes.findByGeneratedcode", query = "SELECT p FROM Paygcodes p WHERE p.generatedcode = :generatedcode"),
    @NamedQuery(name = "Paygcodes.findByGenerateddate", query = "SELECT p FROM Paygcodes p WHERE p.generateddate = :generateddate"),
    @NamedQuery(name = "Paygcodes.updateCurrentC", query = "UPDATE Paygotpgenerator p SET p.currenthashindex = :currenthashindex WHERE p.idpaygproduct = :idpaygproduct"),
    @NamedQuery(name = "Paygcodes.findByNuminstall", query = "SELECT p FROM Paygcodes p WHERE p.numinstall = :numinstall")})
public class Paygcodes implements Serializable {

    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date enddate;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpaygcodes")
    private Long idpaygcodes;
    @Size(max = 50)
    @Column(name = "generatedcode")
    private String generatedcode;
    @Column(name = "generateddate")
    @Temporal(TemporalType.DATE)
    private Date generateddate;
    @Column(name = "numinstall")
    private Integer numinstall;

    public Paygcodes() {
    }

    public Paygcodes(Long idpaygcodes) {
        this.idpaygcodes = idpaygcodes;
    }

    public Long getIdpaygcodes() {
        return idpaygcodes;
    }

    public void setIdpaygcodes(Long idpaygcodes) {
        this.idpaygcodes = idpaygcodes;
    }

    public String getGeneratedcode() {
        return generatedcode;
    }

    public void setGeneratedcode(String generatedcode) {
        this.generatedcode = generatedcode;
    }

    public Date getGenerateddate() {
        return generateddate;
    }

    public void setGenerateddate(Date generateddate) {
        this.generateddate = generateddate;
    }

    public Integer getNuminstall() {
        return numinstall;
    }

    public void setNuminstall(Integer numinstall) {
        this.numinstall = numinstall;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpaygcodes != null ? idpaygcodes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paygcodes)) {
            return false;
        }
        Paygcodes other = (Paygcodes) object;
        if ((this.idpaygcodes == null && other.idpaygcodes != null) || (this.idpaygcodes != null && !this.idpaygcodes.equals(other.idpaygcodes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Paygcodes[ idpaygcodes=" + idpaygcodes + " ]";
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}
