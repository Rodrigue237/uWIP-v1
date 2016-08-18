/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Oemproductitems;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface OemproductitemsFacadeLocal {

    void create(Oemproductitems oemproductitems);

    void edit(Oemproductitems oemproductitems);

    void remove(Oemproductitems oemproductitems);

    Oemproductitems find(Object id);

    List<Oemproductitems> findAll();

    List<Oemproductitems> findRange(int[] range);

    int count();
}
