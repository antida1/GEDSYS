package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.Acl;
import com.sucomunicacion.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
@StaticMetamodel(Modulo.class)
public class Modulo_ {

    public static volatile SingularAttribute<Modulo, String> descripcion;
    public static volatile SingularAttribute<Modulo, Usuario> creadoPor;
    public static volatile SingularAttribute<Modulo, Date> fechaModificacion;
    public static volatile CollectionAttribute<Modulo, Acl> aclCollection;
    public static volatile SingularAttribute<Modulo, Boolean> oculto;
    public static volatile SingularAttribute<Modulo, Boolean> borrado;
    public static volatile SingularAttribute<Modulo, Date> fechaCreacion;
    public static volatile SingularAttribute<Modulo, Usuario> modificadoPor;
    public static volatile SingularAttribute<Modulo, Integer> id;
    public static volatile SingularAttribute<Modulo, String> nombre;

}
