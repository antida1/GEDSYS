package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.TipoDocumento;
import com.sucomunicacion.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
@StaticMetamodel(Consecutivo.class)
public class Consecutivo_ {

    public static volatile SingularAttribute<Consecutivo, String> consecutivo;
    public static volatile SingularAttribute<Consecutivo, Usuario> creadoPor;
    public static volatile SingularAttribute<Consecutivo, TipoDocumento> tipoDocumento;
    public static volatile SingularAttribute<Consecutivo, Date> fechaModificacion;
    public static volatile SingularAttribute<Consecutivo, String> sufijo;
    public static volatile SingularAttribute<Consecutivo, Boolean> borrado;
    public static volatile SingularAttribute<Consecutivo, Date> fechaCreacion;
    public static volatile SingularAttribute<Consecutivo, Usuario> modificadoPor;
    public static volatile SingularAttribute<Consecutivo, String> prefijo;
    public static volatile SingularAttribute<Consecutivo, Integer> id;
    public static volatile SingularAttribute<Consecutivo, String> tipoConsecutivo;

}
