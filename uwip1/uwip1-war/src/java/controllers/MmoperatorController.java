
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Utils;
import entities.Mmoperator;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessions.MmoperatorFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "mmoperatorController")
@SessionScoped
public class MmoperatorController implements Serializable {

    @Inject
    private MmoperatorFacadeLocal mmoperatorFacade;
    private List<Mmoperator> listMmoperator = new ArrayList<Mmoperator>();
    private List<Mmoperator> listMmoperators = new ArrayList<Mmoperator>();
    private Mmoperator mmoperator = new Mmoperator();
    private String message = "";
    private String codeToCount = "";
    private int reqCount = 0;
    private boolean showMessage = false;
    private Utils utils = new Utils();

    /**
     * Creates a new instance of MmoperatorController
     */
    public MmoperatorController() {
    }

    public String mmoperator() {
        listMmoperator.clear();
        listMmoperator.addAll(mmoperatorFacade.findAll());
        //chargeListOperateurDispos();
        return "mmoperators.xhtml?faces-redirect=true";
    }

    public void chargeListOperateurDispos() {
        listMmoperators.clear();
        listMmoperators.addAll(mmoperatorFacade.findAll());
    }

    public void prepareCreate() {
        mmoperator = new Mmoperator();
    }

    public void prepareEdit() {
        mmoperator = new Mmoperator();
    }

    public String saveMmoperator() {
        try {
            codeToCount = mmoperator.getCode();
            reqCount = mmoperatorFacade.findCopieDouble(codeToCount);
            if (reqCount >= 1) {
                showMessage = true;
                message = "Désolé! Cet opérateur existe déjà";
            } else {
                mmoperatorFacade.create(mmoperator); // Creation du mmoperator dans la derniere ligne vide
                showMessage = true;
                message = "Enregistrement effectué avec succès";
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return mmoperator();
    }

    public String editMmoperator() {
        try {
            mmoperatorFacade.edit(mmoperator);
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return mmoperator();
    }

    public String deleteMmoperator() {
        try {
            mmoperatorFacade.remove(mmoperator);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la suppression";
            e.printStackTrace();
        }
        return mmoperator();
    }

    public String imprimer() {

        try {
            utils.print("Reports/ListeMmoperator.jasper");


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            return ext.getRequestPathInfo() + "?faces-redirect=true";

        }


    }

    public List<Mmoperator> getListMmoperator() {
        return this.listMmoperator;
    }

    public void setListMmoperator(List<Mmoperator> listmmoperator) {
        this.listMmoperator = listmmoperator;
    }

    public List<Mmoperator> getListMmoperators() {
        return listMmoperators;
    }

    public void setListMmoperators(List<Mmoperator> listMmoperators) {
        this.listMmoperators = listMmoperators;
    }

    public Mmoperator getMmoperator() {
        return mmoperator;
    }

    public void setMmoperator(Mmoperator mmoperator) {
        this.mmoperator = mmoperator;
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
