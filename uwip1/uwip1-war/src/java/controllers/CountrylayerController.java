
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Utils;
import entities.Countrylayer;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessions.CountrylayerFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "countrylayerController")
@SessionScoped
public class CountrylayerController implements Serializable {

    @Inject
    private CountrylayerFacadeLocal countrylayerFacade;
    private List<Countrylayer> listCountrylayer = new ArrayList<Countrylayer>();
    private Countrylayer countrylayer = new Countrylayer();
    private String message = "";
    private String nameToCount = "";
    private int reqCount = 0;
    private boolean showMessage = false;
    private Utils utils = new Utils();

    /**
     * Creates a new instance of CountrylayerController
     */
    public CountrylayerController() {
    }

    public String countrylayer() {
        listCountrylayer.clear();
        listCountrylayer.addAll(countrylayerFacade.findAll());
        return "countrylayers.xhtml?faces-redirect=true";
    }

    public void prepareCreate() {
        countrylayer = new Countrylayer();
    }

    public void prepareEdit() {
        countrylayer = new Countrylayer();
    }

    public String saveCountrylayer() {
        try {
            nameToCount = countrylayer.getLayer();
            reqCount = countrylayerFacade.findCopieDouble(nameToCount);
            if (reqCount >= 1) {
                showMessage = true;
                message = "Désolé! Ce layer existe déjà";
            } else {
                countrylayerFacade.create(countrylayer); // Creation du countrylayer dans la derniere ligne vide
                showMessage = true;
                message = "Enregistrement effectué avec succès";
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return countrylayer();
    }

    public String editCountrylayer() {
        try {
            countrylayerFacade.edit(countrylayer);
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return countrylayer();
    }

    public String deleteCountrylayer() {
        try {
            countrylayerFacade.remove(countrylayer);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la suppression";
            e.printStackTrace();
        }
        return countrylayer();
    }

    public String imprimer() {

        try {
            utils.print("Reports/ListeCountrylayer.jasper");


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            return ext.getRequestPathInfo() + "?faces-redirect=true";

        }


    }

    public List<Countrylayer> getListCountrylayer() {
        return this.listCountrylayer;
    }

    public void setListCountrylayer(List<Countrylayer> listcountrylayer) {
        this.listCountrylayer = listcountrylayer;
    }

    public Countrylayer getCountrylayer() {
        return countrylayer;
    }

    public void setCountrylayer(Countrylayer countrylayer) {
        this.countrylayer = countrylayer;
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
