/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Automovil;
import logica.Reparacion;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author USER
 */
public class ReparacionJpaController implements Serializable {

    public ReparacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public  ReparacionJpaController() {
        emf = Persistence.createEntityManagerFactory("mecanica_PU");
    }

    public void create(Reparacion reparacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Automovil automovil = reparacion.getAutomovil();
            if (automovil != null) {
                automovil = em.getReference(automovil.getClass(), automovil.getId());
                reparacion.setAutomovil(automovil);
            }
            em.persist(reparacion);
            if (automovil != null) {
                automovil.getReparaciones().add(reparacion);
                automovil = em.merge(automovil);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reparacion reparacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reparacion persistentReparacion = em.find(Reparacion.class, reparacion.getId());
            Automovil automovilOld = persistentReparacion.getAutomovil();
            Automovil automovilNew = reparacion.getAutomovil();
            if (automovilNew != null) {
                automovilNew = em.getReference(automovilNew.getClass(), automovilNew.getId());
                reparacion.setAutomovil(automovilNew);
            }
            reparacion = em.merge(reparacion);
            if (automovilOld != null && !automovilOld.equals(automovilNew)) {
                automovilOld.getReparaciones().remove(reparacion);
                automovilOld = em.merge(automovilOld);
            }
            if (automovilNew != null && !automovilNew.equals(automovilOld)) {
                automovilNew.getReparaciones().add(reparacion);
                automovilNew = em.merge(automovilNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = reparacion.getId();
                if (findReparacion(id) == null) {
                    throw new NonexistentEntityException("The reparacion with id " + id + " no longer exists.");
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
            Reparacion reparacion;
            try {
                reparacion = em.getReference(Reparacion.class, id);
                reparacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reparacion with id " + id + " no longer exists.", enfe);
            }
            Automovil automovil = reparacion.getAutomovil();
            if (automovil != null) {
                automovil.getReparaciones().remove(reparacion);
                automovil = em.merge(automovil);
            }
            em.remove(reparacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reparacion> findReparacionEntities() {
        return findReparacionEntities(true, -1, -1);
    }

    public List<Reparacion> findReparacionEntities(int maxResults, int firstResult) {
        return findReparacionEntities(false, maxResults, firstResult);
    }

    private List<Reparacion> findReparacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reparacion.class));
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

    public Reparacion findReparacion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reparacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getReparacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reparacion> rt = cq.from(Reparacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
