package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-22T20:05:23")
@StaticMetamodel(Informe.class)
public class Informe_ { 

    public static volatile SingularAttribute<Informe, String> consecutivo;
    public static volatile SingularAttribute<Informe, String> objetivo;
    public static volatile SingularAttribute<Informe, Usuario> creadoPor;
    public static volatile SingularAttribute<Informe, Date> fecha;
    public static volatile SingularAttribute<Informe, Integer> estado;
    public static volatile SingularAttribute<Informe, Date> fechaModificacion;
    public static volatile SingularAttribute<Informe, Date> fechaFirma;
    public static volatile SingularAttribute<Informe, Usuario> remitente;
    public static volatile SingularAttribute<Informe, Usuario> modificadoPor;
    public static volatile SingularAttribute<Informe, Date> fechaCreacion;
    public static volatile SingularAttribute<Informe, Integer> id;
    public static volatile SingularAttribute<Informe, String> conclusiones;

}