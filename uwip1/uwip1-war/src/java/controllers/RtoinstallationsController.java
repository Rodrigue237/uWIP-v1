/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.AfricasTalkingGateway;
import classes.DateOperations;
import classes.ShaHash;
import entities.Client;
import entities.Code;
import entities.Credits;
import entities.Mmtransaction;
import entities.Paygcodes;
import entities.Paygotpgenerator;
import entities.Produits;
import entities.Termes;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.inject.Inject;
import org.json.JSONArray;
import org.json.JSONObject;
import sessions.ClientFacadeLocal;
import sessions.CodeFacadeLocal;
import sessions.CreditsFacadeLocal;
import sessions.MmtransactionFacadeLocal;
import sessions.PaygcodesFacadeLocal;
import sessions.PaygotpgeneratorFacadeLocal;
import sessions.ProduitsFacadeLocal;
import sessions.TermesFacadeLocal;
//import org.primefaces.event.SelectEvent;
//import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Seanjackson
 */
@Named(value = "rtoinstallationsController")
@SessionScoped
public class RtoinstallationsController implements Serializable {

    @Inject
    private CreditsFacadeLocal creditsFacade;
    private List<Credits> listWaitingInstallCredits = new ArrayList<Credits>();
    private List<Credits> listInstalledCredits = new ArrayList<Credits>();
    private List<Credits> listValidForMaintenance = new ArrayList<Credits>();
    private List<Credits> listCredits = new ArrayList<Credits>();
    private Credits credits = new Credits();
    private Credits selectedCredits = new Credits();
    @Inject
    private PaygotpgeneratorFacadeLocal paygotpgeneratorFacade;
    private List<Paygotpgenerator> listNotInstallOMNIBOX = new ArrayList<Paygotpgenerator>();
    private List<Paygotpgenerator> listNotInitialiseOMNIBOX = new ArrayList<Paygotpgenerator>();
    private List<Paygotpgenerator> listAllBoxsLucioles = new ArrayList<Paygotpgenerator>();
    private Paygotpgenerator paygotpgenerator = new Paygotpgenerator();
    @Inject
    private MmtransactionFacadeLocal mmtransactionFacade;
    private List<Mmtransaction> listMmtransaction = new ArrayList<Mmtransaction>();
    private Mmtransaction mmtransaction = new Mmtransaction();
    @Inject
    private ClientFacadeLocal clientFacade;
    private List<Client> listClientPotentiel = new ArrayList<Client>();
    private Client client = new Client();
    @Inject
    private ProduitsFacadeLocal produitsFacade;
    private List<Produits> listProduitSystem = new ArrayList<Produits>();
    private Produits produits = new Produits();
    @Inject
    private TermesFacadeLocal termesFacade;
    private List<Termes> listTermes = new ArrayList<Termes>();
    private Termes termes = new Termes();
    @Inject
    private PaygcodesFacadeLocal paygcodesFacade;
    private List<Paygcodes> listPaygcodes = new ArrayList<Paygcodes>();
    private Paygcodes paygcodes = new Paygcodes();
    @Inject
    private CodeFacadeLocal codeFacade;
    private List<Code> listCode = new ArrayList<Code>();
    private Code code = new Code();
    private boolean showMessage = false;
    private String message = "";
    // Variable for RTO
    private String creditSatus0 = "- -";
    private String creditSatus1 = "En attente d'acompte";
    private String creditSatus2 = "En attente d'installation";
    private String creditSatus3 = "Activé";
    private String creditSatus4 = "Desactivé";
    private String creditSatus5 = "Remboursé";
    private String filtreNoInstallBox = "FREE";
    private String filtreYesInstallBox = "USED";
    private String filtreYesINITBox = "YES";
    private String filtreNoINITBox = "NO";
    private String filtrePAYGmode = "PAYG";
    private Double latitudeIN = 0.0;
    private Double longitudeIN = 0.0;
    private String idTransactIN = "";
    private String refManualCode = "MC";
    private String finalSMSstatut = "";
    private String strNull = "''";
    private String statutTransact = "Pas de transaction";
    private int maxIDPlus1 = 0;
    private int manualOperatorID = 3;
    private String statutCode = "ECHEC";
    private int numBoxCode = 0;
    private String idPAYGprod = "NoID";
    private String idPAYGprodNew = "NoID";
    private String filtreIdPAYG = "NoID";
    private String filtreIdPAYG2 = "- -";
    private int prix1jour = 0;
    //Variable Pour OmniVoltaic Code
    private String h_RootForm = "";
    private int k_forms = 0;
    private int maxHCJ = 0;
    private int i_forms = 0;
    private int hcj_forms = 0;
    private int nbreJourSup = 0;
    private int c_default = 100000;
    private int c_currentIndexForm = 0;
    private String hexToDec_forms = "";
    private String finalHTop = "";
    private String finalDecimal = "";
    private String finalCodeDec = "";
    private String messagePOP = "";
    private boolean showMessagePOP = false;
    private String strprix1jour = "";
    private String codeGeneratedByOMNI = "";
    private String sDateLastEnd = "";
    private String sDateNow = "";
    private Date dateTransact;
    private Date dateDuJour;
    private Date datePrevueInstall;
    private Date dateTransact0;
    private Date dateToday;
    private Date dateValidInstall;
    private Date estimEndDate;
    private Date previousEstimEndDate;
    private Date boxEstimEndDate;
    private Date estimInstallDate;
    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    static DateFormat dateFormatSMS = new SimpleDateFormat("dd-MM-yyyy");
    // Variable PAYG and RTO
    private String creditStatusNow = "";
    private String creditStatFilterOFF = "Disabled";
    private String creditStatFilterON = "Enabled";
    private int reqUpdateCredits = 0;
    private int montantTotalPayeNow = 0;
    private int montantTotalDuCredit = 0;
    private int acompteCreditNow = 0;
    private int prixUniteINT = 0;
    private int reqUpdateCreditStatus = 0;
    private int nowCDT = 0;
    private int numInstallIN = 0;
    private int totalCreditRembourse = 0;
    private int totalCreditRestant = 0;
    private int montantpayeRefresh = 0;
    private int montantrestantRefresh = 0;
    private int acompteRestantPourInstall = 0;
    private String statutCodeGeneration = "";
    private String idTrans = "";
    private String numPhone = "";
    private String prixUnite = "";
    private String dateTrans = "";
    private String endDateForSMS = "";
    private String username = "upowa";
    private String apiKey = "86fbfe36157a6d75796f038f28f611ef00a9165c29eab9dde0d2555e8df47403";
    private String shortname = "UPOWA";
    private String codePays = "+237";
    private String recipients = "";
    private String phoneSAV = "+237691415574";
    private String statusTransfertSMStoSAV = "";
    private String statutSMSsending = "ECHEC";
    private String statusSMSsending = "";
    private String messageToParse = "";
    private StringBuffer strBuff = new StringBuffer();
    private boolean showStatusSMSsending = false;

    /**
     * Creates a new instance of RtoinstallationsController
     */
    public RtoinstallationsController() {
    }

    public void prepareEdit() {
        paygotpgenerator = new Paygotpgenerator();
        credits = new Credits();
        nbreJourSup = 0;
    }

    public void prepareCreateManualCode() {
        prixUnite = "";
        idTransactIN = "";
        numPhone = "";
        paygcodes = new Paygcodes();
        idPAYGprod = filtreIdPAYG;

    }

    //Page des installations en attente
    public String waitinginstallations() {
        listWaitingInstallCredits.clear();
        listWaitingInstallCredits.addAll(creditsFacade.findAllWaitinInstall(creditSatus2));
        listCredits.clear();
        listCredits.addAll(creditsFacade.findAll());
        chargeOMNIBOXListFreeToInstall();
        prepareCreateManualCode();
        prepareEdit();
        return "waitinginstallations.xhtml?faces-redirect=true";
    }
    
    //Page des maintenance RTO2
    public String maintenanceRTO() {
        listValidForMaintenance.clear();
        listValidForMaintenance.addAll(creditsFacade.findAllValidForMaintenance(creditSatus3, creditSatus4, creditSatus5));
        listCredits.clear();
        listCredits.addAll(creditsFacade.findAll());
        chargeOMNIBOXListFreeToInstall();
        prepareCreateManualCode();
        prepareEdit();
        return "maintenance-rto.xhtml?faces-redirect=true";
    }

    //Page d'initialisation des box
    public String pageinitialisationboxs() {
        chargeOMNIBOXListFreeToInitialise();
        prepareCreateManualCode();
        prepareEdit();
        return "initialisationboxs.xhtml?faces-redirect=true";
    }

    //Page all initialised box free to install
    public String pageallfreeBoxToInstall() {
        chargeOMNIBOXListFreeToInstall();
        prepareCreateManualCode();
        prepareEdit();
        return "boxsinitialisees.xhtml?faces-redirect=true";
    }
    
    //Page all initialised box free to install
    public String pageAllBoxsLucioles() {
        chargeListAllBoxsLucioles();
        return "boxslucioles.xhtml?faces-redirect=true";
    }

    //Page de gestion des transactions manuelles
    public String rtocodes() {
        listPaygcodes.clear();
        listPaygcodes.addAll(paygcodesFacade.findAll());
        chargeListInstalledCredit();
        prepareCreateManualCode();
        prepareEdit();
        return "rto-codes.xhtml?faces-redirect=true";
    }

    //Page de gestion des transactions manuelles
    public String pageMaintenanceRTO() {
        listValidForMaintenance.clear();
        listValidForMaintenance.addAll(creditsFacade.findAllValidForMaintenance(creditSatus3, creditSatus4, creditSatus5));
        chargeOMNIBOXListFreeToInstall();
        return "maintenances-rto.xhtml?faces-redirect=true";
    }

    public void chargeListInstalledCredit() {
        listInstalledCredits.clear();
        listInstalledCredits.addAll(creditsFacade.findAllInstalledCredit(creditSatus3, creditSatus4, creditSatus1, creditSatus2));
    }

    public void chargeOMNIBOXListFreeToInstall() {
        listNotInstallOMNIBOX.clear();
        listNotInstallOMNIBOX.addAll(paygotpgeneratorFacade.findAllFreeToInstall(filtreNoInstallBox, filtreYesINITBox));
    }
    
    public void chargeListAllBoxsLucioles() {
        listAllBoxsLucioles.clear();
        listAllBoxsLucioles.addAll(paygotpgeneratorFacade.findAll());
    }

    public void chargeOMNIBOXListFreeToInitialise() {
        listNotInitialiseOMNIBOX.clear();
        listNotInitialiseOMNIBOX.addAll(paygotpgeneratorFacade.findAllFreeToInitialise(filtreNoInstallBox, filtreNoINITBox));
    }

    public void chargeAllValidForMaintenance() {
        listValidForMaintenance.clear();
        listValidForMaintenance.addAll(creditsFacade.findAllValidForMaintenance(creditSatus3, creditSatus4, creditSatus5));
    }

