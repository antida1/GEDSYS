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
import com.base16.gedsys.entities.Actaausente;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class ActaausenteJpaController implements Serializable {

    public ActaausenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Actaausente actaausente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acta acta = actaausente.getActa();
            if (acta != null) {
                acta = em.getReference(acta.getClass(), acta.getId());
                actaausente.setActa(acta);
            }
            Usuario ausente = actaausente.getAusente();
            if (ausente != null) {
                ausente = em.getReference(ausente.getClass(), ausente.getId());
                actaausente.setAusente(ausente);
            }
            em.persist(actaausente);
            if (acta != null) {
                acta.getActaausenteList().add(actaausente);
                acta = em.merge(acta);
            }
            if (ausente != null) {
                ausente.getActaausenteList().add(actaausente);
                ausente = em.merge(ausente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actaausente actaausente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actaausente persistentActaausente = em.find(Actaausente.class, actaausente.getId());
            Acta actaOld = persistentActaausente.getActa();
            Acta actaNew = actaausente.getActa();
            Usuario ausenteOld = persistentActaausente.getAusente();
            Usuario ausenteNew = actaausente.getAusente();
            if (actaNew != null) {
                actaNew = em.getReference(actaNew.getClass(), actaNew.getId());
                actaausente.setActa(actaNew);
            }
            if (ausenteNew != null) {
                ausenteNew = em.getReference(ausenteNew.getClass(), ausenteNew.getId());
                actaausente.setAusente(ausenteNew);
            }
            actaausente = em.merge(actaausente);
            if (actaOld != null && !actaOld.equals(actaNew)) {
                actaOld.getActaausenteList().remove(actaausente);
                actaOld = em.merge(actaOld);
            }
            if (actaNew != null && !actaNew.equals(actaOld)) {
                actaNew.getActaausenteList().add(actaausente);
                actaNew = em.merge(actaNew);
            }
            if (ausenteOld != null && !ausenteOld.equals(ausenteNew)) {
                ausenteOld.getActaausenteList().remove(actaausente);
                ausenteOld = em.merge(ausenteOld);
            }
            if (ausenteNew != null && !ausenteNew.equals(ausenteOld)) {
                ausenteNew.getActaausenteList().add(actaausente);
                ausenteNew = em.merge(ausenteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = actaausente.getId();
                if (findActaausente(id) == null) {
                    throw new NonexistentEntityException("The actaausente with id " + id + " no longer exists.");
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
            Actaausente actaausente;
            try {
                actaausente = em.getReference(Actaausente.class, id);
                actaausente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actaausente with id " + id + " no longer exists.", enfe);
            }
            Acta acta = actaausente.getActa();
            if (acta != null) {
                acta.getActaausenteList().remove(actaausente);
                acta = em.merge(acta);
            }
            Usuario ausente = actaausente.getAusente();
            if (ausente != null) {
                ausente.getActaausenteList().remove(actaausente);
                ausente = em.merge(ausente);
            }
            em.remove(actaausente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Actaausente> findActaausenteEntities() {
        return findActaausenteEntities(true, -1, -1);
    }

    public List<Actaausente> findActaausenteEntities(int maxResults, int firstResult) {
        return findActaausenteEntities(false, maxResults, firstResult);
    }

    private List<Actaausente> findActaausenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Actaausente.class));
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

    public Actaausente findActaausente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actaausente.class, id);
        } finally {
            em.close();
        }
    }

    public int getActaausenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Actaausente> rt = cq.from(Actaausente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
