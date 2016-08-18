
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Utils;
import entities.Box;
import entities.Code;
import entities.Client;
import entities.Organization;
import entities.Distributor;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessions.CodeFacadeLocal;
import sessions.BoxFacadeLocal;
import sessions.ClientFacadeLocal;
import sessions.DistributorFacadeLocal;
import sessions.OrganizationFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "codeController")
@SessionScoped
public class CodeController implements Serializable {

    @Inject
    private CodeFacadeLocal codeFacade;
    private BoxFacadeLocal boxFacade;
    private List<Code> listCode = new ArrayList<Code>();
    private List<Box> listBoxsLibres = new ArrayList<Box>();
    private Code code = new Code();
    private Client client = new Client();
    private Client idclientUSSD;
    private Box box = new Box();
    private Box idboxeUSSD;
    private Distributor distributor = new Distributor();
    private Distributor iddistributorUSSD;
    private Organization organization = new Organization();
    private Organization idorganisationUSSD;
    //Debut ci dessous les variables FurtherMarket
    static int AccountID = 605870;
    static int ProductID = 373; // Forcement pas necessaire si on ne dispose que d'un produit.
    static String AccountPwd = "s";
    static int NonPrecise = 0;
    private String UssdSaleRequest = "";
    private String encodeType = "UTF-8";
    private String urlFM = "http://api.furthermarket.com/FM/MARKET/Sales?MyAccountID=" + AccountID + "&Password=" + AccountPwd + "&StartIDDate=" + NonPrecise + "&StopIDDate=" + NonPrecise + "&StartMarketIDDate=" + NonPrecise + "&StopMarketIDDate=" + NonPrecise + "&ProductID=" + ProductID;
    @Inject
    private ClientFacadeLocal clientFacade;
    private List<Client> listClient = new ArrayList<Client>();
    @Inject
    private DistributorFacadeLocal distributorFacade;
    private List<Distributor> listDistributor = new ArrayList<Distributor>();
    @Inject
    private OrganizationFacadeLocal organizationFacade;
    private List<Organization> listOrganization = new ArrayList<Organization>();
    private List<Box> listBoxInstalled = new ArrayList<Box>();
    private int reqUpdateKeys = 0;
    private int nbreJours = 40;
    private int numBoxCode = 0;
    private int numPCBCode = 0;
    private int key1Code = 0;
    private int key2Code = 0;
    private int key3Code = 0;
    private int lastTotalTime = 0;
    private int nextKey3 = 0;
    private int valeurCode = 0;
    private long diffDate = 0;
    private long diffDateConvert = 0;
    private long CONST_DURATION_OF_DAY = 1000l * 60 * 60 * 24;
    private long estimLeftTime = 0;
    private BigInteger valeurCodeBig;
    private BigInteger valeurTimeCodeBig;
    private BigInteger lastValueTime;
    private BigInteger newLeftTimePaid;
    private BigInteger newTotalTimePaid;
    private int newLeftTimePaid0 = 0;
    private double prix30Jours = 5000;
    private double prix07Jours = 1500;
    private double prixCadeaux = 0;
    private double prixCode = 0;
    private long idBoxCode = 1;
    private long idClientCode = 1;
    private long idOrganizationCode = 1;
    private long idDistributeurCode = 1;
    private long numBoxUSSD = 1;
    private long clientUniqNumberUSSD = 1;
    private long idorganizationUSSD = 1;
    private long distribuniqnumberUSSD = 1;
    private String message = "";
    private boolean showMessage = false;
    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Date dateTransact;
    private Date dateTransact0;
    private Date estimEndDate;
    private Date previousEstimEndDate;
    private Utils utils = new Utils();

    /**
     * Creates a new instance of CodeController
     */
    public CodeController() {
    }

    public String code() {
        listCode.clear();
        listCode.addAll(codeFacade.findAll());
        chargeListOrganization();
        chargeListClient();
        chargeListDistributor();
        chargeListBoxsInstalled();
        return "codes.xhtml?faces-redirect=true";
    }

    public void prepareCreate() {
        code = new Code();
    }

