/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.model;

import com.sucomunicacion.gedsys.entities.FlujoDocumental;
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
public class FlujoDocumentalJpaController implements Serializable {

    public FlujoDocumentalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FlujoDocumental flujoDocumental) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(flujoDocumental);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFlujoDocumental(flujoDocumental.getId()) != null) {
                throw new PreexistingEntityException("FlujoDocumental " + flujoDocumental + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FlujoDocumental flujoDocumental) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            flujoDocumental = em.merge(flujoDocumental);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = flujoDocumental.getId();
                if (findFlujoDocumental(id) == null) {
                    throw new NonexistentEntityException("The flujoDocumental with id " + id + " no longer exists.");
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
            FlujoDocumental flujoDocumental;
            try {
                flujoDocumental = em.getReference(FlujoDocumental.class, id);
                flujoDocumental.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The flujoDocumental with id " + id + " no longer exists.", enfe);
            }
            em.remove(flujoDocumental);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FlujoDocumental> findFlujoDocumentalEntities() {
        return findFlujoDocumentalEntities(true, -1, -1);
    }

    public List<FlujoDocumental> findFlujoDocumentalEntities(int maxResults, int firstResult) {
        return findFlujoDocumentalEntities(false, maxResults, firstResult);
    }

    private List<FlujoDocumental> findFlujoDocumentalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FlujoDocumental.class));
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

    public FlujoDocumental findFlujoDocumental(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FlujoDocumental.class, id);
        } finally {
            em.close();
        }
    }

    public int getFlujoDocumentalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FlujoDocumental> rt = cq.from(FlujoDocumental.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
