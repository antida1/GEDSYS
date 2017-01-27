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
import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.Transportador;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.ArrayList;
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
        if (transportador.getDocumentoList() == null) {
            transportador.setDocumentoList(new ArrayList<Documento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Documento> attachedDocumentoList = new ArrayList<Documento>();
            for (Documento documentoListDocumentoToAttach : transportador.getDocumentoList()) {
                documentoListDocumentoToAttach = em.getReference(documentoListDocumentoToAttach.getClass(), documentoListDocumentoToAttach.getId());
                attachedDocumentoList.add(documentoListDocumentoToAttach);
            }
            transportador.setDocumentoList(attachedDocumentoList);
            em.persist(transportador);
            for (Documento documentoListDocumento : transportador.getDocumentoList()) {
                Transportador oldTransportadorOfDocumentoListDocumento = documentoListDocumento.getTransportador();
                documentoListDocumento.setTransportador(transportador);
                documentoListDocumento = em.merge(documentoListDocumento);
                if (oldTransportadorOfDocumentoListDocumento != null) {
                    oldTransportadorOfDocumentoListDocumento.getDocumentoList().remove(documentoListDocumento);
                    oldTransportadorOfDocumentoListDocumento = em.merge(oldTransportadorOfDocumentoListDocumento);
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
            List<Documento> documentoListOld = persistentTransportador.getDocumentoList();
            List<Documento> documentoListNew = transportador.getDocumentoList();
            List<Documento> attachedDocumentoListNew = new ArrayList<Documento>();
            for (Documento documentoListNewDocumentoToAttach : documentoListNew) {
                documentoListNewDocumentoToAttach = em.getReference(documentoListNewDocumentoToAttach.getClass(), documentoListNewDocumentoToAttach.getId());
                attachedDocumentoListNew.add(documentoListNewDocumentoToAttach);
            }
            documentoListNew = attachedDocumentoListNew;
            transportador.setDocumentoList(documentoListNew);
            transportador = em.merge(transportador);
            for (Documento documentoListOldDocumento : documentoListOld) {
                if (!documentoListNew.contains(documentoListOldDocumento)) {
                    documentoListOldDocumento.setTransportador(null);
                    documentoListOldDocumento = em.merge(documentoListOldDocumento);
                }
            }
            for (Documento documentoListNewDocumento : documentoListNew) {
                if (!documentoListOld.contains(documentoListNewDocumento)) {
                    Transportador oldTransportadorOfDocumentoListNewDocumento = documentoListNewDocumento.getTransportador();
                    documentoListNewDocumento.setTransportador(transportador);
                    documentoListNewDocumento = em.merge(documentoListNewDocumento);
                    if (oldTransportadorOfDocumentoListNewDocumento != null && !oldTransportadorOfDocumentoListNewDocumento.equals(transportador)) {
                        oldTransportadorOfDocumentoListNewDocumento.getDocumentoList().remove(documentoListNewDocumento);
                        oldTransportadorOfDocumentoListNewDocumento = em.merge(oldTransportadorOfDocumentoListNewDocumento);
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
            List<Documento> documentoList = transportador.getDocumentoList();
            for (Documento documentoListDocumento : documentoList) {
                documentoListDocumento.setTransportador(null);
                documentoListDocumento = em.merge(documentoListDocumento);
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
