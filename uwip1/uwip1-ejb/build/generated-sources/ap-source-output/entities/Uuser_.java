package entities;

import entities.Organization;
import entities.Profile;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-18T09:08:54")
@StaticMetamodel(Uuser.class)
public class Uuser_ { 

    public static volatile SingularAttribute<Uuser, Profile> idprofile;
    public static volatile SingularAttribute<Uuser, Date> birth;
    public static volatile SingularAttribute<Uuser, String> mdp;
    public static volatile SingularAttribute<Uuser, Organization> idorganization;
    public static volatile SingularAttribute<Uuser, Long> iduuser;
    public static volatile SingularAttribute<Uuser, String> lastname;
    public static volatile SingularAttribute<Uuser, String> login;
    public static volatile SingularAttribute<Uuser, String> firstname;

}