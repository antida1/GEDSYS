package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Circularcc;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-20T17:33:31")
@StaticMetamodel(Circular.class)
public class Circular_ { 

    public static volatile SingularAttribute<Circular, String> contenido;
    public static volatile SingularAttribute<Circular, Integer> estado;
    public static volatile ListAttribute<Circular, Circularcc> circularccList;
    public static volatile SingularAttribute<Circular, String> anexos;
    public static volatile SingularAttribute<Circular, Usuario> remitente;
    public static volatile SingularAttribute<Circular, Usuario> modificadoPor;
    public static volatile SingularAttribute<Circular, String> grupoDestinatario;
    public static volatile SingularAttribute<Circular, String> consecutivo;
    public static volatile SingularAttribute<Circular, Usuario> creadoPor;
    public static volatile SingularAttribute<Circular, Date> fecha;
    public static volatile SingularAttribute<Circular, String> asunto;
    public static volatile SingularAttribute<Circular, Date> fechaFirma;
    public static volatile SingularAttribute<Circular, Date> fechaCreacion;
    public static volatile SingularAttribute<Circular, Date> fechaModifiacion;
    public static volatile SingularAttribute<Circular, Integer> id;

}