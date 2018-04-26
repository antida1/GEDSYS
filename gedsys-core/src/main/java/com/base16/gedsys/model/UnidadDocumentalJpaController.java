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
import com.base16.gedsys.entities.SubSerie;
import com.base16.gedsys.entities.TipoDocumental;
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
public class UnidadDocumentalJpaController implements Serializable {

    public UnidadDocumentalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UnidadDocumental unidadDocumental) {
        if (unidadDocumental.getTipoDocumentalCollection() == null) {
            unidadDocumental.setTipoDocumentalCollection(new ArrayList<TipoDocumental>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = unidadDocumental.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                unidadDocumental.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = unidadDocumental.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                unidadDocumental.setModificadoPor(modificadoPor);
            }
            SubSerie subSerie = unidadDocumental.getSubSerie();
            if (subSerie != null) {
                subSerie = em.getReference(subSerie.getClass(), subSerie.getId());
                unidadDocumental.setSubSerie(subSerie);
            }
            Collection<TipoDocumental> attachedTipoDocumentalCollection = new ArrayList<TipoDocumental>();
            for (TipoDocumental tipoDocumentalCollectionTipoDocumentalToAttach : unidadDocumental.getTipoDocumentalCollection()) {
                tipoDocumentalCollectionTipoDocumentalToAttach = em.getReference(tipoDocumentalCollectionTipoDocumentalToAttach.getClass(), tipoDocumentalCollectionTipoDocumentalToAttach.getId());
                attachedTipoDocumentalCollection.add(tipoDocumentalCollectionTipoDocumentalToAttach);
            }
            unidadDocumental.setTipoDocumentalCollection(attachedTipoDocumentalCollection);
            em.persist(unidadDocumental);
            if (creadoPor != null) {
                creadoPor.getUnidadDocumentalCollection().add(unidadDocumental);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getUnidadDocumentalCollection().add(unidadDocumental);
                modificadoPor = em.merge(modificadoPor);
            }
            if (subSerie != null) {
                subSerie.getUnidadDocumentalCollection().add(unidadDocumental);
                subSerie = em.merge(subSerie);
            }
            for (TipoDocumental tipoDocumentalCollectionTipoDocumental : unidadDocumental.getTipoDocumentalCollection()) {
                UnidadDocumental oldUnidadDocumentalOfTipoDocumentalCollectionTipoDocumental = tipoDocumentalCollectionTipoDocumental.getUnidadDocumental();
                tipoDocumentalCollectionTipoDocumental.setUnidadDocumental(unidadDocumental);
                tipoDocumentalCollectionTipoDocumental = em.merge(tipoDocumentalCollectionTipoDocumental);
                if (oldUnidadDocumentalOfTipoDocumentalCollectionTipoDocumental != null) {
                    oldUnidadDocumentalOfTipoDocumentalCollectionTipoDocumental.getTipoDocumentalCollection().remove(tipoDocumentalCollectionTipoDocumental);
                    oldUnidadDocumentalOfTipoDocumentalCollectionTipoDocumental = em.merge(oldUnidadDocumentalOfTipoDocumentalCollectionTipoDocumental);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UnidadDocumental unidadDocumental) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UnidadDocumental persistentUnidadDocumental = em.find(UnidadDocumental.class, unidadDocumental.getId());
            Usuario creadoPorOld = persistentUnidadDocumental.getCreadoPor();
            Usuario creadoPorNew = unidadDocumental.getCreadoPor();
            Usuario modificadoPorOld = persistentUnidadDocumental.getModificadoPor();
            Usuario modificadoPorNew = unidadDocumental.getModificadoPor();
            SubSerie subSerieOld = persistentUnidadDocumental.getSubSerie();
            SubSerie subSerieNew = unidadDocumental.getSubSerie();
            Collection<TipoDocumental> tipoDocumentalCollectionOld = persistentUnidadDocumental.getTipoDocumentalCollection();
            Collection<TipoDocumental> tipoDocumentalCollectionNew = unidadDocumental.getTipoDocumentalCollection();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                unidadDocumental.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                unidadDocumental.setModificadoPor(modificadoPorNew);
            }
            if (subSerieNew != null) {
                subSerieNew = em.getReference(subSerieNew.getClass(), subSerieNew.getId());
                unidadDocumental.setSubSerie(subSerieNew);
            }
            Collection<TipoDocumental> attachedTipoDocumentalCollectionNew = new ArrayList<TipoDocumental>();
            for (TipoDocumental tipoDocumentalCollectionNewTipoDocumentalToAttach : tipoDocumentalCollectionNew) {
                tipoDocumentalCollectionNewTipoDocumentalToAttach = em.getReference(tipoDocumentalCollectionNewTipoDocumentalToAttach.getClass(), tipoDocumentalCollectionNewTipoDocumentalToAttach.getId());
                attachedTipoDocumentalCollectionNew.add(tipoDocumentalCollectionNewTipoDocumentalToAttach);
            }
            tipoDocumentalCollectionNew = attachedTipoDocumentalCollectionNew;
            unidadDocumental.setTipoDocumentalCollection(tipoDocumentalCollectionNew);
            unidadDocumental = em.merge(unidadDocumental);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getUnidadDocumentalCollection().remove(unidadDocumental);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getUnidadDocumentalCollection().add(unidadDocumental);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getUnidadDocumentalCollection().remove(unidadDocumental);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getUnidadDocumentalCollection().add(unidadDocumental);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            if (subSerieOld != null && !subSerieOld.equals(subSerieNew)) {
                subSerieOld.getUnidadDocumentalCollection().remove(unidadDocumental);
                subSerieOld = em.merge(subSerieOld);
            }
            if (subSerieNew != null && !subSerieNew.equals(subSerieOld)) {
                subSerieNew.getUnidadDocumentalCollection().add(unidadDocumental);
                subSerieNew = em.merge(subSerieNew);
            }
            for (TipoDocumental tipoDocumentalCollectionOldTipoDocumental : tipoDocumentalCollectionOld) {
                if (!tipoDocumentalCollectionNew.contains(tipoDocumentalCollectionOldTipoDocumental)) {
                    tipoDocumentalCollectionOldTipoDocumental.setUnidadDocumental(null);
                    tipoDocumentalCollectionOldTipoDocumental = em.merge(tipoDocumentalCollectionOldTipoDocumental);
                }
            }
            for (TipoDocumental tipoDocumentalCollectionNewTipoDocumental : tipoDocumentalCollectionNew) {
                if (!tipoDocumentalCollectionOld.contains(tipoDocumentalCollectionNewTipoDocumental)) {
                    UnidadDocumental oldUnidadDocumentalOfTipoDocumentalCollectionNewTipoDocumental = tipoDocumentalCollectionNewTipoDocumental.getUnidadDocumental();
                    tipoDocumentalCollectionNewTipoDocumental.setUnidadDocumental(unidadDocumental);
                    tipoDocumentalCollectionNewTipoDocumental = em.merge(tipoDocumentalCollectionNewTipoDocumental);
                    if (oldUnidadDocumentalOfTipoDocumentalCollectionNewTipoDocumental != null && !oldUnidadDocumentalOfTipoDocumentalCollectionNewTipoDocumental.equals(unidadDocumental)) {
                        oldUnidadDocumentalOfTipoDocumentalCollectionNewTipoDocumental.getTipoDocumentalCollection().remove(tipoDocumentalCollectionNewTipoDocumental);
                        oldUnidadDocumentalOfTipoDocumentalCollectionNewTipoDocumental = em.merge(oldUnidadDocumentalOfTipoDocumentalCollectionNewTipoDocumental);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = unidadDocumental.getId();
                if (findUnidadDocumental(id) == null) {
                    throw new NonexistentEntityException("The unidadDocumental with id " + id + " no longer exists.");
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
            UnidadDocumental unidadDocumental;
            try {
                unidadDocumental = em.getReference(UnidadDocumental.class, id);
                unidadDocumental.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The unidadDocumental with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = unidadDocumental.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getUnidadDocumentalCollection().remove(unidadDocumental);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = unidadDocumental.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getUnidadDocumentalCollection().remove(unidadDocumental);
                modificadoPor = em.merge(modificadoPor);
            }
            SubSerie subSerie = unidadDocumental.getSubSerie();
            if (subSerie != null) {
                subSerie.getUnidadDocumentalCollection().remove(unidadDocumental);
                subSerie = em.merge(subSerie);
            }
            Collection<TipoDocumental> tipoDocumentalCollection = unidadDocumental.getTipoDocumentalCollection();
            for (TipoDocumental tipoDocumentalCollectionTipoDocumental : tipoDocumentalCollection) {
                tipoDocumentalCollectionTipoDocumental.setUnidadDocumental(null);
                tipoDocumentalCollectionTipoDocumental = em.merge(tipoDocumentalCollectionTipoDocumental);
            }
            em.remove(unidadDocumental);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UnidadDocumental> findUnidadDocumentalEntities() {
        return findUnidadDocumentalEntities(true, -1, -1);
    }

    public List<UnidadDocumental> findUnidadDocumentalEntities(int maxResults, int firstResult) {
        return findUnidadDocumentalEntities(false, maxResults, firstResult);
    }

    private List<UnidadDocumental> findUnidadDocumentalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UnidadDocumental.class));
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

    public UnidadDocumental findUnidadDocumental(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UnidadDocumental.class, id);
        } finally {
            em.close();
        }
    }

    public int getUnidadDocumentalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UnidadDocumental> rt = cq.from(UnidadDocumental.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<UnidadDocumental> findUnidadDocumentalBySubSerie(SubSerie subSerie) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UnidadDocumental.class));
            Query q = em.createNamedQuery("UnidadDocumental.findBySubSerie", UnidadDocumental.class)
                    .setParameter("subSerie", subSerie);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
