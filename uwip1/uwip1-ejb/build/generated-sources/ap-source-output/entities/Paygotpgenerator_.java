package entities;

import entities.Credits;
import entities.Oemproductitems;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-18T09:08:54")
@StaticMetamodel(Paygotpgenerator.class)
public class Paygotpgenerator_ { 

    public static volatile SingularAttribute<Paygotpgenerator, String> initialisationstatus;
    public static volatile SingularAttribute<Paygotpgenerator, String> paygsechashtop;
    public static volatile SingularAttribute<Paygotpgenerator, Integer> hashchainlength;
    public static volatile SingularAttribute<Paygotpgenerator, Date> lastmaintenance;
    public static volatile CollectionAttribute<Paygotpgenerator, Credits> creditsCollection;
    public static volatile SingularAttribute<Paygotpgenerator, String> payglifecyclestatus;
    public static volatile SingularAttribute<Paygotpgenerator, String> idpaygproduct;
    public static volatile SingularAttribute<Paygotpgenerator, Date> dateinitialisation;
    public static volatile SingularAttribute<Paygotpgenerator, String> boxstatus;
    public static volatile CollectionAttribute<Paygotpgenerator, Oemproductitems> oemproductitemsCollection;
    public static volatile SingularAttribute<Paygotpgenerator, Integer> maxhcj;
    public static volatile SingularAttribute<Paygotpgenerator, Double> longitude;
    public static volatile SingularAttribute<Paygotpgenerator, Double> latitude;
    public static volatile SingularAttribute<Paygotpgenerator, String> firstcode;
    public static volatile SingularAttribute<Paygotpgenerator, Integer> currenthashindex;
    public static volatile SingularAttribute<Paygotpgenerator, String> paygsechashroot;
    public static volatile SingularAttribute<Paygotpgenerator, String> marquerstyle;
    public static volatile SingularAttribute<Paygotpgenerator, Integer> otpcount;

}