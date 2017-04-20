package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.TipoDocumento;
import com.sucomunicacion.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
@StaticMetamodel(PlantillaDocumental.class)
public class PlantillaDocumental_ {

    public static volatile SingularAttribute<PlantillaDocumental, String> texto;
    public static volatile SingularAttribute<PlantillaDocumental, Usuario> creadoPor;
    public static volatile SingularAttribute<PlantillaDocumental, TipoDocumento> tipoDocumento;
    public static volatile SingularAttribute<PlantillaDocumental, Boolean> estado;
    public static volatile SingularAttribute<PlantillaDocumental, Date> fechaModificacion;
    public static volatile SingularAttribute<PlantillaDocumental, Boolean> borrado;
    public static volatile SingularAttribute<PlantillaDocumental, Date> fechaCreacion;
    public static volatile SingularAttribute<PlantillaDocumental, Usuario> modificadoPor;
    public static volatile SingularAttribute<PlantillaDocumental, Integer> id;
    public static volatile SingularAttribute<PlantillaDocumental, String> nombre;

}
