package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
@StaticMetamodel(Notificacion.class)
public class Notificacion_ {

    public static volatile SingularAttribute<Notificacion, String> descripcion;
    public static volatile SingularAttribute<Notificacion, Boolean> notificacionCorreo;
    public static volatile SingularAttribute<Notificacion, Date> fechaModificacion;
    public static volatile SingularAttribute<Notificacion, Usuario> responsable;
    public static volatile SingularAttribute<Notificacion, Integer> peridicidadNotificacion;
    public static volatile SingularAttribute<Notificacion, Boolean> notificacionPeriodica;
    public static volatile SingularAttribute<Notificacion, Integer> modificadoPor;
    public static volatile SingularAttribute<Notificacion, Date> fechaNotificacion;
    public static volatile SingularAttribute<Notificacion, Integer> tipoPeriodo;
    public static volatile SingularAttribute<Notificacion, Boolean> notificacionPush;
    public static volatile SingularAttribute<Notificacion, Integer> mesesNotificacion;
    public static volatile SingularAttribute<Notificacion, Integer> diasNotificacion;
    public static volatile SingularAttribute<Notificacion, String> asunto;
    public static volatile SingularAttribute<Notificacion, Date> fechaCreacion;
    public static volatile SingularAttribute<Notificacion, Long> id;
    public static volatile SingularAttribute<Notificacion, Boolean> notificacionPopup;
    public static volatile SingularAttribute<Notificacion, Integer> creadorPor;

}
