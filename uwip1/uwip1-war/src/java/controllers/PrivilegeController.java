
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Utils;
import entities.Privilege;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessions.PrivilegeFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "privilegeController")
@SessionScoped
public class PrivilegeController implements Serializable {

    @Inject
    private PrivilegeFacadeLocal privilegeFacade;
    private List<Privilege> listPrivilege = new ArrayList<Privilege>();
    private Privilege privilege = new Privilege();
    private String message = "";
    private boolean showMessage = false;
    private Utils utils = new Utils();

    /**
     * Creates a new instance of PrivilegeController
     */
    public PrivilegeController() {
    }

    public String privilege() {
        listPrivilege.clear();
        listPrivilege.addAll(privilegeFacade.findAll());
        return "privileges.xhtml?faces-redirect=true";
    }

    public void prepareCreate() {
        privilege = new Privilege();
    }

    public void prepareEdit() {
        privilege = new Privilege();
    }

    public String savePrivilege() {
        try {
            privilegeFacade.create(privilege); // Creation du privilege dans la derniere ligne vide
            showMessage = true;
            message = "Enregistrement effectué avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return privilege();
    }

    public String editPrivilege() {
        try {
            privilegeFacade.edit(privilege);
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return privilege();
    }

    public String deletePrivilege() {
        try {
            privilegeFacade.remove(privilege);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la suppression";
            e.printStackTrace();
        }
        return privilege();
    }

    public String imprimer() {

        try {
            utils.print("Reports/ListePrivilege.jasper");


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            return ext.getRequestPathInfo() + "?faces-redirect=true";

        }


    }

    public List<Privilege> getListPrivilege() {
        return this.listPrivilege;
    }

    public void setListPrivilege(List<Privilege> listprivilege) {
        this.listPrivilege = listprivilege;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
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
