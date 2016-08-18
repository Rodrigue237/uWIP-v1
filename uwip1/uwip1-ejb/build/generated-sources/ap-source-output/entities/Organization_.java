package entities;

import entities.Box;
import entities.Code;
import entities.Distributor;
import entities.Note;
import entities.Uuser;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-18T09:08:54")
@StaticMetamodel(Organization.class)
public class Organization_ { 

    public static volatile CollectionAttribute<Organization, Box> boxCollection;
    public static volatile CollectionAttribute<Organization, Uuser> uuserCollection;
    public static volatile SingularAttribute<Organization, Long> idorganization;
    public static volatile SingularAttribute<Organization, String> name;
    public static volatile CollectionAttribute<Organization, Distributor> distributorCollection;
    public static volatile SingularAttribute<Organization, BigInteger> capital;
    public static volatile CollectionAttribute<Organization, Code> codeCollection;
    public static volatile SingularAttribute<Organization, String> activity;
    public static volatile CollectionAttribute<Organization, Note> noteCollection;

}