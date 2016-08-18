/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Contract;
import java.util.Date;
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
public class ContractFacade extends AbstractFacade<Contract> implements ContractFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContractFacade() {
        super(Contract.class);
    }

    @Override
    public List<Contract> findByIdcontract(Long idcontract) {
        Query query = em.createNamedQuery("Contract.findByIdcontract");
        query.setParameter("idcontract", idcontract);
        List listObj = query.getResultList();
        return listObj;
    }

    @Override
    public Long findMaxIdContract() {
        Query query8 = em.createNamedQuery("Contract.maxContractId");
        List myMaxIdContract = query8.getResultList();
        return ((Long) myMaxIdContract.get(0) + 0);
    }

    @Override
    public int findLastClientContract(long reqMaxIdContract) {
        Query queryfindclient = em.createNamedQuery("Contract.lastClientContract");
        queryfindclient.setParameter("idcontract", reqMaxIdContract);
        queryfindclient.setFirstResult(0);
        queryfindclient.setMaxResults(1);
        int myLastClient = queryfindclient.getSingleResult().hashCode();
        return myLastClient;
    }

    @Override
    public int findLastBoxContract(long reqMaxIdContract) {
        Query queryfindbox = em.createNamedQuery("Contract.lastBoxContract");
        queryfindbox.setParameter("idcontract", reqMaxIdContract);
        int myLastBox = queryfindbox.getSingleResult().hashCode();
        return myLastBox;
    }

    @Override
    public int updateBalance(long moneyBalance, long reqMaxIdContract) {
        Query queryupdateBalance = em.createNamedQuery("Contract.balanceUpdate");
        queryupdateBalance.setParameter("idcontract", reqMaxIdContract);
        queryupdateBalance.setParameter("moneybalance", moneyBalance);
        int reponseupdateBalance = queryupdateBalance.executeUpdate();
        return reponseupdateBalance;
    }

    @Override
    public int updateDesinstallDate(Date desinstallDate, String desactivStatus, long idlastcontratBox) {
        Query queryupdateDesinstallDate = em.createNamedQuery("Contract.updateDesinstallDate");
        queryupdateDesinstallDate.setParameter("idcontract", idlastcontratBox);
        queryupdateDesinstallDate.setParameter("desinstalldate", desinstallDate);
        queryupdateDesinstallDate.setParameter("installstatus", desactivStatus);
        int reponseupdateDesinstallDate = queryupdateDesinstallDate.executeUpdate();
        return reponseupdateDesinstallDate;
    }
}
