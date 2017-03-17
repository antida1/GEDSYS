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
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.Seccion;
import java.util.ArrayList;
import java.util.Collection;
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
public class SeccionJpaController implements Serializable {

    public SeccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Seccion seccion) throws PreexistingEntityException, Exception {
        if (seccion.getDocumentoCollection() == null) {
            seccion.setDocumentoCollection(new ArrayList<Documento>());
        }
        if (seccion.getSubSeccionCollection() == null) {
            seccion.setSubSeccionCollection(new ArrayList<SubSeccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = seccion.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                seccion.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = seccion.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                seccion.setModificadoPor(modificadoPor);
            }
            Collection<Documento> attachedDocumentoCollection = new ArrayList<Documento>();
            for (Documento documentoCollectionDocumentoToAttach : seccion.getDocumentoCollection()) {
                documentoCollectionDocumentoToAttach = em.getReference(documentoCollectionDocumentoToAttach.getClass(), documentoCollectionDocumentoToAttach.getId());
                attachedDocumentoCollection.add(documentoCollectionDocumentoToAttach);
            }
            seccion.setDocumentoCollection(attachedDocumentoCollection);
            Collection<SubSeccion> attachedSubSeccionCollection = new ArrayList<SubSeccion>();
            for (SubSeccion subSeccionCollectionSubSeccionToAttach : seccion.getSubSeccionCollection()) {
                subSeccionCollectionSubSeccionToAttach = em.getReference(subSeccionCollectionSubSeccionToAttach.getClass(), subSeccionCollectionSubSeccionToAttach.getId());
                attachedSubSeccionCollection.add(subSeccionCollectionSubSeccionToAttach);
            }
            seccion.setSubSeccionCollection(attachedSubSeccionCollection);
            em.persist(seccion);
            if (creadoPor != null) {
                creadoPor.getSeccionCollection().add(seccion);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getSeccionCollection().add(seccion);
                modificadoPor = em.merge(modificadoPor);
            }
            for (Documento documentoCollectionDocumento : seccion.getDocumentoCollection()) {
                Seccion oldSessionOfDocumentoCollectionDocumento = documentoCollectionDocumento.getSession();
                documentoCollectionDocumento.setSession(seccion);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
                if (oldSessionOfDocumentoCollectionDocumento != null) {
                    oldSessionOfDocumentoCollectionDocumento.getDocumentoCollection().remove(documentoCollectionDocumento);
                    oldSessionOfDocumentoCollectionDocumento = em.merge(oldSessionOfDocumentoCollectionDocumento);
                }
            }
            for (SubSeccion subSeccionCollectionSubSeccion : seccion.getSubSeccionCollection()) {
                Seccion oldSeccionOfSubSeccionCollectionSubSeccion = subSeccionCollectionSubSeccion.getSeccion();
                subSeccionCollectionSubSeccion.setSeccion(seccion);
                subSeccionCollectionSubSeccion = em.merge(subSeccionCollectionSubSeccion);
                if (oldSeccionOfSubSeccionCollectionSubSeccion != null) {
                    oldSeccionOfSubSeccionCollectionSubSeccion.getSubSeccionCollection().remove(subSeccionCollectionSubSeccion);
                    oldSeccionOfSubSeccionCollectionSubSeccion = em.merge(oldSeccionOfSubSeccionCollectionSubSeccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSeccion(seccion.getId()) != null) {
                throw new PreexistingEntityException("Seccion " + seccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Seccion seccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Seccion persistentSeccion = em.find(Seccion.class, seccion.getId());
            Usuario creadoPorOld = persistentSeccion.getCreadoPor();
            Usuario creadoPorNew = seccion.getCreadoPor();
            Usuario modificadoPorOld = persistentSeccion.getModificadoPor();
            Usuario modificadoPorNew = seccion.getModificadoPor();
            Collection<Documento> documentoCollectionOld = persistentSeccion.getDocumentoCollection();
            Collection<Documento> documentoCollectionNew = seccion.getDocumentoCollection();
            Collection<SubSeccion> subSeccionCollectionOld = persistentSeccion.getSubSeccionCollection();
            Collection<SubSeccion> subSeccionCollectionNew = seccion.getSubSeccionCollection();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                seccion.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                seccion.setModificadoPor(modificadoPorNew);
            }
            Collection<Documento> attachedDocumentoCollectionNew = new ArrayList<Documento>();
            for (Documento documentoCollectionNewDocumentoToAttach : documentoCollectionNew) {
                documentoCollectionNewDocumentoToAttach = em.getReference(documentoCollectionNewDocumentoToAttach.getClass(), documentoCollectionNewDocumentoToAttach.getId());
                attachedDocumentoCollectionNew.add(documentoCollectionNewDocumentoToAttach);
            }
            documentoCollectionNew = attachedDocumentoCollectionNew;
            seccion.setDocumentoCollection(documentoCollectionNew);
            Collection<SubSeccion> attachedSubSeccionCollectionNew = new ArrayList<SubSeccion>();
            for (SubSeccion subSeccionCollectionNewSubSeccionToAttach : subSeccionCollectionNew) {
                subSeccionCollectionNewSubSeccionToAttach = em.getReference(subSeccionCollectionNewSubSeccionToAttach.getClass(), subSeccionCollectionNewSubSeccionToAttach.getId());
                attachedSubSeccionCollectionNew.add(subSeccionCollectionNewSubSeccionToAttach);
            }
            subSeccionCollectionNew = attachedSubSeccionCollectionNew;
            seccion.setSubSeccionCollection(subSeccionCollectionNew);
            seccion = em.merge(seccion);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getSeccionCollection().remove(seccion);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getSeccionCollection().add(seccion);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getSeccionCollection().remove(seccion);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getSeccionCollection().add(seccion);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            for (Documento documentoCollectionOldDocumento : documentoCollectionOld) {
                if (!documentoCollectionNew.contains(documentoCollectionOldDocumento)) {
                    documentoCollectionOldDocumento.setSession(null);
                    documentoCollectionOldDocumento = em.merge(documentoCollectionOldDocumento);
                }
            }
            for (Documento documentoCollectionNewDocumento : documentoCollectionNew) {
                if (!documentoCollectionOld.contains(documentoCollectionNewDocumento)) {
                    Seccion oldSessionOfDocumentoCollectionNewDocumento = documentoCollectionNewDocumento.getSession();
                    documentoCollectionNewDocumento.setSession(seccion);
                    documentoCollectionNewDocumento = em.merge(documentoCollectionNewDocumento);
                    if (oldSessionOfDocumentoCollectionNewDocumento != null && !oldSessionOfDocumentoCollectionNewDocumento.equals(seccion)) {
                        oldSessionOfDocumentoCollectionNewDocumento.getDocumentoCollection().remove(documentoCollectionNewDocumento);
                        oldSessionOfDocumentoCollectionNewDocumento = em.merge(oldSessionOfDocumentoCollectionNewDocumento);
                    }
                }
            }
            for (SubSeccion subSeccionCollectionOldSubSeccion : subSeccionCollectionOld) {
                if (!subSeccionCollectionNew.contains(subSeccionCollectionOldSubSeccion)) {
                    subSeccionCollectionOldSubSeccion.setSeccion(null);
                    subSeccionCollectionOldSubSeccion = em.merge(subSeccionCollectionOldSubSeccion);
                }
            }
            for (SubSeccion subSeccionCollectionNewSubSeccion : subSeccionCollectionNew) {
                if (!subSeccionCollectionOld.contains(subSeccionCollectionNewSubSeccion)) {
                    Seccion oldSeccionOfSubSeccionCollectionNewSubSeccion = subSeccionCollectionNewSubSeccion.getSeccion();
                    subSeccionCollectionNewSubSeccion.setSeccion(seccion);
                    subSeccionCollectionNewSubSeccion = em.merge(subSeccionCollectionNewSubSeccion);
                    if (oldSeccionOfSubSeccionCollectionNewSubSeccion != null && !oldSeccionOfSubSeccionCollectionNewSubSeccion.equals(seccion)) {
                        oldSeccionOfSubSeccionCollectionNewSubSeccion.getSubSeccionCollection().remove(subSeccionCollectionNewSubSeccion);
                        oldSeccionOfSubSeccionCollectionNewSubSeccion = em.merge(oldSeccionOfSubSeccionCollectionNewSubSeccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = seccion.getId();
                if (findSeccion(id) == null) {
                    throw new NonexistentEntityException("The seccion with id " + id + " no longer exists.");
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
            Seccion seccion;
            try {
                seccion = em.getReference(Seccion.class, id);
                seccion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seccion with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = seccion.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getSeccionCollection().remove(seccion);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = seccion.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getSeccionCollection().remove(seccion);
                modificadoPor = em.merge(modificadoPor);
            }
            Collection<Documento> documentoCollection = seccion.getDocumentoCollection();
            for (Documento documentoCollectionDocumento : documentoCollection) {
                documentoCollectionDocumento.setSession(null);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
            }
            Collection<SubSeccion> subSeccionCollection = seccion.getSubSeccionCollection();
            for (SubSeccion subSeccionCollectionSubSeccion : subSeccionCollection) {
                subSeccionCollectionSubSeccion.setSeccion(null);
                subSeccionCollectionSubSeccion = em.merge(subSeccionCollectionSubSeccion);
            }
            em.remove(seccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Seccion> findSeccionEntities() {
        return findSeccionEntities(true, -1, -1);
    }

    public List<Seccion> findSeccionEntities(int maxResults, int firstResult) {
        return findSeccionEntities(false, maxResults, firstResult);
    }

    private List<Seccion> findSeccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Seccion.class));
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

    public Seccion findSeccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Seccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Seccion> rt = cq.from(Seccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
