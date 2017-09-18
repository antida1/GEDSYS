package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.ProcesoNegocio;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-18T10:40:12")
@StaticMetamodel(ProcesoDocumental.class)
public class ProcesoDocumental_ { 

    public static volatile SingularAttribute<ProcesoDocumental, Date> fechaInicioEstimada;
    public static volatile SingularAttribute<ProcesoDocumental, Integer> estado;
    public static volatile SingularAttribute<ProcesoDocumental, Date> fechaModificacion;
    public static volatile SingularAttribute<ProcesoDocumental, ProcesoNegocio> proceso;
    public static volatile SingularAttribute<ProcesoDocumental, Date> fechaFinalizacionReal;
    public static volatile SingularAttribute<ProcesoDocumental, Documento> documento;
    public static volatile SingularAttribute<ProcesoDocumental, Usuario> modificadoPor;
    public static volatile SingularAttribute<ProcesoDocumental, Usuario> creadoPor;
    public static volatile SingularAttribute<ProcesoDocumental, Integer> estadoProceso;
    public static volatile SingularAttribute<ProcesoDocumental, Boolean> borrado;
    public static volatile SingularAttribute<ProcesoDocumental, Date> fechaInicioReal;
    public static volatile SingularAttribute<ProcesoDocumental, Date> fechaCreacion;
    public static volatile SingularAttribute<ProcesoDocumental, Integer> salidaProceso;
    public static volatile SingularAttribute<ProcesoDocumental, Integer> id;
    public static volatile SingularAttribute<ProcesoDocumental, Date> fechaFinalizacionEstimada;

}