/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Distributor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface DistributorFacadeLocal {

    void create(Distributor distributor);

    void edit(Distributor distributor);

    void remove(Distributor distributor);

    Distributor find(Object id);

    List<Distributor> findAll();

    List<Distributor> findRange(int[] range);

    List<Distributor> findByMapped();

    int count();

    public int findCopieDouble(int numDistributorSaisi);
}
