package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Modulo;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-18T10:40:12")
@StaticMetamodel(Campos.class)
public class Campos_ { 

    public static volatile SingularAttribute<Campos, Usuario> creadoPor;
    public static volatile SingularAttribute<Campos, String> tipo;
    public static volatile SingularAttribute<Campos, Date> fechaModificacion;
    public static volatile SingularAttribute<Campos, Date> fechaCreacion;
    public static volatile SingularAttribute<Campos, Usuario> modificadoPor;
    public static volatile SingularAttribute<Campos, Integer> id;
    public static volatile SingularAttribute<Campos, String> nombre;
    public static volatile SingularAttribute<Campos, Modulo> modulo;

}