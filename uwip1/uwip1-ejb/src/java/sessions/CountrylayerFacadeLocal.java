/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Countrylayer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface CountrylayerFacadeLocal {

    void create(Countrylayer countrylayer);

    void edit(Countrylayer countrylayer);

    void remove(Countrylayer countrylayer);

    Countrylayer find(Object id);

    List<Countrylayer> findAll();

    List<Countrylayer> findRange(int[] range);

    int count();

    public int findCopieDouble(String nameToCount);
}
