
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Utils;
import entities.Note;
import entities.Box;
import entities.Distributor;
import entities.Organization;
import entities.Client;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessions.NoteFacadeLocal;
import sessions.BoxFacadeLocal;
import sessions.DistributorFacadeLocal;
import sessions.OrganizationFacadeLocal;
import sessions.ClientFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "noteController")
@SessionScoped
public class NoteController implements Serializable {

    @Inject
    private NoteFacadeLocal noteFacade;
    private BoxFacadeLocal boxFacade;
    private DistributorFacadeLocal distributorFacade;
    private OrganizationFacadeLocal organizationFacade;
    private ClientFacadeLocal clientFacade;
    private List<Note> listNote = new ArrayList<Note>();
    private List<Box> listBox = new ArrayList<Box>();
    private List<Client> listClient = new ArrayList<Client>();
    private List<Distributor> listDistributor = new ArrayList<Distributor>();
    private List<Organization> listOrganization = new ArrayList<Organization>();
    private Note note = new Note();
    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String message = "";
    private String detailNote = "xx";
    private boolean showMessage = false;
    private Utils utils = new Utils();

    /**
     * Creates a new instance of NoteController
     */
    public NoteController() {
    }

    public String note() {
        listNote.clear();
        listNote.addAll(noteFacade.findAll());
        chargeListOrganization();
        chargeListClient();
        chargeListDistributor();
        chargeListBox();
        return "notes.xhtml?faces-redirect=true";
    }

    public void prepareCreate() {
        note = new Note();
        note.setIdboxe(new Box());
        note.setIdclient(new Client());
        note.setIddistributor(new Distributor());
        note.setIdorganization(new Organization());
    }

    public void prepareEdit() {
        note = new Note();
        detailNote = this.note.getContent();
    }
    
    public String prepareEditContent() {
        note = new Note();
        detailNote = this.note.getContent();
        return detailNote;
    }

    public void chargeListClient() {
        listClient.clear();
        listClient.addAll(noteFacade.findAllClient());
    }

    public void chargeListOrganization() {
        listOrganization.clear();
        listOrganization.addAll(noteFacade.findAllOrganization());
    }

    public void chargeListDistributor() {
        listDistributor.clear();
        listDistributor.addAll(noteFacade.findAllDistributor());
    }

    public void chargeListBox() {
        listBox.clear();
        listBox.addAll(noteFacade.findAllBox());
    }

    public String saveNote() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//Definition du format de date
            String sDate = dateFormat.format(new Date());
            note.setDatecreated(dateFormat.parse(sDate));
            noteFacade.create(note); // Creation du note dans la derniere ligne vide
            showMessage = true;
            message = "Enregistrement effectué avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return note();
    }

    public String editNote() {
        try {
            noteFacade.edit(note);
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return note();
    }

    public String deleteNote() {
        try {
            noteFacade.remove(note);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la suppression";
            e.printStackTrace();
        }
        return note();
    }

    public String imprimer() {

        try {
            utils.print("Reports/ListeNote.jasper");


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            return ext.getRequestPathInfo() + "?faces-redirect=true";

        }


    }

    public List<Note> getListNote() {
        return this.listNote;
    }

    public void setListNote(List<Note> listnote) {
        this.listNote = listnote;
    }

    public List<Box> getListBox() {
        return listBox;
    }

    public void setListBox(List<Box> listBox) {
        this.listBox = listBox;
    }

    public List<Client> getListClient() {
        return listClient;
    }

    public void setListClient(List<Client> listClient) {
        this.listClient = listClient;
    }

    public List<Distributor> getListDistributor() {
        return listDistributor;
    }

    public void setListDistributor(List<Distributor> listDistributor) {
        this.listDistributor = listDistributor;
    }

    public List<Organization> getListOrganization() {
        return listOrganization;
    }

    public void setListOrganization(List<Organization> listOrganization) {
        this.listOrganization = listOrganization;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
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

    public String getDetailNote() {
        return detailNote;
    }

    public void setDetailNote(String detailNote) {
        this.detailNote = detailNote;
    }
}
