/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Produits;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface ProduitsFacadeLocal {

    void create(Produits produits);

    void edit(Produits produits);

    void remove(Produits produits);

    Produits find(Object id);

    List<Produits> findAll();

    List<Produits> findRange(int[] range);

    List<Produits> findByTypeproduit(String filtreTypeSystem);

    int count();

    public int findCopieDouble(String produitToCount);

    public int findPrixCredit1ans(long idproduitsIN);

    public int findPrixCredit3ans(long idproduitsIN);

    public int findPrixCredit2ans(long idproduitsIN);

    public int findPrixRadio1an(String radio1);

    public int findPrixRadio2ans(String radio1);

    public int findPrixRadio3ans(String radio1);

    public int findPrixLampe1an(String lampe1);

    public int findPrixLampe2ans(String lampe1);

    public int findPrixLampe3ans(String lampe1);

    public int findPrixTorche1an(String torche1);

    public int findPrixTorche2ans(String torche1);

    public int findPrixTorche3ans(String torche1);

    public int findPrixAppSupCash(String filtrePrixCash);
}
