/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.model;

import com.sucomunicacion.gedsys.entities.MonitoresProceso;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sucomunicacion.gedsys.entities.ProcesoNegocio;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class MonitoresProcesoJpaController implements Serializable {

    public MonitoresProcesoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MonitoresProceso monitoresProceso) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProcesoNegocio proceso = monitoresProceso.getProceso();
            if (proceso != null) {
                proceso = em.getReference(proceso.getClass(), proceso.getId());
                monitoresProceso.setProceso(proceso);
            }
            Usuario creadoPor = monitoresProceso.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                monitoresProceso.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = monitoresProceso.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                monitoresProceso.setModificadoPor(modificadoPor);
            }
            em.persist(monitoresProceso);
            if (proceso != null) {
                proceso.getMonitoresProcesoCollection().add(monitoresProceso);
                proceso = em.merge(proceso);
            }
            if (creadoPor != null) {
                creadoPor.getMonitoresProcesoCollection().add(monitoresProceso);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getMonitoresProcesoCollection().add(monitoresProceso);
                modificadoPor = em.merge(modificadoPor);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMonitoresProceso(monitoresProceso.getId()) != null) {
                throw new PreexistingEntityException("MonitoresProceso " + monitoresProceso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MonitoresProceso monitoresProceso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MonitoresProceso persistentMonitoresProceso = em.find(MonitoresProceso.class, monitoresProceso.getId());
            ProcesoNegocio procesoOld = persistentMonitoresProceso.getProceso();
            ProcesoNegocio procesoNew = monitoresProceso.getProceso();
            Usuario creadoPorOld = persistentMonitoresProceso.getCreadoPor();
            Usuario creadoPorNew = monitoresProceso.getCreadoPor();
            Usuario modificadoPorOld = persistentMonitoresProceso.getModificadoPor();
            Usuario modificadoPorNew = monitoresProceso.getModificadoPor();
            if (procesoNew != null) {
                procesoNew = em.getReference(procesoNew.getClass(), procesoNew.getId());
                monitoresProceso.setProceso(procesoNew);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                monitoresProceso.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                monitoresProceso.setModificadoPor(modificadoPorNew);
            }
            monitoresProceso = em.merge(monitoresProceso);
            if (procesoOld != null && !procesoOld.equals(procesoNew)) {
                procesoOld.getMonitoresProcesoCollection().remove(monitoresProceso);
                procesoOld = em.merge(procesoOld);
            }
            if (procesoNew != null && !procesoNew.equals(procesoOld)) {
                procesoNew.getMonitoresProcesoCollection().add(monitoresProceso);
                procesoNew = em.merge(procesoNew);
            }
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getMonitoresProcesoCollection().remove(monitoresProceso);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getMonitoresProcesoCollection().add(monitoresProceso);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getMonitoresProcesoCollection().remove(monitoresProceso);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getMonitoresProcesoCollection().add(monitoresProceso);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = monitoresProceso.getId();
                if (findMonitoresProceso(id) == null) {
                    throw new NonexistentEntityException("The monitoresProceso with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MonitoresProceso monitoresProceso;
            try {
                monitoresProceso = em.getReference(MonitoresProceso.class, id);
                monitoresProceso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The monitoresProceso with id " + id + " no longer exists.", enfe);
            }
            ProcesoNegocio proceso = monitoresProceso.getProceso();
            if (proceso != null) {
                proceso.getMonitoresProcesoCollection().remove(monitoresProceso);
                proceso = em.merge(proceso);
            }
            Usuario creadoPor = monitoresProceso.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getMonitoresProcesoCollection().remove(monitoresProceso);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = monitoresProceso.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getMonitoresProcesoCollection().remove(monitoresProceso);
                modificadoPor = em.merge(modificadoPor);
            }
            em.remove(monitoresProceso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MonitoresProceso> findMonitoresProcesoEntities() {
        return findMonitoresProcesoEntities(true, -1, -1);
    }

    public List<MonitoresProceso> findMonitoresProcesoEntities(int maxResults, int firstResult) {
        return findMonitoresProcesoEntities(false, maxResults, firstResult);
    }

    private List<MonitoresProceso> findMonitoresProcesoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MonitoresProceso.class));
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

    public MonitoresProceso findMonitoresProceso(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MonitoresProceso.class, id);
        } finally {
            em.close();
        }
    }

    public int getMonitoresProcesoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MonitoresProceso> rt = cq.from(MonitoresProceso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
