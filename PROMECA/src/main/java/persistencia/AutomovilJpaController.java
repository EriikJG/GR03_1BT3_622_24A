/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Mecanica;
import logica.Reparacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Automovil;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author USER
 */
public class AutomovilJpaController implements Serializable {

    public AutomovilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    // Método para obtener un EntityManager
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Constructor por defecto que inicializa el EntityManagerFactory
    public AutomovilJpaController() {
        emf = Persistence.createEntityManagerFactory("mecanica_PU");
    }

    // Método para crear un nuevo registro de Automovil en la base de datos
    public void create(Automovil automovil) {
        // Verifica si la lista de reparaciones en el automóvil es nula y la inicializa si es necesario
        if (automovil.getReparaciones() == null) {
            automovil.setReparaciones(new ArrayList<Reparacion>());
        }

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            // Adjunta la mecánica al automóvil si existe
            adjuntarMecanicaSiExiste(em, automovil);
            // Adjunta las reparaciones al EntityManager
            adjuntarReparaciones(em, automovil);
            // Persiste el automóvil en la base de datos
            em.persist(automovil);
            // Actualiza la relación bidireccional con la mecánica, si existe
            actualizarRelacionMecanicaSiExiste(em, automovil);

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Método privado para adjuntar la mecánica al automóvil si existe
    private void adjuntarMecanicaSiExiste(EntityManager em, Automovil automovil) {
        Mecanica mecanica = automovil.getMecanica();
        if (mecanica != null) {
            mecanica = em.getReference(mecanica.getClass(), mecanica.getId());
            automovil.setMecanica(mecanica);
        }
    }

    // Método privado para adjuntar las reparaciones al automóvil si existen
    private void adjuntarReparaciones(EntityManager em, Automovil automovil) {
        List<Reparacion> reparaciones = automovil.getReparaciones();
        if (reparaciones != null && !reparaciones.isEmpty()) {
            List<Reparacion> reparacionesAdjuntas = new ArrayList<>();
            for (Reparacion reparacion : reparaciones) {
                reparacionesAdjuntas.add(em.getReference(reparacion.getClass(), reparacion.getId()));
            }
            automovil.setReparaciones(reparacionesAdjuntas);
        }
    }

    // Método privado para actualizar la relación con la mecánica si existe
    private void actualizarRelacionMecanicaSiExiste(EntityManager em, Automovil automovil) {
        Mecanica mecanica = automovil.getMecanica();
        if (mecanica != null) {
            mecanica.getAutomoviles().add(automovil);
            mecanica = em.merge(mecanica);
        }
    }

    // Método para encontrar todas las entidades Automovil
    public List<Automovil> findAutomovilEntities() {
        return findAutomovilEntities(true, -1, -1);
    }

    // Método privado para encontrar las entidades Automovil con opciones de paginación
    private List<Automovil> findAutomovilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Automovil> cq = em.getCriteriaBuilder().createQuery(Automovil.class);
            cq.select(cq.from(Automovil.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}