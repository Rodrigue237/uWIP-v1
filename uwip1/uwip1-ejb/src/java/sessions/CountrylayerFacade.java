/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Countrylayer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Seanjackson
 */
@Stateless
public class CountrylayerFacade extends AbstractFacade<Countrylayer> implements CountrylayerFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CountrylayerFacade() {
        super(Countrylayer.class);
    }

    @Override
    public int findCopieDouble(String nameToCount) {
        Query queryfindCopie = em.createNamedQuery("Countrylayer.findCopieDouble");
        queryfindCopie.setParameter("filtre", nameToCount);
        int nbreCopie = queryfindCopie.getSingleResult().hashCode();
        return nbreCopie;
    }
}
