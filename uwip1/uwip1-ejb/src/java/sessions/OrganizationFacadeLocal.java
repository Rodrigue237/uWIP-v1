/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Organization;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface OrganizationFacadeLocal {

    void create(Organization organization);

    void edit(Organization organization);

    void remove(Organization organization);

    Organization find(Object id);

    List<Organization> findAll();

    List<Organization> findRange(int[] range);

    int count();

    public int findCopieDouble(String nameToCount);
}
