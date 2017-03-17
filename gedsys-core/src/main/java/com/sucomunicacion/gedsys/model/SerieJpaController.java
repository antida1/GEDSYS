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
import com.sucomunicacion.gedsys.entities.SubSeccion;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.entities.SubSerie;
import java.util.ArrayList;
import java.util.Collection;
import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.Serie;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class SerieJpaController implements Serializable {

    public SerieJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Serie serie) throws PreexistingEntityException, Exception {
        if (serie.getSubSerieCollection() == null) {
            serie.setSubSerieCollection(new ArrayList<SubSerie>());
        }
        if (serie.getDocumentoCollection() == null) {
            serie.setDocumentoCollection(new ArrayList<Documento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SubSeccion subSeccion = serie.getSubSeccion();
            if (subSeccion != null) {
                subSeccion = em.getReference(subSeccion.getClass(), subSeccion.getId());
                serie.setSubSeccion(subSeccion);
            }
            Usuario creadoPor = serie.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                serie.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = serie.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                serie.setModificadoPor(modificadoPor);
            }
            Collection<SubSerie> attachedSubSerieCollection = new ArrayList<SubSerie>();
            for (SubSerie subSerieCollectionSubSerieToAttach : serie.getSubSerieCollection()) {
                subSerieCollectionSubSerieToAttach = em.getReference(subSerieCollectionSubSerieToAttach.getClass(), subSerieCollectionSubSerieToAttach.getId());
                attachedSubSerieCollection.add(subSerieCollectionSubSerieToAttach);
            }
            serie.setSubSerieCollection(attachedSubSerieCollection);
            Collection<Documento> attachedDocumentoCollection = new ArrayList<Documento>();
            for (Documento documentoCollectionDocumentoToAttach : serie.getDocumentoCollection()) {
                documentoCollectionDocumentoToAttach = em.getReference(documentoCollectionDocumentoToAttach.getClass(), documentoCollectionDocumentoToAttach.getId());
                attachedDocumentoCollection.add(documentoCollectionDocumentoToAttach);
            }
            serie.setDocumentoCollection(attachedDocumentoCollection);
            em.persist(serie);
            if (subSeccion != null) {
                subSeccion.getSerieCollection().add(serie);
                subSeccion = em.merge(subSeccion);
            }
            if (creadoPor != null) {
                creadoPor.getSerieCollection().add(serie);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getSerieCollection().add(serie);
                modificadoPor = em.merge(modificadoPor);
            }
            for (SubSerie subSerieCollectionSubSerie : serie.getSubSerieCollection()) {
                Serie oldSerieOfSubSerieCollectionSubSerie = subSerieCollectionSubSerie.getSerie();
                subSerieCollectionSubSerie.setSerie(serie);
                subSerieCollectionSubSerie = em.merge(subSerieCollectionSubSerie);
                if (oldSerieOfSubSerieCollectionSubSerie != null) {
                    oldSerieOfSubSerieCollectionSubSerie.getSubSerieCollection().remove(subSerieCollectionSubSerie);
                    oldSerieOfSubSerieCollectionSubSerie = em.merge(oldSerieOfSubSerieCollectionSubSerie);
                }
            }
            for (Documento documentoCollectionDocumento : serie.getDocumentoCollection()) {
                Serie oldSerieOfDocumentoCollectionDocumento = documentoCollectionDocumento.getSerie();
                documentoCollectionDocumento.setSerie(serie);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
                if (oldSerieOfDocumentoCollectionDocumento != null) {
                    oldSerieOfDocumentoCollectionDocumento.getDocumentoCollection().remove(documentoCollectionDocumento);
                    oldSerieOfDocumentoCollectionDocumento = em.merge(oldSerieOfDocumentoCollectionDocumento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSerie(serie.getId()) != null) {
                throw new PreexistingEntityException("Serie " + serie + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Serie serie) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Serie persistentSerie = em.find(Serie.class, serie.getId());
            SubSeccion subSeccionOld = persistentSerie.getSubSeccion();
            SubSeccion subSeccionNew = serie.getSubSeccion();
            Usuario creadoPorOld = persistentSerie.getCreadoPor();
            Usuario creadoPorNew = serie.getCreadoPor();
            Usuario modificadoPorOld = persistentSerie.getModificadoPor();
            Usuario modificadoPorNew = serie.getModificadoPor();
            Collection<SubSerie> subSerieCollectionOld = persistentSerie.getSubSerieCollection();
            Collection<SubSerie> subSerieCollectionNew = serie.getSubSerieCollection();
            Collection<Documento> documentoCollectionOld = persistentSerie.getDocumentoCollection();
            Collection<Documento> documentoCollectionNew = serie.getDocumentoCollection();
            if (subSeccionNew != null) {
                subSeccionNew = em.getReference(subSeccionNew.getClass(), subSeccionNew.getId());
                serie.setSubSeccion(subSeccionNew);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                serie.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                serie.setModificadoPor(modificadoPorNew);
            }
            Collection<SubSerie> attachedSubSerieCollectionNew = new ArrayList<SubSerie>();
            for (SubSerie subSerieCollectionNewSubSerieToAttach : subSerieCollectionNew) {
                subSerieCollectionNewSubSerieToAttach = em.getReference(subSerieCollectionNewSubSerieToAttach.getClass(), subSerieCollectionNewSubSerieToAttach.getId());
                attachedSubSerieCollectionNew.add(subSerieCollectionNewSubSerieToAttach);
            }
            subSerieCollectionNew = attachedSubSerieCollectionNew;
            serie.setSubSerieCollection(subSerieCollectionNew);
            Collection<Documento> attachedDocumentoCollectionNew = new ArrayList<Documento>();
            for (Documento documentoCollectionNewDocumentoToAttach : documentoCollectionNew) {
                documentoCollectionNewDocumentoToAttach = em.getReference(documentoCollectionNewDocumentoToAttach.getClass(), documentoCollectionNewDocumentoToAttach.getId());
                attachedDocumentoCollectionNew.add(documentoCollectionNewDocumentoToAttach);
            }
            documentoCollectionNew = attachedDocumentoCollectionNew;
            serie.setDocumentoCollection(documentoCollectionNew);
            serie = em.merge(serie);
            if (subSeccionOld != null && !subSeccionOld.equals(subSeccionNew)) {
                subSeccionOld.getSerieCollection().remove(serie);
                subSeccionOld = em.merge(subSeccionOld);
            }
            if (subSeccionNew != null && !subSeccionNew.equals(subSeccionOld)) {
                subSeccionNew.getSerieCollection().add(serie);
                subSeccionNew = em.merge(subSeccionNew);
            }
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getSerieCollection().remove(serie);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getSerieCollection().add(serie);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getSerieCollection().remove(serie);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getSerieCollection().add(serie);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            for (SubSerie subSerieCollectionOldSubSerie : subSerieCollectionOld) {
                if (!subSerieCollectionNew.contains(subSerieCollectionOldSubSerie)) {
                    subSerieCollectionOldSubSerie.setSerie(null);
                    subSerieCollectionOldSubSerie = em.merge(subSerieCollectionOldSubSerie);
                }
            }
            for (SubSerie subSerieCollectionNewSubSerie : subSerieCollectionNew) {
                if (!subSerieCollectionOld.contains(subSerieCollectionNewSubSerie)) {
                    Serie oldSerieOfSubSerieCollectionNewSubSerie = subSerieCollectionNewSubSerie.getSerie();
                    subSerieCollectionNewSubSerie.setSerie(serie);
                    subSerieCollectionNewSubSerie = em.merge(subSerieCollectionNewSubSerie);
                    if (oldSerieOfSubSerieCollectionNewSubSerie != null && !oldSerieOfSubSerieCollectionNewSubSerie.equals(serie)) {
                        oldSerieOfSubSerieCollectionNewSubSerie.getSubSerieCollection().remove(subSerieCollectionNewSubSerie);
                        oldSerieOfSubSerieCollectionNewSubSerie = em.merge(oldSerieOfSubSerieCollectionNewSubSerie);
                    }
                }
            }
            for (Documento documentoCollectionOldDocumento : documentoCollectionOld) {
                if (!documentoCollectionNew.contains(documentoCollectionOldDocumento)) {
                    documentoCollectionOldDocumento.setSerie(null);
                    documentoCollectionOldDocumento = em.merge(documentoCollectionOldDocumento);
                }
            }
            for (Documento documentoCollectionNewDocumento : documentoCollectionNew) {
                if (!documentoCollectionOld.contains(documentoCollectionNewDocumento)) {
                    Serie oldSerieOfDocumentoCollectionNewDocumento = documentoCollectionNewDocumento.getSerie();
                    documentoCollectionNewDocumento.setSerie(serie);
                    documentoCollectionNewDocumento = em.merge(documentoCollectionNewDocumento);
                    if (oldSerieOfDocumentoCollectionNewDocumento != null && !oldSerieOfDocumentoCollectionNewDocumento.equals(serie)) {
                        oldSerieOfDocumentoCollectionNewDocumento.getDocumentoCollection().remove(documentoCollectionNewDocumento);
                        oldSerieOfDocumentoCollectionNewDocumento = em.merge(oldSerieOfDocumentoCollectionNewDocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = serie.getId();
                if (findSerie(id) == null) {
                    throw new NonexistentEntityException("The serie with id " + id + " no longer exists.");
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
            Serie serie;
            try {
                serie = em.getReference(Serie.class, id);
                serie.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serie with id " + id + " no longer exists.", enfe);
            }
            SubSeccion subSeccion = serie.getSubSeccion();
            if (subSeccion != null) {
                subSeccion.getSerieCollection().remove(serie);
                subSeccion = em.merge(subSeccion);
            }
            Usuario creadoPor = serie.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getSerieCollection().remove(serie);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = serie.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getSerieCollection().remove(serie);
                modificadoPor = em.merge(modificadoPor);
            }
            Collection<SubSerie> subSerieCollection = serie.getSubSerieCollection();
            for (SubSerie subSerieCollectionSubSerie : subSerieCollection) {
                subSerieCollectionSubSerie.setSerie(null);
                subSerieCollectionSubSerie = em.merge(subSerieCollectionSubSerie);
            }
            Collection<Documento> documentoCollection = serie.getDocumentoCollection();
            for (Documento documentoCollectionDocumento : documentoCollection) {
                documentoCollectionDocumento.setSerie(null);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
            }
            em.remove(serie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Serie> findSerieEntities() {
        return findSerieEntities(true, -1, -1);
    }

    public List<Serie> findSerieEntities(int maxResults, int firstResult) {
        return findSerieEntities(false, maxResults, firstResult);
    }

    private List<Serie> findSerieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Serie.class));
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

    public Serie findSerie(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Serie.class, id);
        } finally {
            em.close();
        }
    }

    public int getSerieCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Serie> rt = cq.from(Serie.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
