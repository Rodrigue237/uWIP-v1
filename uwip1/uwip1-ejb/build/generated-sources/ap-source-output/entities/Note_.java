package entities;

import entities.Box;
import entities.Client;
import entities.Distributor;
import entities.Organization;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-18T09:08:54")
@StaticMetamodel(Note.class)
public class Note_ { 

    public static volatile SingularAttribute<Note, String> content;
    public static volatile SingularAttribute<Note, Organization> idorganization;
    public static volatile SingularAttribute<Note, Date> datecreated;
    public static volatile SingularAttribute<Note, Box> idboxe;
    public static volatile SingularAttribute<Note, Distributor> iddistributor;
    public static volatile SingularAttribute<Note, Client> idclient;
    public static volatile SingularAttribute<Note, Long> idnote;

}