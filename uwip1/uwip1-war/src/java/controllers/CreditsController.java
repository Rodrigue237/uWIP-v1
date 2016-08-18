
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Utils;
import entities.Credits;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessions.CreditsFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "creditsController")
@SessionScoped
public class CreditsController implements Serializable {

    @Inject
    private CreditsFacadeLocal creditsFacade;
    private List<Credits> listCredits = new ArrayList<Credits>();
    private Credits credits = new Credits();
    private String message = "";
    private int creditsToCount = 0;
    private int reqCount = 0;
    private boolean showMessage = false;
    private Utils utils = new Utils();

    /**
     * Creates a new instance of CreditsController
     */
    public CreditsController() {
    }

    public String credits() {
        listCredits.clear();
        listCredits.addAll(creditsFacade.findAll());
        return "credits.xhtml?faces-redirect=true";
    }

    public void prepareCreate() {
        credits = new Credits();
    }

    public void prepareEdit() {
        credits = new Credits();
    }

    public String saveCredits() {
        try {
            creditsToCount = credits.getNuminstall(); 
            reqCount = creditsFacade.findCopieDouble(creditsToCount);
            if (reqCount >= 1) {
                showMessage = true;
                message = "Désolé! Cette organisaion existe déjà";
            } else {
                creditsFacade.create(credits); // Creation du credits dans la derniere ligne vide
                showMessage = true;
                message = "Enregistrement effectué avec succès";
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return credits();
    }

    public String editCredits() {
        try {
            creditsFacade.edit(credits);
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return credits();
    }

    public String deleteCredits() {
        try {
            System.out.println("Credit:" + credits.getNuminstall());
            creditsFacade.remove(credits);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la suppression";
            e.printStackTrace();
        }
        return credits();
    }
       

    public String imprimer() {

        try {
            utils.print("Reports/ListeCredits.jasper"); 


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            return ext.getRequestPathInfo() + "?faces-redirect=true";

        }


    }

    public List<Credits> getListCredits() {
        return this.listCredits;
    }

    public void setListCredits(List<Credits> listcredits) {
        this.listCredits = listcredits;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
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

    public CreditsFacadeLocal getCreditsFacade() {
        return creditsFacade;
    }

    public void setCreditsFacade(CreditsFacadeLocal creditsFacade) {
        this.creditsFacade = creditsFacade;
    }

    public int getCreditsToCount() {
        return creditsToCount;
    }

    public void setCreditsToCount(int creditsToCount) {
        this.creditsToCount = creditsToCount;
    }

    public int getReqCount() {
        return reqCount;
    }

    public void setReqCount(int reqCount) {
        this.reqCount = reqCount;
    }

    public Utils getUtils() {
        return utils;
    }

    public void setUtils(Utils utils) {
        this.utils = utils;
    }
}
