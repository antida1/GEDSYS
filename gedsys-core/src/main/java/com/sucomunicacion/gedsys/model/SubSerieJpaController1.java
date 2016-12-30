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
import com.sucomunicacion.gedsys.entities.SubSerie;
import com.sucomunicacion.gedsys.entities.UnidadDocumental;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
 */
public class SubSerieJpaController1 implements Serializable {

    public SubSerieJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SubSerie subSerie) throws PreexistingEntityException, Exception {
        if (subSerie.getUnidadDocumentalList() == null) {
            subSerie.setUnidadDocumentalList(new ArrayList<UnidadDocumental>());
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
            List<UnidadDocumental> attachedUnidadDocumentalList = new ArrayList<UnidadDocumental>();
            for (UnidadDocumental unidadDocumentalListUnidadDocumentalToAttach : subSerie.getUnidadDocumentalList()) {
                unidadDocumentalListUnidadDocumentalToAttach = em.getReference(unidadDocumentalListUnidadDocumentalToAttach.getClass(), unidadDocumentalListUnidadDocumentalToAttach.getId());
                attachedUnidadDocumentalList.add(unidadDocumentalListUnidadDocumentalToAttach);
            }
            subSerie.setUnidadDocumentalList(attachedUnidadDocumentalList);
            em.persist(subSerie);
            if (serie != null) {
                serie.getSubSerieList().add(subSerie);
                serie = em.merge(serie);
            }
            for (UnidadDocumental unidadDocumentalListUnidadDocumental : subSerie.getUnidadDocumentalList()) {
                SubSerie oldSubSerieOfUnidadDocumentalListUnidadDocumental = unidadDocumentalListUnidadDocumental.getSubSerie();
                unidadDocumentalListUnidadDocumental.setSubSerie(subSerie);
                unidadDocumentalListUnidadDocumental = em.merge(unidadDocumentalListUnidadDocumental);
                if (oldSubSerieOfUnidadDocumentalListUnidadDocumental != null) {
                    oldSubSerieOfUnidadDocumentalListUnidadDocumental.getUnidadDocumentalList().remove(unidadDocumentalListUnidadDocumental);
                    oldSubSerieOfUnidadDocumentalListUnidadDocumental = em.merge(oldSubSerieOfUnidadDocumentalListUnidadDocumental);
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
            List<UnidadDocumental> unidadDocumentalListOld = persistentSubSerie.getUnidadDocumentalList();
            List<UnidadDocumental> unidadDocumentalListNew = subSerie.getUnidadDocumentalList();
            if (serieNew != null) {
                serieNew = em.getReference(serieNew.getClass(), serieNew.getId());
                subSerie.setSerie(serieNew);
            }
            List<UnidadDocumental> attachedUnidadDocumentalListNew = new ArrayList<UnidadDocumental>();
            for (UnidadDocumental unidadDocumentalListNewUnidadDocumentalToAttach : unidadDocumentalListNew) {
                unidadDocumentalListNewUnidadDocumentalToAttach = em.getReference(unidadDocumentalListNewUnidadDocumentalToAttach.getClass(), unidadDocumentalListNewUnidadDocumentalToAttach.getId());
                attachedUnidadDocumentalListNew.add(unidadDocumentalListNewUnidadDocumentalToAttach);
            }
            unidadDocumentalListNew = attachedUnidadDocumentalListNew;
            subSerie.setUnidadDocumentalList(unidadDocumentalListNew);
            subSerie = em.merge(subSerie);
            if (serieOld != null && !serieOld.equals(serieNew)) {
                serieOld.getSubSerieList().remove(subSerie);
                serieOld = em.merge(serieOld);
            }
            if (serieNew != null && !serieNew.equals(serieOld)) {
                serieNew.getSubSerieList().add(subSerie);
                serieNew = em.merge(serieNew);
            }
            for (UnidadDocumental unidadDocumentalListOldUnidadDocumental : unidadDocumentalListOld) {
                if (!unidadDocumentalListNew.contains(unidadDocumentalListOldUnidadDocumental)) {
                    unidadDocumentalListOldUnidadDocumental.setSubSerie(null);
                    unidadDocumentalListOldUnidadDocumental = em.merge(unidadDocumentalListOldUnidadDocumental);
                }
            }
            for (UnidadDocumental unidadDocumentalListNewUnidadDocumental : unidadDocumentalListNew) {
                if (!unidadDocumentalListOld.contains(unidadDocumentalListNewUnidadDocumental)) {
                    SubSerie oldSubSerieOfUnidadDocumentalListNewUnidadDocumental = unidadDocumentalListNewUnidadDocumental.getSubSerie();
                    unidadDocumentalListNewUnidadDocumental.setSubSerie(subSerie);
                    unidadDocumentalListNewUnidadDocumental = em.merge(unidadDocumentalListNewUnidadDocumental);
                    if (oldSubSerieOfUnidadDocumentalListNewUnidadDocumental != null && !oldSubSerieOfUnidadDocumentalListNewUnidadDocumental.equals(subSerie)) {
                        oldSubSerieOfUnidadDocumentalListNewUnidadDocumental.getUnidadDocumentalList().remove(unidadDocumentalListNewUnidadDocumental);
                        oldSubSerieOfUnidadDocumentalListNewUnidadDocumental = em.merge(oldSubSerieOfUnidadDocumentalListNewUnidadDocumental);
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
                serie.getSubSerieList().remove(subSerie);
                serie = em.merge(serie);
            }
            List<UnidadDocumental> unidadDocumentalList = subSerie.getUnidadDocumentalList();
            for (UnidadDocumental unidadDocumentalListUnidadDocumental : unidadDocumentalList) {
                unidadDocumentalListUnidadDocumental.setSubSerie(null);
                unidadDocumentalListUnidadDocumental = em.merge(unidadDocumentalListUnidadDocumental);
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
