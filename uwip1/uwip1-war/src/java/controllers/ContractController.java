
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Utils;
import controllers.SessionController;
import entities.Box;
import entities.Client;
import entities.Contract;
import entities.Organization;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessions.BoxFacadeLocal;
import sessions.ClientFacadeLocal;
import sessions.ContractFacadeLocal;
import sessions.OrganizationFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "contractController")
@SessionScoped
public class ContractController implements Serializable {

    @Inject
    private ContractFacadeLocal contractFacade;
    private List<Contract> listContract = new ArrayList<Contract>();
    @Inject
    private BoxFacadeLocal boxFacade;
    private List<Box> listBoxLibre = new ArrayList<Box>();
    @Inject
    private ClientFacadeLocal clientFacade;
    private List<Client> listClientPotentiel = new ArrayList<Client>();
    private Contract contract = new Contract();
    private Organization idorganization;
    private int reqChangeEtatRep = 0;
    private int reqChangeLatiAndLongRep = 0;
    private long reqMaxIdContract = 0;
    private int reqChangeClientBox = 0;
    private int reqChangeOrganizationBox = 0;
    private int reqChangeTotalBoxes = 0;
    private int reqChangeTotalInstall = 0;
    private int idOfLastContract = 0;
    private int reqUpdateBalance = 0;
    private int totalInstall = 0;
    private int reqInitNumBox = 0;
    private int reqUpdateFirsInstallDate = 0;
    private int reqUpdateLastInstallDate = 0;
    private int numInstall = 0;
    private int phoneUserSystem = 0;
    private String message = "";
    private String resultUpdateDates = "";
    private String resultUpdateDatesPrint = "";
    private boolean showMessage = false;
    private long idboxinstal = 0;
    private long idclientinstal = 0;
    private long moneyDue;
    private long moneyPay;
    private long moneyBalance;
    private Double latitudeinstal = 0.0;
    private Double longitudeinstal = 0.0;
    private String userOfSystem = "";
    private String styleMarquer1 = "clientMarq";
    private String styleAtelier = "A1.png";
    private String magDispo = "A1.png";
    private String boxActivated = "A5.png";
    private String defaultStatus = "activ";
    private Date contratInstallDate;
    private int totalInstallBox = 0;
    private Utils utils = new Utils();

    /**
     * Creates a new instance of ContractController
     */
    public ContractController() {
    }

    public String contract() {
        listContract.clear();
        listContract.addAll(contractFacade.findAll());
        chargeListBoxLibre();
        chargeListClientPotentiel();
        return "contracts.xhtml?faces-redirect=true";
    }
    
    public String installation() {
        listContract.clear();
        listContract.addAll(contractFacade.findAll());
        chargeListBoxLibre();
        chargeListClientPotentiel();
        return "installations.xhtml?faces-redirect=true";
    }

    public void prepareCreate() {
        contract = new Contract();
        contract.setIdclient(new Client());
        contract.setIdboxe(new Box());
    }

    public void initContract() {
        //userOfSystem = contract.getUserofsystem();
        contract.setInstallstatus(defaultStatus);
        //contract.setDesinstalldate(this.getContract().getInstalldate());
        contract.setUserofsystem(userOfSystem);
        contract.setPhoneofusersystem(phoneUserSystem);
    }

    public String initBoxFirstAndLastInstallDate() {
        try {
            contratInstallDate = this.getContract().getInstalldate();
            System.out.println("Date install contrat:" + contratInstallDate);
            if (totalInstallBox >= 1) {
                reqUpdateLastInstallDate = boxFacade.updateLastInstallDate(idboxinstal, contratInstallDate);
                resultUpdateDates = "Succes update Last InstallDate";
                System.out.println("Nbre update LastInstal:" + reqUpdateLastInstallDate);
            } else {
                reqUpdateFirsInstallDate = boxFacade.updateFirstInstallDate(idboxinstal, contratInstallDate);
                resultUpdateDates = "Succes update Fist InstallDate";
                System.out.println("Nbre update FirsInstal:" + reqUpdateFirsInstallDate);
            }
        } catch (Exception e) {
            resultUpdateDates = "Echec update";
            e.printStackTrace();
        }
        return resultUpdateDates;
    }

