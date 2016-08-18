/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Seanjackson
 */
public class DateOperations {

    static int UN = 1;
    static int DOUZE = 12;

    public long joursEcart(Date date1, Date date2) {

        Calendar calStr1 = Calendar.getInstance();
        Calendar calStr2 = Calendar.getInstance();
        Calendar calStr0 = Calendar.getInstance();

        int nbMois = 0;
        int nbAnnees = 0;
        long nbJours = 0;

        if (date1.equals(date2)) {
            return 0;
        }

        calStr1.setTime(date1);
        calStr2.setTime(date2);

        nbMois = 0;
        while (calStr1.before(calStr2)) {
            calStr1.add(GregorianCalendar.MONTH, UN);
            if (calStr1.before(calStr2) || calStr1.equals(calStr2)) {
                nbMois++;
            }
        }
        nbAnnees = (nbMois / DOUZE);
        nbMois = (nbMois - (nbAnnees * DOUZE));

        calStr0 = Calendar.getInstance();
        calStr0.setTime(date1);
        calStr0.add(GregorianCalendar.YEAR, nbAnnees);
        calStr0.add(GregorianCalendar.MONTH, nbMois);
        nbJours = (calStr2.getTimeInMillis() - calStr0.getTimeInMillis()) / 86400000;

        //System.out.print("Nb Annees : " + nbAnnees + "\n");
        //System.out.print("Nb Mois : " + nbMois + "\n");
        System.out.print("Nb Jours : " + nbJours + "\n");
        return nbJours;

    }
}
