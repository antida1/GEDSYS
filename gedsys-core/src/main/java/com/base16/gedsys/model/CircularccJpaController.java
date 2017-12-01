/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.model;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.base16.gedsys.entities.Circular;
import com.base16.gedsys.entities.Circularcc;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class CircularccJpaController implements Serializable {

    public CircularccJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Circularcc circularcc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Circular circular = circularcc.getCircular();
            if (circular != null) {
                circular = em.getReference(circular.getClass(), circular.getId());
                circularcc.setCircular(circular);
            }
            Usuario conCopiaA = circularcc.getConCopiaA();
            if (conCopiaA != null) {
                conCopiaA = em.getReference(conCopiaA.getClass(), conCopiaA.getId());
                circularcc.setConCopiaA(conCopiaA);
            }
            em.persist(circularcc);
            if (circular != null) {
                circular.getCircularccList().add(circularcc);
                circular = em.merge(circular);
            }
            if (conCopiaA != null) {
                conCopiaA.getCircularccList().add(circularcc);
                conCopiaA = em.merge(conCopiaA);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Circularcc circularcc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Circularcc persistentCircularcc = em.find(Circularcc.class, circularcc.getId());
            Circular circularOld = persistentCircularcc.getCircular();
            Circular circularNew = circularcc.getCircular();
            Usuario conCopiaAOld = persistentCircularcc.getConCopiaA();
            Usuario conCopiaANew = circularcc.getConCopiaA();
            if (circularNew != null) {
                circularNew = em.getReference(circularNew.getClass(), circularNew.getId());
                circularcc.setCircular(circularNew);
            }
            if (conCopiaANew != null) {
                conCopiaANew = em.getReference(conCopiaANew.getClass(), conCopiaANew.getId());
                circularcc.setConCopiaA(conCopiaANew);
            }
            circularcc = em.merge(circularcc);
            if (circularOld != null && !circularOld.equals(circularNew)) {
                circularOld.getCircularccList().remove(circularcc);
                circularOld = em.merge(circularOld);
            }
            if (circularNew != null && !circularNew.equals(circularOld)) {
                circularNew.getCircularccList().add(circularcc);
                circularNew = em.merge(circularNew);
            }
            if (conCopiaAOld != null && !conCopiaAOld.equals(conCopiaANew)) {
                conCopiaAOld.getCircularccList().remove(circularcc);
                conCopiaAOld = em.merge(conCopiaAOld);
            }
            if (conCopiaANew != null && !conCopiaANew.equals(conCopiaAOld)) {
                conCopiaANew.getCircularccList().add(circularcc);
                conCopiaANew = em.merge(conCopiaANew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = circularcc.getId();
                if (findCircularcc(id) == null) {
                    throw new NonexistentEntityException("The circularcc with id " + id + " no longer exists.");
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
            Circularcc circularcc;
            try {
                circularcc = em.getReference(Circularcc.class, id);
                circularcc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The circularcc with id " + id + " no longer exists.", enfe);
            }
            Circular circular = circularcc.getCircular();
            if (circular != null) {
                circular.getCircularccList().remove(circularcc);
                circular = em.merge(circular);
            }
            Usuario conCopiaA = circularcc.getConCopiaA();
            if (conCopiaA != null) {
                conCopiaA.getCircularccList().remove(circularcc);
                conCopiaA = em.merge(conCopiaA);
            }
            em.remove(circularcc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Circularcc> findCircularccEntities() {
        return findCircularccEntities(true, -1, -1);
    }

    public List<Circularcc> findCircularccEntities(int maxResults, int firstResult) {
        return findCircularccEntities(false, maxResults, firstResult);
    }

    private List<Circularcc> findCircularccEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Circularcc.class));
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

    public Circularcc findCircularcc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Circularcc.class, id);
        } finally {
            em.close();
        }
    }

    public int getCircularccCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Circularcc> rt = cq.from(Circularcc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
