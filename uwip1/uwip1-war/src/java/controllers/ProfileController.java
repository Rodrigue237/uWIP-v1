
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Utils;
import entities.Privilege;
import entities.Profile;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessions.ProfileFacadeLocal;
import sessions.PrivilegeFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "profileController")
@SessionScoped
public class ProfileController implements Serializable {

    @Inject
    private ProfileFacadeLocal profileFacade;
    private PrivilegeFacadeLocal privilegeFacade;
    private List<Profile> listProfile = new ArrayList<Profile>();
    private List<Privilege> listPrivilege = new ArrayList<Privilege>();
    private List<Integer> listOfSelectedPrivileges = new ArrayList<Integer>();
    private Profile profile = new Profile();
    private String message = "";
    private boolean showMessage = false;
    private Utils utils = new Utils();
    private long idprivilegeG = 0;
    private long idprivilegeD = 0;

    /**
     * Creates a new instance of ProfileController
     */
    public ProfileController() {
    }

    public String profile() {
        listProfile.clear();
        listProfile.addAll(profileFacade.findAll());
        chargeListPrivilege();
        return "profiles.xhtml?faces-redirect=true";
    }

    public void prepareCreate() {
        profile = new Profile();
        listOfSelectedPrivileges.clear();
    }

    public void prepareEdit() {
        profile = new Profile();
        //lookAcces();
    }

    public void lookAcces() {
        String config = "";
        //config = profile.getConfigprivilege();
        listOfSelectedPrivileges.clear();
        for (int i = 0; i < config.length(); i = i + 6) {
            if (config.substring(i, i + 6).substring(5, 6).equals("1")) {
                listOfSelectedPrivileges.add(profileFacade.findByKey(config.substring(i, i + 6).substring(0, 5)).getIdprivilegeacces());
            }
        }
    }

    public void chargeListPrivilege() {
        listPrivilege.clear();
        listPrivilege.addAll(profileFacade.findAllPrivilege());
    }

    public String accesPrivilege() {
        String config = "";
        for (int i = 0; i < listPrivilege.size(); i++) {
            boolean trouve = false;
            System.out.println("Valeur 1 de trouvé:" + trouve);

            for (int k = 0; k < listOfSelectedPrivileges.size(); k++) {
                System.out.println("Privilege Gauche:" + listPrivilege.get(i).getIdprivilegeacces());
                System.out.println("Privilege Droite:" + listOfSelectedPrivileges.get(k));
                if (listPrivilege.get(i).getIdprivilegeacces() == listOfSelectedPrivileges.get(k)) {
                    trouve = true;
                    System.out.println("Valeur 2 de trouvé:" + trouve);
                }
            }

            if (trouve == true) {
                config += listPrivilege.get(i).getKey() + "1";
                System.out.println("Valeur config1:" + config);
            } else {
                config += listPrivilege.get(i).getKey() + "0";
                System.out.println("Valeur config2:" + config);
            }

        }
        return config;
    }

    public String saveProfile() {
        try {
            profile.setConfigprivilege(accesPrivilege());
            profileFacade.create(profile); // Creation du profile dans la derniere ligne vide
            showMessage = true;
            message = "Enregistrement effectué avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return profile();
    }

    public String editProfile() {
        try {
            profile.setConfigprivilege(accesPrivilege());
            profileFacade.edit(profile);
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return profile();
    }

    public String deleteProfile() {
        try {
            profileFacade.remove(profile);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la suppression";
            e.printStackTrace();
        }
        return profile();
    }

    public String imprimer() {

        try {
            utils.print("Reports/ListeProfile.jasper");


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            return ext.getRequestPathInfo() + "?faces-redirect=true";

        }


    }

    public List<Profile> getListProfile() {
        return this.listProfile;
    }

    public void setListProfile(List<Profile> listprofile) {
        this.listProfile = listprofile;
    }

    public List<Privilege> getListPrivilege() {
        return listPrivilege;
    }

    public void setListPrivilege(List<Privilege> listPrivilege) {
        this.listPrivilege = listPrivilege;
    }

    public List<Integer> getListOfSelectedPrivileges() {
        return listOfSelectedPrivileges;
    }

    public void setListOfSelectedPrivileges(List<Integer> listOfSelectedPrivileges) {
        this.listOfSelectedPrivileges = listOfSelectedPrivileges;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
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