    public void chargeListClient() {
        listClient.clear();
        listClient.addAll(clientFacade.findAll());
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

    public void creationCode() {
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
    
    

    public void prepareEdit() {
        code = new Code();
    }

    public void initialisationCode() throws ParseException {
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh':'mm ");//Definition du format de date
        String sDate = dateFormat.format(new Date());
        code.setDategene(dateFormat.parse(sDate));
        code.setTimegene(sDate);
        dateTransact = dateFormat.parse(sDate);//Conversion de string en date
        GregorianCalendar calendar = new java.util.GregorianCalendar();
        calendar.setTime(dateTransact);
        calendar.add(Calendar.DATE, nbreJours);
        estimEndDate = calendar.getTime();//Conversion de calendar en date
    }

    public void calculTotalTimePaid() {
        lastTotalTime = codeFacade.findLastTotalTimePaid(numBoxCode);
        lastTotalTime = lastTotalTime + (int)estimLeftTime;
    }

    public void calculPriceCode() {
        if (nbreJours == 30) {
            code.setPrice(prix30Jours);
        } else if (nbreJours == 7) {
            code.setPrice(prix07Jours);
        } else {
            code.setPrice(prixCadeaux);
        }
    }
    
    public int calculNbreJoursCodeUssd(int prixUniteUssd) {
        int prix1Jour = 100;
        int nbreJoursBuy = prixUniteUssd / prix1Jour;
        return nbreJoursBuy;
    }

    public void upNextKey() {
        nextKey3 = key3Code + 1;
        lastValueTime = new BigInteger(Integer.toString(nbreJours));
        reqUpdateKeys = codeFacade.findToUpdateKeys(key3Code, nextKey3, idBoxCode, dateTransact, estimEndDate, lastValueTime, newLeftTimePaid, newTotalTimePaid);
    }

    public String saveCodeManual() {
        try {
            //UssdSaleRequest = codeFacade.getContents(urlFM, encodeType);
            //System.out.println("Liste des ventes:" +content);
            //System.out.println("Liste des ventes:" + UssdSaleRequest);
            //decoupe1(UssdSaleRequest);
            DateFormat dateFormatLastEnd = new SimpleDateFormat("yyyy-MM-dd hh':'mm ");//Definition du format de date
            String sDateLastEnd = dateFormatLastEnd.format(codeFacade.findLastEndDate(numBoxCode));
            String sDateNow = dateFormatLastEnd.format(new Date());
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
            creationCode();
            initialisationCode();
            code.setPrice(prixCadeaux);
            estimLeftTime = diffDate + nbreJours;
            System.out.println("Estim LeftTimePaid:" + estimLeftTime);
            newLeftTimePaid0 = (int) estimLeftTime;
            newLeftTimePaid = new BigInteger(Integer.toString(newLeftTimePaid0));// Sera mise à jour dans upNextKey()
            calculTotalTimePaid();
            newTotalTimePaid = new BigInteger(Integer.toString(lastTotalTime));
            codeFacade.create(code);
            upNextKey();
            System.out.println("Nbre keys box updated:" + reqUpdateKeys);
            showMessage = true;
            message = "Nouveau code généré avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de génération de code";
            e.printStackTrace();
        }
        return code();
    }

    public String saveCodeAutomatic() {
        try {
            creationCode();
            initialisationCode();
            calculPriceCode();
            codeFacade.create(code);
            upNextKey();
            System.out.println("Nbre keys box updated:" + reqUpdateKeys);
            showMessage = true;
            message = "Nouveau code généré avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de génération de code";
            e.printStackTrace();
        }
        return code();
    }
       

    public String editCode() {
        try {
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return code();
    }

    public String deleteCode() {
        try {
            codeFacade.remove(code);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la suppression";
            e.printStackTrace();
        }
        return code();
    }

    public static void decoupe1(String UssdSaleRequest) {
        String[] splitArray = null; //tableau de chaînes
        //la chaîne à traiter
        String allSales = UssdSaleRequest;

        // On découpe la chaîne "allSales" à traiter et on récupère le résultat dans le tableau "splitArray"
        splitArray = allSales.split("&");

        for (int i = 0; i < splitArray.length; i++) {
            // On affiche chaque élément du tableau
            System.out.println("élement n° " + i + "=[" + splitArray[i] + "]");
        }
    }

    public String imprimer() {

        try {
            utils.print("Reports/ListeCode.jasper");
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            return ext.getRequestPathInfo() + "?faces-redirect=true";
        }
    }

    public List<Code> getListCode() {
        return this.listCode;
    }

    public void setListCode(List<Code> listcode) {
        this.listCode = listcode;
    }

    public List<Box> getListBoxsLibres() {
        return listBoxsLibres;
    }

    public void setListBoxsLibres(List<Box> listBoxsLibres) {
        this.listBoxsLibres = listBoxsLibres;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isShowMessage() {
        return showMessage;
    }

    public void setShowMessage(boolean showMessage) {
        this.showMessage = showMessage;
    }

    public List<Client> getListClient() {
        return listClient;
    }

    public void setListClient(List<Client> listClient) {
        this.listClient = listClient;
    }

    public List<Organization> getListOrganization() {
        return listOrganization;
    }

    public void setListOrganization(List<Organization> listOrganization) {
        this.listOrganization = listOrganization;
    }

    public List<Distributor> getListDistributor() {
        return listDistributor;
    }

    public void setListDistributor(List<Distributor> listDistributor) {
        this.listDistributor = listDistributor;
    }

    public List<Box> getListBoxInstalled() {
        return listBoxInstalled;
    }

    public void setListBoxInstalled(List<Box> listBoxInstalled) {
        this.listBoxInstalled = listBoxInstalled;
    }

    public int getNbreJours() {
        return nbreJours;
    }

    public void setNbreJours(int nbreJours) {
        this.nbreJours = nbreJours;
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

    public int getNextKey3() {
        return nextKey3;
    }

    public void setNextKey3(int nextKey3) {
        this.nextKey3 = nextKey3;
    }

    public int getNumBoxCode() {
        return numBoxCode;
    }

    public void setNumBoxCode(int numBoxCode) {
        this.numBoxCode = numBoxCode;
    }

    public int getNumPCBCode() {
        return numPCBCode;
    }

    public void setNumPCBCode(int numPCBCode) {
        this.numPCBCode = numPCBCode;
    }

    public int getValeurCode() {
        return valeurCode;
    }

    public void setValeurCode(int valeurCode) {
        this.valeurCode = valeurCode;
    }

    public double getPrix30Jours() {
        return prix30Jours;
    }

    public void setPrix30Jours(int prix30Jours) {
        this.prix30Jours = prix30Jours;
    }

    public double getPrix07Jours() {
        return prix07Jours;
    }

    public void setPrix07Jours(int prix07Jours) {
        this.prix07Jours = prix07Jours;
    }

    public double getPrixCadeaux() {
        return prixCadeaux;
    }

    public void setPrixCadeaux(int prixCadeaux) {
        this.prixCadeaux = prixCadeaux;
    }

    public double getPrixCode() {
        return prixCode;
    }

    public void setPrixCode(int prixCode) {
        this.prixCode = prixCode;
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

    public long getNumBoxUSSD() {
        return numBoxUSSD;
    }

    public void setNumBoxUSSD(int numBoxUSSD) {
        this.numBoxUSSD = numBoxUSSD;
    }

    public long getClientUniqNumberUSSD() {
        return clientUniqNumberUSSD;
    }

    public void setClientUniqNumberUSSD(int clientUniqNumberUSSD) {
        this.clientUniqNumberUSSD = clientUniqNumberUSSD;
    }

    public long getIdorganizationUSSD() {
        return idorganizationUSSD;
    }

    public void setIdorganizationUSSD(int idorganizationUSSD) {
        this.idorganizationUSSD = idorganizationUSSD;
    }

    public long getDistribuniqnumberUSSD() {
        return distribuniqnumberUSSD;
    }

    public void setDistribuniqnumberUSSD(int distribuniqnumberUSSD) {
        this.distribuniqnumberUSSD = distribuniqnumberUSSD;
    }

    public Client getIdclientUSSD() {
        return idclientUSSD;
    }

    public void setIdclientUSSD(Client idclientUSSD) {
        this.idclientUSSD = idclientUSSD;
    }

    public Box getIdboxeUSSD() {
        return idboxeUSSD;
    }

    public void setIdboxeUSSD(Box idboxeUSSD) {
        this.idboxeUSSD = idboxeUSSD;
    }

    public Distributor getIddistributorUSSD() {
        return iddistributorUSSD;
    }

    public void setIddistributorUSSD(Distributor iddistributorUSSD) {
        this.iddistributorUSSD = iddistributorUSSD;
    }

    public Organization getIdorganisationUSSD() {
        return idorganisationUSSD;
    }

    public void setIdorganisationUSSD(Organization idorganisationUSSD) {
        this.idorganisationUSSD = idorganisationUSSD;
    }

    public Date getEstimEndDate() {
        return estimEndDate;
    }

    public void setEstimEndDate(Date estimEndDate) {
        this.estimEndDate = estimEndDate;
    }
}