    //Echange de box
    public String echangeBoxCredit() {
        try {
            System.out.println("Debut Echange Box");
            idPAYGprod = this.credits.getIdpaygproduct().getIdpaygproduct();//Id PAYG box sortante
            longitudeIN = this.credits.getIdpaygproduct().getLongitude();
            latitudeIN = this.credits.getIdpaygproduct().getLatitude();
            System.out.println("idPAYG: " + idPAYGprod);
            paygotpgenerator.setIdpaygproduct(idPAYGprodNew);
            System.out.println("Etape 1 OK");
            //creationCodeEchangeBoxLuciole();
            credits.setIdpaygproduct(paygotpgenerator);
            System.out.println("Etape 2 OK");
            creditsFacade.edit(credits);
            System.out.println("Etape 3 OK");
            System.out.println("Succes de l'echange");
            creationCodeEchangeBoxLuciole();
            int reqUpdateCreditsAndInstallStatus = creditsFacade.updateCredStatAfterNewInst(paygotpgenerator, creditSatus3);
            System.out.println("Total update CreditsStat After New Install: " + reqUpdateCreditsAndInstallStatus);
            initialisationCodeUssdLuciole();
            paygcodesFacade.create(paygcodes);
            int reqUpdateCurrentC = paygcodesFacade.findToUpdateCurrentC(k_forms, idPAYGprodNew);
            System.out.println("Total updated CurrentC: " + reqUpdateCurrentC);
            int reqUpdateLastCode = creditsFacade.findToUpdateLastCode(numBoxCode, codeGeneratedByOMNI);
            System.out.println("Total updated LastCode: " + reqUpdateLastCode);
            int reqUpdateBoxInstallInfoIN = paygotpgeneratorFacade.updateBoxInstallInfo(filtrePAYGmode, filtreYesInstallBox, idPAYGprodNew);
            System.out.println("Total update Box Info Of New Box: " + reqUpdateBoxInstallInfoIN);
            int reqUpdateBoxInstallInfoOUT = paygotpgeneratorFacade.updateBoxInstallInfo(filtrePAYGmode, filtreNoInstallBox, idPAYGprod);
            System.out.println("Total update Box Info Of Previous Install: " + reqUpdateBoxInstallInfoOUT);
            showMessage = true;
            message = "Box échangée avec succès.";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'echange";
            e.printStackTrace();
        }
        return pageMaintenanceRTO();
    }

