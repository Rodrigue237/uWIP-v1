
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Utils;
import controllers.SessionController;
import entities.Client;
import entities.Credits;
import entities.Produits;
import entities.Termes;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessions.ClientFacadeLocal;
import sessions.CreditsFacadeLocal;
import sessions.ProduitsFacadeLocal;
import sessions.TermesFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "rtocontractController")
@SessionScoped
public class RtocontractController implements Serializable {

    @Inject
    private CreditsFacadeLocal creditsFacade;
    private List<Credits> listCredits = new ArrayList<Credits>();
    private List<Credits> listAllFreeCredits = new ArrayList<Credits>();
    private Credits credits = new Credits();
    @Inject
    private ClientFacadeLocal clientFacade;
    private List<Client> listClientPotentiel = new ArrayList<Client>();
    private Client client = new Client();
    private Client clientIN = new Client();
    @Inject
    private ProduitsFacadeLocal produitsFacade;
    private List<Produits> listProduitSystem = new ArrayList<Produits>();
    private Produits produits = new Produits();
    @Inject
    private TermesFacadeLocal termesFacade;
    private List<Termes> listTermes = new ArrayList<Termes>();
    private Termes termes = new Termes();
    private int phoneUserSystem = 0;
    private String message = "";
    private boolean showMessage = false;
    // Variable Form IN for update Credits
    private long idclientPotentielIN;
    private int numInstallIN = 0;
    private long idtermesIN;
    private long idproduitsIN;
    private int qteLampeSupCreditIN = 0;
    private int qteRadioSupCreditIN = 0;
    private int qteTorcheSupCreditIN = 0;
    private int qteLampeSupCashIN = 0;
    private int qteRadioSupCashIN = 0;
    private int qteTorcheSupCashIN = 0;
    private int montantTransact;
    private int montantTotalCreditAUTO = 0;
    private int acompteCreditAUTO = 0;
    private int acompteTotalAUTO = 0;
    private int prix1MoisAUTO = 0;
    private int prix1JourAUTO = 0;
    private int montantPayeAUTO = 0;
    private int montantrestantAUTO = 0;
    private int prixSystemeCredit = 0;
    private int prix1LampeSupCredit = 0;
    private int prix1RadioSupSupCredit = 0;
    private int prix1TorcheSupCredit = 0;
    private int prix1LampeSupCash = 0;
    private int prix1RadioSupCash = 0;
    private int prix1TorcheSupCash = 0;
    private int modeleTerme1 = 12;
    private int modeleTerme2 = 24;
    private int modeleTerme3 = 36;
    private int dureeCreditIN = 0;
    private int prixTotalAppSupCredit = 0;
    private int prixTotalAppSupCash = 0;
    private double acomptePourcent;
    private int acompteCredit = 0;
    private int nbreMensualiteTermes = 0;
    private String radio1 = "Radio-OV";
    private String lampe1 = "Lampe-OV";
    private String torche1 = "Torche-OV";
    private String creditSatus0 = "- -";
    private String creditSatus1 = "En attente d'acompte";
    private String creditSatus2 = "En attente d'installation";
    private String creditSatus3 = "Activé";
    private String creditSatus4 = "Desactivé";
    private String creditSatus5 = "Remboursé";
    private int reqUpdateCreditByNewContract = 0;
    private int reqUpdateCredNewContr1 = 0;
    private String nameClientIN = "";
    private String moyenJr1Mois = "30.4375";
    private String userOfSystem = "";
    private String styleMarquer1 = "clientMarq";
    private String styleAtelier = "A1.png";
    private String magDispo = "A1.png";
    private String boxActivated = "A5.png";
    private String defaultStatus = "activ";
    private String filtreTypeSystem = "System";
    private String filtreClientFirstName = "-";
    private String filtreClientLastName = "-";
    private String filtreValidTermes = "- -";
    private int totalInstallBox = 0;
    private long idOfClientIN = 0;
    private int montantTotalPayeNow = 0;
    private Utils utils = new Utils();

    /**
     * Creates a new instance of ContractController
     */
    public RtocontractController() {
    }

    public String rtocontract() {
        listCredits.clear();
        listCredits.addAll(creditsFacade.findAll());
        chargeListClientPotentielle();
        chargeListProduitSystem();
        chargeListTermes();
        chargeListCreditNonAttribue();
        return "rtocontracts.xhtml?faces-redirect=true";
    }

