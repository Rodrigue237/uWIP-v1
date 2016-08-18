package entities;

import entities.Box;
import entities.Client;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-18T09:08:54")
@StaticMetamodel(Contract.class)
public class Contract_ { 

    public static volatile SingularAttribute<Contract, Integer> numusersystem;
    public static volatile SingularAttribute<Contract, String> contractpic;
    public static volatile SingularAttribute<Contract, String> natconnect;
    public static volatile SingularAttribute<Contract, String> municipality;
    public static volatile SingularAttribute<Contract, Box> idboxe;
    public static volatile SingularAttribute<Contract, Double> totalmoneypaid;
    public static volatile SingularAttribute<Contract, Double> totalmoneydue;
    public static volatile SingularAttribute<Contract, Client> idclient;
    public static volatile SingularAttribute<Contract, Double> moneybalance;
    public static volatile SingularAttribute<Contract, Date> desinstalldate;
    public static volatile SingularAttribute<Contract, String> homepic;
    public static volatile SingularAttribute<Contract, Date> datecontra;
    public static volatile SingularAttribute<Contract, String> village;
    public static volatile SingularAttribute<Contract, String> typecontrat;
    public static volatile SingularAttribute<Contract, String> userofsystem;
    public static volatile SingularAttribute<Contract, Integer> phoneofusersystem;
    public static volatile SingularAttribute<Contract, String> installstatus;
    public static volatile SingularAttribute<Contract, Date> installdate;
    public static volatile SingularAttribute<Contract, Date> estimnexttransact;
    public static volatile SingularAttribute<Contract, String> typeinstall;
    public static volatile SingularAttribute<Contract, Long> idcontract;

}