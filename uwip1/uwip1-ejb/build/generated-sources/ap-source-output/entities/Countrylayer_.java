package entities;

import entities.Townlayer;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-18T09:08:54")
@StaticMetamodel(Countrylayer.class)
public class Countrylayer_ { 

    public static volatile CollectionAttribute<Countrylayer, Townlayer> townlayerCollection;
    public static volatile SingularAttribute<Countrylayer, Long> idcountrylayer;
    public static volatile SingularAttribute<Countrylayer, String> layer;

}