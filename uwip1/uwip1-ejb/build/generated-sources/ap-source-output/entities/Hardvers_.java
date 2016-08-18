package entities;

import entities.Box;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-18T09:08:54")
@StaticMetamodel(Hardvers.class)
public class Hardvers_ { 

    public static volatile CollectionAttribute<Hardvers, Box> boxCollection;
    public static volatile SingularAttribute<Hardvers, String> updates;
    public static volatile SingularAttribute<Hardvers, String> description;
    public static volatile SingularAttribute<Hardvers, String> name;
    public static volatile SingularAttribute<Hardvers, Date> createddate;
    public static volatile SingularAttribute<Hardvers, Long> idhardvers;
    public static volatile SingularAttribute<Hardvers, String> version;

}