/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Box;
import entities.Client;
import entities.Distributor;
import entities.Note;
import entities.Organization;
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
public class NoteFacade extends AbstractFacade<Note> implements NoteFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NoteFacade() {
        super(Note.class);
    }

    @Override
    public List<Organization> findAllOrganization() {
        Query queryOrganization = em.createNamedQuery("Note.findAllOrganization");
        List listAllOrganization = queryOrganization.getResultList();
        return listAllOrganization;
    }

    @Override
    public List<Box> findAllBox() {
        Query queryBox = em.createNamedQuery("Note.findAllBox");
        List listAllBox = queryBox.getResultList();
        return listAllBox;
    }

    @Override
    public List<Client> findAllClient() {
        Query queryClient = em.createNamedQuery("Note.findAllClient");
        List listAllClient = queryClient.getResultList();
        return listAllClient;
    }

    @Override
    public List<Distributor> findAllDistributor() {
        Query queryDistributor = em.createNamedQuery("Note.findAllDistributor");
        List listAllDistributor = queryDistributor.getResultList();
        return listAllDistributor;
    }
}
