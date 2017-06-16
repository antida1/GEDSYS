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
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.entities.SeccionSubSeccion;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class SeccionSubSeccionJpaController implements Serializable {

    public SeccionSubSeccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeccionSubSeccion seccionSubSeccion) {
        if (seccionSubSeccion.getSeccionSubSeccionCollection() == null) {
            seccionSubSeccion.setSeccionSubSeccionCollection(new ArrayList<SeccionSubSeccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = seccionSubSeccion.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                seccionSubSeccion.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = seccionSubSeccion.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                seccionSubSeccion.setModificadoPor(modificadoPor);
            }
            SeccionSubSeccion dependeDe = seccionSubSeccion.getDependeDe();
            if (dependeDe != null) {
                dependeDe = em.getReference(dependeDe.getClass(), dependeDe.getId());
                seccionSubSeccion.setDependeDe(dependeDe);
            }
            Collection<SeccionSubSeccion> attachedSeccionSubSeccionCollection = new ArrayList<SeccionSubSeccion>();
            for (SeccionSubSeccion seccionSubSeccionCollectionSeccionSubSeccionToAttach : seccionSubSeccion.getSeccionSubSeccionCollection()) {
                seccionSubSeccionCollectionSeccionSubSeccionToAttach = em.getReference(seccionSubSeccionCollectionSeccionSubSeccionToAttach.getClass(), seccionSubSeccionCollectionSeccionSubSeccionToAttach.getId());
                attachedSeccionSubSeccionCollection.add(seccionSubSeccionCollectionSeccionSubSeccionToAttach);
            }
            seccionSubSeccion.setSeccionSubSeccionCollection(attachedSeccionSubSeccionCollection);
            em.persist(seccionSubSeccion);
            if (creadoPor != null) {
                creadoPor.getSeccionSubSeccionCollection().add(seccionSubSeccion);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getSeccionSubSeccionCollection().add(seccionSubSeccion);
                modificadoPor = em.merge(modificadoPor);
            }
            if (dependeDe != null) {
                dependeDe.getSeccionSubSeccionCollection().add(seccionSubSeccion);
                dependeDe = em.merge(dependeDe);
            }
            for (SeccionSubSeccion seccionSubSeccionCollectionSeccionSubSeccion : seccionSubSeccion.getSeccionSubSeccionCollection()) {
                SeccionSubSeccion oldDependeDeOfSeccionSubSeccionCollectionSeccionSubSeccion = seccionSubSeccionCollectionSeccionSubSeccion.getDependeDe();
                seccionSubSeccionCollectionSeccionSubSeccion.setDependeDe(seccionSubSeccion);
                seccionSubSeccionCollectionSeccionSubSeccion = em.merge(seccionSubSeccionCollectionSeccionSubSeccion);
                if (oldDependeDeOfSeccionSubSeccionCollectionSeccionSubSeccion != null) {
                    oldDependeDeOfSeccionSubSeccionCollectionSeccionSubSeccion.getSeccionSubSeccionCollection().remove(seccionSubSeccionCollectionSeccionSubSeccion);
                    oldDependeDeOfSeccionSubSeccionCollectionSeccionSubSeccion = em.merge(oldDependeDeOfSeccionSubSeccionCollectionSeccionSubSeccion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeccionSubSeccion seccionSubSeccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeccionSubSeccion persistentSeccionSubSeccion = em.find(SeccionSubSeccion.class, seccionSubSeccion.getId());
            Usuario creadoPorOld = persistentSeccionSubSeccion.getCreadoPor();
            Usuario creadoPorNew = seccionSubSeccion.getCreadoPor();
            Usuario modificadoPorOld = persistentSeccionSubSeccion.getModificadoPor();
            Usuario modificadoPorNew = seccionSubSeccion.getModificadoPor();
            SeccionSubSeccion dependeDeOld = persistentSeccionSubSeccion.getDependeDe();
            SeccionSubSeccion dependeDeNew = seccionSubSeccion.getDependeDe();
            Collection<SeccionSubSeccion> seccionSubSeccionCollectionOld = persistentSeccionSubSeccion.getSeccionSubSeccionCollection();
            Collection<SeccionSubSeccion> seccionSubSeccionCollectionNew = seccionSubSeccion.getSeccionSubSeccionCollection();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                seccionSubSeccion.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                seccionSubSeccion.setModificadoPor(modificadoPorNew);
            }
            if (dependeDeNew != null) {
                dependeDeNew = em.getReference(dependeDeNew.getClass(), dependeDeNew.getId());
                seccionSubSeccion.setDependeDe(dependeDeNew);
            }
            Collection<SeccionSubSeccion> attachedSeccionSubSeccionCollectionNew = new ArrayList<SeccionSubSeccion>();
            for (SeccionSubSeccion seccionSubSeccionCollectionNewSeccionSubSeccionToAttach : seccionSubSeccionCollectionNew) {
                seccionSubSeccionCollectionNewSeccionSubSeccionToAttach = em.getReference(seccionSubSeccionCollectionNewSeccionSubSeccionToAttach.getClass(), seccionSubSeccionCollectionNewSeccionSubSeccionToAttach.getId());
                attachedSeccionSubSeccionCollectionNew.add(seccionSubSeccionCollectionNewSeccionSubSeccionToAttach);
            }
            seccionSubSeccionCollectionNew = attachedSeccionSubSeccionCollectionNew;
            seccionSubSeccion.setSeccionSubSeccionCollection(seccionSubSeccionCollectionNew);
            seccionSubSeccion = em.merge(seccionSubSeccion);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getSeccionSubSeccionCollection().remove(seccionSubSeccion);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getSeccionSubSeccionCollection().add(seccionSubSeccion);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getSeccionSubSeccionCollection().remove(seccionSubSeccion);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getSeccionSubSeccionCollection().add(seccionSubSeccion);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            if (dependeDeOld != null && !dependeDeOld.equals(dependeDeNew)) {
                dependeDeOld.getSeccionSubSeccionCollection().remove(seccionSubSeccion);
                dependeDeOld = em.merge(dependeDeOld);
            }
            if (dependeDeNew != null && !dependeDeNew.equals(dependeDeOld)) {
                dependeDeNew.getSeccionSubSeccionCollection().add(seccionSubSeccion);
                dependeDeNew = em.merge(dependeDeNew);
            }
            for (SeccionSubSeccion seccionSubSeccionCollectionOldSeccionSubSeccion : seccionSubSeccionCollectionOld) {
                if (!seccionSubSeccionCollectionNew.contains(seccionSubSeccionCollectionOldSeccionSubSeccion)) {
                    seccionSubSeccionCollectionOldSeccionSubSeccion.setDependeDe(null);
                    seccionSubSeccionCollectionOldSeccionSubSeccion = em.merge(seccionSubSeccionCollectionOldSeccionSubSeccion);
                }
            }
            for (SeccionSubSeccion seccionSubSeccionCollectionNewSeccionSubSeccion : seccionSubSeccionCollectionNew) {
                if (!seccionSubSeccionCollectionOld.contains(seccionSubSeccionCollectionNewSeccionSubSeccion)) {
                    SeccionSubSeccion oldDependeDeOfSeccionSubSeccionCollectionNewSeccionSubSeccion = seccionSubSeccionCollectionNewSeccionSubSeccion.getDependeDe();
                    seccionSubSeccionCollectionNewSeccionSubSeccion.setDependeDe(seccionSubSeccion);
                    seccionSubSeccionCollectionNewSeccionSubSeccion = em.merge(seccionSubSeccionCollectionNewSeccionSubSeccion);
                    if (oldDependeDeOfSeccionSubSeccionCollectionNewSeccionSubSeccion != null && !oldDependeDeOfSeccionSubSeccionCollectionNewSeccionSubSeccion.equals(seccionSubSeccion)) {
                        oldDependeDeOfSeccionSubSeccionCollectionNewSeccionSubSeccion.getSeccionSubSeccionCollection().remove(seccionSubSeccionCollectionNewSeccionSubSeccion);
                        oldDependeDeOfSeccionSubSeccionCollectionNewSeccionSubSeccion = em.merge(oldDependeDeOfSeccionSubSeccionCollectionNewSeccionSubSeccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = seccionSubSeccion.getId();
                if (findSeccionSubSeccion(id) == null) {
                    throw new NonexistentEntityException("The seccionSubSeccion with id " + id + " no longer exists.");
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
            SeccionSubSeccion seccionSubSeccion;
            try {
                seccionSubSeccion = em.getReference(SeccionSubSeccion.class, id);
                seccionSubSeccion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seccionSubSeccion with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = seccionSubSeccion.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getSeccionSubSeccionCollection().remove(seccionSubSeccion);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = seccionSubSeccion.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getSeccionSubSeccionCollection().remove(seccionSubSeccion);
                modificadoPor = em.merge(modificadoPor);
            }
            SeccionSubSeccion dependeDe = seccionSubSeccion.getDependeDe();
            if (dependeDe != null) {
                dependeDe.getSeccionSubSeccionCollection().remove(seccionSubSeccion);
                dependeDe = em.merge(dependeDe);
            }
            Collection<SeccionSubSeccion> seccionSubSeccionCollection = seccionSubSeccion.getSeccionSubSeccionCollection();
            for (SeccionSubSeccion seccionSubSeccionCollectionSeccionSubSeccion : seccionSubSeccionCollection) {
                seccionSubSeccionCollectionSeccionSubSeccion.setDependeDe(null);
                seccionSubSeccionCollectionSeccionSubSeccion = em.merge(seccionSubSeccionCollectionSeccionSubSeccion);
            }
            em.remove(seccionSubSeccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeccionSubSeccion> findSeccionSubSeccionEntities() {
        return findSeccionSubSeccionEntities(true, -1, -1);
    }

    public List<SeccionSubSeccion> findSeccionSubSeccionEntities(int maxResults, int firstResult) {
        return findSeccionSubSeccionEntities(false, maxResults, firstResult);
    }

    private List<SeccionSubSeccion> findSeccionSubSeccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeccionSubSeccion.class));
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

    public SeccionSubSeccion findSeccionSubSeccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeccionSubSeccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeccionSubSeccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeccionSubSeccion> rt = cq.from(SeccionSubSeccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
