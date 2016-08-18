/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Produits;
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
public class ProduitsFacade extends AbstractFacade<Produits> implements ProduitsFacadeLocal {

    @PersistenceContext(unitName = "uwip1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProduitsFacade() {
        super(Produits.class);
    }

    @Override
    public int findCopieDouble(String produitToCount) {
        Query queryfindCopie = em.createNamedQuery("Produits.findCopieDouble");
        queryfindCopie.setParameter("filtre", produitToCount);
        int nbreCopie = queryfindCopie.getSingleResult().hashCode();
        return nbreCopie;
    }

    @Override
    public List<Produits> findByTypeproduit(String filtreTypeSystem) {
        Query queryfindProduitSystem = em.createNamedQuery("Produits.findByTypeproduit");
        queryfindProduitSystem.setParameter("typeproduit", filtreTypeSystem);
        List listObjProduitSystem = queryfindProduitSystem.getResultList();
        return listObjProduitSystem;
    }

    @Override
    public int findPrixCredit1ans(long idproduitsIN) {
        Query queryfindPrixCredit1ans = em.createNamedQuery("Produits.findPrixCredit1ans");
        queryfindPrixCredit1ans.setParameter("idproduits", idproduitsIN);
        int myPrixCredit1ans = queryfindPrixCredit1ans.getSingleResult().hashCode();
        return myPrixCredit1ans;
    }

    @Override
    public int findPrixCredit2ans(long idproduitsIN) {
        Query queryfindPrixCredit2ans = em.createNamedQuery("Produits.findPrixCredit2ans");
        queryfindPrixCredit2ans.setParameter("idproduits", idproduitsIN);
        int myPrixCredit2ans = queryfindPrixCredit2ans.getSingleResult().hashCode();
        return myPrixCredit2ans;
    }

    @Override
    public int findPrixCredit3ans(long idproduitsIN) {
        Query queryfindPrixCredit3ans = em.createNamedQuery("Produits.findPrixCredit3ans");
        queryfindPrixCredit3ans.setParameter("idproduits", idproduitsIN);
        int myPrixCredit3ans = queryfindPrixCredit3ans.getSingleResult().hashCode();
        return myPrixCredit3ans;
    }

    @Override
    public int findPrixRadio1an(String radio1) {
        Query qry1 = em.createNamedQuery("Produits.findPrixRadio1an");
        qry1.setParameter("designation", radio1);
        int myReponse = qry1.getSingleResult().hashCode();
        return myReponse;
    }

    @Override
    public int findPrixRadio2ans(String radio1) {
        Query qry1 = em.createNamedQuery("Produits.findPrixRadio2ans");
        qry1.setParameter("designation", radio1);
        int myReponse = qry1.getSingleResult().hashCode();
        return myReponse;
    }

    @Override
    public int findPrixRadio3ans(String radio1) {
        Query qry1 = em.createNamedQuery("Produits.findPrixRadio3ans");
        qry1.setParameter("designation", radio1);
        int myReponse = qry1.getSingleResult().hashCode();
        return myReponse;
    }

    @Override
    public int findPrixLampe1an(String lampe1) {
        Query qry1 = em.createNamedQuery("Produits.findPrixLampe1an");
        qry1.setParameter("designation", lampe1);
        int myReponse = qry1.getSingleResult().hashCode();
        return myReponse;
    }

    @Override
    public int findPrixLampe2ans(String lampe1) {
        Query qry1 = em.createNamedQuery("Produits.findPrixLampe2ans");
        qry1.setParameter("designation", lampe1);
        int myReponse = qry1.getSingleResult().hashCode();
        return myReponse;
    }

    @Override
    public int findPrixLampe3ans(String lampe1) {
        Query qry1 = em.createNamedQuery("Produits.findPrixLampe3ans");
        qry1.setParameter("designation", lampe1);
        int myReponse = qry1.getSingleResult().hashCode();
        return myReponse;
    }

    @Override
    public int findPrixTorche1an(String torche1) {
        Query qry1 = em.createNamedQuery("Produits.findPrixTorche1an");
        qry1.setParameter("designation", torche1);
        int myReponse = qry1.getSingleResult().hashCode();
        return myReponse;
    }

    @Override
    public int findPrixTorche2ans(String torche1) {
        Query qry1 = em.createNamedQuery("Produits.findPrixTorche2ans");
        qry1.setParameter("designation", torche1);
        int myReponse = qry1.getSingleResult().hashCode();
        return myReponse;
    }

    @Override
    public int findPrixTorche3ans(String torche1) {
        Query qry1 = em.createNamedQuery("Produits.findPrixTorche3ans");
        qry1.setParameter("designation", torche1);
        int myReponse = qry1.getSingleResult().hashCode();
        return myReponse;
    }

    @Override
    public int findPrixAppSupCash(String filtrePrixCash) {
        Query qry2 = em.createNamedQuery("Produits.findPrixAppSupCash");
        qry2.setParameter("designation", filtrePrixCash);
        int myReponse = qry2.getSingleResult().hashCode();
        return myReponse;
    }
}
