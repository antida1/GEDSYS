/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.model;

import com.sucomunicacion.gedsys.entities.Preferencias;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class PreferenciasJpaController implements Serializable {

    public PreferenciasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Preferencias preferencias) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = preferencias.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                preferencias.setUsuario(usuario);
            }
            em.persist(preferencias);
            if (usuario != null) {
                usuario.getPreferenciasCollection().add(preferencias);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Preferencias preferencias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Preferencias persistentPreferencias = em.find(Preferencias.class, preferencias.getId());
            Usuario usuarioOld = persistentPreferencias.getUsuario();
            Usuario usuarioNew = preferencias.getUsuario();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                preferencias.setUsuario(usuarioNew);
            }
            preferencias = em.merge(preferencias);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getPreferenciasCollection().remove(preferencias);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getPreferenciasCollection().add(preferencias);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = preferencias.getId();
                if (findPreferencias(id) == null) {
                    throw new NonexistentEntityException("The preferencias with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Preferencias preferencias;
            try {
                preferencias = em.getReference(Preferencias.class, id);
                preferencias.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The preferencias with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = preferencias.getUsuario();
            if (usuario != null) {
                usuario.getPreferenciasCollection().remove(preferencias);
                usuario = em.merge(usuario);
            }
            em.remove(preferencias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Preferencias> findPreferenciasEntities() {
        return findPreferenciasEntities(true, -1, -1);
    }

    public List<Preferencias> findPreferenciasEntities(int maxResults, int firstResult) {
        return findPreferenciasEntities(false, maxResults, firstResult);
    }

    private List<Preferencias> findPreferenciasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Preferencias.class));
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

    public Preferencias findPreferencias(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Preferencias.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreferenciasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Preferencias> rt = cq.from(Preferencias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
