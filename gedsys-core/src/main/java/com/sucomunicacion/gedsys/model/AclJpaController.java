/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.model;

import com.sucomunicacion.gedsys.entities.Acl;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sucomunicacion.gedsys.entities.Modulo;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
 */
public class AclJpaController implements Serializable {

    public AclJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Acl acl) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Modulo moduleId = acl.getModuleId();
            if (moduleId != null) {
                moduleId = em.getReference(moduleId.getClass(), moduleId.getId());
                acl.setModuleId(moduleId);
            }
            em.persist(acl);
            if (moduleId != null) {
                moduleId.getAclList().add(acl);
                moduleId = em.merge(moduleId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAcl(acl.getId()) != null) {
                throw new PreexistingEntityException("Acl " + acl + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Acl acl) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acl persistentAcl = em.find(Acl.class, acl.getId());
            Modulo moduleIdOld = persistentAcl.getModuleId();
            Modulo moduleIdNew = acl.getModuleId();
            if (moduleIdNew != null) {
                moduleIdNew = em.getReference(moduleIdNew.getClass(), moduleIdNew.getId());
                acl.setModuleId(moduleIdNew);
            }
            acl = em.merge(acl);
            if (moduleIdOld != null && !moduleIdOld.equals(moduleIdNew)) {
                moduleIdOld.getAclList().remove(acl);
                moduleIdOld = em.merge(moduleIdOld);
            }
            if (moduleIdNew != null && !moduleIdNew.equals(moduleIdOld)) {
                moduleIdNew.getAclList().add(acl);
                moduleIdNew = em.merge(moduleIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = acl.getId();
                if (findAcl(id) == null) {
                    throw new NonexistentEntityException("The acl with id " + id + " no longer exists.");
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
            Acl acl;
            try {
                acl = em.getReference(Acl.class, id);
                acl.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The acl with id " + id + " no longer exists.", enfe);
            }
            Modulo moduleId = acl.getModuleId();
            if (moduleId != null) {
                moduleId.getAclList().remove(acl);
                moduleId = em.merge(moduleId);
            }
            em.remove(acl);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Acl> findAclEntities() {
        return findAclEntities(true, -1, -1);
    }

    public List<Acl> findAclEntities(int maxResults, int firstResult) {
        return findAclEntities(false, maxResults, firstResult);
    }

    private List<Acl> findAclEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Acl.class));
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

    public Acl findAcl(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Acl.class, id);
        } finally {
            em.close();
        }
    }

    public int getAclCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Acl> rt = cq.from(Acl.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
