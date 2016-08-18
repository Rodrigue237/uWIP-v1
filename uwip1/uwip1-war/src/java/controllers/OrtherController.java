/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import org.json.*;
import classes.AfricasTalkingGateway;
import classes.ShaHash;
import classes.RandomString;
import entities.Box;
import entities.Client;
import entities.Code;
import entities.Distributor;
import entities.Mmtransaction;
import entities.Organization;
import entities.Credits;
import entities.Paygcodes;
import entities.Paygotpgenerator;
import entities.Produits;
import entities.Termes;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import sessions.MmtransactionFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import sessions.CodeFacadeLocal;
import sessions.BoxFacadeLocal;
import sessions.ClientFacadeLocal;
import sessions.CreditsFacadeLocal;
import sessions.DistributorFacadeLocal;
import sessions.OrganizationFacadeLocal;
import sessions.PaygcodesFacadeLocal;
import sessions.PaygotpgeneratorFacadeLocal;
import sessions.ProduitsFacadeLocal;
import sessions.TermesFacadeLocal;
import controllers.CodeController;
import entities.Paygotpgenerator2;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import sessions.Paygotpgenerator2FacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "ortherController")
@ApplicationScoped
public class OrtherController {

    //Variable Pour OmniVoltaic Code
    private String h_RootForm = "";
    private int k_forms = 0;
    private int maxHCJ = 0;
    private int i_forms = 0;
    private int hcj_forms = 0;
    private int c_default = 100000;
    private int c_currentIndexForm = 0;
    private String hexToDec_forms = "";
    private String messagePOP = "";
    private boolean showMessagePOP = false;
    private int prix1jour = 1;
    private String strprix1jour = "";
    private String codeGeneratedByOMNI = "";
    // Variable generation code USSD OmniVoltaic
    private String idPAYGprod = "";
    //Variable de reception MobileMoney
    private String snifferTransact = "";
    private boolean valideTransact = false;
    private String statutTransact = "Pas de transaction";
    private String statutCodeGeneration = "";
    private String statutSMSsending = "";
    private String finalSMSstatut = "";
    private String statutCode = "";
    private String idOperat = "";
    private String idTrans = "";
    private String refDist = "";
    private String numBox = "";
    private String numPhone = "";
    private String dateTrans = "";
    private String refOffre = "";
    private String nbreUnite = "";
    private String prixUnite = "";
    private String statutSMSCode = "";
    private String idNewTransactMoney = "";
    private int reqCount = 0;
    // Variable PAYG and RTO
    private String creditStatusNow = "";
    private String creditStatFilterOFF = "Disabled";
    private String creditStatFilterON = "Enabled";
    private String boxinstallStatusOK = "USED";
    private int reqUpdateCredits = 0;
    private int montantTotalPayeNow = 0;
    private int montantTotalDuCredit = 0;
    private int acompteCreditNow = 0;
    private int acompteRestantPourInstall = 0;
    private int prixUniteINT = 0;
    private int reqUpdateCreditStatus = 0;
    private int nowCDT = 0;
    private int totalCreditRembourse = 0;
    private int totalCreditRestant = 0;
    private int montantpayeRefresh = 0;
    private int montantrestantRefresh = 0;
    private String creditSatus0 = "- -";
    private String creditSatus1 = "En attente d'acompte";
    private String creditSatus2 = "En attente d'installation";
    private String creditSatus3 = "Activé";
    private String creditSatus4 = "Desactivé";
    private String creditSatus5 = "Remboursé";
    //Variable generation code
    private int reqUpdateKeys = 0;
    private int reqUpdateCurrentC = 0;
    private int reqUpdateLastCode = 0;
    private int nbreJours = 30;
    private int numPCBCode = 0;
    private String creditSniffer = "";
    private int key1Code = 0;
    private int key2Code = 0;
    private int key3Code = 0;
    private int nextKey3 = 0;
    private int lastTotalTime = 0;
    private int valeurCode = 0;
    private int numBoxCode = 0;
    private String sDateLastEnd = "";
    private String sDateNow = "";
    //Variable initialisation du code
    private Box box = new Box();
    private Distributor distributor = new Distributor();
    private Paygcodes paygcodes = new Paygcodes();
    private long idBoxCode = 1;
    private long idClientCode = 1;
    private long idOrganizationCode = 1;
    private long idDistributeurCode = 1;
    private double prix30Jours = 5000;
    private double prix07Jours = 1500;
    private double prixCadeaux = 0;
    private double prixCode = 0;
    private BigInteger valeurCodeBig;
    private BigInteger valeurTimeCodeBig;
    private BigInteger lastValueTime;
    private BigInteger newLeftTimePaid;
    private BigInteger newTotalTimePaid;
    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    static DateFormat dateFormatForSMS = new SimpleDateFormat("dd-MM-yyyy");
    static DateFormat dateFormatSMS = new SimpleDateFormat("dd-MM-yyyy");
    private Date dateTransact;
    private Date dateNow;
    private Date dateTransact0;
    private Date dateToday;
    private Date estimEndDate;
    private Date previousEstimEndDate;
    private Date boxEstimEndDate;
    private long diffDate = 0;
    private long diffDateConvert = 0;
    private long CONST_DURATION_OF_DAY = 1000l * 60 * 60 * 24;
    private long estimLeftTime = 0;
    private int newLeftTimePaid0 = 0;
    private long jourRestant = 0;
    //Variable d'envoie SMS
    private String username = "upowa";
    private String apiKey = "86fbfe36157a6d75796f038f28f611ef00a9165c29eab9dde0d2555e8df47403";
    private String shortname = "UPOWA";
    private String codePays = "+237";
    private String phoneSAV = "+237691415574";
    private String statusTransfertSMStoSAV = "";
    private String recipients = "";
    private String message = "";
    private String myPhoneNumber = "+237696236215";
    private String smsAchatCode = "";
    private String smsRappelFin = "";
    private String smsVerifiCredit = "";
    private String dateExpireCode = "";
    private String MtR = "";
    private String endDateForSMS = "";
    private boolean sendAlert = false;
    private int minimForAlert1 = 1;
    private int minimForAlert3 = 3;
    private int minimForAlert7 = 7;
    private int minimForAlert2 = 2;
    private String statusSMSsending = "";
    private String notification = "";
    private boolean showStatusSMSsending = false;
    private String finalHTop = "";
    private String finalDecimal = "";
    private String finalCodeDec = "";
    private boolean showMessage = false;
    //@Inject
    //private MmtransactionFacadeLocal mmtransactionFacade;
    @Inject
    private PaygcodesFacadeLocal paygcodesFacade;
    @Inject
    private BoxFacadeLocal boxFacade;
    @Inject
    private OrganizationFacadeLocal organizationFacade;
    @Inject
    private MmtransactionFacadeLocal mmtransactionFacade;
    private List<Mmtransaction> listMmtransaction = new ArrayList<Mmtransaction>();
    private Mmtransaction mmtransaction = new Mmtransaction();
    @Inject
    private DistributorFacadeLocal distributorFacade;
    private List<Paygcodes> listPaygcodes = new ArrayList<Paygcodes>();
    private List<Box> listBoxsLibres = new ArrayList<Box>();
    private List<Organization> listOrganization = new ArrayList<Organization>();
    private List<Box> listBoxInstalled = new ArrayList<Box>();
    private List<Distributor> listDistributor = new ArrayList<Distributor>();
    private Client idclientUSSD;
    private Box idboxeUSSD;
    private Distributor iddistributorUSSD;
    private Organization organization = new Organization();
    private Organization idorganisationUSSD;
    @Inject
    private CreditsFacadeLocal creditsFacade;
    private List<Credits> listCredits = new ArrayList<Credits>();
    private Credits credits = new Credits();
    @Inject
    private ClientFacadeLocal clientFacade;
    private List<Client> listClientPotentiel = new ArrayList<Client>();
    private List<Client> listClient = new ArrayList<Client>();
    private Client client = new Client();
    @Inject
    private ProduitsFacadeLocal produitsFacade;
    private List<Produits> listProduitSystem = new ArrayList<Produits>();
    private List<Produits> listProduits = new ArrayList<Produits>();
    private Produits produits = new Produits();
    @Inject
    private TermesFacadeLocal termesFacade;
    private List<Termes> listTermes = new ArrayList<Termes>();
    private Termes termes = new Termes();
    @Inject
    private Paygotpgenerator2FacadeLocal paygotpgenerator2Facade;
    private List<Paygotpgenerator2> listPaygotpgenerator2 = new ArrayList<Paygotpgenerator2>();
    private Paygotpgenerator2 paygotpgenerator2 = new Paygotpgenerator2();
    @Inject
    private PaygotpgeneratorFacadeLocal paygotpgeneratorFacade;
    private List<Paygotpgenerator> listPaygotpgenerator = new ArrayList<Paygotpgenerator>();
    private Paygotpgenerator paygotpgenerator = new Paygotpgenerator();
    @Inject
    private CodeFacadeLocal codeFacade;
    private List<Code> listCode = new ArrayList<Code>();
    private Code code = new Code();
    //private static Timer timer;
    private static TimerTask ourTask;
    //Star Variables for autoTask
    int lenghtCDT = 0;
    int lenghtPAYGfactory = 0;
    private String filtres = " ";
    private String statusTask = "ECHEC";
    private int waitBeforeStarTask = 3;
    private int intervalBetweenTasks = 1500;
    private int waitBeforeStarTaskSMS = 0;
    private int intervalBetweenTasksSMS = 0;
    private int otpInterval = 900;// In second(s)
    Timer timer2;
    Timer timer3;
    Random myRadom = new Random();
    private String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //End Variables for AutoTask

