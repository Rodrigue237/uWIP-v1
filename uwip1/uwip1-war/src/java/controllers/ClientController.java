
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Utils;
import entities.Client;
import entities.Mmoperator;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import sessions.ClientFacadeLocal;
import sessions.MmoperatorFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "clientController")
@SessionScoped
public class ClientController implements Serializable {

    @Inject
    private ClientFacadeLocal clientFacade;
    private List<Client> listClient = new ArrayList<Client>();
    private MmoperatorFacadeLocal mmoperatorFacade;
    private List<Mmoperator> listMmoperator;
    private List<Mmoperator> listOperateurDispo = new ArrayList<Mmoperator>();
    private Client client = new Client();
    private String firstnameToCount = "";
    private String lastnameToCount = "";
    private String fichier = "Reports/ListeClientExel.jasper";
    private int reqCount = 0;
    private String message = "";
    private String operat = "";
    private boolean showMessage = false;
    private Utils utils = new Utils();

    /**
     * Creates a new instance of ClientController
     */
    public ClientController() {
        listMmoperator = new ArrayList<Mmoperator>();
    }

    public String client() {
        listClient.clear();
        listClient.addAll(clientFacade.findAll());
        chargeListMmoperator();
        prepareCreate();
        return "clients.xhtml?faces-redirect=true";
    }

    public void prepareCreate() {
        client = new Client();
        client.setIdoperator(new Mmoperator());
    }

    public void chargeListMmoperator() {
        listMmoperator.clear();
        listMmoperator.addAll(clientFacade.findAllOperator(operat));
    }

    public void initialisationClient() {
        client.setTotalbox(0);
    }

    public void prepareEdit() {
        client = new Client();
        client.setIdoperator(new Mmoperator());
    }

    public String saveClient() {
        try {
            initialisationClient();
            firstnameToCount = client.getFirstname();
            lastnameToCount = client.getLastname();
            reqCount = clientFacade.findCopieDouble(firstnameToCount, lastnameToCount);
            if (reqCount >= 1) {
                showMessage = true;
                message = "Désolé! Ce client existe déjà";
            } else {
                clientFacade.create(client); // Creation du client dans la derniere ligne vide
                showMessage = true;
                message = "Enregistrement effectué avec succès";
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return client();
    }

    public String editClient() {
        try {
            clientFacade.edit(client);
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return client();
    }

    public String deleteClient() {
        try {
            clientFacade.remove(client);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la suppression";
            e.printStackTrace();
        }
        return client();
    }

    public String imprimer() {

        try {
            utils.print("Reports/ListeClient.jasper");


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            return ext.getRequestPathInfo() + "?faces-redirect=true";

        }

    }
    
    public String imprimerExcel() {

        try {
            utils.print("Reports/ListeClientExel.jasper");


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            return ext.getRequestPathInfo() + "?faces-redirect=true";

        }
    }

    /*public String imprimerExcel() {

        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/uwip4_db[postgres on public]");
            Map parameters = new HashMap();
            parameters.put("p", 1);
            JasperPrint jasperPrint = JasperFillManager.fillReport(fichier, parameters, con);
            JRXlsExporter exporterXLS = new JRXlsExporter();
            exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
            exporterXLS.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C://ListeClient.xls");

            exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
            exporterXLS.exportReport();
            //utils.printExel("Reports/ListeClientExel.jasper");
            //utils.print("Reports/ListeClientExel.jasper");


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            return ext.getRequestPathInfo() + "?faces-redirect=true";

        }
    }*/

    public List<Client> getListClient() {
        return this.listClient;
    }

    public void setListClient(List<Client> listclient) {
        this.listClient = listclient;
    }

    public List<Mmoperator> getListMmoperator() {
        return this.listMmoperator;
    }

    public void setListMmoperator(List<Mmoperator> listmmoperator) {
        this.listMmoperator = listmmoperator;
    }

    public List<Mmoperator> getListOperateurDispo() {
        return listOperateurDispo;
    }

    public void setListOperateurDispo(List<Mmoperator> listOperateurDispo) {
        this.listOperateurDispo = listOperateurDispo;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
