package entities;

import entities.Credits;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-18T09:08:54")
@StaticMetamodel(Termes.class)
public class Termes_ { 

    public static volatile SingularAttribute<Termes, String> acompte;
    public static volatile SingularAttribute<Termes, String> nomcredit;
    public static volatile SingularAttribute<Termes, Integer> nbremensualite;
    public static volatile SingularAttribute<Termes, Long> idtermes;
    public static volatile SingularAttribute<Termes, Integer> nbrejourgrace;
    public static volatile SingularAttribute<Termes, Integer> dureecredit;
    public static volatile CollectionAttribute<Termes, Credits> creditsCollection;

}