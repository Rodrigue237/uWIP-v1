
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Utils;
import entities.Hardvers;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessions.HardversFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "hardversController")
@SessionScoped
public class HardversController implements Serializable {

    @Inject
    private HardversFacadeLocal hardversFacade;
    private List<Hardvers> listHardvers = new ArrayList<Hardvers>();
    private Hardvers hardvers = new Hardvers();
    private String message = "";
    private String nameToCount = "";
    private String versionToCount = "";
    private int reqCount = 0;
    private boolean showMessage = false;
    private Utils utils = new Utils();

    /**
     * Creates a new instance of HardversController
     */
    public HardversController() {
    }

    public String hardvers() {
        listHardvers.clear();
        listHardvers.addAll(hardversFacade.findAll());
        return "hardvers.xhtml?faces-redirect=true";
    }

    public void prepareCreate() {
        hardvers = new Hardvers();
    }

    public void prepareEdit() {
        hardvers = new Hardvers();
    }

    public String saveHardvers() {
        try {
            nameToCount = hardvers.getName();
            versionToCount = hardvers.getVersion();
            reqCount = hardversFacade.findCopieDouble(nameToCount, versionToCount);
            if (reqCount >= 1) {
                showMessage = true;
                message = "Désolé! Cette version matérielle existe déjà";
            } else {
                hardversFacade.create(hardvers); // Creation du hardvers dans la derniere ligne vide
                showMessage = true;
                message = "Enregistrement effectué avec succès";
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return hardvers();
    }

    public String editHardvers() {
        try {
            hardversFacade.edit(hardvers);
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return hardvers();
    }

    public String deleteHardvers() {
        try {
            System.out.println("Hard:" + hardvers.getName());
            hardversFacade.remove(hardvers);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la suppression";
            e.printStackTrace();
        }
        return hardvers();
    }

    public String imprimer() {

        try {
            utils.print("Reports/ListeHardvers.jasper");


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            return ext.getRequestPathInfo() + "?faces-redirect=true";

        }


    }

    public List<Hardvers> getListHardvers() {
        return this.listHardvers;
    }

    public void setListHardvers(List<Hardvers> listhardvers) {
        this.listHardvers = listhardvers;
    }

    public Hardvers getHardvers() {
        return hardvers;
    }

    public void setHardvers(Hardvers hardvers) {
        this.hardvers = hardvers;
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
