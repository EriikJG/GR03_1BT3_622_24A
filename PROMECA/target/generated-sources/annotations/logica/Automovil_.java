package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Mecanica;
import logica.Reparacion;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-03T22:01:51")
@StaticMetamodel(Automovil.class)
public class Automovil_ { 

    public static volatile SingularAttribute<Automovil, String> añoFabricacion;
    public static volatile SingularAttribute<Automovil, Mecanica> mecanica;
    public static volatile SingularAttribute<Automovil, String> marca;
    public static volatile ListAttribute<Automovil, Reparacion> reparaciones;
    public static volatile SingularAttribute<Automovil, String> propietario;
    public static volatile SingularAttribute<Automovil, Integer> id;
    public static volatile SingularAttribute<Automovil, String> placa;

}