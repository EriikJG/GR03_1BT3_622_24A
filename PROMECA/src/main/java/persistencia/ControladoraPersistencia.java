/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Automovil;
import logica.Mecanica;
import logica.Reparacion;

/**
 *
 * @author USER
 */
public class ControladoraPersistencia {
    
    AutomovilJpaController autoJPA = new AutomovilJpaController();
    MecanicaJpaController mecaJPA = new MecanicaJpaController();
    ReparacionJpaController repaJPA = new ReparacionJpaController();
    
    public ControladoraPersistencia() {
    }
    
    public void crearMecanica(Mecanica mecanica) {
        mecaJPA.create(mecanica);
    }
    
    public void crearReparacion(Reparacion reparacion) {
        repaJPA.create(reparacion);
    }

    public List<Automovil> getAutomovil() {
       return autoJPA.findAutomovilEntities();
    }

    public List<Reparacion> getReparaciones() {
         return repaJPA.findReparacionEntities();
    }

    public void crearAuto(Automovil auto) {
        autoJPA.create(auto);
    }

    public void editarMecanica() {
        try {
            mecaJPA.edit(mecaJPA.findMecanica(1));
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Mecanica traerMecanica() {
        return mecaJPA.findMecanica(1);
    }

    public void editarAutomovil(Automovil automovil) {
        try {
            autoJPA.edit(autoJPA.findAutomovil(automovil.getId()));
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


  

    
    
}
