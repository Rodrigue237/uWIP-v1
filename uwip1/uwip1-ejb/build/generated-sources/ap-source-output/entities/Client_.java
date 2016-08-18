package entities;

import entities.Box;
import entities.Code;
import entities.Contract;
import entities.Credits;
import entities.Mmoperator;
import entities.Note;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-18T09:08:54")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, Integer> clientuniqnumber;
    public static volatile SingularAttribute<Client, String> firstcontact;
    public static volatile SingularAttribute<Client, String> municipality;
    public static volatile CollectionAttribute<Client, Contract> contractCollection;
    public static volatile SingularAttribute<Client, String> lastname;
    public static volatile SingularAttribute<Client, String> firstname;
    public static volatile SingularAttribute<Client, String> phonenexttel;
    public static volatile SingularAttribute<Client, Long> idclient;
    public static volatile CollectionAttribute<Client, Code> codeCollection;
    public static volatile CollectionAttribute<Client, Credits> creditsCollection;
    public static volatile CollectionAttribute<Client, Note> noteCollection;
    public static volatile CollectionAttribute<Client, Box> boxCollection;
    public static volatile SingularAttribute<Client, String> mobilemoney;
    public static volatile SingularAttribute<Client, String> nationality;
    public static volatile SingularAttribute<Client, String> phoneorange;
    public static volatile SingularAttribute<Client, String> village;
    public static volatile SingularAttribute<Client, String> phonemtn;
    public static volatile SingularAttribute<Client, String> preferredphone;
    public static volatile SingularAttribute<Client, Integer> totalbox;
    public static volatile SingularAttribute<Client, Date> acquisitdate;
    public static volatile SingularAttribute<Client, String> cnipic;
    public static volatile SingularAttribute<Client, Mmoperator> idoperator;
    public static volatile SingularAttribute<Client, String> signedistinctif;

}