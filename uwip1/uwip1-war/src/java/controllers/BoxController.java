
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Utils;
import entities.Box;
import entities.Client;
import entities.Hardvers;
import entities.Organization;
import entities.Softvers;
import entities.Townlayer;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessions.BoxFacadeLocal;
import sessions.ClientFacadeLocal;
import sessions.ContractFacadeLocal;
import sessions.HardversFacadeLocal;
import sessions.OrganizationFacadeLocal;
import sessions.SoftversFacadeLocal;
import sessions.TownlayerFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "boxController")
@SessionScoped
public class BoxController implements Serializable {

    @Inject
    private BoxFacadeLocal boxFacade;
    private List<Box> listBox = new ArrayList<Box>();
    private List<Box> listBoxMaintenance = new ArrayList<Box>();
    private List<Box> listBoxIndispo = new ArrayList<Box>();
    private List<Box> listBoxMapped = new ArrayList<Box>();
    private List<Box> listBoxLibre = new ArrayList<Box>();
    private Box box = new Box();
    @Inject
    private ContractFacadeLocal contractFacade;
    @Inject
    private ClientFacadeLocal clientFacade;
    private List<Client> listClient = new ArrayList<Client>();
    private List<Client> listClient2 = new ArrayList<Client>();
    @Inject
    private TownlayerFacadeLocal townlayerFacade;
    private List<Townlayer> listTownlayer = new ArrayList<Townlayer>();
    @Inject
    private OrganizationFacadeLocal organizationFacade;
    private List<Organization> listOrganization = new ArrayList<Organization>();
    @Inject
    private HardversFacadeLocal hardversFacade;
    private List<Hardvers> listHardvers = new ArrayList<Hardvers>();
    private List<Hardvers> listHardversion = new ArrayList<Hardvers>();
    @Inject
    private SoftversFacadeLocal softversFacade;
    private List<Softvers> listSoftvers = new ArrayList<Softvers>();
    private List<Softvers> listSoftversion = new ArrayList<Softvers>();
    private String numBoxSerieSaisi = "";
    private int reqCount = 0;
    private int reqLibererBox = 0;
    private int reqLibererClient = 0;
    private int reqRemplaceBox = 0;
    private int reqUpdatedesinstallDate = 0;
    private long idboxinstal = 0;
    private String message = "";
    private String styleMarquer1 = "clientMarq";
    private String styleAtelier = "A1.png";
    private String magDispo = "A1.png";
    private String magHS = "A2.png";
    private int numeroBox = 0;
    private int numboxOut = 0;
    private int idlastContractOut = 0;
    private double latitudeOut = 1.1;
    private double longitudeOut = 1.1;
    private String countryOut = "";
    private Date firstinstaldateOut;
    private Date lastinstaldateOut;
    private Date lasttransactOut;
    private BigInteger lasttimecodevalueOut;
    private BigInteger totaltimepaidOut;
    private BigInteger estimlefttimepaidOut;
    private int marquerstatusOut = 0;
    private long idlastcontratBox = 0;
    private String userofsystemtOut = "";
    private Date estimatedenddateOut;
    private String marquerstyleOut = "";
    private String atelierOut = "";
    private String desactivStatus = "disabled";
    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Date desinstallDate;
    private Date newlastinstallDate;
    private long idtownlayerOut = 1;
    private long idorganizationOut = 1;
    private long idclientOut = 1;
    private long idboxeIn = 1;
    private int totatInstallIn = 0;
    private String boxActivated = "A5.png";
    private String finalDestination = "";
    private boolean showMessage = false;
    private Utils utils = new Utils();

    /**
     * Creates a new instance of BoxController
     */
    public BoxController() {
    }

    public String box() {
        listBox.clear();
        listBox.addAll(boxFacade.findAll());
        chargeListOrganization();
        chargeListClient();
        chargeListSoftvers();
        chargeListHardvers();
        chargeListTownlayer();
        listBoxMapped.clear();
        listBoxMapped.addAll(boxFacade.findByMapped());
        listHardversion.clear();
        listHardversion.addAll(hardversFacade.findAllHard());
        listSoftversion.clear();
        listSoftversion.addAll(softversFacade.findAllSoft());
        return "boxs.xhtml?faces-redirect=true";
    }

