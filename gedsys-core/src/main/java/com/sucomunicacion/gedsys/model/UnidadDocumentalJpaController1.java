/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.model;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sucomunicacion.gedsys.entities.SubSerie;
import com.sucomunicacion.gedsys.entities.UnidadDocumental;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
 */
public class UnidadDocumentalJpaController1 implements Serializable {

    public UnidadDocumentalJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UnidadDocumental unidadDocumental) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SubSerie subSerie = unidadDocumental.getSubSerie();
            if (subSerie != null) {
                subSerie = em.getReference(subSerie.getClass(), subSerie.getId());
                unidadDocumental.setSubSerie(subSerie);
            }
            em.persist(unidadDocumental);
            if (subSerie != null) {
                subSerie.getUnidadDocumentalList().add(unidadDocumental);
                subSerie = em.merge(subSerie);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUnidadDocumental(unidadDocumental.getId()) != null) {
                throw new PreexistingEntityException("UnidadDocumental " + unidadDocumental + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UnidadDocumental unidadDocumental) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UnidadDocumental persistentUnidadDocumental = em.find(UnidadDocumental.class, unidadDocumental.getId());
            SubSerie subSerieOld = persistentUnidadDocumental.getSubSerie();
            SubSerie subSerieNew = unidadDocumental.getSubSerie();
            if (subSerieNew != null) {
                subSerieNew = em.getReference(subSerieNew.getClass(), subSerieNew.getId());
                unidadDocumental.setSubSerie(subSerieNew);
            }
            unidadDocumental = em.merge(unidadDocumental);
            if (subSerieOld != null && !subSerieOld.equals(subSerieNew)) {
                subSerieOld.getUnidadDocumentalList().remove(unidadDocumental);
                subSerieOld = em.merge(subSerieOld);
            }
            if (subSerieNew != null && !subSerieNew.equals(subSerieOld)) {
                subSerieNew.getUnidadDocumentalList().add(unidadDocumental);
                subSerieNew = em.merge(subSerieNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = unidadDocumental.getId();
                if (findUnidadDocumental(id) == null) {
                    throw new NonexistentEntityException("The unidadDocumental with id " + id + " no longer exists.");
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
            UnidadDocumental unidadDocumental;
            try {
                unidadDocumental = em.getReference(UnidadDocumental.class, id);
                unidadDocumental.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The unidadDocumental with id " + id + " no longer exists.", enfe);
            }
            SubSerie subSerie = unidadDocumental.getSubSerie();
            if (subSerie != null) {
                subSerie.getUnidadDocumentalList().remove(unidadDocumental);
                subSerie = em.merge(subSerie);
            }
            em.remove(unidadDocumental);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UnidadDocumental> findUnidadDocumentalEntities() {
        return findUnidadDocumentalEntities(true, -1, -1);
    }

    public List<UnidadDocumental> findUnidadDocumentalEntities(int maxResults, int firstResult) {
        return findUnidadDocumentalEntities(false, maxResults, firstResult);
    }

    private List<UnidadDocumental> findUnidadDocumentalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UnidadDocumental.class));
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

    public UnidadDocumental findUnidadDocumental(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UnidadDocumental.class, id);
        } finally {
            em.close();
        }
    }

    public int getUnidadDocumentalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UnidadDocumental> rt = cq.from(UnidadDocumental.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
