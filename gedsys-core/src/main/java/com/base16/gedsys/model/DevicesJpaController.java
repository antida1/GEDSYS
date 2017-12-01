/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.model;

import com.base16.gedsys.entities.Devices;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
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
 * @author rober
 */
public class DevicesJpaController implements Serializable {

    public DevicesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Devices devices) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(devices);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Devices devices) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            devices = em.merge(devices);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = devices.getId();
                if (findDevices(id) == null) {
                    throw new NonexistentEntityException("The devices with id " + id + " no longer exists.");
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
            Devices devices;
            try {
                devices = em.getReference(Devices.class, id);
                devices.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The devices with id " + id + " no longer exists.", enfe);
            }
            em.remove(devices);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Devices> findDevicesEntities() {
        return findDevicesEntities(true, -1, -1);
    }

    public List<Devices> findDevicesEntities(int maxResults, int firstResult) {
        return findDevicesEntities(false, maxResults, firstResult);
    }

    private List<Devices> findDevicesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Devices.class));
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

    public Devices findDevices(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Devices.class, id);
        } finally {
            em.close();
        }
    }

    public int getDevicesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Devices> rt = cq.from(Devices.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public List<Devices> findDevicesByUsuario(Usuario usuario) {
        return findDevicesByUsuario(usuario, -1, -1);
}

    public List<Devices> findDevicesByUsuario(Usuario usuario, int maxResults, int firstResult) {
        return findDevicesByUsuario(usuario, true, maxResults, firstResult);
    }
    
    private List<Devices> findDevicesByUsuario( Usuario usuario ,boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Devices.class));
            Query q = em.createNamedQuery("Devices.findByUsuario", Devices.class)
                    .setParameter("usuario", usuario);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
