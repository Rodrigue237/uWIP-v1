/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Paygotpgenerator2;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Seanjackson
 */
@Stateless
public class Paygotpgenerator2Facade extends AbstractFacade<Paygotpgenerator2> implements Paygotpgenerator2FacadeLocal {
    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Paygotpgenerator2Facade() {
        super(Paygotpgenerator2.class);
    }
    
    @Override
    public int updateSalt(String toUpdate, String idPAYGnow) {
        Query queryupdateSalt = em.createNamedQuery("Paygotpgenerator2.updateSalt");
        queryupdateSalt.setParameter("salt", toUpdate);
        queryupdateSalt.setParameter("paygproductid", idPAYGnow);
        int reponseOfUpdate = queryupdateSalt.executeUpdate();
        return reponseOfUpdate;
    }
    
    @Override
    public int updateSeed(String toUpdate, String idPAYGnow) {
        Query queryupdateSeed = em.createNamedQuery("Paygotpgenerator2.updateSeed");
        queryupdateSeed.setParameter("seed", toUpdate);
        queryupdateSeed.setParameter("paygproductid", idPAYGnow);
        int reponseOfUpdate = queryupdateSeed.executeUpdate();
        return reponseOfUpdate;
    }
    
    @Override
    public int updateHroot(String toUpdate, String idPAYGnow) {
        Query queryupdateHroot = em.createNamedQuery("Paygotpgenerator2.updateHroot");
        queryupdateHroot.setParameter("hroot", toUpdate);
        queryupdateHroot.setParameter("paygproductid", idPAYGnow);
        int reponseOfUpdate = queryupdateHroot.executeUpdate();
        return reponseOfUpdate;
    }
    
    @Override
    public int updateHtop(String toUpdate, String idPAYGnow) {
        Query queryupdateHtop = em.createNamedQuery("Paygotpgenerator2.updateHtop");
        queryupdateHtop.setParameter("htop", toUpdate);
        queryupdateHtop.setParameter("paygproductid", idPAYGnow);
        int reponseOfUpdate = queryupdateHtop.executeUpdate();
        return reponseOfUpdate;
    }
    
}
