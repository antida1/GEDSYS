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
import com.sucomunicacion.gedsys.entities.Serie;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.entities.UnidadDocumental;
import java.util.ArrayList;
import java.util.Collection;
import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.SubSerie;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class SubSerieJpaController implements Serializable {

    public SubSerieJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SubSerie subSerie) throws PreexistingEntityException, Exception {
        if (subSerie.getUnidadDocumentalCollection() == null) {
            subSerie.setUnidadDocumentalCollection(new ArrayList<UnidadDocumental>());
        }
        if (subSerie.getDocumentoCollection() == null) {
            subSerie.setDocumentoCollection(new ArrayList<Documento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Serie serie = subSerie.getSerie();
            if (serie != null) {
                serie = em.getReference(serie.getClass(), serie.getId());
                subSerie.setSerie(serie);
            }
            Usuario creadoPor = subSerie.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                subSerie.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = subSerie.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                subSerie.setModificadoPor(modificadoPor);
            }
            Collection<UnidadDocumental> attachedUnidadDocumentalCollection = new ArrayList<UnidadDocumental>();
            for (UnidadDocumental unidadDocumentalCollectionUnidadDocumentalToAttach : subSerie.getUnidadDocumentalCollection()) {
                unidadDocumentalCollectionUnidadDocumentalToAttach = em.getReference(unidadDocumentalCollectionUnidadDocumentalToAttach.getClass(), unidadDocumentalCollectionUnidadDocumentalToAttach.getId());
                attachedUnidadDocumentalCollection.add(unidadDocumentalCollectionUnidadDocumentalToAttach);
            }
            subSerie.setUnidadDocumentalCollection(attachedUnidadDocumentalCollection);
            Collection<Documento> attachedDocumentoCollection = new ArrayList<Documento>();
            for (Documento documentoCollectionDocumentoToAttach : subSerie.getDocumentoCollection()) {
                documentoCollectionDocumentoToAttach = em.getReference(documentoCollectionDocumentoToAttach.getClass(), documentoCollectionDocumentoToAttach.getId());
                attachedDocumentoCollection.add(documentoCollectionDocumentoToAttach);
            }
            subSerie.setDocumentoCollection(attachedDocumentoCollection);
            em.persist(subSerie);
            if (serie != null) {
                serie.getSubSerieCollection().add(subSerie);
                serie = em.merge(serie);
            }
            if (creadoPor != null) {
                creadoPor.getSubSerieCollection().add(subSerie);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getSubSerieCollection().add(subSerie);
                modificadoPor = em.merge(modificadoPor);
            }
            for (UnidadDocumental unidadDocumentalCollectionUnidadDocumental : subSerie.getUnidadDocumentalCollection()) {
                SubSerie oldSubSerieOfUnidadDocumentalCollectionUnidadDocumental = unidadDocumentalCollectionUnidadDocumental.getSubSerie();
                unidadDocumentalCollectionUnidadDocumental.setSubSerie(subSerie);
                unidadDocumentalCollectionUnidadDocumental = em.merge(unidadDocumentalCollectionUnidadDocumental);
                if (oldSubSerieOfUnidadDocumentalCollectionUnidadDocumental != null) {
                    oldSubSerieOfUnidadDocumentalCollectionUnidadDocumental.getUnidadDocumentalCollection().remove(unidadDocumentalCollectionUnidadDocumental);
                    oldSubSerieOfUnidadDocumentalCollectionUnidadDocumental = em.merge(oldSubSerieOfUnidadDocumentalCollectionUnidadDocumental);
                }
            }
            for (Documento documentoCollectionDocumento : subSerie.getDocumentoCollection()) {
                SubSerie oldSubSerieOfDocumentoCollectionDocumento = documentoCollectionDocumento.getSubSerie();
                documentoCollectionDocumento.setSubSerie(subSerie);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
                if (oldSubSerieOfDocumentoCollectionDocumento != null) {
                    oldSubSerieOfDocumentoCollectionDocumento.getDocumentoCollection().remove(documentoCollectionDocumento);
                    oldSubSerieOfDocumentoCollectionDocumento = em.merge(oldSubSerieOfDocumentoCollectionDocumento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSubSerie(subSerie.getId()) != null) {
                throw new PreexistingEntityException("SubSerie " + subSerie + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SubSerie subSerie) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SubSerie persistentSubSerie = em.find(SubSerie.class, subSerie.getId());
            Serie serieOld = persistentSubSerie.getSerie();
            Serie serieNew = subSerie.getSerie();
            Usuario creadoPorOld = persistentSubSerie.getCreadoPor();
            Usuario creadoPorNew = subSerie.getCreadoPor();
            Usuario modificadoPorOld = persistentSubSerie.getModificadoPor();
            Usuario modificadoPorNew = subSerie.getModificadoPor();
            Collection<UnidadDocumental> unidadDocumentalCollectionOld = persistentSubSerie.getUnidadDocumentalCollection();
            Collection<UnidadDocumental> unidadDocumentalCollectionNew = subSerie.getUnidadDocumentalCollection();
            Collection<Documento> documentoCollectionOld = persistentSubSerie.getDocumentoCollection();
            Collection<Documento> documentoCollectionNew = subSerie.getDocumentoCollection();
            if (serieNew != null) {
                serieNew = em.getReference(serieNew.getClass(), serieNew.getId());
                subSerie.setSerie(serieNew);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                subSerie.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                subSerie.setModificadoPor(modificadoPorNew);
            }
            Collection<UnidadDocumental> attachedUnidadDocumentalCollectionNew = new ArrayList<UnidadDocumental>();
            for (UnidadDocumental unidadDocumentalCollectionNewUnidadDocumentalToAttach : unidadDocumentalCollectionNew) {
                unidadDocumentalCollectionNewUnidadDocumentalToAttach = em.getReference(unidadDocumentalCollectionNewUnidadDocumentalToAttach.getClass(), unidadDocumentalCollectionNewUnidadDocumentalToAttach.getId());
                attachedUnidadDocumentalCollectionNew.add(unidadDocumentalCollectionNewUnidadDocumentalToAttach);
            }
            unidadDocumentalCollectionNew = attachedUnidadDocumentalCollectionNew;
            subSerie.setUnidadDocumentalCollection(unidadDocumentalCollectionNew);
            Collection<Documento> attachedDocumentoCollectionNew = new ArrayList<Documento>();
            for (Documento documentoCollectionNewDocumentoToAttach : documentoCollectionNew) {
                documentoCollectionNewDocumentoToAttach = em.getReference(documentoCollectionNewDocumentoToAttach.getClass(), documentoCollectionNewDocumentoToAttach.getId());
                attachedDocumentoCollectionNew.add(documentoCollectionNewDocumentoToAttach);
            }
            documentoCollectionNew = attachedDocumentoCollectionNew;
            subSerie.setDocumentoCollection(documentoCollectionNew);
            subSerie = em.merge(subSerie);
            if (serieOld != null && !serieOld.equals(serieNew)) {
                serieOld.getSubSerieCollection().remove(subSerie);
                serieOld = em.merge(serieOld);
            }
            if (serieNew != null && !serieNew.equals(serieOld)) {
                serieNew.getSubSerieCollection().add(subSerie);
                serieNew = em.merge(serieNew);
            }
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getSubSerieCollection().remove(subSerie);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getSubSerieCollection().add(subSerie);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getSubSerieCollection().remove(subSerie);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getSubSerieCollection().add(subSerie);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            for (UnidadDocumental unidadDocumentalCollectionOldUnidadDocumental : unidadDocumentalCollectionOld) {
                if (!unidadDocumentalCollectionNew.contains(unidadDocumentalCollectionOldUnidadDocumental)) {
                    unidadDocumentalCollectionOldUnidadDocumental.setSubSerie(null);
                    unidadDocumentalCollectionOldUnidadDocumental = em.merge(unidadDocumentalCollectionOldUnidadDocumental);
                }
            }
            for (UnidadDocumental unidadDocumentalCollectionNewUnidadDocumental : unidadDocumentalCollectionNew) {
                if (!unidadDocumentalCollectionOld.contains(unidadDocumentalCollectionNewUnidadDocumental)) {
                    SubSerie oldSubSerieOfUnidadDocumentalCollectionNewUnidadDocumental = unidadDocumentalCollectionNewUnidadDocumental.getSubSerie();
                    unidadDocumentalCollectionNewUnidadDocumental.setSubSerie(subSerie);
                    unidadDocumentalCollectionNewUnidadDocumental = em.merge(unidadDocumentalCollectionNewUnidadDocumental);
                    if (oldSubSerieOfUnidadDocumentalCollectionNewUnidadDocumental != null && !oldSubSerieOfUnidadDocumentalCollectionNewUnidadDocumental.equals(subSerie)) {
                        oldSubSerieOfUnidadDocumentalCollectionNewUnidadDocumental.getUnidadDocumentalCollection().remove(unidadDocumentalCollectionNewUnidadDocumental);
                        oldSubSerieOfUnidadDocumentalCollectionNewUnidadDocumental = em.merge(oldSubSerieOfUnidadDocumentalCollectionNewUnidadDocumental);
                    }
                }
            }
            for (Documento documentoCollectionOldDocumento : documentoCollectionOld) {
                if (!documentoCollectionNew.contains(documentoCollectionOldDocumento)) {
                    documentoCollectionOldDocumento.setSubSerie(null);
                    documentoCollectionOldDocumento = em.merge(documentoCollectionOldDocumento);
                }
            }
            for (Documento documentoCollectionNewDocumento : documentoCollectionNew) {
                if (!documentoCollectionOld.contains(documentoCollectionNewDocumento)) {
                    SubSerie oldSubSerieOfDocumentoCollectionNewDocumento = documentoCollectionNewDocumento.getSubSerie();
                    documentoCollectionNewDocumento.setSubSerie(subSerie);
                    documentoCollectionNewDocumento = em.merge(documentoCollectionNewDocumento);
                    if (oldSubSerieOfDocumentoCollectionNewDocumento != null && !oldSubSerieOfDocumentoCollectionNewDocumento.equals(subSerie)) {
                        oldSubSerieOfDocumentoCollectionNewDocumento.getDocumentoCollection().remove(documentoCollectionNewDocumento);
                        oldSubSerieOfDocumentoCollectionNewDocumento = em.merge(oldSubSerieOfDocumentoCollectionNewDocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = subSerie.getId();
                if (findSubSerie(id) == null) {
                    throw new NonexistentEntityException("The subSerie with id " + id + " no longer exists.");
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
            SubSerie subSerie;
            try {
                subSerie = em.getReference(SubSerie.class, id);
                subSerie.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The subSerie with id " + id + " no longer exists.", enfe);
            }
            Serie serie = subSerie.getSerie();
            if (serie != null) {
                serie.getSubSerieCollection().remove(subSerie);
                serie = em.merge(serie);
            }
            Usuario creadoPor = subSerie.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getSubSerieCollection().remove(subSerie);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = subSerie.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getSubSerieCollection().remove(subSerie);
                modificadoPor = em.merge(modificadoPor);
            }
            Collection<UnidadDocumental> unidadDocumentalCollection = subSerie.getUnidadDocumentalCollection();
            for (UnidadDocumental unidadDocumentalCollectionUnidadDocumental : unidadDocumentalCollection) {
                unidadDocumentalCollectionUnidadDocumental.setSubSerie(null);
                unidadDocumentalCollectionUnidadDocumental = em.merge(unidadDocumentalCollectionUnidadDocumental);
            }
            Collection<Documento> documentoCollection = subSerie.getDocumentoCollection();
            for (Documento documentoCollectionDocumento : documentoCollection) {
                documentoCollectionDocumento.setSubSerie(null);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
            }
            em.remove(subSerie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SubSerie> findSubSerieEntities() {
        return findSubSerieEntities(true, -1, -1);
    }

    public List<SubSerie> findSubSerieEntities(int maxResults, int firstResult) {
        return findSubSerieEntities(false, maxResults, firstResult);
    }

    private List<SubSerie> findSubSerieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SubSerie.class));
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

    public SubSerie findSubSerie(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SubSerie.class, id);
        } finally {
            em.close();
        }
    }

    public int getSubSerieCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SubSerie> rt = cq.from(SubSerie.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
