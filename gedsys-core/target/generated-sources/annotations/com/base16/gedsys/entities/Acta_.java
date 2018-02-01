package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Actaasistente;
import com.base16.gedsys.entities.Actaausente;
import com.base16.gedsys.entities.Actainvitado;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-31T21:44:01")
@StaticMetamodel(Acta.class)
public class Acta_ { 

    public static volatile SingularAttribute<Acta, Integer> estado;
    public static volatile SingularAttribute<Acta, Date> fechaModificacion;
    public static volatile ListAttribute<Acta, Actaasistente> actaasistenteList;
    public static volatile ListAttribute<Acta, Actainvitado> actainvitadoList;
    public static volatile SingularAttribute<Acta, String> lugar;
    public static volatile SingularAttribute<Acta, String> convocatoria;
    public static volatile SingularAttribute<Acta, Usuario> modificadoPor;
    public static volatile SingularAttribute<Acta, Date> horaInicio;
    public static volatile SingularAttribute<Acta, String> consecutivo;
    public static volatile SingularAttribute<Acta, Usuario> creadoPor;
    public static volatile SingularAttribute<Acta, Date> fecha;
    public static volatile SingularAttribute<Acta, Date> fechaFirma;
    public static volatile SingularAttribute<Acta, Date> horaFinalizacion;
    public static volatile SingularAttribute<Acta, String> desarrollo;
    public static volatile SingularAttribute<Acta, Date> fechaCreacion;
    public static volatile SingularAttribute<Acta, Integer> id;
    public static volatile SingularAttribute<Acta, String> orden;
    public static volatile SingularAttribute<Acta, Usuario> secretaria;
    public static volatile ListAttribute<Acta, Actaausente> actaausenteList;
    public static volatile SingularAttribute<Acta, Usuario> presidente;

}