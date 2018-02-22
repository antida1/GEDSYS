package com.base16.gedsys.entities;

import com.base16.gedsys.entities.TipoDocumento;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-22T16:07:47")
@StaticMetamodel(PlantillaDocumental.class)
public class PlantillaDocumental_ { 

    public static volatile SingularAttribute<PlantillaDocumental, Usuario> creadoPor;
    public static volatile SingularAttribute<PlantillaDocumental, TipoDocumento> tipoDocumento;
    public static volatile SingularAttribute<PlantillaDocumental, Boolean> estado;
    public static volatile SingularAttribute<PlantillaDocumental, Date> fechaModificacion;
    public static volatile SingularAttribute<PlantillaDocumental, Boolean> borrado;
    public static volatile SingularAttribute<PlantillaDocumental, Usuario> modificadoPor;
    public static volatile SingularAttribute<PlantillaDocumental, Date> fechaCreacion;
    public static volatile SingularAttribute<PlantillaDocumental, Integer> id;
    public static volatile SingularAttribute<PlantillaDocumental, String> Encabezado;
    public static volatile SingularAttribute<PlantillaDocumental, String> nombre;
    public static volatile SingularAttribute<PlantillaDocumental, String> Pie;
    public static volatile SingularAttribute<PlantillaDocumental, String> cuerpo;

}