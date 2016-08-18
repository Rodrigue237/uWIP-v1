
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Utils;
import entities.Produits;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessions.ProduitsFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "produitsController")
@SessionScoped
public class ProduitsController implements Serializable {

    @Inject
    private ProduitsFacadeLocal produitsFacade; 
    private List<Produits> listProduits = new ArrayList<Produits>();
    private Produits produits = new Produits();
    private String message = "";
    private String produitToCount = "";
    private int reqCount = 0;
    private boolean showMessage = false;
    private Utils utils = new Utils();

    /**
     * Creates a new instance of ProduitsController
     */
    public ProduitsController() {
    }

    public String produits() {
        listProduits.clear();
        listProduits.addAll(produitsFacade.findAll());
        prepareCreate();
        return "produits.xhtml?faces-redirect=true";
    }

    public void prepareCreate() {
        produits = new Produits();
    }

    public void prepareEdit() {
        produits = new Produits();
    }

    public String saveProduits() {
        try {
            produitToCount = produits.getDesignation();
            reqCount = produitsFacade.findCopieDouble(produitToCount);
            if (reqCount >= 1) {
                showMessage = true;
                message = "Désolé! Ce produit existe déjà";
            } else {
                produitsFacade.create(produits); // Creation du produits dans la derniere ligne vide
                showMessage = true;
                message = "Enregistrement effectué avec succès";
            }
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de l'enregistrement";
            e.printStackTrace();
        }
        return produits();
    }

    public String editProduits() {
        try {
            produitsFacade.edit(produits);
            showMessage = true;
            message = "Modification effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la modification";
            e.printStackTrace();
        }
        return produits();
    }

    public String deleteProduits() {
        try {
            System.out.println("Produit:" + produits.getDesignation());
            produitsFacade.remove(produits);
            showMessage = true;
            message = "Suppression effectuée avec succès";
        } catch (Exception e) {
            showMessage = true;
            message = "Echec de la suppression";
            e.printStackTrace();
        }
        return produits();
    }

    public String imprimer() {

        try {
            utils.print("Reports/ListeProduits.jasper");


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            return ext.getRequestPathInfo() + "?faces-redirect=true";

        }


    }

    public List<Produits> getListProduits() {
        return this.listProduits;
    }

    public void setListProduits(List<Produits> listproduits) {
        this.listProduits = listproduits;
    }

    public Produits getProduits() {
        return produits;
    }

    public void setProduits(Produits produits) {
        this.produits = produits;
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
