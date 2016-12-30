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
import com.sucomunicacion.gedsys.entities.SubSerie;
import java.util.ArrayList;
import java.util.List;
import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.Serie;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
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
        if (serie.getSubSerieList() == null) {
            serie.setSubSerieList(new ArrayList<SubSerie>());
        }
        if (serie.getDocumentoList() == null) {
            serie.setDocumentoList(new ArrayList<Documento>());
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
            List<SubSerie> attachedSubSerieList = new ArrayList<SubSerie>();
            for (SubSerie subSerieListSubSerieToAttach : serie.getSubSerieList()) {
                subSerieListSubSerieToAttach = em.getReference(subSerieListSubSerieToAttach.getClass(), subSerieListSubSerieToAttach.getId());
                attachedSubSerieList.add(subSerieListSubSerieToAttach);
            }
            serie.setSubSerieList(attachedSubSerieList);
            List<Documento> attachedDocumentoList = new ArrayList<Documento>();
            for (Documento documentoListDocumentoToAttach : serie.getDocumentoList()) {
                documentoListDocumentoToAttach = em.getReference(documentoListDocumentoToAttach.getClass(), documentoListDocumentoToAttach.getId());
                attachedDocumentoList.add(documentoListDocumentoToAttach);
            }
            serie.setDocumentoList(attachedDocumentoList);
            em.persist(serie);
            if (subSeccion != null) {
                subSeccion.getSerieList().add(serie);
                subSeccion = em.merge(subSeccion);
            }
            for (SubSerie subSerieListSubSerie : serie.getSubSerieList()) {
                Serie oldSerieOfSubSerieListSubSerie = subSerieListSubSerie.getSerie();
                subSerieListSubSerie.setSerie(serie);
                subSerieListSubSerie = em.merge(subSerieListSubSerie);
                if (oldSerieOfSubSerieListSubSerie != null) {
                    oldSerieOfSubSerieListSubSerie.getSubSerieList().remove(subSerieListSubSerie);
                    oldSerieOfSubSerieListSubSerie = em.merge(oldSerieOfSubSerieListSubSerie);
                }
            }
            for (Documento documentoListDocumento : serie.getDocumentoList()) {
                Serie oldSerieOfDocumentoListDocumento = documentoListDocumento.getSerie();
                documentoListDocumento.setSerie(serie);
                documentoListDocumento = em.merge(documentoListDocumento);
                if (oldSerieOfDocumentoListDocumento != null) {
                    oldSerieOfDocumentoListDocumento.getDocumentoList().remove(documentoListDocumento);
                    oldSerieOfDocumentoListDocumento = em.merge(oldSerieOfDocumentoListDocumento);
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
            List<SubSerie> subSerieListOld = persistentSerie.getSubSerieList();
            List<SubSerie> subSerieListNew = serie.getSubSerieList();
            List<Documento> documentoListOld = persistentSerie.getDocumentoList();
            List<Documento> documentoListNew = serie.getDocumentoList();
            if (subSeccionNew != null) {
                subSeccionNew = em.getReference(subSeccionNew.getClass(), subSeccionNew.getId());
                serie.setSubSeccion(subSeccionNew);
            }
            List<SubSerie> attachedSubSerieListNew = new ArrayList<SubSerie>();
            for (SubSerie subSerieListNewSubSerieToAttach : subSerieListNew) {
                subSerieListNewSubSerieToAttach = em.getReference(subSerieListNewSubSerieToAttach.getClass(), subSerieListNewSubSerieToAttach.getId());
                attachedSubSerieListNew.add(subSerieListNewSubSerieToAttach);
            }
            subSerieListNew = attachedSubSerieListNew;
            serie.setSubSerieList(subSerieListNew);
            List<Documento> attachedDocumentoListNew = new ArrayList<Documento>();
            for (Documento documentoListNewDocumentoToAttach : documentoListNew) {
                documentoListNewDocumentoToAttach = em.getReference(documentoListNewDocumentoToAttach.getClass(), documentoListNewDocumentoToAttach.getId());
                attachedDocumentoListNew.add(documentoListNewDocumentoToAttach);
            }
            documentoListNew = attachedDocumentoListNew;
            serie.setDocumentoList(documentoListNew);
            serie = em.merge(serie);
            if (subSeccionOld != null && !subSeccionOld.equals(subSeccionNew)) {
                subSeccionOld.getSerieList().remove(serie);
                subSeccionOld = em.merge(subSeccionOld);
            }
            if (subSeccionNew != null && !subSeccionNew.equals(subSeccionOld)) {
                subSeccionNew.getSerieList().add(serie);
                subSeccionNew = em.merge(subSeccionNew);
            }
            for (SubSerie subSerieListOldSubSerie : subSerieListOld) {
                if (!subSerieListNew.contains(subSerieListOldSubSerie)) {
                    subSerieListOldSubSerie.setSerie(null);
                    subSerieListOldSubSerie = em.merge(subSerieListOldSubSerie);
                }
            }
            for (SubSerie subSerieListNewSubSerie : subSerieListNew) {
                if (!subSerieListOld.contains(subSerieListNewSubSerie)) {
                    Serie oldSerieOfSubSerieListNewSubSerie = subSerieListNewSubSerie.getSerie();
                    subSerieListNewSubSerie.setSerie(serie);
                    subSerieListNewSubSerie = em.merge(subSerieListNewSubSerie);
                    if (oldSerieOfSubSerieListNewSubSerie != null && !oldSerieOfSubSerieListNewSubSerie.equals(serie)) {
                        oldSerieOfSubSerieListNewSubSerie.getSubSerieList().remove(subSerieListNewSubSerie);
                        oldSerieOfSubSerieListNewSubSerie = em.merge(oldSerieOfSubSerieListNewSubSerie);
                    }
                }
            }
            for (Documento documentoListOldDocumento : documentoListOld) {
                if (!documentoListNew.contains(documentoListOldDocumento)) {
                    documentoListOldDocumento.setSerie(null);
                    documentoListOldDocumento = em.merge(documentoListOldDocumento);
                }
            }
            for (Documento documentoListNewDocumento : documentoListNew) {
                if (!documentoListOld.contains(documentoListNewDocumento)) {
                    Serie oldSerieOfDocumentoListNewDocumento = documentoListNewDocumento.getSerie();
                    documentoListNewDocumento.setSerie(serie);
                    documentoListNewDocumento = em.merge(documentoListNewDocumento);
                    if (oldSerieOfDocumentoListNewDocumento != null && !oldSerieOfDocumentoListNewDocumento.equals(serie)) {
                        oldSerieOfDocumentoListNewDocumento.getDocumentoList().remove(documentoListNewDocumento);
                        oldSerieOfDocumentoListNewDocumento = em.merge(oldSerieOfDocumentoListNewDocumento);
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
                subSeccion.getSerieList().remove(serie);
                subSeccion = em.merge(subSeccion);
            }
            List<SubSerie> subSerieList = serie.getSubSerieList();
            for (SubSerie subSerieListSubSerie : subSerieList) {
                subSerieListSubSerie.setSerie(null);
                subSerieListSubSerie = em.merge(subSerieListSubSerie);
            }
            List<Documento> documentoList = serie.getDocumentoList();
            for (Documento documentoListDocumento : documentoList) {
                documentoListDocumento.setSerie(null);
                documentoListDocumento = em.merge(documentoListDocumento);
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
