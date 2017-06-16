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
import com.sucomunicacion.gedsys.entities.ProcesoNegocio;
import com.sucomunicacion.gedsys.entities.ProcesoTipoDocumento;
import com.sucomunicacion.gedsys.entities.TipoDocumento;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class ProcesoTipoDocumentoJpaController implements Serializable {

    public ProcesoTipoDocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProcesoTipoDocumento procesoTipoDocumento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = procesoTipoDocumento.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                procesoTipoDocumento.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = procesoTipoDocumento.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                procesoTipoDocumento.setModificadoPor(modificadoPor);
            }
            ProcesoNegocio proceso = procesoTipoDocumento.getProceso();
            if (proceso != null) {
                proceso = em.getReference(proceso.getClass(), proceso.getId());
                procesoTipoDocumento.setProceso(proceso);
            }
            TipoDocumento tipoDocumento = procesoTipoDocumento.getTipoDocumento();
            if (tipoDocumento != null) {
                tipoDocumento = em.getReference(tipoDocumento.getClass(), tipoDocumento.getId());
                procesoTipoDocumento.setTipoDocumento(tipoDocumento);
            }
            em.persist(procesoTipoDocumento);
            if (creadoPor != null) {
                creadoPor.getProcesoTipoDocumentoCollection().add(procesoTipoDocumento);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getProcesoTipoDocumentoCollection().add(procesoTipoDocumento);
                modificadoPor = em.merge(modificadoPor);
            }
            if (proceso != null) {
                proceso.getProcesoTipoDocumentoCollection().add(procesoTipoDocumento);
                proceso = em.merge(proceso);
            }
            if (tipoDocumento != null) {
                tipoDocumento.getProcesoTipoDocumentoCollection().add(procesoTipoDocumento);
                tipoDocumento = em.merge(tipoDocumento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProcesoTipoDocumento procesoTipoDocumento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProcesoTipoDocumento persistentProcesoTipoDocumento = em.find(ProcesoTipoDocumento.class, procesoTipoDocumento.getId());
            Usuario creadoPorOld = persistentProcesoTipoDocumento.getCreadoPor();
            Usuario creadoPorNew = procesoTipoDocumento.getCreadoPor();
            Usuario modificadoPorOld = persistentProcesoTipoDocumento.getModificadoPor();
            Usuario modificadoPorNew = procesoTipoDocumento.getModificadoPor();
            ProcesoNegocio procesoOld = persistentProcesoTipoDocumento.getProceso();
            ProcesoNegocio procesoNew = procesoTipoDocumento.getProceso();
            TipoDocumento tipoDocumentoOld = persistentProcesoTipoDocumento.getTipoDocumento();
            TipoDocumento tipoDocumentoNew = procesoTipoDocumento.getTipoDocumento();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                procesoTipoDocumento.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                procesoTipoDocumento.setModificadoPor(modificadoPorNew);
            }
            if (procesoNew != null) {
                procesoNew = em.getReference(procesoNew.getClass(), procesoNew.getId());
                procesoTipoDocumento.setProceso(procesoNew);
            }
            if (tipoDocumentoNew != null) {
                tipoDocumentoNew = em.getReference(tipoDocumentoNew.getClass(), tipoDocumentoNew.getId());
                procesoTipoDocumento.setTipoDocumento(tipoDocumentoNew);
            }
            procesoTipoDocumento = em.merge(procesoTipoDocumento);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getProcesoTipoDocumentoCollection().remove(procesoTipoDocumento);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getProcesoTipoDocumentoCollection().add(procesoTipoDocumento);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getProcesoTipoDocumentoCollection().remove(procesoTipoDocumento);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getProcesoTipoDocumentoCollection().add(procesoTipoDocumento);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            if (procesoOld != null && !procesoOld.equals(procesoNew)) {
                procesoOld.getProcesoTipoDocumentoCollection().remove(procesoTipoDocumento);
                procesoOld = em.merge(procesoOld);
            }
            if (procesoNew != null && !procesoNew.equals(procesoOld)) {
                procesoNew.getProcesoTipoDocumentoCollection().add(procesoTipoDocumento);
                procesoNew = em.merge(procesoNew);
            }
            if (tipoDocumentoOld != null && !tipoDocumentoOld.equals(tipoDocumentoNew)) {
                tipoDocumentoOld.getProcesoTipoDocumentoCollection().remove(procesoTipoDocumento);
                tipoDocumentoOld = em.merge(tipoDocumentoOld);
            }
            if (tipoDocumentoNew != null && !tipoDocumentoNew.equals(tipoDocumentoOld)) {
                tipoDocumentoNew.getProcesoTipoDocumentoCollection().add(procesoTipoDocumento);
                tipoDocumentoNew = em.merge(tipoDocumentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = procesoTipoDocumento.getId();
                if (findProcesoTipoDocumento(id) == null) {
                    throw new NonexistentEntityException("The procesoTipoDocumento with id " + id + " no longer exists.");
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
            ProcesoTipoDocumento procesoTipoDocumento;
            try {
                procesoTipoDocumento = em.getReference(ProcesoTipoDocumento.class, id);
                procesoTipoDocumento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procesoTipoDocumento with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = procesoTipoDocumento.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getProcesoTipoDocumentoCollection().remove(procesoTipoDocumento);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = procesoTipoDocumento.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getProcesoTipoDocumentoCollection().remove(procesoTipoDocumento);
                modificadoPor = em.merge(modificadoPor);
            }
            ProcesoNegocio proceso = procesoTipoDocumento.getProceso();
            if (proceso != null) {
                proceso.getProcesoTipoDocumentoCollection().remove(procesoTipoDocumento);
                proceso = em.merge(proceso);
            }
            TipoDocumento tipoDocumento = procesoTipoDocumento.getTipoDocumento();
            if (tipoDocumento != null) {
                tipoDocumento.getProcesoTipoDocumentoCollection().remove(procesoTipoDocumento);
                tipoDocumento = em.merge(tipoDocumento);
            }
            em.remove(procesoTipoDocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProcesoTipoDocumento> findProcesoTipoDocumentoEntities() {
        return findProcesoTipoDocumentoEntities(true, -1, -1);
    }

    public List<ProcesoTipoDocumento> findProcesoTipoDocumentoEntities(int maxResults, int firstResult) {
        return findProcesoTipoDocumentoEntities(false, maxResults, firstResult);
    }

    private List<ProcesoTipoDocumento> findProcesoTipoDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProcesoTipoDocumento.class));
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

    public ProcesoTipoDocumento findProcesoTipoDocumento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProcesoTipoDocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesoTipoDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProcesoTipoDocumento> rt = cq.from(ProcesoTipoDocumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
