package entities;

import entities.Box;
import entities.Client;
import entities.Distributor;
import entities.Organization;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-18T09:08:54")
@StaticMetamodel(Code.class)
public class Code_ { 

    public static volatile SingularAttribute<Code, Date> dategene;
    public static volatile SingularAttribute<Code, Double> price;
    public static volatile SingularAttribute<Code, Organization> idorganization;
    public static volatile SingularAttribute<Code, Long> idcode;
    public static volatile SingularAttribute<Code, BigInteger> timevalue;
    public static volatile SingularAttribute<Code, String> timegene;
    public static volatile SingularAttribute<Code, Box> idboxe;
    public static volatile SingularAttribute<Code, Distributor> iddistributor;
    public static volatile SingularAttribute<Code, Date> enddate;
    public static volatile SingularAttribute<Code, Integer> genekeyused;
    public static volatile SingularAttribute<Code, Client> idclient;
    public static volatile SingularAttribute<Code, BigInteger> codevalue;

}