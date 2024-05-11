/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.List;

import persistencia.AutomovilJpaController;
import persistencia.MecanicaJpaController;
import persistencia.ReparacionJpaController;
import persistencia.UsuarioJpaController;

/**
 *
 * @author USER
 */
public class Controladora {
    MecanicaJpaController controlMecanica = new MecanicaJpaController();
    AutomovilJpaController controlAutomovil = new AutomovilJpaController();

    ReparacionJpaController controlReparacion = new ReparacionJpaController();
    UsuarioJpaController controlUsuario = new UsuarioJpaController();

    public Controladora() {
    }

   

    public void crearMecanica(String nombre,String direccion ,String correo,List<Automovil> autos) {

        Mecanica mecanicaController = controlMecanica.findMecanica(1);

        if (mecanicaController == null || !nombre.equals(mecanicaController.getNombre())){
            Mecanica mecanica = new Mecanica(nombre,direccion,correo,autos);
            controlMecanica.create(mecanica);
             }

    }


      public void crearReparacion( String descripcion ,String costo, Automovil automovil) {

        Reparacion reparacion = new Reparacion();
          enviarDatosReparacion(descripcion, costo, automovil, reparacion);

          controlReparacion.create(reparacion);
          aniadirReparacion(automovil, reparacion);

      }

    private static void enviarDatosReparacion(String descripcion, String costo, Automovil automovil, Reparacion reparacion) {
        reparacion.setDescripcion(descripcion);
        reparacion.setCosto(costo);
        reparacion.setAutomovil(automovil);
    }

    private void aniadirReparacion(Automovil automovil, Reparacion reparacion) {
        List<Reparacion> reparaciones = encontrarAuto(automovil.getPlaca()).getReparaciones();
        reparaciones.add(reparacion);
    }

    public Automovil encontrarAuto (String placa) {
       
        List<Automovil> listaAutomoviles =  controlAutomovil.findAutomovilEntities();
        for (Automovil auto : listaAutomoviles) {
            if(auto.getPlaca().equals(placa)) {
                return auto;
            }
        }
        return null;
      }
       


      public List<Reparacion> getReparaciones() {

        return controlReparacion.findReparacionEntities();


    }




    public void crearAutomovil(String placa, String marca, String anioFab, Usuario propietario, List<Reparacion> reparaciones, Mecanica mecanica) {
        Automovil auto = new Automovil();
        aniadirDatosAutomovil(placa, marca, anioFab, propietario, reparaciones, mecanica, auto);

        controlAutomovil.create(auto);
        aniadirAutomovil(auto);

    }

    private void aniadirAutomovil(Automovil auto) {
        List<Automovil> autos = obtenerMecanica().getAutomoviles();
        autos.add(auto);
    }

    private static void aniadirDatosAutomovil(String placa, String marca, String anioFab, Usuario propietario, List<Reparacion> reparaciones, Mecanica mecanica, Automovil auto) {
        auto.setPlaca(placa);
        auto.setMarca(marca);
        auto.setAñoFabricacion(anioFab);
        auto.setPropietario(propietario);
        auto.setReparaciones(reparaciones);
        auto.setMecanica(mecanica);
    }

    public Mecanica obtenerMecanica() {

        return controlMecanica.findMecanica(1);
    }


    public void crearUsuario(int cedula, String nombre, String apellido, String correo, String telefono, String direccion, List<Automovil> autos) {
        Usuario usuario = new Usuario(cedula,nombre,apellido,telefono,correo,direccion,autos);


        controlUsuario.create(usuario);

    }


    public Usuario encontrarUsuario(int cedula) {
        List<Usuario> usuarios =  controlUsuario.findUsuarioEntities();
        for (Usuario usuario : usuarios) {
            if(usuario.getCedula() == cedula) {
                return usuario;
            }
        }
        return null;
    }


}

      
