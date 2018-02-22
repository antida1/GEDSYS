package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-22T16:07:47")
@StaticMetamodel(Sede.class)
public class Sede_ { 

    public static volatile SingularAttribute<Sede, Usuario> creadoPor;
    public static volatile SingularAttribute<Sede, String> codigo;
    public static volatile SingularAttribute<Sede, Short> estado;
    public static volatile SingularAttribute<Sede, Date> fechaModificacion;
    public static volatile SingularAttribute<Sede, Date> fechaCreacion;
    public static volatile SingularAttribute<Sede, Usuario> modificadoPor;
    public static volatile SingularAttribute<Sede, Integer> id;
    public static volatile SingularAttribute<Sede, String> nombre;

}