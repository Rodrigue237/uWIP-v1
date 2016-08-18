/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Payplandetails;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface PayplandetailsFacadeLocal {

    void create(Payplandetails payplandetails);

    void edit(Payplandetails payplandetails);

    void remove(Payplandetails payplandetails);

    Payplandetails find(Object id);

    List<Payplandetails> findAll();

    List<Payplandetails> findRange(int[] range);

    int count();
}
