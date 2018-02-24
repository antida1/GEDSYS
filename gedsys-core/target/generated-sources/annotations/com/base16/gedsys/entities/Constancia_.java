package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-22T20:05:23")
@StaticMetamodel(Constancia.class)
public class Constancia_ { 

    public static volatile SingularAttribute<Constancia, String> consecutivo;
    public static volatile SingularAttribute<Constancia, Usuario> creadoPor;
    public static volatile SingularAttribute<Constancia, Date> fecha;
    public static volatile SingularAttribute<Constancia, String> contenido;
    public static volatile SingularAttribute<Constancia, Integer> estado;
    public static volatile SingularAttribute<Constancia, Date> fechaModificacion;
    public static volatile SingularAttribute<Constancia, Date> fechaFirma;
    public static volatile SingularAttribute<Constancia, Date> fechaCreacion;
    public static volatile SingularAttribute<Constancia, Usuario> remitente;
    public static volatile SingularAttribute<Constancia, Usuario> modificadoPor;
    public static volatile SingularAttribute<Constancia, Integer> id;

}