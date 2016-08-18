/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Client;
import entities.Credits;
import entities.Paygotpgenerator;
import entities.Produits;
import entities.Termes;
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
public class CreditsFacade extends AbstractFacade<Credits> implements CreditsFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CreditsFacade() {
        super(Credits.class);
    }

    @Override
    public int findCopieDouble(int creditsToCount) {
        Query queryfindCopie = em.createNamedQuery("Credits.findCopieDouble");
        queryfindCopie.setParameter("filtre", creditsToCount);
        int nbreCopie = queryfindCopie.getSingleResult().hashCode();
        return nbreCopie;
    }
    
    @Override
    public int findOccurenceTermes(Termes idTermesToCount) {
        Query queryfindOccurenceTermes = em.createNamedQuery("Credits.findOccurenceTermes");
        queryfindOccurenceTermes.setParameter("idtermes", idTermesToCount);
        int nbreCopie = queryfindOccurenceTermes.getSingleResult().hashCode();
        return nbreCopie;
    }

    @Override
    public List<Credits> findAllFreeCredits(String creditSatus0) {
        Query queryfindAllFreeCredits = em.createNamedQuery("Credits.findAllFreeCredits");
        queryfindAllFreeCredits.setParameter("creditstatus", creditSatus0);
        List listAllFreeCredits = queryfindAllFreeCredits.getResultList();
        return listAllFreeCredits;
    }

    @Override
    public List<Credits> findAllInstalledCredit(String creditSatus3, String creditSatus4, String creditSatus1, String creditSatus2) {
        Query queryfindAllInstalledCredit = em.createNamedQuery("Credits.findAllInstalledCredits");
        queryfindAllInstalledCredit.setParameter("creditstatusActiv", creditSatus3);
        queryfindAllInstalledCredit.setParameter("creditstatusDesactiv", creditSatus4);
        queryfindAllInstalledCredit.setParameter("creditstatusWaitAcompt", creditSatus1);
        queryfindAllInstalledCredit.setParameter("creditstatusWaitInstall", creditSatus2);
        List listAllInstalledCredits = queryfindAllInstalledCredit.getResultList();
        return listAllInstalledCredits;
    }
    
    @Override
    public List<Credits> findAllValidForMaintenance(String creditSatus3, String creditSatus4, String creditSatus5) {
        Query queryfindAllValidForMaintenance = em.createNamedQuery("Credits.findAllValidForMaintenance");
        queryfindAllValidForMaintenance.setParameter("creditstatusActiv", creditSatus3);
        queryfindAllValidForMaintenance.setParameter("creditstatusDesactiv", creditSatus4);
        queryfindAllValidForMaintenance.setParameter("creditstatusRembourse", creditSatus5);
        List listAllValidForMaintenance = queryfindAllValidForMaintenance.getResultList();
        return listAllValidForMaintenance;
    }

    @Override
    public List<Credits> findAllWaitinInstall(String creditSatus2) {
        Query queryfindAllFreeCredits = em.createNamedQuery("Credits.findAllFreeCredits");
        queryfindAllFreeCredits.setParameter("creditstatus", creditSatus2);
        List listAllFreeCredits = queryfindAllFreeCredits.getResultList();
        return listAllFreeCredits;
    }
    
    @Override
    public List<Credits> findAllCredits() {
        Query queryfindAllCredits = em.createNamedQuery("Credits.findAllCredits");
        List listAllCredits = queryfindAllCredits.getResultList();
        return listAllCredits;
    }

    @Override
    public String findPAYGidprod(int numBoxCode) {
        Query queryfindPAYGidprod = em.createNamedQuery("Credits.findPAYGidprod");
        queryfindPAYGidprod.setParameter("numinstall", numBoxCode);
        String myPAYGidprod = queryfindPAYGidprod.getSingleResult().toString();
        return myPAYGidprod;
    }

    @Override
    public int findNumInstForFirstCode(Paygotpgenerator idPaygProductObject) {
        Query queryfindNumInstForFirstCode = em.createNamedQuery("Credits.findNumInstallForFirstCode");
        queryfindNumInstForFirstCode.setParameter("idpaygproduct", idPaygProductObject);
        int myNumInstall = queryfindNumInstForFirstCode.getSingleResult().hashCode();
        return myNumInstall;
    }

    @Override
    public Object findOmniBoxByNumInstalls(int numBoxCode) {
        Query queryfindOmniBoxByNumInstalls = em.createNamedQuery("Credits.findOmniBoxByNuminstall");
        queryfindOmniBoxByNumInstalls.setParameter("numbox", numBoxCode);
        Object myPAYGidprod = queryfindOmniBoxByNumInstalls.getSingleResult();
        return myPAYGidprod;
    }

    @Override
    public int findPrix1Jour(int numBoxCode) {
        Query queryfindPrix1Jour = em.createNamedQuery("Credits.findPrix1Jour");
        queryfindPrix1Jour.setParameter("numbox", numBoxCode);
        int myPrix1Jour = queryfindPrix1Jour.getSingleResult().hashCode();
        return myPrix1Jour;
    }

    @Override
    public Client findClientForSMS(int numBoxCode) {
        Query queryfindClientForSMS = em.createNamedQuery("Credits.findClientForSMS");
        queryfindClientForSMS.setParameter("numinstall", numBoxCode);
        Client myClient = (Client) queryfindClientForSMS.getSingleResult();
        return myClient;
    }
    
    @Override
    public Credits findCreditByPAYGprodID(Paygotpgenerator idPAYGprodObject) {
        Query queryfindCreditByPAYGprodID = em.createNamedQuery("Credits.findByPAYGprodID");
        queryfindCreditByPAYGprodID.setParameter("idpaygproduct", idPAYGprodObject);
        Credits myCredits = (Credits) queryfindCreditByPAYGprodID.getSingleResult();
        return myCredits;
    }

    @Override
    public String findCreditStatus(int numBoxCode) {
        Query queryfindCreditStatus = em.createNamedQuery("Credits.findCreditStatus");
        queryfindCreditStatus.setParameter("numbox", numBoxCode);
        String myCreditStatus = (String) queryfindCreditStatus.getSingleResult();
        return myCreditStatus;
    }

    @Override
    public int findMontantPaye(int numBoxCode) {
        Query queryfindMontantPaye = em.createNamedQuery("Credits.findMontantPayee");
        queryfindMontantPaye.setParameter("numbox", numBoxCode);
        int myMontPaye = queryfindMontantPaye.getSingleResult().hashCode();
        return myMontPaye;
    }
    
    @Override
    public int findTotalOfCredits(int numBoxCode) {
        Query queryfindTotalOfCredits = em.createNamedQuery("Credits.findTotalOfCredits");
        queryfindTotalOfCredits.setParameter("numbox", numBoxCode);
        int myTotalCredit = queryfindTotalOfCredits.getSingleResult().hashCode();
        return myTotalCredit;
    }

    @Override
    public int findMontantRestant(int numBoxCode) {
        Query queryfindMontantRestant = em.createNamedQuery("Credits.findMontantRestant");
        queryfindMontantRestant.setParameter("numbox", numBoxCode);
        int myMontRestant = queryfindMontantRestant.getSingleResult().hashCode();
        return myMontRestant;
    }

    @Override
    public int findAcompteCreditNow(int numBoxCode) {
        Query queryfindAcompteCreditNow = em.createNamedQuery("Credits.findAcompteCreditNow");
        queryfindAcompteCreditNow.setParameter("numbox", numBoxCode);
        int myAcompteCredits = queryfindAcompteCreditNow.getSingleResult().hashCode();
        return myAcompteCredits;
    }
    
    @Override
    public int findAcompteTotalNow(int numBoxCode) {
        Query queryfindAcompteTotalNow = em.createNamedQuery("Credits.findAcompteTotalNow");
        queryfindAcompteTotalNow.setParameter("numbox", numBoxCode);
        int myAcompteTotal = queryfindAcompteTotalNow.getSingleResult().hashCode();
        return myAcompteTotal;
    }

    @Override
    public int findNbreJrGraceRestNow(int numBoxCode) {
        Query queryfindNbreJrGraceNow = em.createNamedQuery("Credits.findNbreJrGraceRestNow");
        queryfindNbreJrGraceNow.setParameter("numbox", numBoxCode);
        int myJrGraceRestant = queryfindNbreJrGraceNow.getSingleResult().hashCode();
        return myJrGraceRestant;
    }

    @Override
    public int findNbreJrGraceUsedNow(int numBoxCode) {
        Query queryfindNbreJrGraceUsedNow = em.createNamedQuery("Credits.findNbreJrGraceUsedNow");
        queryfindNbreJrGraceUsedNow.setParameter("numbox", numBoxCode);
        int myJrGraceUsed = queryfindNbreJrGraceUsedNow.getSingleResult().hashCode();
        return myJrGraceUsed;
    }

    @Override
    public Date findCodeEndDateNow(int numBoxCode) {
        Query queryfindCodeEndDateNow = em.createNamedQuery("Credits.findCodeEndDateNow");
        queryfindCodeEndDateNow.setParameter("numbox", numBoxCode);
        Date myCodeEndDate = (Date) queryfindCodeEndDateNow.getSingleResult();
        return myCodeEndDate;
    }

    @Override
    public int findToUpdateLastCode(int numBoxCode, String codeGeneratedByOMNI) {
        Query queryUpdateLastCode = em.createNamedQuery("Credits.updateLastCode");
        queryUpdateLastCode.setParameter("lastcode", codeGeneratedByOMNI);
        queryUpdateLastCode.setParameter("numinstall", numBoxCode);
        int reponseUpdateLastCode = queryUpdateLastCode.executeUpdate();
        return reponseUpdateLastCode;
    }

    @Override
    public int findToUpdateMontPayeAndRest(int numBoxCode, int montantpayeRefresh, int montantrestantRefresh) {
        Query queryfindToUpdateMontPayeAndRest = em.createNamedQuery("Credits.updateMontantPayeAndRest");
        queryfindToUpdateMontPayeAndRest.setParameter("totalmontantpaye", montantpayeRefresh);
        queryfindToUpdateMontPayeAndRest.setParameter("totalmontantrestant", montantrestantRefresh);
        queryfindToUpdateMontPayeAndRest.setParameter("numinstall", numBoxCode);
        int reponseUpdateMontantPayeAndRest = queryfindToUpdateMontPayeAndRest.executeUpdate();
        return reponseUpdateMontantPayeAndRest;
    }

    @Override
    public int findToUpdateGraceRest(int numBoxCode, int newNbreJrGraceRest) {
        Query queryfindToUpdateGraceRest = em.createNamedQuery("Credits.updateGraceRestant");
        queryfindToUpdateGraceRest.setParameter("totalgracerestant", newNbreJrGraceRest);
        queryfindToUpdateGraceRest.setParameter("numinstall", numBoxCode);
        int reponseUpdateGraceRest = queryfindToUpdateGraceRest.executeUpdate();
        return reponseUpdateGraceRest;
    }

    @Override
    public int findToUpdateGraceUsed(int numBoxCode, int newNbreJrGraceUsed) {
        Query queryfindToUpdateGraceUsed = em.createNamedQuery("Credits.updateGraceUsed");
        queryfindToUpdateGraceUsed.setParameter("totalgraceused", newNbreJrGraceUsed);
        queryfindToUpdateGraceUsed.setParameter("numinstall", numBoxCode);
        int reponseUpdateGraceUsed = queryfindToUpdateGraceUsed.executeUpdate();
        return reponseUpdateGraceUsed;
    }

    @Override
    public int findToUpdateGraceAndEndDate(int numBoxCode, int newNbreJrGraceUsed, int newNbreJrGraceRest, Date estimEndDate, String creditSatus3) {
        Query queryfindToUpdateGraceAndEndDate = em.createNamedQuery("Credits.updateGraceAndEndDate");
        queryfindToUpdateGraceAndEndDate.setParameter("creditstatus", creditSatus3);
        queryfindToUpdateGraceAndEndDate.setParameter("codeenddate", estimEndDate);
        queryfindToUpdateGraceAndEndDate.setParameter("totalgraceused", newNbreJrGraceUsed);
        queryfindToUpdateGraceAndEndDate.setParameter("totalgracerestant", newNbreJrGraceRest);
        queryfindToUpdateGraceAndEndDate.setParameter("numinstall", numBoxCode);
        int reponseUpdateGraceUsed = queryfindToUpdateGraceAndEndDate.executeUpdate();
        return reponseUpdateGraceUsed;
    }

    @Override
    public int findToUpdateEndDate(int numBoxCode, Date estimEndDate) {
        Query queryfindToUpdateEndDate = em.createNamedQuery("Credits.updateEndDate");
        queryfindToUpdateEndDate.setParameter("codeenddate", estimEndDate);
        queryfindToUpdateEndDate.setParameter("numinstall", numBoxCode);
        int reponseUpdateEndDate = queryfindToUpdateEndDate.executeUpdate();
        return reponseUpdateEndDate;
    }

    @Override
    public int findToUpdateDateInstall(int numBoxCode, Date dateTransact) {
        Query queryfindToUpdateDateInstall = em.createNamedQuery("Credits.updateDateInstall");
        queryfindToUpdateDateInstall.setParameter("installdate", dateTransact);
        queryfindToUpdateDateInstall.setParameter("numinstall", numBoxCode);
        int reponseUpdateEndDate = queryfindToUpdateDateInstall.executeUpdate();
        return reponseUpdateEndDate;
    }

    @Override
    public int findToUpdateCodeEndDate(int numBoxCode, Date estimEndDate) {
        Query queryfindToUpdateCodeEndDate = em.createNamedQuery("Credits.updateCodeEndDate");
        queryfindToUpdateCodeEndDate.setParameter("codeenddate", estimEndDate);
        queryfindToUpdateCodeEndDate.setParameter("numinstall", numBoxCode);
        int reponseUpdateGraceUsed = queryfindToUpdateCodeEndDate.executeUpdate();
        return reponseUpdateGraceUsed;
    }

    @Override
    public int findToUpdateCreditStatus(int numBoxCode, String creditStatFilterON) {
        Query queryfindToUpdateCreditStatus = em.createNamedQuery("Credits.updateCreditStatus");
        queryfindToUpdateCreditStatus.setParameter("creditstatus", creditStatFilterON);
        queryfindToUpdateCreditStatus.setParameter("numinstall", numBoxCode);
        int reponseUpdateCredStatus = queryfindToUpdateCreditStatus.executeUpdate();
        return reponseUpdateCredStatus;
    }

    @Override
    public int findToUpdateCredStatAfterAutoCDT(Paygotpgenerator idPAYGprodObject, String creditStatFilterOFF) {
        Query queryfindToUpdateCredStatAfterAutoCDT = em.createNamedQuery("Credits.updateCredStatAfterAutoCDT");
        queryfindToUpdateCredStatAfterAutoCDT.setParameter("creditstatus", creditStatFilterOFF);
        queryfindToUpdateCredStatAfterAutoCDT.setParameter("idpaygproduct", idPAYGprodObject);
        int reponseUpdateCredStatAfterAutoCDT = queryfindToUpdateCredStatAfterAutoCDT.executeUpdate();
        return reponseUpdateCredStatAfterAutoCDT;
    }

    @Override
    public int updateCredStatAfterNewInst(Paygotpgenerator idPaygProductObject, String creditStatFilterON) {
        Query queryfindToUpdateCreditStatus = em.createNamedQuery("Credits.updateCredStatusAfterNewInst");
        queryfindToUpdateCreditStatus.setParameter("creditstatus", creditStatFilterON);
        queryfindToUpdateCreditStatus.setParameter("idpaygproduct", idPaygProductObject);
        int reponseUpdateCredStatus = queryfindToUpdateCreditStatus.executeUpdate();
        return reponseUpdateCredStatus;
    }

    @Override
    public int findToUpdateCreditNewContract(Client idclientPotentielIN, Produits idproduitsIN, Termes idtermesIN, int numInstallIN) {
        Query queryupdateCredNewCon = em.createNamedQuery("Credits.updateCreditsNewContract");
        queryupdateCredNewCon.setParameter("numinstall", numInstallIN);
        queryupdateCredNewCon.setParameter("idtermes", idtermesIN);
        queryupdateCredNewCon.setParameter("idproduits", idproduitsIN);
        queryupdateCredNewCon.setParameter("idclient", idclientPotentielIN);
        int reponseQry = queryupdateCredNewCon.executeUpdate();
        return reponseQry;
    }

    @Override
    public int findToUpdateCredNewContr1(int numInstallIN, int montantrestantAUTO, int montantPayeAUTO, int montantTotalCreditAUTO, int qteTorcheSupCreditIN, int qteRadioSupCreditIN, int qteLampeSupCreditIN, int qteLampeSupCashIN, int qteRadioSupCashIN, int qteTorcheSupCashIN, int prix1MoisAUTO, int prix1JourAUTO, int acompteTotalAUTO, int acompteCreditAUTO, String creditSatus1) {
        Query queryupdateCredNewCon = em.createNamedQuery("Credits.updateCredNewContr1");
        queryupdateCredNewCon.setParameter("numinstall", numInstallIN);
        queryupdateCredNewCon.setParameter("totalmontantrestant", montantrestantAUTO);
        queryupdateCredNewCon.setParameter("totalmontantpaye", montantPayeAUTO);
        queryupdateCredNewCon.setParameter("totalcredits", montantTotalCreditAUTO);
        queryupdateCredNewCon.setParameter("qteappsupcredit3", qteTorcheSupCreditIN);
        queryupdateCredNewCon.setParameter("qteappsupcredit2", qteRadioSupCreditIN);
        queryupdateCredNewCon.setParameter("qteappsupcredit1", qteLampeSupCreditIN);
        queryupdateCredNewCon.setParameter("qteappsupcash3", qteTorcheSupCashIN);
        queryupdateCredNewCon.setParameter("qteappsupcash2", qteRadioSupCashIN);
        queryupdateCredNewCon.setParameter("qteappsupcash1", qteLampeSupCashIN);
        queryupdateCredNewCon.setParameter("prix1mois", prix1MoisAUTO);
        queryupdateCredNewCon.setParameter("prix1jour", prix1JourAUTO);
        queryupdateCredNewCon.setParameter("acomptetotal", acompteTotalAUTO);
        queryupdateCredNewCon.setParameter("acomptecredit", acompteCreditAUTO);
        queryupdateCredNewCon.setParameter("creditstatus", creditSatus1);
        int reponseQry = queryupdateCredNewCon.executeUpdate();
        return reponseQry;
    }

    @Override
    public int findToUpdateMontantPaye(int numBoxCode, int montantPaye) {
        Query queryfindToUpdateMontantPaye = em.createNamedQuery("Credits.updatemontantPaye");
        queryfindToUpdateMontantPaye.setParameter("numinstall", numBoxCode);
        queryfindToUpdateMontantPaye.setParameter("numinstall", montantPaye);
        int reponseUpdateLastCode = queryfindToUpdateMontantPaye.executeUpdate();
        return reponseUpdateLastCode;
    }
}
