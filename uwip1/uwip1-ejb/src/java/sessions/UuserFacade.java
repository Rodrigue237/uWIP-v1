/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Uuser;
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
public class UuserFacade extends AbstractFacade<Uuser> implements UuserFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UuserFacade() {
        super(Uuser.class);
    }

    @Override
    public Uuser connexion(String login, String mdp) {
        Query query = em.createNamedQuery("Uuser.findByLoginMdp");
        query.setParameter("login", login);
        query.setParameter("mdp", mdp);
        List listObj = query.getResultList();
        if (listObj.isEmpty()) {
            return null;
        } else {
            return (Uuser) listObj.get(0);
        }
    }

    public List<Uuser> findAll2(String profilSA) {
        Query query2 = em.createNamedQuery("Uuser.findAll2");
        query2.setParameter("name", profilSA);
        List listObj2 = query2.getResultList();
        return listObj2;
    }

    public int findCopieDouble(String loginToCount) {
        Query queryfindCopie = em.createNamedQuery("Uuser.findCopieDouble");
        queryfindCopie.setParameter("logins", loginToCount);
        int nbreCopie = queryfindCopie.getSingleResult().hashCode();
        return nbreCopie;
    }
}
