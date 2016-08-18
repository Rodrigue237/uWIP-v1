package entities;

import entities.Client;
import entities.Code;
import entities.Contract;
import entities.Hardvers;
import entities.Note;
import entities.Organization;
import entities.Softvers;
import entities.Townlayer;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-18T09:08:54")
@StaticMetamodel(Box.class)
public class Box_ { 

    public static volatile SingularAttribute<Box, Integer> genekeyactiv;
    public static volatile SingularAttribute<Box, String> currentlocation;
    public static volatile SingularAttribute<Box, Date> firstinstaldate;
    public static volatile SingularAttribute<Box, Integer> maintenancestatus;
    public static volatile SingularAttribute<Box, Long> idboxe;
    public static volatile SingularAttribute<Box, Integer> totalinstall;
    public static volatile SingularAttribute<Box, Client> idclient;
    public static volatile SingularAttribute<Box, String> refbox;
    public static volatile SingularAttribute<Box, Hardvers> idhardvers;
    public static volatile SingularAttribute<Box, Date> lasttransact;
    public static volatile SingularAttribute<Box, BigInteger> estimtotaltimeadd;
    public static volatile SingularAttribute<Box, Integer> numpcb;
    public static volatile SingularAttribute<Box, String> userofsystem;
    public static volatile SingularAttribute<Box, Integer> numbox;
    public static volatile SingularAttribute<Box, Double> longitude;
    public static volatile SingularAttribute<Box, Integer> marquerstatus;
    public static volatile SingularAttribute<Box, String> atelier;
    public static volatile SingularAttribute<Box, Integer> kpi1;
    public static volatile SingularAttribute<Box, Date> lastinstaldate;
    public static volatile SingularAttribute<Box, Integer> kpi2;
    public static volatile SingularAttribute<Box, Integer> kpi3;
    public static volatile CollectionAttribute<Box, Contract> contractCollection;
    public static volatile SingularAttribute<Box, Date> lastmaintenance;
    public static volatile SingularAttribute<Box, BigInteger> lasttimecodevalue;
    public static volatile SingularAttribute<Box, String> hardwareversion;
    public static volatile SingularAttribute<Box, Townlayer> idtownlayer;
    public static volatile SingularAttribute<Box, Date> estimatedenddate;
    public static volatile CollectionAttribute<Box, Code> codeCollection;
    public static volatile CollectionAttribute<Box, Note> noteCollection;
    public static volatile SingularAttribute<Box, String> country;
    public static volatile SingularAttribute<Box, String> softwareversion;
    public static volatile SingularAttribute<Box, Integer> idlastcontract;
    public static volatile SingularAttribute<Box, Organization> idorganization;
    public static volatile SingularAttribute<Box, Integer> phoneofusersystem;
    public static volatile SingularAttribute<Box, Softvers> idsoftvers;
    public static volatile SingularAttribute<Box, BigInteger> estimlefttimepaid;
    public static volatile SingularAttribute<Box, BigInteger> totaltimepaid;
    public static volatile SingularAttribute<Box, Double> latitude;
    public static volatile SingularAttribute<Box, String> marquerstyle;

}