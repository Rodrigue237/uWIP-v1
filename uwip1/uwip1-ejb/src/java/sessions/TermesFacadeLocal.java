/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Termes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface TermesFacadeLocal {

    void create(Termes termes);

    void edit(Termes termes);

    void remove(Termes termes);

    Termes find(Object id);

    List<Termes> findAll();

    List<Termes> findRange(int[] range);
    
    List<Termes> findAllValideTermes(String filtreValidTermes);

    int count();

    public int findCopieDouble(String termesToCount);

    public int findDureeCredit(long idtermesIN);

    public String findAcomptePourcent(long idtermesIN);

    public int findNbreMensualite(long idtermesIN);
}
