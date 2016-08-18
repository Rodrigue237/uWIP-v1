/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Townlayer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface TownlayerFacadeLocal {

    void create(Townlayer townlayer);

    void edit(Townlayer townlayer);

    void remove(Townlayer townlayer);

    Townlayer find(Object id);

    List<Townlayer> findAll();

    List<Townlayer> findRange(int[] range);

    int count();

    public int findCopieDouble(String nameToCount);
}
