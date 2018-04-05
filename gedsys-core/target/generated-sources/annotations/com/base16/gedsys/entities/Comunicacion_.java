package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Comunicacioncc;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-04T14:32:34")
@StaticMetamodel(Comunicacion.class)
public class Comunicacion_ { 

    public static volatile SingularAttribute<Comunicacion, String> descripcionAnexos;
    public static volatile SingularAttribute<Comunicacion, String> contenido;
    public static volatile SingularAttribute<Comunicacion, String> estado;
    public static volatile SingularAttribute<Comunicacion, Date> fechaModificacion;
    public static volatile SingularAttribute<Comunicacion, Usuario> remitente;
    public static volatile SingularAttribute<Comunicacion, Usuario> modificadoPor;
    public static volatile SingularAttribute<Comunicacion, Usuario> destinatario;
    public static volatile SingularAttribute<Comunicacion, String> consecutivo;
    public static volatile SingularAttribute<Comunicacion, Usuario> creadoPor;
    public static volatile SingularAttribute<Comunicacion, Date> fecha;
    public static volatile SingularAttribute<Comunicacion, String> asunto;
    public static volatile SingularAttribute<Comunicacion, Date> fechaFirma;
    public static volatile ListAttribute<Comunicacion, Comunicacioncc> comunicacionccList;
    public static volatile SingularAttribute<Comunicacion, Date> fechaCreacion;
    public static volatile SingularAttribute<Comunicacion, Integer> id;

}