    public String maintenance() {
        listBox.clear();
        listBox.addAll(boxFacade.findAll());
        chargeListOrganization();
        chargeListClient();
        chargeListClient2();
        chargeListSoftvers();
        chargeListHardvers();
        chargeListBoxLibre();
        listBoxMaintenance.clear();
        listBoxMaintenance.addAll(boxFacade.findByMaintenance());
        return "maintenances.xhtml?faces-redirect=true";
    }

    public String boxIndispo() {
        listBox.clear();
        listBox.addAll(boxFacade.findAll());
        chargeListOrganization();
        chargeListClient();
        chargeListClient2();
        chargeListSoftvers();
        chargeListHardvers();
        listBoxMaintenance.clear();
        listBoxMaintenance.addAll(boxFacade.findByMaintenance());
        listBoxIndispo.clear();
        listBoxIndispo.addAll(boxFacade.findByIndispo(magDispo));
        return "indisponibles.xhtml?faces-redirect=true";
    }

    public void prepareCreate() {
        box = new Box();
        box.setIdclient(new Client());
        box.setIdorganization(new Organization());
        box.setIdtownlayer(new Townlayer());
        box.setIdhardvers(new Hardvers());
        box.setIdsoftvers(new Softvers());
        chargeListSoftvers();
        chargeListHardvers();
    }

    public void chargeListClient() {
        listClient.clear();
        listClient.addAll(clientFacade.findAll());
    }

    public void chargeListClient2() {
        listClient2.clear();
        listClient2.addAll(clientFacade.findFirstClient());
    }

    public void chargeListTownlayer() {
        listTownlayer.clear();
        listTownlayer.addAll(townlayerFacade.findAll());
    }

    public void chargeListOrganization() {
        listOrganization.clear();
        listOrganization.addAll(organizationFacade.findAll());
    }
    
    public void chargeListHardvers() {
        listHardvers.clear();
        listHardvers.addAll(hardversFacade.findAll());
    }
    
    public void chargeListSoftvers() {
        listSoftvers.clear();
        listSoftvers.addAll(softversFacade.findAll());
    }

    public void chargeListBoxLibre() {
        listBoxLibre.clear();
        listBoxLibre.addAll(boxFacade.findByLibre());
    }

    public void initialisationBox() throws ParseException {
        box.setMarquerstatus(0);
        box.setLatitude(5.49024);
        box.setLongitude(10.38208);
        box.setMaintenancestatus(1);
        box.setKpi1(4);
        box.setKpi2(9);
        box.setKpi3(38);
        box.setNumbox(0);
        box.setTotalinstall(0);
        box.setGenekeyactiv(37);
        box.setEstimlefttimepaid(BigInteger.ZERO);
        box.setLasttimecodevalue(BigInteger.ZERO);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh':'mm ");//Definition du format de date
        String sDate = dateFormat.format(new Date());
        box.setEstimatedenddate(dateFormat.parse(sDate));
        //box.setFirstinstaldate(dateFormat.parse(sDate));
        //box.setLastinstaldate(dateFormat.parse(sDate));
        box.setLastmaintenance(dateFormat.parse(sDate));
        box.setLasttransact(dateFormat.parse(sDate));
        box.setTotaltimepaid(BigInteger.ZERO);
        box.setMarquerstyle(styleMarquer1); 
        box.setAtelier(styleAtelier);
    }

