package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-05T11:35:00")
@StaticMetamodel(Devices.class)
public class Devices_ { 

    public static volatile SingularAttribute<Devices, Date> createdAt;
    public static volatile SingularAttribute<Devices, String> name;
    public static volatile SingularAttribute<Devices, Usuario> usuario;
    public static volatile SingularAttribute<Devices, Integer> id;
    public static volatile SingularAttribute<Devices, String> gcmRegid;
    public static volatile SingularAttribute<Devices, String> email;

}