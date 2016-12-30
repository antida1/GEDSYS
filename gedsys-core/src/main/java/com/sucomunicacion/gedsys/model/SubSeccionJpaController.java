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
import com.sucomunicacion.gedsys.entities.Documento;
import java.util.ArrayList;
import java.util.List;
import com.sucomunicacion.gedsys.entities.Serie;
import com.sucomunicacion.gedsys.entities.SubSeccion;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
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
        if (subSeccion.getDocumentoList() == null) {
            subSeccion.setDocumentoList(new ArrayList<Documento>());
        }
        if (subSeccion.getSerieList() == null) {
            subSeccion.setSerieList(new ArrayList<Serie>());
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
            List<Documento> attachedDocumentoList = new ArrayList<Documento>();
            for (Documento documentoListDocumentoToAttach : subSeccion.getDocumentoList()) {
                documentoListDocumentoToAttach = em.getReference(documentoListDocumentoToAttach.getClass(), documentoListDocumentoToAttach.getId());
                attachedDocumentoList.add(documentoListDocumentoToAttach);
            }
            subSeccion.setDocumentoList(attachedDocumentoList);
            List<Serie> attachedSerieList = new ArrayList<Serie>();
            for (Serie serieListSerieToAttach : subSeccion.getSerieList()) {
                serieListSerieToAttach = em.getReference(serieListSerieToAttach.getClass(), serieListSerieToAttach.getId());
                attachedSerieList.add(serieListSerieToAttach);
            }
            subSeccion.setSerieList(attachedSerieList);
            em.persist(subSeccion);
            if (seccion != null) {
                seccion.getSubSeccionList().add(subSeccion);
                seccion = em.merge(seccion);
            }
            for (Documento documentoListDocumento : subSeccion.getDocumentoList()) {
                SubSeccion oldSubSeccionOfDocumentoListDocumento = documentoListDocumento.getSubSeccion();
                documentoListDocumento.setSubSeccion(subSeccion);
                documentoListDocumento = em.merge(documentoListDocumento);
                if (oldSubSeccionOfDocumentoListDocumento != null) {
                    oldSubSeccionOfDocumentoListDocumento.getDocumentoList().remove(documentoListDocumento);
                    oldSubSeccionOfDocumentoListDocumento = em.merge(oldSubSeccionOfDocumentoListDocumento);
                }
            }
            for (Serie serieListSerie : subSeccion.getSerieList()) {
                SubSeccion oldSubSeccionOfSerieListSerie = serieListSerie.getSubSeccion();
                serieListSerie.setSubSeccion(subSeccion);
                serieListSerie = em.merge(serieListSerie);
                if (oldSubSeccionOfSerieListSerie != null) {
                    oldSubSeccionOfSerieListSerie.getSerieList().remove(serieListSerie);
                    oldSubSeccionOfSerieListSerie = em.merge(oldSubSeccionOfSerieListSerie);
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
            List<Documento> documentoListOld = persistentSubSeccion.getDocumentoList();
            List<Documento> documentoListNew = subSeccion.getDocumentoList();
            List<Serie> serieListOld = persistentSubSeccion.getSerieList();
            List<Serie> serieListNew = subSeccion.getSerieList();
            if (seccionNew != null) {
                seccionNew = em.getReference(seccionNew.getClass(), seccionNew.getId());
                subSeccion.setSeccion(seccionNew);
            }
            List<Documento> attachedDocumentoListNew = new ArrayList<Documento>();
            for (Documento documentoListNewDocumentoToAttach : documentoListNew) {
                documentoListNewDocumentoToAttach = em.getReference(documentoListNewDocumentoToAttach.getClass(), documentoListNewDocumentoToAttach.getId());
                attachedDocumentoListNew.add(documentoListNewDocumentoToAttach);
            }
            documentoListNew = attachedDocumentoListNew;
            subSeccion.setDocumentoList(documentoListNew);
            List<Serie> attachedSerieListNew = new ArrayList<Serie>();
            for (Serie serieListNewSerieToAttach : serieListNew) {
                serieListNewSerieToAttach = em.getReference(serieListNewSerieToAttach.getClass(), serieListNewSerieToAttach.getId());
                attachedSerieListNew.add(serieListNewSerieToAttach);
            }
            serieListNew = attachedSerieListNew;
            subSeccion.setSerieList(serieListNew);
            subSeccion = em.merge(subSeccion);
            if (seccionOld != null && !seccionOld.equals(seccionNew)) {
                seccionOld.getSubSeccionList().remove(subSeccion);
                seccionOld = em.merge(seccionOld);
            }
            if (seccionNew != null && !seccionNew.equals(seccionOld)) {
                seccionNew.getSubSeccionList().add(subSeccion);
                seccionNew = em.merge(seccionNew);
            }
            for (Documento documentoListOldDocumento : documentoListOld) {
                if (!documentoListNew.contains(documentoListOldDocumento)) {
                    documentoListOldDocumento.setSubSeccion(null);
                    documentoListOldDocumento = em.merge(documentoListOldDocumento);
                }
            }
            for (Documento documentoListNewDocumento : documentoListNew) {
                if (!documentoListOld.contains(documentoListNewDocumento)) {
                    SubSeccion oldSubSeccionOfDocumentoListNewDocumento = documentoListNewDocumento.getSubSeccion();
                    documentoListNewDocumento.setSubSeccion(subSeccion);
                    documentoListNewDocumento = em.merge(documentoListNewDocumento);
                    if (oldSubSeccionOfDocumentoListNewDocumento != null && !oldSubSeccionOfDocumentoListNewDocumento.equals(subSeccion)) {
                        oldSubSeccionOfDocumentoListNewDocumento.getDocumentoList().remove(documentoListNewDocumento);
                        oldSubSeccionOfDocumentoListNewDocumento = em.merge(oldSubSeccionOfDocumentoListNewDocumento);
                    }
                }
            }
            for (Serie serieListOldSerie : serieListOld) {
                if (!serieListNew.contains(serieListOldSerie)) {
                    serieListOldSerie.setSubSeccion(null);
                    serieListOldSerie = em.merge(serieListOldSerie);
                }
            }
            for (Serie serieListNewSerie : serieListNew) {
                if (!serieListOld.contains(serieListNewSerie)) {
                    SubSeccion oldSubSeccionOfSerieListNewSerie = serieListNewSerie.getSubSeccion();
                    serieListNewSerie.setSubSeccion(subSeccion);
                    serieListNewSerie = em.merge(serieListNewSerie);
                    if (oldSubSeccionOfSerieListNewSerie != null && !oldSubSeccionOfSerieListNewSerie.equals(subSeccion)) {
                        oldSubSeccionOfSerieListNewSerie.getSerieList().remove(serieListNewSerie);
                        oldSubSeccionOfSerieListNewSerie = em.merge(oldSubSeccionOfSerieListNewSerie);
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
                seccion.getSubSeccionList().remove(subSeccion);
                seccion = em.merge(seccion);
            }
            List<Documento> documentoList = subSeccion.getDocumentoList();
            for (Documento documentoListDocumento : documentoList) {
                documentoListDocumento.setSubSeccion(null);
                documentoListDocumento = em.merge(documentoListDocumento);
            }
            List<Serie> serieList = subSeccion.getSerieList();
            for (Serie serieListSerie : serieList) {
                serieListSerie.setSubSeccion(null);
                serieListSerie = em.merge(serieListSerie);
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
