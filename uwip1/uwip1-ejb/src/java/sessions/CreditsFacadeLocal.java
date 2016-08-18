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
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface CreditsFacadeLocal {

    void create(Credits credits);

    void edit(Credits credits);

    void remove(Credits credits);

    Credits find(Object id);

    List<Credits> findAll();
    
    List<Credits> findAllCredits();

    List<Credits> findRange(int[] range);

    int count();

    List<Credits> findAllFreeCredits(String creditSatus0);

    List<Credits> findAllInstalledCredit(String creditSatus3, String creditSatus4, String creditSatus1, String creditSatus2);

    List<Credits> findAllWaitinInstall(String creditSatus2);
    
    List<Credits> findAllValidForMaintenance(String creditSatus3, String creditSatus4, String creditSatus5);

    public int findCopieDouble(int creditsToCount);
    
    public int findOccurenceTermes(Termes idTermesToCount);

    public Date findCodeEndDateNow(int numBoxCode);

    public int findToUpdateCodeEndDate(int numBoxCode, Date estimEndDate);

    public String findPAYGidprod(int numBoxCode);

    public int findPrix1Jour(int numBoxCode);

    public String findCreditStatus(int numBoxCode);
    
    public int findTotalOfCredits(int numBoxCode);

    public int findMontantPaye(int numBoxCode);

    public int findMontantRestant(int numBoxCode);

    public int findNumInstForFirstCode(Paygotpgenerator idPaygProductObject);

    public int findToUpdateEndDate(int numBoxCode, Date estimEndDate);

    public int findToUpdateDateInstall(int numBoxCode, Date dateTransact);

    public int findToUpdateMontPayeAndRest(int numBoxCode, int montantpayeRefresh, int montantrestantRefresh);

    public int findToUpdateMontantPaye(int numBoxCode, int montantPaye);

    public int findAcompteCreditNow(int numBoxCode);
    
    public int findAcompteTotalNow(int numBoxCode);

    public int findNbreJrGraceRestNow(int numBoxCode);

    public int findNbreJrGraceUsedNow(int numBoxCode);

    public int findToUpdateGraceRest(int numBoxCode, int newNbreJrGraceRest);

    public int findToUpdateGraceUsed(int numBoxCode, int newNbreJrGraceUsed);

    public int findToUpdateGraceAndEndDate(int numBoxCode, int newNbreJrGraceUsed, int newNbreJrGraceRest, Date estimEndDate, String creditSatus3);

    public int findToUpdateCreditStatus(int numBoxCode, String creditStatFilterON);

    public int updateCredStatAfterNewInst(Paygotpgenerator idPaygProductObject, String creditStatFilterON);

    public int findToUpdateCredStatAfterAutoCDT(Paygotpgenerator idPAYGprodObject, String creditStatFilterOFF);

    public int findToUpdateLastCode(int numBoxCode, String codeGeneratedByOMNI);

    public Object findOmniBoxByNumInstalls(int numBoxCode);

    public Client findClientForSMS(int numBoxCode);
    
    public Credits findCreditByPAYGprodID(Paygotpgenerator idPAYGprodObject);

    public int findToUpdateCreditNewContract(Client idclientPotentielIN, Produits idproduitsIN, Termes idtermesIN, int numInstallIN);

    public int findToUpdateCredNewContr1(int numInstallIN, int montantrestantAUTO, int montantPayeAUTO, int montantTotalCreditAUTO, int qteTorcheSupCreditIN, int qteRadioSupCreditIN, int qteLampeSupCreditIN, int qteLampeSupCashIN, int qteRadioSupCashIN, int qteTorcheSupCashIN, int prix1MoisAUTO, int prix1JourAUTO, int acompteTotalAUTO, int acompteCreditAUTO, String creditSatus1);
}
