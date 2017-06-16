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
import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.ProcesoDocumental;
import com.sucomunicacion.gedsys.entities.ProcesoNegocio;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class ProcesoDocumentalJpaController implements Serializable {

    public ProcesoDocumentalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProcesoDocumental procesoDocumental) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = procesoDocumental.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                procesoDocumental.setCreadoPor(creadoPor);
            }
            Documento documento = procesoDocumental.getDocumento();
            if (documento != null) {
                documento = em.getReference(documento.getClass(), documento.getId());
                procesoDocumental.setDocumento(documento);
            }
            Usuario modificadoPor = procesoDocumental.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                procesoDocumental.setModificadoPor(modificadoPor);
            }
            ProcesoNegocio proceso = procesoDocumental.getProceso();
            if (proceso != null) {
                proceso = em.getReference(proceso.getClass(), proceso.getId());
                procesoDocumental.setProceso(proceso);
            }
            em.persist(procesoDocumental);
            if (creadoPor != null) {
                creadoPor.getProcesodocumentalCollection().add(procesoDocumental);
                creadoPor = em.merge(creadoPor);
            }
            if (documento != null) {
                documento.getProcesodocumentalCollection().add(procesoDocumental);
                documento = em.merge(documento);
            }
            if (modificadoPor != null) {
                modificadoPor.getProcesodocumentalCollection().add(procesoDocumental);
                modificadoPor = em.merge(modificadoPor);
            }
            if (proceso != null) {
                proceso.getProcesodocumentalCollection().add(procesoDocumental);
                proceso = em.merge(proceso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProcesoDocumental procesoDocumental) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProcesoDocumental persistentProcesoDocumental = em.find(ProcesoDocumental.class, procesoDocumental.getId());
            Usuario creadoPorOld = persistentProcesoDocumental.getCreadoPor();
            Usuario creadoPorNew = procesoDocumental.getCreadoPor();
            Documento documentoOld = persistentProcesoDocumental.getDocumento();
            Documento documentoNew = procesoDocumental.getDocumento();
            Usuario modificadoPorOld = persistentProcesoDocumental.getModificadoPor();
            Usuario modificadoPorNew = procesoDocumental.getModificadoPor();
            ProcesoNegocio procesoOld = persistentProcesoDocumental.getProceso();
            ProcesoNegocio procesoNew = procesoDocumental.getProceso();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                procesoDocumental.setCreadoPor(creadoPorNew);
            }
            if (documentoNew != null) {
                documentoNew = em.getReference(documentoNew.getClass(), documentoNew.getId());
                procesoDocumental.setDocumento(documentoNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                procesoDocumental.setModificadoPor(modificadoPorNew);
            }
            if (procesoNew != null) {
                procesoNew = em.getReference(procesoNew.getClass(), procesoNew.getId());
                procesoDocumental.setProceso(procesoNew);
            }
            procesoDocumental = em.merge(procesoDocumental);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getProcesodocumentalCollection().remove(procesoDocumental);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getProcesodocumentalCollection().add(procesoDocumental);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (documentoOld != null && !documentoOld.equals(documentoNew)) {
                documentoOld.getProcesodocumentalCollection().remove(procesoDocumental);
                documentoOld = em.merge(documentoOld);
            }
            if (documentoNew != null && !documentoNew.equals(documentoOld)) {
                documentoNew.getProcesodocumentalCollection().add(procesoDocumental);
                documentoNew = em.merge(documentoNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getProcesodocumentalCollection().remove(procesoDocumental);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getProcesodocumentalCollection().add(procesoDocumental);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            if (procesoOld != null && !procesoOld.equals(procesoNew)) {
                procesoOld.getProcesodocumentalCollection().remove(procesoDocumental);
                procesoOld = em.merge(procesoOld);
            }
            if (procesoNew != null && !procesoNew.equals(procesoOld)) {
                procesoNew.getProcesodocumentalCollection().add(procesoDocumental);
                procesoNew = em.merge(procesoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = procesoDocumental.getId();
                if (findProcesoDocumental(id) == null) {
                    throw new NonexistentEntityException("The procesoDocumental with id " + id + " no longer exists.");
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
            ProcesoDocumental procesoDocumental;
            try {
                procesoDocumental = em.getReference(ProcesoDocumental.class, id);
                procesoDocumental.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procesoDocumental with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = procesoDocumental.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getProcesodocumentalCollection().remove(procesoDocumental);
                creadoPor = em.merge(creadoPor);
            }
            Documento documento = procesoDocumental.getDocumento();
            if (documento != null) {
                documento.getProcesodocumentalCollection().remove(procesoDocumental);
                documento = em.merge(documento);
            }
            Usuario modificadoPor = procesoDocumental.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getProcesodocumentalCollection().remove(procesoDocumental);
                modificadoPor = em.merge(modificadoPor);
            }
            ProcesoNegocio proceso = procesoDocumental.getProceso();
            if (proceso != null) {
                proceso.getProcesodocumentalCollection().remove(procesoDocumental);
                proceso = em.merge(proceso);
            }
            em.remove(procesoDocumental);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProcesoDocumental> findProcesoDocumentalEntities() {
        return findProcesoDocumentalEntities(true, -1, -1);
    }

    public List<ProcesoDocumental> findProcesoDocumentalEntities(int maxResults, int firstResult) {
        return findProcesoDocumentalEntities(false, maxResults, firstResult);
    }

    private List<ProcesoDocumental> findProcesoDocumentalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProcesoDocumental.class));
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

    public ProcesoDocumental findProcesoDocumental(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProcesoDocumental.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesoDocumentalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProcesoDocumental> rt = cq.from(ProcesoDocumental.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
