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
import com.base16.gedsys.entities.Comunicacion;
import com.base16.gedsys.entities.Comunicacioncc;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class ComunicacionccJpaController implements Serializable {

    public ComunicacionccJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comunicacioncc comunicacioncc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comunicacion comunicacion = comunicacioncc.getComunicacion();
            if (comunicacion != null) {
                comunicacion = em.getReference(comunicacion.getClass(), comunicacion.getId());
                comunicacioncc.setComunicacion(comunicacion);
            }
            Usuario conCopiaA = comunicacioncc.getConCopiaA();
            if (conCopiaA != null) {
                conCopiaA = em.getReference(conCopiaA.getClass(), conCopiaA.getId());
                comunicacioncc.setConCopiaA(conCopiaA);
            }
            em.persist(comunicacioncc);
            if (comunicacion != null) {
                comunicacion.getComunicacionccList().add(comunicacioncc);
                comunicacion = em.merge(comunicacion);
            }
            if (conCopiaA != null) {
                conCopiaA.getComunicacionccList().add(comunicacioncc);
                conCopiaA = em.merge(conCopiaA);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comunicacioncc comunicacioncc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comunicacioncc persistentComunicacioncc = em.find(Comunicacioncc.class, comunicacioncc.getId());
            Comunicacion comunicacionOld = persistentComunicacioncc.getComunicacion();
            Comunicacion comunicacionNew = comunicacioncc.getComunicacion();
            Usuario conCopiaAOld = persistentComunicacioncc.getConCopiaA();
            Usuario conCopiaANew = comunicacioncc.getConCopiaA();
            if (comunicacionNew != null) {
                comunicacionNew = em.getReference(comunicacionNew.getClass(), comunicacionNew.getId());
                comunicacioncc.setComunicacion(comunicacionNew);
            }
            if (conCopiaANew != null) {
                conCopiaANew = em.getReference(conCopiaANew.getClass(), conCopiaANew.getId());
                comunicacioncc.setConCopiaA(conCopiaANew);
            }
            comunicacioncc = em.merge(comunicacioncc);
            if (comunicacionOld != null && !comunicacionOld.equals(comunicacionNew)) {
                comunicacionOld.getComunicacionccList().remove(comunicacioncc);
                comunicacionOld = em.merge(comunicacionOld);
            }
            if (comunicacionNew != null && !comunicacionNew.equals(comunicacionOld)) {
                comunicacionNew.getComunicacionccList().add(comunicacioncc);
                comunicacionNew = em.merge(comunicacionNew);
            }
            if (conCopiaAOld != null && !conCopiaAOld.equals(conCopiaANew)) {
                conCopiaAOld.getComunicacionccList().remove(comunicacioncc);
                conCopiaAOld = em.merge(conCopiaAOld);
            }
            if (conCopiaANew != null && !conCopiaANew.equals(conCopiaAOld)) {
                conCopiaANew.getComunicacionccList().add(comunicacioncc);
                conCopiaANew = em.merge(conCopiaANew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comunicacioncc.getId();
                if (findComunicacioncc(id) == null) {
                    throw new NonexistentEntityException("The comunicacioncc with id " + id + " no longer exists.");
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
            Comunicacioncc comunicacioncc;
            try {
                comunicacioncc = em.getReference(Comunicacioncc.class, id);
                comunicacioncc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comunicacioncc with id " + id + " no longer exists.", enfe);
            }
            Comunicacion comunicacion = comunicacioncc.getComunicacion();
            if (comunicacion != null) {
                comunicacion.getComunicacionccList().remove(comunicacioncc);
                comunicacion = em.merge(comunicacion);
            }
            Usuario conCopiaA = comunicacioncc.getConCopiaA();
            if (conCopiaA != null) {
                conCopiaA.getComunicacionccList().remove(comunicacioncc);
                conCopiaA = em.merge(conCopiaA);
            }
            em.remove(comunicacioncc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comunicacioncc> findComunicacionccEntities() {
        return findComunicacionccEntities(true, -1, -1);
    }

    public List<Comunicacioncc> findComunicacionccEntities(int maxResults, int firstResult) {
        return findComunicacionccEntities(false, maxResults, firstResult);
    }

    private List<Comunicacioncc> findComunicacionccEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comunicacioncc.class));
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

    public Comunicacioncc findComunicacioncc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comunicacioncc.class, id);
        } finally {
            em.close();
        }
    }

    public int getComunicacionccCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comunicacioncc> rt = cq.from(Comunicacioncc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
