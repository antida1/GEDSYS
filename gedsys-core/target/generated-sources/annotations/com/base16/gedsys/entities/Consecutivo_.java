package com.base16.gedsys.entities;

import com.base16.gedsys.entities.TipoDocumento;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-20T15:32:03")
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