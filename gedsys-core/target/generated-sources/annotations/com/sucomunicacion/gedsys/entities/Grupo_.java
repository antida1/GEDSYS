package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.Acl;
import com.sucomunicacion.gedsys.entities.GrupoUsuario;
import com.sucomunicacion.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
@StaticMetamodel(Grupo.class)
public class Grupo_ {

    public static volatile SingularAttribute<Grupo, Integer> creadoPor;
    public static volatile CollectionAttribute<Grupo, GrupoUsuario> grupoUsuarioCollection;
    public static volatile SingularAttribute<Grupo, Boolean> estado;
    public static volatile SingularAttribute<Grupo, Date> fechaModificacion;
    public static volatile CollectionAttribute<Grupo, Acl> aclCollection;
    public static volatile SingularAttribute<Grupo, Boolean> borrado;
    public static volatile SingularAttribute<Grupo, Date> fechaCreacion;
    public static volatile SingularAttribute<Grupo, Usuario> modificadoPor;
    public static volatile SingularAttribute<Grupo, Integer> id;
    public static volatile SingularAttribute<Grupo, String> nombre;

}
