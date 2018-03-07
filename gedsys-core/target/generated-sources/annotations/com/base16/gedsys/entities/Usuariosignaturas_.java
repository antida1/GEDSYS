package com.base16.gedsys.entities;

import com.base16.gedsys.entities.SignaturaTopografica;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-06T16:49:53")
@StaticMetamodel(Usuariosignaturas.class)
public class Usuariosignaturas_ { 

    public static volatile SingularAttribute<Usuariosignaturas, Usuario> creadoPor;
    public static volatile SingularAttribute<Usuariosignaturas, Date> fechaModificacion;
    public static volatile SingularAttribute<Usuariosignaturas, Date> fechaCreacion;
    public static volatile SingularAttribute<Usuariosignaturas, SignaturaTopografica> signatura;
    public static volatile SingularAttribute<Usuariosignaturas, Usuario> usuario;
    public static volatile SingularAttribute<Usuariosignaturas, Integer> id;
    public static volatile SingularAttribute<Usuariosignaturas, Usuario> modificadorPor;

}