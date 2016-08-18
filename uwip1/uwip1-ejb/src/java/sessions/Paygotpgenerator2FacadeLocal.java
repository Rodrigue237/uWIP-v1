/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Paygotpgenerator2;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface Paygotpgenerator2FacadeLocal {

    void create(Paygotpgenerator2 paygotpgenerator2);

    void edit(Paygotpgenerator2 paygotpgenerator2);

    void remove(Paygotpgenerator2 paygotpgenerator2);

    Paygotpgenerator2 find(Object id);

    List<Paygotpgenerator2> findAll();

    List<Paygotpgenerator2> findRange(int[] range);

    int count();
    
    public int updateSalt(String toUpdate, String idPAYGnow);
    
    public int updateSeed(String toUpdate, String idPAYGnow);
    
    public int updateHroot(String toUpdate, String idPAYGnow);
    
    public int updateHtop(String toUpdate, String idPAYGnow);
    
}
