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

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
     public AutomovilJpaController() {
         emf = Persistence.createEntityManagerFactory("mecanica_PU");
    }

    public void create(Automovil automovil) {
        if (automovil.getReparaciones() == null) {
            automovil.setReparaciones(new ArrayList<Reparacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mecanica mecanica = automovil.getMecanica();
            if (mecanica != null) {
                mecanica = em.getReference(mecanica.getClass(), mecanica.getId());
                automovil.setMecanica(mecanica);
            }
            List<Reparacion> attachedReparaciones = new ArrayList<Reparacion>();
            for (Reparacion reparacionesReparacionToAttach : automovil.getReparaciones()) {
                reparacionesReparacionToAttach = em.getReference(reparacionesReparacionToAttach.getClass(), reparacionesReparacionToAttach.getId());
                attachedReparaciones.add(reparacionesReparacionToAttach);
            }
            automovil.setReparaciones(attachedReparaciones);
            em.persist(automovil);
            if (mecanica != null) {
                mecanica.getAutomoviles().add(automovil);
                mecanica = em.merge(mecanica);
            }
            for (Reparacion reparacionesReparacion : automovil.getReparaciones()) {
                Automovil oldAutomovilOfReparacionesReparacion = reparacionesReparacion.getAutomovil();
                reparacionesReparacion.setAutomovil(automovil);
                reparacionesReparacion = em.merge(reparacionesReparacion);
                if (oldAutomovilOfReparacionesReparacion != null) {
                    oldAutomovilOfReparacionesReparacion.getReparaciones().remove(reparacionesReparacion);
                    oldAutomovilOfReparacionesReparacion = em.merge(oldAutomovilOfReparacionesReparacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Automovil automovil) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Automovil persistentAutomovil = em.find(Automovil.class, automovil.getId());
            Mecanica mecanicaOld = persistentAutomovil.getMecanica();
            Mecanica mecanicaNew = automovil.getMecanica();
            List<Reparacion> reparacionesOld = persistentAutomovil.getReparaciones();
            List<Reparacion> reparacionesNew = automovil.getReparaciones();
            if (mecanicaNew != null) {
                mecanicaNew = em.getReference(mecanicaNew.getClass(), mecanicaNew.getId());
                automovil.setMecanica(mecanicaNew);
            }
            List<Reparacion> attachedReparacionesNew = new ArrayList<Reparacion>();
            for (Reparacion reparacionesNewReparacionToAttach : reparacionesNew) {
                reparacionesNewReparacionToAttach = em.getReference(reparacionesNewReparacionToAttach.getClass(), reparacionesNewReparacionToAttach.getId());
                attachedReparacionesNew.add(reparacionesNewReparacionToAttach);
            }
            reparacionesNew = attachedReparacionesNew;
            automovil.setReparaciones(reparacionesNew);
            automovil = em.merge(automovil);
            if (mecanicaOld != null && !mecanicaOld.equals(mecanicaNew)) {
                mecanicaOld.getAutomoviles().remove(automovil);
                mecanicaOld = em.merge(mecanicaOld);
            }
            if (mecanicaNew != null && !mecanicaNew.equals(mecanicaOld)) {
                mecanicaNew.getAutomoviles().add(automovil);
                mecanicaNew = em.merge(mecanicaNew);
            }
            for (Reparacion reparacionesOldReparacion : reparacionesOld) {
                if (!reparacionesNew.contains(reparacionesOldReparacion)) {
                    reparacionesOldReparacion.setAutomovil(null);
                    reparacionesOldReparacion = em.merge(reparacionesOldReparacion);
                }
            }
            for (Reparacion reparacionesNewReparacion : reparacionesNew) {
                if (!reparacionesOld.contains(reparacionesNewReparacion)) {
                    Automovil oldAutomovilOfReparacionesNewReparacion = reparacionesNewReparacion.getAutomovil();
                    reparacionesNewReparacion.setAutomovil(automovil);
                    reparacionesNewReparacion = em.merge(reparacionesNewReparacion);
                    if (oldAutomovilOfReparacionesNewReparacion != null && !oldAutomovilOfReparacionesNewReparacion.equals(automovil)) {
                        oldAutomovilOfReparacionesNewReparacion.getReparaciones().remove(reparacionesNewReparacion);
                        oldAutomovilOfReparacionesNewReparacion = em.merge(oldAutomovilOfReparacionesNewReparacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = automovil.getId();
                if (findAutomovil(id) == null) {
                    throw new NonexistentEntityException("The automovil with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Automovil automovil;
            try {
                automovil = em.getReference(Automovil.class, id);
                automovil.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The automovil with id " + id + " no longer exists.", enfe);
            }
            Mecanica mecanica = automovil.getMecanica();
            if (mecanica != null) {
                mecanica.getAutomoviles().remove(automovil);
                mecanica = em.merge(mecanica);
            }
            List<Reparacion> reparaciones = automovil.getReparaciones();
            for (Reparacion reparacionesReparacion : reparaciones) {
                reparacionesReparacion.setAutomovil(null);
                reparacionesReparacion = em.merge(reparacionesReparacion);
            }
            em.remove(automovil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Automovil> findAutomovilEntities() {
        return findAutomovilEntities(true, -1, -1);
    }

    public List<Automovil> findAutomovilEntities(int maxResults, int firstResult) {
        return findAutomovilEntities(false, maxResults, firstResult);
    }

    private List<Automovil> findAutomovilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
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

    public Automovil findAutomovil(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Automovil.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutomovilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Automovil> rt = cq.from(Automovil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
