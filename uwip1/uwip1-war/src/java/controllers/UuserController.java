
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.ShaHash;
import classes.Utils;
import entities.Organization;
import entities.Profile;
import entities.Uuser;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessions.OrganizationFacadeLocal;
import sessions.ProfileFacadeLocal;
import sessions.UuserFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "uuserController")
@SessionScoped
public class UuserController implements Serializable {

    @Inject
    private UuserFacadeLocal uuserFacade;
    private List<Uuser> listUuser = new ArrayList<Uuser>();
    private List<Uuser> listUuser2 = new ArrayList<Uuser>();
    private Uuser uuser = new Uuser();
    @Inject
    private ProfileFacadeLocal profileFacade;
    private List<Profile> listProfile = new ArrayList<Profile>();
    private List<Profile> listProfile2 = new ArrayList<Profile>();
    @Inject
    private OrganizationFacadeLocal organizationFacade;
    private List<Organization> listOrganization = new ArrayList<Organization>();
    private String message = "";
    private String profilSA = "Super-Admin";
    private String profilA = "Admin";
    private String connectedProfil = "";
    private String fordeleteProfil = "";
    private int reqCount = 0;
    private String loginToCount = "";
    private boolean showMessage = false;
    private Utils utils = new Utils();
    private SessionController mySession = new SessionController();

    /**
     * Creates a new instance of UuserController
     */
    public UuserController() {
    }

    public String uuser() {
        listUuser.clear();
        listUuser.addAll(uuserFacade.findAll());
        chargeListOrganization();
        chargeListProfile();
        //connectedProfil = mySession.getProfilOnline();
        //System.out.println("Profil supprimeur:" + connectedProfil);
        return "uusers.xhtml?faces-redirect=true";
    }

    public String uuser2() {
        listUuser2.clear();
        listUuser2.addAll(uuserFacade.findAll2(profilSA));
        chargeListOrganization();
        chargeListProfile2();
        //connectedProfil = mySession.getProfilOnline();
        //System.out.println("Profil supprimeur:" + connectedProfil);
        return "uusers2.xhtml?faces-redirect=true";
    }

    public void prepareCreate() {
        uuser = new Uuser();
        uuser.setIdprofile(new Profile());
        uuser.setIdorganization(new Organization());
    }

    public void chargeListProfile() {
        listProfile.clear();
        listProfile.addAll(profileFacade.findAll());
    }

    public void chargeListProfile2() {
        listProfile.clear();
        listProfile.addAll(profileFacade.findAll2(profilSA));
    }

    public void checkConnectedProfil() {
        connectedProfil = mySession.getConnectedUser().getIdprofile().getName();
        System.out.println("Profil en ligne:" + connectedProfil);
    }

    public void chargeListOrganization() {
        listOrganization.clear();
        listOrganization.addAll(organizationFacade.findAll());
    }

    public void prepareEdit() {
        uuser = new Uuser();
    }

    public String saveUuser() {
        try {
            loginToCount = uuser.getLogin();
            System.out.println("Login:" + loginToCount);
            reqCount = uuserFacade.findCopieDouble(loginToCount);
            System.out.println("Existant:" + reqCount);
            if (reqCount >= 1) {
                showMessage = true;
                message = "Désolé! Ce login existe déjà";
            } else {
                uuser.setMdp(new ShaHash().hash(uuser.getMdp()));
                uuserFacade.create(uuser); // Creation du uuser dans la derniere ligne vide
                showMessage = true;
                message = "Enregistrement effectué avec succès";
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return uuser();
    }

    public String saveUuser2() {
        try {
            loginToCount = uuser.getLogin();
            System.out.println("Login:" + loginToCount);
            reqCount = uuserFacade.findCopieDouble(loginToCount);
            System.out.println("Existant:" + reqCount);
            if (reqCount >= 1) {
                showMessage = true;
                message = "Désolé! Ce login existe déjà";
            } else {
                uuser.setMdp(new ShaHash().hash(uuser.getMdp()));
                uuserFacade.create(uuser); // Creation du uuser dans la derniere ligne vide
                showMessage = true;
                message = "Enregistrement effectué avec succès";
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return uuser2();
    }

    public String editUuser() {
        try {
            uuserFacade.edit(uuser);
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return uuser();
    }

    public String editUuser2() {
        try {
            uuserFacade.edit(uuser);
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return uuser2();
    }

    public String deleteUuser() {
        try {
            uuserFacade.remove(uuser);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la suppression";
            e.printStackTrace();
        }
        return uuser();
    }

    public String deleteUuser2() {
        try {
            uuserFacade.remove(uuser);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la suppression";
            e.printStackTrace();
        }
        return uuser2();
    }

    public void droitSuppression() {
        if (connectedProfil == profilSA) {
            uuserFacade.remove(uuser);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } else if (connectedProfil == profilA || fordeleteProfil != profilSA) {
            uuserFacade.remove(uuser);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } else {
            showMessage = true;
            message = "Vous ne pouvez supprimer cet utilisateur";
        }

    }

    public String imprimer() {

        try {
            utils.print("Reports/ListeUtilisateur.jasper");


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            return ext.getRequestPathInfo() + "?faces-redirect=true";

        }


    }

    public List<Uuser> getListUuser() {
        return this.listUuser;
    }

    public void setListUuser(List<Uuser> listuuser) {
        this.listUuser = listuuser;
    }

    public List<Uuser> getListUuser2() {
        return listUuser2;
    }

    public void setListUuser2(List<Uuser> listUuser2) {
        this.listUuser2 = listUuser2;
    }

    public Uuser getUuser() {
        return uuser;
    }

    public void setUuser(Uuser uuser) {
        this.uuser = uuser;
    }

    public SessionController getMySession() {
        return mySession;
    }

    public void setMySession(SessionController mySession) {
        this.mySession = mySession;
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

    public String getConnectedProfil() {
        return connectedProfil;
    }

    public void setConnectedProfil(String connectedProfil) {
        this.connectedProfil = connectedProfil;
    }

    public List<Profile> getListProfile() {
        return listProfile;
    }

    public void setListProfile(List<Profile> listProfile) {
        this.listProfile = listProfile;
    }

    public List<Profile> getListProfile2() {
        return listProfile2;
    }

    public void setListProfile2(List<Profile> listProfile2) {
        this.listProfile2 = listProfile2;
    }

    public List<Organization> getListOrganization() {
        return listOrganization;
    }

    public void setListOrganization(List<Organization> listOrganization) {
        this.listOrganization = listOrganization;
    }
}
