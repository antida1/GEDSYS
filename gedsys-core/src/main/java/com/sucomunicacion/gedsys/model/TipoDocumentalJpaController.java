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
import com.sucomunicacion.gedsys.entities.TipoDocumental;
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
public class TipoDocumentalJpaController implements Serializable {

    public TipoDocumentalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoDocumental tipoDocumental) throws PreexistingEntityException, Exception {
        if (tipoDocumental.getDocumentoList() == null) {
            tipoDocumental.setDocumentoList(new ArrayList<Documento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Documento> attachedDocumentoList = new ArrayList<Documento>();
            for (Documento documentoListDocumentoToAttach : tipoDocumental.getDocumentoList()) {
                documentoListDocumentoToAttach = em.getReference(documentoListDocumentoToAttach.getClass(), documentoListDocumentoToAttach.getId());
                attachedDocumentoList.add(documentoListDocumentoToAttach);
            }
            tipoDocumental.setDocumentoList(attachedDocumentoList);
            em.persist(tipoDocumental);
            for (Documento documentoListDocumento : tipoDocumental.getDocumentoList()) {
                TipoDocumental oldTipoDocumentalOfDocumentoListDocumento = documentoListDocumento.getTipoDocumental();
                documentoListDocumento.setTipoDocumental(tipoDocumental);
                documentoListDocumento = em.merge(documentoListDocumento);
                if (oldTipoDocumentalOfDocumentoListDocumento != null) {
                    oldTipoDocumentalOfDocumentoListDocumento.getDocumentoList().remove(documentoListDocumento);
                    oldTipoDocumentalOfDocumentoListDocumento = em.merge(oldTipoDocumentalOfDocumentoListDocumento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoDocumental(tipoDocumental.getId()) != null) {
                throw new PreexistingEntityException("TipoDocumental " + tipoDocumental + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoDocumental tipoDocumental) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDocumental persistentTipoDocumental = em.find(TipoDocumental.class, tipoDocumental.getId());
            List<Documento> documentoListOld = persistentTipoDocumental.getDocumentoList();
            List<Documento> documentoListNew = tipoDocumental.getDocumentoList();
            List<Documento> attachedDocumentoListNew = new ArrayList<Documento>();
            for (Documento documentoListNewDocumentoToAttach : documentoListNew) {
                documentoListNewDocumentoToAttach = em.getReference(documentoListNewDocumentoToAttach.getClass(), documentoListNewDocumentoToAttach.getId());
                attachedDocumentoListNew.add(documentoListNewDocumentoToAttach);
            }
            documentoListNew = attachedDocumentoListNew;
            tipoDocumental.setDocumentoList(documentoListNew);
            tipoDocumental = em.merge(tipoDocumental);
            for (Documento documentoListOldDocumento : documentoListOld) {
                if (!documentoListNew.contains(documentoListOldDocumento)) {
                    documentoListOldDocumento.setTipoDocumental(null);
                    documentoListOldDocumento = em.merge(documentoListOldDocumento);
                }
            }
            for (Documento documentoListNewDocumento : documentoListNew) {
                if (!documentoListOld.contains(documentoListNewDocumento)) {
                    TipoDocumental oldTipoDocumentalOfDocumentoListNewDocumento = documentoListNewDocumento.getTipoDocumental();
                    documentoListNewDocumento.setTipoDocumental(tipoDocumental);
                    documentoListNewDocumento = em.merge(documentoListNewDocumento);
                    if (oldTipoDocumentalOfDocumentoListNewDocumento != null && !oldTipoDocumentalOfDocumentoListNewDocumento.equals(tipoDocumental)) {
                        oldTipoDocumentalOfDocumentoListNewDocumento.getDocumentoList().remove(documentoListNewDocumento);
                        oldTipoDocumentalOfDocumentoListNewDocumento = em.merge(oldTipoDocumentalOfDocumentoListNewDocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoDocumental.getId();
                if (findTipoDocumental(id) == null) {
                    throw new NonexistentEntityException("The tipoDocumental with id " + id + " no longer exists.");
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
            TipoDocumental tipoDocumental;
            try {
                tipoDocumental = em.getReference(TipoDocumental.class, id);
                tipoDocumental.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoDocumental with id " + id + " no longer exists.", enfe);
            }
            List<Documento> documentoList = tipoDocumental.getDocumentoList();
            for (Documento documentoListDocumento : documentoList) {
                documentoListDocumento.setTipoDocumental(null);
                documentoListDocumento = em.merge(documentoListDocumento);
            }
            em.remove(tipoDocumental);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoDocumental> findTipoDocumentalEntities() {
        return findTipoDocumentalEntities(true, -1, -1);
    }

    public List<TipoDocumental> findTipoDocumentalEntities(int maxResults, int firstResult) {
        return findTipoDocumentalEntities(false, maxResults, firstResult);
    }

    private List<TipoDocumental> findTipoDocumentalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoDocumental.class));
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

    public TipoDocumental findTipoDocumental(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoDocumental.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoDocumentalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoDocumental> rt = cq.from(TipoDocumental.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
