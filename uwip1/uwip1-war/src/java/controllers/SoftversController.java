
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Utils;
import entities.Softvers;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessions.SoftversFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "softversController")
@SessionScoped
public class SoftversController implements Serializable {

    @Inject
    private SoftversFacadeLocal softversFacade;
    private List<Softvers> listSoftvers = new ArrayList<Softvers>();
    private Softvers softvers = new Softvers();
    private String message = "";
    private String nameToCount = "";
    private String versionToCount = "";
    private int reqCount = 0;
    private boolean showMessage = false;
    private Utils utils = new Utils();

    /**
     * Creates a new instance of SoftversController
     */
    public SoftversController() {
    }

    public String softvers() {
        listSoftvers.clear();
        listSoftvers.addAll(softversFacade.findAll());
        return "softvers.xhtml?faces-redirect=true";
    }

    public void prepareCreate() {
        softvers = new Softvers();
    }

    public void prepareEdit() {
        softvers = new Softvers();
    }

    public String saveSoftvers() {
        try {
            nameToCount = softvers.getName();
            versionToCount = softvers.getVersion();
            reqCount = softversFacade.findCopieDouble(nameToCount, versionToCount);
            if (reqCount >= 1) {
                showMessage = true;
                message = "Désolé! Cette version logicielle existe déjà";
            } else {
                softversFacade.create(softvers); // Creation du softvers dans la derniere ligne vide
                showMessage = true;
                message = "Enregistrement effectué avec succès";
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return softvers();
    }

    public String editSoftvers() {
        try {
            softversFacade.edit(softvers);
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return softvers();
    }

    public String deleteSoftvers() {
        try {
            System.out.println("soft:" + softvers.getName());
            softversFacade.remove(softvers);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la suppression";
            e.printStackTrace();
        }
        return softvers();
    }

    public String imprimer() {

        try {
            utils.print("Reports/ListeSoftvers.jasper");


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            return ext.getRequestPathInfo() + "?faces-redirect=true";

        }


    }

    public List<Softvers> getListSoftvers() {
        return this.listSoftvers;
    }

    public void setListSoftvers(List<Softvers> listsoftvers) {
        this.listSoftvers = listsoftvers;
    }

    public Softvers getSoftvers() {
        return softvers;
    }

    public void setSoftvers(Softvers softvers) {
        this.softvers = softvers;
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
}
