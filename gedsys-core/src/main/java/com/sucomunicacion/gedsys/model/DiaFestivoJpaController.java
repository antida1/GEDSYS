/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.model;

import com.sucomunicacion.gedsys.entities.DiaFestivo;
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
public class DiaFestivoJpaController implements Serializable {

    public DiaFestivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DiaFestivo diaFestivo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(diaFestivo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDiaFestivo(diaFestivo.getId()) != null) {
                throw new PreexistingEntityException("DiaFestivo " + diaFestivo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DiaFestivo diaFestivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            diaFestivo = em.merge(diaFestivo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = diaFestivo.getId();
                if (findDiaFestivo(id) == null) {
                    throw new NonexistentEntityException("The diaFestivo with id " + id + " no longer exists.");
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
            DiaFestivo diaFestivo;
            try {
                diaFestivo = em.getReference(DiaFestivo.class, id);
                diaFestivo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The diaFestivo with id " + id + " no longer exists.", enfe);
            }
            em.remove(diaFestivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DiaFestivo> findDiaFestivoEntities() {
        return findDiaFestivoEntities(true, -1, -1);
    }

    public List<DiaFestivo> findDiaFestivoEntities(int maxResults, int firstResult) {
        return findDiaFestivoEntities(false, maxResults, firstResult);
    }

    private List<DiaFestivo> findDiaFestivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DiaFestivo.class));
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

    public DiaFestivo findDiaFestivo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DiaFestivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiaFestivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DiaFestivo> rt = cq.from(DiaFestivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
