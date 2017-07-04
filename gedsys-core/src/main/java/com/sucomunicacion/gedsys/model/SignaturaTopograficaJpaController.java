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
import com.sucomunicacion.gedsys.entities.SignaturaTopografica;
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
public class SignaturaTopograficaJpaController implements Serializable {

    public SignaturaTopograficaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SignaturaTopografica signaturaTopografica) {
        if (signaturaTopografica.getDocumentoCollection() == null) {
            signaturaTopografica.setDocumentoCollection(new ArrayList<Documento>());
        }
        if (signaturaTopografica.getSignaturaTopograficaCollection() == null) {
            signaturaTopografica.setSignaturaTopograficaCollection(new ArrayList<SignaturaTopografica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = signaturaTopografica.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                signaturaTopografica.setCreadoPor(creadoPor);
            }
            SignaturaTopografica dependeDe = signaturaTopografica.getDependeDe();
            if (dependeDe != null) {
                dependeDe = em.getReference(dependeDe.getClass(), dependeDe.getId());
                signaturaTopografica.setDependeDe(dependeDe);
            }
            Usuario modificadoPor = signaturaTopografica.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                signaturaTopografica.setModificadoPor(modificadoPor);
            }
            Collection<Documento> attachedDocumentoCollection = new ArrayList<Documento>();
            for (Documento documentoCollectionDocumentoToAttach : signaturaTopografica.getDocumentoCollection()) {
                documentoCollectionDocumentoToAttach = em.getReference(documentoCollectionDocumentoToAttach.getClass(), documentoCollectionDocumentoToAttach.getId());
                attachedDocumentoCollection.add(documentoCollectionDocumentoToAttach);
            }
            signaturaTopografica.setDocumentoCollection(attachedDocumentoCollection);
            Collection<SignaturaTopografica> attachedSignaturaTopograficaCollection = new ArrayList<SignaturaTopografica>();
            for (SignaturaTopografica signaturaTopograficaCollectionSignaturaTopograficaToAttach : signaturaTopografica.getSignaturaTopograficaCollection()) {
                signaturaTopograficaCollectionSignaturaTopograficaToAttach = em.getReference(signaturaTopograficaCollectionSignaturaTopograficaToAttach.getClass(), signaturaTopograficaCollectionSignaturaTopograficaToAttach.getId());
                attachedSignaturaTopograficaCollection.add(signaturaTopograficaCollectionSignaturaTopograficaToAttach);
            }
            signaturaTopografica.setSignaturaTopograficaCollection(attachedSignaturaTopograficaCollection);
            em.persist(signaturaTopografica);
            if (creadoPor != null) {
                creadoPor.getSignaturaTopograficaCollection().add(signaturaTopografica);
                creadoPor = em.merge(creadoPor);
            }
            if (dependeDe != null) {
                dependeDe.getSignaturaTopograficaCollection().add(signaturaTopografica);
                dependeDe = em.merge(dependeDe);
            }
            if (modificadoPor != null) {
                modificadoPor.getSignaturaTopograficaCollection().add(signaturaTopografica);
                modificadoPor = em.merge(modificadoPor);
            }
            for (Documento documentoCollectionDocumento : signaturaTopografica.getDocumentoCollection()) {
                SignaturaTopografica oldSignaturaTopograficaOfDocumentoCollectionDocumento = documentoCollectionDocumento.getSignaturaTopografica();
                documentoCollectionDocumento.setSignaturaTopografica(signaturaTopografica);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
                if (oldSignaturaTopograficaOfDocumentoCollectionDocumento != null) {
                    oldSignaturaTopograficaOfDocumentoCollectionDocumento.getDocumentoCollection().remove(documentoCollectionDocumento);
                    oldSignaturaTopograficaOfDocumentoCollectionDocumento = em.merge(oldSignaturaTopograficaOfDocumentoCollectionDocumento);
                }
            }
            for (SignaturaTopografica signaturaTopograficaCollectionSignaturaTopografica : signaturaTopografica.getSignaturaTopograficaCollection()) {
                SignaturaTopografica oldDependeDeOfSignaturaTopograficaCollectionSignaturaTopografica = signaturaTopograficaCollectionSignaturaTopografica.getDependeDe();
                signaturaTopograficaCollectionSignaturaTopografica.setDependeDe(signaturaTopografica);
                signaturaTopograficaCollectionSignaturaTopografica = em.merge(signaturaTopograficaCollectionSignaturaTopografica);
                if (oldDependeDeOfSignaturaTopograficaCollectionSignaturaTopografica != null) {
                    oldDependeDeOfSignaturaTopograficaCollectionSignaturaTopografica.getSignaturaTopograficaCollection().remove(signaturaTopograficaCollectionSignaturaTopografica);
                    oldDependeDeOfSignaturaTopograficaCollectionSignaturaTopografica = em.merge(oldDependeDeOfSignaturaTopograficaCollectionSignaturaTopografica);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SignaturaTopografica signaturaTopografica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SignaturaTopografica persistentSignaturaTopografica = em.find(SignaturaTopografica.class, signaturaTopografica.getId());
            Usuario creadoPorOld = persistentSignaturaTopografica.getCreadoPor();
            Usuario creadoPorNew = signaturaTopografica.getCreadoPor();
            SignaturaTopografica dependeDeOld = persistentSignaturaTopografica.getDependeDe();
            SignaturaTopografica dependeDeNew = signaturaTopografica.getDependeDe();
            Usuario modificadoPorOld = persistentSignaturaTopografica.getModificadoPor();
            Usuario modificadoPorNew = signaturaTopografica.getModificadoPor();
            Collection<Documento> documentoCollectionOld = persistentSignaturaTopografica.getDocumentoCollection();
            Collection<Documento> documentoCollectionNew = signaturaTopografica.getDocumentoCollection();
            Collection<SignaturaTopografica> signaturaTopograficaCollectionOld = persistentSignaturaTopografica.getSignaturaTopograficaCollection();
            Collection<SignaturaTopografica> signaturaTopograficaCollectionNew = signaturaTopografica.getSignaturaTopograficaCollection();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                signaturaTopografica.setCreadoPor(creadoPorNew);
            }
            if (dependeDeNew != null) {
                dependeDeNew = em.getReference(dependeDeNew.getClass(), dependeDeNew.getId());
                signaturaTopografica.setDependeDe(dependeDeNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                signaturaTopografica.setModificadoPor(modificadoPorNew);
            }
            Collection<Documento> attachedDocumentoCollectionNew = new ArrayList<Documento>();
            for (Documento documentoCollectionNewDocumentoToAttach : documentoCollectionNew) {
                documentoCollectionNewDocumentoToAttach = em.getReference(documentoCollectionNewDocumentoToAttach.getClass(), documentoCollectionNewDocumentoToAttach.getId());
                attachedDocumentoCollectionNew.add(documentoCollectionNewDocumentoToAttach);
            }
            documentoCollectionNew = attachedDocumentoCollectionNew;
            signaturaTopografica.setDocumentoCollection(documentoCollectionNew);
            Collection<SignaturaTopografica> attachedSignaturaTopograficaCollectionNew = new ArrayList<SignaturaTopografica>();
            for (SignaturaTopografica signaturaTopograficaCollectionNewSignaturaTopograficaToAttach : signaturaTopograficaCollectionNew) {
                signaturaTopograficaCollectionNewSignaturaTopograficaToAttach = em.getReference(signaturaTopograficaCollectionNewSignaturaTopograficaToAttach.getClass(), signaturaTopograficaCollectionNewSignaturaTopograficaToAttach.getId());
                attachedSignaturaTopograficaCollectionNew.add(signaturaTopograficaCollectionNewSignaturaTopograficaToAttach);
            }
            signaturaTopograficaCollectionNew = attachedSignaturaTopograficaCollectionNew;
            signaturaTopografica.setSignaturaTopograficaCollection(signaturaTopograficaCollectionNew);
            signaturaTopografica = em.merge(signaturaTopografica);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getSignaturaTopograficaCollection().remove(signaturaTopografica);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getSignaturaTopograficaCollection().add(signaturaTopografica);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (dependeDeOld != null && !dependeDeOld.equals(dependeDeNew)) {
                dependeDeOld.getSignaturaTopograficaCollection().remove(signaturaTopografica);
                dependeDeOld = em.merge(dependeDeOld);
            }
            if (dependeDeNew != null && !dependeDeNew.equals(dependeDeOld)) {
                dependeDeNew.getSignaturaTopograficaCollection().add(signaturaTopografica);
                dependeDeNew = em.merge(dependeDeNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getSignaturaTopograficaCollection().remove(signaturaTopografica);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getSignaturaTopograficaCollection().add(signaturaTopografica);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            for (Documento documentoCollectionOldDocumento : documentoCollectionOld) {
                if (!documentoCollectionNew.contains(documentoCollectionOldDocumento)) {
                    documentoCollectionOldDocumento.setSignaturaTopografica(null);
                    documentoCollectionOldDocumento = em.merge(documentoCollectionOldDocumento);
                }
            }
            for (Documento documentoCollectionNewDocumento : documentoCollectionNew) {
                if (!documentoCollectionOld.contains(documentoCollectionNewDocumento)) {
                    SignaturaTopografica oldSignaturaTopograficaOfDocumentoCollectionNewDocumento = documentoCollectionNewDocumento.getSignaturaTopografica();
                    documentoCollectionNewDocumento.setSignaturaTopografica(signaturaTopografica);
                    documentoCollectionNewDocumento = em.merge(documentoCollectionNewDocumento);
                    if (oldSignaturaTopograficaOfDocumentoCollectionNewDocumento != null && !oldSignaturaTopograficaOfDocumentoCollectionNewDocumento.equals(signaturaTopografica)) {
                        oldSignaturaTopograficaOfDocumentoCollectionNewDocumento.getDocumentoCollection().remove(documentoCollectionNewDocumento);
                        oldSignaturaTopograficaOfDocumentoCollectionNewDocumento = em.merge(oldSignaturaTopograficaOfDocumentoCollectionNewDocumento);
                    }
                }
            }
            for (SignaturaTopografica signaturaTopograficaCollectionOldSignaturaTopografica : signaturaTopograficaCollectionOld) {
                if (!signaturaTopograficaCollectionNew.contains(signaturaTopograficaCollectionOldSignaturaTopografica)) {
                    signaturaTopograficaCollectionOldSignaturaTopografica.setDependeDe(null);
                    signaturaTopograficaCollectionOldSignaturaTopografica = em.merge(signaturaTopograficaCollectionOldSignaturaTopografica);
                }
            }
            for (SignaturaTopografica signaturaTopograficaCollectionNewSignaturaTopografica : signaturaTopograficaCollectionNew) {
                if (!signaturaTopograficaCollectionOld.contains(signaturaTopograficaCollectionNewSignaturaTopografica)) {
                    SignaturaTopografica oldDependeDeOfSignaturaTopograficaCollectionNewSignaturaTopografica = signaturaTopograficaCollectionNewSignaturaTopografica.getDependeDe();
                    signaturaTopograficaCollectionNewSignaturaTopografica.setDependeDe(signaturaTopografica);
                    signaturaTopograficaCollectionNewSignaturaTopografica = em.merge(signaturaTopograficaCollectionNewSignaturaTopografica);
                    if (oldDependeDeOfSignaturaTopograficaCollectionNewSignaturaTopografica != null && !oldDependeDeOfSignaturaTopograficaCollectionNewSignaturaTopografica.equals(signaturaTopografica)) {
                        oldDependeDeOfSignaturaTopograficaCollectionNewSignaturaTopografica.getSignaturaTopograficaCollection().remove(signaturaTopograficaCollectionNewSignaturaTopografica);
                        oldDependeDeOfSignaturaTopograficaCollectionNewSignaturaTopografica = em.merge(oldDependeDeOfSignaturaTopograficaCollectionNewSignaturaTopografica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = signaturaTopografica.getId();
                if (findSignaturaTopografica(id) == null) {
                    throw new NonexistentEntityException("The signaturaTopografica with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SignaturaTopografica signaturaTopografica;
            try {
                signaturaTopografica = em.getReference(SignaturaTopografica.class, id);
                signaturaTopografica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The signaturaTopografica with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = signaturaTopografica.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getSignaturaTopograficaCollection().remove(signaturaTopografica);
                creadoPor = em.merge(creadoPor);
            }
            SignaturaTopografica dependeDe = signaturaTopografica.getDependeDe();
            if (dependeDe != null) {
                dependeDe.getSignaturaTopograficaCollection().remove(signaturaTopografica);
                dependeDe = em.merge(dependeDe);
            }
            Usuario modificadoPor = signaturaTopografica.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getSignaturaTopograficaCollection().remove(signaturaTopografica);
                modificadoPor = em.merge(modificadoPor);
            }
            Collection<Documento> documentoCollection = signaturaTopografica.getDocumentoCollection();
            for (Documento documentoCollectionDocumento : documentoCollection) {
                documentoCollectionDocumento.setSignaturaTopografica(null);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
            }
            Collection<SignaturaTopografica> signaturaTopograficaCollection = signaturaTopografica.getSignaturaTopograficaCollection();
            for (SignaturaTopografica signaturaTopograficaCollectionSignaturaTopografica : signaturaTopograficaCollection) {
                signaturaTopograficaCollectionSignaturaTopografica.setDependeDe(null);
                signaturaTopograficaCollectionSignaturaTopografica = em.merge(signaturaTopograficaCollectionSignaturaTopografica);
            }
            em.remove(signaturaTopografica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SignaturaTopografica> findSignaturaTopograficaEntities() {
        return findSignaturaTopograficaEntities(true, -1, -1);
    }

    public List<SignaturaTopografica> findSignaturaTopograficaEntities(int maxResults, int firstResult) {
        return findSignaturaTopograficaEntities(false, maxResults, firstResult);
    }

    private List<SignaturaTopografica> findSignaturaTopograficaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SignaturaTopografica.class));
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

    public SignaturaTopografica findSignaturaTopografica(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SignaturaTopografica.class, id);
        } finally {
            em.close();
        }
    }

    public int getSignaturaTopograficaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SignaturaTopografica> rt = cq.from(SignaturaTopografica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<SignaturaTopografica> findSignaturaTopograficaByDependencia(SignaturaTopografica signatura) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SignaturaTopografica.class));
            Query q = em.createNamedQuery("SignaturaTopografica.findByDependeDe", SignaturaTopografica.class)
                    .setParameter("dependeDe", signatura);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<SignaturaTopografica> findSignaturaTopograficaRoots() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SignaturaTopografica.class));
            Query q = em.createNamedQuery("SignaturaTopografica.findRoots", SignaturaTopografica.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
