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
import com.sucomunicacion.gedsys.entities.SignaturaTopografica;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
 */
public class SignaturaTopograficaJpaController implements Serializable {

    public SignaturaTopograficaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SignaturaTopografica signaturaTopografica) throws PreexistingEntityException, Exception {
        if (signaturaTopografica.getSignaturaTopograficaList() == null) {
            signaturaTopografica.setSignaturaTopograficaList(new ArrayList<SignaturaTopografica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SignaturaTopografica dependeDe = signaturaTopografica.getDependeDe();
            if (dependeDe != null) {
                dependeDe = em.getReference(dependeDe.getClass(), dependeDe.getId());
                signaturaTopografica.setDependeDe(dependeDe);
            }
            List<SignaturaTopografica> attachedSignaturaTopograficaList = new ArrayList<SignaturaTopografica>();
            for (SignaturaTopografica signaturaTopograficaListSignaturaTopograficaToAttach : signaturaTopografica.getSignaturaTopograficaList()) {
                signaturaTopograficaListSignaturaTopograficaToAttach = em.getReference(signaturaTopograficaListSignaturaTopograficaToAttach.getClass(), signaturaTopograficaListSignaturaTopograficaToAttach.getId());
                attachedSignaturaTopograficaList.add(signaturaTopograficaListSignaturaTopograficaToAttach);
            }
            signaturaTopografica.setSignaturaTopograficaList(attachedSignaturaTopograficaList);
            em.persist(signaturaTopografica);
            if (dependeDe != null) {
                dependeDe.getSignaturaTopograficaList().add(signaturaTopografica);
                dependeDe = em.merge(dependeDe);
            }
            for (SignaturaTopografica signaturaTopograficaListSignaturaTopografica : signaturaTopografica.getSignaturaTopograficaList()) {
                SignaturaTopografica oldDependeDeOfSignaturaTopograficaListSignaturaTopografica = signaturaTopograficaListSignaturaTopografica.getDependeDe();
                signaturaTopograficaListSignaturaTopografica.setDependeDe(signaturaTopografica);
                signaturaTopograficaListSignaturaTopografica = em.merge(signaturaTopograficaListSignaturaTopografica);
                if (oldDependeDeOfSignaturaTopograficaListSignaturaTopografica != null) {
                    oldDependeDeOfSignaturaTopograficaListSignaturaTopografica.getSignaturaTopograficaList().remove(signaturaTopograficaListSignaturaTopografica);
                    oldDependeDeOfSignaturaTopograficaListSignaturaTopografica = em.merge(oldDependeDeOfSignaturaTopograficaListSignaturaTopografica);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSignaturaTopografica(signaturaTopografica.getId()) != null) {
                throw new PreexistingEntityException("SignaturaTopografica " + signaturaTopografica + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SignaturaTopografica signaturaTopografica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SignaturaTopografica persistentSignaturaTopografica = em.find(SignaturaTopografica.class, signaturaTopografica.getId());
            SignaturaTopografica dependeDeOld = persistentSignaturaTopografica.getDependeDe();
            SignaturaTopografica dependeDeNew = signaturaTopografica.getDependeDe();
            List<SignaturaTopografica> signaturaTopograficaListOld = persistentSignaturaTopografica.getSignaturaTopograficaList();
            List<SignaturaTopografica> signaturaTopograficaListNew = signaturaTopografica.getSignaturaTopograficaList();
            if (dependeDeNew != null) {
                dependeDeNew = em.getReference(dependeDeNew.getClass(), dependeDeNew.getId());
                signaturaTopografica.setDependeDe(dependeDeNew);
            }
            List<SignaturaTopografica> attachedSignaturaTopograficaListNew = new ArrayList<SignaturaTopografica>();
            for (SignaturaTopografica signaturaTopograficaListNewSignaturaTopograficaToAttach : signaturaTopograficaListNew) {
                signaturaTopograficaListNewSignaturaTopograficaToAttach = em.getReference(signaturaTopograficaListNewSignaturaTopograficaToAttach.getClass(), signaturaTopograficaListNewSignaturaTopograficaToAttach.getId());
                attachedSignaturaTopograficaListNew.add(signaturaTopograficaListNewSignaturaTopograficaToAttach);
            }
            signaturaTopograficaListNew = attachedSignaturaTopograficaListNew;
            signaturaTopografica.setSignaturaTopograficaList(signaturaTopograficaListNew);
            signaturaTopografica = em.merge(signaturaTopografica);
            if (dependeDeOld != null && !dependeDeOld.equals(dependeDeNew)) {
                dependeDeOld.getSignaturaTopograficaList().remove(signaturaTopografica);
                dependeDeOld = em.merge(dependeDeOld);
            }
            if (dependeDeNew != null && !dependeDeNew.equals(dependeDeOld)) {
                dependeDeNew.getSignaturaTopograficaList().add(signaturaTopografica);
                dependeDeNew = em.merge(dependeDeNew);
            }
            for (SignaturaTopografica signaturaTopograficaListOldSignaturaTopografica : signaturaTopograficaListOld) {
                if (!signaturaTopograficaListNew.contains(signaturaTopograficaListOldSignaturaTopografica)) {
                    signaturaTopograficaListOldSignaturaTopografica.setDependeDe(null);
                    signaturaTopograficaListOldSignaturaTopografica = em.merge(signaturaTopograficaListOldSignaturaTopografica);
                }
            }
            for (SignaturaTopografica signaturaTopograficaListNewSignaturaTopografica : signaturaTopograficaListNew) {
                if (!signaturaTopograficaListOld.contains(signaturaTopograficaListNewSignaturaTopografica)) {
                    SignaturaTopografica oldDependeDeOfSignaturaTopograficaListNewSignaturaTopografica = signaturaTopograficaListNewSignaturaTopografica.getDependeDe();
                    signaturaTopograficaListNewSignaturaTopografica.setDependeDe(signaturaTopografica);
                    signaturaTopograficaListNewSignaturaTopografica = em.merge(signaturaTopograficaListNewSignaturaTopografica);
                    if (oldDependeDeOfSignaturaTopograficaListNewSignaturaTopografica != null && !oldDependeDeOfSignaturaTopograficaListNewSignaturaTopografica.equals(signaturaTopografica)) {
                        oldDependeDeOfSignaturaTopograficaListNewSignaturaTopografica.getSignaturaTopograficaList().remove(signaturaTopograficaListNewSignaturaTopografica);
                        oldDependeDeOfSignaturaTopograficaListNewSignaturaTopografica = em.merge(oldDependeDeOfSignaturaTopograficaListNewSignaturaTopografica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = signaturaTopografica.getId();
                if (findSignaturaTopografica(id) == null) {
                    throw new NonexistentEntityException("The signaturaTopografica with id " + id + " no longer exists.");
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
            SignaturaTopografica signaturaTopografica;
            try {
                signaturaTopografica = em.getReference(SignaturaTopografica.class, id);
                signaturaTopografica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The signaturaTopografica with id " + id + " no longer exists.", enfe);
            }
            SignaturaTopografica dependeDe = signaturaTopografica.getDependeDe();
            if (dependeDe != null) {
                dependeDe.getSignaturaTopograficaList().remove(signaturaTopografica);
                dependeDe = em.merge(dependeDe);
            }
            List<SignaturaTopografica> signaturaTopograficaList = signaturaTopografica.getSignaturaTopograficaList();
            for (SignaturaTopografica signaturaTopograficaListSignaturaTopografica : signaturaTopograficaList) {
                signaturaTopograficaListSignaturaTopografica.setDependeDe(null);
                signaturaTopograficaListSignaturaTopografica = em.merge(signaturaTopograficaListSignaturaTopografica);
            }
            em.remove(signaturaTopografica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SignaturaTopografica> findSignaturaTopograficaEntities() {
        return findSignaturaTopograficaEntities(true, -1, -1);
    }

    public List<SignaturaTopografica> findSignaturaTopograficaEntities(int maxResults, int firstResult) {
        return findSignaturaTopograficaEntities(false, maxResults, firstResult);
    }

    private List<SignaturaTopografica> findSignaturaTopograficaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SignaturaTopografica.class));
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

    public SignaturaTopografica findSignaturaTopografica(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SignaturaTopografica.class, id);
        } finally {
            em.close();
        }
    }

    public int getSignaturaTopograficaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SignaturaTopografica> rt = cq.from(SignaturaTopografica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
