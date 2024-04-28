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
import logica.Automovil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Mecanica;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author USER
 */
public class MecanicaJpaController implements Serializable {

    public MecanicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

       public MecanicaJpaController() {
         emf = Persistence.createEntityManagerFactory("mecanica_PU");
    }
    public void create(Mecanica mecanica) {
        if (mecanica.getAutomoviles() == null) {
            mecanica.setAutomoviles(new ArrayList<Automovil>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Automovil> attachedAutomoviles = new ArrayList<Automovil>();
            for (Automovil automovilesAutomovilToAttach : mecanica.getAutomoviles()) {
                automovilesAutomovilToAttach = em.getReference(automovilesAutomovilToAttach.getClass(), automovilesAutomovilToAttach.getId());
                attachedAutomoviles.add(automovilesAutomovilToAttach);
            }
            mecanica.setAutomoviles(attachedAutomoviles);
            em.persist(mecanica);
            for (Automovil automovilesAutomovil : mecanica.getAutomoviles()) {
                Mecanica oldMecanicaOfAutomovilesAutomovil = automovilesAutomovil.getMecanica();
                automovilesAutomovil.setMecanica(mecanica);
                automovilesAutomovil = em.merge(automovilesAutomovil);
                if (oldMecanicaOfAutomovilesAutomovil != null) {
                    oldMecanicaOfAutomovilesAutomovil.getAutomoviles().remove(automovilesAutomovil);
                    oldMecanicaOfAutomovilesAutomovil = em.merge(oldMecanicaOfAutomovilesAutomovil);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mecanica mecanica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mecanica persistentMecanica = em.find(Mecanica.class, mecanica.getId());
            List<Automovil> automovilesOld = persistentMecanica.getAutomoviles();
            List<Automovil> automovilesNew = mecanica.getAutomoviles();
            List<Automovil> attachedAutomovilesNew = new ArrayList<Automovil>();
            for (Automovil automovilesNewAutomovilToAttach : automovilesNew) {
                automovilesNewAutomovilToAttach = em.getReference(automovilesNewAutomovilToAttach.getClass(), automovilesNewAutomovilToAttach.getId());
                attachedAutomovilesNew.add(automovilesNewAutomovilToAttach);
            }
            automovilesNew = attachedAutomovilesNew;
            mecanica.setAutomoviles(automovilesNew);
            mecanica = em.merge(mecanica);
            for (Automovil automovilesOldAutomovil : automovilesOld) {
                if (!automovilesNew.contains(automovilesOldAutomovil)) {
                    automovilesOldAutomovil.setMecanica(null);
                    automovilesOldAutomovil = em.merge(automovilesOldAutomovil);
                }
            }
            for (Automovil automovilesNewAutomovil : automovilesNew) {
                if (!automovilesOld.contains(automovilesNewAutomovil)) {
                    Mecanica oldMecanicaOfAutomovilesNewAutomovil = automovilesNewAutomovil.getMecanica();
                    automovilesNewAutomovil.setMecanica(mecanica);
                    automovilesNewAutomovil = em.merge(automovilesNewAutomovil);
                    if (oldMecanicaOfAutomovilesNewAutomovil != null && !oldMecanicaOfAutomovilesNewAutomovil.equals(mecanica)) {
                        oldMecanicaOfAutomovilesNewAutomovil.getAutomoviles().remove(automovilesNewAutomovil);
                        oldMecanicaOfAutomovilesNewAutomovil = em.merge(oldMecanicaOfAutomovilesNewAutomovil);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = mecanica.getId();
                if (findMecanica(id) == null) {
                    throw new NonexistentEntityException("The mecanica with id " + id + " no longer exists.");
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
            Mecanica mecanica;
            try {
                mecanica = em.getReference(Mecanica.class, id);
                mecanica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mecanica with id " + id + " no longer exists.", enfe);
            }
            List<Automovil> automoviles = mecanica.getAutomoviles();
            for (Automovil automovilesAutomovil : automoviles) {
                automovilesAutomovil.setMecanica(null);
                automovilesAutomovil = em.merge(automovilesAutomovil);
            }
            em.remove(mecanica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mecanica> findMecanicaEntities() {
        return findMecanicaEntities(true, -1, -1);
    }

    public List<Mecanica> findMecanicaEntities(int maxResults, int firstResult) {
        return findMecanicaEntities(false, maxResults, firstResult);
    }

    private List<Mecanica> findMecanicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mecanica.class));
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

    public Mecanica findMecanica(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mecanica.class, id);
        } finally {
            em.close();
        }
    }

    public int getMecanicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mecanica> rt = cq.from(Mecanica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
