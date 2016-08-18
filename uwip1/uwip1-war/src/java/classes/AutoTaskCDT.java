/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import entities.Paygotpgenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.inject.Inject;
import sessions.PaygotpgeneratorFacadeLocal;
import controllers.AutotasksController;

/**
 *
 * @author Seanjackson
 */
public class AutoTaskCDT {

    // Variables
    @Inject
    private PaygotpgeneratorFacadeLocal paygotpgeneratorFacade;
    private List<Paygotpgenerator> listPaygotpgenerator = new ArrayList<Paygotpgenerator>();
    private Paygotpgenerator paygotpgenerator = new Paygotpgenerator();
    private AutotasksController autotaskscontroller = new AutotasksController();
    int lenghtCDT = 0;
    private String filtres = " ";
    Timer myTimer = new Timer();
    Timer timer;

    public void Reminder(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds * 1000);
    }

    class RemindTask extends TimerTask {

        public void run() {
            lenghtCDT = paygotpgeneratorFacade.findDataLength(filtres);
            System.out.println("Time's up! Total line = " + filtres);
            timer.cancel(); //Terminate the timer thread
        }
    }

    public void initialiseTImeIntervalTask(int intervalInSeconde) {
        System.out.println("Task scheduled.");
        Reminder(intervalInSeconde);
        
    }
    
    TimerTask task = new TimerTask() {
        // Definition des taches ici a l'interieur de la methode native RUN
        public void run() {
            //minutePass++;
            int lenght = autotaskscontroller.getLenghOUT();
            System.out.println("Lenght Out: " + lenght);
            System.out.println("DEBUT AUTO");
            for (int i = lenght; i <= 1; i--) {
                System.out.println("Ligne: " + lenght);
            }
            System.out.println("FIN AUTO");
            System.out.println(" ");
        }
    };

    public void start() {
        myTimer.scheduleAtFixedRate(task, 30000, 60000);
    }

    public static void autoExecuteCDT_Update() {
        // TODO code application logic here
        AutoTaskCDT autoUpdateCDT24 = new AutoTaskCDT();
        autoUpdateCDT24.start();
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

    public TimerTask getTask() {
        return task;
    }

    public void setTask(TimerTask task) {
        this.task = task;
    }

    public AutotasksController getAutotaskscontroller() {
        return autotaskscontroller;
    }

    public void setAutotaskscontroller(AutotasksController autotaskscontroller) {
        this.autotaskscontroller = autotaskscontroller;
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
}
