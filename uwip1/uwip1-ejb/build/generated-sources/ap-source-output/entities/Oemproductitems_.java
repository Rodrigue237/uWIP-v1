package entities;

import entities.Paygotpgenerator;
import entities.Payplandetails;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-18T09:08:54")
@StaticMetamodel(Oemproductitems.class)
public class Oemproductitems_ { 

    public static volatile CollectionAttribute<Oemproductitems, Payplandetails> payplandetailsCollection;
    public static volatile SingularAttribute<Oemproductitems, Paygotpgenerator> idpaygproduct;
    public static volatile SingularAttribute<Oemproductitems, String> productcode;
    public static volatile SingularAttribute<Oemproductitems, String> oemlifecyclestatus;
    public static volatile SingularAttribute<Oemproductitems, String> batchnumber;
    public static volatile SingularAttribute<Oemproductitems, String> idoemproductitems;

}