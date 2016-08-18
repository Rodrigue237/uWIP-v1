/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Paygcodes;
import java.math.BigInteger;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Seanjackson
 */
@Stateless
public class PaygcodesFacade extends AbstractFacade<Paygcodes> implements PaygcodesFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaygcodesFacade() {
        super(Paygcodes.class);
    }

    @Override
    public int findToUpdateCurrentC(int k_forms, String idPAYGprod) {
        Query queryUpdateCurrentC = em.createNamedQuery("Paygcodes.updateCurrentC");
        queryUpdateCurrentC.setParameter("currenthashindex", k_forms);
        queryUpdateCurrentC.setParameter("idpaygproduct", idPAYGprod);
        int reponseUpdateCurrentC = queryUpdateCurrentC.executeUpdate();
        return reponseUpdateCurrentC;
    }
}
