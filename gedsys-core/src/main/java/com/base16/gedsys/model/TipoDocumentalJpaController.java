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
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.UnidadDocumental;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.TipoDocumental;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class TipoDocumentalJpaController implements Serializable {

    public TipoDocumentalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoDocumental tipoDocumental) {
        if (tipoDocumental.getDocumentoCollection() == null) {
            tipoDocumental.setDocumentoCollection(new ArrayList<Documento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = tipoDocumental.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                tipoDocumental.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = tipoDocumental.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                tipoDocumental.setModificadoPor(modificadoPor);
            }
            UnidadDocumental unidadDocumental = tipoDocumental.getUnidadDocumental();
            if (unidadDocumental != null) {
                unidadDocumental = em.getReference(unidadDocumental.getClass(), unidadDocumental.getId());
                tipoDocumental.setUnidadDocumental(unidadDocumental);
            }
            Collection<Documento> attachedDocumentoCollection = new ArrayList<Documento>();
            for (Documento documentoCollectionDocumentoToAttach : tipoDocumental.getDocumentoCollection()) {
                documentoCollectionDocumentoToAttach = em.getReference(documentoCollectionDocumentoToAttach.getClass(), documentoCollectionDocumentoToAttach.getId());
                attachedDocumentoCollection.add(documentoCollectionDocumentoToAttach);
            }
            tipoDocumental.setDocumentoCollection(attachedDocumentoCollection);
            em.persist(tipoDocumental);
            if (creadoPor != null) {
                creadoPor.getTipoDocumentalCollection().add(tipoDocumental);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getTipoDocumentalCollection().add(tipoDocumental);
                modificadoPor = em.merge(modificadoPor);
            }
            if (unidadDocumental != null) {
                unidadDocumental.getTipoDocumentalCollection().add(tipoDocumental);
                unidadDocumental = em.merge(unidadDocumental);
            }
            for (Documento documentoCollectionDocumento : tipoDocumental.getDocumentoCollection()) {
                TipoDocumental oldTipoDocumentalOfDocumentoCollectionDocumento = documentoCollectionDocumento.getTipoDocumental();
                documentoCollectionDocumento.setTipoDocumental(tipoDocumental);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
                if (oldTipoDocumentalOfDocumentoCollectionDocumento != null) {
                    oldTipoDocumentalOfDocumentoCollectionDocumento.getDocumentoCollection().remove(documentoCollectionDocumento);
                    oldTipoDocumentalOfDocumentoCollectionDocumento = em.merge(oldTipoDocumentalOfDocumentoCollectionDocumento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoDocumental tipoDocumental) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDocumental persistentTipoDocumental = em.find(TipoDocumental.class, tipoDocumental.getId());
            Usuario creadoPorOld = persistentTipoDocumental.getCreadoPor();
            Usuario creadoPorNew = tipoDocumental.getCreadoPor();
            Usuario modificadoPorOld = persistentTipoDocumental.getModificadoPor();
            Usuario modificadoPorNew = tipoDocumental.getModificadoPor();
            UnidadDocumental unidadDocumentalOld = persistentTipoDocumental.getUnidadDocumental();
            UnidadDocumental unidadDocumentalNew = tipoDocumental.getUnidadDocumental();
            Collection<Documento> documentoCollectionOld = persistentTipoDocumental.getDocumentoCollection();
            Collection<Documento> documentoCollectionNew = tipoDocumental.getDocumentoCollection();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                tipoDocumental.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                tipoDocumental.setModificadoPor(modificadoPorNew);
            }
            if (unidadDocumentalNew != null) {
                unidadDocumentalNew = em.getReference(unidadDocumentalNew.getClass(), unidadDocumentalNew.getId());
                tipoDocumental.setUnidadDocumental(unidadDocumentalNew);
            }
            Collection<Documento> attachedDocumentoCollectionNew = new ArrayList<Documento>();
            for (Documento documentoCollectionNewDocumentoToAttach : documentoCollectionNew) {
                documentoCollectionNewDocumentoToAttach = em.getReference(documentoCollectionNewDocumentoToAttach.getClass(), documentoCollectionNewDocumentoToAttach.getId());
                attachedDocumentoCollectionNew.add(documentoCollectionNewDocumentoToAttach);
            }
            documentoCollectionNew = attachedDocumentoCollectionNew;
            tipoDocumental.setDocumentoCollection(documentoCollectionNew);
            tipoDocumental = em.merge(tipoDocumental);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getTipoDocumentalCollection().remove(tipoDocumental);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getTipoDocumentalCollection().add(tipoDocumental);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getTipoDocumentalCollection().remove(tipoDocumental);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getTipoDocumentalCollection().add(tipoDocumental);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            if (unidadDocumentalOld != null && !unidadDocumentalOld.equals(unidadDocumentalNew)) {
                unidadDocumentalOld.getTipoDocumentalCollection().remove(tipoDocumental);
                unidadDocumentalOld = em.merge(unidadDocumentalOld);
            }
            if (unidadDocumentalNew != null && !unidadDocumentalNew.equals(unidadDocumentalOld)) {
                unidadDocumentalNew.getTipoDocumentalCollection().add(tipoDocumental);
                unidadDocumentalNew = em.merge(unidadDocumentalNew);
            }
            for (Documento documentoCollectionOldDocumento : documentoCollectionOld) {
                if (!documentoCollectionNew.contains(documentoCollectionOldDocumento)) {
                    documentoCollectionOldDocumento.setTipoDocumental(null);
                    documentoCollectionOldDocumento = em.merge(documentoCollectionOldDocumento);
                }
            }
            for (Documento documentoCollectionNewDocumento : documentoCollectionNew) {
                if (!documentoCollectionOld.contains(documentoCollectionNewDocumento)) {
                    TipoDocumental oldTipoDocumentalOfDocumentoCollectionNewDocumento = documentoCollectionNewDocumento.getTipoDocumental();
                    documentoCollectionNewDocumento.setTipoDocumental(tipoDocumental);
                    documentoCollectionNewDocumento = em.merge(documentoCollectionNewDocumento);
                    if (oldTipoDocumentalOfDocumentoCollectionNewDocumento != null && !oldTipoDocumentalOfDocumentoCollectionNewDocumento.equals(tipoDocumental)) {
                        oldTipoDocumentalOfDocumentoCollectionNewDocumento.getDocumentoCollection().remove(documentoCollectionNewDocumento);
                        oldTipoDocumentalOfDocumentoCollectionNewDocumento = em.merge(oldTipoDocumentalOfDocumentoCollectionNewDocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoDocumental.getId();
                if (findTipoDocumental(id) == null) {
                    throw new NonexistentEntityException("The tipoDocumental with id " + id + " no longer exists.");
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
            TipoDocumental tipoDocumental;
            try {
                tipoDocumental = em.getReference(TipoDocumental.class, id);
                tipoDocumental.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoDocumental with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = tipoDocumental.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getTipoDocumentalCollection().remove(tipoDocumental);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = tipoDocumental.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getTipoDocumentalCollection().remove(tipoDocumental);
                modificadoPor = em.merge(modificadoPor);
            }
            UnidadDocumental unidadDocumental = tipoDocumental.getUnidadDocumental();
            if (unidadDocumental != null) {
                unidadDocumental.getTipoDocumentalCollection().remove(tipoDocumental);
                unidadDocumental = em.merge(unidadDocumental);
            }
            Collection<Documento> documentoCollection = tipoDocumental.getDocumentoCollection();
            for (Documento documentoCollectionDocumento : documentoCollection) {
                documentoCollectionDocumento.setTipoDocumental(null);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
            }
            em.remove(tipoDocumental);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoDocumental> findTipoDocumentalEntities() {
        return findTipoDocumentalEntities(true, -1, -1);
    }

    public List<TipoDocumental> findTipoDocumentalEntities(int maxResults, int firstResult) {
        return findTipoDocumentalEntities(false, maxResults, firstResult);
    }

    private List<TipoDocumental> findTipoDocumentalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoDocumental.class));
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

    public TipoDocumental findTipoDocumental(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoDocumental.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoDocumentalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoDocumental> rt = cq.from(TipoDocumental.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<TipoDocumental> findUnidadDocumentalBySubSerie(UnidadDocumental unidadDocumental) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoDocumental.class));
            Query q = em.createNamedQuery("TipoDocumental.findByUnidadDocumental", TipoDocumental.class)
                    .setParameter("unidadDocumental", unidadDocumental);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
