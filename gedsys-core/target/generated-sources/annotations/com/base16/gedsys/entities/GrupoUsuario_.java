package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Grupo;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-22T12:37:32")
@StaticMetamodel(GrupoUsuario.class)
public class GrupoUsuario_ { 

    public static volatile SingularAttribute<GrupoUsuario, Usuario> creadoPor;
    public static volatile SingularAttribute<GrupoUsuario, Date> fechaModificacion;
    public static volatile SingularAttribute<GrupoUsuario, Boolean> borrado;
    public static volatile SingularAttribute<GrupoUsuario, Grupo> grupo;
    public static volatile SingularAttribute<GrupoUsuario, Date> fechaCreacion;
    public static volatile SingularAttribute<GrupoUsuario, Usuario> modificadoPor;
    public static volatile SingularAttribute<GrupoUsuario, Usuario> usuario;
    public static volatile SingularAttribute<GrupoUsuario, Long> id;

}