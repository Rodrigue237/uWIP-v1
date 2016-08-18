/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.ShaHash;
import entities.Profile;
import entities.Uuser;
import entities.Box;
import entities.Distributor;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import sessions.UuserFacadeLocal;
import sessions.DistributorFacadeLocal;
import sessions.BoxFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "sessionController")
@SessionScoped
public class SessionController implements Serializable {

    @Inject
    private UuserFacadeLocal uuserFacade;
    private DistributorFacadeLocal distributorFacade;
    private BoxFacadeLocal boxFacade;
    private List<Distributor> listDistributorMapped = new ArrayList<Distributor>();
    private List<Box> listBoxMapped = new ArrayList<Box>();
    private String message = "";
    private boolean showMessage = false;
    private String language = "fr";
    private Uuser connectedUser = new Uuser();
    private String login = "";
    private String mdp = "";
    private String profilOnline = "a";
    private boolean conected = false;
    private boolean porga = false;
    private boolean pusra = false;
    private boolean pusrb = false;
    private boolean pclia = false;
    private boolean pclib = false;
    private boolean pboxa = false;
    private boolean pboxb = false;
    private boolean pnota = false;
    private boolean pnotb = false;
    private boolean pdisa = false;
    private boolean pdisb = false;
    private boolean pcoda = false;
    private boolean pcodb = false;
    private boolean pmtna = false;
    private boolean pmtnb = false;
    private boolean pwins = false;
    private boolean pcred = false;
    private boolean ptrto = false;
    private boolean pprod = false;
    private boolean pterm = false;

    /**
     * Creates a new instance of SessionController
     */
    public SessionController() {
    }

    public void chargeListMappedDistributor() {
        listDistributorMapped.clear();
        listDistributorMapped.addAll(distributorFacade.findByMapped());
    }

    public void chargeListBoxMapped() {
        listBoxMapped.clear();
        listBoxMapped.addAll(boxFacade.findByMapped());
    }

    public String accueil() {
        return "home.xhtml?faces-redirect=true";
    }

