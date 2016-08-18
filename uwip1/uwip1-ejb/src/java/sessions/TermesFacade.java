/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Termes;
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
public class TermesFacade extends AbstractFacade<Termes> implements TermesFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TermesFacade() {
        super(Termes.class);
    }

    @Override
    public int findCopieDouble(String termesToCount) {
        Query queryfindCopie = em.createNamedQuery("Termes.findCopieDouble");
        queryfindCopie.setParameter("filtre", termesToCount);
        int nbreCopie = queryfindCopie.getSingleResult().hashCode();
        return nbreCopie;
    }
    
    @Override
    public List<Termes> findAllValideTermes(String filtreValidTermes) {
        Query queryfindAllValideTermes = em.createNamedQuery("Termes.findAllValideTermes");
        queryfindAllValideTermes.setParameter("nomcredit", filtreValidTermes);
        List listAllValideTermes = queryfindAllValideTermes.getResultList();
        return listAllValideTermes;
    }

    @Override
    public int findDureeCredit(long idtermesIN) {
        Query queryfindDureeCredit = em.createNamedQuery("Termes.findDureeCredit");
        queryfindDureeCredit.setParameter("idtermes", idtermesIN);
        int myDureeCredit = queryfindDureeCredit.getSingleResult().hashCode();
        return myDureeCredit;
    }

    @Override
    public String findAcomptePourcent(long idtermesIN) {
        Query queryfindAcomptePourcent = em.createNamedQuery("Termes.findAcomptePourcent");
        queryfindAcomptePourcent.setParameter("idtermes", idtermesIN);
        String myAcomptePourcent = (String) queryfindAcomptePourcent.getSingleResult();
        return myAcomptePourcent;
    }

    @Override
    public int findNbreMensualite(long idtermesIN) {
        Query queryfindNbreMensualite = em.createNamedQuery("Termes.findNbreMensualite");
        queryfindNbreMensualite.setParameter("idtermes", idtermesIN);
        int myNbreMensualite = queryfindNbreMensualite.getSingleResult().hashCode();
        return myNbreMensualite;
    }
}
