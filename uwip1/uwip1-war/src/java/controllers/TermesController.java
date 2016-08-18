
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Utils;
import entities.Client;
import entities.Credits;
import entities.Produits;
import entities.Termes;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
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
@Named(value = "termesController")
@SessionScoped
public class TermesController implements Serializable {

    @Inject
    private TermesFacadeLocal termesFacade;
    private List<Termes> listTermes = new ArrayList<Termes>();
    private Termes termes = new Termes();
    private Termes idTermesToCount = new Termes();
    @Inject
    private CreditsFacadeLocal creditsFacade;
    private List<Credits> listCredits = new ArrayList<Credits>();
    private Credits credits = new Credits();
    private String message = "";
    private String termesToCount = "";
    private String filtreValidTermes = "- -";
    private int reqCount = 0;
    private boolean showMessage = false;
    private Utils utils = new Utils();

    /**
     * Creates a new instance of TermesController
     */
    public TermesController() {
    }

    public String termes() {
        prepareCreate();
        listTermes.clear();
        listTermes.addAll(termesFacade.findAllValideTermes(filtreValidTermes));
        return "termes.xhtml?faces-redirect=true";
    }

    public void prepareCreate() {
        termes = new Termes();
        reqCount = 0;
    }

    public void prepareEdit() {
        termes = new Termes();
        reqCount = 0;
    }

    public String saveTermes() {
        try {
            termesToCount = termes.getNomcredit();
            reqCount = termesFacade.findCopieDouble(termesToCount);
            if (reqCount >= 1) {
                showMessage = true;
                message = "Désolé! Ce terme existe déjà";
            } else {
                termesFacade.create(termes); // Creation du termes dans la derniere ligne vide
                showMessage = true;
                message = "Enregistrement effectué avec succès";
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return termes();
    }

    public String editTermes() {
        try {
            termesFacade.edit(termes);
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return termes();
    }

    public String deleteTermes() {
        try {
            long idTermesOUT = this.termes.getIdtermes();
            idTermesToCount.setIdtermes(idTermesOUT);
            reqCount = creditsFacade.findOccurenceTermes(idTermesToCount);
            if (reqCount >= 1) {
                System.out.println("Nombre d'occurence de termes: " + reqCount);
                showMessage = true;
                message = "Désolé! Vous ne pouvez supprimer ce termes.";
            } else {
                System.out.println("Nombre d'occurence de termes: " + reqCount);
                termesFacade.remove(termes);
                showMessage = true;
                message = "Suppression effectuée avec succès";
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la suppression";
            e.printStackTrace();
        }
        return termes();
    }

    public String imprimer() {

        try {
            utils.print("Reports/ListeTermes.jasper");


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            return ext.getRequestPathInfo() + "?faces-redirect=true";

        }


    }

    public List<Termes> getListTermes() {
        return this.listTermes;
    }

    public void setListTermes(List<Termes> listtermes) {
        this.listTermes = listtermes;
    }

    public Termes getTermes() {
        return termes;
    }

    public void setTermes(Termes termes) {
        this.termes = termes;
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

    public String getFiltreValidTermes() {
        return filtreValidTermes;
    }

    public void setFiltreValidTermes(String filtreValidTermes) {
        this.filtreValidTermes = filtreValidTermes;
    }

    public TermesFacadeLocal getTermesFacade() {
        return termesFacade;
    }

    public void setTermesFacade(TermesFacadeLocal termesFacade) {
        this.termesFacade = termesFacade;
    }

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

    public Termes getIdTermesToCount() {
        return idTermesToCount;
    }

    public void setIdTermesToCount(Termes idTermesToCount) {
        this.idTermesToCount = idTermesToCount;
    }

    public String getTermesToCount() {
        return termesToCount;
    }

    public void setTermesToCount(String termesToCount) {
        this.termesToCount = termesToCount;
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
