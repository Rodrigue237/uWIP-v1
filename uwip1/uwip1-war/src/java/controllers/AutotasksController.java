/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.AutoTaskCDT;
import entities.Paygotpgenerator;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import sessions.PaygotpgeneratorFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "autotasksController")
@SessionScoped
public class AutotasksController implements Serializable {

    @Inject
    private PaygotpgeneratorFacadeLocal paygotpgeneratorFacade;
    private List<Paygotpgenerator> listPaygotpgenerator = new ArrayList<Paygotpgenerator>();
    private Paygotpgenerator paygotpgenerator = new Paygotpgenerator();
    private String filtreCount = " ";
    private int lenghtIN = 0;
    private int lenghOUT = 0;

    public AutotasksController() {
        lenghOUT = 0;
    }

    public void updateCDTAutomaticaly() {
        lenghOUT = paygotpgeneratorFacade.findDataLength(filtreCount);
        System.out.println("Total de ligne: " + lenghOUT);
        System.out.println("-----START TASKS-----");
        // Script d'execution automatique des taches du CDT
        AutoTaskCDT.autoExecuteCDT_Update();
    }

    public PaygotpgeneratorFacadeLocal getPaygotpgeneratorFacade() {
        return paygotpgeneratorFacade;
    }

    public void setPaygotpgeneratorFacade(PaygotpgeneratorFacadeLocal paygotpgeneratorFacade) {
        this.paygotpgeneratorFacade = paygotpgeneratorFacade;
    }

    public List<Paygotpgenerator> getListPaygotpgenerator() {
        return listPaygotpgenerator;
    }

    public void setListPaygotpgenerator(List<Paygotpgenerator> listPaygotpgenerator) {
        this.listPaygotpgenerator = listPaygotpgenerator;
    }

    public Paygotpgenerator getPaygotpgenerator() {
        return paygotpgenerator;
    }

    public void setPaygotpgenerator(Paygotpgenerator paygotpgenerator) {
        this.paygotpgenerator = paygotpgenerator;
    }

    public String getFiltreCount() {
        return filtreCount;
    }

    public void setFiltreCount(String filtreCount) {
        this.filtreCount = filtreCount;
    }

    public int getLenghtIN() {
        return lenghtIN;
    }

    public void setLenghtIN(int lenghtIN) {
        this.lenghtIN = lenghtIN;
    }

    public int getLenghOUT() {
        return lenghOUT;
    }

    public void setLenghOUT(int lenghOUT) {
        this.lenghOUT = lenghOUT;
    }
}
