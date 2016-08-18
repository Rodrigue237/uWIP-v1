package entities;

import entities.Box;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-18T09:08:54")
@StaticMetamodel(Softvers.class)
public class Softvers_ { 

    public static volatile CollectionAttribute<Softvers, Box> boxCollection;
    public static volatile SingularAttribute<Softvers, String> updates;
    public static volatile SingularAttribute<Softvers, Long> idsoftvers;
    public static volatile SingularAttribute<Softvers, String> description;
    public static volatile SingularAttribute<Softvers, String> name;
    public static volatile SingularAttribute<Softvers, Date> createddate;
    public static volatile SingularAttribute<Softvers, String> version;

}