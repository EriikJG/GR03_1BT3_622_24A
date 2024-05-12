package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Automovil;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-11T21:12:50")
@StaticMetamodel(Mecanica.class)
public class Mecanica_ { 

    public static volatile SingularAttribute<Mecanica, String> correo;
    public static volatile SingularAttribute<Mecanica, String> direccion;
    public static volatile ListAttribute<Mecanica, Automovil> automoviles;
    public static volatile SingularAttribute<Mecanica, Integer> id;
    public static volatile SingularAttribute<Mecanica, String> nombre;

}