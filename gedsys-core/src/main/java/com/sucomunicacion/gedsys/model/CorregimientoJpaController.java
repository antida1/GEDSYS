/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.model;

import com.sucomunicacion.gedsys.entities.Corregimiento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.entities.Municipio;
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
public class CorregimientoJpaController implements Serializable {

    public CorregimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Corregimiento corregimiento) {
        if (corregimiento.getDocumentoCollection() == null) {
            corregimiento.setDocumentoCollection(new ArrayList<Documento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = corregimiento.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                corregimiento.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = corregimiento.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                corregimiento.setModificadoPor(modificadoPor);
            }
            Municipio municipio = corregimiento.getMunicipio();
            if (municipio != null) {
                municipio = em.getReference(municipio.getClass(), municipio.getId());
                corregimiento.setMunicipio(municipio);
            }
            Collection<Documento> attachedDocumentoCollection = new ArrayList<Documento>();
            for (Documento documentoCollectionDocumentoToAttach : corregimiento.getDocumentoCollection()) {
                documentoCollectionDocumentoToAttach = em.getReference(documentoCollectionDocumentoToAttach.getClass(), documentoCollectionDocumentoToAttach.getId());
                attachedDocumentoCollection.add(documentoCollectionDocumentoToAttach);
            }
            corregimiento.setDocumentoCollection(attachedDocumentoCollection);
            em.persist(corregimiento);
            if (creadoPor != null) {
                creadoPor.getCorregimientoCollection().add(corregimiento);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getCorregimientoCollection().add(corregimiento);
                modificadoPor = em.merge(modificadoPor);
            }
            if (municipio != null) {
                municipio.getCorregimientoCollection().add(corregimiento);
                municipio = em.merge(municipio);
            }
            for (Documento documentoCollectionDocumento : corregimiento.getDocumentoCollection()) {
                Corregimiento oldCorregimientoOfDocumentoCollectionDocumento = documentoCollectionDocumento.getCorregimiento();
                documentoCollectionDocumento.setCorregimiento(corregimiento);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
                if (oldCorregimientoOfDocumentoCollectionDocumento != null) {
                    oldCorregimientoOfDocumentoCollectionDocumento.getDocumentoCollection().remove(documentoCollectionDocumento);
                    oldCorregimientoOfDocumentoCollectionDocumento = em.merge(oldCorregimientoOfDocumentoCollectionDocumento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Corregimiento corregimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Corregimiento persistentCorregimiento = em.find(Corregimiento.class, corregimiento.getId());
            Usuario creadoPorOld = persistentCorregimiento.getCreadoPor();
            Usuario creadoPorNew = corregimiento.getCreadoPor();
            Usuario modificadoPorOld = persistentCorregimiento.getModificadoPor();
            Usuario modificadoPorNew = corregimiento.getModificadoPor();
            Municipio municipioOld = persistentCorregimiento.getMunicipio();
            Municipio municipioNew = corregimiento.getMunicipio();
            Collection<Documento> documentoCollectionOld = persistentCorregimiento.getDocumentoCollection();
            Collection<Documento> documentoCollectionNew = corregimiento.getDocumentoCollection();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                corregimiento.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                corregimiento.setModificadoPor(modificadoPorNew);
            }
            if (municipioNew != null) {
                municipioNew = em.getReference(municipioNew.getClass(), municipioNew.getId());
                corregimiento.setMunicipio(municipioNew);
            }
            Collection<Documento> attachedDocumentoCollectionNew = new ArrayList<Documento>();
            for (Documento documentoCollectionNewDocumentoToAttach : documentoCollectionNew) {
                documentoCollectionNewDocumentoToAttach = em.getReference(documentoCollectionNewDocumentoToAttach.getClass(), documentoCollectionNewDocumentoToAttach.getId());
                attachedDocumentoCollectionNew.add(documentoCollectionNewDocumentoToAttach);
            }
            documentoCollectionNew = attachedDocumentoCollectionNew;
            corregimiento.setDocumentoCollection(documentoCollectionNew);
            corregimiento = em.merge(corregimiento);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getCorregimientoCollection().remove(corregimiento);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getCorregimientoCollection().add(corregimiento);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getCorregimientoCollection().remove(corregimiento);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getCorregimientoCollection().add(corregimiento);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            if (municipioOld != null && !municipioOld.equals(municipioNew)) {
                municipioOld.getCorregimientoCollection().remove(corregimiento);
                municipioOld = em.merge(municipioOld);
            }
            if (municipioNew != null && !municipioNew.equals(municipioOld)) {
                municipioNew.getCorregimientoCollection().add(corregimiento);
                municipioNew = em.merge(municipioNew);
            }
            for (Documento documentoCollectionOldDocumento : documentoCollectionOld) {
                if (!documentoCollectionNew.contains(documentoCollectionOldDocumento)) {
                    documentoCollectionOldDocumento.setCorregimiento(null);
                    documentoCollectionOldDocumento = em.merge(documentoCollectionOldDocumento);
                }
            }
            for (Documento documentoCollectionNewDocumento : documentoCollectionNew) {
                if (!documentoCollectionOld.contains(documentoCollectionNewDocumento)) {
                    Corregimiento oldCorregimientoOfDocumentoCollectionNewDocumento = documentoCollectionNewDocumento.getCorregimiento();
                    documentoCollectionNewDocumento.setCorregimiento(corregimiento);
                    documentoCollectionNewDocumento = em.merge(documentoCollectionNewDocumento);
                    if (oldCorregimientoOfDocumentoCollectionNewDocumento != null && !oldCorregimientoOfDocumentoCollectionNewDocumento.equals(corregimiento)) {
                        oldCorregimientoOfDocumentoCollectionNewDocumento.getDocumentoCollection().remove(documentoCollectionNewDocumento);
                        oldCorregimientoOfDocumentoCollectionNewDocumento = em.merge(oldCorregimientoOfDocumentoCollectionNewDocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = corregimiento.getId();
                if (findCorregimiento(id) == null) {
                    throw new NonexistentEntityException("The corregimiento with id " + id + " no longer exists.");
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
            Corregimiento corregimiento;
            try {
                corregimiento = em.getReference(Corregimiento.class, id);
                corregimiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The corregimiento with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = corregimiento.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getCorregimientoCollection().remove(corregimiento);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = corregimiento.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getCorregimientoCollection().remove(corregimiento);
                modificadoPor = em.merge(modificadoPor);
            }
            Municipio municipio = corregimiento.getMunicipio();
            if (municipio != null) {
                municipio.getCorregimientoCollection().remove(corregimiento);
                municipio = em.merge(municipio);
            }
            Collection<Documento> documentoCollection = corregimiento.getDocumentoCollection();
            for (Documento documentoCollectionDocumento : documentoCollection) {
                documentoCollectionDocumento.setCorregimiento(null);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
            }
            em.remove(corregimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Corregimiento> findCorregimientoEntities() {
        return findCorregimientoEntities(true, -1, -1);
    }

    public List<Corregimiento> findCorregimientoEntities(int maxResults, int firstResult) {
        return findCorregimientoEntities(false, maxResults, firstResult);
    }

    private List<Corregimiento> findCorregimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Corregimiento.class));
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

    public Corregimiento findCorregimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Corregimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getCorregimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Corregimiento> rt = cq.from(Corregimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
