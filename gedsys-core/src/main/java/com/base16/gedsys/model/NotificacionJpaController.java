/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.model;

import com.base16.gedsys.entities.Notificacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class NotificacionJpaController implements Serializable {

    public NotificacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Notificacion notificacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario responsable = notificacion.getResponsable();
            if (responsable != null) {
                responsable = em.getReference(responsable.getClass(), responsable.getId());
                notificacion.setResponsable(responsable);
            }
            em.persist(notificacion);
            if (responsable != null) {
                responsable.getNotificacionCollection().add(notificacion);
                responsable = em.merge(responsable);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Notificacion notificacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Notificacion persistentNotificacion = em.find(Notificacion.class, notificacion.getId());
            Usuario responsableOld = persistentNotificacion.getResponsable();
            Usuario responsableNew = notificacion.getResponsable();
            if (responsableNew != null) {
                responsableNew = em.getReference(responsableNew.getClass(), responsableNew.getId());
                notificacion.setResponsable(responsableNew);
            }
            notificacion = em.merge(notificacion);
            if (responsableOld != null && !responsableOld.equals(responsableNew)) {
                responsableOld.getNotificacionCollection().remove(notificacion);
                responsableOld = em.merge(responsableOld);
            }
            if (responsableNew != null && !responsableNew.equals(responsableOld)) {
                responsableNew.getNotificacionCollection().add(notificacion);
                responsableNew = em.merge(responsableNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = notificacion.getId();
                if (findNotificacion(id) == null) {
                    throw new NonexistentEntityException("The notificacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Notificacion notificacion;
            try {
                notificacion = em.getReference(Notificacion.class, id);
                notificacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notificacion with id " + id + " no longer exists.", enfe);
            }
            Usuario responsable = notificacion.getResponsable();
            if (responsable != null) {
                responsable.getNotificacionCollection().remove(notificacion);
                responsable = em.merge(responsable);
            }
            em.remove(notificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Notificacion> findNotificacionEntities() {
        return findNotificacionEntities(true, -1, -1);
    }

    public List<Notificacion> findNotificacionEntities(int maxResults, int firstResult) {
        return findNotificacionEntities(false, maxResults, firstResult);
    }

    private List<Notificacion> findNotificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Notificacion.class));
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

    public Notificacion findNotificacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Notificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotificacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Notificacion> rt = cq.from(Notificacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Notificacion> findNotificacionByResponsable(Usuario responsable) {
        return findNotificacionByResponsable(responsable, -1, -1);
}

    public List<Notificacion> findNotificacionByResponsable(Usuario responsable, int maxResults, int firstResult) {
        return findNotificacionByResponsable(responsable, true, maxResults, firstResult);
    }
    
    private List<Notificacion> findNotificacionByResponsable( Usuario responsable ,boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Notificacion.class));
            Query q = em.createNamedQuery("Notificacion.findByResponsable", Notificacion.class)
                    .setParameter("responsable", responsable);
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
