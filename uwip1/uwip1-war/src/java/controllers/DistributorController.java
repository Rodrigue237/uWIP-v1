
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Utils;
import entities.Distributor;
import entities.Client;
import entities.Organization;
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
import sessions.DistributorFacadeLocal;
import sessions.OrganizationFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "distributorController")
@SessionScoped
public class DistributorController implements Serializable {

    @Inject
    private DistributorFacadeLocal distributorFacade;
    private List<Distributor> listDistributor = new ArrayList<Distributor>();
    private List<Distributor> listDistributorMapped = new ArrayList<Distributor>();
    private Distributor distributor = new Distributor();
    @Inject
    private OrganizationFacadeLocal organizationFacade;
    private List<Organization> listOrganization = new ArrayList<Organization>();
    private int numDistributorSaisi = 0;
    private int reqCount = 0;
    private String message = "";
    private boolean showMessage = false;
    private Utils utils = new Utils();

    /**
     * Creates a new instance of BoxController
     */
    public DistributorController() {
    }

    public String distributor() {
        listDistributor.clear();
        listDistributor.addAll(distributorFacade.findAll());
        chargeListOrganization();
        chargeListMappedDistributor();
        return "distributors.xhtml?faces-redirect=true";
    }

    public void prepareCreate() {
        distributor = new Distributor();
        distributor.setIdorganization(new Organization());
    }

    public void chargeListOrganization() {
        listOrganization.clear();
        listOrganization.addAll(organizationFacade.findAll());
    }
    
    public void chargeListMappedDistributor() {
        listDistributorMapped.clear();
        listDistributorMapped.addAll(distributorFacade.findByMapped());
    }


    public void prepareEdit() {
        distributor = new Distributor();
    }
    
    public void initialisationDistributor() {
        distributor.setTotalcodesolde(BigInteger.ZERO);
        distributor.setTotaltimesolde(BigInteger.ZERO);
        distributor.setMarquerstatus(1);
    }

    public String saveDistributor() {
        try {
            initialisationDistributor();
            numDistributorSaisi = distributor.getDistribuniqnumber();
            reqCount = distributorFacade.findCopieDouble(numDistributorSaisi);
            if (reqCount >= 1) {
                showMessage = true;
                message = "Désolé! Ce distributeur existe déjà";
            } else {
                distributorFacade.create(distributor); // Creation du distributor dans la derniere ligne vide
                showMessage = true;
                message = "Enregistrement effectué avec succès";
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return distributor();
    }

    public String editDistributor() {
        try {
            distributorFacade.edit(distributor);
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return distributor();
    }

    public String deleteDistributor() {
        try {
            distributorFacade.remove(distributor);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la suppression";
            e.printStackTrace();
        }
        return distributor();
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

    public String imprimer2() {

        try {
            utils.print("Reports/ListeBoxLibre.jasper");


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            return ext.getRequestPathInfo() + "?faces-redirect=true";

        }


    }
    
    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
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

    public int getNumDistributorSaisi() {
        return numDistributorSaisi;
    }

    public void setNumDistributorSaisi(int numDistributorSaisi) {
        this.numDistributorSaisi = numDistributorSaisi;
    }

    public int getReqCount() {
        return reqCount;
    }

    public void setReqCount(int reqCount) {
        this.reqCount = reqCount;
    }

    public List<Distributor> getListDistributor() {
        return listDistributor;
    }

    public void setListDistributor(List<Distributor> listDistributor) {
        this.listDistributor = listDistributor;
    }

    public List<Distributor> getListDistributorMapped() {
        return listDistributorMapped;
    }

    public void setListDistributorMapped(List<Distributor> listDistributorMapped) {
        this.listDistributorMapped = listDistributorMapped;
    }

    public List<Organization> getListOrganization() {
        return listOrganization;
    }

    public void setListOrganization(List<Organization> listOrganization) {
        this.listOrganization = listOrganization;
    }
}
