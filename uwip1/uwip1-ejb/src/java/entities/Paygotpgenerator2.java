/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Seanjackson
 */
@Entity
@Table(name = "paygotpgenerator2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paygotpgenerator2.findAll", query = "SELECT p FROM Paygotpgenerator2 p"),
    @NamedQuery(name = "Paygotpgenerator2.findByIdline", query = "SELECT p FROM Paygotpgenerator2 p WHERE p.idline = :idline"),
    @NamedQuery(name = "Paygotpgenerator2.findByProductcode", query = "SELECT p FROM Paygotpgenerator2 p WHERE p.productcode = :productcode"),
    @NamedQuery(name = "Paygotpgenerator2.findByPaygproductid", query = "SELECT p FROM Paygotpgenerator2 p WHERE p.paygproductid = :paygproductid"),
    @NamedQuery(name = "Paygotpgenerator2.findByOemproductid", query = "SELECT p FROM Paygotpgenerator2 p WHERE p.oemproductid = :oemproductid"),
    @NamedQuery(name = "Paygotpgenerator2.findByOemlifecyclestatus", query = "SELECT p FROM Paygotpgenerator2 p WHERE p.oemlifecyclestatus = :oemlifecyclestatus"),
    @NamedQuery(name = "Paygotpgenerator2.findByHroot", query = "SELECT p FROM Paygotpgenerator2 p WHERE p.hroot = :hroot"),
    @NamedQuery(name = "Paygotpgenerator2.findByHtop", query = "SELECT p FROM Paygotpgenerator2 p WHERE p.htop = :htop"),
    @NamedQuery(name = "Paygotpgenerator2.findByOtpcount", query = "SELECT p FROM Paygotpgenerator2 p WHERE p.otpcount = :otpcount"),
    @NamedQuery(name = "Paygotpgenerator2.findByCurrenthashindex", query = "SELECT p FROM Paygotpgenerator2 p WHERE p.currenthashindex = :currenthashindex"),
    @NamedQuery(name = "Paygotpgenerator2.findByHashchainlength", query = "SELECT p FROM Paygotpgenerator2 p WHERE p.hashchainlength = :hashchainlength"),
    @NamedQuery(name = "Paygotpgenerator2.findByMaxhcj", query = "SELECT p FROM Paygotpgenerator2 p WHERE p.maxhcj = :maxhcj"),
    @NamedQuery(name = "Paygotpgenerator2.findBySalt", query = "SELECT p FROM Paygotpgenerator2 p WHERE p.salt = :salt"),
    @NamedQuery(name = "Paygotpgenerator2.updateSalt", query = "UPDATE Paygotpgenerator2 p SET p.salt = :salt WHERE p.paygproductid = :paygproductid"),
    @NamedQuery(name = "Paygotpgenerator2.updateSeed", query = "UPDATE Paygotpgenerator2 p SET p.seed = :seed WHERE p.paygproductid = :paygproductid"),
    @NamedQuery(name = "Paygotpgenerator2.updateHroot", query = "UPDATE Paygotpgenerator2 p SET p.hroot = :hroot WHERE p.paygproductid = :paygproductid"),
    @NamedQuery(name = "Paygotpgenerator2.updateHtop", query = "UPDATE Paygotpgenerator2 p SET p.htop = :htop WHERE p.paygproductid = :paygproductid"),
    @NamedQuery(name = "Paygotpgenerator2.findByBatchnumber", query = "SELECT p FROM Paygotpgenerator2 p WHERE p.batchnumber = :batchnumber")})
public class Paygotpgenerator2 implements Serializable {
    @Size(max = 254)
    @Column(name = "seed")
    private String seed;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idline")
    private Long idline;
    @Size(max = 40)
    @Column(name = "productcode")
    private String productcode;
    @Size(max = 20)
    @Column(name = "paygproductid")
    private String paygproductid;
    @Size(max = 40)
    @Column(name = "oemproductid")
    private String oemproductid;
    @Size(max = 10)
    @Column(name = "oemlifecyclestatus")
    private String oemlifecyclestatus;
    @Size(max = 254)
    @Column(name = "hroot")
    private String hroot;
    @Size(max = 254)
    @Column(name = "htop")
    private String htop;
    @Column(name = "otpcount")
    private Integer otpcount;
    @Column(name = "currenthashindex")
    private Integer currenthashindex;
    @Column(name = "hashchainlength")
    private Integer hashchainlength;
    @Column(name = "maxhcj")
    private Integer maxhcj;
    @Size(max = 25)
    @Column(name = "salt")
    private String salt;
    @Size(max = 25)
    @Column(name = "batchnumber")
    private String batchnumber;

    public Paygotpgenerator2() {
    }

    public Paygotpgenerator2(Long idline) {
        this.idline = idline;
    }

    public Long getIdline() {
        return idline;
    }

    public void setIdline(Long idline) {
        this.idline = idline;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public String getPaygproductid() {
        return paygproductid;
    }

    public void setPaygproductid(String paygproductid) {
        this.paygproductid = paygproductid;
    }

    public String getOemproductid() {
        return oemproductid;
    }

    public void setOemproductid(String oemproductid) {
        this.oemproductid = oemproductid;
    }

    public String getOemlifecyclestatus() {
        return oemlifecyclestatus;
    }

    public void setOemlifecyclestatus(String oemlifecyclestatus) {
        this.oemlifecyclestatus = oemlifecyclestatus;
    }

    public String getHroot() {
        return hroot;
    }

    public void setHroot(String hroot) {
        this.hroot = hroot;
    }

    public String getHtop() {
        return htop;
    }

    public void setHtop(String htop) {
        this.htop = htop;
    }

    public Integer getOtpcount() {
        return otpcount;
    }

    public void setOtpcount(Integer otpcount) {
        this.otpcount = otpcount;
    }

    public Integer getCurrenthashindex() {
        return currenthashindex;
    }

    public void setCurrenthashindex(Integer currenthashindex) {
        this.currenthashindex = currenthashindex;
    }

    public Integer getHashchainlength() {
        return hashchainlength;
    }

    public void setHashchainlength(Integer hashchainlength) {
        this.hashchainlength = hashchainlength;
    }

    public Integer getMaxhcj() {
        return maxhcj;
    }

    public void setMaxhcj(Integer maxhcj) {
        this.maxhcj = maxhcj;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getBatchnumber() {
        return batchnumber;
    }

    public void setBatchnumber(String batchnumber) {
        this.batchnumber = batchnumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idline != null ? idline.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paygotpgenerator2)) {
            return false;
        }
        Paygotpgenerator2 other = (Paygotpgenerator2) object;
        if ((this.idline == null && other.idline != null) || (this.idline != null && !this.idline.equals(other.idline))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Paygotpgenerator2[ idline=" + idline + " ]";
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }
    
}
