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
        // Verifica si la lista de automóviles en la entidad Mecanica es nula y la inicializa si es necesario
        if (mecanica.getAutomoviles() == null) {
            mecanica.setAutomoviles(new ArrayList<Automovil>());
        }
        EntityManager em = null;
        try {
            // Inicia la transacción con el EntityManager
            em = getEntityManager();
            em.getTransaction().begin();
            // Adjunta los automóviles al EntityManager
            List<Automovil> automovilesAdjuntos = adjuntarAutomoviles(em, mecanica.getAutomoviles());
            mecanica.setAutomoviles(automovilesAdjuntos);
            // Persiste la entidad Mecanica en la base de datos
            em.persist(mecanica);
            // Confirma la transacción
            em.getTransaction().commit();
        } finally {
            // Cierra el EntityManager
            if (em != null) {
                em.close();
            }
        }
    }

    // Adjunta los automóviles al EntityManager
    private List<Automovil> adjuntarAutomoviles(EntityManager em, List<Automovil> automoviles) {
        List<Automovil> automovilesAdjuntos = new ArrayList<>();
        for (Automovil automovil : automoviles) {
            // Obtiene una referencia a cada automóvil y lo agrega a la lista de automóviles adjuntos
            automovilesAdjuntos.add(em.getReference(Automovil.class, automovil.getId()));
        }
        return automovilesAdjuntos; // Devuelve la lista de automóviles adjuntos
    }

    public Mecanica findMecanica(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mecanica.class, id);
        } finally {
            em.close();
        }
    }


    
}
