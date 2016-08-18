/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Oemproductitems;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Seanjackson
 */
@Stateless
public class OemproductitemsFacade extends AbstractFacade<Oemproductitems> implements OemproductitemsFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OemproductitemsFacade() {
        super(Oemproductitems.class);
    }
}
