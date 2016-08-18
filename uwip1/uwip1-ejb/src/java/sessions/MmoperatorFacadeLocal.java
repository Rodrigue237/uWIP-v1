/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Mmoperator;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface MmoperatorFacadeLocal {

    void create(Mmoperator mmoperator);

    void edit(Mmoperator mmoperator);

    void remove(Mmoperator mmoperator);

    Mmoperator find(Object id);

    List<Mmoperator> findAll();

    List<Mmoperator> findRange(int[] range);

    int count();

    public int findCopieDouble(String codeToCount);
}
