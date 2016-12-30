/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.model;

import com.sucomunicacion.gedsys.entities.Consecutivo;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
 */
public class ConsecutivoJpaController implements Serializable {

    public ConsecutivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Consecutivo consecutivo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(consecutivo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findConsecutivo(consecutivo.getId()) != null) {
                throw new PreexistingEntityException("Consecutivo " + consecutivo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Consecutivo consecutivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            consecutivo = em.merge(consecutivo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = consecutivo.getId();
                if (findConsecutivo(id) == null) {
                    throw new NonexistentEntityException("The consecutivo with id " + id + " no longer exists.");
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
            Consecutivo consecutivo;
            try {
                consecutivo = em.getReference(Consecutivo.class, id);
                consecutivo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The consecutivo with id " + id + " no longer exists.", enfe);
            }
            em.remove(consecutivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Consecutivo> findConsecutivoEntities() {
        return findConsecutivoEntities(true, -1, -1);
    }

    public List<Consecutivo> findConsecutivoEntities(int maxResults, int firstResult) {
        return findConsecutivoEntities(false, maxResults, firstResult);
    }

    private List<Consecutivo> findConsecutivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Consecutivo.class));
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

    public Consecutivo findConsecutivo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Consecutivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getConsecutivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Consecutivo> rt = cq.from(Consecutivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
