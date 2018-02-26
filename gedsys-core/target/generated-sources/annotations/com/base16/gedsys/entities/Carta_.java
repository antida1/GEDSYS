package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Municipio;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-26T11:00:53")
@StaticMetamodel(Carta.class)
public class Carta_ { 

    public static volatile SingularAttribute<Carta, String> contenido;
    public static volatile SingularAttribute<Carta, String> estado;
    public static volatile SingularAttribute<Carta, Date> fechaModificacion;
    public static volatile SingularAttribute<Carta, String> direccion;
    public static volatile SingularAttribute<Carta, Usuario> remitente;
    public static volatile SingularAttribute<Carta, Usuario> modificadoPor;
    public static volatile SingularAttribute<Carta, String> tratamiento;
    public static volatile SingularAttribute<Carta, String> destinatario;
    public static volatile SingularAttribute<Carta, String> consecutivo;
    public static volatile SingularAttribute<Carta, Usuario> creadoPor;
    public static volatile SingularAttribute<Carta, Date> fecha;
    public static volatile SingularAttribute<Carta, Documento> documentoPadre;
    public static volatile SingularAttribute<Carta, String> despedida;
    public static volatile SingularAttribute<Carta, Municipio> ciudad;
    public static volatile SingularAttribute<Carta, String> asunto;
    public static volatile SingularAttribute<Carta, Date> fechaFirma;
    public static volatile SingularAttribute<Carta, Date> fechaCreacion;
    public static volatile SingularAttribute<Carta, Integer> id;
    public static volatile SingularAttribute<Carta, String> cargo;
    public static volatile SingularAttribute<Carta, String> empresa;

}