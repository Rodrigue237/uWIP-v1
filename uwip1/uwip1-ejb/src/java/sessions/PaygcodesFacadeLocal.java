/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Paygcodes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface PaygcodesFacadeLocal {

    void create(Paygcodes paygcodes);

    void edit(Paygcodes paygcodes);

    void remove(Paygcodes paygcodes);

    Paygcodes find(Object id);

    List<Paygcodes> findAll();

    List<Paygcodes> findRange(int[] range);

    int count();

    public int findToUpdateCurrentC(int k_forms, String idPAYGprod);
}
