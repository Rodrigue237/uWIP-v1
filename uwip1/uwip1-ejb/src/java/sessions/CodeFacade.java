/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Box;
import entities.Code;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Seanjackson
 */
@Stateless
public class CodeFacade extends AbstractFacade<Code> implements CodeFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CodeFacade() {
        super(Code.class);
    }

    @Override
    public List<Box> findByInstalled() {
        Query queryinstlled = em.createNamedQuery("Code.findBoxInstalled");
        List listInstalled = queryinstlled.getResultList();
        return listInstalled;
    }

    @Override
    public int findKey1(int numBoxCode) {
        Query queryfindKey1 = em.createNamedQuery("Code.findKey1");
        queryfindKey1.setParameter("numbox", numBoxCode);
        int myKey1 = queryfindKey1.getSingleResult().hashCode();
        return myKey1;
    }

    @Override
    public int findKey2(int numBoxCode) {
        Query queryfindKey2 = em.createNamedQuery("Code.findKey2");
        queryfindKey2.setParameter("numbox", numBoxCode);
        int myKey2 = queryfindKey2.getSingleResult().hashCode();
        return myKey2;
    }

    @Override
    public int findKey3(int numBoxCode) {
        Query queryfindKey3 = em.createNamedQuery("Code.findKey3");
        queryfindKey3.setParameter("numbox", numBoxCode);
        int myKey3 = queryfindKey3.getSingleResult().hashCode();
        return myKey3;
    }

    /*@Override
     public int findNumBox(int idboxselect) {
     Query queryfindNumBox = em.createNamedQuery("Code.findNumBox");
     queryfindNumBox.setParameter("idboxe", idboxselect);
     int myNumBox = queryfindNumBox.getSingleResult().hashCode(); 
     return myNumBox;
     }*/
    @Override
    public int findNumPcb(int numBoxCode) {
        Query queryfindNumPcb = em.createNamedQuery("Code.findNumPcb");
        queryfindNumPcb.setParameter("numbox", numBoxCode);
        int myNumPcb = queryfindNumPcb.getSingleResult().hashCode();
        return myNumPcb;
    }

    @Override
    public String findPaygIdprod(int numBoxCode) {
        Query queryfindPaygIdprod = em.createNamedQuery("Code.findPaygIdprod");
        queryfindPaygIdprod.setParameter("numinstall", numBoxCode);
        String myPaygIdprod = (String) queryfindPaygIdprod.getSingleResult();
        return myPaygIdprod;
    }

    public String findPrix1Jours(int numBoxCode) {
        Query queryfindPrix1Jour = em.createNamedQuery("Code.findPrix1Jour");
        queryfindPrix1Jour.setParameter("numinstall", numBoxCode);
        String myPrix1Jour = (String) queryfindPrix1Jour.getSingleResult();
        return myPrix1Jour;
    }

    @Override
    public int findTotalTimePaid(int numBoxCode) {
        Query queryfindTotalTimePaid = em.createNamedQuery("Code.findTotalTimePaid");
        queryfindTotalTimePaid.setParameter("numbox", numBoxCode);
        int totalTimePaid = queryfindTotalTimePaid.getSingleResult().hashCode();
        return totalTimePaid;
    }

    @Override
    public int findIdBoxOfCode(int numBoxUSSD) {
        Query queryfindIdBoxOfCode = em.createNamedQuery("Code.findIdBoxOfCode");
        queryfindIdBoxOfCode.setParameter("numbox", numBoxUSSD);
        int idBoxOfCode = queryfindIdBoxOfCode.getSingleResult().hashCode();
        return idBoxOfCode;
    }

    @Override
    public int findIdClientOfCode(int numBoxUSSD) {
        Query queryfindIdClientOfCode = em.createNamedQuery("Code.findIdClientOfCode");
        queryfindIdClientOfCode.setParameter("numbox", numBoxUSSD);
        int idClienOfCode = queryfindIdClientOfCode.getSingleResult().hashCode();
        return idClienOfCode;
    }

    @Override
    public int findIdOrganizationOfCode(int numBoxUSSD) {
        Query queryfindIdOrganizationOfCode = em.createNamedQuery("Code.findIdOrganizationOfCode");
        queryfindIdOrganizationOfCode.setParameter("numbox", numBoxUSSD);
        int idOrganizationOfCode = queryfindIdOrganizationOfCode.getSingleResult().hashCode();
        return idOrganizationOfCode;
    }

    @Override
    public int findIdDistributorOfCode(int distribuniqnumberUSSD) {
        Query queryfindIdDistributorOfCode = em.createNamedQuery("Code.findIdDistributorOfCode");
        queryfindIdDistributorOfCode.setParameter("distribuniqnumber", distribuniqnumberUSSD);
        int idDistributorUSSD = queryfindIdDistributorOfCode.getSingleResult().hashCode();
        return idDistributorUSSD;
    }

    @Override
    public Date findLastEndDate(int numBoxCode) {
        Query queryfindLastEndDate = em.createNamedQuery("Code.findLastEndDate");
        queryfindLastEndDate.setParameter("numbox", numBoxCode);
        Date lastEndDate = (Date) queryfindLastEndDate.getSingleResult();
        return lastEndDate;
    }

    @Override
    public Date findLastEndDateForAlert(int numBoxAlert) {
        Query queryfindLastEndDate = em.createNamedQuery("Code.findLastEndDate");
        queryfindLastEndDate.setParameter("numbox", numBoxAlert);
        Date lastEndDateAlert = (Date) queryfindLastEndDate.getSingleResult();
        return lastEndDateAlert;
    }

    @Override
    public int findToUpdateKeys(int key3Code, int nextKey3, long idBoxCode, Date dateTransact, Date estimEndDate, BigInteger lastValueTime, BigInteger newLeftTimePaid, BigInteger newTotalTimePaid) {
        Query queryUpKey = em.createNamedQuery("Code.updateKeyactivAndKey3");
        queryUpKey.setParameter("genekeyactiv", key3Code);
        queryUpKey.setParameter("kpi3", nextKey3);
        queryUpKey.setParameter("idboxe", idBoxCode);
        queryUpKey.setParameter("lasttransact", dateTransact);
        queryUpKey.setParameter("lasttimecodevalue", lastValueTime);
        queryUpKey.setParameter("estimatedenddate", estimEndDate);
        queryUpKey.setParameter("estimlefttimepaid", newLeftTimePaid);
        queryUpKey.setParameter("totaltimepaid", newTotalTimePaid);
        int reponseUpKey = queryUpKey.executeUpdate();
        return reponseUpKey;
    }

    @Override
    public int findLastTotalTimePaid(int numBoxCode) {
        Query queryfindLastTotalTimePaid = em.createNamedQuery("Code.findLastTotalTimePaid");
        queryfindLastTotalTimePaid.setParameter("numbox", numBoxCode);
        int lastTotalTime = queryfindLastTotalTimePaid.getSingleResult().hashCode();
        return lastTotalTime;
    }

    @Override
    public String getContents(String urlFM, String encodeType) {
        URL u;
        StringBuilder builder = new StringBuilder();
        try {
            u = new URL(urlFM);
            try {
                BufferedReader theHTML = new BufferedReader(new InputStreamReader(u.openStream(), encodeType));
                String thisLine;
                while ((thisLine = theHTML.readLine()) != null) {
                    builder.append(thisLine).append("\n");
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        } catch (MalformedURLException e) {
            System.err.println(urlFM + " is not a parseable URL");
            System.err.println(e);
        }
        return builder.toString();
    }
}
