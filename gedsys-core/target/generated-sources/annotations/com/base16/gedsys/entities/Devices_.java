package com.base16.gedsys.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-26T05:22:31")
@StaticMetamodel(Devices.class)
public class Devices_ { 

    public static volatile SingularAttribute<Devices, Date> createdAt;
    public static volatile SingularAttribute<Devices, String> name;
    public static volatile SingularAttribute<Devices, Integer> id;
    public static volatile SingularAttribute<Devices, String> gcmRegid;
    public static volatile SingularAttribute<Devices, String> email;

}