    public void libererBox() throws ParseException {
        idboxinstal = this.box.getIdboxe();
        System.out.println("IdBox à desinstaller:" + idboxinstal);
        idlastcontratBox = (long)this.box.getIdlastcontract();
        System.out.println("IdContrat box desinstallé:" + idlastcontratBox);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh':'mm ");//Definition du format de date
        String sDate = dateFormat.format(new Date());
        desinstallDate = dateFormat.parse(sDate);
        System.out.println("Date de desinstallation:" + desinstallDate);
        reqLibererBox = boxFacade.findToChangeStMarquer1(idboxinstal, magHS, numeroBox);
        reqLibererClient = boxFacade.findToDesactivateClientBox(idboxinstal, this.box.getIdclient());
        reqUpdatedesinstallDate = contractFacade.updateDesinstallDate(desinstallDate, desactivStatus, idlastcontratBox);
        System.out.println("Nbre de update date desinstalle:" + reqUpdatedesinstallDate);
    }

    public void remplaceBox() throws ParseException {
        idboxinstal = this.box.getIdboxe();// Recuperation Id Box Sortante
        numboxOut = this.box.getNumbox();// Debut Recuperation des champ de la Box Sortante
        latitudeOut = this.box.getLatitude();
        longitudeOut = this.box.getLongitude();
        countryOut = this.box.getCountry();
        firstinstaldateOut = this.box.getFirstinstaldate();
        lastinstaldateOut = this.box.getLastinstaldate();
        lasttransactOut = this.box.getLasttransact();
        lasttimecodevalueOut = this.box.getLasttimecodevalue();
        totaltimepaidOut = this.box.getTotaltimepaid();
        estimlefttimepaidOut = this.box.getEstimlefttimepaid();
        marquerstatusOut = this.box.getMarquerstatus();
        userofsystemtOut = this.box.getUserofsystem();
        estimatedenddateOut = this.box.getEstimatedenddate();
        marquerstyleOut = this.box.getMarquerstyle();
        idlastContractOut = this.box.getIdlastcontract();
        atelierOut = this.box.getAtelier();// Fin Recuperation des champ de la Box Sortante
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh':'mm ");//Definition du format de date
        String sDate = dateFormat.format(new Date());
        newlastinstallDate = dateFormat.parse(sDate);
        totatInstallIn = box.getTotalinstall();
        totatInstallIn++;
        // Remplacement de la box
        reqRemplaceBox = boxFacade.remplaceBox(numboxOut, idlastContractOut, totatInstallIn, latitudeOut, longitudeOut, countryOut, newlastinstallDate, lasttransactOut, lasttimecodevalueOut, totaltimepaidOut, estimlefttimepaidOut, marquerstatusOut, userofsystemtOut, estimatedenddateOut, marquerstyleOut, atelierOut, this.box.getIdtownlayer(), this.box.getIdorganization(), this.box.getIdclient(), idboxeIn);        
        // Desactivation de la box sortante
        reqLibererBox = boxFacade.findToChangeStMarquer1(idboxinstal, magHS, numeroBox);
    }

    public void prepareEdit() {
        box = new Box();
        box.setIdclient(new Client());
        box.setIdorganization(new Organization());
        box.setIdtownlayer(new Townlayer());
        box.setIdhardvers(new Hardvers());
        box.setIdsoftvers(new Softvers());
        chargeListSoftvers();
        chargeListHardvers();
    }

