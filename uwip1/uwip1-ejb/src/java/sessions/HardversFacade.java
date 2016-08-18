/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Hardvers;
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
public class HardversFacade extends AbstractFacade<Hardvers> implements HardversFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HardversFacade() {
        super(Hardvers.class);
    }

    public List<Hardvers> findAllHard() {
        Query queryHardvers = em.createNamedQuery("Hardvers.findAllHard");
        List listObjHardvers = queryHardvers.getResultList();
        return listObjHardvers;
    }

    @Override
    public int findCopieDouble(String nameToCount, String versionToCount) {
        Query queryfindCopie = em.createNamedQuery("Hardvers.findCopieDouble");
        queryfindCopie.setParameter("filtre", nameToCount);
        queryfindCopie.setParameter("filtre2", versionToCount);
        int nbreCopie = queryfindCopie.getSingleResult().hashCode();
        return nbreCopie;
    }
}
