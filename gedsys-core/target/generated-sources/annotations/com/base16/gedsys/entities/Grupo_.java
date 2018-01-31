package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Acl;
import com.base16.gedsys.entities.GrupoUsuario;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-24T23:48:11")
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