    /**
     * Creates a new instance of OrtherController
     */
    public OrtherController() {
    }
    
    public void chargeListClient() {
        listClient.clear();
        listClient.addAll(clientFacade.findAll());
    }
    
    public void chargeListCode() {
        listCode.clear();
        listCode.addAll(codeFacade.findAll());
    }
    
    public void chargeListPaygFactory() {
        listPaygotpgenerator2.clear();
        listPaygotpgenerator2.addAll(paygotpgenerator2Facade.findAll());
    }
    
    public void chargeListDistributor() {
        listDistributor.clear();
        listDistributor.addAll(distributorFacade.findAll());
    }
    
    public void chargeListOrganization() {
        listOrganization.clear();
        listOrganization.addAll(organizationFacade.findAll());
    }
    
    public void chargeListBoxsLibre() {
        listBoxsLibres.clear();
        listBoxsLibres.addAll(boxFacade.findAll());
    }
    
    public void chargeListBoxsInstalled() {
        listBoxInstalled.clear();
        listBoxInstalled.addAll(codeFacade.findByInstalled());
    }
    
    public void chargeListCredits() {
        listCredits.clear();
        listCredits.addAll(creditsFacade.findAll());
    }
    
    public void chargeListProduits() {
        listProduits.clear();
        listProduits.addAll(produitsFacade.findAll());
    }
    
    public void chargeListTermes() {
        listTermes.clear();
        listTermes.addAll(termesFacade.findAll());
    }
    
    public void chargeListPaygotpgenerator() {
        listPaygotpgenerator.clear();
        listPaygotpgenerator.addAll(paygotpgeneratorFacade.findAll());
    }
    
    public void chargeListMmtransaction() {
        listMmtransaction.clear();
        listMmtransaction.addAll(mmtransactionFacade.findAll());
    }
    
    public void chargeListPaygcodes() {
        listPaygcodes.clear();
        listPaygcodes.addAll(paygcodesFacade.findAll());
    }
    
    public void prepareCreate2() {
        code = new Code();
    }
    
    public void prepareCreatePaygcodes() {
        paygcodes = new Paygcodes();
    }
    
    public void prepareCreateProduits() {
        produits = new Produits();
    }
    
    public void prepareCreateTermes() {
        termes = new Termes();
    }
    
    public void prepareCreateCredits() {
        credits = new Credits();
    }
    
    public void prepareCreatePaygotpgenerator() {
        paygotpgenerator = new Paygotpgenerator();
    }
    
    public void prepareCreate() {
        mmtransaction = new Mmtransaction();
    }
    
    public void prepareCreatePaygFactory() {
        paygotpgenerator2 = new Paygotpgenerator2();
    }
    
    public void prepareCreateHTop() {
        finalHTop = "NoHTop";
    }
    
    public void prepareTasksCDT() {
        waitBeforeStarTask = 0;
        intervalBetweenTasks = 0;
    }
    
    public void prepareTasksAlertSMS() {
        waitBeforeStarTaskSMS = 0;
        intervalBetweenTasksSMS = 0;
    }
    
    public void prepareCreateDecimal() {
        finalDecimal = "NoValue";
    }
    
    public void prepareCreateCodeOmni() {
        finalCodeDec = "NoValue";
    }
    
    public String pagetest() {
        return "testomnivoltaic.xhtml?faces-redirect=true";
    }
    
    public String pageAutoTasks() {
        return "autotasks.xhtml?faces-redirect=true";
    }
    
    public String pagePaygFactory() {
        chargeListPaygFactory();
        return "paygfactorys.xhtml?faces-redirect=true";
    }
    
    public String pageTransactions() {
        chargeListMmtransaction();
        return "transactions.xhtml?faces-redirect=true";
    }

    //Imported Methode (START)
    public void creationCodeUssdV12() {
        System.out.println("Nombre de jours:" + nbreJours);
        System.out.println("Numero Box:" + numBoxCode);
        numPCBCode = codeFacade.findNumPcb(numBoxCode);
        key1Code = codeFacade.findKey1(numBoxCode);
        key2Code = codeFacade.findKey2(numBoxCode);
        key3Code = codeFacade.findKey3(numBoxCode);
        valeurCode = numPCBCode * key1Code * (nbreJours * key2Code + key3Code);
        System.out.println("Key1:" + key1Code);
        System.out.println("Key2:" + key2Code);
        System.out.println("Key3:" + key3Code);
        System.out.println("Numero Box:" + numBoxCode);
        System.out.println("Numero PCB:" + numPCBCode);
        System.out.println("Code généré:" + valeurCode);
    }
    
