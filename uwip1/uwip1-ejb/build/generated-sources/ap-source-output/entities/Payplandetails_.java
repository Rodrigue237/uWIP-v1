package entities;

import entities.Oemproductitems;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-18T09:08:54")
@StaticMetamodel(Payplandetails.class)
public class Payplandetails_ { 

    public static volatile SingularAttribute<Payplandetails, Integer> payplanid;
    public static volatile SingularAttribute<Payplandetails, Integer> planpaynumber;
    public static volatile SingularAttribute<Payplandetails, String> idpayplandetails;
    public static volatile SingularAttribute<Payplandetails, String> oemproductid;
    public static volatile SingularAttribute<Payplandetails, Double> payamount;
    public static volatile SingularAttribute<Payplandetails, Date> paydate;
    public static volatile SingularAttribute<Payplandetails, Oemproductitems> idoemproductitems;

}