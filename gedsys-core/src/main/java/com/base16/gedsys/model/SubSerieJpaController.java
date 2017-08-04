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
import com.base16.gedsys.entities.Serie;
import com.base16.gedsys.entities.SubSerie;
import com.base16.gedsys.entities.UnidadDocumental;
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
public class SubSerieJpaController implements Serializable {

    public SubSerieJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SubSerie subSerie) {
        if (subSerie.getUnidadDocumentalCollection() == null) {
            subSerie.setUnidadDocumentalCollection(new ArrayList<UnidadDocumental>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
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
            Serie serie = subSerie.getSerie();
            if (serie != null) {
                serie = em.getReference(serie.getClass(), serie.getId());
                subSerie.setSerie(serie);
            }
            Collection<UnidadDocumental> attachedUnidadDocumentalCollection = new ArrayList<UnidadDocumental>();
            for (UnidadDocumental unidadDocumentalCollectionUnidadDocumentalToAttach : subSerie.getUnidadDocumentalCollection()) {
                unidadDocumentalCollectionUnidadDocumentalToAttach = em.getReference(unidadDocumentalCollectionUnidadDocumentalToAttach.getClass(), unidadDocumentalCollectionUnidadDocumentalToAttach.getId());
                attachedUnidadDocumentalCollection.add(unidadDocumentalCollectionUnidadDocumentalToAttach);
            }
            subSerie.setUnidadDocumentalCollection(attachedUnidadDocumentalCollection);
            em.persist(subSerie);
            if (creadoPor != null) {
                creadoPor.getSubSerieCollection().add(subSerie);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getSubSerieCollection().add(subSerie);
                modificadoPor = em.merge(modificadoPor);
            }
            if (serie != null) {
                serie.getSubSerieCollection().add(subSerie);
                serie = em.merge(serie);
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
            em.getTransaction().commit();
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
            Usuario creadoPorOld = persistentSubSerie.getCreadoPor();
            Usuario creadoPorNew = subSerie.getCreadoPor();
            Usuario modificadoPorOld = persistentSubSerie.getModificadoPor();
            Usuario modificadoPorNew = subSerie.getModificadoPor();
            Serie serieOld = persistentSubSerie.getSerie();
            Serie serieNew = subSerie.getSerie();
            Collection<UnidadDocumental> unidadDocumentalCollectionOld = persistentSubSerie.getUnidadDocumentalCollection();
            Collection<UnidadDocumental> unidadDocumentalCollectionNew = subSerie.getUnidadDocumentalCollection();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                subSerie.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                subSerie.setModificadoPor(modificadoPorNew);
            }
            if (serieNew != null) {
                serieNew = em.getReference(serieNew.getClass(), serieNew.getId());
                subSerie.setSerie(serieNew);
            }
            Collection<UnidadDocumental> attachedUnidadDocumentalCollectionNew = new ArrayList<UnidadDocumental>();
            for (UnidadDocumental unidadDocumentalCollectionNewUnidadDocumentalToAttach : unidadDocumentalCollectionNew) {
                unidadDocumentalCollectionNewUnidadDocumentalToAttach = em.getReference(unidadDocumentalCollectionNewUnidadDocumentalToAttach.getClass(), unidadDocumentalCollectionNewUnidadDocumentalToAttach.getId());
                attachedUnidadDocumentalCollectionNew.add(unidadDocumentalCollectionNewUnidadDocumentalToAttach);
            }
            unidadDocumentalCollectionNew = attachedUnidadDocumentalCollectionNew;
            subSerie.setUnidadDocumentalCollection(unidadDocumentalCollectionNew);
            subSerie = em.merge(subSerie);
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
            if (serieOld != null && !serieOld.equals(serieNew)) {
                serieOld.getSubSerieCollection().remove(subSerie);
                serieOld = em.merge(serieOld);
            }
            if (serieNew != null && !serieNew.equals(serieOld)) {
                serieNew.getSubSerieCollection().add(subSerie);
                serieNew = em.merge(serieNew);
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
            Serie serie = subSerie.getSerie();
            if (serie != null) {
                serie.getSubSerieCollection().remove(subSerie);
                serie = em.merge(serie);
            }
            Collection<UnidadDocumental> unidadDocumentalCollection = subSerie.getUnidadDocumentalCollection();
            for (UnidadDocumental unidadDocumentalCollectionUnidadDocumental : unidadDocumentalCollection) {
                unidadDocumentalCollectionUnidadDocumental.setSubSerie(null);
                unidadDocumentalCollectionUnidadDocumental = em.merge(unidadDocumentalCollectionUnidadDocumental);
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
    
    public List<SubSerie> findSubSerieBySerie(Serie serie) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Serie.class));
            Query q = em.createNamedQuery("SubSerie.findBySerie", SubSerie.class)
                    .setParameter("serie", serie);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
