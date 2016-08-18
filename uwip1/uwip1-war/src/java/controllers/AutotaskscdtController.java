/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.AutoTaskCDT;
import entities.Credits;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import entities.Paygotpgenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import sessions.CreditsFacadeLocal;
import sessions.PaygotpgeneratorFacadeLocal;

/**
 *
 * @author Seanjackson
 */
@Named(value = "autotaskscdtController")
@ApplicationScoped
public class AutotaskscdtController {
    // Variables
    
    @Inject
    private PaygotpgeneratorFacadeLocal paygotpgeneratorFacade;
    private List<Paygotpgenerator> listPaygotpgenerator = new ArrayList<Paygotpgenerator>();
    private Paygotpgenerator paygotpgenerator = new Paygotpgenerator();
    @Inject
    private CreditsFacadeLocal creditsFacade;
    private List<Credits> listCredits = new ArrayList<Credits>();
    private Credits credits = new Credits();
    int lenghtCDT = 0;
    private String filtres = " ";
    private String statusTask = "ECHEC";
    private int waitBeforeStarTask = 0;
    private int intervalBetweenTasks = 0;
    Timer myTimer = new Timer();
    Timer timer;
    private String waitingIN = "";
    private String intervalIN = "";

    /**
     * Creates a new instance of AutotaskscdtController
     */
    public AutotaskscdtController() {
    }
    
    
    
    public void captureURLforTask() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest httpRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        waitingIN = httpRequest.getParameter("waitingIN");
        System.out.println("Waiting before start task: " + waitingIN);
        intervalIN = httpRequest.getParameter("intervalIN");
        System.out.println("Interval entre les taches: " + intervalIN);
    }

    public void Reminder(int secondsStar, int secondInterval) {
        timer = new Timer();
        timer.schedule(new RemindTask(), secondsStar * 1000, secondInterval * 1000);
    }

    class RemindTask extends TimerTask {

        public void run() {
            String lifeCyclePAYG = "PAYG";
            String boxStatusUSED = "USED";
            String creditSatus4 = "Desactivé";
            //String creditSatus5 = "Remboursé";
            lenghtCDT = paygotpgeneratorFacade.count();
            System.out.println("Total line = " + lenghtCDT);
            System.out.println("DEBUT AUTO");
            listPaygotpgenerator.clear();
            listPaygotpgenerator.addAll(paygotpgeneratorFacade.findAll());
            for (int i = lenghtCDT; i >= 1; i--) {
                System.out.println("Itteration: " + i);
                //Index Java (De 0 a i-1)
                int indexCDT = i - 1;
                System.out.println("Index: " + indexCDT);
                // Recuperation des status
                String nowLifeCycleStatus = listPaygotpgenerator.get(indexCDT).getPayglifecyclestatus();
                String nowBoxStatus = listPaygotpgenerator.get(indexCDT).getBoxstatus();
                if (nowLifeCycleStatus.equals(lifeCyclePAYG) && nowBoxStatus.equals(boxStatusUSED)) {
                    // Recuperation du CDT de l'index
                    int nowCDT = listPaygotpgenerator.get(indexCDT).getOtpcount();
                    // Recuperation de l'ID PAYG de l'index
                    String idPAYGprod = listPaygotpgenerator.get(indexCDT).getIdpaygproduct();
                    //Calcul du nouveau CDT apres 24h passé
                    int newtCDT = nowCDT - 1;
                    //Update du CDT
                    int reqUpdateCDT = paygotpgeneratorFacade.findToUpdateCDT(idPAYGprod, newtCDT);
                    System.out.println("Now CDT (" + i + "): " + nowCDT);
                    System.out.println("New CDT (" + i + "): " + newtCDT);
                    System.out.println("Nbre update of CDT = " + reqUpdateCDT);
                    if (newtCDT <= 0) {
                        paygotpgenerator.setIdpaygproduct(idPAYGprod);
                        //credits.setIdpaygproduct(paygotpgenerator);
                        int reqUpdateCreditStatus = creditsFacade.findToUpdateCredStatAfterAutoCDT(paygotpgenerator, creditSatus4);
                        System.out.println("Nbre update of CreditStatus = " + reqUpdateCreditStatus);
                        System.out.println("Nbre de credit desactivé = " + reqUpdateCreditStatus);
                    } else {
                        System.out.println("Systeme à Jour. Passez");
                    }
                } else {
                    System.out.println("Non Valide pour la decrementation du CDT. Passez");
                }

            }
            System.out.println("FIN AUTO");
            System.out.println("-----------");
            //timer.cancel(); //Terminer la tache auto
        }
    }

    public String initialiseTImeIntervalTask() {
        try {
            captureURLforTask();
            System.out.println("-----START TASKS-----");
            waitBeforeStarTask = Integer.parseInt(waitingIN);
            intervalBetweenTasks = Integer.parseInt(intervalIN);
            Reminder(waitBeforeStarTask, intervalBetweenTasks);
            statusTask = "SUCCES";
        } catch (Exception e) {
            statusTask = "ECHEC";
            e.printStackTrace();
        }
        return statusTask;
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

    public Timer getMyTimer() {
        return myTimer;
    }

    public void setMyTimer(Timer myTimer) {
        this.myTimer = myTimer;
    }

    public int getLenghtCDT() {
        return lenghtCDT;
    }

    public void setLenghtCDT(int lenghtCDT) {
        this.lenghtCDT = lenghtCDT;
    }

    public String getFiltres() {
        return filtres;
    }

    public void setFiltres(String filtres) {
        this.filtres = filtres;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public CreditsFacadeLocal getCreditsFacade() {
        return creditsFacade;
    }

    public void setCreditsFacade(CreditsFacadeLocal creditsFacade) {
        this.creditsFacade = creditsFacade;
    }

    public List<Credits> getListCredits() {
        return listCredits;
    }

    public void setListCredits(List<Credits> listCredits) {
        this.listCredits = listCredits;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public String getStatusTask() {
        return statusTask;
    }

    public void setStatusTask(String statusTask) {
        this.statusTask = statusTask;
    }

    public int getWaitBeforeStarTask() {
        return waitBeforeStarTask;
    }

    public void setWaitBeforeStarTask(int waitBeforeStarTask) {
        this.waitBeforeStarTask = waitBeforeStarTask;
    }

    public int getIntervalBetweenTasks() {
        return intervalBetweenTasks;
    }

    public void setIntervalBetweenTasks(int intervalBetweenTasks) {
        this.intervalBetweenTasks = intervalBetweenTasks;
    }

    public String getWaitingIN() {
        return waitingIN;
    }

    public void setWaitingIN(String waitingIN) {
        this.waitingIN = waitingIN;
    }

    public String getIntervalIN() {
        return intervalIN;
    }

    public void setIntervalIN(String intervalIN) {
        this.intervalIN = intervalIN;
    }
}
