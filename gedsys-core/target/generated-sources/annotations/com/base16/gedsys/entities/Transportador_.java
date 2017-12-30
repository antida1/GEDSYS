package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-28T23:43:35")
@StaticMetamodel(Transportador.class)
public class Transportador_ { 

    public static volatile SingularAttribute<Transportador, Date> fechaModificacion;
    public static volatile CollectionAttribute<Transportador, Documento> documentoCollection;
    public static volatile SingularAttribute<Transportador, Usuario> modificadoPor;
    public static volatile SingularAttribute<Transportador, String> nombre;
    public static volatile SingularAttribute<Transportador, Usuario> creadoPor;
    public static volatile SingularAttribute<Transportador, String> tipoDocumento;
    public static volatile SingularAttribute<Transportador, Boolean> borrado;
    public static volatile SingularAttribute<Transportador, String> celular;
    public static volatile SingularAttribute<Transportador, Date> fechaCreacion;
    public static volatile SingularAttribute<Transportador, Integer> id;
    public static volatile SingularAttribute<Transportador, String> numeroDocumento;
    public static volatile SingularAttribute<Transportador, String> fax;
    public static volatile SingularAttribute<Transportador, String> telefono;
    public static volatile SingularAttribute<Transportador, String> email;

}