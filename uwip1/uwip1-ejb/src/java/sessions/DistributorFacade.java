/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Distributor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Seanjackson
 */
@Stateless
public class DistributorFacade extends AbstractFacade<Distributor> implements DistributorFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DistributorFacade() {
        super(Distributor.class);
    }

    public int findCopieDouble(int numDistributorSaisi) {
        Query queryfindCopie = em.createNamedQuery("Distributor.findCopieDouble");
        queryfindCopie.setParameter("filtre", numDistributorSaisi);
        int nbreCopie = queryfindCopie.getSingleResult().hashCode();
        return nbreCopie;
    }

    @Override
    public List<Distributor> findByMapped() {
        Query queryfindByMapped = em.createNamedQuery("Distributor.findByMapped");
        List listObjDistrib = queryfindByMapped.getResultList();
        return listObjDistrib;
    }
}
