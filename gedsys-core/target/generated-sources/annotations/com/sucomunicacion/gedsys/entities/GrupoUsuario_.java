package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.Grupo;
import com.sucomunicacion.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
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
