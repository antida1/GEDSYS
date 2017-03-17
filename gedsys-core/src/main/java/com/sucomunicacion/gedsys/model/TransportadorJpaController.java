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
import com.sucomunicacion.gedsys.entities.Transportador;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class TransportadorJpaController implements Serializable {

    public TransportadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Transportador transportador) throws PreexistingEntityException, Exception {
        if (transportador.getDocumentoCollection() == null) {
            transportador.setDocumentoCollection(new ArrayList<Documento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = transportador.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                transportador.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = transportador.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                transportador.setModificadoPor(modificadoPor);
            }
            Collection<Documento> attachedDocumentoCollection = new ArrayList<Documento>();
            for (Documento documentoCollectionDocumentoToAttach : transportador.getDocumentoCollection()) {
                documentoCollectionDocumentoToAttach = em.getReference(documentoCollectionDocumentoToAttach.getClass(), documentoCollectionDocumentoToAttach.getId());
                attachedDocumentoCollection.add(documentoCollectionDocumentoToAttach);
            }
            transportador.setDocumentoCollection(attachedDocumentoCollection);
            em.persist(transportador);
            if (creadoPor != null) {
                creadoPor.getTransportadorCollection().add(transportador);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getTransportadorCollection().add(transportador);
                modificadoPor = em.merge(modificadoPor);
            }
            for (Documento documentoCollectionDocumento : transportador.getDocumentoCollection()) {
                Transportador oldTransportadorOfDocumentoCollectionDocumento = documentoCollectionDocumento.getTransportador();
                documentoCollectionDocumento.setTransportador(transportador);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
                if (oldTransportadorOfDocumentoCollectionDocumento != null) {
                    oldTransportadorOfDocumentoCollectionDocumento.getDocumentoCollection().remove(documentoCollectionDocumento);
                    oldTransportadorOfDocumentoCollectionDocumento = em.merge(oldTransportadorOfDocumentoCollectionDocumento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTransportador(transportador.getId()) != null) {
                throw new PreexistingEntityException("Transportador " + transportador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Transportador transportador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transportador persistentTransportador = em.find(Transportador.class, transportador.getId());
            Usuario creadoPorOld = persistentTransportador.getCreadoPor();
            Usuario creadoPorNew = transportador.getCreadoPor();
            Usuario modificadoPorOld = persistentTransportador.getModificadoPor();
            Usuario modificadoPorNew = transportador.getModificadoPor();
            Collection<Documento> documentoCollectionOld = persistentTransportador.getDocumentoCollection();
            Collection<Documento> documentoCollectionNew = transportador.getDocumentoCollection();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                transportador.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                transportador.setModificadoPor(modificadoPorNew);
            }
            Collection<Documento> attachedDocumentoCollectionNew = new ArrayList<Documento>();
            for (Documento documentoCollectionNewDocumentoToAttach : documentoCollectionNew) {
                documentoCollectionNewDocumentoToAttach = em.getReference(documentoCollectionNewDocumentoToAttach.getClass(), documentoCollectionNewDocumentoToAttach.getId());
                attachedDocumentoCollectionNew.add(documentoCollectionNewDocumentoToAttach);
            }
            documentoCollectionNew = attachedDocumentoCollectionNew;
            transportador.setDocumentoCollection(documentoCollectionNew);
            transportador = em.merge(transportador);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getTransportadorCollection().remove(transportador);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getTransportadorCollection().add(transportador);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getTransportadorCollection().remove(transportador);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getTransportadorCollection().add(transportador);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            for (Documento documentoCollectionOldDocumento : documentoCollectionOld) {
                if (!documentoCollectionNew.contains(documentoCollectionOldDocumento)) {
                    documentoCollectionOldDocumento.setTransportador(null);
                    documentoCollectionOldDocumento = em.merge(documentoCollectionOldDocumento);
                }
            }
            for (Documento documentoCollectionNewDocumento : documentoCollectionNew) {
                if (!documentoCollectionOld.contains(documentoCollectionNewDocumento)) {
                    Transportador oldTransportadorOfDocumentoCollectionNewDocumento = documentoCollectionNewDocumento.getTransportador();
                    documentoCollectionNewDocumento.setTransportador(transportador);
                    documentoCollectionNewDocumento = em.merge(documentoCollectionNewDocumento);
                    if (oldTransportadorOfDocumentoCollectionNewDocumento != null && !oldTransportadorOfDocumentoCollectionNewDocumento.equals(transportador)) {
                        oldTransportadorOfDocumentoCollectionNewDocumento.getDocumentoCollection().remove(documentoCollectionNewDocumento);
                        oldTransportadorOfDocumentoCollectionNewDocumento = em.merge(oldTransportadorOfDocumentoCollectionNewDocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = transportador.getId();
                if (findTransportador(id) == null) {
                    throw new NonexistentEntityException("The transportador with id " + id + " no longer exists.");
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
            Transportador transportador;
            try {
                transportador = em.getReference(Transportador.class, id);
                transportador.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transportador with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = transportador.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getTransportadorCollection().remove(transportador);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = transportador.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getTransportadorCollection().remove(transportador);
                modificadoPor = em.merge(modificadoPor);
            }
            Collection<Documento> documentoCollection = transportador.getDocumentoCollection();
            for (Documento documentoCollectionDocumento : documentoCollection) {
                documentoCollectionDocumento.setTransportador(null);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
            }
            em.remove(transportador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Transportador> findTransportadorEntities() {
        return findTransportadorEntities(true, -1, -1);
    }

    public List<Transportador> findTransportadorEntities(int maxResults, int firstResult) {
        return findTransportadorEntities(false, maxResults, firstResult);
    }

    private List<Transportador> findTransportadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Transportador.class));
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

    public Transportador findTransportador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Transportador.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransportadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Transportador> rt = cq.from(Transportador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
