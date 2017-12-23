/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.model;

import com.base16.gedsys.entities.Sede;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class SedeJpaController implements Serializable {

    public SedeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sede sede) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = sede.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                sede.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = sede.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                sede.setModificadoPor(modificadoPor);
            }
            em.persist(sede);
            if (creadoPor != null) {
                creadoPor.getSedeList().add(sede);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getSedeList().add(sede);
                modificadoPor = em.merge(modificadoPor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sede sede) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sede persistentSede = em.find(Sede.class, sede.getId());
            Usuario creadoPorOld = persistentSede.getCreadoPor();
            Usuario creadoPorNew = sede.getCreadoPor();
            Usuario modificadoPorOld = persistentSede.getModificadoPor();
            Usuario modificadoPorNew = sede.getModificadoPor();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                sede.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                sede.setModificadoPor(modificadoPorNew);
            }
            sede = em.merge(sede);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getSedeList().remove(sede);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getSedeList().add(sede);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getSedeList().remove(sede);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getSedeList().add(sede);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sede.getId();
                if (findSede(id) == null) {
                    throw new NonexistentEntityException("The sede with id " + id + " no longer exists.");
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
            Sede sede;
            try {
                sede = em.getReference(Sede.class, id);
                sede.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sede with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = sede.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getSedeList().remove(sede);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = sede.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getSedeList().remove(sede);
                modificadoPor = em.merge(modificadoPor);
            }
            em.remove(sede);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sede> findSedeEntities() {
        return findSedeEntities(true, -1, -1);
    }

    public List<Sede> findSedeEntities(int maxResults, int firstResult) {
        return findSedeEntities(false, maxResults, firstResult);
    }

    private List<Sede> findSedeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sede.class));
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

    public Sede findSede(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sede.class, id);
        } finally {
            em.close();
        }
    }

    public int getSedeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sede> rt = cq.from(Sede.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