    public String credits() {
        listCredits.clear();
        listCredits.addAll(creditsFacade.findAllCredits());
        chargeListClientPotentielle();
        chargeListProduitSystem();
        chargeListTermes();
        chargeListCreditNonAttribue();
        prepareCreate();
        return "credits.xhtml?faces-redirect=true";
    }

    public void chargeListCreditNonAttribue() {
        listAllFreeCredits.clear();
        listAllFreeCredits.addAll(creditsFacade.findAllFreeCredits(creditSatus0));
    }

    public void chargeListClientPotentielle() {
        listClientPotentiel.clear();
        listClientPotentiel.addAll(clientFacade.findAll());
    }

    public void chargeListProduitSystem() {
        listProduitSystem.clear();
        listProduitSystem.addAll(produitsFacade.findByTypeproduit(filtreTypeSystem));
    }

    public void chargeListTermes() {
        listTermes.clear();
        listTermes.addAll(termesFacade.findAllValideTermes(filtreValidTermes));
    }

    public void prepareCreate() {
        qteLampeSupCreditIN = 0;
        qteRadioSupCreditIN = 0;
        qteTorcheSupCreditIN = 0;
        qteLampeSupCashIN = 0;
        qteRadioSupCashIN = 0;
        qteTorcheSupCashIN = 0;
        client = new Client();
        produits = new Produits();
        termes = new Termes();
        credits = new Credits();
        credits.setIdclient(client);
        credits.setIdproduits(produits);
        credits.setIdtermes(termes);
    }

    public void prepareEdit() {
        credits = new Credits();
        credits.setIdclient(new Client());
        credits.setIdproduits(new Produits());
        credits.setIdtermes(new Termes());
        /*credits.setQteappsupcash1(qteLampeSupCashIN);
         credits.setQteappsupcash2(qteRadioSupCashIN);
         credits.setQteappsupcash3(qteTorcheSupCashIN);
         credits.setQteappsupcredit1(qteLampeSupCreditIN);
         credits.setQteappsupcredit2(qteRadioSupCreditIN);
         credits.setQteappsupcredit3(qteTorcheSupCreditIN);
         credits.setNuminstall(numInstallIN);*/
    }

