/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Softvers;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface SoftversFacadeLocal {

    void create(Softvers softvers);

    void edit(Softvers softvers);

    void remove(Softvers softvers);

    Softvers find(Object id);

    List<Softvers> findAll();

    List<Softvers> findRange(int[] range);

    List<Softvers> findAllSoft();

    int count();

    public int findCopieDouble(String nameToCount, String versionToCount);
}
