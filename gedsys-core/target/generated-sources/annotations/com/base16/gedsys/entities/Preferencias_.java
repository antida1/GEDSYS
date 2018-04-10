package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-09T23:05:04")
@StaticMetamodel(Preferencias.class)
public class Preferencias_ { 

    public static volatile SingularAttribute<Preferencias, Integer> creadoPor;
    public static volatile SingularAttribute<Preferencias, Date> fechaModificacion;
    public static volatile SingularAttribute<Preferencias, Boolean> borrado;
    public static volatile SingularAttribute<Preferencias, String> valor;
    public static volatile SingularAttribute<Preferencias, Date> fechaCreacion;
    public static volatile SingularAttribute<Preferencias, Integer> modificadoPor;
    public static volatile SingularAttribute<Preferencias, Usuario> usuario;
    public static volatile SingularAttribute<Preferencias, Integer> id;
    public static volatile SingularAttribute<Preferencias, String> nombre;

}