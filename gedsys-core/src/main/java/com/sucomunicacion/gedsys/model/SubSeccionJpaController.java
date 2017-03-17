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
import com.sucomunicacion.gedsys.entities.Seccion;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.entities.Serie;
import java.util.ArrayList;
import java.util.Collection;
import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.SubSeccion;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class SubSeccionJpaController implements Serializable {

    public SubSeccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SubSeccion subSeccion) throws PreexistingEntityException, Exception {
        if (subSeccion.getSerieCollection() == null) {
            subSeccion.setSerieCollection(new ArrayList<Serie>());
        }
        if (subSeccion.getDocumentoCollection() == null) {
            subSeccion.setDocumentoCollection(new ArrayList<Documento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Seccion seccion = subSeccion.getSeccion();
            if (seccion != null) {
                seccion = em.getReference(seccion.getClass(), seccion.getId());
                subSeccion.setSeccion(seccion);
            }
            Usuario creadoPor = subSeccion.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                subSeccion.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = subSeccion.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                subSeccion.setModificadoPor(modificadoPor);
            }
            Collection<Serie> attachedSerieCollection = new ArrayList<Serie>();
            for (Serie serieCollectionSerieToAttach : subSeccion.getSerieCollection()) {
                serieCollectionSerieToAttach = em.getReference(serieCollectionSerieToAttach.getClass(), serieCollectionSerieToAttach.getId());
                attachedSerieCollection.add(serieCollectionSerieToAttach);
            }
            subSeccion.setSerieCollection(attachedSerieCollection);
            Collection<Documento> attachedDocumentoCollection = new ArrayList<Documento>();
            for (Documento documentoCollectionDocumentoToAttach : subSeccion.getDocumentoCollection()) {
                documentoCollectionDocumentoToAttach = em.getReference(documentoCollectionDocumentoToAttach.getClass(), documentoCollectionDocumentoToAttach.getId());
                attachedDocumentoCollection.add(documentoCollectionDocumentoToAttach);
            }
            subSeccion.setDocumentoCollection(attachedDocumentoCollection);
            em.persist(subSeccion);
            if (seccion != null) {
                seccion.getSubSeccionCollection().add(subSeccion);
                seccion = em.merge(seccion);
            }
            if (creadoPor != null) {
                creadoPor.getSubSeccionCollection().add(subSeccion);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getSubSeccionCollection().add(subSeccion);
                modificadoPor = em.merge(modificadoPor);
            }
            for (Serie serieCollectionSerie : subSeccion.getSerieCollection()) {
                SubSeccion oldSubSeccionOfSerieCollectionSerie = serieCollectionSerie.getSubSeccion();
                serieCollectionSerie.setSubSeccion(subSeccion);
                serieCollectionSerie = em.merge(serieCollectionSerie);
                if (oldSubSeccionOfSerieCollectionSerie != null) {
                    oldSubSeccionOfSerieCollectionSerie.getSerieCollection().remove(serieCollectionSerie);
                    oldSubSeccionOfSerieCollectionSerie = em.merge(oldSubSeccionOfSerieCollectionSerie);
                }
            }
            for (Documento documentoCollectionDocumento : subSeccion.getDocumentoCollection()) {
                SubSeccion oldSubSeccionOfDocumentoCollectionDocumento = documentoCollectionDocumento.getSubSeccion();
                documentoCollectionDocumento.setSubSeccion(subSeccion);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
                if (oldSubSeccionOfDocumentoCollectionDocumento != null) {
                    oldSubSeccionOfDocumentoCollectionDocumento.getDocumentoCollection().remove(documentoCollectionDocumento);
                    oldSubSeccionOfDocumentoCollectionDocumento = em.merge(oldSubSeccionOfDocumentoCollectionDocumento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSubSeccion(subSeccion.getId()) != null) {
                throw new PreexistingEntityException("SubSeccion " + subSeccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SubSeccion subSeccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SubSeccion persistentSubSeccion = em.find(SubSeccion.class, subSeccion.getId());
            Seccion seccionOld = persistentSubSeccion.getSeccion();
            Seccion seccionNew = subSeccion.getSeccion();
            Usuario creadoPorOld = persistentSubSeccion.getCreadoPor();
            Usuario creadoPorNew = subSeccion.getCreadoPor();
            Usuario modificadoPorOld = persistentSubSeccion.getModificadoPor();
            Usuario modificadoPorNew = subSeccion.getModificadoPor();
            Collection<Serie> serieCollectionOld = persistentSubSeccion.getSerieCollection();
            Collection<Serie> serieCollectionNew = subSeccion.getSerieCollection();
            Collection<Documento> documentoCollectionOld = persistentSubSeccion.getDocumentoCollection();
            Collection<Documento> documentoCollectionNew = subSeccion.getDocumentoCollection();
            if (seccionNew != null) {
                seccionNew = em.getReference(seccionNew.getClass(), seccionNew.getId());
                subSeccion.setSeccion(seccionNew);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                subSeccion.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                subSeccion.setModificadoPor(modificadoPorNew);
            }
            Collection<Serie> attachedSerieCollectionNew = new ArrayList<Serie>();
            for (Serie serieCollectionNewSerieToAttach : serieCollectionNew) {
                serieCollectionNewSerieToAttach = em.getReference(serieCollectionNewSerieToAttach.getClass(), serieCollectionNewSerieToAttach.getId());
                attachedSerieCollectionNew.add(serieCollectionNewSerieToAttach);
            }
            serieCollectionNew = attachedSerieCollectionNew;
            subSeccion.setSerieCollection(serieCollectionNew);
            Collection<Documento> attachedDocumentoCollectionNew = new ArrayList<Documento>();
            for (Documento documentoCollectionNewDocumentoToAttach : documentoCollectionNew) {
                documentoCollectionNewDocumentoToAttach = em.getReference(documentoCollectionNewDocumentoToAttach.getClass(), documentoCollectionNewDocumentoToAttach.getId());
                attachedDocumentoCollectionNew.add(documentoCollectionNewDocumentoToAttach);
            }
            documentoCollectionNew = attachedDocumentoCollectionNew;
            subSeccion.setDocumentoCollection(documentoCollectionNew);
            subSeccion = em.merge(subSeccion);
            if (seccionOld != null && !seccionOld.equals(seccionNew)) {
                seccionOld.getSubSeccionCollection().remove(subSeccion);
                seccionOld = em.merge(seccionOld);
            }
            if (seccionNew != null && !seccionNew.equals(seccionOld)) {
                seccionNew.getSubSeccionCollection().add(subSeccion);
                seccionNew = em.merge(seccionNew);
            }
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getSubSeccionCollection().remove(subSeccion);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getSubSeccionCollection().add(subSeccion);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getSubSeccionCollection().remove(subSeccion);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getSubSeccionCollection().add(subSeccion);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            for (Serie serieCollectionOldSerie : serieCollectionOld) {
                if (!serieCollectionNew.contains(serieCollectionOldSerie)) {
                    serieCollectionOldSerie.setSubSeccion(null);
                    serieCollectionOldSerie = em.merge(serieCollectionOldSerie);
                }
            }
            for (Serie serieCollectionNewSerie : serieCollectionNew) {
                if (!serieCollectionOld.contains(serieCollectionNewSerie)) {
                    SubSeccion oldSubSeccionOfSerieCollectionNewSerie = serieCollectionNewSerie.getSubSeccion();
                    serieCollectionNewSerie.setSubSeccion(subSeccion);
                    serieCollectionNewSerie = em.merge(serieCollectionNewSerie);
                    if (oldSubSeccionOfSerieCollectionNewSerie != null && !oldSubSeccionOfSerieCollectionNewSerie.equals(subSeccion)) {
                        oldSubSeccionOfSerieCollectionNewSerie.getSerieCollection().remove(serieCollectionNewSerie);
                        oldSubSeccionOfSerieCollectionNewSerie = em.merge(oldSubSeccionOfSerieCollectionNewSerie);
                    }
                }
            }
            for (Documento documentoCollectionOldDocumento : documentoCollectionOld) {
                if (!documentoCollectionNew.contains(documentoCollectionOldDocumento)) {
                    documentoCollectionOldDocumento.setSubSeccion(null);
                    documentoCollectionOldDocumento = em.merge(documentoCollectionOldDocumento);
                }
            }
            for (Documento documentoCollectionNewDocumento : documentoCollectionNew) {
                if (!documentoCollectionOld.contains(documentoCollectionNewDocumento)) {
                    SubSeccion oldSubSeccionOfDocumentoCollectionNewDocumento = documentoCollectionNewDocumento.getSubSeccion();
                    documentoCollectionNewDocumento.setSubSeccion(subSeccion);
                    documentoCollectionNewDocumento = em.merge(documentoCollectionNewDocumento);
                    if (oldSubSeccionOfDocumentoCollectionNewDocumento != null && !oldSubSeccionOfDocumentoCollectionNewDocumento.equals(subSeccion)) {
                        oldSubSeccionOfDocumentoCollectionNewDocumento.getDocumentoCollection().remove(documentoCollectionNewDocumento);
                        oldSubSeccionOfDocumentoCollectionNewDocumento = em.merge(oldSubSeccionOfDocumentoCollectionNewDocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = subSeccion.getId();
                if (findSubSeccion(id) == null) {
                    throw new NonexistentEntityException("The subSeccion with id " + id + " no longer exists.");
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
            SubSeccion subSeccion;
            try {
                subSeccion = em.getReference(SubSeccion.class, id);
                subSeccion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The subSeccion with id " + id + " no longer exists.", enfe);
            }
            Seccion seccion = subSeccion.getSeccion();
            if (seccion != null) {
                seccion.getSubSeccionCollection().remove(subSeccion);
                seccion = em.merge(seccion);
            }
            Usuario creadoPor = subSeccion.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getSubSeccionCollection().remove(subSeccion);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = subSeccion.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getSubSeccionCollection().remove(subSeccion);
                modificadoPor = em.merge(modificadoPor);
            }
            Collection<Serie> serieCollection = subSeccion.getSerieCollection();
            for (Serie serieCollectionSerie : serieCollection) {
                serieCollectionSerie.setSubSeccion(null);
                serieCollectionSerie = em.merge(serieCollectionSerie);
            }
            Collection<Documento> documentoCollection = subSeccion.getDocumentoCollection();
            for (Documento documentoCollectionDocumento : documentoCollection) {
                documentoCollectionDocumento.setSubSeccion(null);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
            }
            em.remove(subSeccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SubSeccion> findSubSeccionEntities() {
        return findSubSeccionEntities(true, -1, -1);
    }

    public List<SubSeccion> findSubSeccionEntities(int maxResults, int firstResult) {
        return findSubSeccionEntities(false, maxResults, firstResult);
    }

    private List<SubSeccion> findSubSeccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SubSeccion.class));
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

    public SubSeccion findSubSeccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SubSeccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getSubSeccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SubSeccion> rt = cq.from(SubSeccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
