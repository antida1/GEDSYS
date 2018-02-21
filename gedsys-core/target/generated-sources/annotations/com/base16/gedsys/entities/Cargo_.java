package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-20T17:33:32")
@StaticMetamodel(Cargo.class)
public class Cargo_ { 

    public static volatile SingularAttribute<Cargo, Usuario> creadoPor;
    public static volatile SingularAttribute<Cargo, Date> fechaModificacion;
    public static volatile SingularAttribute<Cargo, Boolean> borrado;
    public static volatile SingularAttribute<Cargo, Date> fechaCreacion;
    public static volatile SingularAttribute<Cargo, Usuario> modificadoPor;
    public static volatile SingularAttribute<Cargo, Integer> id;
    public static volatile CollectionAttribute<Cargo, Usuario> usuarioCollection;
    public static volatile SingularAttribute<Cargo, String> nombre;

}