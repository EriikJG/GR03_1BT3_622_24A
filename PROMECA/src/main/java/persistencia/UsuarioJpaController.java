package persistencia;


import logica.Automovil;
import logica.Reparacion;
import logica.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsuarioJpaController implements Serializable {
    // EntityManagerFactory para la gestión de entidades
    private final EntityManagerFactory emf;

    // Constructor que recibe una instancia de EntityManagerFactory
    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // Constructor por defecto que crea una instancia de EntityManagerFactory
    public UsuarioJpaController() {
        emf = Persistence.createEntityManagerFactory("mecanica_PU");
    }

    // Método para obtener un EntityManager
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Método para crear una nueva reparación en la base de datos
    public void create(Usuario usuario) {
        // Verifica si la lista de reparaciones en el automóvil es nula y la inicializa si es necesario
        if (usuario.getAutomoviles() == null) {
            usuario.setAutomoviles(new ArrayList<Automovil>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            // Adjunta las reparaciones al EntityManager
            adjuntarAutomoviles(em, usuario);
            // Persiste el automóvil en la base de datos
            em.persist(usuario);

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void adjuntarAutomoviles(EntityManager em, Usuario usuario) {
        List<Automovil> automoviles = usuario.getAutomoviles();
        if (automoviles != null && !automoviles.isEmpty()) {
            List<Automovil> automovilesAdjuntos = new ArrayList<>();
            for (Automovil automovil : automoviles) {
                automovilesAdjuntos.add(em.getReference(automovil.getClass(), automovil.getId()));
            }
            usuario.setAutomoviles(automovilesAdjuntos);
        }
    }
    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    // Método privado para encontrar las entidades Automovil con opciones de paginación
    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Usuario> cq = em.getCriteriaBuilder().createQuery(Usuario.class);
            cq.select(cq.from(Usuario.class));
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
