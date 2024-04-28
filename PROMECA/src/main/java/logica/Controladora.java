/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;
import java.util.List;
import persistencia.ControladoraPersistencia;

/**
 *
 * @author USER
 */
public class Controladora {
    ControladoraPersistencia control = new ControladoraPersistencia();
    //private Mecanica mecanica = new Mecanica();
    public Controladora() {
    }

   
    
    public void crearMecanica(String nombre,String direccion ,String correo,List<Automovil> autos) {
        Mecanica mecanica = new Mecanica(nombre,direccion,correo,autos);
               
        control.crearMecanica(mecanica);
    }
      public void crearReparacion( String descripcion ,String costo, Automovil automovil) {

        Reparacion reparacion = new Reparacion();
        reparacion.setDescripcion(descripcion);
        reparacion.setCosto(costo);
        reparacion.setAutomovil(automovil);
        
        control.crearReparacion(reparacion);
        List<Reparacion> reparaciones = encontrarAuto(automovil.getPlaca()).getReparaciones();
        reparaciones.add(reparacion);
        editarAutomovil(encontrarAuto(automovil.getPlaca()));
        
    }
      
      public Automovil encontrarAuto (String placa) {
       
        List<Automovil> listaAutomoviles = new ArrayList<>();
        listaAutomoviles = control.getAutomovil();
        for (Automovil auto : listaAutomoviles) {
            if(auto.getPlaca().equals(placa)) {
                return auto;
            }
        }
        return null;
      }
       

    

      public List<Reparacion> getReparaciones() {
       
        return control.getReparaciones();
        
        
    }
      
     
      

    public void crearAutomovil(String placa, String marca, String anioFab, String propietario, List<Reparacion> reparaciones, Mecanica mecanica) {
        Automovil auto = new Automovil();
        auto.setPlaca(placa);
        auto.setMarca(marca);
        auto.setAñoFabricacion(anioFab);
        auto.setPropietario(propietario);
        auto.setReparaciones(reparaciones);
        auto.setMecanica(mecanica);
        
       // Mecanica mecanica = control.traerMecanica();
        control.crearAuto(auto);
        List<Automovil> autos = traerMecanica().getAutomoviles();
        autos.add(auto);
        editarMecanica();
        
    }
    
    public void editarMecanica(){
        control.editarMecanica();
    }

    public Mecanica traerMecanica() {
      return control.traerMecanica();
    }

    private void editarAutomovil(Automovil automovil) {
        control.editarAutomovil(automovil);
    }
}
      
