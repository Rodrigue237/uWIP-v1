package entities;

import entities.Box;
import entities.Countrylayer;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-18T09:08:54")
@StaticMetamodel(Townlayer.class)
public class Townlayer_ { 

    public static volatile CollectionAttribute<Townlayer, Box> boxCollection;
    public static volatile SingularAttribute<Townlayer, String> layer;
    public static volatile SingularAttribute<Townlayer, Countrylayer> idcountrylayer;
    public static volatile SingularAttribute<Townlayer, Long> idtownlayer;

}