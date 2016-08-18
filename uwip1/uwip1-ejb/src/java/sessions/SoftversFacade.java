/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Softvers;
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
public class SoftversFacade extends AbstractFacade<Softvers> implements SoftversFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SoftversFacade() {
        super(Softvers.class);
    }

    public List<Softvers> findAllSoft() {
        Query querySoftvers = em.createNamedQuery("Softvers.findAllSoft");
        List listObjSoftvers = querySoftvers.getResultList();
        return listObjSoftvers;
    }

    @Override
    public int findCopieDouble(String nameToCount, String versionToCount) {
        Query queryfindCopie = em.createNamedQuery("Softvers.findCopieDouble");
        queryfindCopie.setParameter("filtre", nameToCount);
        queryfindCopie.setParameter("filtre2", versionToCount);
        int nbreCopie = queryfindCopie.getSingleResult().hashCode();
        return nbreCopie;
    }
}
