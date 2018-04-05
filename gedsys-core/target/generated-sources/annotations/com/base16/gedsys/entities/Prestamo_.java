package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-04T14:32:34")
@StaticMetamodel(Prestamo.class)
public class Prestamo_ { 

    public static volatile SingularAttribute<Prestamo, Usuario> prestadoA;
    public static volatile SingularAttribute<Prestamo, Date> fechaInicial;
    public static volatile SingularAttribute<Prestamo, Integer> estado;
    public static volatile SingularAttribute<Prestamo, Date> fechaModificacion;
    public static volatile SingularAttribute<Prestamo, Date> fechaVencimiento;
    public static volatile SingularAttribute<Prestamo, Usuario> modificadoPor;
    public static volatile SingularAttribute<Prestamo, Documento> documento;
    public static volatile SingularAttribute<Prestamo, String> firma;
    public static volatile SingularAttribute<Prestamo, Integer> estadoAnterior;
    public static volatile SingularAttribute<Prestamo, Usuario> creadoPor;
    public static volatile SingularAttribute<Prestamo, String> tipoDocumento;
    public static volatile SingularAttribute<Prestamo, String> quienDevuelve;
    public static volatile SingularAttribute<Prestamo, Date> fechaDevolucion;
    public static volatile SingularAttribute<Prestamo, String> queinRecibe;
    public static volatile SingularAttribute<Prestamo, String> nombreDocumento;
    public static volatile SingularAttribute<Prestamo, String> observaciones;
    public static volatile SingularAttribute<Prestamo, Date> fechaCreacion;
    public static volatile SingularAttribute<Prestamo, Integer> id;

}