    public void chargeListBoxLibre() {
        listBoxLibre.clear();
        listBoxLibre.addAll(boxFacade.findByLibre());
    }

    public void chargeListClientPotentiel() {
        listClientPotentiel.clear();
        listClientPotentiel.addAll(clientFacade.findAll());
    }

    public void changeEtatMarqueur() {
        reqChangeEtatRep = boxFacade.findToUpdateStMarquer(idboxinstal, boxActivated);
    }

    public void initialiseNumBox() {
        reqInitNumBox = boxFacade.findToInitNumBox(numInstall, idboxinstal);
    }

    public void changeLatiAndLongi() {
        reqChangeLatiAndLongRep = boxFacade.findToUpdateLatiAndLong(latitudeinstal, longitudeinstal, idboxinstal);
    }

    public void changeClientNameBox() {
        reqChangeClientBox = boxFacade.findToUpdateClientBox(idboxinstal, this.contract.getIdclient());
    }

    public void changeTotalBox() {
        reqChangeTotalBoxes = clientFacade.findToUpdateTotalBox(idclientinstal);
    }

    public void changeTotalInstall() {
        totalInstall = boxFacade.findTotalInstall(idboxinstal) + 1;
        //totalInstall = totalInstall + 1;
        reqChangeTotalInstall = boxFacade.changeTotalInstall(totalInstall, idboxinstal, userOfSystem, idOfLastContract, phoneUserSystem);
    }

    public void takeAllId() {
        reqMaxIdContract = contractFacade.findMaxIdContract();
        idOfLastContract = (int) reqMaxIdContract;
        System.out.println("Id last Contrat:" + idOfLastContract);
        System.out.println("Max Id Take:" + reqMaxIdContract);
        idclientinstal = contractFacade.findLastClientContract(reqMaxIdContract);
        System.out.println("IdClient Take:" + idclientinstal);
        idboxinstal = contractFacade.findLastBoxContract(reqMaxIdContract);
        System.out.println("IdBox Take:" + idboxinstal);
        totalInstallBox = boxFacade.findTotalInstall(idboxinstal);
        System.out.println("Total precedente install:" + totalInstallBox);
    }

    /*public void changeOrganizationName(){
     reqChangeOrganizationBox = boxFacade.findToUpdateOrganizationBox(idboxinstal, connectedUser.firstname);
     }*/
    public void moneyBalance() {
        moneyDue = contract.getTotalmoneydue().longValue();
        moneyPay = contract.getTotalmoneypaid().longValue();
        moneyBalance = moneyDue - moneyPay;
    }

    public void updateBalance() {
        reqUpdateBalance = contractFacade.updateBalance(moneyBalance, reqMaxIdContract);
    }

    public void prepareEdit() {
        contract = new Contract();
    }

