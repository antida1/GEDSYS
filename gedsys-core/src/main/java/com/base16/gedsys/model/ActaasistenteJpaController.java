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
import com.base16.gedsys.entities.Acta;
import com.base16.gedsys.entities.Actaasistente;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class ActaasistenteJpaController implements Serializable {

    public ActaasistenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Actaasistente actaasistente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acta acta = actaasistente.getActa();
            if (acta != null) {
                acta = em.getReference(acta.getClass(), acta.getId());
                actaasistente.setActa(acta);
            }
            Usuario asistente = actaasistente.getAsistente();
            if (asistente != null) {
                asistente = em.getReference(asistente.getClass(), asistente.getId());
                actaasistente.setAsistente(asistente);
            }
            em.persist(actaasistente);
            if (acta != null) {
                acta.getActaasistenteList().add(actaasistente);
                acta = em.merge(acta);
            }
            if (asistente != null) {
                asistente.getActaasistenteList().add(actaasistente);
                asistente = em.merge(asistente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actaasistente actaasistente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actaasistente persistentActaasistente = em.find(Actaasistente.class, actaasistente.getId());
            Acta actaOld = persistentActaasistente.getActa();
            Acta actaNew = actaasistente.getActa();
            Usuario asistenteOld = persistentActaasistente.getAsistente();
            Usuario asistenteNew = actaasistente.getAsistente();
            if (actaNew != null) {
                actaNew = em.getReference(actaNew.getClass(), actaNew.getId());
                actaasistente.setActa(actaNew);
            }
            if (asistenteNew != null) {
                asistenteNew = em.getReference(asistenteNew.getClass(), asistenteNew.getId());
                actaasistente.setAsistente(asistenteNew);
            }
            actaasistente = em.merge(actaasistente);
            if (actaOld != null && !actaOld.equals(actaNew)) {
                actaOld.getActaasistenteList().remove(actaasistente);
                actaOld = em.merge(actaOld);
            }
            if (actaNew != null && !actaNew.equals(actaOld)) {
                actaNew.getActaasistenteList().add(actaasistente);
                actaNew = em.merge(actaNew);
            }
            if (asistenteOld != null && !asistenteOld.equals(asistenteNew)) {
                asistenteOld.getActaasistenteList().remove(actaasistente);
                asistenteOld = em.merge(asistenteOld);
            }
            if (asistenteNew != null && !asistenteNew.equals(asistenteOld)) {
                asistenteNew.getActaasistenteList().add(actaasistente);
                asistenteNew = em.merge(asistenteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = actaasistente.getId();
                if (findActaasistente(id) == null) {
                    throw new NonexistentEntityException("The actaasistente with id " + id + " no longer exists.");
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
            Actaasistente actaasistente;
            try {
                actaasistente = em.getReference(Actaasistente.class, id);
                actaasistente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actaasistente with id " + id + " no longer exists.", enfe);
            }
            Acta acta = actaasistente.getActa();
            if (acta != null) {
                acta.getActaasistenteList().remove(actaasistente);
                acta = em.merge(acta);
            }
            Usuario asistente = actaasistente.getAsistente();
            if (asistente != null) {
                asistente.getActaasistenteList().remove(actaasistente);
                asistente = em.merge(asistente);
            }
            em.remove(actaasistente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Actaasistente> findActaasistenteEntities() {
        return findActaasistenteEntities(true, -1, -1);
    }

    public List<Actaasistente> findActaasistenteEntities(int maxResults, int firstResult) {
        return findActaasistenteEntities(false, maxResults, firstResult);
    }

    private List<Actaasistente> findActaasistenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Actaasistente.class));
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

    public Actaasistente findActaasistente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actaasistente.class, id);
        } finally {
            em.close();
        }
    }

    public int getActaasistenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Actaasistente> rt = cq.from(Actaasistente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