    public int searchPrixSystem(int dureeCredit) {
        try {
            if (dureeCredit == modeleTerme1) {
                prixSystemeCredit = produitsFacade.findPrixCredit1ans(idproduitsIN);
            } else if (dureeCredit == modeleTerme2) {
                prixSystemeCredit = produitsFacade.findPrixCredit2ans(idproduitsIN);
            } else if (dureeCredit == modeleTerme3) {
                prixSystemeCredit = produitsFacade.findPrixCredit3ans(idproduitsIN);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prixSystemeCredit;
    }

    public String savePreactiveCredit() {
        try {
            System.out.println("-----VARIABLES FORMULAIRE-----");
            idclientPotentielIN = this.credits.getIdclient().getIdclient();
            System.out.println("Client Id IN: " + idclientPotentielIN);
            idtermesIN = this.credits.getIdtermes().getIdtermes();
            System.out.println("Terme Id IN: " + idtermesIN);
            idproduitsIN = this.credits.getIdproduits().getIdproduits();
            System.out.println("Produit Id IN: " + idproduitsIN);
            System.out.println("No Install IN: " + numInstallIN);
            System.out.println("Credit 1: " + qteLampeSupCreditIN);
            System.out.println("Credit 2: " + qteRadioSupCreditIN);
            System.out.println("Credit 3: " + qteTorcheSupCreditIN);
            System.out.println("Cash 1: " + qteLampeSupCashIN);
            System.out.println("Cash 2: " + qteRadioSupCashIN);
            System.out.println("Cash 3: " + qteTorcheSupCashIN);
            nbreMensualiteTermes = termesFacade.findNbreMensualite(idtermesIN);
            initialisePrixsAppSupCash(lampe1, radio1, torche1);
            prixTotalAppSupCash = (prix1LampeSupCash * qteLampeSupCashIN) + (prix1RadioSupCash * qteRadioSupCashIN) + (prix1TorcheSupCash * qteTorcheSupCashIN);
            dureeCreditIN = termesFacade.findDureeCredit(idtermesIN);
            // 1ere etape calcul montant total du credit
            montantTotalCreditAUTO = searchPrixSystem(dureeCreditIN);
            initialisePrixsAppSupCredit(dureeCreditIN);
            prixTotalAppSupCredit = (prix1LampeSupCredit * qteLampeSupCreditIN) + (prix1RadioSupSupCredit * qteRadioSupCreditIN) + (prix1TorcheSupCredit * qteTorcheSupCreditIN);
            montantTotalCreditAUTO = montantTotalCreditAUTO + prixTotalAppSupCredit;
            acomptePourcent = Double.parseDouble(termesFacade.findAcomptePourcent(idtermesIN));
            System.out.println("Acompte pourcentage Before operation: " + acomptePourcent + " %");
            acompteCreditAUTO = (int) Math.ceil((montantTotalCreditAUTO * (acomptePourcent / 100)));
            acompteTotalAUTO = acompteCreditAUTO + prixTotalAppSupCash;
            prix1JourAUTO = (int) Math.ceil((montantTotalCreditAUTO - acompteCreditAUTO) / (nbreMensualiteTermes * Double.parseDouble(moyenJr1Mois)));
            prix1MoisAUTO = prix1JourAUTO * 30;
            montantrestantAUTO = montantTotalCreditAUTO - montantPayeAUTO;
            System.out.println(". ");
            System.out.println("-----VARIABLES CALCULATE-----");
            System.out.println("Prix du systeme a credit: " + prixSystemeCredit);
            System.out.println("Mensualite: " + nbreMensualiteTermes);
            System.out.println("Prix Lampe credit: " + prix1LampeSupCredit);
            System.out.println("Qte lampe: " + qteLampeSupCreditIN);
            System.out.println("Prix Radio credit: " + prix1RadioSupSupCredit);
            System.out.println("Qte Radio: " + qteRadioSupCreditIN);
            System.out.println("Prix Torche credit: " + prix1TorcheSupCredit);
            System.out.println("Qte Torche: " + qteTorcheSupCreditIN);
            System.out.println("Prix Lampe cash: " + prix1LampeSupCash);
            System.out.println("Qte: " + qteLampeSupCashIN);
            System.out.println("Prix Radio cash: " + prix1RadioSupCash);
            System.out.println("Qte: " + qteRadioSupCashIN);
            System.out.println("Prix Torche cash: " + prix1TorcheSupCash);
            System.out.println("Qte: " + qteTorcheSupCashIN);
            System.out.println("Montant total credit: " + montantTotalCreditAUTO);
            System.out.println("Acompte pourcentage after operation: " + acomptePourcent + " %");
            System.out.println("Acompte du creditt: " + acompteCreditAUTO);
            System.out.println("Montant total App Sup a Credit: " + prixTotalAppSupCredit);
            System.out.println("Acompte total: " + acompteTotalAUTO);
            System.out.println("Prix / Mois: " + prix1MoisAUTO);
            System.out.println("Prix / Jours: " + prix1JourAUTO);
            System.out.println("Montant paye : " + montantPayeAUTO);
            System.out.println("Montant restant : " + montantrestantAUTO);
            reqUpdateCredNewContr1 = creditsFacade.findToUpdateCredNewContr1(numInstallIN, montantrestantAUTO, montantPayeAUTO, montantTotalCreditAUTO, qteTorcheSupCreditIN, qteRadioSupCreditIN, qteLampeSupCreditIN, qteLampeSupCashIN, qteRadioSupCashIN, qteTorcheSupCashIN, prix1MoisAUTO, prix1JourAUTO, acompteTotalAUTO, acompteCreditAUTO, creditSatus1);
            System.out.println("1er Nombre de CreditNewContract update: " + reqUpdateCredNewContr1);
            reqUpdateCreditByNewContract = creditsFacade.findToUpdateCreditNewContract(this.credits.getIdclient(), this.credits.getIdproduits(), this.credits.getIdtermes(), numInstallIN);
            System.out.println("2er Nombre de CreditNewContract update: " + reqUpdateCreditByNewContract);
            showMessage = true;
            message = "Succes nouveau contrat";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec nouveau contrat";
            e.printStackTrace();
        }
        return credits();
    }

    public void resetAllRow() {
        clientIN = new Client();
        produits = new Produits();
        termes = new Termes();
        idOfClientIN = clientFacade.findIdClient(filtreClientFirstName, filtreClientLastName);
        System.out.println("Id Client IN: " + idOfClientIN);
        clientIN.setIdclient( idOfClientIN);
        this.credits.setIdclient(clientIN);
        this.credits.setAcomptecredit(0);
        this.credits.setAcomptetotal(0);
        this.credits.setCreditstatus(creditSatus0);
        //this.credits.setNuminstall(numInstallIN);
        this.credits.setPrix1jour(0);
        this.credits.setPrix1mois(0);
        this.credits.setQteappsupcash1(0);
        this.credits.setQteappsupcash2(0);
        this.credits.setQteappsupcash3(0);
        this.credits.setQteappsupcredit1(0);
        this.credits.setQteappsupcredit2(0);
        this.credits.setQteappsupcredit3(0);
        this.credits.setTotalcredits(0);
        this.credits.setTotalgracerestant(60);
        this.credits.setTotalgraceused(0);
        this.credits.setTotalmontantpaye(0);
        this.credits.setTotalmontantrestant(0);
        //credits.setIdclient(client);
        //credits.setIdproduits(produits);
        //credits.setIdtermes(termes); 
    }

    public String resetCredits() {
        try {
            numInstallIN = this.credits.getNuminstall();
            System.out.println("NumInstall a reinitialiser: " + numInstallIN);
            //String creditOfStatusNow = creditsFacade.findCreditStatus(numInstallIN);
            montantTotalPayeNow = creditsFacade.findMontantPaye(numInstallIN);
            //if (creditOfStatusNow.equals(creditSatus3)) {
            if (montantTotalPayeNow > 0) {
                showMessage = true;
                message = "Impossible de modifier ce crddit. Un 1er versement déjà éffectué";
            } else {
                resetAllRow();
                creditsFacade.edit(this.credits);
                showMessage = true;
                message = "Succes re-initialisation";
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return credits();
    }

    public String editCredits() {
        try {
            numInstallIN = this.credits.getNuminstall();
            //String creditOfStatusNow = creditsFacade.findCreditStatus(numInstallIN);
            montantTotalPayeNow = creditsFacade.findMontantPaye(numInstallIN);
            //if (creditOfStatusNow.equals(creditSatus3)) {
            if (montantTotalPayeNow > 0) {
                prepareCreate();
                showMessage = true;
                message = "Impossible de modifier ce crddit. Un 1er versement déjà éffectué";
            } else {
                editPreactiveCredit();
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return credits();
    }

    public String editPreactiveCredit() {
        try {
            System.out.println("-----VARIABLES FORMULAIRE-----");
            idclientPotentielIN = this.credits.getIdclient().getIdclient();
            System.out.println("Client Id IN: " + idclientPotentielIN);
            idtermesIN = this.credits.getIdtermes().getIdtermes();
            System.out.println("Terme Id IN: " + idtermesIN);
            idproduitsIN = this.credits.getIdproduits().getIdproduits();
            System.out.println("Produit Id IN: " + idproduitsIN);
            numInstallIN = this.credits.getNuminstall();
            qteLampeSupCreditIN = this.credits.getQteappsupcredit1();
            qteRadioSupCreditIN = this.credits.getQteappsupcredit2();
            qteTorcheSupCreditIN = this.credits.getQteappsupcredit3();
            qteLampeSupCashIN = this.credits.getQteappsupcash1();
            qteRadioSupCashIN = this.credits.getQteappsupcash2();
            qteTorcheSupCashIN = this.credits.getQteappsupcash3();
            nbreMensualiteTermes = termesFacade.findNbreMensualite(idtermesIN);
            initialisePrixsAppSupCash(lampe1, radio1, torche1);
            prixTotalAppSupCash = (prix1LampeSupCash * qteLampeSupCashIN) + (prix1RadioSupCash * qteRadioSupCashIN) + (prix1TorcheSupCash * qteTorcheSupCashIN);
            dureeCreditIN = termesFacade.findDureeCredit(idtermesIN);
            // 1ere etape calcul montant total du credit
            montantTotalCreditAUTO = searchPrixSystem(dureeCreditIN);
            initialisePrixsAppSupCredit(dureeCreditIN);
            prixTotalAppSupCredit = (prix1LampeSupCredit * qteLampeSupCreditIN) + (prix1RadioSupSupCredit * qteRadioSupCreditIN) + (prix1TorcheSupCredit * qteTorcheSupCreditIN);
            montantTotalCreditAUTO = montantTotalCreditAUTO + prixTotalAppSupCredit;
            acomptePourcent = Double.parseDouble(termesFacade.findAcomptePourcent(idtermesIN));
            System.out.println("Acompte pourcentage Before operation: " + acomptePourcent + " %");
            acompteCreditAUTO = (int) Math.ceil((montantTotalCreditAUTO * (acomptePourcent / 100)));
            acompteTotalAUTO = acompteCreditAUTO + prixTotalAppSupCash;
            prix1JourAUTO = (int) Math.ceil((montantTotalCreditAUTO - acompteCreditAUTO) / (nbreMensualiteTermes * Double.parseDouble(moyenJr1Mois)));
            prix1MoisAUTO = prix1JourAUTO * 30;
            montantrestantAUTO = montantTotalCreditAUTO - montantPayeAUTO;
            System.out.println(". ");
            System.out.println("-----VARIABLES CALCULATE-----");
            System.out.println("Prix du systeme a credit: " + prixSystemeCredit);
            System.out.println("Mensualite: " + nbreMensualiteTermes);
            System.out.println("Prix Lampe credit: " + prix1LampeSupCredit);
            System.out.println("Qte lampe: " + qteLampeSupCreditIN);
            System.out.println("Prix Radio credit: " + prix1RadioSupSupCredit);
            System.out.println("Qte Radio: " + qteRadioSupCreditIN);
            System.out.println("Prix Torche credit: " + prix1TorcheSupCredit);
            System.out.println("Qte Torche: " + qteTorcheSupCreditIN);
            System.out.println("Prix Lampe cash: " + prix1LampeSupCash);
            System.out.println("Qte: " + qteLampeSupCashIN);
            System.out.println("Prix Radio cash: " + prix1RadioSupCash);
            System.out.println("Qte: " + qteRadioSupCashIN);
            System.out.println("Prix Torche cash: " + prix1TorcheSupCash);
            System.out.println("Qte: " + qteTorcheSupCashIN);
            System.out.println("Montant total credit: " + montantTotalCreditAUTO);
            System.out.println("Acompte pourcentage after operation: " + acomptePourcent + " %");
            System.out.println("Acompte du creditt: " + acompteCreditAUTO);
            System.out.println("Montant total App Sup a Credit: " + prixTotalAppSupCredit);
            System.out.println("Acompte total: " + acompteTotalAUTO);
            System.out.println("Prix / Mois: " + prix1MoisAUTO);
            System.out.println("Prix / Jours: " + prix1JourAUTO);
            System.out.println("Montant paye : " + montantPayeAUTO);
            System.out.println("Montant restant : " + montantrestantAUTO);
            reqUpdateCredNewContr1 = creditsFacade.findToUpdateCredNewContr1(numInstallIN, montantrestantAUTO, montantPayeAUTO, montantTotalCreditAUTO, qteTorcheSupCreditIN, qteRadioSupCreditIN, qteLampeSupCreditIN, qteLampeSupCashIN, qteRadioSupCashIN, qteTorcheSupCashIN, prix1MoisAUTO, prix1JourAUTO, acompteTotalAUTO, acompteCreditAUTO, creditSatus1);
            System.out.println("1er Nombre de CreditNewContract update: " + reqUpdateCredNewContr1);
            reqUpdateCreditByNewContract = creditsFacade.findToUpdateCreditNewContract(this.credits.getIdclient(), this.credits.getIdproduits(), this.credits.getIdtermes(), numInstallIN);
            System.out.println("2eme Nombre de CreditNewContract update: " + reqUpdateCreditByNewContract);
            prepareCreate();
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            prepareCreate();
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return credits();
    }

    public String fakdeleteCredits() {
        try {
            //creditsFacade.remove(credits);
            showMessage = true;
            message = "Suppression non autorisée";
        } catch (Exception e) {
            showMessage = true;
            message = "Suppression non autorisée";
            e.printStackTrace();
        }
        return rtocontract();
    }

    public void initialisePrixsAppSupCredit(int dureeCredit) {
        if (dureeCredit == modeleTerme1) {
            prix1LampeSupCredit = produitsFacade.findPrixLampe1an(lampe1);
            prix1RadioSupSupCredit = produitsFacade.findPrixRadio1an(radio1);
            prix1TorcheSupCredit = produitsFacade.findPrixTorche1an(torche1);
        } else if (dureeCredit == modeleTerme2) {
            prix1LampeSupCredit = produitsFacade.findPrixLampe2ans(lampe1);
            prix1RadioSupSupCredit = produitsFacade.findPrixRadio2ans(radio1);
            prix1TorcheSupCredit = produitsFacade.findPrixTorche2ans(torche1);
        } else if (dureeCredit == modeleTerme3) {
            prix1LampeSupCredit = produitsFacade.findPrixLampe3ans(lampe1);
            prix1RadioSupSupCredit = produitsFacade.findPrixRadio3ans(radio1);
            prix1TorcheSupCredit = produitsFacade.findPrixTorche3ans(torche1);
        }
    }

    public void initialisePrixsAppSupCash(String lampe1Cash, String radio1Cash, String torche1Cash) {
        prix1LampeSupCash = produitsFacade.findPrixAppSupCash(lampe1Cash);
        prix1RadioSupCash = produitsFacade.findPrixAppSupCash(radio1Cash);
        prix1TorcheSupCash = produitsFacade.findPrixAppSupCash(torche1Cash);
    }

// Accesseur et mutateur
    public CreditsFacadeLocal getCreditsFacade() {
        return creditsFacade;
    }

    public void setCreditsFacade(CreditsFacadeLocal creditsFacade) {
        this.creditsFacade = creditsFacade;
    }

    public List<Credits> getListCredits() {
        return listCredits;
    }

    public void setListCredits(List<Credits> listCredits) {
        this.listCredits = listCredits;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    /*public ClientFacadeLocal getClientFacade() {
     return clientFacade;
     }

     public void setClientFacade(ClientFacadeLocal clientFacade) {
     this.clientFacade = clientFacade;
     }*/
    public List<Client> getListClientPotentiel() {
        return listClientPotentiel;
    }

    public void setListClientPotentiel(List<Client> listClientPotentiel) {
        this.listClientPotentiel = listClientPotentiel;
    }

    public int getNumInstallIN() {
        return numInstallIN;
    }

    public void setNumInstallIN(int numInstallIN) {
        this.numInstallIN = numInstallIN;
    }

    public int getPhoneUserSystem() {
        return phoneUserSystem;
    }

    public void setPhoneUserSystem(int phoneUserSystem) {
        this.phoneUserSystem = phoneUserSystem;
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

    public String getUserOfSystem() {
        return userOfSystem;
    }

    public void setUserOfSystem(String userOfSystem) {
        this.userOfSystem = userOfSystem;
    }

    public String getStyleMarquer1() {
        return styleMarquer1;
    }

    public void setStyleMarquer1(String styleMarquer1) {
        this.styleMarquer1 = styleMarquer1;
    }

    public String getStyleAtelier() {
        return styleAtelier;
    }

    public void setStyleAtelier(String styleAtelier) {
        this.styleAtelier = styleAtelier;
    }

    public String getMagDispo() {
        return magDispo;
    }

    public void setMagDispo(String magDispo) {
        this.magDispo = magDispo;
    }

    public String getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(String defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public Utils getUtils() {
        return utils;
    }

    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    public String getBoxActivated() {
        return boxActivated;
    }

    public void setBoxActivated(String boxActivated) {
        this.boxActivated = boxActivated;
    }

    public int getTotalInstallBox() {
        return totalInstallBox;
    }

    public void setTotalInstallBox(int totalInstallBox) {
        this.totalInstallBox = totalInstallBox;
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

    public String getFiltreTypeSystem() {
        return filtreTypeSystem;
    }

    public void setFiltreTypeSystem(String filtreTypeSystem) {
        this.filtreTypeSystem = filtreTypeSystem;
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

    public long getIdclientPotentielIN() {
        return idclientPotentielIN;
    }

    public void setIdclientPotentielIN(long idclientPotentielIN) {
        this.idclientPotentielIN = idclientPotentielIN;
    }

    public long getIdtermesIN() {
        return idtermesIN;
    }

    public void setIdtermesIN(long idtermesIN) {
        this.idtermesIN = idtermesIN;
    }

    public long getIdproduitsIN() {
        return idproduitsIN;
    }

    public void setIdproduitsIN(long idproduitsIN) {
        this.idproduitsIN = idproduitsIN;
    }

    public int getQteLampeSupCreditIN() {
        return qteLampeSupCreditIN;
    }

    public void setQteLampeSupCreditIN(int qteLampeSupCreditIN) {
        this.qteLampeSupCreditIN = qteLampeSupCreditIN;
    }

    public int getQteRadioSupCreditIN() {
        return qteRadioSupCreditIN;
    }

    public void setQteRadioSupCreditIN(int qteRadioSupCreditIN) {
        this.qteRadioSupCreditIN = qteRadioSupCreditIN;
    }

    public int getQteTorcheSupCreditIN() {
        return qteTorcheSupCreditIN;
    }

    public void setQteTorcheSupCreditIN(int qteTorcheSupCreditIN) {
        this.qteTorcheSupCreditIN = qteTorcheSupCreditIN;
    }

    public int getQteLampeSupCashIN() {
        return qteLampeSupCashIN;
    }

    public void setQteLampeSupCashIN(int qteLampeSupCashIN) {
        this.qteLampeSupCashIN = qteLampeSupCashIN;
    }

    public int getQteRadioSupCashIN() {
        return qteRadioSupCashIN;
    }

    public void setQteRadioSupCashIN(int qteRadioSupCashIN) {
        this.qteRadioSupCashIN = qteRadioSupCashIN;
    }

    public int getQteTorcheSupCashIN() {
        return qteTorcheSupCashIN;
    }

    public void setQteTorcheSupCashIN(int qteTorcheSupCashIN) {
        this.qteTorcheSupCashIN = qteTorcheSupCashIN;
    }

    public int getMontantTransact() {
        return montantTransact;
    }

    public void setMontantTransact(int montantTransact) {
        this.montantTransact = montantTransact;
    }

    public int getMontantTotalCreditAUTO() {
        return montantTotalCreditAUTO;
    }

    public void setMontantTotalCreditAUTO(int montantTotalCreditAUTO) {
        this.montantTotalCreditAUTO = montantTotalCreditAUTO;
    }

    public int getAcompteCreditAUTO() {
        return acompteCreditAUTO;
    }

    public void setAcompteCreditAUTO(int acompteCreditAUTO) {
        this.acompteCreditAUTO = acompteCreditAUTO;
    }

    public int getAcompteTotalAUTO() {
        return acompteTotalAUTO;
    }

    public void setAcompteTotalAUTO(int acompteTotalAUTO) {
        this.acompteTotalAUTO = acompteTotalAUTO;
    }

    public int getPrix1MoisAUTO() {
        return prix1MoisAUTO;
    }

    public void setPrix1MoisAUTO(int prix1MoisAUTO) {
        this.prix1MoisAUTO = prix1MoisAUTO;
    }

    public int getPrix1JourAUTO() {
        return prix1JourAUTO;
    }

    public void setPrix1JourAUTO(int prix1JourAUTO) {
        this.prix1JourAUTO = prix1JourAUTO;
    }

    public int getMontantPayeAUTO() {
        return montantPayeAUTO;
    }

    public void setMontantPayeAUTO(int montantPayeAUTO) {
        this.montantPayeAUTO = montantPayeAUTO;
    }

    public int getMontantrestantAUTO() {
        return montantrestantAUTO;
    }

    public void setMontantrestantAUTO(int montantrestantAUTO) {
        this.montantrestantAUTO = montantrestantAUTO;
    }

    public int getModeleTerme1() {
        return modeleTerme1;
    }

    public void setModeleTerme1(int modeleTerme1) {
        this.modeleTerme1 = modeleTerme1;
    }

    public int getModeleTerme2() {
        return modeleTerme2;
    }

    public void setModeleTerme2(int modeleTerme2) {
        this.modeleTerme2 = modeleTerme2;
    }

    public int getModeleTerme3() {
        return modeleTerme3;
    }

    public void setModeleTerme3(int modeleTerme3) {
        this.modeleTerme3 = modeleTerme3;
    }

    public int getDureeCreditIN() {
        return dureeCreditIN;
    }

    public void setDureeCreditIN(int dureeCreditIN) {
        this.dureeCreditIN = dureeCreditIN;
    }

    public int getPrixSystemeCredit() {
        return prixSystemeCredit;
    }

    public void setPrixSystemeCredit(int prixSystemeCredit) {
        this.prixSystemeCredit = prixSystemeCredit;
    }

    public int getPrix1LampeSupCredit() {
        return prix1LampeSupCredit;
    }

    public void setPrix1LampeSupCredit(int prix1LampeSupCredit) {
        this.prix1LampeSupCredit = prix1LampeSupCredit;
    }

    public int getPrix1RadioSupSupCredit() {
        return prix1RadioSupSupCredit;
    }

    public void setPrix1RadioSupSupCredit(int prix1RadioSupSupCredit) {
        this.prix1RadioSupSupCredit = prix1RadioSupSupCredit;
    }

    public int getPrix1TorcheSupCredit() {
        return prix1TorcheSupCredit;
    }

    public void setPrix1TorcheSupCredit(int prix1TorcheSupCredit) {
        this.prix1TorcheSupCredit = prix1TorcheSupCredit;
    }

    public String getRadio1() {
        return radio1;
    }

    public void setRadio1(String radio1) {
        this.radio1 = radio1;
    }

    public String getLampe1() {
        return lampe1;
    }

    public void setLampe1(String lampe1) {
        this.lampe1 = lampe1;
    }

    public String getTorche1() {
        return torche1;
    }

    public void setTorche1(String torche1) {
        this.torche1 = torche1;
    }

    public int getPrixTotalAppSupCredit() {
        return prixTotalAppSupCredit;
    }

    public void setPrixTotalAppSupCredit(int prixTotalAppSupCredit) {
        this.prixTotalAppSupCredit = prixTotalAppSupCredit;
    }

    public int getPrixTotalAppSupCash() {
        return prixTotalAppSupCash;
    }

    public void setPrixTotalAppSupCash(int prixTotalAppSupCash) {
        this.prixTotalAppSupCash = prixTotalAppSupCash;
    }

    public int getPrix1LampeSupCash() {
        return prix1LampeSupCash;
    }

    public void setPrix1LampeSupCash(int prix1LampeSupCash) {
        this.prix1LampeSupCash = prix1LampeSupCash;
    }

    public int getPrix1RadioSupCash() {
        return prix1RadioSupCash;
    }

    public void setPrix1RadioSupCash(int prix1RadioSupCash) {
        this.prix1RadioSupCash = prix1RadioSupCash;
    }

    public int getPrix1TorcheSupCash() {
        return prix1TorcheSupCash;
    }

    public void setPrix1TorcheSupCash(int prix1TorcheSupCash) {
        this.prix1TorcheSupCash = prix1TorcheSupCash;
    }

    public double getAcomptePourcent() {
        return acomptePourcent;
    }

    public void setAcomptePourcent(double acomptePourcent) {
        this.acomptePourcent = acomptePourcent;
    }

    public int getAcompteCredit() {
        return acompteCredit;
    }

    public void setAcompteCredit(int acompteCredit) {
        this.acompteCredit = acompteCredit;
    }

    public int getNbreMensualiteTermes() {
        return nbreMensualiteTermes;
    }

    public void setNbreMensualiteTermes(int nbreMensualiteTermes) {
        this.nbreMensualiteTermes = nbreMensualiteTermes;
    }

    public int getReqUpdateCreditByNewContract() {
        return reqUpdateCreditByNewContract;
    }

    public void setReqUpdateCreditByNewContract(int reqUpdateCreditByNewContract) {
        this.reqUpdateCreditByNewContract = reqUpdateCreditByNewContract;
    }

    public int getReqUpdateCredNewContr1() {
        return reqUpdateCredNewContr1;
    }

    public void setReqUpdateCredNewContr1(int reqUpdateCredNewContr1) {
        this.reqUpdateCredNewContr1 = reqUpdateCredNewContr1;
    }

    public String getNameClientIN() {
        return nameClientIN;
    }

    public void setNameClientIN(String nameClientIN) {
        this.nameClientIN = nameClientIN;
    }

    public ClientFacadeLocal getClientFacade() {
        return clientFacade;
    }

    public void setClientFacade(ClientFacadeLocal clientFacade) {
        this.clientFacade = clientFacade;
    }

    public String getMoyenJr1Mois() {
        return moyenJr1Mois;
    }

    public void setMoyenJr1Mois(String moyenJr1Mois) {
        this.moyenJr1Mois = moyenJr1Mois;
    }

    public List<Credits> getListAllFreeCredits() {
        return listAllFreeCredits;
    }

    public void setListAllFreeCredits(List<Credits> listAllFreeCredits) {
        this.listAllFreeCredits = listAllFreeCredits;
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

    public String getFiltreClientFirstName() {
        return filtreClientFirstName;
    }

    public void setFiltreClientFirstName(String filtreClientFirstName) {
        this.filtreClientFirstName = filtreClientFirstName;
    }

    public String getFiltreClientLastName() {
        return filtreClientLastName;
    }

    public void setFiltreClientLastName(String filtreClientLastName) {
        this.filtreClientLastName = filtreClientLastName;
    }

    public long getIdOfClientIN() {
        return idOfClientIN;
    }

    public void setIdOfClientIN(long idOfClientIN) {
        this.idOfClientIN = idOfClientIN;
    }

    public Client getClientIN() {
        return clientIN;
    }

    public void setClientIN(Client clientIN) {
        this.clientIN = clientIN;
    }

    public String getFiltreValidTermes() {
        return filtreValidTermes;
    }

    public void setFiltreValidTermes(String filtreValidTermes) {
        this.filtreValidTermes = filtreValidTermes;
    }

    public int getMontantTotalPayeNow() {
        return montantTotalPayeNow;
    }

    public void setMontantTotalPayeNow(int montantTotalPayeNow) {
        this.montantTotalPayeNow = montantTotalPayeNow;
    }
}
