package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Modulo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-20T13:28:05")
@StaticMetamodel(CamposPlantilla.class)
public class CamposPlantilla_ { 

    public static volatile SingularAttribute<CamposPlantilla, Integer> creadoPor;
    public static volatile SingularAttribute<CamposPlantilla, Boolean> estado;
    public static volatile SingularAttribute<CamposPlantilla, Date> fechaModificacion;
    public static volatile SingularAttribute<CamposPlantilla, Integer> modificadoPor;
    public static volatile SingularAttribute<CamposPlantilla, Date> fechaCreacion;
    public static volatile SingularAttribute<CamposPlantilla, Integer> id;
    public static volatile SingularAttribute<CamposPlantilla, String> nombre;
    public static volatile SingularAttribute<CamposPlantilla, Modulo> modulo;

}