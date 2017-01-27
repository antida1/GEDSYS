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
import com.sucomunicacion.gedsys.entities.TipoDocumento;
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
public class TipoDocumentoJpaController implements Serializable {

    public TipoDocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoDocumento tipoDocumento) throws PreexistingEntityException, Exception {
        if (tipoDocumento.getDocumentoList() == null) {
            tipoDocumento.setDocumentoList(new ArrayList<Documento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Documento> attachedDocumentoList = new ArrayList<Documento>();
            for (Documento documentoListDocumentoToAttach : tipoDocumento.getDocumentoList()) {
                documentoListDocumentoToAttach = em.getReference(documentoListDocumentoToAttach.getClass(), documentoListDocumentoToAttach.getId());
                attachedDocumentoList.add(documentoListDocumentoToAttach);
            }
            tipoDocumento.setDocumentoList(attachedDocumentoList);
            em.persist(tipoDocumento);
            for (Documento documentoListDocumento : tipoDocumento.getDocumentoList()) {
                TipoDocumento oldTipoDocumentoOfDocumentoListDocumento = documentoListDocumento.getTipoDocumento();
                documentoListDocumento.setTipoDocumento(tipoDocumento);
                documentoListDocumento = em.merge(documentoListDocumento);
                if (oldTipoDocumentoOfDocumentoListDocumento != null) {
                    oldTipoDocumentoOfDocumentoListDocumento.getDocumentoList().remove(documentoListDocumento);
                    oldTipoDocumentoOfDocumentoListDocumento = em.merge(oldTipoDocumentoOfDocumentoListDocumento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoDocumento(tipoDocumento.getId()) != null) {
                throw new PreexistingEntityException("TipoDocumento " + tipoDocumento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoDocumento tipoDocumento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDocumento persistentTipoDocumento = em.find(TipoDocumento.class, tipoDocumento.getId());
            List<Documento> documentoListOld = persistentTipoDocumento.getDocumentoList();
            List<Documento> documentoListNew = tipoDocumento.getDocumentoList();
            List<Documento> attachedDocumentoListNew = new ArrayList<Documento>();
            for (Documento documentoListNewDocumentoToAttach : documentoListNew) {
                documentoListNewDocumentoToAttach = em.getReference(documentoListNewDocumentoToAttach.getClass(), documentoListNewDocumentoToAttach.getId());
                attachedDocumentoListNew.add(documentoListNewDocumentoToAttach);
            }
            documentoListNew = attachedDocumentoListNew;
            tipoDocumento.setDocumentoList(documentoListNew);
            tipoDocumento = em.merge(tipoDocumento);
            for (Documento documentoListOldDocumento : documentoListOld) {
                if (!documentoListNew.contains(documentoListOldDocumento)) {
                    documentoListOldDocumento.setTipoDocumento(null);
                    documentoListOldDocumento = em.merge(documentoListOldDocumento);
                }
            }
            for (Documento documentoListNewDocumento : documentoListNew) {
                if (!documentoListOld.contains(documentoListNewDocumento)) {
                    TipoDocumento oldTipoDocumentoOfDocumentoListNewDocumento = documentoListNewDocumento.getTipoDocumento();
                    documentoListNewDocumento.setTipoDocumento(tipoDocumento);
                    documentoListNewDocumento = em.merge(documentoListNewDocumento);
                    if (oldTipoDocumentoOfDocumentoListNewDocumento != null && !oldTipoDocumentoOfDocumentoListNewDocumento.equals(tipoDocumento)) {
                        oldTipoDocumentoOfDocumentoListNewDocumento.getDocumentoList().remove(documentoListNewDocumento);
                        oldTipoDocumentoOfDocumentoListNewDocumento = em.merge(oldTipoDocumentoOfDocumentoListNewDocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoDocumento.getId();
                if (findTipoDocumento(id) == null) {
                    throw new NonexistentEntityException("The tipoDocumento with id " + id + " no longer exists.");
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
            TipoDocumento tipoDocumento;
            try {
                tipoDocumento = em.getReference(TipoDocumento.class, id);
                tipoDocumento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoDocumento with id " + id + " no longer exists.", enfe);
            }
            List<Documento> documentoList = tipoDocumento.getDocumentoList();
            for (Documento documentoListDocumento : documentoList) {
                documentoListDocumento.setTipoDocumento(null);
                documentoListDocumento = em.merge(documentoListDocumento);
            }
            em.remove(tipoDocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoDocumento> findTipoDocumentoEntities() {
        return findTipoDocumentoEntities(true, -1, -1);
    }

    public List<TipoDocumento> findTipoDocumentoEntities(int maxResults, int firstResult) {
        return findTipoDocumentoEntities(false, maxResults, firstResult);
    }

    private List<TipoDocumento> findTipoDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoDocumento.class));
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

    public TipoDocumento findTipoDocumento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoDocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoDocumento> rt = cq.from(TipoDocumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
