/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Mmoperator;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Seanjackson
 */
@Stateless
public class MmoperatorFacade extends AbstractFacade<Mmoperator> implements MmoperatorFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MmoperatorFacade() {
        super(Mmoperator.class);
    }

    @Override
    public int findCopieDouble(String codeToCount) {
        Query queryfindCopie = em.createNamedQuery("Mmoperator.findCopieDouble");
        queryfindCopie.setParameter("filtre", codeToCount);
        int nbreCopie = queryfindCopie.getSingleResult().hashCode();
        return nbreCopie;
    }
}
