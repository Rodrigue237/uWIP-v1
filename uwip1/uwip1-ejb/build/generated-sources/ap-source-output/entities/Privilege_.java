package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-18T09:08:54")
@StaticMetamodel(Privilege.class)
public class Privilege_ { 

    public static volatile SingularAttribute<Privilege, Long> idprivilege;
    public static volatile SingularAttribute<Privilege, String> description;
    public static volatile SingularAttribute<Privilege, String> key;
    public static volatile SingularAttribute<Privilege, Integer> idprivilegeacces;

}