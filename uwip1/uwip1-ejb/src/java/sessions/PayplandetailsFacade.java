/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Payplandetails;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Seanjackson
 */
@Stateless
public class PayplandetailsFacade extends AbstractFacade<Payplandetails> implements PayplandetailsFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PayplandetailsFacade() {
        super(Payplandetails.class);
    }
}
