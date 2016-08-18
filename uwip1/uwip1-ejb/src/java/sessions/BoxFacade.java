/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Box;
import entities.Client;
import entities.Organization;
import entities.Townlayer;
import java.math.BigInteger;
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
public class BoxFacade extends AbstractFacade<Box> implements BoxFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BoxFacade() {
        super(Box.class);
    }

    @Override
    public int findNumPcb(int numBoxCode) {
        Query queryfindNumPcb = em.createNamedQuery("Box.findNumPcb");
        queryfindNumPcb.setParameter("numbox", numBoxCode);
        int myNumPcb = queryfindNumPcb.getSingleResult().hashCode();
        return myNumPcb;
    }

    public List<Box> findByMapped() {
        Query query1 = em.createNamedQuery("Box.findByMapped");
        List listObj1 = query1.getResultList();
        return listObj1;
    }

    public List<Box> findByLibre() {
        Query query2 = em.createNamedQuery("Box.findByLibre");
        List listObj2 = query2.getResultList();
        return listObj2;
    }

    public List<Box> findByMaintenance() {
        Query query3 = em.createNamedQuery("Box.findByMaintenance");
        List listObj3 = query3.getResultList();
        return listObj3;
    }

    public List<Box> findByIndispo(String magDispo) {
        Query query4 = em.createNamedQuery("Box.findByIndispo");
        query4.setParameter("atelier", magDispo);
        List listObj4 = query4.getResultList();
        return listObj4;
    }

    @Override
    public int findToUpdateStMarquer(long idboxinstal, String boxActivated) {
        Query query6 = em.createNamedQuery("Box.changeStatusMarquer");
        query6.setParameter("idboxe", idboxinstal);
        query6.setParameter("atelier", boxActivated);
        int reponse = query6.executeUpdate();
        return reponse;
    }

    @Override
    public int findToUpdateLatiAndLong(double latitudeinstal, double longitudeinstal, long idboxinstal) {
        Query query7 = em.createNamedQuery("Box.changeLatiAndLongi");
        query7.setParameter("latitude", latitudeinstal);
        query7.setParameter("longitude", longitudeinstal);
        query7.setParameter("idboxe", idboxinstal);
        int reponse2 = query7.executeUpdate();
        return reponse2;
    }

    @Override
    public int findToInitNumBox(int numInstall, long idboxinstal) {
        Query query13 = em.createNamedQuery("Box.initNumBox");
        query13.setParameter("numbox", numInstall);
        query13.setParameter("idboxe", idboxinstal);
        int reponse13 = query13.executeUpdate();
        return reponse13;
    }

    @Override
    public int findToUpdateClientBox(long idboxinstal, Client idclient) {
        Query query9 = em.createNamedQuery("Box.changeClientNameBox");
        query9.setParameter("idclient", idclient);
        query9.setParameter("idboxe", idboxinstal);
        int reponse4 = query9.executeUpdate();
        return reponse4;
    }

    @Override
    public int findToUpdateOrganizationBox(long idboxinstal, Organization idorganization) {
        Query query10 = em.createNamedQuery("Box.changeOrganizationNameBox");
        query10.setParameter("idorganization", idorganization);
        query10.setParameter("idboxe", idboxinstal);
        int reponse5 = query10.executeUpdate();
        return reponse5;
    }

    public int findCopieDouble(String numBoxSerieSaisi) {
        Query queryfindCopie = em.createNamedQuery("Box.findCopieDouble");
        queryfindCopie.setParameter("filtre", numBoxSerieSaisi);
        int nbreCopie = queryfindCopie.getSingleResult().hashCode();
        return nbreCopie;
    }

    @Override
    public int findToChangeStMarquer1(long idboxinstal, String magHS, int numeroBox) {
        Query query11 = em.createNamedQuery("Box.changeStatusMarquer1");
        query11.setParameter("idboxe", idboxinstal);
        query11.setParameter("atelier", magHS);
        query11.setParameter("numbox", numeroBox);
        int reponse11 = query11.executeUpdate();
        return reponse11;
    }

    @Override
    public int findToDesactivateClientBox(long idboxinstal, Client idclient) {
        Query query12 = em.createNamedQuery("Box.changeClientNameBox");
        query12.setParameter("idclient", idclient);
        query12.setParameter("idboxe", idboxinstal);
        int reponse12 = query12.executeUpdate();
        return reponse12;
    }

    @Override
    public int findKey1(int idboxselect) {
        Query queryfindKey1 = em.createNamedQuery("Box.findKey1");
        queryfindKey1.setParameter("idboxe", idboxselect);
        int myKey1 = queryfindKey1.getSingleResult().hashCode();
        return myKey1;
    }

    @Override
    public int findKey2(int idboxselect) {
        Query queryfindKey2 = em.createNamedQuery("Box.findKey2");
        queryfindKey2.setParameter("idboxe", idboxselect);
        int myKey2 = queryfindKey2.getSingleResult().hashCode();
        return myKey2;
    }

    @Override
    public int findKey3(int idboxselect) {
        Query queryfindKey3 = em.createNamedQuery("Box.findKey3");
        queryfindKey3.setParameter("idboxe", idboxselect);
        int myKey3 = queryfindKey3.getSingleResult().hashCode();
        return myKey3;
    }

    @Override
    public int findNumBox(int idboxselect) {
        Query queryfindNumBox = em.createNamedQuery("Box.findNumBox");
        queryfindNumBox.setParameter("idboxe", idboxselect);
        int myNumBox = queryfindNumBox.getSingleResult().hashCode();
        return myNumBox;
    }

    @Override
    public int findTotalInstall(long idboxinstal) {
        Query queryfindTotalInstall = em.createNamedQuery("Box.findTotalInstall");
        queryfindTotalInstall.setParameter("idboxe", idboxinstal);
        int totatInstalls = queryfindTotalInstall.getSingleResult().hashCode();
        return totatInstalls;
    }

    @Override
    public int changeTotalInstall(int totalInstall, long idboxinstal, String userOfSystem, int idOfLastContract, int phoneUserSystem) {
        Query querychangeTotalInstall = em.createNamedQuery("Box.changeTotalInstall");
        querychangeTotalInstall.setParameter("totalinstall", totalInstall);
        querychangeTotalInstall.setParameter("idlastcontract", idOfLastContract);
        querychangeTotalInstall.setParameter("userofsystem", userOfSystem);
        querychangeTotalInstall.setParameter("idboxe", idboxinstal);
        querychangeTotalInstall.setParameter("phoneofusersystem", phoneUserSystem);
        int reponseTotalInstall = querychangeTotalInstall.executeUpdate();
        return reponseTotalInstall;
    }

    @Override
    public int updateFirstInstallDate(long idboxinstal, Date contratInstallDate) {
        Query queryupdateFirstInstallDate = em.createNamedQuery("Box.updateFirstInstallDate");
        queryupdateFirstInstallDate.setParameter("firstinstaldate", contratInstallDate);
        queryupdateFirstInstallDate.setParameter("idboxe", idboxinstal);
        int reponseUpdateDateFirstInst = queryupdateFirstInstallDate.executeUpdate();
        return reponseUpdateDateFirstInst;
    }

    @Override
    public int updateLastInstallDate(long idboxinstal, Date contratInstallDate) {
        Query queryupdateLastInstallDate = em.createNamedQuery("Box.updateLastInstallDate");
        queryupdateLastInstallDate.setParameter("lastinstaldate", contratInstallDate);
        queryupdateLastInstallDate.setParameter("idboxe", idboxinstal);
        int reponseUpdateDateLasttInst = queryupdateLastInstallDate.executeUpdate();
        return reponseUpdateDateLasttInst;
    }
    /*
     @Override
     public int findIdClientOut(int numboxOut){
     Query queryfindIdClientOut = em.createNamedQuery("Box.findIdClientOut");
     queryfindIdClientOut.setParameter("numbox", numboxOut);
     int idClienOut = queryfindIdClientOut.getSingleResult().hashCode(); 
     return idClienOut;   
     }
    
     @Override
     public int findIdOrganizationOut(int numboxOut){
     Query queryfindIdOrganizationOut = em.createNamedQuery("Box.findIdOrganizationOut");
     queryfindIdOrganizationOut.setParameter("numbox", numboxOut);
     int idOrganizationOut = queryfindIdOrganizationOut.getSingleResult().hashCode(); 
     return idOrganizationOut;   
     }
    
     @Override
     public int findIdTownlayerOut(int numboxOut){
     Query queryfindIdTownlayerOut = em.createNamedQuery("Box.findIdTownlayerOut");
     queryfindIdTownlayerOut.setParameter("numbox", numboxOut);
     int idTownlayerOut = queryfindIdTownlayerOut.getSingleResult().hashCode(); 
     return idTownlayerOut;   
     }*/

    @Override
    public int remplaceBox(int numboxOut, int idlastContractOut, int totatInstallIn, double latitudeOut, double longitudeOut, String countryOut, Date newlastinstallDate, Date lasttransactOut, BigInteger lasttimecodevalueOut, BigInteger totaltimepaidOut, BigInteger estimlefttimepaidOut, int marquerstatusOut, String userofsystemtOut, Date estimatedenddateOut, String marquerstyleOut, String atelierOut, Townlayer idtownlayerOut, Organization idorganizationOut, Client idclientOut, long idboxeIn) {
        Query query14 = em.createNamedQuery("Box.remplaceBox");
        query14.setParameter("numbox", numboxOut);
        query14.setParameter("latitude", latitudeOut);
        query14.setParameter("longitude", longitudeOut);
        query14.setParameter("country", countryOut);
        query14.setParameter("idlastcontract", idlastContractOut);
        query14.setParameter("totalinstall", totatInstallIn);
        query14.setParameter("lastinstaldate", newlastinstallDate);
        query14.setParameter("lasttransact", lasttransactOut);
        query14.setParameter("lasttimecodevalue", lasttimecodevalueOut);
        query14.setParameter("totaltimepaid", totaltimepaidOut);
        query14.setParameter("estimlefttimepaid", estimlefttimepaidOut);
        query14.setParameter("marquerstatus", marquerstatusOut);
        query14.setParameter("userofsystem", userofsystemtOut);
        query14.setParameter("estimatedenddate", estimatedenddateOut);
        query14.setParameter("marquerstyle", marquerstyleOut);
        query14.setParameter("atelier", atelierOut);
        query14.setParameter("idtownlayer", idtownlayerOut);
        query14.setParameter("idorganization", idorganizationOut);
        query14.setParameter("idclient", idclientOut);
        query14.setParameter("idboxe", idboxeIn);
        int reponse14 = query14.executeUpdate();
        return reponse14;
    }
}