    //Validation installation
    public String editInstallationCredit() {
        try {
            System.out.println("Debut Edit Credit IdPAYG prod");
            idPAYGprod = this.credits.getIdpaygproduct().getIdpaygproduct();
            System.out.println("idPAYG: " + idPAYGprod);
            if (idPAYGprod.equals(filtreIdPAYG) || idPAYGprod.equals(filtreIdPAYG2)) {
                showMessage = true;
                message = "Désolé. Cette installation n'a pas de box affectée. Veillez affecter une box avant.";
            } else {
                paygotpgenerator.setIdpaygproduct(idPAYGprod);
                System.out.println("Etape 1 OK");
                credits.setIdpaygproduct(paygotpgenerator);
                System.out.println("Etape 2 OK");
                creditsFacade.edit(credits);
                System.out.println("Etape 3 OK");
                System.out.println("Succes nouvelle installation");
                updateMokboxAndCreditsInfo();
                creation2emCodeUssdLucioleAjust();
                //initialisationCodeUssdLuciole();
                //paygcodesFacade.create(paygcodes);
                //upNextKeyUssdLuciole();
                showMessage = true;
                message = "Succès installation et " + statutCode;
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec nouvelle installation";
            e.printStackTrace();
        }
        return waitinginstallations();
    }
    
    //Validation l'echange de box
    public String validerEchangeBoxCredit() {
        try {
            System.out.println("Debut Edit Credit IdPAYG prod");
            idPAYGprod = this.credits.getIdpaygproduct().getIdpaygproduct();
            System.out.println("idPAYG: " + idPAYGprod);
            if (idPAYGprod.equals(filtreIdPAYG) || idPAYGprod.equals(filtreIdPAYG2)) {
                showMessage = true;
                message = "Désolé. Cette installation n'a pas de box affectée. Veillez affecter une box avant.";
            } else {
                paygotpgenerator.setIdpaygproduct(idPAYGprod);
                System.out.println("Etape 1 OK");
                credits.setIdpaygproduct(paygotpgenerator);
                System.out.println("Etape 2 OK");
                creditsFacade.edit(credits);
                System.out.println("Etape 3 OK");
                System.out.println("Succes de l'echange");
                creation2emCodeUssdLucioleAjust();
                showMessage = true;
                message = "Succès echange et " + statutCode;
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'echange";
            e.printStackTrace();
        }
        return pageMaintenanceRTO();
    }

    //Validation preparation system
    public String validationPrepaSystemCredit() {
        try {
            System.out.println("Debut Validation system Credit");
            idPAYGprod = this.credits.getIdpaygproduct().getIdpaygproduct();
            System.out.println("idPAYG: " + idPAYGprod);
            if (idPAYGprod.equals(filtreIdPAYG) || idPAYGprod.equals(filtreIdPAYG2)) {
                showMessage = true;
                message = "Désolé. Cette installation n'a pas de box affectée. Veillez affecter une box avant.";
            } else {
                paygotpgenerator.setIdpaygproduct(idPAYGprod);
                System.out.println("Etape 1 OK");
                credits.setIdpaygproduct(paygotpgenerator);
                System.out.println("Etape 2 OK");
                creditsFacade.edit(credits);
                System.out.println("Etape 3 OK");
                System.out.println("Succes nouvelle installation");
                updateMokboxInfo();
                String Status1Code = save1erUSSDCodeAutomaticLuciole();
                showMessage = true;
                message = "Succès preparation et code " + Status1Code;
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec nouvelle installation";
            e.printStackTrace();
        }
        return waitinginstallations();
    }

    //Validation installation system
    public String validationInstallSystemCredit() {
        try {
            System.out.println("Debut Validation Install system Credit");
            idPAYGprod = this.credits.getIdpaygproduct().getIdpaygproduct();
            System.out.println("idPAYG: " + idPAYGprod);
            if (idPAYGprod.equals(filtreIdPAYG) || idPAYGprod.equals(filtreIdPAYG2)) {
                showMessage = true;
                message = "Désolé. Cette installation n'a pas de box affectée. Veillez affecter une box avant.";
            } else {
                paygotpgenerator.setIdpaygproduct(idPAYGprod);
                System.out.println("Etape 1 OK");
                credits.setIdpaygproduct(paygotpgenerator);
                System.out.println("Etape 2 OK");
                creditsFacade.edit(credits);
                System.out.println("Etape 3 OK");
                System.out.println("Succes nouvelle installation");
                updateMokboxInfo();
                String Status1Code = save1erUSSDCodeAutomaticLuciole();
                showMessage = true;
                message = "Succès preparation et code " + Status1Code;
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec nouvelle installation";
            e.printStackTrace();
        }
        return waitinginstallations();
    }

    public String editNewBoxForCredit() {
        try {
            System.out.println("idPAYG: " + idPAYGprod);
            paygotpgenerator.setIdpaygproduct(idPAYGprod);
            System.out.println("Etape 1 OK");
            credits.setIdpaygproduct(paygotpgenerator);
            System.out.println("Etape 2 OK");
            creditsFacade.edit(credits);
            apercu1erCodeUssdLuciole();
            credits.setLastcode(codeGeneratedByOMNI);
            System.out.println("Etape 3 OK");
            creditsFacade.edit(credits);
            System.out.println("Etape 4 OK");
            System.out.println("Succes nouvelle affectation de Box");
            showMessage = true;
            message = "Nouvelle box affectée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec affectation de box";
            e.printStackTrace();
        }
        return waitinginstallations();
    }

    public void updateMokboxAndCreditsInfo() {
        int reqUpdateCreditsAndInstallStatus = creditsFacade.updateCredStatAfterNewInst(paygotpgenerator, creditSatus3);
        System.out.println("Total update of CreditsStat After New Install: " + reqUpdateCreditsAndInstallStatus);
        int reqUpdateBoxInstallInfo = paygotpgeneratorFacade.updateBoxInstallInfo(filtrePAYGmode, filtreYesInstallBox, credits.getIdpaygproduct().getIdpaygproduct());
        System.out.println("Total update Box Info After New Install: " + reqUpdateBoxInstallInfo);
    }

    public void updateMokboxInfo() {
        int reqUpdateBoxInstallInfo = paygotpgeneratorFacade.updateBoxInstallInfo(filtrePAYGmode, filtreYesInstallBox, credits.getIdpaygproduct().getIdpaygproduct());
        System.out.println("Total update Box Info After New Install: " + reqUpdateBoxInstallInfo);
    }

    // Generation code luciole
    public String saveManualTransactionLuciole() {
        try {
            numBoxCode = numInstallIN;
            //creditStatusNow = creditsFacade.findCreditStatus(numBoxCode);
            //System.out.println("Credit Status Now: " + creditStatusNow);
            initialiseIdTranasct();
            initialisationTransaction();
            mmtransactionFacade.create(mmtransaction);
            //creationCodeUssdLuciole();
            //initialisationCodeUssdLuciole();
            //paygcodesFacade.create(paygcodes);
            //upNextKeyUssdLuciole();
            //updateCreditInfosAfterManualCode();
            showMessage = true;
            message = "SUCCES Nouveau code";
        } catch (Exception e) {
            showMessage = true;
            message = "ECHEC Nouveau code";
            e.printStackTrace();
        }
        initialiseNumPhone();
        try {
            System.out.println("Num Install before check Credit stat: " + numBoxCode);
            creditStatusNow = creditsFacade.findCreditStatus(numBoxCode);
            System.out.println("Credit Status Now: " + creditStatusNow);
            idPAYGprod = codeFacade.findPaygIdprod(numBoxCode);
            System.out.println("PAYG Id by codeFace:" + idPAYGprod);
            //Recuperation du montant total paye actuellement en BD
            montantTotalDuCredit = creditsFacade.findTotalOfCredits(numBoxCode);
            System.out.println("Montant total du credit(Pour deblocage): " + montantTotalDuCredit);
            montantTotalPayeNow = creditsFacade.findMontantPaye(numBoxCode);
            System.out.println("Montant total paye (precedemment): " + montantTotalPayeNow);
            totalCreditRestant = creditsFacade.findMontantRestant(numBoxCode);
            System.out.println("Montant total restant a payer (precedemment): " + totalCreditRestant);
            prixUniteINT = Integer.parseInt(prixUnite);
            System.out.println("Montant de la transaction(Actuelle): " + prixUniteINT);
            //Cumul du montant total paye
            montantTotalPayeNow = montantTotalPayeNow + prixUniteINT;
            System.out.println("Montant total paye apres la transact: " + montantTotalPayeNow);
            maxHCJ = paygotpgeneratorFacade.findMaxHCJ(idPAYGprod);
            System.out.println("Max HCJ for FREE use box: " + maxHCJ);
            //Operation si la somme payee est = a la totalite a payer
            if (montantTotalPayeNow == montantTotalDuCredit) {
                System.out.println("DEBUT Operation pour code de deblocage suite a l'atteinte du remboursement");
                statutCodeGeneration = saveManualCodeLucioleDebloc(maxHCJ);
                statutTransact = statutCodeGeneration;
                message = "MMerci pour votre paiement de " + prixUnite + ".00 FCFA. Entrez le code " + codeGeneratedByOMNI + " pour debloquer l'installation " + numBoxCode + " a vie. Montant total rembourse " + montantTotalPayeNow + ".00 FCFA. Felicitation.";
                recipients = codePays + numPhone;
                statutSMSsending = sendSMS(username, apiKey, recipients, message, shortname);
                statusTransfertSMStoSAV = sendSMS(username, apiKey, phoneSAV, message, shortname);
                statutTransact = "SUCCES";
                System.out.println("Fin de code de deblocage");
                //Fin Operation de generation de code de deblocage
            } else {
                if (creditStatusNow.equals(creditSatus1) || creditStatusNow.equals(creditSatus2)) {
                    montantrestantRefresh = totalCreditRestant - prixUniteINT;
                    System.out.println("Montant total restant apres la transact: " + montantrestantRefresh);
                    // Mise a jour du montant total paye
                    reqUpdateCredits = creditsFacade.findToUpdateMontPayeAndRest(numBoxCode, montantTotalPayeNow, montantrestantRefresh);
                    System.out.println("Total update Rest and Paye Montant: " + reqUpdateCredits);
                    acompteCreditNow = creditsFacade.findAcompteTotalNow(numBoxCode);
                    System.out.println("Acompte Credit After update: " + acompteCreditNow);
                    acompteRestantPourInstall = acompteCreditNow - montantTotalPayeNow;
                    prixUniteINT = (montantTotalPayeNow - acompteCreditNow);
                    System.out.println("Difference entre MontPaye et Acompte credit: " + prixUniteINT);
                    // Voir si la difference est positiv pour Changer le Credit Status
                    if (prixUniteINT >= 0) {
                        //Activation du status credit en Status2 (Attente d'installation)
                        reqUpdateCreditStatus = creditsFacade.findToUpdateCreditStatus(numBoxCode, creditSatus2);
                        System.out.println("Nbre de credit stat update to CreditStaus2: " + reqUpdateCreditStatus);
                        // Initialisation du message de confirmation pour futur installation
                        message = "MMerci pour votre paiement de " + prixUnite + "FCFA. Montant total paye " + montantTotalPayeNow + "FCFA. Un representant d'upOwa prendra contact avec vous dans un delai de 2 jours ouvre pour oorganiser l'installation de votre systeme upOwa.";
                        recipients = codePays + numPhone;
                        statutSMSsending = sendSMS(username, apiKey, recipients, message, shortname);
                        statusTransfertSMStoSAV = sendSMS(username, apiKey, phoneSAV, message, shortname);
                        statutTransact = "SUCCES";
                        System.out.println("End of update of Acompte and Wait Installation for generate 1st code");
                    } else {
                        // Initialisation du message de confirmation pour acompte pas encore suffisant pour installation
                        message = "MMerci pour votre paiement de " + prixUnite + ".00 FCFA. Montant total paye " + montantTotalPayeNow + ".00 FCFA, montant restant a payer pour completer l'acompte et valider l'installation de votre systeme upOwa " + acompteRestantPourInstall + ".00 FCFA.";
                        recipients = codePays + numPhone;
                        statutSMSsending = sendSMS(username, apiKey, recipients, message, shortname);
                        statusTransfertSMStoSAV = sendSMS(username, apiKey, phoneSAV, message, shortname);
                        statutTransact = "SUCCES";
                        System.out.println("End of simple transaction before become a upOwa Client");
                    }
                    //Generation auto d'un code apres une New Transact et si Le credit status est Enabled                   
                } else {
                    System.out.println("Debut Generation code Normal");
                    statutCodeGeneration = saveManualCodeLuciole();
                    statutTransact = statutCodeGeneration;
                    totalCreditRembourse = creditsFacade.findMontantPaye(numBoxCode);
                    totalCreditRestant = creditsFacade.findMontantRestant(numBoxCode);
                    montantpayeRefresh = totalCreditRembourse + prixUniteINT;
                    System.out.println("Nouveau MontantTotal Paye: " + montantpayeRefresh);
                    montantrestantRefresh = totalCreditRestant - prixUniteINT;
                    System.out.println("Nouveau MontantTotal Restant: " + montantrestantRefresh);
                    int reqUpdateTotalMontPaye = creditsFacade.findToUpdateMontPayeAndRest(numBoxCode, montantpayeRefresh, montantrestantRefresh);
                    System.out.println("Total update Rest and Paye Montant: " + reqUpdateTotalMontPaye);
                    reqUpdateCreditStatus = creditsFacade.findToUpdateCreditStatus(numBoxCode, creditSatus3);
                    System.out.println("Nbre de credit status update: " + reqUpdateCreditStatus);
                    endDateForSMS = dateFormatSMS.format(estimEndDate);
                    System.out.println("End date for SMS: " + endDateForSMS);
                    finalSMSstatut = sendSMSafterManualCode();
                    statutTransact = finalSMSstatut;
                }
            }
        } catch (Exception e) {
            statutTransact = "ECHEC";
            e.printStackTrace();
        }
        return rtocodes();
    }

    public String saveManualCodeLucioleDebloc(int keyPAYGtoFREE) {
        try {
            creationCodeUssdLucioleDeblocage(keyPAYGtoFREE);
            initialisationCodeUssdLuciole();
            paygcodesFacade.create(paygcodes);
            upNextKeyUssdLuciole();
            //updateCreditInfosAfterManualCode();
            showMessage = true;
            statutCode = "SUCCES";
        } catch (Exception e) {
            showMessage = true;
            statutCode = "ECHEC";
            e.printStackTrace();
        }
        return statutCode;
    }

    public String saveManualCodeLuciole() {
        try {
            creationCodeUssdLuciole();
            initialisationCodeUssdLuciole();
            paygcodesFacade.create(paygcodes);
            upNextKeyUssdLuciole();
            //updateCreditInfosAfterManualCode();
            showMessage = true;
            statutCode = "SUCCES";
        } catch (Exception e) {
            showMessage = true;
            statutCode = "ECHEC";
            e.printStackTrace();
        }
        return statutCode;
    }

    public void initialiseIdTranasct() {
        try {
            if (idTransactIN.isEmpty()) {
                System.out.println("DEBUT CREATION AUTOMATIQUE ID-TRANSACT");
                idTrans = "";
            } else {
                idTrans = idTransactIN;
                System.out.println("ID Transact manual generated: " + idTrans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initialiseNumPhone() {
        try {
            if (numPhone.isEmpty()) {
                System.out.println("DEBUT RECUPERATION DU NUMPHONE");
                client = creditsFacade.findClientForSMS(numBoxCode);
                String numPhoneInt = client.getPreferredphone();
                numPhone = numPhoneInt;
                System.out.println("NumPhone Prefere: " + numPhone);
            } else {
                System.out.println("NumPhone Non null: " + numPhone);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initialisationTransaction() {
        mmtransaction.setIdoperator(manualOperatorID);
        mmtransaction.setIdtransact(idTrans);
        mmtransaction.setNumbox(numBoxCode);
        mmtransaction.setNumphone(Integer.parseInt(numPhone));
        mmtransaction.setDatetrans(dateFormat.format(dateTransact));
        mmtransaction.setPrixunite(Integer.parseInt(prixUnite));
        mmtransaction.setSommetotale(Integer.parseInt(prixUnite));
    }

    public String save1erUSSDCodeAutomaticLuciole() {
        try {
            creation1erCodeUssdLuciole();
            initialisationCodeUssdLuciole();
            paygcodesFacade.create(paygcodes);
            upNextKeyUssdLuciole();
            showMessage = true;
            statutCode = "SUCCES";
        } catch (Exception e) {
            showMessage = true;
            statutCode = "ECHEC";
            e.printStackTrace();
        }
        return statutCode;
    }

    public void apercu1erCodeUssdLuciole() throws ParseException {
        System.out.println("Debut Edit Credit IdPAYG prod");
        //idPAYGprod = this.credits.getIdpaygproduct().getIdpaygproduct();
        System.out.println("PAYG Id by codeFace:" + idPAYGprod);
        numBoxCode = creditsFacade.findNumInstForFirstCode(paygotpgenerator);
        System.out.println("Numero installation:" + numBoxCode);
        prix1jour = creditsFacade.findPrix1Jour(numBoxCode);
        System.out.println("Prix 1 jours by creditFacade:" + prix1jour);
        h_RootForm = paygotpgeneratorFacade.findHRoot(idPAYGprod);
        System.out.println("HRoot by PaygotpFacade:" + h_RootForm);
        c_currentIndexForm = paygotpgeneratorFacade.findCurrentCindex(idPAYGprod);
        System.out.println("Current Index by PaygoptFacade:" + c_currentIndexForm);
        montantTotalPayeNow = creditsFacade.findMontantPaye(numBoxCode);
        System.out.println("Montant total paye (precedemment): " + montantTotalPayeNow);
        totalCreditRestant = creditsFacade.findMontantRestant(numBoxCode);
        System.out.println("Montant total restant a payer (precedemment): " + totalCreditRestant);
        montantrestantRefresh = totalCreditRestant - prixUniteINT;
        acompteCreditNow = creditsFacade.findAcompteTotalNow(numBoxCode);
        System.out.println("Acompte Credit After update: " + acompteCreditNow);
        prixUniteINT = (montantTotalPayeNow - acompteCreditNow);
        System.out.println("Difference entre MontPaye et Acompte credit: " + prixUniteINT);
        hcj_forms = calculNbreJours();
        hcj_forms += 30;
        System.out.println("Nombre de jour by calculate:" + hcj_forms);
        nowCDT = lookCDTnow();
        System.out.println("CDT before Positivite Operat:" + nowCDT);
        if (nowCDT < 0) {
            System.out.println("---OPERATIONS DE GENERATION CODE AVEC CDT<0");
            nowCDT = Math.abs(nowCDT);//Rendre positif un nombre negatif
            System.out.println("CDT after Positivite Operat:" + nowCDT);
            hcj_forms += nowCDT;// Nbre de jours apres redressement des defauts de jours de graces
            System.out.println("Nombre de jour by calculate:" + hcj_forms);
            //Debut Operation de date de fin forfait
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
            //Definition de la date d'installation
            String sDatePrevueInstall = dateFormat.format(this.credits.getInstalldate());//Definition de la date d'installation
            datePrevueInstall = dateFormat.parse(sDatePrevueInstall);
            GregorianCalendar calendar2 = new java.util.GregorianCalendar();
            calendar2.setTime(dateFormat.parse(sDatePrevueInstall));
            calendar2.add(Calendar.DATE, hcj_forms);
            estimEndDate = calendar2.getTime();//Conversion de calendar en date
            System.out.println("Estim EndDate to upDate:" + estimEndDate);
            //Fin Operation de date de fin forfait
            String sDate = dateFormat.format(new Date());
            dateTransact = dateFormat.parse(sDate);//Conversion de string en date
            //Calcul du nombre de jour entre la preparation de la box et du jour prevue de l'installation                       
            final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
            long delta = datePrevueInstall.getTime() - dateTransact.getTime();
            long interval = (delta / (MILLISECONDS_PER_DAY));
            int daysPassed = (int) interval;
            System.out.println("Nombre de jour entre la preparation et l'installation effective:" + daysPassed);
            hcj_forms += daysPassed;
            System.out.println("Nombre de jour by calculate and ajusted:" + hcj_forms);
            codeGeneratedByOMNI = generateUSSDCodeOMNI();
            System.out.println("Code final OMNI:" + codeGeneratedByOMNI);
            System.out.println("Install date to Update:" + datePrevueInstall);
            //int reqUpdateCDT = paygotpgeneratorFacade.findToUpdateCDT(idPAYGprod, hcj_forms);
            //System.out.println("Nbre upDate of CDT avec defaut de jour de grace:" + reqUpdateCDT);
            int newNbreJrGraceUsed = 0;
            int newNbreJrGraceRest = 60;
            int reqUpdateDateInstall = creditsFacade.findToUpdateDateInstall(numBoxCode, datePrevueInstall);
            System.out.println("Nbre DateInstall update:" + reqUpdateDateInstall);
            int reqUpdateGraceAndDate = creditsFacade.findToUpdateGraceAndEndDate(numBoxCode, newNbreJrGraceUsed, newNbreJrGraceRest, estimEndDate, creditSatus2);
            System.out.println("Nbre Graces update:" + reqUpdateGraceAndDate);
            int reqUpdateEndDate = creditsFacade.findToUpdateEndDate(numBoxCode, estimEndDate);
            System.out.println("Nbre EndDate update:" + reqUpdateEndDate);
        } else {
            System.out.println("---OPERATIONS DE GENERATION CODE AVEC CDT>=0");
            //Debut Operation de date de fin forfait
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
            //Definition de la date d'installation
            String sDate = dateFormat.format(new Date());
            dateTransact = dateFormat.parse(sDate);//Conversion de string en date           
            //Pause Operation de date de fin de forfait
            //Calcul du nombre de jour entre la preparation de la box et du jour prevue de l'installation           
            String sDatePrevueInstall = dateFormat.format(this.credits.getInstalldate());//Definition de la date d'installation
            datePrevueInstall = dateFormat.parse(sDatePrevueInstall);
            final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
            long delta = datePrevueInstall.getTime() - dateTransact.getTime();
            long interval = (delta / (MILLISECONDS_PER_DAY));
            int daysPassed = (int) interval;
            System.out.println("Nombre de jour entre la preparation et l'installation effective:" + daysPassed);
            hcj_forms += daysPassed;
            System.out.println("Nombre de jour by calculate and ajusted:" + hcj_forms);
            codeGeneratedByOMNI = generateUSSDCodeOMNI();
            System.out.println("Code final OMNI:" + codeGeneratedByOMNI);
            // Suite Operation de date de fin forfait
            GregorianCalendar calendar2 = new java.util.GregorianCalendar();
            calendar2.setTime(dateFormat.parse(sDate));
            calendar2.add(Calendar.DATE, hcj_forms);
            estimEndDate = calendar2.getTime();//Conversion de calendar en date
            System.out.println("Estim EndDate to upDate:" + estimEndDate);
            //Fin Operation de date de fin forfait
            System.out.println("Install date to Update:" + datePrevueInstall);
            //int reqUpdateCDT = paygotpgeneratorFacade.findToUpdateCDT(idPAYGprod, hcj_forms);
            //System.out.println("Nbre upDate of CDT avec defaut de jour de grace:" + reqUpdateCDT);
            int newNbreJrGraceUsed = 0;
            int newNbreJrGraceRest = 60;
            int reqUpdateGraceAndDate = creditsFacade.findToUpdateGraceAndEndDate(numBoxCode, newNbreJrGraceUsed, newNbreJrGraceRest, estimEndDate, creditSatus2);
            System.out.println("Nbre Graces and CodeEndDate update:" + reqUpdateGraceAndDate);
            /*int reqUpdateCodeEndDate = creditsFacade.findToUpdateCodeEndDate(numBoxCode, estimEndDate);
             System.out.println("Nbre CodeEndDate update:" + reqUpdateCodeEndDate);
             int nextCDT = nowCDT + hcj_forms;
             int reqUpdateCDT = paygotpgeneratorFacade.findToUpdateCDT(idPAYGprod, nextCDT);
             System.out.println("Nbre upDate of CDT sans defaut de de jourGrace:" + reqUpdateCDT);*/
            int reqUpdateDateInstall = creditsFacade.findToUpdateDateInstall(numBoxCode, datePrevueInstall);
            System.out.println("Nbre DateInstall update:" + reqUpdateDateInstall);
        }
    }

    public String valider1erCodeInitialiseBox() throws ParseException {
        idPAYGprod = this.paygotpgenerator.getIdpaygproduct();
        System.out.println("PAYG Id by codeFace:" + idPAYGprod);
        h_RootForm = paygotpgeneratorFacade.findHRoot(idPAYGprod);
        System.out.println("HRoot by PaygotpFacade:" + h_RootForm);
        c_currentIndexForm = paygotpgeneratorFacade.findCurrentCindex(idPAYGprod);
        System.out.println("Current Index by PaygoptFacade:" + c_currentIndexForm);
        //hcj_forms = 1;
        System.out.println("Nombre de jour pour initialisation:" + hcj_forms);
        codeGeneratedByOMNI = generateUSSDCodeOMNI();
        System.out.println("Code d'initialisation OMNI:" + codeGeneratedByOMNI);
        //Debut Operation de date de fin forfait
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
        //Definition de la date d'installation
        String sDate = dateFormat.format(this.paygotpgenerator.getDateinitialisation());
        dateTransact = dateFormat.parse(sDate);//Conversion de string en date
        GregorianCalendar calendar = new java.util.GregorianCalendar();
        calendar.setTime(dateTransact);
        calendar.add(Calendar.DATE, hcj_forms);
        estimEndDate = calendar.getTime();//Conversion de calendar en date
        System.out.println("Estim EndDate to upDate:" + estimEndDate);
        //Fin Operation de date de fin forfait
        //Calcul du nombre de jour passe entre l'installation et l'enregistrement
        DateFormat dateDuJourFormat = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
        //Definition de la date d'installation
        String sDateDuJour = dateDuJourFormat.format(new Date());
        dateDuJour = dateDuJourFormat.parse(sDateDuJour);
        final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
        long delta = dateDuJour.getTime() - dateTransact.getTime();
        long interval = (delta / (MILLISECONDS_PER_DAY));
        int daysPassed = (int) interval;
        System.out.println("Nombre de jour entre l'install et l'enregistrement effectif:" + daysPassed);
        int firstCDT = hcj_forms - daysPassed;
        System.out.println("First CDT:" + firstCDT);
        int reqUpdateCDT = paygotpgeneratorFacade.findToUpdateCDT(idPAYGprod, firstCDT);
        System.out.println("Nbre upDate of CDT:" + reqUpdateCDT);
        int reqUpdateCurrentC = paygcodesFacade.findToUpdateCurrentC(k_forms, idPAYGprod);
        System.out.println("Total updated CurrentC: " + reqUpdateCurrentC);
        int reqUpdateFirstCode = paygotpgeneratorFacade.findToUpdateFirstCode(idPAYGprod, codeGeneratedByOMNI);
        System.out.println("Total updated FirstCode: " + reqUpdateFirstCode);
        int reqUpdateDateFirstInit = paygotpgeneratorFacade.findToUpdateDateInitFirst(idPAYGprod, dateTransact);
        System.out.println("Total updated Date Init: " + reqUpdateDateFirstInit);
        int reqUpdateInitstatus = paygotpgeneratorFacade.findToUpdateInitstatus(idPAYGprod, filtreYesINITBox);
        System.out.println("Total updated InitStatus: " + reqUpdateInitstatus);
        return pageinitialisationboxs();
    }

    public String creation1erCodeInitialiseBox() throws ParseException {
        System.out.println("PAYG Id by codeFace:" + idPAYGprod);
        h_RootForm = paygotpgeneratorFacade.findHRoot(idPAYGprod);
        System.out.println("HRoot by PaygotpFacade:" + h_RootForm);
        c_currentIndexForm = paygotpgeneratorFacade.findCurrentCindex(idPAYGprod);
        System.out.println("Current Index by PaygoptFacade:" + c_currentIndexForm);
        //hcj_forms = 1;
        System.out.println("Nombre de jour pour initialisation:" + hcj_forms);
        codeGeneratedByOMNI = generateUSSDCodeOMNI();
        System.out.println("Code d'initialisation OMNI:" + codeGeneratedByOMNI);
        int reqUpdateFirstCode = paygotpgeneratorFacade.findToUpdateFirstCode(idPAYGprod, codeGeneratedByOMNI);
        System.out.println("Total updated FirstCode: " + reqUpdateFirstCode);
        return pageinitialisationboxs();
    }

    public void validationDu1erCodeUssdLuciole() throws ParseException {
        idPAYGprod = this.credits.getIdpaygproduct().getIdpaygproduct();
        System.out.println("PAYG Id by codeFace:" + idPAYGprod);
        numBoxCode = creditsFacade.findNumInstForFirstCode(paygotpgenerator);
        System.out.println("Numero Box:" + numBoxCode);
        prix1jour = creditsFacade.findPrix1Jour(numBoxCode);
        System.out.println("Prix 1 jours by creditFacade:" + prix1jour);
        h_RootForm = paygotpgeneratorFacade.findHRoot(idPAYGprod);
        System.out.println("HRoot by PaygotpFacade:" + h_RootForm);
        c_currentIndexForm = paygotpgeneratorFacade.findCurrentCindex(idPAYGprod);
        System.out.println("Current Index by PaygoptFacade:" + c_currentIndexForm);
        montantTotalPayeNow = creditsFacade.findMontantPaye(numBoxCode);
        System.out.println("Montant total paye (precedemment): " + montantTotalPayeNow);
        totalCreditRestant = creditsFacade.findMontantRestant(numBoxCode);
        System.out.println("Montant total restant a payer (precedemment): " + totalCreditRestant);
        montantrestantRefresh = totalCreditRestant - prixUniteINT;
        acompteCreditNow = creditsFacade.findAcompteTotalNow(numBoxCode);
        System.out.println("Acompte Credit After update: " + acompteCreditNow);
        prixUniteINT = (montantTotalPayeNow - acompteCreditNow);
        System.out.println("Difference entre MontPaye et Acompte credit: " + prixUniteINT);
        hcj_forms = calculNbreJours();
        hcj_forms += 30;
        System.out.println("Nombre de jour by calculate:" + hcj_forms);
        codeGeneratedByOMNI = generateUSSDCodeOMNI();
        System.out.println("Code final OMNI:" + codeGeneratedByOMNI);
        //Debut Operation de date de fin forfait
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
        //Definition de la date d'installation
        String sDate = dateFormat.format(this.credits.getInstalldate());
        dateTransact = dateFormat.parse(sDate);//Conversion de string en date
        GregorianCalendar calendar = new java.util.GregorianCalendar();
        calendar.setTime(dateTransact);
        calendar.add(Calendar.DATE, hcj_forms);
        estimEndDate = calendar.getTime();//Conversion de calendar en date
        System.out.println("Estim EndDate to upDate:" + estimEndDate);
        //Fin Operation de date de fin forfait
        //Calcul du nombre de jour passe entre l'installation et l'enregistrement
        DateFormat dateDuJourFormat = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
        //Definition de la date d'installation
        String sDateDuJour = dateDuJourFormat.format(new Date());
        dateDuJour = dateDuJourFormat.parse(sDateDuJour);
        final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
        long delta = dateDuJour.getTime() - dateTransact.getTime();
        long interval = (delta / (MILLISECONDS_PER_DAY));
        int daysPassed = (int) interval;
        System.out.println("Nombre de jour entre l'install et l'enregistrement effectif:" + daysPassed);
        int firstCDT = hcj_forms - daysPassed;
        System.out.println("First CDT:" + firstCDT);
        int reqUpdateCDT = paygotpgeneratorFacade.findToUpdateCDT(idPAYGprod, firstCDT);
        System.out.println("Nbre upDate of CDT:" + reqUpdateCDT);
        int reqUpdateEndDate = creditsFacade.findToUpdateEndDate(numBoxCode, estimEndDate);
        System.out.println("Nbre Graces and CodeEndDate update:" + reqUpdateEndDate);
        int reqUpdateDateInstall = creditsFacade.findToUpdateDateInstall(numBoxCode, dateTransact);
        System.out.println("Nbre DateInstall update:" + reqUpdateDateInstall);
    }

    public void creation1erCodeUssdLuciole() throws ParseException {
        idPAYGprod = this.credits.getIdpaygproduct().getIdpaygproduct();
        System.out.println("PAYG Id by codeFace:" + idPAYGprod);
        numBoxCode = creditsFacade.findNumInstForFirstCode(paygotpgenerator);
        System.out.println("Numero Box:" + numBoxCode);
        prix1jour = creditsFacade.findPrix1Jour(numBoxCode);
        System.out.println("Prix 1 jours by creditFacade:" + prix1jour);
        h_RootForm = paygotpgeneratorFacade.findHRoot(idPAYGprod);
        System.out.println("HRoot by PaygotpFacade:" + h_RootForm);
        c_currentIndexForm = paygotpgeneratorFacade.findCurrentCindex(idPAYGprod);
        System.out.println("Current Index by PaygoptFacade:" + c_currentIndexForm);
        montantTotalPayeNow = creditsFacade.findMontantPaye(numBoxCode);
        System.out.println("Montant total paye (precedemment): " + montantTotalPayeNow);
        totalCreditRestant = creditsFacade.findMontantRestant(numBoxCode);
        System.out.println("Montant total restant a payer (precedemment): " + totalCreditRestant);
        montantrestantRefresh = totalCreditRestant - prixUniteINT;
        acompteCreditNow = creditsFacade.findAcompteTotalNow(numBoxCode);
        System.out.println("Acompte Credit After update: " + acompteCreditNow);
        prixUniteINT = (montantTotalPayeNow - acompteCreditNow);
        System.out.println("Difference entre MontPaye et Acompte credit: " + prixUniteINT);
        hcj_forms = calculNbreJours();
        hcj_forms += 30;
        System.out.println("Nombre de jour by calculate:" + hcj_forms);
        //Debut Operation de date de fin forfait
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
        //Definition de la date d'installation
        String sDate = dateFormat.format(this.credits.getInstalldate());
        dateTransact = dateFormat.parse(sDate);//Conversion de string en date
        GregorianCalendar calendar = new java.util.GregorianCalendar();
        calendar.setTime(dateTransact);
        //Pause Operation de date
        //Calcul du nombre de jour passe entre la date prevu de l'installation et la date de preparation
        DateFormat dateDuJourFormat = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
        //Definition de la date d'installation
        String sDateDuJour = dateDuJourFormat.format(new Date());
        dateDuJour = dateDuJourFormat.parse(sDateDuJour);
        final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
        long delta = dateTransact.getTime() - dateDuJour.getTime();
        long interval = (delta / (MILLISECONDS_PER_DAY));
        int daysPassed = (int) interval;
        hcj_forms += daysPassed;
        int firstCDT = hcj_forms;
        //Suite operation de date de fin
        calendar.add(Calendar.DATE, hcj_forms);
        estimEndDate = calendar.getTime();//Conversion de calendar en date
        System.out.println("Estim EndDate to upDate:" + estimEndDate);
        //Fin Operation de date de fin forfait
        nowCDT = lookCDTnow();
        System.out.println("CDT before Positivite Operat:" + nowCDT);
        nowCDT = Math.abs(nowCDT);//Rendre positif un nombre negatif
        System.out.println("CDT after Positivite Operat:" + nowCDT);
        hcj_forms += nowCDT;// Nbre de jours apres redressement des defauts de jours de graces
        System.out.println("Nombre de jour by calculate and ajusted:" + hcj_forms);
        System.out.println("Nombre de jour entre le jour de preparation et la date d'install prevue:" + daysPassed);
        codeGeneratedByOMNI = generateUSSDCodeOMNI();
        System.out.println("Code final OMNI de " + hcj_forms + " jours:" + codeGeneratedByOMNI);
        /*int firstCDT = hcj_forms - daysPassed;
         System.out.println("First CDT:" + firstCDT);
         int reqUpdateCDT = paygotpgeneratorFacade.findToUpdateCDT(idPAYGprod, firstCDT);
         System.out.println("Nbre upDate of CDT:" + reqUpdateCDT);*/
        int reqUpdateEndDate = creditsFacade.findToUpdateEndDate(numBoxCode, estimEndDate);
        System.out.println("Nbre Graces and CodeEndDate update:" + reqUpdateEndDate);
        /*int reqUpdateDateInstall = creditsFacade.findToUpdateDateInstall(numBoxCode, dateTransact);
         System.out.println("Nbre DateInstall update:" + reqUpdateDateInstall);*/
        System.out.println("First CDT:" + firstCDT);
        int reqUpdateCDT = paygotpgeneratorFacade.findToUpdateCDT(idPAYGprod, firstCDT);
        System.out.println("Nbre upDate of CDT:" + reqUpdateCDT);
    }

    public void creation2emCodeUssdLucioleAjust() throws ParseException {
        idPAYGprod = this.credits.getIdpaygproduct().getIdpaygproduct();
        System.out.println("PAYG Id by codeFace:" + idPAYGprod);
        numBoxCode = creditsFacade.findNumInstForFirstCode(paygotpgenerator);
        System.out.println("Numero Box:" + numBoxCode);
        h_RootForm = paygotpgeneratorFacade.findHRoot(idPAYGprod);
        System.out.println("HRoot by PaygotpFacade:" + h_RootForm);
        c_currentIndexForm = paygotpgeneratorFacade.findCurrentCindex(idPAYGprod);
        System.out.println("Current Index by PaygoptFacade:" + c_currentIndexForm);
        //Debut Operation de date de fin forfait
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
        //Definition de la date d'installation
        String sDate = dateFormat.format(this.credits.getInstalldate());
        dateTransact = dateFormat.parse(sDate);//Conversion de string en date
        GregorianCalendar calendar = new java.util.GregorianCalendar();
        calendar.setTime(dateTransact);
        //Pause Operation de date
        //Calcul du nombre de jour passe entre la date prevu de l'installation et la date de preparation
        DateFormat dateRealInstallFormat = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
        //Definition de la date d'installation
        String sDateRealInstall = dateRealInstallFormat.format(dateValidInstall);
        dateValidInstall = dateRealInstallFormat.parse(sDateRealInstall);
        final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
        long delta = dateValidInstall.getTime() - dateTransact.getTime();
        long interval = (delta / (MILLISECONDS_PER_DAY));
        int daysPassed = (int) interval;
        hcj_forms = daysPassed;
        System.out.println("Nombre de jours entre la date d'install prevue et la date d'install effective:" + hcj_forms);
        if (hcj_forms > 0) {
            //Suite operation de date de fin
            String sDateEnd = dateFormat.format(this.credits.getCodeenddate());
            System.out.println("Now String EndDate:" + sDateEnd);
            dateTransact = dateFormat.parse(sDateEnd);//Conversion de string en date
            System.out.println("Now Date EndDate:" + dateTransact);
            GregorianCalendar calendar2 = new java.util.GregorianCalendar();
            calendar2.setTime(dateTransact);
            calendar2.add(Calendar.DATE, hcj_forms);
            estimEndDate = calendar2.getTime();//Conversion de calendar en date
            System.out.println("Estim EndDate to upDate (New):" + estimEndDate);
            DateFormat dateFormatEndSMS = new SimpleDateFormat("dd-MM-yyyy");
            String sDateEndSMS = dateFormatEndSMS.format(estimEndDate);
            System.out.println("Estim EndDate for SMS:" + sDateEndSMS);
            //Fin Operation de date de fin forfait
            nowCDT = lookCDTnow();
            System.out.println("Now CDT:" + nowCDT);
            int secondCDT = hcj_forms + nowCDT;
            System.out.println("Nombre de jour entre la date d'install prevue et la date d'install effective:" + daysPassed);
            codeGeneratedByOMNI = generateUSSDCodeOMNI();
            System.out.println("Code final OMNI de " + hcj_forms + " jours:" + codeGeneratedByOMNI);
            int reqUpdateEndDate = creditsFacade.findToUpdateEndDate(numBoxCode, estimEndDate);
            System.out.println("Nbre Graces and CodeEndDate update:" + reqUpdateEndDate);
            int reqUpdateDateInstall = creditsFacade.findToUpdateDateInstall(numBoxCode, dateValidInstall);
            System.out.println("Nbre DateInstall update:" + reqUpdateDateInstall);
            System.out.println("CDT after ajust:" + secondCDT);
            int reqUpdateCDT = paygotpgeneratorFacade.findToUpdateCDT(idPAYGprod, secondCDT);
            System.out.println("Nbre upDate of CDT:" + reqUpdateCDT);
            statutCode = "1 nouveau code d'ajustement genere";
            //Envoie du SMS avec le code d'ajustement
            String messageSMS = "CCher client, suite a l'installation de votre systeme upOwa, veuillez entrer le code " + codeGeneratedByOMNI + " pour activer l'installation " + numBoxCode + " jusqu'au " + sDateEndSMS + ". Merci pour votre confiance";
            recipients = codePays + this.credits.getIdclient().getPreferredphone();
            statutSMSsending = sendSMS(username, apiKey, recipients, messageSMS, shortname);
            statutCode = "1 nouveau code d'ajustement genere: SMS = " + statutSMSsending;
            initialisationCodeUssdLuciole();
            paygcodesFacade.create(paygcodes);
            upNextKeyUssdLuciole();
        } else {
            int reqUpdateDateInstall = creditsFacade.findToUpdateDateInstall(numBoxCode, dateValidInstall);
            System.out.println("Nbre DateInstall update:" + reqUpdateDateInstall);
            statutCode = "aucun code d'ajustement genere: SMS = " + statutSMSsending;
        }
    }

    public void creationCodeEchangeBoxLuciole() throws ParseException {
        //idPAYGprod = this.credits.getIdpaygproduct().getIdpaygproduct();
        System.out.println("PAYG Id sortant:" + idPAYGprod);
        System.out.println("PAYG Id Entrant:" + idPAYGprodNew);
        numBoxCode = creditsFacade.findNumInstForFirstCode(paygotpgenerator);
        System.out.println("Numero Installation concernee par l'echange:" + numBoxCode);
        prix1jour = creditsFacade.findPrix1Jour(numBoxCode);
        System.out.println("Prix 1 jours by creditFacade:" + prix1jour);
        h_RootForm = paygotpgeneratorFacade.findHRoot(idPAYGprodNew);
        System.out.println("HRoot Box Entrante:" + h_RootForm);
        c_currentIndexForm = paygotpgeneratorFacade.findCurrentCindex(idPAYGprodNew);
        System.out.println("Current Index Box Entrante:" + c_currentIndexForm);
        int nowCDTnewBox = lookCDTofOMNIbox(idPAYGprodNew);
        System.out.println("CDT Box Entrante:" + nowCDTnewBox);
        if (nowCDTnewBox < 0) {
            nowCDTnewBox = Math.abs(nowCDTnewBox);//Rendre positif un nombre negatif 
        }
        System.out.println("CDT Box Entrante (Positiv):" + nowCDTnewBox);
        int nowCDToutBox = lookCDTofOMNIbox(idPAYGprod);
        System.out.println("CDT Box Sortante:" + nowCDToutBox);
        if (nowCDToutBox > 0) {
            hcj_forms = nowCDToutBox + nowCDTnewBox + nbreJourSup;
        } else {
            hcj_forms = nowCDTnewBox + nbreJourSup;
        }
        System.out.println("Nombre de jour by calculate:" + hcj_forms);
        //Debut Operation de date de fin forfait
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
        //Definition de la date d'installation
        String sDatePrevueInstall = dateFormat.format(this.credits.getInstalldate());//Definition de la date d'installation
        datePrevueInstall = dateFormat.parse(sDatePrevueInstall);
        GregorianCalendar calendar2 = new java.util.GregorianCalendar();
        calendar2.setTime(dateFormat.parse(sDatePrevueInstall));
        calendar2.add(Calendar.DATE, hcj_forms);
        estimEndDate = calendar2.getTime();//Conversion de calendar en date
        System.out.println("Estim EndDate to upDate:" + estimEndDate);
        //Fin Operation de date de fin forfait
        String sDate = dateFormat.format(new Date());
        dateTransact = dateFormat.parse(sDate);//Conversion de string en date
        //Calcul du nombre de jour entre la preparation de la box et du jour prevue de l'installation                               
        int daysPassed = (int) new DateOperations().joursEcart(dateTransact, datePrevueInstall);
        System.out.println("Jours d'ecart entre la preparation et l'echange:" + daysPassed);
        hcj_forms += daysPassed;
        System.out.println("Nombre de jour by calculate and ajusted:" + hcj_forms);
        codeGeneratedByOMNI = generateUSSDCodeOMNI();
        System.out.println("Code final OMNI:" + codeGeneratedByOMNI);
        System.out.println("Install date to Update:" + datePrevueInstall);
        int newCDTboxIN = nowCDToutBox + nbreJourSup;
        int reqUpdateCDT = paygotpgeneratorFacade.findToUpdateCDT(idPAYGprodNew, newCDTboxIN);
        System.out.println("Nbre upDate of CDT avec defaut de jour de grace:" + reqUpdateCDT);
        int reqUpdateLastMaintenanceIN = paygotpgeneratorFacade.updateLastMaintenanceDate(datePrevueInstall, idPAYGprodNew);
        System.out.println("Nbre LastMaintenanceIN update:" + reqUpdateLastMaintenanceIN);
        int reqUpdateLastMaintenanceOut = paygotpgeneratorFacade.updateLastMaintenanceDate(datePrevueInstall, idPAYGprod);
        System.out.println("Nbre LastMaintenanceOut update:" + reqUpdateLastMaintenanceOut);
        int reqUpdateEndDate = creditsFacade.findToUpdateEndDate(numBoxCode, estimEndDate);
        System.out.println("Nbre EndDate update:" + reqUpdateEndDate);
    }

    public void initialisationCodeUssdLuciole() throws ParseException {
        paygcodes.setGeneratedcode(codeGeneratedByOMNI);
        paygcodes.setNuminstall(numBoxCode);
        DateFormat dateFormatGeneration = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
        sDateNow = dateFormatGeneration.format(new Date());
        System.out.println("Date Generation Code OMNI:" + sDateNow);
        dateTransact0 = dateFormatGeneration.parse(sDateNow);
        paygcodes.setGenerateddate(dateTransact0);
        paygcodes.setEnddate(estimEndDate);
    }

    public void upNextKeyUssdLuciole() {
        int reqUpdateCurrentC = paygcodesFacade.findToUpdateCurrentC(k_forms, idPAYGprod);
        System.out.println("Total updated CurrentC: " + reqUpdateCurrentC);
        int reqUpdateLastCode = creditsFacade.findToUpdateLastCode(numBoxCode, codeGeneratedByOMNI);
        System.out.println("Total updated LastCode: " + reqUpdateLastCode);
    }

    public void updateCreditInfosAfterManualCode() {
        totalCreditRembourse = creditsFacade.findMontantPaye(numBoxCode);
        totalCreditRestant = creditsFacade.findMontantRestant(numBoxCode);
        montantpayeRefresh = totalCreditRembourse + prixUniteINT;
        System.out.println("Nouveau MontantTotal Paye: " + montantpayeRefresh);
        montantrestantRefresh = totalCreditRestant - prixUniteINT;
        System.out.println("Nouveau MontantTotal Restant: " + montantrestantRefresh);
        int reqUpdateTotalMontPaye = creditsFacade.findToUpdateMontPayeAndRest(numBoxCode, montantpayeRefresh, montantrestantRefresh);
        System.out.println("Total update Rest and Paye Montant: " + reqUpdateTotalMontPaye);
        endDateForSMS = dateFormatSMS.format(estimEndDate);
        System.out.println("End date for SMS: " + endDateForSMS);
        finalSMSstatut = sendSMSafterManualCode();
        statutTransact = finalSMSstatut;
    }

    public String sendSMSafterManualCode() {
        try {            
            message = "MMerci pour votre paiement de " + prixUnite + ".00 FCFA. Entrez le code " + codeGeneratedByOMNI + " pour activer l'installation " + numBoxCode + " jusqu'au " + endDateForSMS + ". Montant total rembourse " + montantpayeRefresh + ".00 FCFA, credit restant a payer " + montantrestantRefresh + ".00 FCFA.";
            //int longueurSMS = message.length();
            //messageToParse = message;
            //String part1OfSMS = messageToParse.substring(0, 153);
            //String part2OfSMS = messageToParse.substring(153, longueurSMS);
            //message = part1OfSMS + " " + part2OfSMS;
            //recipients = codePays + numPhone;
            //codePays = "+33";
            recipients = codePays + numPhone;
            statutSMSsending = sendSMS(username, apiKey, recipients, message, shortname);
            statusTransfertSMStoSAV = sendSMS(username, apiKey, phoneSAV, message, shortname);
        } catch (Exception e) {
            statutTransact = "ECHEC";
            e.printStackTrace();
        }
        return statutSMSsending;
    }

    // Methode d'envoie SMS
    public String sendSMS(String username, String apiKey, String recipients, String message, String shortname) {
        AfricasTalkingGateway gateway = new AfricasTalkingGateway(username, apiKey);
        try {
            JSONArray results = gateway.sendMessageUPOWA(recipients, message, shortname);
            statusSMSsending = "SUCCES";
            showStatusSMSsending = true;

            for (int i = 0; i < results.length(); ++i) {
                JSONObject result = results.getJSONObject(i);
                System.out.print("Status: " + result.getString("status") + ","); // status is either "Success" or "error message"
                System.out.print("PhoneNumber: " + result.getString("number") + ",");
                System.out.print("Message Id: " + result.getString("messageId") + ",");
                System.out.println("Prix SMS: " + result.getString("cost"));
            }
        } catch (Exception e) {
            System.out.println("Encountered an error while sending " + e.getMessage());
            statusSMSsending = "ECHEC";
            showStatusSMSsending = true;
        }
        return statusSMSsending;
    }

    public int calculNbreJours() {
        int nbreJoursCalcule = prixUniteINT / prix1jour;
        return nbreJoursCalcule;
    }

    public String generateUSSDCodeOMNI() {
        try {
            k_forms = c_currentIndexForm - hcj_forms;
            finalCodeDec = new ShaHash().hash_str_to_Low_High_Bytes(new ShaHash().H_Fonction(h_RootForm, k_forms));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalCodeDec;
    }

    public void creationCodeUssdLucioleDeblocage(int hcj_formsDebloc) throws ParseException {
        System.out.println("Numero d'installation:" + numBoxCode);
        //idPAYGprod = creditsFacade.findPAYGidprod(numBoxCode);
        h_RootForm = paygotpgeneratorFacade.findHRoot(idPAYGprod);
        System.out.println("HRoot by PaygotpFacade:" + h_RootForm);
        c_currentIndexForm = paygotpgeneratorFacade.findCurrentCindex(idPAYGprod);
        System.out.println("Current Index by PaygoptFacade:" + c_currentIndexForm);
        int totalJrGraceRestNow = creditsFacade.findNbreJrGraceRestNow(numBoxCode);
        int totalJrGraceUsedNow = creditsFacade.findNbreJrGraceUsedNow(numBoxCode);
            System.out.println("---OPERATIONS DE GENERATION CODE DEBLOCAGE AVEC CDT>=0");
            hcj_forms = hcj_formsDebloc;
            System.out.println("Nombre de jour by calculate:" + hcj_forms);
            codeGeneratedByOMNI = generateUSSDCodeOMNI();
            System.out.println("Code final OMNI:" + codeGeneratedByOMNI);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
            String sDate = dateFormat.format(new Date());
            estimEndDate = dateFormat.parse(sDate);
            int reqUpdateGraceAndDate = creditsFacade.findToUpdateGraceAndEndDate(numBoxCode, totalJrGraceUsedNow, totalJrGraceRestNow, estimEndDate, creditSatus5);
            System.out.println("Nbre CodeEndDate update:" + reqUpdateGraceAndDate);
            int reqUpdateCDT = paygotpgeneratorFacade.findToUpdateCDT(idPAYGprod, hcj_forms);
            System.out.println("Nbre upDate of CDT sans defaut de de jourGrace:" + reqUpdateCDT);
    }

    public void creationCodeUssdLuciole() throws ParseException {
        System.out.println("Numero d'installation:" + numBoxCode);
        //idPAYGprod = creditsFacade.findPAYGidprod(numBoxCode);
        idPAYGprod = codeFacade.findPaygIdprod(numBoxCode);
        System.out.println("PAYG Id by codeFace:" + idPAYGprod);
        //strprix1jour = creditsFacade.findPrix1Jour(numBoxCode);
        prix1jour = creditsFacade.findPrix1Jour(numBoxCode);
        System.out.println("Prix 1 jours by creditFacade:" + prix1jour);
        h_RootForm = paygotpgeneratorFacade.findHRoot(idPAYGprod);
        System.out.println("HRoot by PaygotpFacade:" + h_RootForm);
        c_currentIndexForm = paygotpgeneratorFacade.findCurrentCindex(idPAYGprod);
        System.out.println("Current Index by PaygoptFacade:" + c_currentIndexForm);
        nowCDT = lookCDTnow();
        System.out.println("CDT before Positivite Operat:" + nowCDT);
        if (nowCDT < 0) {
            System.out.println("---OPERATIONS DE GENERATION CODE AVEC CDT<0");
            nowCDT = Math.abs(nowCDT);//Rendre positif un nombre negatif
            System.out.println("CDT after Positivite Operat:" + nowCDT);
            int totalJrGraceRestNow = creditsFacade.findNbreJrGraceRestNow(numBoxCode);
            int totalJrGraceUsedNow = creditsFacade.findNbreJrGraceUsedNow(numBoxCode);
            int newNbreJrGraceRest = totalJrGraceRestNow - nowCDT;
            int newNbreJrGraceUsed = totalJrGraceUsedNow + nowCDT;
            hcj_forms = calculNbreJours();//Nbre de Jour avant ajustement des defaut de jour de graces
            hcj_forms += nowCDT;// Nbre de jours apres redressement des defauts de jours de graces
            System.out.println("Nombre de jour by calculate:" + hcj_forms);
            codeGeneratedByOMNI = generateUSSDCodeOMNI();
            System.out.println("Code final OMNI:" + codeGeneratedByOMNI);
            //Debut Operation de date de fin forfait
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
            String sDate = dateFormat.format(new Date());
            dateTransact = dateFormat.parse(sDate);//Conversion de string en date
            GregorianCalendar calendar = new java.util.GregorianCalendar();
            calendar.setTime(dateTransact);
            calendar.add(Calendar.DATE, hcj_forms);
            estimEndDate = calendar.getTime();//Conversion de calendar en date
            System.out.println("Estim EndDate to upDate:" + estimEndDate);
            //Fin Operation de date de fin forfait
            int reqUpdateCDT = paygotpgeneratorFacade.findToUpdateCDT(idPAYGprod, hcj_forms);
            System.out.println("Nbre upDate of CDT avec defaut de jour de grace:" + reqUpdateCDT);
            int reqUpdateGraceAndDate = creditsFacade.findToUpdateGraceAndEndDate(numBoxCode, newNbreJrGraceUsed, newNbreJrGraceRest, estimEndDate, creditSatus3);
            System.out.println("Nbre Graces and CodeEndDate update:" + reqUpdateGraceAndDate);
        } else {
            System.out.println("---OPERATIONS DE GENERATION CODE AVEC CDT>=0");
            hcj_forms = calculNbreJours();
            System.out.println("Nombre de jour by calculate:" + hcj_forms);
            codeGeneratedByOMNI = generateUSSDCodeOMNI();
            System.out.println("Code final OMNI:" + codeGeneratedByOMNI);
            previousEstimEndDate = creditsFacade.findCodeEndDateNow(numBoxCode);
            System.out.println("Previous estim EndDate:" + previousEstimEndDate);
            GregorianCalendar calendar = new java.util.GregorianCalendar();
            calendar.setTime(previousEstimEndDate);
            calendar.add(Calendar.DATE, hcj_forms);
            estimEndDate = calendar.getTime();//Conversion de calendar en date
            int reqUpdateCodeEndDate = creditsFacade.findToUpdateCodeEndDate(numBoxCode, estimEndDate);
            System.out.println("Nbre CodeEndDate update:" + reqUpdateCodeEndDate);
            int nextCDT = nowCDT + hcj_forms;
            int reqUpdateCDT = paygotpgeneratorFacade.findToUpdateCDT(idPAYGprod, nextCDT);
            System.out.println("Nbre upDate of CDT sans defaut de de jourGrace:" + reqUpdateCDT);
        }

    }

    public int lookCDTnow() {
        int actualCDT = paygotpgeneratorFacade.findCDT(idPAYGprod);
        return actualCDT;
    }

    public int lookCDTofOMNIbox(String idPAYGProduct) {
        int actualCDT = paygotpgeneratorFacade.findCDT(idPAYGProduct);
        return actualCDT;
    }

    // ACCESSEURS ET MUTATEURS
    public CreditsFacadeLocal getCreditsFacade() {
        return creditsFacade;
    }

    public void setCreditsFacade(CreditsFacadeLocal creditsFacade) {
        this.creditsFacade = creditsFacade;
    }

    public List<Credits> getListWaitingInstallCredits() {
        return listWaitingInstallCredits;
    }

    public void setListWaitingInstallCredits(List<Credits> listWaitingInstallCredits) {
        this.listWaitingInstallCredits = listWaitingInstallCredits;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public ClientFacadeLocal getClientFacade() {
        return clientFacade;
    }

    public void setClientFacade(ClientFacadeLocal clientFacade) {
        this.clientFacade = clientFacade;
    }

    public List<Client> getListClientPotentiel() {
        return listClientPotentiel;
    }

    public void setListClientPotentiel(List<Client> listClientPotentiel) {
        this.listClientPotentiel = listClientPotentiel;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ProduitsFacadeLocal getProduitsFacade() {
        return produitsFacade;
    }

    public void setProduitsFacade(ProduitsFacadeLocal produitsFacade) {
        this.produitsFacade = produitsFacade;
    }

    public List<Produits> getListProduitSystem() {
        return listProduitSystem;
    }

    public void setListProduitSystem(List<Produits> listProduitSystem) {
        this.listProduitSystem = listProduitSystem;
    }

    public Produits getProduits() {
        return produits;
    }

    public void setProduits(Produits produits) {
        this.produits = produits;
    }

    public TermesFacadeLocal getTermesFacade() {
        return termesFacade;
    }

    public void setTermesFacade(TermesFacadeLocal termesFacade) {
        this.termesFacade = termesFacade;
    }

    public List<Termes> getListTermes() {
        return listTermes;
    }

    public void setListTermes(List<Termes> listTermes) {
        this.listTermes = listTermes;
    }

    public Termes getTermes() {
        return termes;
    }

    public void setTermes(Termes termes) {
        this.termes = termes;
    }

    public PaygotpgeneratorFacadeLocal getPaygotpgeneratorFacade() {
        return paygotpgeneratorFacade;
    }

    public void setPaygotpgeneratorFacade(PaygotpgeneratorFacadeLocal paygotpgeneratorFacade) {
        this.paygotpgeneratorFacade = paygotpgeneratorFacade;
    }

    public List<Paygotpgenerator> getListNotInstallOMNIBOX() {
        return listNotInstallOMNIBOX;
    }

    public void setListNotInstallOMNIBOX(List<Paygotpgenerator> listNotInstallOMNIBOX) {
        this.listNotInstallOMNIBOX = listNotInstallOMNIBOX;
    }

    public List<Paygotpgenerator> getListNotInitialiseOMNIBOX() {
        return listNotInitialiseOMNIBOX;
    }

    public void setListNotInitialiseOMNIBOX(List<Paygotpgenerator> listNotInitialiseOMNIBOX) {
        this.listNotInitialiseOMNIBOX = listNotInitialiseOMNIBOX;
    }

    public List<Credits> getListValidForMaintenance() {
        return listValidForMaintenance;
    }

    public void setListValidForMaintenance(List<Credits> listValidForMaintenance) {
        this.listValidForMaintenance = listValidForMaintenance;
    }

    public List<Credits> getListCredits() {
        return listCredits;
    }

    public void setListCredits(List<Credits> listCredits) {
        this.listCredits = listCredits;
    }

    public Paygotpgenerator getPaygotpgenerator() {
        return paygotpgenerator;
    }

    public void setPaygotpgenerator(Paygotpgenerator paygotpgenerator) {
        this.paygotpgenerator = paygotpgenerator;
    }

    public String getCreditSatus0() {
        return creditSatus0;
    }

    public void setCreditSatus0(String creditSatus0) {
        this.creditSatus0 = creditSatus0;
    }

    public String getCreditSatus1() {
        return creditSatus1;
    }

    public void setCreditSatus1(String creditSatus1) {
        this.creditSatus1 = creditSatus1;
    }

    public String getCreditSatus2() {
        return creditSatus2;
    }

    public void setCreditSatus2(String creditSatus2) {
        this.creditSatus2 = creditSatus2;
    }

    public String getCreditSatus3() {
        return creditSatus3;
    }

    public void setCreditSatus3(String creditSatus3) {
        this.creditSatus3 = creditSatus3;
    }

    public String getCreditSatus4() {
        return creditSatus4;
    }

    public void setCreditSatus4(String creditSatus4) {
        this.creditSatus4 = creditSatus4;
    }

    public String getCreditSatus5() {
        return creditSatus5;
    }

    public void setCreditSatus5(String creditSatus5) {
        this.creditSatus5 = creditSatus5;
    }

    public boolean isShowMessage() {
        return showMessage;
    }

    public void setShowMessage(boolean showMessage) {
        this.showMessage = showMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFiltreNoInstallBox() {
        return filtreNoInstallBox;
    }

    public void setFiltreNoInstallBox(String filtreNoInstallBox) {
        this.filtreNoInstallBox = filtreNoInstallBox;
    }

    public Double getLatitudeIN() {
        return latitudeIN;
    }

    public void setLatitudeIN(Double latitudeIN) {
        this.latitudeIN = latitudeIN;
    }

    public Double getLongitudeIN() {
        return longitudeIN;
    }

    public void setLongitudeIN(Double longitudeIN) {
        this.longitudeIN = longitudeIN;
    }

    public String getFiltreYesInstallBox() {
        return filtreYesInstallBox;
    }

    public void setFiltreYesInstallBox(String filtreYesInstallBox) {
        this.filtreYesInstallBox = filtreYesInstallBox;
    }

    public String getFiltrePAYGmode() {
        return filtrePAYGmode;
    }

    public void setFiltrePAYGmode(String filtrePAYGmode) {
        this.filtrePAYGmode = filtrePAYGmode;
    }

    public String getStatutCode() {
        return statutCode;
    }

    public void setStatutCode(String statutCode) {
        this.statutCode = statutCode;
    }

    public int getNumBoxCode() {
        return numBoxCode;
    }

    public void setNumBoxCode(int numBoxCode) {
        this.numBoxCode = numBoxCode;
    }

    public String getIdPAYGprod() {
        return idPAYGprod;
    }

    public void setIdPAYGprod(String idPAYGprod) {
        this.idPAYGprod = idPAYGprod;
    }

    public int getPrix1jour() {
        return prix1jour;
    }

    public void setPrix1jour(int prix1jour) {
        this.prix1jour = prix1jour;
    }

    public String getH_RootForm() {
        return h_RootForm;
    }

    public void setH_RootForm(String h_RootForm) {
        this.h_RootForm = h_RootForm;
    }

    public int getK_forms() {
        return k_forms;
    }

    public void setK_forms(int k_forms) {
        this.k_forms = k_forms;
    }

    public int getMaxHCJ() {
        return maxHCJ;
    }

    public void setMaxHCJ(int maxHCJ) {
        this.maxHCJ = maxHCJ;
    }

    public int getI_forms() {
        return i_forms;
    }

    public void setI_forms(int i_forms) {
        this.i_forms = i_forms;
    }

    public int getHcj_forms() {
        return hcj_forms;
    }

    public void setHcj_forms(int hcj_forms) {
        this.hcj_forms = hcj_forms;
    }

    public int getC_default() {
        return c_default;
    }

    public void setC_default(int c_default) {
        this.c_default = c_default;
    }

    public int getC_currentIndexForm() {
        return c_currentIndexForm;
    }

    public void setC_currentIndexForm(int c_currentIndexForm) {
        this.c_currentIndexForm = c_currentIndexForm;
    }

    public String getHexToDec_forms() {
        return hexToDec_forms;
    }

    public void setHexToDec_forms(String hexToDec_forms) {
        this.hexToDec_forms = hexToDec_forms;
    }

    public String getMessagePOP() {
        return messagePOP;
    }

    public void setMessagePOP(String messagePOP) {
        this.messagePOP = messagePOP;
    }

    public boolean isShowMessagePOP() {
        return showMessagePOP;
    }

    public void setShowMessagePOP(boolean showMessagePOP) {
        this.showMessagePOP = showMessagePOP;
    }

    public String getStrprix1jour() {
        return strprix1jour;
    }

    public void setStrprix1jour(String strprix1jour) {
        this.strprix1jour = strprix1jour;
    }

    public String getCodeGeneratedByOMNI() {
        return codeGeneratedByOMNI;
    }

    public void setCodeGeneratedByOMNI(String codeGeneratedByOMNI) {
        this.codeGeneratedByOMNI = codeGeneratedByOMNI;
    }

    public String getCreditStatusNow() {
        return creditStatusNow;
    }

    public void setCreditStatusNow(String creditStatusNow) {
        this.creditStatusNow = creditStatusNow;
    }

    public String getCreditStatFilterOFF() {
        return creditStatFilterOFF;
    }

    public void setCreditStatFilterOFF(String creditStatFilterOFF) {
        this.creditStatFilterOFF = creditStatFilterOFF;
    }

    public String getCreditStatFilterON() {
        return creditStatFilterON;
    }

    public void setCreditStatFilterON(String creditStatFilterON) {
        this.creditStatFilterON = creditStatFilterON;
    }

    public int getReqUpdateCredits() {
        return reqUpdateCredits;
    }

    public void setReqUpdateCredits(int reqUpdateCredits) {
        this.reqUpdateCredits = reqUpdateCredits;
    }

    public int getMontantTotalPayeNow() {
        return montantTotalPayeNow;
    }

    public void setMontantTotalPayeNow(int montantTotalPayeNow) {
        this.montantTotalPayeNow = montantTotalPayeNow;
    }

    public int getMontantTotalDuCredit() {
        return montantTotalDuCredit;
    }

    public void setMontantTotalDuCredit(int montantTotalDuCredit) {
        this.montantTotalDuCredit = montantTotalDuCredit;
    }

    public int getAcompteCreditNow() {
        return acompteCreditNow;
    }

    public void setAcompteCreditNow(int acompteCreditNow) {
        this.acompteCreditNow = acompteCreditNow;
    }

    public int getPrixUniteINT() {
        return prixUniteINT;
    }

    public void setPrixUniteINT(int prixUniteINT) {
        this.prixUniteINT = prixUniteINT;
    }

    public int getReqUpdateCreditStatus() {
        return reqUpdateCreditStatus;
    }

    public void setReqUpdateCreditStatus(int reqUpdateCreditStatus) {
        this.reqUpdateCreditStatus = reqUpdateCreditStatus;
    }

    public int getNowCDT() {
        return nowCDT;
    }

    public void setNowCDT(int nowCDT) {
        this.nowCDT = nowCDT;
    }

    public int getTotalCreditRembourse() {
        return totalCreditRembourse;
    }

    public void setTotalCreditRembourse(int totalCreditRembourse) {
        this.totalCreditRembourse = totalCreditRembourse;
    }

    public int getTotalCreditRestant() {
        return totalCreditRestant;
    }

    public void setTotalCreditRestant(int totalCreditRestant) {
        this.totalCreditRestant = totalCreditRestant;
    }

    public int getMontantpayeRefresh() {
        return montantpayeRefresh;
    }

    public void setMontantpayeRefresh(int montantpayeRefresh) {
        this.montantpayeRefresh = montantpayeRefresh;
    }

    public int getMontantrestantRefresh() {
        return montantrestantRefresh;
    }

    public void setMontantrestantRefresh(int montantrestantRefresh) {
        this.montantrestantRefresh = montantrestantRefresh;
    }

    public String getFinalHTop() {
        return finalHTop;
    }

    public void setFinalHTop(String finalHTop) {
        this.finalHTop = finalHTop;
    }

    public String getFinalDecimal() {
        return finalDecimal;
    }

    public void setFinalDecimal(String finalDecimal) {
        this.finalDecimal = finalDecimal;
    }

    public String getFinalCodeDec() {
        return finalCodeDec;
    }

    public void setFinalCodeDec(String finalCodeDec) {
        this.finalCodeDec = finalCodeDec;
    }

    public PaygcodesFacadeLocal getPaygcodesFacade() {
        return paygcodesFacade;
    }

    public void setPaygcodesFacade(PaygcodesFacadeLocal paygcodesFacade) {
        this.paygcodesFacade = paygcodesFacade;
    }

    public List<Paygcodes> getListPaygcodes() {
        return listPaygcodes;
    }

    public void setListPaygcodes(List<Paygcodes> listPaygcodes) {
        this.listPaygcodes = listPaygcodes;
    }

    public Paygcodes getPaygcodes() {
        return paygcodes;
    }

    public void setPaygcodes(Paygcodes paygcodes) {
        this.paygcodes = paygcodes;
    }

    public String getsDateLastEnd() {
        return sDateLastEnd;
    }

    public void setsDateLastEnd(String sDateLastEnd) {
        this.sDateLastEnd = sDateLastEnd;
    }

    public String getsDateNow() {
        return sDateNow;
    }

    public void setsDateNow(String sDateNow) {
        this.sDateNow = sDateNow;
    }

    public Date getDateTransact() {
        return dateTransact;
    }

    public void setDateTransact(Date dateTransact) {
        this.dateTransact = dateTransact;
    }

    public Date getDateTransact0() {
        return dateTransact0;
    }

    public void setDateTransact0(Date dateTransact0) {
        this.dateTransact0 = dateTransact0;
    }

    public Date getDateToday() {
        return dateToday;
    }

    public void setDateToday(Date dateToday) {
        this.dateToday = dateToday;
    }

    public Date getEstimEndDate() {
        return estimEndDate;
    }

    public void setEstimEndDate(Date estimEndDate) {
        this.estimEndDate = estimEndDate;
    }

    public Date getPreviousEstimEndDate() {
        return previousEstimEndDate;
    }

    public void setPreviousEstimEndDate(Date previousEstimEndDate) {
        this.previousEstimEndDate = previousEstimEndDate;
    }

    public Date getBoxEstimEndDate() {
        return boxEstimEndDate;
    }

    public void setBoxEstimEndDate(Date boxEstimEndDate) {
        this.boxEstimEndDate = boxEstimEndDate;
    }

    public int getNumInstallIN() {
        return numInstallIN;
    }

    public void setNumInstallIN(int numInstallIN) {
        this.numInstallIN = numInstallIN;
    }

    public List<Credits> getListInstalledCredits() {
        return listInstalledCredits;
    }

    public void setListInstalledCredits(List<Credits> listInstalledCredits) {
        this.listInstalledCredits = listInstalledCredits;
    }

    public CodeFacadeLocal getCodeFacade() {
        return codeFacade;
    }

    public void setCodeFacade(CodeFacadeLocal codeFacade) {
        this.codeFacade = codeFacade;
    }

    public List<Code> getListCode() {
        return listCode;
    }

    public void setListCode(List<Code> listCode) {
        this.listCode = listCode;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public String getIdTransactIN() {
        return idTransactIN;
    }

    public void setIdTransactIN(String idTransactIN) {
        this.idTransactIN = idTransactIN;
    }

    public MmtransactionFacadeLocal getMmtransactionFacade() {
        return mmtransactionFacade;
    }

    public void setMmtransactionFacade(MmtransactionFacadeLocal mmtransactionFacade) {
        this.mmtransactionFacade = mmtransactionFacade;
    }

    public List<Mmtransaction> getListMmtransaction() {
        return listMmtransaction;
    }

    public void setListMmtransaction(List<Mmtransaction> listMmtransaction) {
        this.listMmtransaction = listMmtransaction;
    }

    public Mmtransaction getMmtransaction() {
        return mmtransaction;
    }

    public void setMmtransaction(Mmtransaction mmtransaction) {
        this.mmtransaction = mmtransaction;
    }

    public int getManualOperatorID() {
        return manualOperatorID;
    }

    public void setManualOperatorID(int manualOperatorID) {
        this.manualOperatorID = manualOperatorID;
    }

    public String getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(String idTrans) {
        this.idTrans = idTrans;
    }

    public int getMaxIDPlus1() {
        return maxIDPlus1;
    }

    public void setMaxIDPlus1(int maxIDPlus1) {
        this.maxIDPlus1 = maxIDPlus1;
    }

    public String getRefManualCode() {
        return refManualCode;
    }

    public void setRefManualCode(String refManualCode) {
        this.refManualCode = refManualCode;
    }

    public String getNumPhone() {
        return numPhone;
    }

    public void setNumPhone(String numPhone) {
        this.numPhone = numPhone;
    }

    public static DateFormat getDateFormat() {
        return dateFormat;
    }

    public static void setDateFormat(DateFormat dateFormat) {
        RtoinstallationsController.dateFormat = dateFormat;
    }

    public String getDateTrans() {
        return dateTrans;
    }

    public void setDateTrans(String dateTrans) {
        this.dateTrans = dateTrans;
    }

    public String getFinalSMSstatut() {
        return finalSMSstatut;
    }

    public void setFinalSMSstatut(String finalSMSstatut) {
        this.finalSMSstatut = finalSMSstatut;
    }

    public String getStatutTransact() {
        return statutTransact;
    }

    public void setStatutTransact(String statutTransact) {
        this.statutTransact = statutTransact;
    }

    public String getEndDateForSMS() {
        return endDateForSMS;
    }

    public void setEndDateForSMS(String endDateForSMS) {
        this.endDateForSMS = endDateForSMS;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getCodePays() {
        return codePays;
    }

    public void setCodePays(String codePays) {
        this.codePays = codePays;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getStatutSMSsending() {
        return statutSMSsending;
    }

    public void setStatutSMSsending(String statutSMSsending) {
        this.statutSMSsending = statutSMSsending;
    }

    public String getStatusSMSsending() {
        return statusSMSsending;
    }

    public void setStatusSMSsending(String statusSMSsending) {
        this.statusSMSsending = statusSMSsending;
    }

    public boolean isShowStatusSMSsending() {
        return showStatusSMSsending;
    }

    public void setShowStatusSMSsending(boolean showStatusSMSsending) {
        this.showStatusSMSsending = showStatusSMSsending;
    }

    public String getStrNull() {
        return strNull;
    }

    public void setStrNull(String strNull) {
        this.strNull = strNull;
    }

    public static DateFormat getDateFormatSMS() {
        return dateFormatSMS;
    }

    public static void setDateFormatSMS(DateFormat dateFormatSMS) {
        RtoinstallationsController.dateFormatSMS = dateFormatSMS;
    }

    public int getAcompteRestantPourInstall() {
        return acompteRestantPourInstall;
    }

    public void setAcompteRestantPourInstall(int acompteRestantPourInstall) {
        this.acompteRestantPourInstall = acompteRestantPourInstall;
    }

    public String getStatutCodeGeneration() {
        return statutCodeGeneration;
    }

    public void setStatutCodeGeneration(String statutCodeGeneration) {
        this.statutCodeGeneration = statutCodeGeneration;
    }

    public String getPrixUnite() {
        return prixUnite;
    }

    public void setPrixUnite(String prixUnite) {
        this.prixUnite = prixUnite;
    }

    public Credits getSelectedCredits() {
        return selectedCredits;
    }

    public void setSelectedCredits(Credits selectedCredits) {
        this.selectedCredits = selectedCredits;
    }

    public String getFiltreIdPAYG() {
        return filtreIdPAYG;
    }

    public void setFiltreIdPAYG(String filtreIdPAYG) {
        this.filtreIdPAYG = filtreIdPAYG;
    }

    public String getFiltreIdPAYG2() {
        return filtreIdPAYG2;
    }

    public void setFiltreIdPAYG2(String filtreIdPAYG2) {
        this.filtreIdPAYG2 = filtreIdPAYG2;
    }

    public Date getDateDuJour() {
        return dateDuJour;
    }

    public void setDateDuJour(Date dateDuJour) {
        this.dateDuJour = dateDuJour;
    }

    public String getFiltreYesINITBox() {
        return filtreYesINITBox;
    }

    public void setFiltreYesINITBox(String filtreYesINITBox) {
        this.filtreYesINITBox = filtreYesINITBox;
    }

    public Date getEstimInstallDate() {
        return estimInstallDate;
    }

    public void setEstimInstallDate(Date estimInstallDate) {
        this.estimInstallDate = estimInstallDate;
    }

    public Date getDatePrevueInstall() {
        return datePrevueInstall;
    }

    public void setDatePrevueInstall(Date datePrevueInstall) {
        this.datePrevueInstall = datePrevueInstall;
    }

    public String getFiltreNoINITBox() {
        return filtreNoINITBox;
    }

    public void setFiltreNoINITBox(String filtreNoINITBox) {
        this.filtreNoINITBox = filtreNoINITBox;
    }

    public Date getDateValidInstall() {
        return dateValidInstall;
    }

    public void setDateValidInstall(Date dateValidInstall) {
        this.dateValidInstall = dateValidInstall;
    }

    public String getIdPAYGprodNew() {
        return idPAYGprodNew;
    }

    public void setIdPAYGprodNew(String idPAYGprodNew) {
        this.idPAYGprodNew = idPAYGprodNew;
    }

    public int getNbreJourSup() {
        return nbreJourSup;
    }

    public void setNbreJourSup(int nbreJourSup) {
        this.nbreJourSup = nbreJourSup;
    }

    public String getPhoneSAV() {
        return phoneSAV;
    }

    public void setPhoneSAV(String phoneSAV) {
        this.phoneSAV = phoneSAV;
    }

    public String getStatusTransfertSMStoSAV() {
        return statusTransfertSMStoSAV;
    }

    public void setStatusTransfertSMStoSAV(String statusTransfertSMStoSAV) {
        this.statusTransfertSMStoSAV = statusTransfertSMStoSAV;
    }

    public List<Paygotpgenerator> getListAllBoxsLucioles() {
        return listAllBoxsLucioles;
    }

    public void setListAllBoxsLucioles(List<Paygotpgenerator> listAllBoxsLucioles) {
        this.listAllBoxsLucioles = listAllBoxsLucioles;
    }

    public String getMessageToParse() {
        return messageToParse;
    }

    public void setMessageToParse(String messageToParse) {
        this.messageToParse = messageToParse;
    }

    public StringBuffer getStrBuff() {
        return strBuff;
    }

    public void setStrBuff(StringBuffer strBuff) {
        this.strBuff = strBuff;
    }
}
