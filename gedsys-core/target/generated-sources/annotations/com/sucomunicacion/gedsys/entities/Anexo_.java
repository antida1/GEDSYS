package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
@StaticMetamodel(Anexo.class)
public class Anexo_ {

    public static volatile SingularAttribute<Anexo, Integer> folioNro;
    public static volatile SingularAttribute<Anexo, Usuario> creadoPor;
    public static volatile SingularAttribute<Anexo, Date> fechaModificacion;
    public static volatile SingularAttribute<Anexo, Documento> anexo;
    public static volatile SingularAttribute<Anexo, Boolean> borrado;
    public static volatile SingularAttribute<Anexo, Date> fechaCreacion;
    public static volatile SingularAttribute<Anexo, Documento> documento;
    public static volatile SingularAttribute<Anexo, Long> id;
    public static volatile SingularAttribute<Anexo, Usuario> modificadorPor;

}
