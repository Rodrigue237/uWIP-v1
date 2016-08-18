package entities;

import entities.Client;
import entities.Paygotpgenerator;
import entities.Produits;
import entities.Termes;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-18T09:08:54")
@StaticMetamodel(Credits.class)
public class Credits_ { 

    public static volatile SingularAttribute<Credits, Integer> totalgraceused;
    public static volatile SingularAttribute<Credits, Integer> prix1mois;
    public static volatile SingularAttribute<Credits, Date> codeenddate;
    public static volatile SingularAttribute<Credits, Integer> totalmontantpaye;
    public static volatile SingularAttribute<Credits, Integer> qteappsupcash1;
    public static volatile SingularAttribute<Credits, Termes> idtermes;
    public static volatile SingularAttribute<Credits, Integer> totalcredits;
    public static volatile SingularAttribute<Credits, Client> idclient;
    public static volatile SingularAttribute<Credits, Integer> totalmontantrestant;
    public static volatile SingularAttribute<Credits, String> creditstatus;
    public static volatile SingularAttribute<Credits, Produits> idproduits;
    public static volatile SingularAttribute<Credits, Integer> qteappsupcash2;
    public static volatile SingularAttribute<Credits, Integer> qteappsupcash3;
    public static volatile SingularAttribute<Credits, Long> idcredits;
    public static volatile SingularAttribute<Credits, String> nomclient;
    public static volatile SingularAttribute<Credits, Integer> numinstall;
    public static volatile SingularAttribute<Credits, String> installstatus;
    public static volatile SingularAttribute<Credits, Integer> prix1jour;
    public static volatile SingularAttribute<Credits, Integer> qteappsupcredit3;
    public static volatile SingularAttribute<Credits, Integer> qteappsupcredit2;
    public static volatile SingularAttribute<Credits, Integer> qteappsupcredit1;
    public static volatile SingularAttribute<Credits, Integer> acomptetotal;
    public static volatile SingularAttribute<Credits, Integer> acomptecredit;
    public static volatile SingularAttribute<Credits, Integer> totalgracerestant;
    public static volatile SingularAttribute<Credits, Paygotpgenerator> idpaygproduct;
    public static volatile SingularAttribute<Credits, String> lastcode;
    public static volatile SingularAttribute<Credits, Date> creditexpirationdate;
    public static volatile SingularAttribute<Credits, Date> installdate;

}