    public String saveBox() {
        try {
            initialisationBox();
            numBoxSerieSaisi = box.getRefbox();
            reqCount = boxFacade.findCopieDouble(numBoxSerieSaisi);
            if (reqCount >= 1) {
                showMessage = true;
                message = "Désolé! Cette mOk box existe déjà";
            } else {
                boxFacade.create(box); // Creation du box dans la derniere ligne vide
                showMessage = true;
                message = "Enregistrement effectué avec succès";
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return box();
    }

    public String editBox() {
        try {
            libererBox();
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return box();
    }

    public String editBox2() {
        try {
            remplaceBox();
            showMessage = true;
            message = "Box remplacée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de remplacement";
            e.printStackTrace();
        }
        return maintenance();
    }
    
    public String editBoxMAJLogiciel() {
        try {
            boxFacade.edit(box);
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return maintenance();
    }

    public String editBox3() {
        try {
            finalDestination = box.getAtelier();
            if (finalDestination == magDispo) {
                box.setMarquerstatus(0);
                boxFacade.edit(box);
                showMessage = true;
                message = "Box déplacée avec succès";
            } else {
                boxFacade.edit(box);
                showMessage = true;
                message = "Box déplacée avec succès";
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec du déplacement";
            e.printStackTrace();
        }
        return boxIndispo();
    }

    public String deleteBox() {
        try {
            boxFacade.remove(box);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la suppression";
            e.printStackTrace();
        }
        return box();
    }

    public String desinstalleBox() {
        try {
            libererBox();
            showMessage = true;
            message = "Désinstallation effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la désinstallation";
            e.printStackTrace();
        }
        return maintenance();
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

    public void updateMarqueStatus() {
        idboxinstal = box.getIdboxe();
        reqLibererBox = boxFacade.findToChangeStMarquer1(idboxinstal, magHS, numeroBox);
        reqLibererClient = boxFacade.findToDesactivateClientBox(idboxinstal, this.box.getIdclient());
    }

    public List<Box> getListBox() {
        return this.listBox;
    }

    public void setListBox(List<Box> listbox) {
        this.listBox = listbox;
    }

    public List<Box> getListBoxMapped() {
        return listBoxMapped;
    }

    public void setListBoxMapped(List<Box> listBoxMapped) {
        this.listBoxMapped = listBoxMapped;
    }

    public List<Box> getListBoxMaintenance() {
        return listBoxMaintenance;
    }

    public void setListBoxMaintenance(List<Box> listBoxMaintenance) {
        this.listBoxMaintenance = listBoxMaintenance;
    }

    public List<Box> getListBoxIndispo() {
        return listBoxIndispo;
    }

    public void setListBoxIndispo(List<Box> listBoxIndispo) {
        this.listBoxIndispo = listBoxIndispo;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
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

    public List<Client> getListClient() {
        return listClient;
    }

    public void setListClient(List<Client> listClient) {
        this.listClient = listClient;
    }

    public List<Client> getListClient2() {
        return listClient2;
    }

    public void setListClient2(List<Client> listClient2) {
        this.listClient2 = listClient2;
    }

    public List<Townlayer> getListTownlayer() {
        return listTownlayer;
    }

    public void setListTownlayer(List<Townlayer> listTownlayer) {
        this.listTownlayer = listTownlayer;
    }

    public List<Organization> getListOrganization() {
        return listOrganization;
    }

    public List<Hardvers> getListHardvers() {
        return listHardvers;
    }

    public void setListHardvers(List<Hardvers> listHardvers) {
        this.listHardvers = listHardvers;
    }

    public List<Softvers> getListSoftvers() {
        return listSoftvers;
    }

    public void setListSoftvers(List<Softvers> listSoftvers) {
        this.listSoftvers = listSoftvers;
    }

    public void setListOrganization(List<Organization> listOrganization) {
        this.listOrganization = listOrganization;
    }

    public List<Box> getListBoxLibre() {
        return listBoxLibre;
    }

    public void setListBoxLibre(List<Box> listBoxLibre) {
        this.listBoxLibre = listBoxLibre;
    }

    public List<Hardvers> getListHardversion() {
        return listHardversion;
    }

    public void setListHardversion(List<Hardvers> listHardversion) {
        this.listHardversion = listHardversion;
    }

    public List<Softvers> getListSoftversion() {
        return listSoftversion;
    }

    public void setListSoftversion(List<Softvers> listSoftversion) {
        this.listSoftversion = listSoftversion;
    }

    public String getStyleMarquer1() {
        return styleMarquer1;
    }

    public void setStyleMarquer1(String styleMarquer1) {
        this.styleMarquer1 = styleMarquer1;
    }

    public String getStyleAtelier() {
        return styleAtelier;
    }

    public void setStyleAtelier(String styleAtelier) {
        this.styleAtelier = styleAtelier;
    }

    public String getMagDispo() {
        return magDispo;
    }

    public void setMagDispo(String magDispo) {
        this.magDispo = magDispo;
    }

    public String getMagHS() {
        return magHS;
    }

    public void setMagHS(String magHS) {
        this.magHS = magHS;
    }

    public int getNumeroBox() {
        return numeroBox;
    }

    public void setNumeroBox(int numeroBox) {
        this.numeroBox = numeroBox;
    }

    public String getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(String finalDestination) {
        this.finalDestination = finalDestination;
    }

    public int getNumboxOut() {
        return numboxOut;
    }

    public void setNumboxOut(int numboxOut) {
        this.numboxOut = numboxOut;
    }

    public double getLatitudeOut() {
        return latitudeOut;
    }

    public void setLatitudeOut(double latitudeOut) {
        this.latitudeOut = latitudeOut;
    }

    public double getLongitudeOut() {
        return longitudeOut;
    }

    public void setLongitudeOut(double longitudeOut) {
        this.longitudeOut = longitudeOut;
    }

    public String getCountryOut() {
        return countryOut;
    }

    public void setCountryOut(String countryOut) {
        this.countryOut = countryOut;
    }

    public Date getFirstinstaldateOut() {
        return firstinstaldateOut;
    }

    public void setFirstinstaldateOut(Date firstinstaldateOut) {
        this.firstinstaldateOut = firstinstaldateOut;
    }

    public Date getLastinstaldateOut() {
        return lastinstaldateOut;
    }

    public void setLastinstaldateOut(Date lastinstaldateOut) {
        this.lastinstaldateOut = lastinstaldateOut;
    }

    public Date getLasttransactOut() {
        return lasttransactOut;
    }

    public void setLasttransactOut(Date lasttransactOut) {
        this.lasttransactOut = lasttransactOut;
    }

    public BigInteger getLasttimecodevalueOut() {
        return lasttimecodevalueOut;
    }

    public void setLasttimecodevalueOut(BigInteger lasttimecodevalueOut) {
        this.lasttimecodevalueOut = lasttimecodevalueOut;
    }

    public BigInteger getTotaltimepaidOut() {
        return totaltimepaidOut;
    }

    public void setTotaltimepaidOut(BigInteger totaltimepaidOut) {
        this.totaltimepaidOut = totaltimepaidOut;
    }

    public BigInteger getEstimlefttimepaidOut() {
        return estimlefttimepaidOut;
    }

    public void setEstimlefttimepaidOut(BigInteger estimlefttimepaidOut) {
        this.estimlefttimepaidOut = estimlefttimepaidOut;
    }

    public int getMarquerstatusOut() {
        return marquerstatusOut;
    }

    public void setMarquerstatusOut(int marquerstatusOut) {
        this.marquerstatusOut = marquerstatusOut;
    }

    public String getUserofsystemtOut() {
        return userofsystemtOut;
    }

    public void setUserofsystemtOut(String userofsystemtOut) {
        this.userofsystemtOut = userofsystemtOut;
    }

    public Date getEstimatedenddateOut() {
        return estimatedenddateOut;
    }

    public void setEstimatedenddateOut(Date estimatedenddateOut) {
        this.estimatedenddateOut = estimatedenddateOut;
    }

    public String getMarquerstyleOut() {
        return marquerstyleOut;
    }

    public void setMarquerstyleOut(String marquerstyleOut) {
        this.marquerstyleOut = marquerstyleOut;
    }

    public String getAtelierOut() {
        return atelierOut;
    }

    public void setAtelierOut(String atelierOut) {
        this.atelierOut = atelierOut;
    }

    public long getIdtownlayerOut() {
        return idtownlayerOut;
    }

    public void setIdtownlayerOut(long idtownlayerOut) {
        this.idtownlayerOut = idtownlayerOut;
    }

    public long getIdorganizationOut() {
        return idorganizationOut;
    }

    public void setIdorganizationOut(long idorganizationOut) {
        this.idorganizationOut = idorganizationOut;
    }

    public long getIdclientOut() {
        return idclientOut;
    }

    public void setIdclientOut(long idclientOut) {
        this.idclientOut = idclientOut;
    }

    public long getIdboxeIn() {
        return idboxeIn;
    }

    public void setIdboxeIn(long idboxeIn) {
        this.idboxeIn = idboxeIn;
    }
}
