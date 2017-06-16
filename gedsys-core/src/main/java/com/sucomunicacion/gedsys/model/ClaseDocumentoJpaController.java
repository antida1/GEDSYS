/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.model;

import com.sucomunicacion.gedsys.entities.ClaseDocumento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.entities.Documento;
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
public class ClaseDocumentoJpaController implements Serializable {

    public ClaseDocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ClaseDocumento claseDocumento) {
        if (claseDocumento.getDocumentoCollection() == null) {
            claseDocumento.setDocumentoCollection(new ArrayList<Documento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = claseDocumento.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                claseDocumento.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = claseDocumento.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                claseDocumento.setModificadoPor(modificadoPor);
            }
            Collection<Documento> attachedDocumentoCollection = new ArrayList<Documento>();
            for (Documento documentoCollectionDocumentoToAttach : claseDocumento.getDocumentoCollection()) {
                documentoCollectionDocumentoToAttach = em.getReference(documentoCollectionDocumentoToAttach.getClass(), documentoCollectionDocumentoToAttach.getId());
                attachedDocumentoCollection.add(documentoCollectionDocumentoToAttach);
            }
            claseDocumento.setDocumentoCollection(attachedDocumentoCollection);
            em.persist(claseDocumento);
            if (creadoPor != null) {
                creadoPor.getClaseDocumentoCollection().add(claseDocumento);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getClaseDocumentoCollection().add(claseDocumento);
                modificadoPor = em.merge(modificadoPor);
            }
            for (Documento documentoCollectionDocumento : claseDocumento.getDocumentoCollection()) {
                ClaseDocumento oldClaseDocumentoOfDocumentoCollectionDocumento = documentoCollectionDocumento.getClaseDocumento();
                documentoCollectionDocumento.setClaseDocumento(claseDocumento);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
                if (oldClaseDocumentoOfDocumentoCollectionDocumento != null) {
                    oldClaseDocumentoOfDocumentoCollectionDocumento.getDocumentoCollection().remove(documentoCollectionDocumento);
                    oldClaseDocumentoOfDocumentoCollectionDocumento = em.merge(oldClaseDocumentoOfDocumentoCollectionDocumento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ClaseDocumento claseDocumento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ClaseDocumento persistentClaseDocumento = em.find(ClaseDocumento.class, claseDocumento.getId());
            Usuario creadoPorOld = persistentClaseDocumento.getCreadoPor();
            Usuario creadoPorNew = claseDocumento.getCreadoPor();
            Usuario modificadoPorOld = persistentClaseDocumento.getModificadoPor();
            Usuario modificadoPorNew = claseDocumento.getModificadoPor();
            Collection<Documento> documentoCollectionOld = persistentClaseDocumento.getDocumentoCollection();
            Collection<Documento> documentoCollectionNew = claseDocumento.getDocumentoCollection();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                claseDocumento.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                claseDocumento.setModificadoPor(modificadoPorNew);
            }
            Collection<Documento> attachedDocumentoCollectionNew = new ArrayList<Documento>();
            for (Documento documentoCollectionNewDocumentoToAttach : documentoCollectionNew) {
                documentoCollectionNewDocumentoToAttach = em.getReference(documentoCollectionNewDocumentoToAttach.getClass(), documentoCollectionNewDocumentoToAttach.getId());
                attachedDocumentoCollectionNew.add(documentoCollectionNewDocumentoToAttach);
            }
            documentoCollectionNew = attachedDocumentoCollectionNew;
            claseDocumento.setDocumentoCollection(documentoCollectionNew);
            claseDocumento = em.merge(claseDocumento);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getClaseDocumentoCollection().remove(claseDocumento);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getClaseDocumentoCollection().add(claseDocumento);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getClaseDocumentoCollection().remove(claseDocumento);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getClaseDocumentoCollection().add(claseDocumento);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            for (Documento documentoCollectionOldDocumento : documentoCollectionOld) {
                if (!documentoCollectionNew.contains(documentoCollectionOldDocumento)) {
                    documentoCollectionOldDocumento.setClaseDocumento(null);
                    documentoCollectionOldDocumento = em.merge(documentoCollectionOldDocumento);
                }
            }
            for (Documento documentoCollectionNewDocumento : documentoCollectionNew) {
                if (!documentoCollectionOld.contains(documentoCollectionNewDocumento)) {
                    ClaseDocumento oldClaseDocumentoOfDocumentoCollectionNewDocumento = documentoCollectionNewDocumento.getClaseDocumento();
                    documentoCollectionNewDocumento.setClaseDocumento(claseDocumento);
                    documentoCollectionNewDocumento = em.merge(documentoCollectionNewDocumento);
                    if (oldClaseDocumentoOfDocumentoCollectionNewDocumento != null && !oldClaseDocumentoOfDocumentoCollectionNewDocumento.equals(claseDocumento)) {
                        oldClaseDocumentoOfDocumentoCollectionNewDocumento.getDocumentoCollection().remove(documentoCollectionNewDocumento);
                        oldClaseDocumentoOfDocumentoCollectionNewDocumento = em.merge(oldClaseDocumentoOfDocumentoCollectionNewDocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = claseDocumento.getId();
                if (findClaseDocumento(id) == null) {
                    throw new NonexistentEntityException("The claseDocumento with id " + id + " no longer exists.");
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
            ClaseDocumento claseDocumento;
            try {
                claseDocumento = em.getReference(ClaseDocumento.class, id);
                claseDocumento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The claseDocumento with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = claseDocumento.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getClaseDocumentoCollection().remove(claseDocumento);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = claseDocumento.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getClaseDocumentoCollection().remove(claseDocumento);
                modificadoPor = em.merge(modificadoPor);
            }
            Collection<Documento> documentoCollection = claseDocumento.getDocumentoCollection();
            for (Documento documentoCollectionDocumento : documentoCollection) {
                documentoCollectionDocumento.setClaseDocumento(null);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
            }
            em.remove(claseDocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ClaseDocumento> findClaseDocumentoEntities() {
        return findClaseDocumentoEntities(true, -1, -1);
    }

    public List<ClaseDocumento> findClaseDocumentoEntities(int maxResults, int firstResult) {
        return findClaseDocumentoEntities(false, maxResults, firstResult);
    }

    private List<ClaseDocumento> findClaseDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ClaseDocumento.class));
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

    public ClaseDocumento findClaseDocumento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ClaseDocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getClaseDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ClaseDocumento> rt = cq.from(ClaseDocumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
