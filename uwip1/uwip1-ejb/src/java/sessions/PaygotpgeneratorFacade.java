/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Paygotpgenerator;
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
public class PaygotpgeneratorFacade extends AbstractFacade<Paygotpgenerator> implements PaygotpgeneratorFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaygotpgeneratorFacade() {
        super(Paygotpgenerator.class);
    }

    @Override
    public int findDataLength(String filtreCount) {
        Query queryfindDataLength = em.createNamedQuery("Paygotpgenerator.findLengthData");
        queryfindDataLength.setParameter("filtre", filtreCount);
        int nbreLigne = queryfindDataLength.getSingleResult().hashCode();
        return nbreLigne;
    }

    @Override
    public List<Paygotpgenerator> findAllFreeToInstall(String filtreNoInstallBox, String filtreYesINITBox) {
        Query queryfindAllFreeToInstall = em.createNamedQuery("Paygotpgenerator.findAllFreeTo");
        queryfindAllFreeToInstall.setParameter("boxstatus", filtreNoInstallBox);
        queryfindAllFreeToInstall.setParameter("payglifecyclestatus", filtreNoInstallBox);
        queryfindAllFreeToInstall.setParameter("initialisationstatus", filtreYesINITBox);
        List listAllFreeBoxToInstall = queryfindAllFreeToInstall.getResultList();
        return listAllFreeBoxToInstall;
    }
    
    @Override
    public List<Paygotpgenerator> findAllFreeToInitialise(String filtreNoInstallBox, String filtreNoINITBox) {
        Query queryfindAllFreeToInitialise = em.createNamedQuery("Paygotpgenerator.findAllFreeTo");
        queryfindAllFreeToInitialise.setParameter("boxstatus", filtreNoInstallBox);
        queryfindAllFreeToInitialise.setParameter("payglifecyclestatus", filtreNoInstallBox);
        queryfindAllFreeToInitialise.setParameter("initialisationstatus", filtreNoINITBox);
        List listAllFreeBoxToInitialise = queryfindAllFreeToInitialise.getResultList();
        return listAllFreeBoxToInitialise;
    }

    @Override
    public String findHRoot(String idPAYGprod) {
        Query queryfindHRoot = em.createNamedQuery("Paygotpgenerator.findHRoot");
        queryfindHRoot.setParameter("idpaygproduct", idPAYGprod);
        String myHRoot = (String) queryfindHRoot.getSingleResult();
        return myHRoot;
    }

    @Override
    public int findCurrentCindex(String idPAYGprod) {
        Query queryfindCurrentCindex = em.createNamedQuery("Paygotpgenerator.findCurrentCindex");
        queryfindCurrentCindex.setParameter("idpaygproduct", idPAYGprod);
        int myCurrentCindex = queryfindCurrentCindex.getSingleResult().hashCode();
        return myCurrentCindex;
    }

    @Override
    public int findCDT(String idPAYGprod) {
        Query queryfindCDT = em.createNamedQuery("Paygotpgenerator.findCDT");
        queryfindCDT.setParameter("idpaygproduct", idPAYGprod);
        int myCDT = queryfindCDT.getSingleResult().hashCode();
        return myCDT;
    }
    
    @Override
    public int findMaxHCJ(String idPAYGprod) {
        Query queryfindMaxHCJ = em.createNamedQuery("Paygotpgenerator.findMaxHCJ");
        queryfindMaxHCJ.setParameter("idpaygproduct", idPAYGprod);
        int myMaxHCJ = queryfindMaxHCJ.getSingleResult().hashCode();
        return myMaxHCJ;
    }

    @Override
    public int findToUpdateCDT(String idPAYGprod, int hcj_forms) {
        Query queryfindToUpdateCDT = em.createNamedQuery("Paygotpgenerator.updateCDT");
        queryfindToUpdateCDT.setParameter("otpcount", hcj_forms);
        queryfindToUpdateCDT.setParameter("idpaygproduct", idPAYGprod);
        int reponseUpdateCDT = queryfindToUpdateCDT.executeUpdate();
        return reponseUpdateCDT;
    }
    
    @Override
    public int findToUpdateFirstCode(String idPAYGprod, String codeGeneratedByOMNI) {
        Query queryfindToUpdateFirstCode = em.createNamedQuery("Paygotpgenerator.updateFirstCode");
        queryfindToUpdateFirstCode.setParameter("firstcode", codeGeneratedByOMNI);
        queryfindToUpdateFirstCode.setParameter("idpaygproduct", idPAYGprod);
        int reponseUpdateFirstcode = queryfindToUpdateFirstCode.executeUpdate();
        return reponseUpdateFirstcode;
    }
    
    @Override
    public int findToUpdateInitstatus(String idPAYGprod, String filtreYesINITBox) {
        Query queryfindToUpdateInitstatus = em.createNamedQuery("Paygotpgenerator.updateinitstatus");
        queryfindToUpdateInitstatus.setParameter("initialisationstatus", filtreYesINITBox);
        queryfindToUpdateInitstatus.setParameter("idpaygproduct", idPAYGprod);
        int reponseUpdateInitstatus = queryfindToUpdateInitstatus.executeUpdate();
        return reponseUpdateInitstatus;
    }
    
    @Override
    public int findToUpdateDateInitFirst(String idPAYGprod, Date dateTransact) {
        Query queryfindToUpdateDateInitFirst = em.createNamedQuery("Paygotpgenerator.updateDateInitFirst");
        queryfindToUpdateDateInitFirst.setParameter("dateinitialisation", dateTransact);
        queryfindToUpdateDateInitFirst.setParameter("idpaygproduct", idPAYGprod);
        int reponseUpdateDateInit = queryfindToUpdateDateInitFirst.executeUpdate();
        return reponseUpdateDateInit;
    }

    @Override
    public int updateBoxInstallInfo(String filtrePAYGmode, String filtreYesInstallBox, String idOfPAYGprod) {
        Query queryupdateBoxInstallInfo = em.createNamedQuery("Paygotpgenerator.updateBoxInstallInfo");
        queryupdateBoxInstallInfo.setParameter("boxstatus", filtreYesInstallBox);
        queryupdateBoxInstallInfo.setParameter("payglifecyclestatus", filtrePAYGmode);
        queryupdateBoxInstallInfo.setParameter("idpaygproduct", idOfPAYGprod);
        int reponseBoxInfo = queryupdateBoxInstallInfo.executeUpdate();
        return reponseBoxInfo;
    }
    
    @Override
    public int updateLastMaintenanceDate(Date datePrevueInstall, String idPAYGprod) {
        Query queryupdateLastMaintenanceDate = em.createNamedQuery("Paygotpgenerator.updateDateLastMaintenance");
        queryupdateLastMaintenanceDate.setParameter("lastmaintenance", datePrevueInstall);
        queryupdateLastMaintenanceDate.setParameter("idpaygproduct", idPAYGprod);
        int reponseUpdateLastMaint = queryupdateLastMaintenanceDate.executeUpdate();
        return reponseUpdateLastMaint;
    }
}
