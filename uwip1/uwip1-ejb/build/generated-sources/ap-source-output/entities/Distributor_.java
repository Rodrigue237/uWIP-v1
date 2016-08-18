package entities;

import entities.Code;
import entities.Note;
import entities.Organization;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-18T09:08:54")
@StaticMetamodel(Distributor.class)
public class Distributor_ { 

    public static volatile SingularAttribute<Distributor, String> municipality;
    public static volatile SingularAttribute<Distributor, String> lastname;
    public static volatile SingularAttribute<Distributor, String> firstname;
    public static volatile SingularAttribute<Distributor, BigInteger> totalcodesolde;
    public static volatile CollectionAttribute<Distributor, Code> codeCollection;
    public static volatile CollectionAttribute<Distributor, Note> noteCollection;
    public static volatile SingularAttribute<Distributor, String> shoppic;
    public static volatile SingularAttribute<Distributor, Date> lasttransact;
    public static volatile SingularAttribute<Distributor, Integer> distribuniqnumber;
    public static volatile SingularAttribute<Distributor, Organization> idorganization;
    public static volatile SingularAttribute<Distributor, String> village;
    public static volatile SingularAttribute<Distributor, BigInteger> totaltimesolde;
    public static volatile SingularAttribute<Distributor, String> shoplocation;
    public static volatile SingularAttribute<Distributor, String> cnipic;
    public static volatile SingularAttribute<Distributor, Double> longitude;
    public static volatile SingularAttribute<Distributor, Long> iddistributor;
    public static volatile SingularAttribute<Distributor, Double> latitude;
    public static volatile SingularAttribute<Distributor, Integer> marquerstatus;

}