package entities;

import entities.Credits;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-18T09:08:54")
@StaticMetamodel(Produits.class)
public class Produits_ { 

    public static volatile SingularAttribute<Produits, Long> idproduits;
    public static volatile SingularAttribute<Produits, Integer> prixcash;
    public static volatile SingularAttribute<Produits, Integer> prixcredit1an;
    public static volatile SingularAttribute<Produits, Integer> prixcredit2ans;
    public static volatile SingularAttribute<Produits, String> typeproduit;
    public static volatile SingularAttribute<Produits, String> designation;
    public static volatile SingularAttribute<Produits, Integer> prixcredit3ans;
    public static volatile CollectionAttribute<Produits, Credits> creditsCollection;

}