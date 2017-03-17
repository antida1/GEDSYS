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
import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.ProcesoDocumental;
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
public class ProcesoDocumentalJpaController implements Serializable {

    public ProcesoDocumentalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProcesoDocumental procesoDocumental) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Documento documento = procesoDocumental.getDocumento();
            if (documento != null) {
                documento = em.getReference(documento.getClass(), documento.getId());
                procesoDocumental.setDocumento(documento);
            }
            ProcesoNegocio proceso = procesoDocumental.getProceso();
            if (proceso != null) {
                proceso = em.getReference(proceso.getClass(), proceso.getId());
                procesoDocumental.setProceso(proceso);
            }
            Usuario creadoPor = procesoDocumental.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                procesoDocumental.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = procesoDocumental.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                procesoDocumental.setModificadoPor(modificadoPor);
            }
            em.persist(procesoDocumental);
            if (documento != null) {
                documento.getProcesoDocumentalCollection().add(procesoDocumental);
                documento = em.merge(documento);
            }
            if (proceso != null) {
                proceso.getProcesoDocumentalCollection().add(procesoDocumental);
                proceso = em.merge(proceso);
            }
            if (creadoPor != null) {
                creadoPor.getProcesoDocumentalCollection().add(procesoDocumental);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getProcesoDocumentalCollection().add(procesoDocumental);
                modificadoPor = em.merge(modificadoPor);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProcesoDocumental(procesoDocumental.getId()) != null) {
                throw new PreexistingEntityException("ProcesoDocumental " + procesoDocumental + " already exists.", ex);
            }
            throw ex;
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
            Documento documentoOld = persistentProcesoDocumental.getDocumento();
            Documento documentoNew = procesoDocumental.getDocumento();
            ProcesoNegocio procesoOld = persistentProcesoDocumental.getProceso();
            ProcesoNegocio procesoNew = procesoDocumental.getProceso();
            Usuario creadoPorOld = persistentProcesoDocumental.getCreadoPor();
            Usuario creadoPorNew = procesoDocumental.getCreadoPor();
            Usuario modificadoPorOld = persistentProcesoDocumental.getModificadoPor();
            Usuario modificadoPorNew = procesoDocumental.getModificadoPor();
            if (documentoNew != null) {
                documentoNew = em.getReference(documentoNew.getClass(), documentoNew.getId());
                procesoDocumental.setDocumento(documentoNew);
            }
            if (procesoNew != null) {
                procesoNew = em.getReference(procesoNew.getClass(), procesoNew.getId());
                procesoDocumental.setProceso(procesoNew);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                procesoDocumental.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                procesoDocumental.setModificadoPor(modificadoPorNew);
            }
            procesoDocumental = em.merge(procesoDocumental);
            if (documentoOld != null && !documentoOld.equals(documentoNew)) {
                documentoOld.getProcesoDocumentalCollection().remove(procesoDocumental);
                documentoOld = em.merge(documentoOld);
            }
            if (documentoNew != null && !documentoNew.equals(documentoOld)) {
                documentoNew.getProcesoDocumentalCollection().add(procesoDocumental);
                documentoNew = em.merge(documentoNew);
            }
            if (procesoOld != null && !procesoOld.equals(procesoNew)) {
                procesoOld.getProcesoDocumentalCollection().remove(procesoDocumental);
                procesoOld = em.merge(procesoOld);
            }
            if (procesoNew != null && !procesoNew.equals(procesoOld)) {
                procesoNew.getProcesoDocumentalCollection().add(procesoDocumental);
                procesoNew = em.merge(procesoNew);
            }
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getProcesoDocumentalCollection().remove(procesoDocumental);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getProcesoDocumentalCollection().add(procesoDocumental);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getProcesoDocumentalCollection().remove(procesoDocumental);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getProcesoDocumentalCollection().add(procesoDocumental);
                modificadoPorNew = em.merge(modificadoPorNew);
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
            Documento documento = procesoDocumental.getDocumento();
            if (documento != null) {
                documento.getProcesoDocumentalCollection().remove(procesoDocumental);
                documento = em.merge(documento);
            }
            ProcesoNegocio proceso = procesoDocumental.getProceso();
            if (proceso != null) {
                proceso.getProcesoDocumentalCollection().remove(procesoDocumental);
                proceso = em.merge(proceso);
            }
            Usuario creadoPor = procesoDocumental.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getProcesoDocumentalCollection().remove(procesoDocumental);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = procesoDocumental.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getProcesoDocumentalCollection().remove(procesoDocumental);
                modificadoPor = em.merge(modificadoPor);
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