    public String saveContract() {
        try {
            moneyBalance();
            initContract();
            contractFacade.create(contract); // Creation du contract dans la derniere ligne vide
            takeAllId();
            resultUpdateDatesPrint = initBoxFirstAndLastInstallDate();
            updateBalance();
            changeLatiAndLongi();
            changeEtatMarqueur();
            changeTotalBox();
            changeClientNameBox();
            changeTotalInstall();
            initialiseNumBox();
            System.out.println("ID Box choisis:" + idboxinstal);
            System.out.println("ID Client choisis:" + idclientinstal);
            System.out.println("Nbre de box update:" + reqChangeEtatRep);
            System.out.println("Latitude update:" + latitudeinstal);
            System.out.println("Longitude update:" + longitudeinstal);
            System.out.println("Nbre de GPS update:" + reqChangeLatiAndLongRep);
            System.out.println("ID Contract Max:" + reqMaxIdContract);
            System.out.println("Nbre de BoxClient Name update:" + reqChangeClientBox);
            System.out.println("Valeur Balance:" + moneyBalance);
            System.out.println("Nbre de Balance update:" + reqUpdateBalance);
            System.out.println("Nbre dOrganisation update:" + reqChangeOrganizationBox);
            System.out.println("New Num Install:" + numInstall);
            System.out.println("Nbre update NumInstall:" + reqInitNumBox);
            showMessage = true;
            message = "Enregistrement effectué avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return contract();
    }

    public String editContract() {
        try {
            contractFacade.edit(contract);
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return installation();
    }

    public String deleteContract() {
        try {
            contractFacade.remove(contract);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la suppression";
            e.printStackTrace();
        }
        return contract();
    }

    public String imprimer() {

        try {
            utils.print("Reports/ListeContract.jasper");


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            return ext.getRequestPathInfo() + "?faces-redirect=true";

        }


    }

    public List<Contract> getListContract() {
        return this.listContract;
    }

    public void setListContract(List<Contract> listcontract) {
        this.listContract = listcontract;
    }

    public List<Box> getListBoxLibre() {
        return listBoxLibre;
    }

    public void setListBoxLibre(List<Box> listBoxLibre) {
        this.listBoxLibre = listBoxLibre;
    }

    public List<Client> getListClientPotentiel() {
        return listClientPotentiel;
    }

    public void setListClientPotentiel(List<Client> listClientPotentiel) {
        this.listClientPotentiel = listClientPotentiel;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Organization getIdorganization() {
        return idorganization;
    }

    public void setIdorganization(Organization idorganization) {
        this.idorganization = idorganization;
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

    public long getIdboxinstal() {
        return idboxinstal;
    }

    public void setIdboxinstal(long idboxinstal) {
        this.idboxinstal = idboxinstal;
    }

    public long getIdclientinstal() {
        return idclientinstal;
    }

    public void setIdclientinstal(long idclientinstal) {
        this.idclientinstal = idclientinstal;
    }

    public Double getLatitudeinstal() {
        return latitudeinstal;
    }

    public void setLatitudeinstal(Double latitudeinstal) {
        this.latitudeinstal = latitudeinstal;
    }

    public Double getLongitudeinstal() {
        return longitudeinstal;
    }

    public void setLongitudeinstal(Double longitudeinstal) {
        this.longitudeinstal = longitudeinstal;
    }

    public int getNumInstall() {
        return numInstall;
    }

    public void setNumInstall(int numInstall) {
        this.numInstall = numInstall;
    }

    public String getUserOfSystem() {
        return userOfSystem;
    }

    public void setUserOfSystem(String userOfSystem) {
        this.userOfSystem = userOfSystem;
    }

    public String getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(String defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public int getIdOfLastContract() {
        return idOfLastContract;
    }

    public void setIdOfLastContract(int idOfLastContract) {
        this.idOfLastContract = idOfLastContract;
    }

    public ContractFacadeLocal getContractFacade() {
        return contractFacade;
    }

    public void setContractFacade(ContractFacadeLocal contractFacade) {
        this.contractFacade = contractFacade;
    }

    public BoxFacadeLocal getBoxFacade() {
        return boxFacade;
    }

    public void setBoxFacade(BoxFacadeLocal boxFacade) {
        this.boxFacade = boxFacade;
    }

    public ClientFacadeLocal getClientFacade() {
        return clientFacade;
    }

    public void setClientFacade(ClientFacadeLocal clientFacade) {
        this.clientFacade = clientFacade;
    }

    public int getTotalInstall() {
        return totalInstall;
    }

    public void setTotalInstall(int totalInstall) {
        this.totalInstall = totalInstall;
    }

    public String getBoxActivated() {
        return boxActivated;
    }

    public void setBoxActivated(String boxActivated) {
        this.boxActivated = boxActivated;
    }

    public Date getContratInstallDate() {
        return contratInstallDate;
    }

    public void setContratInstallDate(Date contratInstallDate) {
        this.contratInstallDate = contratInstallDate;
    }

    public int getTotalInstallBox() {
        return totalInstallBox;
    }

    public void setTotalInstallBox(int totalInstallBox) {
        this.totalInstallBox = totalInstallBox;
    }

    public int getPhoneUserSystem() {
        return phoneUserSystem;
    }

    public void setPhoneUserSystem(int phoneUserSystem) {
        this.phoneUserSystem = phoneUserSystem;
    }
}
