/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Hardvers;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface HardversFacadeLocal {

    void create(Hardvers hardvers);

    void edit(Hardvers hardvers);

    void remove(Hardvers hardvers);

    Hardvers find(Object id);

    List<Hardvers> findAll();

    List<Hardvers> findRange(int[] range);

    List<Hardvers> findAllHard();

    int count();

    public int findCopieDouble(String nameToCount, String versionToCount);
}
