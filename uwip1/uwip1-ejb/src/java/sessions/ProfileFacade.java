/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Privilege;
import entities.Profile;
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
public class ProfileFacade extends AbstractFacade<Profile> implements ProfileFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfileFacade() {
        super(Profile.class);
    }

    @Override
    public List<Profile> findAll2(String profilSA) {
        Query queryProfil = em.createNamedQuery("Profile.findAll2");
        queryProfil.setParameter("name", profilSA);
        List listObjPro = queryProfil.getResultList();
        return listObjPro;
    }

    @Override
    public List<Privilege> findAllPrivilege() {
        Query queryPrivilege = em.createNamedQuery("Profile.findAllPrivilege");
        List listObjPrivilege = queryPrivilege.getResultList();
        return listObjPrivilege;
    }

    @Override
    public Privilege findByKey(String key) {
        Query queryfindByKey = em.createNamedQuery("Profile.findByKey");
        queryfindByKey.setParameter("key", key);
        List listObjKey = queryfindByKey.getResultList();
        return (Privilege) listObjKey.get(0);
    }
}
