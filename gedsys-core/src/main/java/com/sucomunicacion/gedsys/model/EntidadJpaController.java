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
import com.sucomunicacion.gedsys.entities.Entidad;
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
public class EntidadJpaController implements Serializable {

    public EntidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Entidad entidad) throws PreexistingEntityException, Exception {
        if (entidad.getDocumentoList() == null) {
            entidad.setDocumentoList(new ArrayList<Documento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Documento> attachedDocumentoList = new ArrayList<Documento>();
            for (Documento documentoListDocumentoToAttach : entidad.getDocumentoList()) {
                documentoListDocumentoToAttach = em.getReference(documentoListDocumentoToAttach.getClass(), documentoListDocumentoToAttach.getId());
                attachedDocumentoList.add(documentoListDocumentoToAttach);
            }
            entidad.setDocumentoList(attachedDocumentoList);
            em.persist(entidad);
            for (Documento documentoListDocumento : entidad.getDocumentoList()) {
                Entidad oldEntidadOfDocumentoListDocumento = documentoListDocumento.getEntidad();
                documentoListDocumento.setEntidad(entidad);
                documentoListDocumento = em.merge(documentoListDocumento);
                if (oldEntidadOfDocumentoListDocumento != null) {
                    oldEntidadOfDocumentoListDocumento.getDocumentoList().remove(documentoListDocumento);
                    oldEntidadOfDocumentoListDocumento = em.merge(oldEntidadOfDocumentoListDocumento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEntidad(entidad.getId()) != null) {
                throw new PreexistingEntityException("Entidad " + entidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Entidad entidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entidad persistentEntidad = em.find(Entidad.class, entidad.getId());
            List<Documento> documentoListOld = persistentEntidad.getDocumentoList();
            List<Documento> documentoListNew = entidad.getDocumentoList();
            List<Documento> attachedDocumentoListNew = new ArrayList<Documento>();
            for (Documento documentoListNewDocumentoToAttach : documentoListNew) {
                documentoListNewDocumentoToAttach = em.getReference(documentoListNewDocumentoToAttach.getClass(), documentoListNewDocumentoToAttach.getId());
                attachedDocumentoListNew.add(documentoListNewDocumentoToAttach);
            }
            documentoListNew = attachedDocumentoListNew;
            entidad.setDocumentoList(documentoListNew);
            entidad = em.merge(entidad);
            for (Documento documentoListOldDocumento : documentoListOld) {
                if (!documentoListNew.contains(documentoListOldDocumento)) {
                    documentoListOldDocumento.setEntidad(null);
                    documentoListOldDocumento = em.merge(documentoListOldDocumento);
                }
            }
            for (Documento documentoListNewDocumento : documentoListNew) {
                if (!documentoListOld.contains(documentoListNewDocumento)) {
                    Entidad oldEntidadOfDocumentoListNewDocumento = documentoListNewDocumento.getEntidad();
                    documentoListNewDocumento.setEntidad(entidad);
                    documentoListNewDocumento = em.merge(documentoListNewDocumento);
                    if (oldEntidadOfDocumentoListNewDocumento != null && !oldEntidadOfDocumentoListNewDocumento.equals(entidad)) {
                        oldEntidadOfDocumentoListNewDocumento.getDocumentoList().remove(documentoListNewDocumento);
                        oldEntidadOfDocumentoListNewDocumento = em.merge(oldEntidadOfDocumentoListNewDocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = entidad.getId();
                if (findEntidad(id) == null) {
                    throw new NonexistentEntityException("The entidad with id " + id + " no longer exists.");
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
            Entidad entidad;
            try {
                entidad = em.getReference(Entidad.class, id);
                entidad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entidad with id " + id + " no longer exists.", enfe);
            }
            List<Documento> documentoList = entidad.getDocumentoList();
            for (Documento documentoListDocumento : documentoList) {
                documentoListDocumento.setEntidad(null);
                documentoListDocumento = em.merge(documentoListDocumento);
            }
            em.remove(entidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Entidad> findEntidadEntities() {
        return findEntidadEntities(true, -1, -1);
    }

    public List<Entidad> findEntidadEntities(int maxResults, int firstResult) {
        return findEntidadEntities(false, maxResults, firstResult);
    }

    private List<Entidad> findEntidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Entidad.class));
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

    public Entidad findEntidad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Entidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Entidad> rt = cq.from(Entidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
