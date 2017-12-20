package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-19T11:51:28")
@StaticMetamodel(Certificado.class)
public class Certificado_ { 

    public static volatile SingularAttribute<Certificado, String> consecutivo;
    public static volatile SingularAttribute<Certificado, Usuario> creadoPor;
    public static volatile SingularAttribute<Certificado, Date> fecha;
    public static volatile SingularAttribute<Certificado, String> contenido;
    public static volatile SingularAttribute<Certificado, Integer> estado;
    public static volatile SingularAttribute<Certificado, Date> fechaModificacion;
    public static volatile SingularAttribute<Certificado, Date> fechaFirma;
    public static volatile SingularAttribute<Certificado, Date> fechaCreacion;
    public static volatile SingularAttribute<Certificado, Usuario> remitente;
    public static volatile SingularAttribute<Certificado, Usuario> modificadoPor;
    public static volatile SingularAttribute<Certificado, Integer> id;

}