    public int calculNbreJours() {
        int nbreJoursCalcule = prixUniteINT / prix1jour;
        return nbreJoursCalcule;
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
            prixUniteINT = Integer.parseInt(prixUnite);
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
            prixUniteINT = Integer.parseInt(prixUnite);
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
    
    public void creation1erCodeUssdLuciole() {
        System.out.println("Numero Box:" + numBoxCode);
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
        prixUniteINT = Integer.parseInt(prixUnite);
        hcj_forms = calculNbreJours();
        hcj_forms += 30;
        System.out.println("Nombre de jour by calculate:" + hcj_forms);
        codeGeneratedByOMNI = generateUSSDCodeOMNI();
        System.out.println("Code final OMNI:" + codeGeneratedByOMNI);
        int nextCDT = hcj_forms;
        int reqUpdateCDT = paygotpgeneratorFacade.findToUpdateCDT(idPAYGprod, hcj_forms);
        System.out.println("Nbre upDate of CDT:" + reqUpdateCDT);
    }
    
    public void initialisationCodeUssd() throws ParseException {
        valeurCodeBig = new BigInteger(Integer.toString(valeurCode));
        valeurTimeCodeBig = new BigInteger(Integer.toString(nbreJours));
        idBoxCode = codeFacade.findIdBoxOfCode(numBoxCode);
        idClientCode = codeFacade.findIdClientOfCode(numBoxCode);
        idOrganizationCode = codeFacade.findIdOrganizationOfCode(numBoxCode);
        idDistributeurCode = 1;
        code.setIdboxe(new Box(idBoxCode));
        code.setIddistributor(new Distributor(idDistributeurCode));
        code.setIdclient(new Client(idClientCode));
        code.setIdorganization(new Organization(idOrganizationCode));
        code.setCodevalue(valeurCodeBig);
        code.setGenekeyused(key3Code);
        code.setTimevalue(valeurTimeCodeBig);
        code.setPrice(Double.parseDouble(prixUnite));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
        String sDate = dateFormat.format(new Date());
        code.setDategene(dateFormat.parse(sDate));
        code.setTimegene(sDate);
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
    
    public int lookCDTnow() {
        int actualCDT = paygotpgeneratorFacade.findCDT(idPAYGprod);
        return actualCDT;
    }
    
    public int calculNbreJoursCodeUssd(int prixUniteUssd) {
        int prix1Jour = 100;
        int nbreJoursBuy = prixUniteUssd / prix1Jour;
        return nbreJoursBuy;
    }
    
    public void upNextKeyUssd() {
        nextKey3 = key3Code + 1;
        lastValueTime = new BigInteger(Integer.toString(nbreJours));
        reqUpdateKeys = codeFacade.findToUpdateKeys(key3Code, nextKey3, idBoxCode, dateTransact0, estimEndDate, lastValueTime, newLeftTimePaid, newTotalTimePaid);
    }
    
    public void upNextKeyUssdLuciole() {
        reqUpdateCurrentC = paygcodesFacade.findToUpdateCurrentC(k_forms, idPAYGprod);
        System.out.println("Total updated CurrentC: " + reqUpdateCurrentC);
        reqUpdateLastCode = creditsFacade.findToUpdateLastCode(numBoxCode, codeGeneratedByOMNI);
        System.out.println("Total updated LastCode: " + reqUpdateLastCode);
    }
    
    public void operationsDatesAmont() {
        try {
            DateFormat dateFormatLastEnd = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
            sDateLastEnd = dateFormatLastEnd.format(codeFacade.findLastEndDate(numBoxCode));
            sDateNow = dateFormatLastEnd.format(new Date());
            System.out.println("Last end date:" + sDateLastEnd);
            dateTransact0 = dateFormatLastEnd.parse(sDateNow);
            previousEstimEndDate = dateFormatLastEnd.parse(sDateLastEnd);
            diffDate = Math.abs(previousEstimEndDate.getTime() - dateTransact0.getTime()) / CONST_DURATION_OF_DAY;
            if (diffDate > 0) {
                diffDate = diffDate + 0;
                GregorianCalendar calendar = new java.util.GregorianCalendar();
                calendar.setTime(previousEstimEndDate);
                calendar.add(Calendar.DATE, nbreJours);
                estimEndDate = calendar.getTime();
            } else {
                diffDate = 0;
                GregorianCalendar calendar = new java.util.GregorianCalendar();
                calendar.setTime(dateTransact0);
                calendar.add(Calendar.DATE, nbreJours);
                estimEndDate = calendar.getTime();
            }
            System.out.println("Nbre jour restant sur la box:" + diffDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void operationDatesAval() {
        try {
            estimLeftTime = diffDate + nbreJours;
            System.out.println("Estim LeftTimePaid:" + estimLeftTime);
            newLeftTimePaid0 = (int) estimLeftTime;
            newLeftTimePaid = new BigInteger(Integer.toString(newLeftTimePaid0));// Sera mise à jour dans upNextKey()
            calculTotalTimePaid();
            newTotalTimePaid = new BigInteger(Integer.toString(lastTotalTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Imported Methode (END)
    // METHODE FOR OMNIVOLTAIC GENERATOR (START)
    public String generateHTop() {
        try {
            finalHTop = (new ShaHash().H_Fonction(h_RootForm, k_forms));
            showMessagePOP = true;
            messagePOP = "Succes! HTop = " + finalHTop;
        } catch (Exception e) {
            showMessagePOP = true;
            messagePOP = "Echec de generation";
            e.printStackTrace();
        }
        return pagetest();
    }
    
    public String generateDecimal() {
        try {
            finalDecimal = new ShaHash().hash_str_to_Low_High_Bytes(hexToDec_forms);
            showMessagePOP = true;
            messagePOP = "Succes!Dec = " + finalDecimal;
        } catch (Exception e) {
            showMessagePOP = true;
            messagePOP = "Echec de generation";
            e.printStackTrace();
        }
        return pagetest();
    }
    
    public String generateCodeOMNI() {
        try {
            k_forms = c_currentIndexForm - hcj_forms;
            finalCodeDec = new ShaHash().hash_str_to_Low_High_Bytes(new ShaHash().H_Fonction(h_RootForm, k_forms));
            showMessagePOP = true;
            messagePOP = "Succes!Dec = " + finalCodeDec;
        } catch (Exception e) {
            showMessagePOP = true;
            messagePOP = "Echec de generation";
            e.printStackTrace();
        }
        return pagetest();
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

    // METHODE FOR OMNIVOLTAIC GENERATOR (END)
    public void prepareEdit() {
        mmtransaction = new Mmtransaction();
    }
    
    public void prepareEdit2() {
        code = new Code();
    }
    
    public String saveMmtransaction() {
        try {
            chargeListCode();
            chargeListOrganization();
            chargeListClient();
            chargeListDistributor();
            chargeListBoxsInstalled();
            chargeListCredits();
            chargeListProduits();
            chargeListTermes();
            chargeListPaygotpgenerator();
            chargeListPaygcodes();
            // Capture des valeurs de transaction passées en parametre
            captureURL();
            // Initialisation de la transaction
            initialisationTransaction();
            idNewTransactMoney = idTrans;
            System.out.println("id Transaction: " + idNewTransactMoney);
            reqCount = mmtransactionFacade.findCopieDouble(idNewTransactMoney);
            System.out.println("Toal duplique Transact: " + reqCount);
            if (reqCount >= 1) {
                statutTransact = "Erreur: Id transaction deja existant.";
            } else {
                mmtransactionFacade.create(mmtransaction); // Creation d'une nouvelle transaction
                numBoxCode = Integer.parseInt(numBox);
                int prixUniteUssd = Integer.parseInt(prixUnite);
                nbreJours = calculNbreJoursCodeUssd(prixUniteUssd);
                statutCodeGeneration = saveUSSDCodeAutomatic();
                statutTransact = "Transaction OK, " + statutCodeGeneration + " et " + sendSMSafterTransact();
            }
        } catch (Exception e) {
            statutTransact = "Echec enregistrement";
            e.printStackTrace();
        }
        return statutTransact;
    }
    
    public String saveMmtransactionLuciole() {
        try {
            // Capture des valeurs de transaction passées en parametre
            captureURL();
            // Initialisation de la transaction
            initialisationTransaction();
            idNewTransactMoney = idTrans;
            System.out.println("id Transaction: " + idNewTransactMoney);
            reqCount = mmtransactionFacade.findCopieDouble(idNewTransactMoney);
            System.out.println("Total duplique Transact: " + reqCount);
            if (reqCount >= 1) {
                statutTransact = "ID_TRANSACTION_DOUBLE";
            } else {
                mmtransactionFacade.create(mmtransaction); // Creation d'une nouvelle transaction
                System.out.println("Nbre Transact cree: 1");
            }
        } catch (Exception e) {
            statutTransact = "ECHEC";
            e.printStackTrace();
        }
        try {
            numBoxCode = Integer.parseInt(numBox);
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
                statutCodeGeneration = saveAutoCodeLucioleDebloc(maxHCJ);
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
                    statutCodeGeneration = saveUSSDCodeAutomaticLuciole();
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
                    finalSMSstatut = sendSMSafterTransactLuciole();
                    statutTransact = finalSMSstatut;
                }
            }
        } catch (Exception e) {
            statutTransact = "ECHEC";
            e.printStackTrace();
        }
        return statutTransact;
    }
    
    public String sendSMSafterTransactLuciole() {
        try {
            message = "MMerci pour votre paiement de " + prixUnite + ".00 FCFA. Entrez le code " + codeGeneratedByOMNI + " pour activer l'installation " + numBoxCode + " jusqu'au " + endDateForSMS + ". Montant total rembourse " + montantpayeRefresh + ".00 FCFA, credit restant a payer " + montantrestantRefresh + ".00 FCFA.";
            recipients = codePays + numPhone;
            statutSMSsending = sendSMS(username, apiKey, recipients, message, shortname);
            statusTransfertSMStoSAV = sendSMS(username, apiKey, phoneSAV, message, shortname);
        } catch (Exception e) {
            statutTransact = "ECHEC";
            e.printStackTrace();
        }
        return statutSMSsending;
    }
    
    public String saveAutoCodeLucioleDebloc(int keyPAYGtoFREE) {
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
    
    public String sendSMSafterTransact() {
        try {
            message = "Merci pour votre paiement de " + prixUnite + "FCFA. Montant total remboursé " + montantpayeRefresh + "FCFA, crédit restant à payer " + montantrestantRefresh + "FCFA. Entrez le code " + valeurCode + " pour activer l'installation " + numBox + " jusquau JJ MM AAAA.";
            //message = "upOwa info:Merci pour votre achat de "+prixUnite+"FCFA. Entrez le code "+valeurCode+" pour activer l’installation "+numBox+ "jusqu’au28 "+dateExpireCode;
            //message = "upOwa alerte:Votre crédit arrive à expirationle "+dateExpireCode+" Rachetez du crédit upOwa pour ne pas rester dans le noir.";
            //message = "upOwa info:Votre installation "+numBox+" est activée jusqu’au "+dateExpireCode+" Il vous reste "+MtR+" FCFA à rembourser pour être propriétaire de votre système.";
            recipients = codePays + numPhone;
            statutSMSsending = sendSMS(username, apiKey, recipients, message, shortname);
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
    
    public void alertEndDate(int numBoxAlert) {
        // Alert SMS sur la fin de credit
        try {
            DateFormat dateFormatAlert = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
            String sDateLastEnd = dateFormatAlert.format(codeFacade.findLastEndDate(numBoxAlert));
            String sDateNow = dateFormatAlert.format(new Date());
            System.out.println("Last end date:" + sDateLastEnd);
            dateToday = dateFormat.parse(sDateNow);
            boxEstimEndDate = dateFormat.parse(sDateLastEnd);
            jourRestant = Math.abs(boxEstimEndDate.getTime() - dateToday.getTime()) / CONST_DURATION_OF_DAY;
            if (jourRestant == minimForAlert1) {
                sendAlert = true;
                //Ici code d'envoie SMS d'alert
            } else {
                sendAlert = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void captureURL() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest httpRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        idOperat = httpRequest.getParameter("idOperat");
        idTrans = httpRequest.getParameter("idTrans");
        numBox = httpRequest.getParameter("numInst");
        numPhone = httpRequest.getParameter("numPhone");
        dateTrans = httpRequest.getParameter("dateTrans");
        prixUnite = httpRequest.getParameter("amountPaid");
        nbreJours = 1;
    }
    
    public void initialisationTransaction() {
        mmtransaction.setIdoperator(Integer.parseInt(idOperat));
        mmtransaction.setIdtransact(idTrans);
        mmtransaction.setNumbox(Integer.parseInt(numBox));
        mmtransaction.setNumphone(Integer.parseInt(numPhone));
        mmtransaction.setDatetrans(dateTrans);
        mmtransaction.setPrixunite(Integer.parseInt(prixUnite));
        mmtransaction.setSommetotale(Integer.parseInt(prixUnite));
    }
    
    public void calculTotalTimePaid() {
        lastTotalTime = codeFacade.findLastTotalTimePaid(numBoxCode);
        lastTotalTime = lastTotalTime + nbreJours;
    }
    
    public String saveUSSDCodeAutomatic() {
        try {
            operationsDatesAmont();
            creationCodeUssdV12();
            initialisationCodeUssd();
            operationDatesAval();
            codeFacade.create(code);
            upNextKeyUssd();
            System.out.println("Nbre keys box updated:" + reqUpdateKeys);
            showMessage = true;
            statutCode = "Nouveau code généré avec succès";
        } catch (Exception e) {
            showMessage = true;
            statutCode = "Echec de génération de code";
            e.printStackTrace();
        }
        return statutCode;
    }
    
    public String saveUSSDCodeAutomaticLuciole() {
        try {
            creationCodeUssdLuciole();
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
    
    public String saveCodeAutomatiq() {
        try {
            DateFormat dateFormatLastEnd = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
            sDateLastEnd = dateFormatLastEnd.format(codeFacade.findLastEndDate(numBoxCode));
            sDateNow = dateFormatLastEnd.format(new Date());
            System.out.println("Last end date:" + sDateLastEnd);
            dateTransact0 = dateFormat.parse(sDateNow);
            previousEstimEndDate = dateFormat.parse(sDateLastEnd);
            diffDate = Math.abs(previousEstimEndDate.getTime() - dateTransact0.getTime()) / CONST_DURATION_OF_DAY;
            if (diffDate >= 0) {
                diffDate = diffDate + 0;
            } else {
                diffDate = 0;
            }
            System.out.println("Nbre jour restant sur la box:" + diffDate);
            estimLeftTime = diffDate + nbreJours;
            System.out.println("Estim LeftTimePaid:" + estimLeftTime);
            newLeftTimePaid0 = (int) estimLeftTime;
            newLeftTimePaid = new BigInteger(Integer.toString(newLeftTimePaid0));
            calculTotalTimePaid();
            newTotalTimePaid = new BigInteger(Integer.toString(lastTotalTime));
            codeFacade.create(code);
            upNextKeyUssd();
            System.out.println("Nbre keys box updated:" + reqUpdateKeys);
            notification = sendSMSafterTransact();
            statutCode = "Nouveau code généré avec succès";
        } catch (Exception e) {
            statutCode = "Echec de génération de code";
            e.printStackTrace();
        }
        return statutCode;
    }

    //2eme METHODE DE SCRIT AUTOMATIQUE
    public void startTask_CDT() {
        timer2 = new Timer();
        timer2.schedule(new timerTask_CDT(), waitBeforeStarTask * 1000, intervalBetweenTasks * 1000);
    }
    
    public void startTask_AlertSMS() {
        timer3 = new Timer();
        timer3.schedule(new timerTask_AlertSMS(), waitBeforeStarTaskSMS * 1000, intervalBetweenTasksSMS * 1000);
    }
    
    public String execTaskCDT() {
        try {
            System.out.println("Starting...");
            startTask_CDT();
            showMessagePOP = true;
            messagePOP = "Tache initialisée avec succès";
        } catch (Exception e) {
            showMessagePOP = true;
            messagePOP = "Echec d'initialisation de la tache";
            e.printStackTrace();
        }
        return "autotasks.xhtml?faces-redirect=true";
    }
    
    public String execTaskAlertSMS() {
        try {
            System.out.println("Starting...");
            startTask_AlertSMS();
            showMessagePOP = true;
            messagePOP = "Tache initialisée avec succès";
        } catch (Exception e) {
            showMessagePOP = true;
            messagePOP = "Echec d'initialisation de la tache";
            e.printStackTrace();
        }
        return "autotasks.xhtml?faces-redirect=true";
    }
    
    public String autoGenerateAllSEED() {
        try {
            lenghtPAYGfactory = paygotpgenerator2Facade.count();
            System.out.println("Total line = " + lenghtPAYGfactory);
            System.out.println("DEBUT AUTO GENERATE SEED");
            listPaygotpgenerator2.clear();
            listPaygotpgenerator2.addAll(paygotpgenerator2Facade.findAll());
            for (int i = lenghtPAYGfactory; i >= 1; i--) {
                System.out.println("Itteration: " + i);
                //Index Java (De 0 a i-1)
                int indexLine = i - 1;
                System.out.println("Index: " + indexLine);
                String idPAYGnow = listPaygotpgenerator2.get(indexLine).getPaygproductid();
                String paygCode = listPaygotpgenerator2.get(indexLine).getProductcode();
                //char randomSalt = alphabet.charAt(myRadom.nextInt(alphabet.length()));
                String randomSalt = new RandomString(6).nextString();
                System.out.println("Random char (" + indexLine + ") = " + randomSalt);
                String geneSEED = idPAYGnow + randomSalt + paygCode;
                System.out.println("New SEED: " + geneSEED);
                int reqUpdateSalt = paygotpgenerator2Facade.updateSalt(randomSalt, idPAYGnow);
                System.out.println("Total update of Salt: " + reqUpdateSalt);
                int reqUpdateSeed = paygotpgenerator2Facade.updateSeed(geneSEED, idPAYGnow);
                System.out.println("Total update of Seed: " + reqUpdateSeed);
            }
            System.out.println("FIN AUTO GENERATE SEED");
            //paygotpgenerator2Facade.create(paygotpgenerator2); // Creation du organization dans la derniere ligne vide
            showMessagePOP = true;
            messagePOP = "Enregistrement effectué avec succès";
        } catch (Exception e) {
            showMessagePOP = true;
            messagePOP = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return pagePaygFactory();
    }
    
    public String autoGenerateAllHROOT() {
        try {
            lenghtPAYGfactory = paygotpgenerator2Facade.count();
            System.out.println("Total line = " + lenghtPAYGfactory);
            System.out.println("DEBUT AUTO GENERATE H-ROOT");
            listPaygotpgenerator2.clear();
            listPaygotpgenerator2.addAll(paygotpgenerator2Facade.findAll());
            for (int i = lenghtPAYGfactory; i >= 1; i--) {
                System.out.println("Itteration: " + i);
                //Index Java (De 0 a i-1)
                int indexLine = i - 1;
                System.out.println("Index: " + indexLine);
                String nowSEED = listPaygotpgenerator2.get(indexLine).getSeed();               
                System.out.println("Now SEED: " + nowSEED);
                String idPAYGnow = listPaygotpgenerator2.get(indexLine).getPaygproductid();
                System.out.println("Now PAYGid: " + idPAYGnow);
                h_RootForm = nowSEED;
                k_forms = 1;
                String geneHroot = new ShaHash().H_Fonction(h_RootForm, k_forms);
                System.out.println("Generate Hroot: " + geneHroot);
                int reqUpdateHroot = paygotpgenerator2Facade.updateHroot(geneHroot, idPAYGnow);
                System.out.println("Total update of Hroot: " + reqUpdateHroot);
            }
            System.out.println("FIN AUTO GENERATE HOOT");
            //paygotpgenerator2Facade.create(paygotpgenerator2); // Creation du organization dans la derniere ligne vide
            showMessagePOP = true;
            messagePOP = "Enregistrement effectué avec succès";
        } catch (Exception e) {
            showMessagePOP = true;
            messagePOP = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return pagePaygFactory();
    }
    
    public String autoGenerateAllHTOP() {
        try {
            lenghtPAYGfactory = paygotpgenerator2Facade.count();
            System.out.println("Total line = " + lenghtPAYGfactory);
            System.out.println("DEBUT AUTO GENERATE H-TOP");
            listPaygotpgenerator2.clear();
            listPaygotpgenerator2.addAll(paygotpgenerator2Facade.findAll());
            for (int i = lenghtPAYGfactory; i >= 1; i--) {
                System.out.println("Itteration: " + i);
                //Index Java (De 0 a i-1)
                int indexLine = i - 1;
                System.out.println("Index: " + indexLine);
                String nowHROOT = listPaygotpgenerator2.get(indexLine).getHroot();               
                System.out.println("Now HROOT: " + nowHROOT);
                String idPAYGnow = listPaygotpgenerator2.get(indexLine).getPaygproductid();
                System.out.println("Now PAYGid: " + idPAYGnow);
                h_RootForm = nowHROOT;
                k_forms = 100000;
                String geneTop = new ShaHash().H_Fonction(h_RootForm, k_forms);
                System.out.println("Generate Htop: " + geneTop);
                int reqUpdateHtop = paygotpgenerator2Facade.updateHtop(geneTop, idPAYGnow);
                System.out.println("Total update of Htop: " + reqUpdateHtop);
            }
            System.out.println("FIN AUTO GENERATE HTOP");
            //paygotpgenerator2Facade.create(paygotpgenerator2); // Creation du organization dans la derniere ligne vide
            showMessagePOP = true;
            messagePOP = "Enregistrement effectué avec succès";
        } catch (Exception e) {
            showMessagePOP = true;
            messagePOP = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return pagePaygFactory();
    }
    
    public String saveFirstPaygFactory() {
        try {
            paygotpgenerator2Facade.create(paygotpgenerator2);
            showMessagePOP = true;
            messagePOP = "Enregistrement effectué avec succès";
        } catch (Exception e) {
            showMessagePOP = true;
            messagePOP = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return pagePaygFactory();
    }
    
    class timerTask_CDT extends TimerTask {
        
        public void run() {
            autoUpdateCDT24h();
        }
    }
    
    class timerTask_AlertSMS extends TimerTask {
        
        public void run() {
            autoSendAlertSMS();
        }
    }
    
    public void autoUpdateCDT24h() {
        try {
            String boxInitialiseStatusOK = "YES";
            //String creditSatus4 = "Desactivé";
            System.out.println("Initialising variables...");
            //String creditSatus5 = "Remboursé";
            lenghtCDT = paygotpgeneratorFacade.count();
            System.out.println("Total line = " + lenghtCDT);
            System.out.println("DEBUT AUTO");
            listPaygotpgenerator.clear();
            listPaygotpgenerator.addAll(paygotpgeneratorFacade.findAll());
            for (int i = lenghtCDT; i >= 1; i--) {
                System.out.println("Itteration: " + i);
                //Index Java (De 0 a i-1)
                int indexCDT = i - 1;
                System.out.println("Index: " + indexCDT);
                // Recuperation des status
                //String nowLifeCycleStatus = listPaygotpgenerator.get(indexCDT).getPayglifecyclestatus();
                //String nowBoxStatus = listPaygotpgenerator.get(indexCDT).getBoxstatus();
                String nowInitialiseStatus = listPaygotpgenerator.get(indexCDT).getInitialisationstatus();
                String nowBoxInstallStatus = listPaygotpgenerator.get(indexCDT).getBoxstatus();
                if (nowInitialiseStatus.equals(boxInitialiseStatusOK)) {
                    // Recuperation du CDT de l'index
                    nowCDT = listPaygotpgenerator.get(indexCDT).getOtpcount();
                    // Recuperation de l'ID PAYG de l'index
                    idPAYGprod = listPaygotpgenerator.get(indexCDT).getIdpaygproduct();
                    System.out.println("PAYG_ID-PROD (" + i + "): " + idPAYGprod);
                    //Calcul du nouveau CDT apres 24h passé
                    int newtCDT = nowCDT - 1;
                    //Update du CDT
                    int reqUpdateCDT = paygotpgeneratorFacade.findToUpdateCDT(idPAYGprod, newtCDT);
                    System.out.println("Now CDT (" + i + "): " + nowCDT);
                    System.out.println("New CDT (" + i + "): " + newtCDT);
                    System.out.println("Nbre update of CDT = " + reqUpdateCDT);
                    if (newtCDT <= 0) {
                        paygotpgenerator.setIdpaygproduct(idPAYGprod);
                        if (nowBoxInstallStatus.equals(boxinstallStatusOK)) {
                            creditStatusNow = creditsFacade.findCreditByPAYGprodID(paygotpgenerator).getCreditstatus();
                            if (creditStatusNow.equals(creditSatus2)) {
                                System.out.println("Systeme en attente d'installation. Passez");
                            } else {
                                //credits.setIdpaygproduct(paygotpgenerator);
                                reqUpdateCreditStatus = creditsFacade.findToUpdateCredStatAfterAutoCDT(paygotpgenerator, creditSatus4);
                                System.out.println("Nbre update of CreditStatus = " + reqUpdateCreditStatus);
                                System.out.println("Nbre de credit desactivé = " + reqUpdateCreditStatus);
                            }
                        }
                    } else {
                        System.out.println("Systeme à Jour. Passez");
                    }
                } else {
                    System.out.println("Non Valide pour la decrementation du CDT. Passez");
                }
                
            }
            System.out.println("FIN AUTO");
            System.out.println("-----------");
            //timer.cancel(); //Terminer la tache auto
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //FIN 2eme METHODE DE SCRIPT AUTOMATIQUE

    //DEBUT METHODE D'ENVOIE DE SMS D'ALERT
    public void autoSendAlertSMS() {
        try {
            String boxInitialiseStatusOK = "YES";
            String boxInstallStatusOK = "USED";
            System.out.println("Initialising variables...");
            lenghtCDT = paygotpgeneratorFacade.count();
            System.out.println("Total line = " + lenghtCDT);
            System.out.println("DEBUT AUTO");
            listPaygotpgenerator.clear();
            listPaygotpgenerator.addAll(paygotpgeneratorFacade.findAll());
            for (int i = lenghtCDT; i >= 1; i--) {
                System.out.println("Itteration: " + i);
                //Index Java (De 0 a i-1)
                int indexCDT = i - 1;
                System.out.println("Index: " + indexCDT);
                // Recuperation des status
                String nowInitialiseStatus = listPaygotpgenerator.get(indexCDT).getInitialisationstatus();
                String nowBoxInstallStatus = listPaygotpgenerator.get(indexCDT).getBoxstatus();
                if (nowInitialiseStatus.equals(boxInitialiseStatusOK) && nowBoxInstallStatus.equals(boxInstallStatusOK)) {
                    // Recuperation du CDT de l'index
                    nowCDT = listPaygotpgenerator.get(indexCDT).getOtpcount();
                    // Recuperation de l'ID PAYG de l'index
                    idPAYGprod = listPaygotpgenerator.get(indexCDT).getIdpaygproduct();
                    Paygotpgenerator idPAYGprodObject = new Paygotpgenerator();
                    idPAYGprodObject.setIdpaygproduct(idPAYGprod);
                    System.out.println("PAYG ID: " + idPAYGprod);
                    int NumInstallNow = creditsFacade.findCreditByPAYGprodID(idPAYGprodObject).getNuminstall();
                    System.out.println("--NUMERO D'INSTALLTION: " + NumInstallNow + " --");
                    System.out.println("Now CDT (" + i + "): " + nowCDT);
                    if (nowCDT == minimForAlert7) {
                        System.out.println("Date de coupure dans " + minimForAlert7 + " Jours proche. Envoie de SMS Alert...");
                        String numPhoneForAlert = creditsFacade.findCreditByPAYGprodID(idPAYGprodObject).getIdclient().getPreferredphone();
                        //Debut Operation de date de fin forfait
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
                        String sDate = dateFormat.format(new Date());
                        dateNow = dateFormat.parse(sDate);//Conversion de string en date
                        GregorianCalendar calendar = new java.util.GregorianCalendar();
                        calendar.setTime(dateNow);
                        calendar.add(Calendar.DATE, minimForAlert7);
                        estimEndDate = calendar.getTime();//Conversion de calendar en date
                        System.out.println("Estim EndDate for Alert:" + estimEndDate);
                        endDateForSMS = dateFormatForSMS.format(estimEndDate);
                        System.out.println("End date for SMS: " + endDateForSMS);
                        System.out.println("Numero prefere: " + numPhoneForAlert);
                        //Fin Operation de date de fin forfait
                        recipients = codePays + numPhoneForAlert;
                        System.out.println("Numero pour SMS: " + recipients);
                        message = "upOwa alerte:Votre credit arrive a expiration le " + endDateForSMS + ". Rachetez du credit upOwa pour ne pas rester dans le noir.";
                        finalSMSstatut = sendSMS(username, apiKey, recipients, message, shortname);
                        System.out.println("SMS envoye: " + message);
                    } else if (nowCDT == minimForAlert3) {
                        String numPhoneForAlert = creditsFacade.findCreditByPAYGprodID(idPAYGprodObject).getIdclient().getPreferredphone();
                        System.out.println("Date de coupure dans " + minimForAlert3 + " Jours proche. Envoie de SMS Alert...");
                        //Debut Operation de date de fin forfait
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
                        String sDate = dateFormat.format(new Date());
                        dateNow = dateFormat.parse(sDate);//Conversion de string en date
                        GregorianCalendar calendar = new java.util.GregorianCalendar();
                        calendar.setTime(dateNow);
                        calendar.add(Calendar.DATE, minimForAlert3);
                        estimEndDate = calendar.getTime();//Conversion de calendar en date
                        System.out.println("Estim EndDate for Alert:" + estimEndDate);
                        endDateForSMS = dateFormatForSMS.format(estimEndDate);
                        System.out.println("End date for SMS: " + endDateForSMS);
                        //Fin Operation de date de fin forfait
                        recipients = codePays + numPhoneForAlert;
                        message = "upOwa alerte:Votre credit arrive a expiration le " + endDateForSMS + ". Rachetez du credit upOwa pour ne pas rester dans le noir.";
                        finalSMSstatut = sendSMS(username, apiKey, recipients, message, shortname);
                        System.out.println("SMS envoye: " + message);
                    } else if (nowCDT == minimForAlert1) {
                        String numPhoneForAlert = creditsFacade.findCreditByPAYGprodID(idPAYGprodObject).getIdclient().getPreferredphone();
                        System.out.println("Date de coupure dans " + minimForAlert1 + " Jours proche. Envoie de SMS Alert...");
                        //Debut Operation de date de fin forfait
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
                        String sDate = dateFormat.format(new Date());
                        dateNow = dateFormat.parse(sDate);//Conversion de string en date
                        GregorianCalendar calendar = new java.util.GregorianCalendar();
                        calendar.setTime(dateNow);
                        calendar.add(Calendar.DATE, minimForAlert1);
                        estimEndDate = calendar.getTime();//Conversion de calendar en date
                        System.out.println("Estim EndDate for Alert:" + estimEndDate);
                        endDateForSMS = dateFormatForSMS.format(estimEndDate);
                        System.out.println("End date for SMS: " + endDateForSMS);
                        //Fin Operation de date de fin forfait
                        recipients = codePays + numPhoneForAlert;
                        message = "upOwa alerte:Votre credit arrive a expiration le " + endDateForSMS + ". Rachetez du credit upOwa pour ne pas rester dans le noir.";
                        finalSMSstatut = sendSMS(username, apiKey, recipients, message, shortname);
                        System.out.println("SMS envoye: " + message);
                    } else {
                        System.out.println("Systeme hors zone de relance SMS. Passez");
                    }
                } else {
                    System.out.println("Systeme non installé. Passez...");
                }
                
            }
            System.out.println("FIN AUTO SMS");
            System.out.println("-----------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //FIN METHODE D'ALERT SMS

    //Getter and Setter
    public String getStatutTransact() {
        return statutTransact;
    }
    
    public void setStatutTransact(String statutTransact) {
        this.statutTransact = statutTransact;
    }
    
    public String getIdOperat() {
        return idOperat;
    }
    
    public void setIdOperat(String idOperat) {
        this.idOperat = idOperat;
    }
    
    public String getIdTrans() {
        return idTrans;
    }
    
    public void setIdTrans(String idTrans) {
        this.idTrans = idTrans;
    }
    
    public String getRefDist() {
        return refDist;
    }
    
    public void setRefDist(String refDist) {
        this.refDist = refDist;
    }
    
    public String getNumBox() {
        return numBox;
    }
    
    public void setNumBox(String numBox) {
        this.numBox = numBox;
    }
    
    public String getNumPhone() {
        return numPhone;
    }
    
    public void setNumPhone(String numPhone) {
        this.numPhone = numPhone;
    }
    
    public String getDateTrans() {
        return dateTrans;
    }
    
    public void setDateTrans(String dateTrans) {
        this.dateTrans = dateTrans;
    }
    
    public String getRefOffre() {
        return refOffre;
    }
    
    public void setRefOffre(String refOffre) {
        this.refOffre = refOffre;
    }
    
    public String getNbreUnite() {
        return nbreUnite;
    }
    
    public void setNbreUnite(String nbreUnite) {
        this.nbreUnite = nbreUnite;
    }
    
    public String getPrixUnite() {
        return prixUnite;
    }
    
    public void setPrixUnite(String prixUnite) {
        this.prixUnite = prixUnite;
    }
    
    public String getIdNewTransactMoney() {
        return idNewTransactMoney;
    }
    
    public void setIdNewTransactMoney(String idNewTransactMoney) {
        this.idNewTransactMoney = idNewTransactMoney;
    }
    
    public int getReqCount() {
        return reqCount;
    }
    
    public void setReqCount(int reqCount) {
        this.reqCount = reqCount;
    }
    
    public int getNbreJours() {
        return nbreJours;
    }
    
    public void setNbreJours(int nbreJours) {
        this.nbreJours = nbreJours;
    }
    
    public int getNumPCBCode() {
        return numPCBCode;
    }
    
    public void setNumPCBCode(int numPCBCode) {
        this.numPCBCode = numPCBCode;
    }
    
    public int getKey1Code() {
        return key1Code;
    }
    
    public void setKey1Code(int key1Code) {
        this.key1Code = key1Code;
    }
    
    public int getKey2Code() {
        return key2Code;
    }
    
    public void setKey2Code(int key2Code) {
        this.key2Code = key2Code;
    }
    
    public int getKey3Code() {
        return key3Code;
    }
    
    public void setKey3Code(int key3Code) {
        this.key3Code = key3Code;
    }
    
    public int getValeurCode() {
        return valeurCode;
    }
    
    public void setValeurCode(int valeurCode) {
        this.valeurCode = valeurCode;
    }
    
    public int getNumBoxCode() {
        return numBoxCode;
    }
    
    public void setNumBoxCode(int numBoxCode) {
        this.numBoxCode = numBoxCode;
    }
    
    public long getIdBoxCode() {
        return idBoxCode;
    }
    
    public void setIdBoxCode(long idBoxCode) {
        this.idBoxCode = idBoxCode;
    }
    
    public long getIdClientCode() {
        return idClientCode;
    }
    
    public void setIdClientCode(long idClientCode) {
        this.idClientCode = idClientCode;
    }
    
    public long getIdOrganizationCode() {
        return idOrganizationCode;
    }
    
    public void setIdOrganizationCode(long idOrganizationCode) {
        this.idOrganizationCode = idOrganizationCode;
    }
    
    public long getIdDistributeurCode() {
        return idDistributeurCode;
    }
    
    public void setIdDistributeurCode(long idDistributeurCode) {
        this.idDistributeurCode = idDistributeurCode;
    }
    
    public BigInteger getValeurCodeBig() {
        return valeurCodeBig;
    }
    
    public void setValeurCodeBig(BigInteger valeurCodeBig) {
        this.valeurCodeBig = valeurCodeBig;
    }
    
    public BigInteger getValeurTimeCodeBig() {
        return valeurTimeCodeBig;
    }
    
    public void setValeurTimeCodeBig(BigInteger valeurTimeCodeBig) {
        this.valeurTimeCodeBig = valeurTimeCodeBig;
    }
    
    public BigInteger getLastValueTime() {
        return lastValueTime;
    }
    
    public void setLastValueTime(BigInteger lastValueTime) {
        this.lastValueTime = lastValueTime;
    }
    
    public BigInteger getNewLeftTimePaid() {
        return newLeftTimePaid;
    }
    
    public void setNewLeftTimePaid(BigInteger newLeftTimePaid) {
        this.newLeftTimePaid = newLeftTimePaid;
    }
    
    public BigInteger getNewTotalTimePaid() {
        return newTotalTimePaid;
    }
    
    public void setNewTotalTimePaid(BigInteger newTotalTimePaid) {
        this.newTotalTimePaid = newTotalTimePaid;
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
    
    public String getStatutCodeGeneration() {
        return statutCodeGeneration;
    }
    
    public void setStatutCodeGeneration(String statutCodeGeneration) {
        this.statutCodeGeneration = statutCodeGeneration;
    }
    
    public String getStatutCode() {
        return statutCode;
    }
    
    public void setStatutCode(String statutCode) {
        this.statutCode = statutCode;
    }
    
    public int getReqUpdateKeys() {
        return reqUpdateKeys;
    }
    
    public void setReqUpdateKeys(int reqUpdateKeys) {
        this.reqUpdateKeys = reqUpdateKeys;
    }
    
    public int getNextKey3() {
        return nextKey3;
    }
    
    public void setNextKey3(int nextKey3) {
        this.nextKey3 = nextKey3;
    }
    
    public int getLastTotalTime() {
        return lastTotalTime;
    }
    
    public void setLastTotalTime(int lastTotalTime) {
        this.lastTotalTime = lastTotalTime;
    }
    
    public double getPrix30Jours() {
        return prix30Jours;
    }
    
    public void setPrix30Jours(double prix30Jours) {
        this.prix30Jours = prix30Jours;
    }
    
    public double getPrix07Jours() {
        return prix07Jours;
    }
    
    public void setPrix07Jours(double prix07Jours) {
        this.prix07Jours = prix07Jours;
    }
    
    public double getPrixCadeaux() {
        return prixCadeaux;
    }
    
    public void setPrixCadeaux(double prixCadeaux) {
        this.prixCadeaux = prixCadeaux;
    }
    
    public double getPrixCode() {
        return prixCode;
    }
    
    public void setPrixCode(double prixCode) {
        this.prixCode = prixCode;
    }
    
    public long getDiffDate() {
        return diffDate;
    }
    
    public void setDiffDate(long diffDate) {
        this.diffDate = diffDate;
    }
    
    public long getDiffDateConvert() {
        return diffDateConvert;
    }
    
    public void setDiffDateConvert(long diffDateConvert) {
        this.diffDateConvert = diffDateConvert;
    }
    
    public long getEstimLeftTime() {
        return estimLeftTime;
    }
    
    public void setEstimLeftTime(long estimLeftTime) {
        this.estimLeftTime = estimLeftTime;
    }
    
    public int getNewLeftTimePaid0() {
        return newLeftTimePaid0;
    }
    
    public void setNewLeftTimePaid0(int newLeftTimePaid0) {
        this.newLeftTimePaid0 = newLeftTimePaid0;
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
    
    public int getC_default() {
        return c_default;
    }
    
    public void setC_default(int c_default) {
        this.c_default = c_default;
    }
    
    public String getSnifferTransact() {
        return snifferTransact;
    }
    
    public void setSnifferTransact(String snifferTransact) {
        this.snifferTransact = snifferTransact;
    }
    
    public boolean isValideTransact() {
        return valideTransact;
    }
    
    public void setValideTransact(boolean valideTransact) {
        this.valideTransact = valideTransact;
    }
    
    public String getStatutSMSsending() {
        return statutSMSsending;
    }
    
    public void setStatutSMSsending(String statutSMSsending) {
        this.statutSMSsending = statutSMSsending;
    }
    
    public String getStatutSMSCode() {
        return statutSMSCode;
    }
    
    public void setStatutSMSCode(String statutSMSCode) {
        this.statutSMSCode = statutSMSCode;
    }
    
    public Code getCode() {
        return code;
    }
    
    public void setCode(Code code) {
        this.code = code;
    }
    
    public Date getDateToday() {
        return dateToday;
    }
    
    public void setDateToday(Date dateToday) {
        this.dateToday = dateToday;
    }
    
    public Date getBoxEstimEndDate() {
        return boxEstimEndDate;
    }
    
    public void setBoxEstimEndDate(Date boxEstimEndDate) {
        this.boxEstimEndDate = boxEstimEndDate;
    }
    
    public long getCONST_DURATION_OF_DAY() {
        return CONST_DURATION_OF_DAY;
    }
    
    public void setCONST_DURATION_OF_DAY(long CONST_DURATION_OF_DAY) {
        this.CONST_DURATION_OF_DAY = CONST_DURATION_OF_DAY;
    }
    
    public long getJourRestant() {
        return jourRestant;
    }
    
    public void setJourRestant(long jourRestant) {
        this.jourRestant = jourRestant;
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
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getMyPhoneNumber() {
        return myPhoneNumber;
    }
    
    public void setMyPhoneNumber(String myPhoneNumber) {
        this.myPhoneNumber = myPhoneNumber;
    }
    
    public String getSmsAchatCode() {
        return smsAchatCode;
    }
    
    public void setSmsAchatCode(String smsAchatCode) {
        this.smsAchatCode = smsAchatCode;
    }
    
    public String getSmsRappelFin() {
        return smsRappelFin;
    }
    
    public void setSmsRappelFin(String smsRappelFin) {
        this.smsRappelFin = smsRappelFin;
    }
    
    public String getSmsVerifiCredit() {
        return smsVerifiCredit;
    }
    
    public void setSmsVerifiCredit(String smsVerifiCredit) {
        this.smsVerifiCredit = smsVerifiCredit;
    }
    
    public String getDateExpireCode() {
        return dateExpireCode;
    }
    
    public void setDateExpireCode(String dateExpireCode) {
        this.dateExpireCode = dateExpireCode;
    }
    
    public String getMtR() {
        return MtR;
    }
    
    public void setMtR(String MtR) {
        this.MtR = MtR;
    }
    
    public boolean isSendAlert() {
        return sendAlert;
    }
    
    public void setSendAlert(boolean sendAlert) {
        this.sendAlert = sendAlert;
    }
    
    public int getMinimForAlert1() {
        return minimForAlert1;
    }
    
    public void setMinimForAlert1(int minimForAlert1) {
        this.minimForAlert1 = minimForAlert1;
    }
    
    public int getMinimForAlert2() {
        return minimForAlert2;
    }
    
    public void setMinimForAlert2(int minimForAlert2) {
        this.minimForAlert2 = minimForAlert2;
    }
    
    public String getStatusSMSsending() {
        return statusSMSsending;
    }
    
    public void setStatusSMSsending(String statusSMSsending) {
        this.statusSMSsending = statusSMSsending;
    }
    
    public String getNotification() {
        return notification;
    }
    
    public void setNotification(String notification) {
        this.notification = notification;
    }
    
    public boolean isShowStatusSMSsending() {
        return showStatusSMSsending;
    }
    
    public void setShowStatusSMSsending(boolean showStatusSMSsending) {
        this.showStatusSMSsending = showStatusSMSsending;
    }
    
    public Mmtransaction getMmtransaction() {
        return mmtransaction;
    }
    
    public void setMmtransaction(Mmtransaction mmtransaction) {
        this.mmtransaction = mmtransaction;
    }
    
    public boolean isShowMessage() {
        return showMessage;
    }
    
    public void setShowMessage(boolean showMessage) {
        this.showMessage = showMessage;
    }
    
    public MmtransactionFacadeLocal getMmtransactionFacade() {
        return mmtransactionFacade;
    }
    
    public void setMmtransactionFacade(MmtransactionFacadeLocal mmtransactionFacade) {
        this.mmtransactionFacade = mmtransactionFacade;
    }
    
    public CodeFacadeLocal getCodeFacade() {
        return codeFacade;
    }
    
    public void setCodeFacade(CodeFacadeLocal codeFacade) {
        this.codeFacade = codeFacade;
    }
    
    public BoxFacadeLocal getBoxFacade() {
        return boxFacade;
    }
    
    public void setBoxFacade(BoxFacadeLocal boxFacade) {
        this.boxFacade = boxFacade;
    }
    
    public static DateFormat getDateFormat() {
        return dateFormat;
    }
    
    public static void setDateFormat(DateFormat dateFormat) {
        OrtherController.dateFormat = dateFormat;
    }
    
    public List<Code> getListCode() {
        return listCode;
    }
    
    public void setListCode(List<Code> listCode) {
        this.listCode = listCode;
    }
    
    public List<Box> getListBoxsLibres() {
        return listBoxsLibres;
    }
    
    public void setListBoxsLibres(List<Box> listBoxsLibres) {
        this.listBoxsLibres = listBoxsLibres;
    }
    
    public OrganizationFacadeLocal getOrganizationFacade() {
        return organizationFacade;
    }
    
    public void setOrganizationFacade(OrganizationFacadeLocal organizationFacade) {
        this.organizationFacade = organizationFacade;
    }
    
    public List<Organization> getListOrganization() {
        return listOrganization;
    }
    
    public void setListOrganization(List<Organization> listOrganization) {
        this.listOrganization = listOrganization;
    }
    
    public List<Box> getListBoxInstalled() {
        return listBoxInstalled;
    }
    
    public void setListBoxInstalled(List<Box> listBoxInstalled) {
        this.listBoxInstalled = listBoxInstalled;
    }
    
    public DistributorFacadeLocal getDistributorFacade() {
        return distributorFacade;
    }
    
    public void setDistributorFacade(DistributorFacadeLocal distributorFacade) {
        this.distributorFacade = distributorFacade;
    }
    
    public List<Distributor> getListDistributor() {
        return listDistributor;
    }
    
    public void setListDistributor(List<Distributor> listDistributor) {
        this.listDistributor = listDistributor;
    }
    
    public Client getClient() {
        return client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public ClientFacadeLocal getClientFacade() {
        return clientFacade;
    }
    
    public void setClientFacade(ClientFacadeLocal clientFacade) {
        this.clientFacade = clientFacade;
    }
    
    public List<Client> getListClient() {
        return listClient;
    }
    
    public void setListClient(List<Client> listClient) {
        this.listClient = listClient;
    }
    
    public Client getIdclientUSSD() {
        return idclientUSSD;
    }
    
    public void setIdclientUSSD(Client idclientUSSD) {
        this.idclientUSSD = idclientUSSD;
    }
    
    public Box getBox() {
        return box;
    }
    
    public void setBox(Box box) {
        this.box = box;
    }
    
    public Box getIdboxeUSSD() {
        return idboxeUSSD;
    }
    
    public void setIdboxeUSSD(Box idboxeUSSD) {
        this.idboxeUSSD = idboxeUSSD;
    }
    
    public Distributor getDistributor() {
        return distributor;
    }
    
    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }
    
    public Distributor getIddistributorUSSD() {
        return iddistributorUSSD;
    }
    
    public void setIddistributorUSSD(Distributor iddistributorUSSD) {
        this.iddistributorUSSD = iddistributorUSSD;
    }
    
    public Organization getOrganization() {
        return organization;
    }
    
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
    
    public Organization getIdorganisationUSSD() {
        return idorganisationUSSD;
    }
    
    public void setIdorganisationUSSD(Organization idorganisationUSSD) {
        this.idorganisationUSSD = idorganisationUSSD;
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
    
    public String getIdPAYGprod() {
        return idPAYGprod;
    }
    
    public void setIdPAYGprod(String idPAYGprod) {
        this.idPAYGprod = idPAYGprod;
    }
    
    public CreditsFacadeLocal getCreditsFacade() {
        return creditsFacade;
    }
    
    public void setCreditsFacade(CreditsFacadeLocal creditsFacade) {
        this.creditsFacade = creditsFacade;
    }
    
    public PaygotpgeneratorFacadeLocal getPaygotpgeneratorFacade() {
        return paygotpgeneratorFacade;
    }
    
    public void setPaygotpgeneratorFacade(PaygotpgeneratorFacadeLocal paygotpgeneratorFacade) {
        this.paygotpgeneratorFacade = paygotpgeneratorFacade;
    }
    
    public ProduitsFacadeLocal getProduitsFacade() {
        return produitsFacade;
    }
    
    public void setProduitsFacade(ProduitsFacadeLocal produitsFacade) {
        this.produitsFacade = produitsFacade;
    }
    
    public TermesFacadeLocal getTermesFacade() {
        return termesFacade;
    }
    
    public void setTermesFacade(TermesFacadeLocal termesFacade) {
        this.termesFacade = termesFacade;
    }
    
    public List<Credits> getListCredits() {
        return listCredits;
    }
    
    public void setListCredits(List<Credits> listCredits) {
        this.listCredits = listCredits;
    }
    
    public List<Paygotpgenerator> getListPaygotpgenerator() {
        return listPaygotpgenerator;
    }
    
    public void setListPaygotpgenerator(List<Paygotpgenerator> listPaygotpgenerator) {
        this.listPaygotpgenerator = listPaygotpgenerator;
    }
    
    public List<Produits> getListProduits() {
        return listProduits;
    }
    
    public void setListProduits(List<Produits> listProduits) {
        this.listProduits = listProduits;
    }
    
    public List<Termes> getListTermes() {
        return listTermes;
    }
    
    public void setListTermes(List<Termes> listTermes) {
        this.listTermes = listTermes;
    }
    
    public int getPrix1jour() {
        return prix1jour;
    }
    
    public void setPrix1jour(int prix1jour) {
        this.prix1jour = prix1jour;
    }
    
    public String getCodeGeneratedByOMNI() {
        return codeGeneratedByOMNI;
    }
    
    public void setCodeGeneratedByOMNI(String codeGeneratedByOMNI) {
        this.codeGeneratedByOMNI = codeGeneratedByOMNI;
    }
    
    public Paygcodes getPaygcodes() {
        return paygcodes;
    }
    
    public void setPaygcodes(Paygcodes paygcodes) {
        this.paygcodes = paygcodes;
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
    
    public int getReqUpdateCurrentC() {
        return reqUpdateCurrentC;
    }
    
    public void setReqUpdateCurrentC(int reqUpdateCurrentC) {
        this.reqUpdateCurrentC = reqUpdateCurrentC;
    }
    
    public int getReqUpdateLastCode() {
        return reqUpdateLastCode;
    }
    
    public void setReqUpdateLastCode(int reqUpdateLastCode) {
        this.reqUpdateLastCode = reqUpdateLastCode;
    }
    
    public Credits getCredits() {
        return credits;
    }
    
    public void setCredits(Credits credits) {
        this.credits = credits;
    }
    
    public Termes getTermes() {
        return termes;
    }
    
    public void setTermes(Termes termes) {
        this.termes = termes;
    }
    
    public Paygotpgenerator getPaygotpgenerator() {
        return paygotpgenerator;
    }
    
    public void setPaygotpgenerator(Paygotpgenerator paygotpgenerator) {
        this.paygotpgenerator = paygotpgenerator;
    }
    
    public Produits getProduits() {
        return produits;
    }
    
    public void setProduits(Produits produits) {
        this.produits = produits;
    }
    
    public String getFinalSMSstatut() {
        return finalSMSstatut;
    }
    
    public void setFinalSMSstatut(String finalSMSstatut) {
        this.finalSMSstatut = finalSMSstatut;
    }
    
    public String getCreditSniffer() {
        return creditSniffer;
    }
    
    public void setCreditSniffer(String creditSniffer) {
        this.creditSniffer = creditSniffer;
    }
    
    public String getStrprix1jour() {
        return strprix1jour;
    }
    
    public void setStrprix1jour(String strprix1jour) {
        this.strprix1jour = strprix1jour;
    }
    
    public List<Client> getListClientPotentiel() {
        return listClientPotentiel;
    }
    
    public void setListClientPotentiel(List<Client> listClientPotentiel) {
        this.listClientPotentiel = listClientPotentiel;
    }
    
    public List<Produits> getListProduitSystem() {
        return listProduitSystem;
    }
    
    public void setListProduitSystem(List<Produits> listProduitSystem) {
        this.listProduitSystem = listProduitSystem;
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
    
    public String getEndDateForSMS() {
        return endDateForSMS;
    }
    
    public void setEndDateForSMS(String endDateForSMS) {
        this.endDateForSMS = endDateForSMS;
    }
    
    public String getShortname() {
        return shortname;
    }
    
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
    
    public int getAcompteRestantPourInstall() {
        return acompteRestantPourInstall;
    }
    
    public void setAcompteRestantPourInstall(int acompteRestantPourInstall) {
        this.acompteRestantPourInstall = acompteRestantPourInstall;
    }
    //Getter and setter for autoTask

    public static TimerTask getOurTask() {
        return ourTask;
    }
    
    public static void setOurTask(TimerTask ourTask) {
        OrtherController.ourTask = ourTask;
    }
    
    public int getLenghtCDT() {
        return lenghtCDT;
    }
    
    public void setLenghtCDT(int lenghtCDT) {
        this.lenghtCDT = lenghtCDT;
    }
    
    public String getFiltres() {
        return filtres;
    }
    
    public void setFiltres(String filtres) {
        this.filtres = filtres;
    }
    
    public String getStatusTask() {
        return statusTask;
    }
    
    public void setStatusTask(String statusTask) {
        this.statusTask = statusTask;
    }
    
    public int getWaitBeforeStarTask() {
        return waitBeforeStarTask;
    }
    
    public void setWaitBeforeStarTask(int waitBeforeStarTask) {
        this.waitBeforeStarTask = waitBeforeStarTask;
    }
    
    public int getIntervalBetweenTasks() {
        return intervalBetweenTasks;
    }
    
    public void setIntervalBetweenTasks(int intervalBetweenTasks) {
        this.intervalBetweenTasks = intervalBetweenTasks;
    }
    
    public int getOtpInterval() {
        return otpInterval;
    }
    
    public void setOtpInterval(int otpInterval) {
        this.otpInterval = otpInterval;
    }
    
    public Timer getTimer2() {
        return timer2;
    }
    
    public void setTimer2(Timer timer2) {
        this.timer2 = timer2;
    }
    
    public Date getDateNow() {
        return dateNow;
    }
    
    public void setDateNow(Date dateNow) {
        this.dateNow = dateNow;
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
    
    public static DateFormat getDateFormatForSMS() {
        return dateFormatForSMS;
    }
    
    public static void setDateFormatForSMS(DateFormat dateFormatForSMS) {
        OrtherController.dateFormatForSMS = dateFormatForSMS;
    }
    
    public int getMinimForAlert3() {
        return minimForAlert3;
    }
    
    public void setMinimForAlert3(int minimForAlert3) {
        this.minimForAlert3 = minimForAlert3;
    }
    
    public int getMinimForAlert7() {
        return minimForAlert7;
    }
    
    public void setMinimForAlert7(int minimForAlert7) {
        this.minimForAlert7 = minimForAlert7;
    }
    
    public int getWaitBeforeStarTaskSMS() {
        return waitBeforeStarTaskSMS;
    }
    
    public void setWaitBeforeStarTaskSMS(int waitBeforeStarTaskSMS) {
        this.waitBeforeStarTaskSMS = waitBeforeStarTaskSMS;
    }
    
    public int getIntervalBetweenTasksSMS() {
        return intervalBetweenTasksSMS;
    }
    
    public void setIntervalBetweenTasksSMS(int intervalBetweenTasksSMS) {
        this.intervalBetweenTasksSMS = intervalBetweenTasksSMS;
    }
    
    public Timer getTimer3() {
        return timer3;
    }
    
    public void setTimer3(Timer timer3) {
        this.timer3 = timer3;
    }
    
    public int getMaxHCJ() {
        return maxHCJ;
    }
    
    public void setMaxHCJ(int maxHCJ) {
        this.maxHCJ = maxHCJ;
    }
    
    public int getMontantTotalDuCredit() {
        return montantTotalDuCredit;
    }
    
    public void setMontantTotalDuCredit(int montantTotalDuCredit) {
        this.montantTotalDuCredit = montantTotalDuCredit;
    }
    
    public static DateFormat getDateFormatSMS() {
        return dateFormatSMS;
    }
    
    public static void setDateFormatSMS(DateFormat dateFormatSMS) {
        OrtherController.dateFormatSMS = dateFormatSMS;
    }
    
    public Paygotpgenerator2FacadeLocal getPaygotpgenerator2Facade() {
        return paygotpgenerator2Facade;
    }
    
    public void setPaygotpgenerator2Facade(Paygotpgenerator2FacadeLocal paygotpgenerator2Facade) {
        this.paygotpgenerator2Facade = paygotpgenerator2Facade;
    }

    public List<Mmtransaction> getListMmtransaction() {
        return listMmtransaction;
    }

    public void setListMmtransaction(List<Mmtransaction> listMmtransaction) {
        this.listMmtransaction = listMmtransaction;
    }
    
    public List<Paygotpgenerator2> getListPaygotpgenerator2() {
        return listPaygotpgenerator2;
    }
    
    public void setListPaygotpgenerator2(List<Paygotpgenerator2> listPaygotpgenerator2) {
        this.listPaygotpgenerator2 = listPaygotpgenerator2;
    }
    
    public Paygotpgenerator2 getPaygotpgenerator2() {
        return paygotpgenerator2;
    }
    
    public void setPaygotpgenerator2(Paygotpgenerator2 paygotpgenerator2) {
        this.paygotpgenerator2 = paygotpgenerator2;
    }
    
    public int getLenghtPAYGfactory() {
        return lenghtPAYGfactory;
    }
    
    public void setLenghtPAYGfactory(int lenghtPAYGfactory) {
        this.lenghtPAYGfactory = lenghtPAYGfactory;
    }
    
    public String getBoxinstallStatusOK() {
        return boxinstallStatusOK;
    }
    
    public void setBoxinstallStatusOK(String boxinstallStatusOK) {
        this.boxinstallStatusOK = boxinstallStatusOK;
    }
    
    public Random getMyRadom() {
        return myRadom;
    }
    
    public void setMyRadom(Random myRadom) {
        this.myRadom = myRadom;
    }
    
    public String getAlphabet() {
        return alphabet;
    }
    
    public void setAlphabet(String alphabet) {
        this.alphabet = alphabet;
    }
}
