
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Utils;
import entities.Townlayer;
import entities.Client;
import entities.Countrylayer;
import entities.Townlayer;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessions.TownlayerFacadeLocal;
import sessions.CountrylayerFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "townlayerController")
@SessionScoped
public class TownlayerController implements Serializable {

    @Inject
    private TownlayerFacadeLocal townlayerFacade;
    private List<Townlayer> listTownlayer = new ArrayList<Townlayer>();
    private List<Townlayer> listTownlayerMapped = new ArrayList<Townlayer>();
    private Townlayer townlayer = new Townlayer();
    @Inject
    private CountrylayerFacadeLocal countrylayerFacade;
    private List<Countrylayer> listCountrylayer = new ArrayList<Countrylayer>();
    private String layerTownlayerSaisi = "";
    private int reqCount = 0;
    private String message = "";
    private boolean showMessage = false;
    private Utils utils = new Utils();

    /**
     * Creates a new instance of BoxController
     */
    public TownlayerController() {
    }

    public String townlayer() {
        listTownlayer.clear();
        listTownlayer.addAll(townlayerFacade.findAll());
        chargeListCountrylayer();
        return "townlayers.xhtml?faces-redirect=true";
    }

    public void prepareCreate() {
        townlayer = new Townlayer();
        townlayer.setIdcountrylayer(new Countrylayer());
    }

    public void chargeListCountrylayer() {
        listCountrylayer.clear();
        listCountrylayer.addAll(countrylayerFacade.findAll());
    }


    public void prepareEdit() {
        townlayer = new Townlayer();
    }

    public String saveTownlayer() {
        try {
            layerTownlayerSaisi = townlayer.getLayer();
            reqCount = townlayerFacade.findCopieDouble(layerTownlayerSaisi);
            if (reqCount >= 1) {
                showMessage = true;
                message = "Désolé! Ce layer existe déjà";
            } else {
                townlayerFacade.create(townlayer); // Creation du townlayer dans la derniere ligne vide
                showMessage = true;
                message = "Enregistrement effectué avec succès";
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return townlayer();
    }

    public String editTownlayer() {
        try {
            townlayerFacade.edit(townlayer);
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return townlayer();
    }

    public String deleteTownlayer() {
        try {
            townlayerFacade.remove(townlayer);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la suppression";
            e.printStackTrace();
        }
        return townlayer();
    }

    public String imprimer() {

        try {
            utils.print("Reports/ListeBox.jasper");


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            return ext.getRequestPathInfo() + "?faces-redirect=true";

        }


    }
    
    public Townlayer getTownlayer() {
        return townlayer;
    }

    public void setTownlayer(Townlayer townlayer) {
        this.townlayer = townlayer;
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

    public String getLayerTownlayerSaisi() {
        return layerTownlayerSaisi;
    }

    public void setLayerTownlayerSaisi(String layerTownlayerSaisi) {
        this.layerTownlayerSaisi = layerTownlayerSaisi;
    }

    public int getReqCount() {
        return reqCount;
    }

    public void setReqCount(int reqCount) {
        this.reqCount = reqCount;
    }

    public List<Townlayer> getListTownlayer() {
        return listTownlayer;
    }

    public void setListTownlayer(List<Townlayer> listTownlayer) {
        this.listTownlayer = listTownlayer;
    }

    public List<Townlayer> getListTownlayerMapped() {
        return listTownlayerMapped;
    }

    public void setListTownlayerMapped(List<Townlayer> listTownlayerMapped) {
        this.listTownlayerMapped = listTownlayerMapped;
    }

    public List<Countrylayer> getListCountrylayer() {
        return listCountrylayer;
    }

    public void setListCountrylayer(List<Countrylayer> listCountrylayer) {
        this.listCountrylayer = listCountrylayer;
    }
}
