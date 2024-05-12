package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Automovil;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-11T21:12:50")
@StaticMetamodel(Reparacion.class)
public class Reparacion_ { 

    public static volatile SingularAttribute<Reparacion, String> descripcion;
    public static volatile SingularAttribute<Reparacion, Automovil> automovil;
    public static volatile SingularAttribute<Reparacion, String> costo;
    public static volatile SingularAttribute<Reparacion, Integer> id;

}