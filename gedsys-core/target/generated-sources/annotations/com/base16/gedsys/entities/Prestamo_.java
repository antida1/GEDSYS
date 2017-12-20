package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-19T11:51:28")
@StaticMetamodel(Prestamo.class)
public class Prestamo_ { 

    public static volatile SingularAttribute<Prestamo, Date> fechaInicial;
    public static volatile SingularAttribute<Prestamo, String> estado;
    public static volatile SingularAttribute<Prestamo, Date> fechaModificacion;
    public static volatile SingularAttribute<Prestamo, Date> fechaVencimiento;
    public static volatile SingularAttribute<Prestamo, Usuario> modificadoPor;
    public static volatile SingularAttribute<Prestamo, Usuario> creadoPor;
    public static volatile SingularAttribute<Prestamo, String> tipoDocumento;
    public static volatile SingularAttribute<Prestamo, String> quienDevuelve;
    public static volatile SingularAttribute<Prestamo, String> fechaDevolucion;
    public static volatile SingularAttribute<Prestamo, Usuario> prestador;
    public static volatile SingularAttribute<Prestamo, String> queinRecibe;
    public static volatile SingularAttribute<Prestamo, String> nombreDocumento;
    public static volatile SingularAttribute<Prestamo, Date> fechaCreacion;
    public static volatile SingularAttribute<Prestamo, Integer> id;

}