    public String authentification() {
        try {
            connectedUser = uuserFacade.connexion(login, new ShaHash().hash(mdp));
            if (connectedUser == null) {
                conected = false;
                showMessage = true;
                message = "Login ou mot de passe incorrect. Verifiez...";
            } else {
                conected = true;
                profilOnline = ((connectedUser.getIdprofile()).getName()).toString();
                System.out.println("Profil online:" + profilOnline);
                droitAcces();
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("current_user", connectedUser);
                showMessage = true;
                message = "Bienvenue";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "home.xhtml?faces-redirect=true";
        }
    }

    public String openSite() {
        return "www.upowa.org";
    }

    //**
    public void droitAcces() {
        String configprivilege = "";
        if (!connectedUser.getLogin().isEmpty()) {
            //configprivilege = ((List<Profile>) connectedUser.getIdprofile()).get(0).getConfigprivilege();
            //System.out.println("Config Avant:"+(connectedUser.getIdprofile()).getConfigprivilege());
            configprivilege = ((connectedUser.getIdprofile()).getConfigprivilege()).toString();
            Map<String, String> mapConfigprivilege = new HashMap<String, String>();
            for (int i = 0; i < configprivilege.length(); i = i + 6) {
                mapConfigprivilege.put(configprivilege.substring(i, i + 6).substring(0, 5), configprivilege.substring(i, i + 6).substring(5, 6));
            }
            for (int i = 0; i < mapConfigprivilege.size(); i++) {
                if (mapConfigprivilege.get("porga").equals("1")) {
                    porga = true;
                } else {
                    porga = false;
                }
                if (mapConfigprivilege.get("pusra").equals("1")) {
                    pusra = true;
                } else {
                    pusra = false;
                }
                if (mapConfigprivilege.get("pusrb").equals("1")) {
                    pusrb = true;
                } else {
                    pusrb = false;
                }
                if (mapConfigprivilege.get("pclia").equals("1")) {
                    pclia = true;
                } else {
                    pclia = false;
                }
                if (mapConfigprivilege.get("pclib").equals("1")) {
                    pclib = true;
                } else {
                    pclib = false;
                }
                if (mapConfigprivilege.get("pboxa").equals("1")) {
                    pboxa = true;
                } else {
                    pboxa = false;
                }
                if (mapConfigprivilege.get("pboxb").equals("1")) {
                    pboxb = true;
                } else {
                    pboxb = false;
                }
                if (mapConfigprivilege.get("pnota").equals("1")) {
                    pnota = true;
                } else {
                    pnota = false;
                }
                if (mapConfigprivilege.get("pnotb").equals("1")) {
                    pnotb = true;
                } else {
                    pnotb = false;
                }
                if (mapConfigprivilege.get("pdisa").equals("1")) {
                    pdisa = true;
                } else {
                    pdisa = false;
                }
                if (mapConfigprivilege.get("pdisb").equals("1")) {
                    pdisb = true;
                } else {
                    pdisb = false;
                }
                if (mapConfigprivilege.get("pcoda").equals("1")) {
                    pcoda = true;
                } else {
                    pcoda = false;
                }
                if (mapConfigprivilege.get("pcodb").equals("1")) {
                    pcodb = true;
                } else {
                    pcodb = false;
                }
                if (mapConfigprivilege.get("pmtna").equals("1")) {
                    pmtna = true;
                } else {
                    pmtna = false;
                }
                if (mapConfigprivilege.get("pmtnb").equals("1")) {
                    pmtnb = true;
                } else {
                    pmtnb = false;
                }
                if (mapConfigprivilege.get("pwins").equals("1")) {
                    pwins = true;
                } else {
                    pwins = false;
                }
                if (mapConfigprivilege.get("pcred").equals("1")) {
                    pcred = true;
                } else {
                    pcred = false;
                }
                if (mapConfigprivilege.get("ptrto").equals("1")) {
                    ptrto = true;
                } else {
                    ptrto = false;
                }
                if (mapConfigprivilege.get("pprod").equals("1")) {
                    pprod = true;
                } else {
                    pprod = false;
                }
                if (mapConfigprivilege.get("pterm").equals("1")) {
                    pterm = true;
                } else {
                    pterm = false;
                }
            }
        }
    }
    //**/

    public String deconnexion() {
        conected = false;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("current_user");
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
        return "home.xhtml?faces-redirect=true";
    }

    public Uuser getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(Uuser connectedUser) {
        this.connectedUser = connectedUser;
    }

    public void watcher() {
        try {
            if (!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("current_user")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml?faces-redirect=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getProfilOnline() {
        profilOnline = ((connectedUser.getIdprofile()).getName()).toString();
        return profilOnline;
    }

    public void setProfilOnline(String profilOnline) {
        this.profilOnline = profilOnline;
    }

    public boolean isConected() {
        return conected;
    }

    public void setConected(boolean conected) {
        this.conected = conected;
    }

    public UuserFacadeLocal getUuserFacade() {
        return uuserFacade;
    }

    public void setUuserFacade(UuserFacadeLocal uuserFacade) {
        this.uuserFacade = uuserFacade;
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

    //Accesseur des privileges
    public boolean isPorga() {
        return porga;
    }

    public void setPorga(boolean porga) {
        this.porga = porga;
    }

    public boolean isPusra() {
        return pusra;
    }

    public void setPusra(boolean pusra) {
        this.pusra = pusra;
    }

    public boolean isPusrb() {
        return pusrb;
    }

    public void setPusrb(boolean pusrb) {
        this.pusrb = pusrb;
    }

    public boolean isPclia() {
        return pclia;
    }

    public void setPclia(boolean pclia) {
        this.pclia = pclia;
    }

    public boolean isPclib() {
        return pclib;
    }

    public void setPclib(boolean pclib) {
        this.pclib = pclib;
    }

    public boolean isPboxa() {
        return pboxa;
    }

    public void setPboxa(boolean pboxa) {
        this.pboxa = pboxa;
    }

    public boolean isPboxb() {
        return pboxb;
    }

    public void setPboxb(boolean pboxb) {
        this.pboxb = pboxb;
    }

    public boolean isPnota() {
        return pnota;
    }

    public void setPnota(boolean pnota) {
        this.pnota = pnota;
    }

    public boolean isPnotb() {
        return pnotb;
    }

    public void setPnotb(boolean pnotb) {
        this.pnotb = pnotb;
    }

    public boolean isPdisa() {
        return pdisa;
    }

    public void setPdisa(boolean pdisa) {
        this.pdisa = pdisa;
    }

    public boolean isPdisb() {
        return pdisb;
    }

    public void setPdisb(boolean pdisb) {
        this.pdisb = pdisb;
    }

    public boolean isPcoda() {
        return pcoda;
    }

    public void setPcoda(boolean pcoda) {
        this.pcoda = pcoda;
    }

    public boolean isPcodb() {
        return pcodb;
    }

    public void setPcodb(boolean pcodb) {
        this.pcodb = pcodb;
    }

    public boolean isPmtna() {
        return pmtna;
    }

    public void setPmtna(boolean pmtna) {
        this.pmtna = pmtna;
    }

    public boolean isPmtnb() {
        return pmtnb;
    }

    public void setPmtnb(boolean pmtnb) {
        this.pmtnb = pmtnb;
    }

    public DistributorFacadeLocal getDistributorFacade() {
        return distributorFacade;
    }

    public void setDistributorFacade(DistributorFacadeLocal distributorFacade) {
        this.distributorFacade = distributorFacade;
    }

    public BoxFacadeLocal getBoxFacade() {
        return boxFacade;
    }

    public void setBoxFacade(BoxFacadeLocal boxFacade) {
        this.boxFacade = boxFacade;
    }

    public boolean isPwins() {
        return pwins;
    }

    public void setPwins(boolean pwins) {
        this.pwins = pwins;
    }

    public boolean isPcred() {
        return pcred;
    }

    public void setPcred(boolean pcred) {
        this.pcred = pcred;
    }

    public boolean isPtrto() {
        return ptrto;
    }

    public void setPtrto(boolean ptrto) {
        this.ptrto = ptrto;
    }

    public boolean isPprod() {
        return pprod;
    }

    public void setPprod(boolean pprod) {
        this.pprod = pprod;
    }

    public boolean isPterm() {
        return pterm;
    }

    public void setPterm(boolean pterm) {
        this.pterm = pterm;
    }

    public void switchFr() {
        language = "fr";
    }

    public void switchEn() {
        language = "en";
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Distributor> getListDistributorMapped() {
        return listDistributorMapped;
    }

    public void setListDistributorMapped(List<Distributor> listDistributorMapped) {
        this.listDistributorMapped = listDistributorMapped;
    }

    public List<Box> getListBoxMapped() {
        return listBoxMapped;
    }

    public void setListBoxMapped(List<Box> listBoxMapped) {
        this.listBoxMapped = listBoxMapped;
    }
}
