
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Utils;
import entities.Organization;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessions.OrganizationFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "organizationController")
@SessionScoped
public class OrganizationController implements Serializable {

    @Inject
    private OrganizationFacadeLocal organizationFacade;
    private List<Organization> listOrganization = new ArrayList<Organization>();
    private Organization organization = new Organization();
    private String message = "";
    private String nameToCount = "";
    private int reqCount = 0;
    private boolean showMessage = false;
    private Utils utils = new Utils();

    /**
     * Creates a new instance of OrganizationController
     */
    public OrganizationController() {
    }

    public String organization() {
        listOrganization.clear();
        listOrganization.addAll(organizationFacade.findAll());
        return "organizations.xhtml?faces-redirect=true";
    }

    public void prepareCreate() {
        organization = new Organization();
    }

    public void prepareEdit() {
        organization = new Organization();
    }

    public String saveOrganization() {
        try {
            nameToCount = organization.getName();
            reqCount = organizationFacade.findCopieDouble(nameToCount);
            if (reqCount >= 1) {
                showMessage = true;
                message = "Désolé! Cette organisaion existe déjà";
            } else {
                organizationFacade.create(organization); // Creation du organization dans la derniere ligne vide
                showMessage = true;
                message = "Enregistrement effectué avec succès";
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return organization();
    }

    public String editOrganization() {
        try {
            organizationFacade.edit(organization);
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return organization();
    }

    public String deleteOrganization() {
        try {
            System.out.println("Orga:" + organization.getName());
            organizationFacade.remove(organization);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la suppression";
            e.printStackTrace();
        }
        return organization();
    }

    public String imprimer() {

        try {
            utils.print("Reports/ListeOrganization.jasper");


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            return ext.getRequestPathInfo() + "?faces-redirect=true";

        }


    }

    public List<Organization> getListOrganization() {
        return this.listOrganization;
    }

    public void setListOrganization(List<Organization> listorganization) {
        this.listOrganization = listorganization;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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
