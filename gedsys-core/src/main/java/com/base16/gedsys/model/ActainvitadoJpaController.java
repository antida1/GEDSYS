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
import com.base16.gedsys.entities.Actainvitado;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class ActainvitadoJpaController implements Serializable {

    public ActainvitadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Actainvitado actainvitado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acta acta = actainvitado.getActa();
            if (acta != null) {
                acta = em.getReference(acta.getClass(), acta.getId());
                actainvitado.setActa(acta);
            }
            Usuario invitado = actainvitado.getInvitado();
            if (invitado != null) {
                invitado = em.getReference(invitado.getClass(), invitado.getId());
                actainvitado.setInvitado(invitado);
            }
            em.persist(actainvitado);
            if (acta != null) {
                acta.getActainvitadoList().add(actainvitado);
                acta = em.merge(acta);
            }
            if (invitado != null) {
                invitado.getActainvitadoList().add(actainvitado);
                invitado = em.merge(invitado);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actainvitado actainvitado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actainvitado persistentActainvitado = em.find(Actainvitado.class, actainvitado.getId());
            Acta actaOld = persistentActainvitado.getActa();
            Acta actaNew = actainvitado.getActa();
            Usuario invitadoOld = persistentActainvitado.getInvitado();
            Usuario invitadoNew = actainvitado.getInvitado();
            if (actaNew != null) {
                actaNew = em.getReference(actaNew.getClass(), actaNew.getId());
                actainvitado.setActa(actaNew);
            }
            if (invitadoNew != null) {
                invitadoNew = em.getReference(invitadoNew.getClass(), invitadoNew.getId());
                actainvitado.setInvitado(invitadoNew);
            }
            actainvitado = em.merge(actainvitado);
            if (actaOld != null && !actaOld.equals(actaNew)) {
                actaOld.getActainvitadoList().remove(actainvitado);
                actaOld = em.merge(actaOld);
            }
            if (actaNew != null && !actaNew.equals(actaOld)) {
                actaNew.getActainvitadoList().add(actainvitado);
                actaNew = em.merge(actaNew);
            }
            if (invitadoOld != null && !invitadoOld.equals(invitadoNew)) {
                invitadoOld.getActainvitadoList().remove(actainvitado);
                invitadoOld = em.merge(invitadoOld);
            }
            if (invitadoNew != null && !invitadoNew.equals(invitadoOld)) {
                invitadoNew.getActainvitadoList().add(actainvitado);
                invitadoNew = em.merge(invitadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = actainvitado.getId();
                if (findActainvitado(id) == null) {
                    throw new NonexistentEntityException("The actainvitado with id " + id + " no longer exists.");
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
            Actainvitado actainvitado;
            try {
                actainvitado = em.getReference(Actainvitado.class, id);
                actainvitado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actainvitado with id " + id + " no longer exists.", enfe);
            }
            Acta acta = actainvitado.getActa();
            if (acta != null) {
                acta.getActainvitadoList().remove(actainvitado);
                acta = em.merge(acta);
            }
            Usuario invitado = actainvitado.getInvitado();
            if (invitado != null) {
                invitado.getActainvitadoList().remove(actainvitado);
                invitado = em.merge(invitado);
            }
            em.remove(actainvitado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Actainvitado> findActainvitadoEntities() {
        return findActainvitadoEntities(true, -1, -1);
    }

    public List<Actainvitado> findActainvitadoEntities(int maxResults, int firstResult) {
        return findActainvitadoEntities(false, maxResults, firstResult);
    }

    private List<Actainvitado> findActainvitadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Actainvitado.class));
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

    public Actainvitado findActainvitado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actainvitado.class, id);
        } finally {
            em.close();
        }
    }

    public int getActainvitadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Actainvitado> rt = cq.from(Actainvitado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
