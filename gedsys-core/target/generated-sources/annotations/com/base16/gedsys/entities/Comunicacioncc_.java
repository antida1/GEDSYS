package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Comunicacion;
import com.base16.gedsys.entities.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-21T13:13:40")
@StaticMetamodel(Comunicacioncc.class)
public class Comunicacioncc_ { 

    public static volatile SingularAttribute<Comunicacioncc, Integer> id;
    public static volatile SingularAttribute<Comunicacioncc, Usuario> conCopiaA;
    public static volatile SingularAttribute<Comunicacioncc, Comunicacion> comunicacion;

}