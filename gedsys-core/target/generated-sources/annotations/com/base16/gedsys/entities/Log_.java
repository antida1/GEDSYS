package com.base16.gedsys.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-24T23:48:11")
@StaticMetamodel(Log.class)
public class Log_ { 

    public static volatile SingularAttribute<Log, Integer> creadoPor;
    public static volatile SingularAttribute<Log, Date> fechaModificacion;
    public static volatile SingularAttribute<Log, Boolean> borrado;
    public static volatile SingularAttribute<Log, String> valorActual;
    public static volatile SingularAttribute<Log, Date> fechaCreacion;
    public static volatile SingularAttribute<Log, Integer> modificadoPor;
    public static volatile SingularAttribute<Log, Integer> id;
    public static volatile SingularAttribute<Log, Integer> modulo;
    public static volatile SingularAttribute<Log, String> campo;
    public static volatile SingularAttribute<Log, String> valorAnterior;

}