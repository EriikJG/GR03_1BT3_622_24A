/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 *
 * @author USER
 */
@Entity
public class Automovil implements Serializable {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String placa;
    private String marca;
    private String añoFabricacion;
    @ManyToOne
    @JoinColumn(name="id_propietario")
    private Usuario propietario;
    @OneToMany(mappedBy="automovil")
    private List<Reparacion> reparaciones;
    @ManyToOne
    @JoinColumn(name="id_mecanica")
    private Mecanica mecanica;

    public Automovil() {
    }

    public Automovil(String placa, String marca, String añoFabricacion, Usuario propietario, List<Reparacion> reparaciones) {
        this.placa = placa;
        this.marca = marca;
        this.añoFabricacion = añoFabricacion;
        this.propietario = propietario;
        this.reparaciones = reparaciones;
    }


    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAñoFabricacion() {
        return añoFabricacion;
    }

    public void setAñoFabricacion(String añoFabricacion) {
        this.añoFabricacion = añoFabricacion;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public List<Reparacion> getReparaciones() {
        return reparaciones;
    }

    public void setReparaciones(List<Reparacion> reparaciones) {
        this.reparaciones = reparaciones;
    }

    public Mecanica getMecanica() {
        return mecanica;
    }

    public void setMecanica(Mecanica mecanica) {
        
        this.mecanica = mecanica;
        mecanica.getAutomoviles().add(this);
    }

   
}