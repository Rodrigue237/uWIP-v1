/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Mmtransaction;
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
public class MmtransactionFacade extends AbstractFacade<Mmtransaction> implements MmtransactionFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MmtransactionFacade() {
        super(Mmtransaction.class);
    }

    public int findCopieDouble(String idNewTransactMoney) {
        Query queryfindCopie = em.createNamedQuery("Mmtransaction.findCopieDouble");
        queryfindCopie.setParameter("filtre", idNewTransactMoney);
        int nbreCopie = queryfindCopie.getSingleResult().hashCode();
        return nbreCopie;
    }

    @Override
    public int findNumPcb(int numBoxCode) {
        Query queryfindNumPcb = em.createNamedQuery("Mmtransaction.findNumPcb");
        queryfindNumPcb.setParameter("numbox", numBoxCode);
        int myNumPcb = queryfindNumPcb.getSingleResult().hashCode();
        return myNumPcb;
    }

    public int maxIdPlus1() {
        Query querymaxIdPlus1 = em.createNamedQuery("Utilisateur.nextId");
        List listObj = querymaxIdPlus1.getResultList();
        if (listObj.isEmpty()) {
            return 1;
        } else {
            return ((Integer) listObj.get(0) + 1);
        }
    }
}
