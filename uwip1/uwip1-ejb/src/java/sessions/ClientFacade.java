/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Client;
import entities.Mmoperator;
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
public class ClientFacade extends AbstractFacade<Client> implements ClientFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }

    public List<Mmoperator> findAllOperator(String operat) {
        Query queryOperat = em.createNamedQuery("Client.findAllOperators");
        queryOperat.setParameter("code", operat);
        List listAllOperat = queryOperat.getResultList();
        return listAllOperat;
    }

    @Override
    public int findToUpdateTotalBox(long idclientinstal) {
        Query queryNbreBox = em.createNamedQuery("Client.changeTotalBox");
        queryNbreBox.setParameter("idclient", idclientinstal);
        int reponse1 = queryNbreBox.executeUpdate();
        return reponse1;
    }

    @Override
    public int findCopieDouble(String firstnameToCount, String lastnameToCount) {
        Query queryfindCopie = em.createNamedQuery("Client.findCopieDouble");
        queryfindCopie.setParameter("filtre", firstnameToCount);
        queryfindCopie.setParameter("filtre2", lastnameToCount);
        int nbreCopie = queryfindCopie.getSingleResult().hashCode();
        return nbreCopie;
    }

    public int minId() {
        Query query = em.createNamedQuery("Client.minId");
        List listObj = query.getResultList();
        if (listObj.isEmpty()) {
            return 1;
        } else {
            return ((Integer) listObj.get(0));
        }
    }
    
    public List<Client> findFirstClient() {
        Query queryFirstClient = em.createNamedQuery("Client.minId");
        List listObj3 = queryFirstClient.getResultList();
        return listObj3;
    }
    
    @Override
    public long findIdClient(String filtreClientFirstName, String filtreClientLastName) {
        Query queryfindIdClient = em.createNamedQuery("Client.findIdClientByNAme");
        queryfindIdClient.setParameter("firstname", filtreClientFirstName);
        queryfindIdClient.setParameter("lastname", filtreClientLastName);
        long myIdClient = queryfindIdClient.getSingleResult().hashCode();
        return myIdClient;
    }
}
